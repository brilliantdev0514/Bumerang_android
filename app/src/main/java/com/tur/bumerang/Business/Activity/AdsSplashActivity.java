package com.tur.bumerang.Business.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.Activity.UserProductsActivity;
import com.tur.bumerang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdsSplashActivity extends BaseActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_splash);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_ads_next)
    void goMyProduct(){
        Intent intent = new Intent(AdsSplashActivity.this, UserProductsActivity.class);
        startActivity(intent);
    }

}
