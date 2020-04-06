package com.tur.bumerang.Business.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iamhabib.easy_preference.EasyPreference;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.Position_Search_City;
import com.tur.bumerang.Global.Activity.RegisterCompletedActivity;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class UploadVehicleActivity extends BaseActivity implements OnMapReadyCallback {


    @BindView(R.id.lyt_categoty_vehicle)LinearLayout lyt_categoty_vehicle;
    @BindView(R.id.img_upload_prev)ImageView imv_upload_vehicle;
    @BindView(R.id.spinner_vehicle_captan) Spinner spinner_vehicle_captan;
    @BindView(R.id.edt_vehicle_title) EditText edt_vehicle_title;
    @BindView(R.id.edt_vehicle_des) EditText edt_vehicle_des;
    @BindView(R.id.edt_address) TextView edt_address;
    @BindView(R.id.priceRadioGroup) RadioGroup radioGroup;
    @BindView(R.id.daily) RadioButton daily;
    @BindView(R.id.weekly) RadioButton weekly;
    @BindView(R.id.monthly) RadioButton monthly;
    @BindView(R.id.edt_price) EditText edt_price;
    @BindView(R.id.edt_person_capacity) EditText edt_person_capacity;
    GoogleMap mMap;
    LatLng mLatLng = new LatLng(ReqConst.defaultLat, ReqConst.defaultlng);

    String price, person_capacity;
    String title, captan,  date_unit, description, category,address;
    String owner_id;
    String [] fileList = {null, null, null, null, null, null};

    String [] filePath = {null, null, null, null, null, null};
    int uploadFileIndex = 0;
    @BindView(R.id.btn_upload)
    Button btn_upload;

    public int iSuccessIndex = 0;
    public int iTotalcount = 0;
    public String strImgPath = "";
    FrameLayout mapFramelayout;
    ScrollView scr_upload_vehicle;
    String lat = String.valueOf(ReqConst.defaultLat);
    String lng = String.valueOf(ReqConst.defaultlng);
Product mProduct = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_vehicle);
        ButterKnife.bind(this);

