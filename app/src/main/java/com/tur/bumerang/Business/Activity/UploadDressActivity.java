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

public class UploadDressActivity extends BaseActivity implements OnMapReadyCallback {


    @BindView(R.id.lyt_categoty_dress)LinearLayout lyt_categoty_dress;
    @BindView(R.id.edt_upload_dress_title)EditText edt_upload_dress_title;
    @BindView(R.id.spinner_gender)Spinner spinner_gender;
    @BindView(R.id.spinner_size) Spinner spinner_size;
    @BindView(R.id.spinner_color ) Spinner spinner_color;
    @BindView(R.id.edt_upload_dress_des) EditText edt_upload_dress_des;
    @BindView(R.id.priceRadioGroup)RadioGroup radioGroup;
    @BindView(R.id.daily)RadioButton radioDaily;
    @BindView(R.id.weekly) RadioButton radioWeekly;
    @BindView(R.id.monthly) RadioButton radioMonthly;
    @BindView(R.id.edt_price) EditText edt_price;
    @BindView(R.id.edt_address) TextView edt_address;
    @BindView(R.id.btn_upload)
    Button btn_upload;
    GoogleMap mMap;
    LatLng mLatLng = new LatLng(ReqConst.defaultLat, ReqConst.defaultlng);

    ScrollView scr_upload_dress;
    String price;
    String title, gender,size, color, date_unit, description, category, address;
    String owner_id;
    String [] fileList = {null, null, null, null, null, null};

