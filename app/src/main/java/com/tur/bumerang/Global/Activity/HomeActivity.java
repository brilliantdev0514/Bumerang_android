package com.tur.bumerang.Global.Activity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.iamhabib.easy_preference.EasyPreference;
import com.ogaclejapan.arclayout.ArcLayout;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Blurry;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.MembershipActivity;
import com.tur.bumerang.Business.Activity.SelectCategoryRegisterActivity;
import com.tur.bumerang.Business.Activity.UserProfileBusinessActivity;
import com.tur.bumerang.Global.Fragment.HomeFragment;
import com.tur.bumerang.Global.Fragment.MainFragment;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;
import com.tur.bumerang.Utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements MainFragment.OnMainFragmentChangeListener{

    @BindView(R.id.lyt_bottom_bar)
    LinearLayout lyt_bottom_bar;
    @BindView(R.id.imv_plus)ImageView imv_plus;
    @BindView(R.id.btn_home_signup)Button btn_home_signup;
    @BindView(R.id.imv_chart) ImageView imv_chart;
    @BindView(R.id.imv_chat) ImageView imv_chat;
    @BindView(R.id.imv_box) ImageView imv_box;
    @BindView(R.id.imv_userinfo) ImageView imv_userinfo;

    public static boolean isClickedDontHaveAccount = false;

    @BindView(R.id.arcLayout)
    ArcLayout arcLayout;
    @BindView(R.id.viewBlackTransparent) View viewBlackTransparent;
//    @BindView(R.id.btnSignupWithStandard) Button btnSignupWithStandard;
//    @BindView(R.id.btnSignupWithBusiness) Button btnSignupWithBusiness;
//    @BindView(R.id.txv_messagebadge) TextView txvbadge;
    String userInfo;
    String findCity = null;
    boolean m_bNabBarHS = false;
    int loginstatus = 0;
    int remeberStatus = 0;
    String easyAuthType = null;
    TextView txvbadge;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        arcLayout.setVisibility(View.INVISIBLE);
        txvbadge = (TextView)findViewById(R.id.txv_messagebadge);

        userInfo = EasyPreference.with(this).getString("easyMembership", "");
        String owner_id = EasyPreference.with(this).getString("easyUserId", "");
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child(ReqConst.API_USER).child(owner_id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        Common.user = dataSnapshot.getValue(User.class);
                    }catch (Exception e){
                        Log.e("e", String.valueOf(e));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    closeProgress();
                }
            });
        btn_home_signup.setText(R.string.login);
        if(EasyPreference.with(this).getInt("easyRegistStatus",0) == 1) {
            btn_home_signup.setVisibility(View.GONE);
            lyt_bottom_bar.setVisibility(View.VISIBLE);
            imv_plus.setVisibility(View.VISIBLE);

        }else{
            btn_home_signup.setVisibility(View.VISIBLE);
            lyt_bottom_bar.setVisibility(View.GONE);
            imv_plus.setVisibility(View.GONE);
        }

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("HomeActivity:", "getInstanceId failed", task.getException());
                            return;
                        }

                        String token = task.getResult().getToken();
                        Log.d("Token!!!", token);
                    }
                });

        findCity = getIntent().getStringExtra("findCity");

        loginstatus =  EasyPreference.with(this).getInt("easyRegistStatus",0);
        remeberStatus = EasyPreference.with(this).getInt("easyRememberStatus", 0);
        easyAuthType = EasyPreference.with(this).getString("easyAuthType", "Email");
        m_bNabBarHS = false;
        if (!(loginstatus == 1 || remeberStatus == 1)){
            m_bNabBarHS = true;
            btn_home_signup.setVisibility(View.VISIBLE);
            imv_plus.setVisibility(View.GONE);
            lyt_bottom_bar.setVisibility(View.VISIBLE);
            btn_home_signup.setText(R.string.login);
        }

        goMainHome();
        showMessage();
    }
    public void showMessage(){
        int iCount = BaseActivity.badgecount;
        if (loginstatus == 0 || remeberStatus == 0 || iCount <= 0)
            return;
        View view = findViewById(R.id.lyt_totalcontainer);

        Blurry.with(this)
                .radius(10)
                .sampling(3)
                .color(Color.argb(40, 255, 255, 255))
                .async()
                .onto((ViewGroup) view);

        Dialog dialog  = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_new_msg);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        Button btnok=(Button)dialog.findViewById(R.id.btn_select_show);
        Button btncancel=(Button)dialog.findViewById(R.id.btn_select_cancel);
        TextView txtCount=(TextView)dialog.findViewById(R.id.txt_msg_count);
        txtCount.setText(String.valueOf(iCount)+getString(R.string.new_message));
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChat();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        this.finish();
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

    @Override
    public void updateServiceGroup(ServiceConnection conn, int group, int importance) {
        super.updateServiceGroup(conn, group, importance);
    }

    public void replaceTutorialFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                //.setCustomAnimations(R.animator.fragment_animation_fade_in, R.animator.fragment_animation_fade_out)
                .replace(R.id.lyt_maincontainer, fragment)
                .commit();
    }

    @OnClick(R.id.btn_home_signup)
    void goRegister(){

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

    }
    Dialog dialog;
    void  logOutdialog(){
        dialog  = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_log_out);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        Button btnok=(Button)dialog.findViewById(R.id.btn_select_logout);
        Button btncancel=(Button)dialog.findViewById(R.id.btn_select_cancel);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("Log out!");
                EasyPreference.with(HomeActivity.this)
                        .clearAll()
                        .save();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();

            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("Cancel button clicked!");
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @OnClick(R.id.imv_chart)
    void goMainHome()
    {
        Bundle bundle = new Bundle();
        bundle.putString("findCity", findCity);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        fragment.setOnMainFragmentChangeListener(this);
        replaceTutorialFragment(fragment);
        findCity = null;

    }

    @OnClick(R.id.imv_chat)
    void goChat()
    {
        if (m_bNabBarHS){
            showAlertDialog("Your can't access!");
            return;
        }
        super.removebadget();
        Intent intent =  new Intent(this,ChattingHistoryActivity.class );
        startActivity(intent);
    }

    @OnClick(R.id.imv_box)
    void goBox(){
        Intent intent =  new Intent(this, Position_Search_City.class );
        startActivity(intent);
    }

    @OnClick(R.id.imv_userinfo)
    void goUserProfile(){
        if (m_bNabBarHS){
            showAlertDialog("Your can't access!");
            return;
        }

        if(userInfo.length() == 0) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }else {
            Intent intent =  new Intent(this, UserProfileBusinessActivity.class);
            startActivity( intent);
        }
        finish();
    }

    @OnClick(R.id.imv_plus)
    void goSelectCategory(){
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
    }
     @OnClick(R.id.viewBlackTransparent) void hideBlackTransparent(){
        //hideMenu();
    }

    private Animator createHideItemAnimator(final View item) {
        float dx = btn_home_signup.getX() - item.getX();
        float dy = btn_home_signup.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }

    private Animator createShowItemAnimator(View item) {

        float dx = btn_home_signup.getX() - item.getX();
        float dy = btn_home_signup.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 0f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        return anim;
    }

