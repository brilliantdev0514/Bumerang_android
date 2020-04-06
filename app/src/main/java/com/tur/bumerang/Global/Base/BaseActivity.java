package com.tur.bumerang.Global.Base;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

public class BaseActivity extends AppCompatActivity {

    KProgressHUD kProgress;

    public void showToast(final String message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showToast(int id) {
        showToast(String.valueOf(id));
    }

    public KProgressHUD showProgress(){

        kProgress = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true).show();
        return kProgress;

    }

    public  void hideProgress(){
        if (kProgress != null)
            kProgress.dismiss();
    }



}
