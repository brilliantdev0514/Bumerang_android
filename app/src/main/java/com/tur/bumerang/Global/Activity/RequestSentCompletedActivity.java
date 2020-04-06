package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.RentPageActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestSentCompletedActivity extends BaseActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_completed);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_request_complete)
    void goProductPage(){
        Intent intent = new Intent(RequestSentCompletedActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }
}
