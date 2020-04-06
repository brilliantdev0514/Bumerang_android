package com.tur.bumerang.Global.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iamhabib.easy_preference.EasyPreference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.UserProfileBusinessActivity;
import com.tur.bumerang.Global.CustomeView.CustomMapView;
import com.tur.bumerang.Global.Fragment.SliderImageFragment;
import com.tur.bumerang.Global.Model.UserModel;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.RentPageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tur.bumerang.Global.Activity.DetailbikeActivity.GetStringArray;

public class DetailhomeActivity extends BaseActivity implements OnMapReadyCallback {

    UserModel user;

    @BindView(R.id.mapView) CustomMapView mapView;
    GoogleMap mGoogleMap;
//    LinearLayout LL_profile;
    String userInfo;
    String flat_id;
    @BindView(R.id.detail_flat_title) TextView detail_flat_title;
    @BindView(R.id.detail_flat_roomnumber) TextView detail_flat_roomnumber;
    @BindView(R.id.detail_flat_heating) TextView detail_flat_heating;
    @BindView(R.id.detail_owner_products) Button detail_owner_products;
    @BindView(R.id.detail_flat_fubished) TextView detail_flat_fubished;
    @BindView(R.id.detail_flat_price) TextView detail_flat_price;
    @BindView(R.id.detail_flat_deposit) TextView detail_flat_deposit;
    @BindView(R.id.detail_flat_des) EditText detail_flat_des;
    @BindView(R.id.detail_flat_address) TextView detail_flat_address;
    @BindView(R.id.detail_owner_avatar)
    RoundedImageView detail_owner_avatar;
    @BindView(R.id.detail_owner_name) TextView detail_owner_name;
    @BindView(R.id.deletePro) ImageView deletePro;
    @BindView(R.id.btn_rent_home) Button editandmessage;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailhome);

        ButterKnife.bind(this);

        flat_id = EasyPreference.with(this).getString("easyFlatId","");

        detail_flat_title.setText(Constants.itemModel.title);
        List<String> strPath = Constants.itemModel.image_url;
        String[]strPaths = GetStringArray((ArrayList<String>) strPath);
        SliderImageFragment sliderImageFragment = new SliderImageFragment(this, strPaths);
        getFragmentManager()
                .beginTransaction()
                //.setCustomAnimations(R.animator.fragment_animation_fade_in, R.animator.fragment_animation_fade_out)
                .replace(R.id.viewPage, sliderImageFragment)
                .commit();
//        final ImagePopup imagePopup = new ImagePopup(this);
//        imagePopup.setWindowHeight(1000); // Optional
//        imagePopup.setWindowWidth(1000); // Optional
//        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
//        imagePopup.setFullScreen(true); // Option al
//        imagePopup.setHideCloseIcon(true);  // Optional
//        imagePopup.setImageOnClickClose(true);  // Optional
//
//        imagePopup.initiatePopupWithGlide(Constants.itemModel.image_url);

//        LL_profile=(LinearLayout) findViewById(R.id.LL_profile);

//        LL_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(DetailhomeActivity.this, UserProfileBusinessActivity.class);
//                startActivity(intent);
//            }
//        });
        //TODO; delete owner products from firebase;
        deletePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("owenerid", Constants.itemModel.owner_id);
                Log.v("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                if (!Constants.itemModel.owner_id.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    showAlertDialog("You can't delete this product!");
                }
                else {
                    new AlertDialog.Builder(DetailhomeActivity.this)
                            .setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this product?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    Log.v("product id ", String.valueOf(Constants.itemModel.id));

                                    DatabaseReference deleting = FirebaseDatabase.getInstance().getReference().child("product");
                                    deleting.child(String.valueOf(Constants.itemModel.id)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent = new Intent(DetailhomeActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });


                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });

        detail_flat_roomnumber.setText(String.valueOf(Constants.itemModel.room_number));
        detail_flat_heating.setText(Constants.itemModel.heating);
        detail_flat_fubished.setText(Constants.itemModel.furbished);
        detail_flat_price.setText(String.valueOf(Constants.itemModel.price) + "₺" + "\n" + Constants.itemModel.date_unit);
        detail_flat_deposit.setText(Constants.itemModel.deposit);
        detail_flat_des.setText(Constants.itemModel.description);
        detail_flat_address.setText(Constants.itemModel.address);
        Log.v("address", String.valueOf(Constants.itemModel.id));
        detail_owner_name.setText(Constants.itemModel.owner_info.firstName + " " + Constants.itemModel.owner_info.lastName);
//        detail_owner_score.setText(Constants.itemModel.owner_info.score);
        Glide
                .with(this)
                .load(Constants.itemModel.owner_info.avatarUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_useravatar_new)
                .into(detail_owner_avatar);

        detail_owner_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo = Constants.itemModel.owner_info.membership;
//                Intent intent=new Intent(DetailhomeActivity.this,UserProfileBusinessActivity.class);
//                intent.putExtra("owner_id", Constants.itemModel.owner_id);
//                startActivity(intent);
                if(userInfo.length() == 0) {
                    Intent intent = new Intent(DetailhomeActivity.this, UserProfileActivity.class);
                    intent.putExtra("owner_id", Constants.itemModel.owner_id);
                    startActivity(intent);
                }else {
                    Intent intent =  new Intent(DetailhomeActivity.this, UserProfileBusinessActivity.class);
                    intent.putExtra("owner_id", Constants.itemModel.owner_id);
                    startActivity( intent);
                }
            }
        });

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setRating(0);
//        ratingBar.setSpacing(10);
        ratingBar.setIsIndicator(false);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(0.5F);
