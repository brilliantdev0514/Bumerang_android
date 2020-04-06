package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iamhabib.easy_preference.EasyPreference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.UserProfileBusinessActivity;
import com.tur.bumerang.Global.API.UserProductAPI;
import com.tur.bumerang.Global.Adapter.Profile_history_gridAdapter;
import com.tur.bumerang.Global.Adapter.Profile_review_listAdapter;
import com.tur.bumerang.Global.Fragment.MainFragment;
import com.tur.bumerang.Global.Fragment.UserProfileReviewFragment;
import com.tur.bumerang.Global.Fragment.UserProfileHistoryFragment;
import com.tur.bumerang.Global.Model.ItemModel;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.Global.Model.ReviewModel;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.RentPageActivity;
import com.tur.bumerang.UserProfileMoreProductGrid.UserProfileMoreProductAll;
import com.tur.bumerang.UserProfileMoreProductGrid.UserProfileMoreReviewProductAll;
import com.tur.bumerang.Utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends BaseActivity {

    ViewPager viewPager;
    @BindView(R.id.imv_profile_avatar)
    RoundedImageView imv_profile_avatar;
    TextView txvbadge;

    //UsrProfileHistoryFragment
    Profile_history_gridAdapter profile_history_gridAdapter;
    ArrayList<ItemModel> itemModels = new ArrayList<>();
    LinearLayout lyt_no_product;
    GridView history_grid;
    String category, page_number;
    String user_id;

    //UserProfileReviewFragment
    //  UserProfileActivity userProfileActivity;
    Profile_review_listAdapter profile_review_listAdapter;
    ListView review_list;

    ImageView img_back;
    User CurUser = null;
    TextView txt_more;
    TextView txt_more_review;
    Button send_message_bussiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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
//            Glide
//                    .with(this)
//                    .load(CurUser.avatarUrl)
//                    .centerCrop()
//                    .placeholder(R.drawable.profile_icon)
//                    .into(imv_profile_avatar);

            ((ImageView)findViewById(R.id.imv_setting_profile)).setVisibility(View.GONE);


        }
//


        txvbadge = findViewById(R.id.txv_messagebadge);
        img_back = (ImageView) findViewById(R.id.img_back);

        lyt_no_product = findViewById(R.id.lyt_no_product);



        history_grid=(GridView) findViewById(R.id.history_grid);
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
                    if (EasyPreference.with(UserProfileActivity.this).getInt("easyRegistStatus", 0) == 1) {
                        Intent intent = new Intent(UserProfileActivity.this, RentPageActivity.class);
                        startActivity(intent);
//            startActivityForResult(intent,1);
                    } else {
                        showToast("You can't rent product.\n Please register as user. ");
                        return;
                    }
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurUser= null;

                Intent intent = new Intent(UserProfileActivity.this, HomeActivity.class);
                startActivity(intent);
//                onBackPressed();
                finish();
            }
        });



        user_id = EasyPreference.with(this).getString("easyUserId", "");

        category = "0";

        page_number = "1";

        profile_history_gridAdapter= new Profile_history_gridAdapter(this, itemModels);

        history_grid.setAdapter(profile_history_gridAdapter);

//        loadLayout();

        initData();
        getUserProducts(category);

