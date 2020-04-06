package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Business.Activity.UploadApartmentActivity;
import com.tur.bumerang.R;

import butterknife.ButterKnife;

public class RegisterCompletedActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_completed);
//        ImageView imgView = (ImageView)findViewById(R.id.success_back);

        Button add_product = (Button)findViewById(R.id.add_product) ;
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterCompletedActivity.this, HomeActivity.class));
                finish();
            }
        });

    }

}