//        mapFramelayout = (FrameLayout)findViewById(R.id.ryt_map);
        scr_upload_vehicle = (ScrollView)findViewById(R.id.scr_upload_caravan);

        ImageView imv_setting_profile = (ImageView) findViewById(R.id.imv_setting_profile);
        ImageView img_back = (ImageView) findViewById(R.id.img_back);
        TextView text_toolbar = (TextView) findViewById(R.id.text_toolbar);

        img_back.setVisibility(View.VISIBLE);
        imv_setting_profile.setVisibility(View.INVISIBLE);
        text_toolbar.setVisibility(View.VISIBLE);
        text_toolbar.setText(R.string.vehicle);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        edt_address.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    showProgress();
                    onMapSearch();
                }
                return false;
            }
        });
        //hideKeyboard(lyt_categoty_vehicle);
        showImage();
        getInitData();
    }
     @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(mLatLng).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 5.0f));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }
    public void setSearchText(Address address){
        String strCountry = address.getCountryName();
        String strLocal = address.getLocality();
        if (strLocal == null)
            strLocal = address.getAdminArea();

        edt_address.setText((strCountry == null ? "" : strCountry)+(strLocal == null ? "" : ", "+strLocal));
    }
    public void onMapSearch() {
        String location = edt_address.getText().toString();
        List<Address> addressList = null;

        if (location == null || location.equals("")) {
            closeProgress();
            return;
        }
        Geocoder geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        closeProgress();
        if(addressList == null || addressList.size() <= 0) {
            showAlertDialog("Can't find the city");
            return;
        }
        setSearchText(addressList.get(0));
        mLatLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(mLatLng).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 5.0f));
    }

    @Override
    protected void onStart() {
        getaddress(Common.lat, Common.lng);
        super.onStart();
    }
    private void getInitData(){
        String strId = getIntent().getStringExtra("productId");
        if (strId == null || strId.equals(null) || strId.equals(""))
            return;

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);
        Query selectQuery = mDatabase.orderByChild("id").equalTo(strId);
        selectQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    mProduct = snapshot.getValue(Product.class);

                    edt_vehicle_title.setText(mProduct.title);

                    edt_person_capacity.setText(mProduct.person_capacity);
                    String [] str = getResources().getStringArray(R.array.spinner_captan);
                    for (int i = 0; i < str.length; i++)
                        if (str[i].equals(mProduct.captan)) {
                            spinner_vehicle_captan.setSelection(i);
                            break;
                        }

                    daily.setChecked(false);
                    weekly.setChecked(false);
                    monthly.setChecked(false);
                    if (mProduct.date_unit.equals(getString(R.string.daily)))
                        daily.setChecked(true);
                    else if(mProduct.date_unit.equals(getString(R.string.weekly)))
                        weekly.setChecked(true);
                    else if(mProduct.date_unit.equals(getString(R.string.monthly)))
                        monthly.setChecked(true);

                    edt_price.setText(mProduct.price);
                    edt_vehicle_des.setText(mProduct.description);

                    List<String> strPaths = mProduct.image_url;
                    int [] iArrImg = {R.id.img_upload_0, R.id.img_upload_1, R.id.img_upload_2, R.id.img_upload_3, R.id.img_upload_4, R.id.img_upload_5};
                    for (int i = 0; i < strPaths.size(); i++) {
                        filePath[i] = strPaths.get(i);
                    }
                    showImage();
                    getaddress(Double.valueOf(mProduct.lat), Double.valueOf(mProduct.lng));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @OnClick(R.id.btn_upload)
    void Upload(){

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        btn_upload.startAnimation(myAnim);

        owner_id = EasyPreference.with(UploadVehicleActivity.this).getString("easyUserId", "");


        address =  edt_address.getText().toString();
        if (address.length() ==  0){
            showToast("Input your address.");
            return;
        }
        spinner_vehicle_captan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        String description_string = edt_vehicle_des.getText().toString();
        if (description_string.length() == 0){
            showToast("Input your descriptiion!");
            return;
        }
        else{
            description = description_string;
        }


        title = edt_vehicle_title.getText().toString();
        if (title.length() == 0) {
            showToast("Input product name!");
            return;
        }
        captan = spinner_vehicle_captan.getSelectedItem().toString();
        if(captan.length() == 0){
            showToast("Select captan!");
            return;
        }

        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find which radioButton is checked by id
        if(selectedId == daily.getId()) {

            date_unit = daily.getText().toString();
            Log.d("date_unit =====", date_unit);

        } else if(selectedId == weekly.getId()) {

            date_unit = weekly.getText().toString();
            Log.d("date_unit =====", date_unit);

        } else {

            date_unit = monthly.getText().toString();
            Log.d("date_unit =====", date_unit);

        }

        String price_string = edt_price.getText().toString();
        if(price_string.length() == 0){
            showToast("Input price!");
            return;
        }else{
            price = price_string;
        }

        String person_string = edt_person_capacity.getText().toString();
        if(person_string.length() == 0){
            showToast("Input Person Capacity!");
            return;
        }else{
            person_capacity = person_string;
        }

        category = "4";

        uploadImage();

    }

    @OnClick(R.id.img_upload_0)
    void carImageSelect0() {
        uploadFileIndex = 0;
        showImage();
    }
    @OnClick(R.id.img_upload_1)
    void carImageSelect1() {
        uploadFileIndex = 1;
        showImage();
    }
    @OnClick(R.id.img_upload_2)
    void carImageSelect2() {
        uploadFileIndex = 2;
        showImage();
    }
    @OnClick(R.id.img_upload_3)
    void carImageSelect3() {
        uploadFileIndex = 3;
        showImage();
    }
    @OnClick(R.id.img_upload_4)
    void carImageSelect4() {
        uploadFileIndex = 4;
        showImage();
    }
    @OnClick(R.id.img_upload_5)
    void carImageSelect5() {
        uploadFileIndex = 5;
        showImage();
    }
    void showImage(){
        ImageView img_prev = (ImageView)findViewById(R.id.img_upload_prev);
        Glide
                .with(this)
                .load(R.drawable.add)
                .into(img_prev);
        for (int i = 0; i < filePath.length; i++) {
            ImageView imageView = null;
            switch (i) {
                case 0:
                    imageView = (ImageView) findViewById(R.id.img_upload_0);
                    break;
                case 1:
                    imageView = (ImageView) findViewById(R.id.img_upload_1);
                    break;
                case 2:
                    imageView = (ImageView) findViewById(R.id.img_upload_2);
                    break;
                case 3:
                    imageView = (ImageView) findViewById(R.id.img_upload_3);
                    break;
                case 4:
                    imageView = (ImageView) findViewById(R.id.img_upload_4);
                    break;
                case 5:
                    imageView = (ImageView) findViewById(R.id.img_upload_5);
                    break;
            }
            if (filePath[i] != null && !filePath[i].equals("")) {
                Glide
                        .with(this)
                        .load(filePath[i])
                        .into(imageView);
                if (i == uploadFileIndex)
                    Glide
                            .with(this)
                            .load(filePath[i])
                            .into(img_prev);
            }
        }
    }

    @OnClick(R.id.img_upload_prev)

    void carImageSelect(){


        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Config.RC_PICK_IMAGES);