/*
      viewPager=(ViewPager)findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGray), getResources().getColor(R.color.colorRed));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorRed));
       // tabLayout.setTabRippleColor(new ColorStateList(getResources().getColor(R.color.colorGray)));
        //(getResources().getColor(R.color.colorBlue));
        tabLayout.setupWithViewPager(viewPager);
        DemoCollectionPagerAdapter demoCollectionPagerAdapter= new DemoCollectionPagerAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(demoCollectionPagerAdapter);*/

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
    public void initData1(){
        User user = Common.user;
        if (user.getAvatarUrl() != null) {
            Glide
                    .with(this)
                    .load(user.getAvatarUrl())
                    .centerCrop()
                    .placeholder(R.drawable.profile_icon)
                    .into((ImageView) findViewById(R.id.imv_profile_avatar));
        }
        ((TextView)findViewById(R.id.txv_profile_name)).setText(CurUser.firstName+" "+CurUser.lastName);
//        ((TextView)findViewById(R.id.txt_name_profile)).setText(CurUser.lastName);

        if (!(user.getPhoneVerified().equals("")))
            ((TextView)findViewById(R.id.txt_verified_phon)).setText(R.string.txt_verified_phon);
        if (!(user.getEmailVerified().equals("")))
            ((TextView)findViewById(R.id.txt_verified_email)).setText(R.string.txt_verified_email);
        if (!(user.getIdCardVerified().equals("")))
            ((TextView)findViewById(R.id.txt_verified_id)).setText(R.string.txt_verified_id);
        if (!(user.getFacebookEmail().equals("")))
            ((TextView)findViewById(R.id.txt_verified_facebook)).setText(R.string.txt_verified_facebook);
        if (!(user.getGoogleEmail().equals("")))
            ((TextView)findViewById(R.id.txt_verified_google)).setText(R.string.txt_verified_google);
    }
    public String strCheck(String str){
        if (str == null)
            return "";
        return str;
    }
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        public DemoCollectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0) return "My products";  ////////////Rent History
            else if(position==1) return "Reviews(15)";
            else return "";
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            if(position==0) fragment = new UserProfileHistoryFragment();
            else if(position==1) fragment =  new UserProfileReviewFragment();
            return fragment;
        }
    }

    @OnClick(R.id.imv_setting_profile)
    void  goProfileSetting(){
        Intent intent = new Intent(UserProfileActivity.this, ProfileSettingActivity.class);
        startActivity(intent);
        finish();

    }

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
        super.removebadget();
        Intent intent =  new Intent(this,ChattingHistoryActivity.class );
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imv_box)
    void goBox(){
        Intent intent =  new Intent(this, Position_Search_City.class );
        startActivity(intent);
        finish();
    }

//    @OnClick(R.id.imv_userinfo)
//    void goUserProfile(){
//
//        Intent intent = new Intent(this, UserProfileActivity.class);
//        startActivity(intent);
//        finish();
//
//    }

    @OnClick(R.id.imv_plus)
    void goSelectCategory(){
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
        finish();
    }

    public void updatebagecount(int badgecount){
        super.updatebagecount(badgecount);
        if(badgecount>0){
//            txvbadge.setText(String.valueOf(badgecount));
            txvbadge.setVisibility(View.VISIBLE);
        }
        else
            txvbadge.setVisibility(View.INVISIBLE);
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



                        if(profile_history_gridAdapter.getCount() == 0){
                            lyt_no_product.setVisibility(View.VISIBLE);
                        }else{
                            lyt_no_product.setVisibility(View.INVISIBLE);
                        }


                    }else {
                        Log.d("request_mag===>", ReqConst.MSG);
                        profile_history_gridAdapter.loadData(itemModels);

                        if(profile_history_gridAdapter.getCount() == 0){
                            lyt_no_product.setVisibility(View.VISIBLE);
                        }else{
                            lyt_no_product.setVisibility(View.INVISIBLE);
                        }
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

    private  void loadLayout(){

        ArrayList<ReviewModel> reviewModels = new ArrayList<>();
        for(int i=0; i<21; i++){
            ReviewModel reviewModel = new ReviewModel();
//            reviewModel.setId(i);
//            int min = 0;
//            int max = 9;
//            int random = new Random().nextInt((max - min) + 1) + min;
//            reviewModel.setImagetype(random);
//
//            reviewModel.setId(i);
//            if(i%3 == 0) reviewModel.setFlag(true);

            reviewModels.add(reviewModel);

        }
        profile_review_listAdapter= new Profile_review_listAdapter(this, reviewModels);
        review_list.setAdapter(profile_review_listAdapter);
    }

}
