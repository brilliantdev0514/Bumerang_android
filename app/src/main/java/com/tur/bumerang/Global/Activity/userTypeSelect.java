package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Business.Activity.MembershipActivity;
import com.tur.bumerang.Business.Activity.SelectCategoryRegisterActivity;
import com.tur.bumerang.Business.Activity.SignupBusinessActivity;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.SignupStandardActivity;

public class userTypeSelect extends BaseActivity {


    public String strType = "";
    //public Button btn_usertype_standard,btn_usertype_business;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype);
        strType = getIntent().getStringExtra("type");
//        btn_usertype_standard = findViewById(R.id.btn_usertype_standard);
//        btn_usertype_business = findViewById(R.id.btn_usertype_business);

    }
    public void click_standard_Button(View view){
        EasyPreference.with(this).addString("easyUserType",getString(R.string.standard)).save();
        Intent intent = new Intent(this, SignupStandardActivity.class);
        if (strType != null && strType.equals("upgrade"))
            intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        finish();
    }
    public void click_business_Button(View view){
        EasyPreference.with(this).addString("easyUserType",getString(R.string.business)).save();
        Intent intent = new Intent(this, SelectCategoryRegisterActivity.class);
        intent.putExtra("type", strType);
        startActivity(intent);
        finish();

    }
}



