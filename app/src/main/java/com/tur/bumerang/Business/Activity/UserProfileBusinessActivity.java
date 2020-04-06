package com.tur.bumerang.Business.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iamhabib.easy_preference.EasyPreference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.Activity.ChattingHistoryActivity;
import com.tur.bumerang.Global.Activity.DetailCarActivity;
import com.tur.bumerang.Global.Activity.DetailbikeActivity;
import com.tur.bumerang.Global.Activity.DetailcameraActivity;
import com.tur.bumerang.Global.Activity.DetailcaravanActivity;
import com.tur.bumerang.Global.Activity.DetaildressActivity;
import com.tur.bumerang.Global.Activity.DetailhomeActivity;
import com.tur.bumerang.Global.Activity.DetailkampActivity;
import com.tur.bumerang.Global.Activity.DetailmusicActivity;
import com.tur.bumerang.Global.Activity.DetailsporeActivity;
import com.tur.bumerang.Global.Activity.DetailvehicleActivity;
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.Position_Search_City;
import com.tur.bumerang.Global.Activity.ProfileSettingActivity;
import com.tur.bumerang.Global.Activity.SelectCategoryActivity;
import com.tur.bumerang.Global.Activity.UserProfileActivity;
import com.tur.bumerang.Global.Model.ItemModel;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.Global.Model.ReviewModel;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.RentPageActivity;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileBusinessActivity extends BaseActivity {

    UserProfileBusinessActivity userProfileBusinessActivity;
    Profile_review_listAdapter_business profile_review_listAdapter;
    Profile_history_gridAdapter_business profile_history_gridAdapter;


    ArrayList<ItemModel> itemModels = new ArrayList<>();
    ListView review_list;
    GridView history_grid;

    @BindView(R.id.imv_profile_avatar)
    RoundedImageView imv_profile_avatar;
    @BindView(R.id.txv_profile_name) TextView txv_profile_name;
//    @BindView(R.id.txv_messagebadge) TextView txvbadge;
    TextView txvbadge;
    String category, page_number;
    File imageFile;
    String imageSrc;
    User CurUser = null;
    Button send_message_bussiness;
    String userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_business);
        ButterKnife.bind(this);

        CurUser = Common.user;
        String strOwner = getIntent().getStringExtra("owner_id");
        if (strOwner != null && !strOwner.equals("")){
            this.CurUser = Constants.itemModel.owner_info;
            if (Constants.itemModel.owner_info.getId() == null || Constants.itemModel.owner_info.getId().equals("")) {
                showToast(getString(R.string.user_info_no));
                finish();
                return;
            }
            ((ImageView)findViewById(R.id.imv_setting_profile)).setVisibility(View.GONE);

        }