    String [] filePath = {null, null, null, null, null, null};
    int uploadFileIndex = 0;
    String lat = String.valueOf(ReqConst.defaultLat);
    String lng = String.valueOf(ReqConst.defaultlng);
   Product mProduct = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_dress);
        scr_upload_dress = (ScrollView)findViewById(R.id.scr_upload_dress);
        ButterKnife.bind(this);
        ImageView imv_setting_profile = (ImageView) findViewById(R.id.imv_setting_profile);
        ImageView img_back = (ImageView) findViewById(R.id.img_back);
        TextView text_toolbar = (TextView) findViewById(R.id.text_toolbar);

        img_back.setVisibility(View.VISIBLE);
        imv_setting_profile.setVisibility(View.INVISIBLE);
        text_toolbar.setVisibility(View.VISIBLE);
        text_toolbar.setText(R.string.dress);

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
        hideKeyboard(lyt_categoty_dress);

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

                    edt_upload_dress_title.setText(mProduct.title);

                    String [] str = getResources().getStringArray(R.array.spinner_gender);
                    for (int i = 0; i < str.length; i++)
                        if (str[i].equals(mProduct.gender)) {
                            spinner_gender.setSelection(i);
                            break;
                        }

                    str = getResources().getStringArray(R.array.spinner_size);
                    for (int i = 0; i < str.length; i++)
                        if (str[i].equals(mProduct.size)) {
                            spinner_size.setSelection(i);
                            break;
                        }
                    str = getResources().getStringArray(R.array.spinner_color);
                    for (int i = 0; i < str.length; i++)
                        if (str[i].equals(mProduct.color)) {
                            spinner_color.setSelection(i);
                            break;
                        }

                    radioDaily.setChecked(false);
                    radioWeekly.setChecked(false);
                    radioMonthly.setChecked(false);
                    if (mProduct.date_unit.equals(getString(R.string.daily)))
                        radioDaily.setChecked(true);
                    else if(mProduct.date_unit.equals(getString(R.string.weekly)))
                        radioWeekly.setChecked(true);
                    else if(mProduct.date_unit.equals(getString(R.string.monthly)))
                        radioMonthly.setChecked(true);

                    edt_price.setText(mProduct.price);
                    edt_upload_dress_des.setText(mProduct.description);

                    List<String> strPaths = mProduct.image_url;
                    int [] iArrImg = {R.id.img_upload_0, R.id.img_upload_1, R.id.img_upload_2, R.id.img_upload_3, R.id.img_upload_4, R.id.img_upload_5};
                    for (int i = 0; i < strPaths.size(); i++) {
                        Glide.with(UploadDressActivity.this)
                                .load(strPaths.get(i))
                                .into((ImageView)findViewById(iArrImg[i]));

                        filePath[i] = strPaths.get(i);
                    }
                    getaddress(Double.valueOf(mProduct.lat), Double.valueOf(mProduct.lng));
                    showImage();
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

        owner_id = EasyPreference.with(UploadDressActivity.this).getString("easyUserId", "");

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        description = edt_upload_dress_des.getText().toString();


        title = edt_upload_dress_title.getText().toString().trim();
        if (title.length() == 0) {
            showToast("Input product name!");
            return;
        }

        gender = spinner_gender.getSelectedItem().toString();
        if (gender.length() ==  0){
            showToast("Select gender!");
            return;
        }

        size = spinner_size.getSelectedItem().toString();
        if(size.length() == 0){
            showToast("Select dress size!");
            return;
        }

        color = spinner_color.getSelectedItem().toString();
        if(size.length() == 0){
            showToast("Select dress color!");
            return;
        }

        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find which radioButton is checked by id
        if(selectedId == radioDaily.getId()) {

            date_unit = radioDaily.getText().toString();
            Log.d("date_unit =====", date_unit);

        } else if(selectedId == radioWeekly.getId()) {

            date_unit = radioWeekly.getText().toString();
            Log.d("date_unit =====", date_unit);

        } else {

            date_unit = radioMonthly.getText().toString();
            Log.d("date_unit =====", date_unit);

        }

        address =  edt_address.getText().toString().trim();
        if (address.length() ==  0){
            showToast("Input your address.");
            return;
        }

        String price_string = edt_price.getText().toString();
        if(price_string.length() == 0){
            showToast("Input price!");
            return;
        }else{
            price = price_string;
        }

        category = "5";

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
                if (i == uploadFileIndex){
                    Glide
                        .with(this)
                        .load(filePath[i])
                        .into(img_prev);
                }
            }
        }
    }
    public int iSuccessIndex = 0;
    public int iTotalcount = 0;
    public String strImgPath = "";
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

                                    String membership = EasyPreference.with(UploadDressActivity.this).getString("easyMembership", "");
                                    Product mProduct = new Product(owner_id, category, title, "null", "null", "null", "null", "null", "null", "null",
                                            "null", "null", "null", gender, size, color, price, date_unit, "null", description, nameList, address, lat, lng,
                                            "null", "null", "null", productId, "null", "null", null, membership);

    //                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);
                                    mRef.child(productId).setValue(mProduct);
                                    Common.product = mProduct;

                                    showAlertDialog("Dress Upload Success!");

                                    EasyPreference.with(UploadDressActivity.this).addString("easyDressId", String.valueOf(mProduct.id));
                                    startActivity(new Intent(UploadDressActivity.this, HomeActivity.class));
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
//                            String productId = GenerateRandomString.randomString(28);
//                            if (mProduct != null && mProduct.id != null && !mProduct.id.equals(""))
//                                productId = mProduct.id;
//
//                            String membership = EasyPreference.with(UploadDressActivity.this).getString("easyMembership", "");
//                            Product mProduct = new Product(owner_id, category, title, "null", "null", "null", "null", "null", "null", "null",
//                                    "null", "null", "null", gender, size, color, price, date_unit, "null", description, strImgPath, address, lat, lng,
//                                    "null", "null", "null", productId, "null", "null", null, membership);
//
//                            //                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);
//                            mRef.child(productId).setValue(mProduct);
//                            Common.product = mProduct;
//
//                            showAlertDialog("Dress Upload Success!");
//
//                            EasyPreference.with(UploadDressActivity.this).addString("easyDressId", String.valueOf(mProduct.id));
//                            startActivity(new Intent(UploadDressActivity.this, RegisterCompletedActivity.class));
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

    }

    public void getaddress(double latitude, double longitude){
        try {
            Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
            lat = String.valueOf(latitude);
            lng = String.valueOf(lng);
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
                    //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // getFromLocation() may sometimes fail
        }
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
        // so ImagePicker can work with fragment mode
    }

}
