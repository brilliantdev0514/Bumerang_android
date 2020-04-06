package com.tur.bumerang.Global.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.MembershipActivity;
import com.tur.bumerang.Business.Activity.UploadApartmentActivity;
import com.tur.bumerang.Business.Activity.UploadBikeActivity;
import com.tur.bumerang.Business.Activity.UploadCameraActivity;
import com.tur.bumerang.Business.Activity.UploadCaravanActivity;
import com.tur.bumerang.Business.Activity.UploadDressActivity;
import com.tur.bumerang.Business.Activity.UploadKampActivity;
import com.tur.bumerang.Business.Activity.UploadMusicActivity;
import com.tur.bumerang.Business.Activity.UploadOtherActivity;
import com.tur.bumerang.Business.Activity.UploadRentalcarActivity;
import com.tur.bumerang.Business.Activity.UploadVehicleActivity;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCategoryActivity extends BaseActivity {

    int iTotalCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        String strUserType = Common.user.userType;
        if (strUserType == null || strUserType.equals("") || strUserType.equals(R.string.standard)){

        }
        else if (strUserType.equals(getResources().getString(R.string.house)))
            goUploadApartment();
        else if (strUserType.equals(getResources().getString(R.string.car)))
            goUploadRentalcar();
        else if (strUserType.equals(getResources().getString(R.string.dress)))
            goUploadDress();
        else if (strUserType.equals(getResources().getString(R.string.caravan)))
            goUploadCaravan();
        else if (strUserType.equals(getResources().getString(R.string.vehicle)))
            goUploadVehicle();
//        showProgress();
//        if (Common.user.membership != null && Common.user.membership.equals("0"))
//            iTotalCount = 20;
//        else if (Common.user.membership != null && Common.user.membership.equals("1"))
//            iTotalCount = 100;
//        else if (Common.user.membership != null && Common.user.membership.equals("2"))
//            iTotalCount = 300;

//        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
//        mRef.child(ReqConst.API_PRODUCT).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                int iCount = 0;
//                for (DataSnapshot ds: dataSnapshot.getChildren())
//                {
//                    Product mProduct = ds.getValue(Product.class);
//                    int iCategory = 0;
//                    try {
//                        iCategory = Integer.valueOf(mProduct.category);
//                    }catch (Exception e){
//                        iCategory = 0;
//                    }
//                    if (Common.user.id.equals(mProduct.owner_id) && iCategory > 0 && iCategory < 6)
//                        iCount++;
//                }
//                String strUserType = Common.user.userType;
//                if (strUserType.equals(getResources().getString(R.string.standard))){
//                    if (iCount > 4) {
//                        showExitDialog("You can't add more than 5.", 0);
//                    }
//                }else{
//                    if (iCount >= iTotalCount){
//                        showExitDialog("You can't add more than "+String.valueOf(iTotalCount), 1);
//                    }else{
//                        if (strUserType.equals(getResources().getString(R.string.house)))
//                            goUploadApartment();
//                        if (strUserType.equals(getResources().getString(R.string.car)))
//                            goUploadRentalcar();
//                        if (strUserType.equals(getResources().getString(R.string.dress)))
//                            goUploadDress();
//                        if (strUserType.equals(getResources().getString(R.string.caravan)))
//                            goUploadCaravan();
//                        if (strUserType.equals(getResources().getString(R.string.vehicle)))
//                            goUploadVehicle();
//                        finish();
//                    }
//                }
//
//                closeProgress();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        ButterKnife.bind(this);

    }

    public void showExitDialog(String txt, int type){
        AlertDialog alertDialog = new AlertDialog.Builder(SelectCategoryActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(txt);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "UpGrade",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = null;
                        if (type == 0){
                            intent = new Intent(SelectCategoryActivity.this, userTypeSelect.class);
                        }else{
                            intent = new Intent(SelectCategoryActivity.this, MembershipActivity.class);
                        }
                        intent.putExtra("type", "upgrade");
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialog.show();
    }
    @OnClick(R.id.rounded_category_home)
    void goUploadApartment(){
        Intent intent = new Intent(this, UploadApartmentActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.rounded_category_car)
    void goUploadRentalcar(){

        Intent intent = new Intent(this, UploadRentalcarActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.rounded_category_caravan)
    void goUploadCaravan(){

        Intent intent = new Intent(this, UploadCaravanActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_vehicle)
    void goUploadVehicle(){

        Intent intent = new Intent(this, UploadVehicleActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_dress)
    void goUploadDress(){
        Intent intent = new Intent(this, UploadDressActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_bike)
    void goUploadBike(){
        Intent intent = new Intent(this, UploadBikeActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_camera)
    void goUploadCamera(){
        Intent intent = new Intent(this, UploadCameraActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_kamp)
    void goUploadKamp(){
        Intent intent = new Intent(this, UploadKampActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_music)
    void goUploadMusic(){
        Intent intent = new Intent(this, UploadMusicActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.rounded_category_more)
    void goUploadMore(){
        Intent intent = new Intent(this, UploadOtherActivity.class);
        startActivity(intent);
        finish();
    }

}