//        ratingBar.setProgress(null);
 //       ratingBar.setProgressed(null);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        String owner_id = EasyPreference.with(this).getString("easyUserId", "");
        if(owner_id.equals(Constants.itemModel.owner_id)){
            editandmessage.setText("Düzenle");
            deletePro.setVisibility(View.VISIBLE);
            editandmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editandmessage.getText().toString().equals("Düzenle")) {
                        detail_flat_des.setFocusable(true);
                        detail_flat_des.setFocusableInTouchMode(true);
                        detail_flat_des.setClickable(true);
                        detail_flat_des.requestFocus();

                        editandmessage.setText("Kayıt etmek");
                    } else if (editandmessage.getText().toString().equals("Kayıt etmek")) {
                        DatabaseReference des = FirebaseDatabase.getInstance().getReference().child("product").child(Constants.itemModel.id).child("description");
                        des.setValue(detail_flat_des.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DetailhomeActivity.this, "Başarıyı kaydedin!", Toast.LENGTH_SHORT).show();
                                detail_flat_des.setFocusable(false);
                                detail_flat_des.setFocusableInTouchMode(false);
                                detail_flat_des.setClickable(false);
                                editandmessage.setText("Düzenle");
                            }
                        });
                    }

                }
            });
        }
        else{
            editandmessage.setText(R.string.alread_rent);
            editandmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Constants.itemModel.rental_state != null && Constants.itemModel.rental_state.equals("Yes")) {
                    //send message
                    showToast("Another user rent this Product already.\n Please find another one. ");
                } else {
                    if (EasyPreference.with(DetailhomeActivity.this).getInt("easyRegistStatus", 0) == 1) {
                        Intent intent = new Intent(DetailhomeActivity.this, ChattingActivity.class);
                        intent.putExtra("owner_id", Constants.itemModel.owner_id);
                        startActivity(intent);
//            startActivityForResult(intent,1);
                    } else {
                        showToast("You can't rent product.\n Please register as user. ");
                        return;
                    }
                }
            }
            });

        }





    }

//    @OnClick(R.id.btn_rent_home)
//    void goRentPage(){
//        if(Constants.itemModel.rental_state != null && Constants.itemModel.rental_state.equals("Yes")) {
//            //send message
//        }else {
//            if (EasyPreference.with(this).getInt("easyRegistStatus", 0) == 1) {
//                Intent intent = new Intent(this, RentPageActivity.class);
//                startActivity(intent);
////            startActivityForResult(intent,1);
//            } else {
//                showToast("You can't rent product.\n Please register as user. ");
//                return;
//            }
//        }
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng myLocation = new LatLng(Double.valueOf(Constants.itemModel.lat), Double.valueOf(Constants.itemModel.lng));

        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.addMarker(new MarkerOptions().position(myLocation));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10f));
        mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }

//    public void downloadProduct(){
//
//        final Response.Listener<String> res = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String json) {
//                Log.d("response_home==", json);
//                closeProgress();
//                try{
//                    JSONObject res = new JSONObject(json);
//                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
//                        ApartmentModel apart = new ApartmentModel(res.getJSONObject("product_info"));
//                        Common.apart = apart;
//
//                        detail_flat_title.setText(apart.title);
//                        detail_flat_roomnumber.setText(apart.room_number);
//                        detail_flat_heating.setText(apart.heating);
//                        detail_flat_fubished.setText(apart.furbished);
//                        detail_flat_price.setText(apart.price);
//                        detail_flat_deposit.setText(apart.deposit);
//                        detail_flat_des.setText(apart.description);
//                        detail_flat_address.setText(apart.address);
//                        Picasso.with(DetailhomeActivity.this).load(ReqConst.SERVER_URL + apart.image).into(detail_flat_image);
//                        Picasso.with(DetailhomeActivity.this).load(ReqConst.SERVER_URL + user.avatarUrl).into(detail_owner_imv);
//                        detail_owner_name.setText(user.getLastName() + user.getFirstName());
//                        detail_owner_score.setText(user.score);
//
//
//                    }else {
//                        showAlertDialog(res.getString(ReqConst.MSG));
//                    }
//
//                }catch (JSONException e){
//                    showAlertDialog(e.getMessage());
//                }
//            }
//        };
//        final Response.ErrorListener error = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                closeProgress();
//                showAlertDialog(getString(R.string.serverFailed));
//            }
//        };
//
//        showProgress();
//        DetailHomeAPI req = new DetailHomeAPI(flat_id, res, error);
//        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue request = Volley.newRequestQueue(this);
//        request.add(req);
//
//    }
//
//    @OnClick(R.id.imv_detail_chat)
//    void goChatting(){
//        Intent intent = new Intent(this, ChattingActivity.class);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.imv_share_btn)
//    void onSharing(){
//        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//        sharingIntent.setType("text/plain");
//        String shareBody = "Here is the share content body";
//        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
//        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//        startActivity(Intent.createChooser(sharingIntent, "Share via"));
//    }
}