//        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
//                .setToolbarColor("#212121")         //  Toolbar color
//                .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
//                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
//                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
//                .setProgressBarColor("#4CAF50")     //  ProgressBar color
//                .setBackgroundColor("#212121")      //  Background color
//                .setCameraOnly(false)               //  Camera mode
//                .setMultipleMode(false)              //  Select multiple images or single image
//                .setFolderMode(true)                //  Folder mode
//                .setShowCamera(true)                //  Show camera button
//                .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
//                .setImageTitle("Galleries")         //  Image title (works with FolderMode = false)
//                .setDoneTitle("Done")               //  Done button title
//                .setLimitMessage("You have reached selection limit")    // Selection limit message
//                .setMaxSize(10)                     //  Max images can be selected
//                .setSavePath("ImagePicker")         //  Image capture folder name
//                //               .setSelectedImages(images)          //  Selected images
//                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
//                .setRequestCode(100)                //  Set request code, default Config.RC_PICK_IMAGES
//                .setKeepScreenOn(true)              //  Keep screen on when selecting images
//                .start();                           //  Start ImagePicker
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            if (uploadFileIndex < 0)
                uploadFileIndex = 0;
            filePath[uploadFileIndex] = data.getData().toString();
            showImage();
        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here


    }


    private void uploadImage(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        int i = 0;
        int iEmpty = 0;
        for (i = 0; i < filePath.length; i++)
            if (filePath[i] == null || filePath[i].equals(null) || filePath[i].equals(""))
                iEmpty++;


        if (i == iEmpty){
            showAlertDialog("No Image is selected");
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        for (i = 0; i < filePath.length; i++){
            if (filePath[i] == null || filePath[i].equals(null) || filePath[i].equals(""))
                continue;

            iTotalcount++;
            int finalI = i;

        StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
        ref.putFile(Uri.parse(filePath[i]))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                iSuccessIndex++;
                                if (iTotalcount == iSuccessIndex){
                                    strImgPath += uri.toString();
                                    fileList[finalI] = uri.toString();

                                    progressDialog.dismiss();
                                String productId = GenerateRandomString.randomString(28);
                                if (mProduct != null && mProduct.id != null && !mProduct.id.equals(""))
                                    productId = mProduct.id;
                                    List nameList = new ArrayList<String>(Arrays.asList(fileList));

                                    String membership = EasyPreference.with(UploadVehicleActivity.this).getString("easyMembership", "");
                                Product mProduct = new Product(owner_id, category, title, "null", "null", "null", "null", "null", "null", "null",
                                        "null", person_capacity, captan, "null", "null", "null", price, date_unit, "null", description, nameList, address, lat, lng,
                                        "null", "null", "null", productId, "null", "null", null, membership);

                                //                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);
                                mRef.child(productId).setValue(mProduct);
                                Common.product = mProduct;

                                showAlertDialog("Vehicle Upload Success!");

                                EasyPreference.with(UploadVehicleActivity.this).addString("easyVehicleId", String.valueOf(mProduct.id));
                                startActivity(new Intent(UploadVehicleActivity.this, RegisterCompletedActivity.class));
                                    finish();
                                }
                                strImgPath += uri.toString() + "@";
                                fileList[finalI] = uri.toString();

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        iSuccessIndex++;
//                        if (iTotalcount == iSuccessIndex){
//                            strImgPath += filePath[iSuccessIndex-1];
//                            progressDialog.dismiss();
//                          String productId = GenerateRandomString.randomString(28);
//                            if (mProduct != null && mProduct.id != null && !mProduct.id.equals(""))
//                                productId = mProduct.id;
//                            String membership = EasyPreference.with(UploadVehicleActivity.this).getString("easyMembership", "");
//                            Product mProduct = new Product(owner_id, category, title, "null", "null", "null", "null", "null", "null", "null",
//                                    "null", person_capacity, captan, "null", "null", "null", price, date_unit, "null", description, strImgPath, address, lat, lng,
//                                    "null", "null", "null", productId, "null", "null", null, membership);
//
//                            //                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);
//                            mRef.child(productId).setValue(mProduct);
//                            Common.product = mProduct;
//
//                            showAlertDialog("Vehicle Upload Success!");
//
//                            EasyPreference.with(UploadVehicleActivity.this).addString("easyVehicleId", String.valueOf(mProduct.id));
//                            startActivity(new Intent(UploadVehicleActivity.this, RegisterCompletedActivity.class));
//                            finish();
//                        }
//                        strImgPath += filePath[iSuccessIndex-1] + "@";
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    }
                });
        }

