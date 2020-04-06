package com.tur.bumerang.Business.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Global.Activity.LoginActivity;
import com.tur.bumerang.Global.Base.BaseActivity;
import com.tur.bumerang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectCategoryRegisterActivity extends BaseActivity {

    @BindView(R.id.card_sel_cate_house) RelativeLayout card_sel_cate_house;
    @BindView(R.id.card_sel_cate_car) RelativeLayout card_sel_cate_car;
    @BindView(R.id.card_sel_cate_caravan) RelativeLayout card_sel_cate_caravan;
    @BindView(R.id.card_sel_cate_vehicle) RelativeLayout card_sel_cate_vehicle;
    @BindView(R.id.card_sel_cate_dress) RelativeLayout card_sel_cate_dress;


    Dialog dialog;
    String strCategoryType = null;
    TextView textView;
    String strType = "";

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_register);

        ButterKnife.bind(this);
        strType = getIntent().getStringExtra("type");
    }


    @OnClick(R.id.card_sel_cate_house)
    void onSelectedHouse(){
        strCategoryType = getResources().getString(R.string.house);
        goMembershipPage();
//        showSelectedProduct(house);
    }


    @OnClick(R.id.card_sel_cate_car)
    void onSelectedCar(){

        strCategoryType = getResources().getString(R.string.car);
        goMembershipPage();
     //   showSelectedProduct(car);
    }

    @OnClick(R.id.card_sel_cate_caravan)
    void onSelectedCaravan(){

        strCategoryType = getResources().getString(R.string.caravan);
        goMembershipPage();
       // showSelectedProduct(caravan);
    }

    @OnClick(R.id.card_sel_cate_vehicle)
    void onSelectedVehicle(){

        strCategoryType = getResources().getString(R.string.vehicle);
        goMembershipPage();
        //showSelectedProduct(vehicle);
    }

    @OnClick(R.id.card_sel_cate_dress)
    void onSelectedDress(){

        strCategoryType = getResources().getString(R.string.dress);
        goMembershipPage();
        // showSelectedProduct(dress);
    }

    @OnClick(R.id.txv_close)
    void onClose(){
        finish();
    }

    void goMembershipPage(){
//        strCategoryType
        EasyPreference.with(this).addString("easyUserType", strCategoryType).save();

        Intent intent = new Intent(SelectCategoryRegisterActivity.this, MembershipActivity.class);
        intent.putExtra("type", strType);
        startActivity(intent);
    }

    void showSelectedProduct(String productname){
        dialog  = new Dialog(SelectCategoryRegisterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_select_category);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        Button btnok=(Button)dialog.findViewById(R.id.btn_selcate_ok);
        Button btncancel=(Button)dialog.findViewById(R.id.btn_selcate_cancel);
        textView = (TextView)dialog.findViewById(R.id.txv_category_alert_title);
        textView.setText(productname);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("Ok button clicked!");
                dialog.dismiss();
                Intent intent = new Intent(SelectCategoryRegisterActivity.this, MembershipActivity.class);
                startActivity(intent);

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

}
