package com.tur.bumerang.Global.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.btnStart) Button btnStart;

    private final int PERMISSION_REQ_CODE = 102;

    String email, pwd, auth_type;

    String[] PERMISSIONS = {android.Manifest.permission.INTERNET,android.Manifest.permission.ACCESS_NETWORK_STATE,
            //android.Manifest.permission.READ_EXTERNAL_STORAGE,  android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.VIBRATE, Manifest.permission.CALL_PHONE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        checkAllPermission();
        //ebubread();
    }

    @OnClick(R.id.btnStart) void gotoIntroduce(){
        View view = findViewById(R.id.splash);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 10);
        myAnim.setInterpolator(interpolator);
        btnStart.startAnimation(myAnim);

        //EasyPreference.with(this).clearAll().save();
        int loginstatus =  EasyPreference.with(SplashActivity.this).getInt("easyRegistStatus",0);

        email = EasyPreference.with(SplashActivity.this).getString("easyUserEmail","");
        pwd = EasyPreference.with(SplashActivity.this).getString("easyUserPassword","");
        auth_type = EasyPreference.with(SplashActivity.this).getString("easyAuthType", "");

        Log.d("easyUserEmail =======>", email);
        Log.d("easyUserPassword ==>", pwd);
        Log.d("easyRegistStatus ==>", String.valueOf(loginstatus));
        Log.d("easyAuthType ==>", auth_type);
        if(loginstatus==0){
            Intent intent = new Intent(this, IntroduceActivity.class);
            startActivity(intent);
            finish();

        }else if(loginstatus == 1) {

            /*final Response.Listener<String> res = json -> {
                Log.d("response_login==", json);
                closeProgress();
                try{
                    JSONObject res1 = new JSONObject(json);
                    if (res1.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
                        UserModel user = new UserModel(res1.getJSONObject("user_info"));
                        Common.user = user;
                       // Common.user.setId(1);
                        if(EasyPreference.with(SplashActivity.this).getInt("easyRememberStatus", 0) == 1) {
                            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(EasyPreference.with(SplashActivity.this).getInt("easyRememberStatus", 0) == 0){
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity( intent);
                            finish();
                        }

                    }else {

                        EasyPreference.with(SplashActivity.this).addInt("easyRegistStatus",0).save();
                        Intent intent = new Intent(SplashActivity.this, IntroduceActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }catch (JSONException e){
                    showAlertDialog(e.getMessage());
                }
            };
            final Response.ErrorListener error = error1 -> {
                closeProgress();
                showAlertDialog(getString(R.string.serverFailed));
            };

            showProgress();
            LoginAPI req = new LoginAPI(email, pwd, auth_type,res, error);
            req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueue request = Volley.newRequestQueue(this);
            request.add(req);*/

            if(EasyPreference.with(SplashActivity.this).getInt("easyRememberStatus", 0) == 1) {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }else if(EasyPreference.with(SplashActivity.this).getInt("easyRememberStatus", 0) == 0){
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity( intent);
                finish();
            }

//            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_USER);
//            mRef.orderByChild(ReqConst.EMAIL).equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot ds: dataSnapshot.getChildren())
//                    {
//                        User currentUser = dataSnapshot.getValue(User.class);
//                        Common.user = currentUser;
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
        }

    }

    public void checkAllPermission() {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        if (hasPermissions(this, PERMISSIONS)){
//            getAllNotification();
        }else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 101);
        }
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {

            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) { }
//        getAllNotification();
    }



}

class MyBounceInterpolator implements android.view.animation.Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
