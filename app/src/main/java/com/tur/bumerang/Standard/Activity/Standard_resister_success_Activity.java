package com.tur.bumerang.Standard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.R;

public class Standard_resister_success_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_completed);


        Button goLoginBtn = (Button)findViewById(R.id.add_product);


        goLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Standard_resister_success_Activity.this , HomeActivity.class);
                startActivity(i);
            }
        });


    }

}
