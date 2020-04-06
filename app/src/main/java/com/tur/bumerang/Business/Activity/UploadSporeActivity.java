package com.tur.bumerang.Business.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iamhabib.easy_preference.EasyPreference;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.Model.ProductModel;
import com.tur.bumerang.MyApp;
import com.tur.bumerang.R;
import com.tur.bumerang.Utils.MultiPartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadSporeActivity extends BaseActivity{


    @BindView(R.id.lyt_categoty_spore)
    LinearLayout lyt_categoty_spore;
    @BindView(R.id.edt_upload_spore_title)
    EditText edt_upload_spore_title;
    @BindView(R.id.edt_spore_des) EditText edt_spore_des;
    @BindView(R.id.edt_price) EditText edt_price;
    @BindView(R.id.priceRadioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.daily)
    RadioButton radioDaily;
    @BindView(R.id.weekly) RadioButton radioWeekly;
    @BindView(R.id.monthly) RadioButton radioMonthly;
    GoogleMap mMap;
    LatLng mLatLng = new LatLng(ReqConst.defaultLat, ReqConst.defaultlng);

    File imageFile;
    String imageSrc;
    int price, owner_id;
    String  title, date_unit, description, category;

    @BindView(R.id.btn_upload)
    Button btn_upload;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_spore);

        ButterKnife.bind(this);
        hideKeyboard(lyt_categoty_spore);
    }
    @OnClick(R.id.btn_upload)
    void Upload(){

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        btn_upload.startAnimation(myAnim);

        owner_id = EasyPreference.with(UploadSporeActivity.this).getInt("easyUserId", 0);


        description = edt_spore_des.getText().toString();


        title = edt_upload_spore_title.getText().toString().trim();
        if (title.length() == 0) {
            showToast("Input product name!");
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

        String price_string = edt_price.getText().toString();
        if(price_string.length() == 0){
            showToast("Input price!");
            return;
        }else{
            price = Integer.valueOf(price_string);
        }

        category = "8";

        uploadImage(imageFile);

    }

    private void uploadImage(File image){

        showProgress();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("title", title);
            params.put("owner_id", String.valueOf(owner_id));
            params.put("price", String.valueOf(price));
            params.put("date_unit", date_unit);
            params.put(ReqConst.DESCTIPTION, description);
            params.put("category", category);

            Log.d("parameter======", params.toString());


            String url = ReqConst.SERVER_URL + "product/uploadProduct";

            MultiPartRequest reqMultiPart = new MultiPartRequest(url, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    closeProgress();
                    Log.d("error===", error.getMessage());
                    //(UploadRentalcarActivity.this).showAlertDialog("File upload failed");
                }
            }, new Response.Listener<String>() {

                @Override
                public void onResponse(String json) {
                    Log.d("response_spore==", json);
                    closeProgress();
                   /* try {
                        JSONObject res = new JSONObject(json);
                        if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
                            ProductModel product = new ProductModel(res.getJSONObject("upload_info"));
                            Common.product = product;

                            showAlertDialog("Spore Upload Success!");

                            EasyPreference.with(UploadSporeActivity.this).addString("easySporeId", String.valueOf(product.id));

                        }else {
                            showAlertDialog(res.getString(ReqConst.MSG));
                        }
                    }catch (JSONException e){
                        (UploadSporeActivity.this).closeProgress();
                        (UploadSporeActivity.this).showAlertDialog(getString(R.string.serverFailed));
                    }*/
                }
            }, imageFile, "image", params);

            reqMultiPart.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApp.getInstance().addToRequestQueue(reqMultiPart, url);

        } catch (Exception e) {
            e.printStackTrace();
            closeProgress();
            (UploadSporeActivity.this).showAlertDialog("File upload failed");
        }
    }

    @OnClick(R.id.img_upload_prev)
    void carImageSelect(){
        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
                .setToolbarColor("#212121")         //  Toolbar color
                .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
                .setProgressBarColor("#4CAF50")     //  ProgressBar color
                .setBackgroundColor("#212121")      //  Background color
                .setCameraOnly(false)               //  Camera mode
                .setMultipleMode(false)              //  Select multiple images or single image
                .setFolderMode(true)                //  Folder mode
                .setShowCamera(true)                //  Show camera button
                .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
                .setImageTitle("Galleries")         //  Image title (works with FolderMode = false)
                .setDoneTitle("Done")               //  Done button title
                .setLimitMessage("You have reached selection limit")    // Selection limit message
                .setMaxSize(10)                     //  Max images can be selected
                .setSavePath("ImagePicker")         //  Image capture folder name
                //               .setSelectedImages(images)          //  Selected images
                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
                .setRequestCode(100)                //  Set request code, default Config.RC_PICK_IMAGES
                .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .start();                           //  Start ImagePicker
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            if(images.size()==0){
                Toast.makeText(this, "Please select one image", Toast.LENGTH_SHORT).show();
                return;
            }
            // do your logic here...
            Log.d("path==", images.get(0).getPath());

            imageSrc = images.get(0).getPath();
            imageFile = new File(imageSrc);

            ImageView imageView = (ImageView)findViewById(R.id.img_upload_prev);

            imageView.setImageURI(Uri.parse(new File(images.get(0).getPath()).toString()));
        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }

}