//    @OnClick(R.id.btnSignupWithStandard) void signupWithStandard(){
////        Intent intent =  new Intent(this,RegisterActivity.class );
////        intent.putExtra(Constants.SHOW_USER_REGISTER_PAGE, Constants.SHOW_STANDARD_REGISTER_PAGE);
////        startActivity(intent);
////        finish();
//
//        Intent intent =  new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.btnSignupWithBusiness) void signupWithBusiness(){
////        Intent intent =  new Intent(this,RegisterActivity.class );
////        intent.putExtra(Constants.SHOW_USER_REGISTER_PAGE, Constants.SHOW_BUSINESS_REGISTER_PAGE);
////        startActivity(intent);
////        finish();
//        Intent intent =  new Intent(this, SelectCategoryRegisterActivity.class );
//        startActivity(intent);
//        finish();
//    }

    public void hideMenu(){
            btn_home_signup.setSelected(false);
            List<Animator> animList = new ArrayList<>();
            for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
                animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
            }
            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(800);
            animSet.setInterpolator(new AnticipateInterpolator());
            animSet.playTogether(animList);
            animSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    viewBlackTransparent.setVisibility(View.INVISIBLE);
                    arcLayout.setVisibility(View.INVISIBLE);
                }
            });
            animSet.start();
        }
    public void showMenu(){

            btn_home_signup.setSelected(true);
            viewBlackTransparent.setVisibility(View.VISIBLE);
            arcLayout.setVisibility(View.VISIBLE);

            List<Animator> animList = new ArrayList<>();

            for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
                animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
            }

            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(800);
            animSet.setInterpolator(new OvershootInterpolator());
            animSet.playTogether(animList);
            animSet.start();
        }

    @Override
    public void hideShowNav(boolean bIsShow){
        Log.e("bIsShow", String.valueOf(bIsShow));
        if(EasyPreference.with(this).getInt("easyRegistStatus",0) == 0)
            return;
//        if (!(easyAuthType != null && easyAuthType.equals("Email")) || loginstatus == 0 || remeberStatus == 0){
//            return;
//        }
        View view = findViewById(R.id.lyt_bottom_bar);
        if (bIsShow) {
            view.setVisibility(View.VISIBLE);
            if (m_bNabBarHS)
                btn_home_signup.setVisibility(View.VISIBLE);
            else
                imv_plus.setVisibility(View.VISIBLE);
        }else{
            if (m_bNabBarHS)
                btn_home_signup.setVisibility(View.GONE);
            else
                imv_plus.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
    }
}
