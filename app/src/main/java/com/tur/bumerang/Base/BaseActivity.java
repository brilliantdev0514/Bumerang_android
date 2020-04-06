package com.tur.bumerang.Base;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tur.bumerang.R;

import java.util.Map;

public class BaseActivity extends AppCompatActivity {
    Firebase ref;
    public static int badgecount=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//            ref = new Firebase(ReqConst.FIREBASE_URL + ReqConst.API_notification +  String.valueOf(1));

        ref = new Firebase(ReqConst.FIREBASE_URL + ReqConst.API_notification + Common.user.getId());
        getMessageNotification();
    }

    private ProgressDialog _progressDlg;

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

    public void showProgress(boolean cancelable) {

        closeProgress();

        _progressDlg = new ProgressDialog(this, R.style.progressTheme);
        _progressDlg
                .setProgressStyle(android.R.style.Widget_ProgressBar_Large);
        _progressDlg.setCancelable(cancelable);
        _progressDlg.show();
    }

    public void showProgress() {
        showProgress(false);
    }

    public void closeProgress() {
        if(_progressDlg == null) {
            return;
        }

        _progressDlg.dismiss();
        _progressDlg = null;
    }


    public void hideKeyboard(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
    }

    public void showAlertDialog(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(msg);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok),

                (dialogInterface, i) -> {

                });
        alertDialog.show();
    }

    public void setupUI(View view, Activity activity) {
// Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    try{
                        hideSoftKeyboard(activity);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        }
//If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyboard(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    private void getMessageNotification(){
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Firebase childRef = ref.child(dataSnapshot.getKey());
                childRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        badgecount++;
                        //  Map map = dataSnapshot.getValue(Map.class);
                        updatebagecount(badgecount);

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                       // Map map = dataSnapshot.getValue(Map.class);
                        badgecount=0;
                        updatebagecount(badgecount);

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
//
    public void updatebagecount(int badgecount) {


    }


//    public void updatebagecount(int badgecount){
//        super.updatebagecount(badgecount);
//        if(badgecount>0){
//            txvbadge.setText(String.valueOf(badgecount));
//            txvbadge.setVisibility(View.VISIBLE);
//        }
//        else
//            txvbadge.setVisibility(View.INVISIBLE);
//    }

    public void removebadget(){
        ref.removeValue();
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
