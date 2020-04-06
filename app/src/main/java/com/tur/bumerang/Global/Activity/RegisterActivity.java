package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Business.Activity.SignupBusinessActivity;
import com.tur.bumerang.Global.Adapter.Register_setusertypeAdapter;
import com.tur.bumerang.Global.Model.RegisterModel;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.SignupStandardActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.btn_std_signup) Button btn_std_signup;
    @BindView(R.id.txv_login) Button txv_login;

    public DiscreteScrollView itemPicker;
    int register_type = 0;
    String user_type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        register_type = getIntent().getIntExtra(Constants.SHOW_USER_REGISTER_PAGE, 0);

        itemPicker = (DiscreteScrollView) findViewById(R.id.item_register_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                register_type = adapterPosition;
            }
        });
        ArrayList<RegisterModel> data = new ArrayList<>();

        RegisterModel registerModel = new RegisterModel(0,R.drawable.users_standard, "Register as standard user", "Standard user is able to rent products from business users");
        data.add(registerModel);

        RegisterModel registerModel1 = new RegisterModel(1,R.drawable.ic_house, "Register as business user", "Register user is able to upload their products until amount according to membership");
        data.add(registerModel1);

        itemPicker.setAdapter(new Register_setusertypeAdapter(data, this));
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.85f)
                .build());

        itemPicker.scrollToPosition(register_type);

//        if(register_type == 0){
//            user_type = "standard";
//            EasyPreference.with(RegisterActivity.this).addString("easyUserType", user_type).save();
//        }else{
//            user_type = "business";
//            EasyPreference.with(RegisterActivity.this).addString("easyUserType", user_type).save();
//        }
    }

    @OnClick(R.id.btn_std_signup)
    void goSignup(){
        if( register_type == 0 ){
            Intent intent = new Intent(this, SignupStandardActivity.class);
            startActivity(intent);
        }else if(register_type == 1){
            //showToast("Go to SignupBusinessActivity!");
            Intent intent = new Intent(this, SignupBusinessActivity.class);
            startActivity(intent);
        }

    }

    @OnClick(R.id.txv_login)
    void goLogin(){
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish();

        if( register_type == 0 ){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else if(register_type == 1){
            //showToast("Go to SignupBusinessActivity!");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }


}