//        try {
//            Map<String, String> params = new HashMap<>();
//            params.put("title", title);
//            params.put("owner_id", String.valueOf(owner_id));
//            params.put("person_capacity", String.valueOf(person_capacity));
//            params.put("captan", captan);
//            params.put("price", String.valueOf(price));
//            params.put("date_unit", date_unit);
//            params.put(ReqConst.DESCTIPTION, description);
//            params.put("category", category);
//
//            Log.d("parameter======", params.toString());
//
//
//            String url = ReqConst.SERVER_URL + "product/uploadProduct";
//
//            MultiPartRequest reqMultiPart = new MultiPartRequest(url, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    closeProgress();
//                    Log.d("error===", error.getMessage());
//                    //(UploadRentalcarActivity.this).showAlertDialog("File upload failed");
//                }
//            }, new Response.Listener<String>() {
//
//                @Override
//                public void onResponse(String json) {
//                    Log.d("response_vehicle==", json);
//                    closeProgress();
//                   /* try {
//                        JSONObject res = new JSONObject(json);
//                        if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
//                            ProductModel product = new ProductModel(res.getJSONObject("upload_info"));
//                            Common.product = product;
//
//                            showAlertDialog("Sea Verhicle Upload Success!");
//
//                            EasyPreference.with(UploadVehicleActivity.this).addString("easyVehicleId", String.valueOf(product.id));
//
//                        }else {
//                            showAlertDialog(res.getString(ReqConst.MSG));
//                        }
//                    }catch (JSONException e){
//                        (UploadVehicleActivity.this).closeProgress();
//                        (UploadVehicleActivity.this).showAlertDialog(getString(R.string.serverFailed));
//                    }*/
//                }
//            }, imageFile, "image", params);
//
//            reqMultiPart.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            MyApp.getInstance().addToRequestQueue(reqMultiPart, url);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            closeProgress();
//            (UploadVehicleActivity.this).showAlertDialog("File upload failed");
//        }
    }
    public void getaddress(double latitude, double longitude){
        try {
            Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
            lat = String.valueOf(latitude);
            lng = String.valueOf(longitude);
            if (addresses.isEmpty()) {
                edt_address.setText(R.string.wait_location);
            }
            else {
                if (addresses.size() > 0) {
                    String strCountry = addresses.get(0).getCountryName();
                    String strLocality = addresses.get(0).getLocality();
                    if (strLocality == null)
                        strLocality = addresses.get(0).getAdminArea();
                    edt_address.setText((strCountry == null ? "" : strCountry)+(strLocality == null ? "" : ", "+strLocality));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // getFromLocation() may sometimes fail
        }
    }

}