//        Glide
//                .with(this)
//                .load(Constants.itemModel.owner_info.avatarUrl)
//                .centerCrop()
//                .placeholder(R.drawable.profile_icon)
//                .into(imv_profile_avatar);

        txv_profile_name.setText(CurUser.firstName + " " + CurUser.lastName);
        txvbadge = (TextView)findViewById(R.id.txv_messagebadge);
        review_list=(ListView)findViewById(R.id.list_view_business);
        review_list.setVisibility(View.GONE);
        history_grid=(GridView)findViewById(R.id.history_grid);
        userInfo = EasyPreference.with(this).getString("easyUserType", "");
        send_message_bussiness = findViewById(R.id.send_message_business);

        if (EasyPreference.with(this).getString("easyUserId", "").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            send_message_bussiness.setVisibility(View.GONE);
        }
        send_message_bussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(com.tur.bumerang.Base.Constants.itemModel.rental_state != null && Constants.itemModel.rental_state.equals("Yes")) {
                    //send message
                }else {
                    if (EasyPreference.with(UserProfileBusinessActivity.this).getInt("easyRegistStatus", 0) == 1) {
                        Intent intent = new Intent(UserProfileBusinessActivity.this, RentPageActivity.class);
                        startActivity(intent);
//            startActivityForResult(intent,1);
                    } else {
                        showToast("You can't rent product.\n Please register as user. ");
                        return;
                    }
                }
            }
        });
        category = "0";
        page_number = "1";

        profile_history_gridAdapter= new Profile_history_gridAdapter_business(this, itemModels);
        history_grid.setAdapter(profile_history_gridAdapter);
        loadLayout();
        initData();
        getUserProducts(category);


        if (!(CurUser.getPhoneVerified().equals("")))
            ((TextView)findViewById(R.id.txt_verified_phon)).setText(R.string.txt_verified_phon);
        if (!(CurUser.getEmailVerified().equals("")))
            ((TextView)findViewById(R.id.txt_verified_email)).setText(R.string.txt_verified_email);
        if (!(CurUser.getIdCardVerified().equals("")))
            ((TextView)findViewById(R.id.txt_verified_id)).setText(R.string.txt_verified_id);
        if (!(CurUser.getFacebookEmail().equals("")))
            ((TextView)findViewById(R.id.txt_verified_facebook)).setText(R.string.txt_verified_facebook);
        if (!(CurUser.getGoogleEmail().equals("")))
            ((TextView)findViewById(R.id.txt_verified_google)).setText(R.string.txt_verified_google);

    }
    public void initData(){
//        User user = Common.user;


        Glide
                .with(this)
                .load(CurUser.avatarUrl)
                .centerCrop()
                .placeholder(R.drawable.profile_icon)
                .into(imv_profile_avatar);


        ((TextView)findViewById(R.id.txv_profile_name)).setText(CurUser.firstName+" "+CurUser.lastName);
//        ((TextView)findViewById(R.id.txt_name_profile)).setText(CurUser.lastName);

//        if (!(user.getPhoneVerified().equals("")))
//            ((TextView)findViewById(R.id.txt_verified_phon)).setText(R.string.txt_verified_phon);
//        if (!(user.getEmailVerified().equals("")))
//            ((TextView)findViewById(R.id.txt_verified_email)).setText(R.string.txt_verified_email);
//        if (!(user.getIdCardVerified().equals("")))
//            ((TextView)findViewById(R.id.txt_verified_id)).setText(R.string.txt_verified_id);
//        if (!(user.getFacebookEmail().equals("")))
//            ((TextView)findViewById(R.id.txt_verified_facebook)).setText(R.string.txt_verified_facebook);
//        if (!(user.getGoogleEmail().equals("")))
//            ((TextView)findViewById(R.id.txt_verified_google)).setText(R.string.txt_verified_google);
    }
    private  void loadLayout(){

        ArrayList<ReviewModel> reviewModels = new ArrayList<>();

        for(int i=0; i<11; i++){
            ReviewModel reviewModel = new ReviewModel();
            reviewModels.add(reviewModel);

        }


        profile_review_listAdapter= new Profile_review_listAdapter_business(this, reviewModels);
        review_list.setAdapter(profile_review_listAdapter);

    }

    private void getUserProducts(String category) {

        /*final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_myproduct==", json);
                closeProgress();
                try{

                    JSONObject res = new JSONObject(json);

                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){

                        JSONArray req_array = res.getJSONArray("product_info");

                        for( int i=0 ; i<req_array.length(); i++){

                            JSONObject req = req_array.getJSONObject(i);

                            ItemModel one = new ItemModel(req);
                            itemModels.add(one);
                        }

                        profile_history_gridAdapter.loadData(itemModels);

                        LogUtil.e("aaaa===" + itemModels.size());



//                        if(profile_history_gridAdapter.getCount() == 0){
//                            lyt_no_product.setVisibility(View.VISIBLE);
//                        }else{
//                            lyt_no_product.setVisibility(View.INVISIBLE);
//                        }


                    }else {
                        Log.d("request_mag===>", ReqConst.MSG);
//                        profile_history_gridAdapter.loadData(itemModels);

//                        if(profile_history_gridAdapter.getCount() == 0){
//                            lyt_no_product.setVisibility(View.VISIBLE);
//                        }else{
//                            lyt_no_product.setVisibility(View.INVISIBLE);
//                        }
                    }

                }catch (JSONException e){
                    showAlertDialog(e.getMessage());
                }
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                closeProgress();
                showAlertDialog(getString(R.string.serverFailed));
            }
        };

        showProgress();
        UserProductAPI req = new UserProductAPI(user_id, category, page_number, res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);*/

        showProgress();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child(ReqConst.API_PRODUCT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                closeProgress();
                itemModels = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Product mProduct = ds.getValue(Product.class);
                    if (mProduct.owner_id != null && CurUser.id != null && mProduct.owner_id.equals(CurUser.id))
                    {
                        ItemModel one = new ItemModel(mProduct);
                        itemModels.add(one);

                    }
                }

                profile_history_gridAdapter.loadData(itemModels);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @OnClick(R.id.btn_back)
    void goBack(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserProfileBusinessActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

//
    @OnClick(R.id.imv_chart)
    void goMainHome()
    {
//        replaceTutorialFragment(new MainFragment());
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imv_chat)
    void goChat()
    {
        Intent intent =  new Intent(this, ChattingHistoryActivity.class );
        startActivity(intent);
        finish();
    }
//
    @OnClick(R.id.imv_box)
    void goBox(){
        Intent intent =  new Intent(this, Position_Search_City.class );
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imv_userinfo)
    void goUserProfile(){

        Intent intent = new Intent(this, UserProfileBusinessActivity.class);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.imv_plus)
    void goSelectCategory(){
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imv_setting_profile)
    void  goProfileSetting(){
        Intent intent = new Intent(UserProfileBusinessActivity.this, ProfileSettingActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void updatebagecount(int badgecount){
        super.updatebagecount(badgecount);
        if(badgecount>0){
//            txvbadge.setText(String.valueOf(badgecount));
            txvbadge.setVisibility(View.VISIBLE);
        }
        else
            txvbadge.setVisibility(View.INVISIBLE);
    }

//    void setAvatarPhoto(){
//
//        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
//                .setToolbarColor("#212121")         //  Toolbar color
//                .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
//                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
//                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
//                .setProgressBarColor("#4CAF50")     //  ProgressBar color
//                .setBackgroundColor("#212121")      //  Background color
//                .setCameraOnly(false)               //  Camera mode
//                .setMultipleMode(true)              //  Select multiple images or single image
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
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            if(images.size()==0){
                Toast.makeText(this, "Please select one image", Toast.LENGTH_SHORT).show();
                return;
            }
            // do your logic here...
            Log.d("path====>", images.get(0).getPath());
            //String src = data.getDataString();

            imageSrc = images.get(0).getPath();
            imageFile = new File(imageSrc);

            //RoundedImageView imageView = (RoundedImageView)findViewById(R.id.imv_user_avatar_setting);

            // imv_user_avatar_setting.setImageURI(Uri.parse(new File(images.get(0).getPath()).toString()));
//            Picasso.get()
//                    .load(images.get(0).getPath())
//                    .fit()
//                    .into(imv_user_avatar_setting);
            Glide.with(this)
                    .load(Uri.fromFile(new File(images.get(0).getPath())))
                    .into(imv_profile_avatar);
        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }

}

class Profile_review_listAdapter_business extends BaseAdapter {
    private UserProfileBusinessActivity activity;
    Profile_review_listAdapter_business profile_review_listAdapter;
    private ArrayList<ReviewModel> sonses = new ArrayList<>();

    public Profile_review_listAdapter_business(UserProfileBusinessActivity context, ArrayList<ReviewModel> orders) {

        super();
        this.activity = context;
        sonses=orders;
    }

    @Override
    public int getCount() {
        return sonses.size();
    }

    @Override
    public Object getItem(int position) {
        return sonses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CustomHolder holder;
        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_userprofile_review, parent, false);
//            holder.roundedImageView=(RoundedImageView) convertView.findViewById(R.id.imageView);
//            holder.imv_flag=(ImageView)convertView.findViewById(R.id.imv_rent_bookmark);
//           /* holder.txvuniqueid=(TextView)convertView.findViewById(R.id.txv_uniqueid);
//            holder.txvorderdate=(TextView)convertView.findViewById(R.id.txv_orderdate);
//            holder.btn_confirm=(TextView)convertView.findViewById(R.id.btn_confirm);*/
//            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

//        final ReviewModel trip1 = (ReviewModel) sonses.get(position);
//        int loadimage=-1;
//        if(trip1.getImagetype()==0)loadimage = R.mipmap.default_house_img;
//        else if(trip1.getImagetype()==1)loadimage = R.mipmap.default_car_img;
//        else if(trip1.getImagetype()==2)loadimage = R.mipmap.default_car_img;
//        else if(trip1.getImagetype()==3)loadimage = R.mipmap.default_house_img;
//        else if(trip1.getImagetype()==4)loadimage = R.mipmap.default_dress_img;
//        else if(trip1.getImagetype()==5)loadimage = R.mipmap.default_bike_img;
//        else if(trip1.getImagetype()==6)loadimage = R.mipmap.default_camera_img;
//        else if(trip1.getImagetype()==7)loadimage = R.mipmap.default_camera_img;
//        else if(trip1.getImagetype()==8)loadimage = R.mipmap.default_camera_img;
//        else if(trip1.getImagetype()==9)loadimage = R.mipmap.default_camera_img;
//
//        Log.d("imagetype===", String.valueOf(position)+"____"+String.valueOf(trip1.getImagetype()));
//
//        Glide
//                .with(activity)
//                .load(loadimage)
//                .centerCrop()
//                .placeholder(loadimage)
//                .into(holder.roundedImageView);
//
//        if(trip1.getFlag()) holder.imv_flag.setVisibility(View.VISIBLE);
//        else holder.imv_flag.setVisibility(View.INVISIBLE);
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Constants.ReviewModel=trip1;
//                if(trip1.getImagetype()==0){
//                    Intent intent = new Intent(activity, DetailhomeActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==1){
//                    Intent intent = new Intent(activity, DetailCarActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==2){
//                    Intent intent = new Intent(activity, DetailcaravanActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==3){
//                    Intent intent = new Intent(activity, DetailvehicleActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==4){
//                    Intent intent = new Intent(activity, DetaildressActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==5){
//                    Intent intent = new Intent(activity, DetailbikeActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==6){
//                    Intent intent = new Intent(activity, DetailcameraActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==7){
//                    Intent intent = new Intent(activity, DetailsporeActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==8){
//                    Intent intent = new Intent(activity, DetailkampActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==9){
//                    Intent intent = new Intent(activity, DetailmusicActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }
//            }
//        });
        return convertView;
    }

    public class CustomHolder {
//        RoundedImageView roundedImageView;
//        ImageView imv_flag;
        //TextView txvuniqueid, txvstatus,  txvorderdate, btn_confirm;

    }

}


class Profile_history_gridAdapter_business extends BaseAdapter {
    private UserProfileBusinessActivity activity;
    private ArrayList<ItemModel> sonses = new ArrayList<>();

    public Profile_history_gridAdapter_business(UserProfileBusinessActivity context, ArrayList<ItemModel> orders) {

        super();
        this.activity = context;
    }

    public void loadData( ArrayList<ItemModel> data){
//        this.sonses.removeAll(data);
        this.sonses.clear();
        this.sonses.addAll(data);

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return sonses.size();
    }

    @Override
    public Object getItem(int position) {
        return sonses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CustomHolder holder;
        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_userprofile_history, parent, false);
            holder.roundedImageView=(RoundedImageView) convertView.findViewById(R.id.imv_userprofile_his_image);
            holder.imv_flag=(ImageView)convertView.findViewById(R.id.imv_rent_bookmark);
            holder.txv_title = (TextView)convertView.findViewById(R.id.txv_userprofile_his_title);
            holder.txv_deposit = (TextView)convertView.findViewById(R.id.txv_userprofile_his_deposit);
            holder.txv_price = (TextView)convertView.findViewById(R.id.txv_userprofile_his_price);
            holder.txv_dateunit = (TextView)convertView.findViewById(R.id.txv_userprofile_his_dateunit);

            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final ItemModel trip1 = (ItemModel) sonses.get(position);
        int loadimage=-1;
        if(trip1.getImagetype()==0)loadimage = R.mipmap.default_house_img;
        else if(trip1.getImagetype()==1)loadimage = R.mipmap.default_car_img;
        else if(trip1.getImagetype()==2)loadimage = R.mipmap.default_car_img;
        else if(trip1.getImagetype()==3)loadimage = R.mipmap.default_house_img;
        else if(trip1.getImagetype()==4)loadimage = R.mipmap.default_dress_img;
        else if(trip1.getImagetype()==5)loadimage = R.mipmap.default_bike_img;
        else if(trip1.getImagetype()==6)loadimage = R.mipmap.default_camera_img;
        else if(trip1.getImagetype()==7)loadimage = R.mipmap.default_camera_img;
        else if(trip1.getImagetype()==8)loadimage = R.mipmap.default_camera_img;
        else if(trip1.getImagetype()==9)loadimage = R.mipmap.default_camera_img;

        Log.d("imagetype===", String.valueOf(position)+"____"+String.valueOf(trip1.getImagetype()));

        Glide
                .with(activity)
                .load(trip1.image_url.get(0))
                .centerCrop()
                .placeholder(loadimage)
                .into(holder.roundedImageView);
        holder.txv_title.setText(trip1.title);
        holder.txv_deposit.setText(trip1.deposit);
        holder.txv_price.setText(trip1.price+"₺"+" "+"/"+" ");
        holder.txv_dateunit.setText(trip1.date_unit);


        if(trip1.getFlag()) holder.imv_flag.setVisibility(View.VISIBLE);
        else holder.imv_flag.setVisibility(View.INVISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Constants.itemModel=trip1;
                if(trip1.getImagetype()==0){
                    Intent intent = new Intent(activity, DetailhomeActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==1){
                    Intent intent = new Intent(activity, DetailCarActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==2){
                    Intent intent = new Intent(activity, DetailcaravanActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==3){
                    Intent intent = new Intent(activity, DetailvehicleActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==4){
                    Intent intent = new Intent(activity, DetaildressActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==5){
                    Intent intent = new Intent(activity, DetailbikeActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==6){
                    Intent intent = new Intent(activity, DetailcameraActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==7){
                    Intent intent = new Intent(activity, DetailsporeActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==8){
                    Intent intent = new Intent(activity, DetailkampActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==9){
                    Intent intent = new Intent(activity, DetailmusicActivity.class);
                    activity.startActivity(intent);
                }
            }
        });


        return convertView;
    }


    public class CustomHolder {
        RoundedImageView roundedImageView;
        ImageView imv_flag;
        TextView txv_title, txv_deposit, txv_price, txv_dateunit;
    }

}

