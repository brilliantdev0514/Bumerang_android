package com.tur.bumerang.Global.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.iamhabib.easy_preference.EasyPreference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.MembershipActivity;
import com.tur.bumerang.Business.Activity.SelectCategoryRegisterActivity;
import com.tur.bumerang.Business.Activity.UserProfileBusinessActivity;
import com.tur.bumerang.Global.API.LoginAPI;
import com.tur.bumerang.Global.API.UpdateProfileAPI;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.Global.Model.UserModel;
import com.tur.bumerang.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileSettingActivity extends BaseActivity {

    @BindView(R.id.btn_membership)  Button btn_membership;
    @BindView(R.id.imv_user_avatar_setting) RoundedImageView imv_user_avatar_setting;

    @BindView(R.id.edt_profile_fname) EditText edt_profile_fname;
    @BindView(R.id.edt_profile_lname) EditText edt_profile_lname;
    @BindView(R.id.edt_profile_email) EditText edt_profile_email;
    @BindView(R.id.edt_profile_phone) EditText edt_profile_phone;
    @BindView(R.id.edt_profile_address) EditText edt_profile_address;
    @BindView(R.id.edt_profile_pwd) EditText edt_profile_pwd;

    String update_fname,update_lname, update_email, update_phonenumber,update_adress,update_pwd;
    File imageFile;
    Uri imageSrc;
    Dialog dialog;
    FirebaseUser mAuth = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        ButterKnife.bind(this);

        if(Common.user != null) {
            Glide
                    .with(this)
                    .load(Common.user.avatarUrl)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_avatar)
                    .into(imv_user_avatar_setting);
            if (Common.user.avatarUrl != null && !Common.user.avatarUrl.equals("")){
                findViewById(R.id.ic_avatar_add).setVisibility(View.GONE);
            }

            edt_profile_fname.setText(Common.user.firstName );
            edt_profile_lname.setText(Common.user.lastName);
            edt_profile_email.setText(Common.user.email);
            edt_profile_phone.setText(Common.user.phone);
            edt_profile_address.setText(Common.user.address);
            edt_profile_pwd.setText(Common.user.password);
        }

        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @OnClick(R.id.btn_membership)
    void goMembership(){
        Intent intent = new Intent(ProfileSettingActivity.this, MembershipActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    @OnClick(R.id.imv_user_avatar_setting)
    void setAvatarPhoto(){
        startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), Config.RC_PICK_IMAGES);
//        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
//                .setToolbarColor("#212121")         //  Toolbar color
//                .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
//                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
//                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
//                .setProgressBarColor("#4CAF50")     //  ProgressBar color
//                .setBackgroundColor("#212121")      //  Background color
//                .setCameraOnly(false)               //  Camera mode
//                .setMultipleMode(true)              //  Select multiple images or single image
//                .setFolderMode(true)                //  Folder mode
//                .setShowCamera(true)                //  Show camera button
//                .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
//                .setImageTitle("Galleries")         //  Image title (works with FolderMode = false)
//                .setDoneTitle("Done")               //  Done button title
//                .setLimitMessage("You have reached selection limit")    // Selection limit message
//                .setMaxSize(10)                     //  Max images can be selected
//                .setSavePath("ImagePicker")         //  Image capture folder name
//                //               .setSelectedImages(images)          //  Selected images
//                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
//                .setRequestCode(100)                //  Set request code, default Config.RC_PICK_IMAGES
//                .setKeepScreenOn(true)              //  Keep screen on when selecting images
//                .start();                           //  Start ImagePicker

    }

    @OnClick(R.id.ic_avatar_remove)
    void removeAvatar(){
        imageSrc = null;
        showAvatar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.RC_PICK_IMAGES && resultCode == RESULT_OK) {
//            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
//            if(images.size()==0){
            if (data == null){
                Toast.makeText(this, "Please select the image", Toast.LENGTH_SHORT).show();
                return;
            }
            imageSrc = data.getData();
            imageFile = new File(data.getDataString());

            //RoundedImageView imageView = (RoundedImageView)findViewById(R.id.imv_user_avatar_setting);

           // imv_user_avatar_setting.setImageURI(Uri.parse(new File(images.get(0).getPath()).toString()));
//            Picasso.get()
//                    .load(images.get(0).getPath())
//                    .fit()
//                    .into(imv_user_avatar_setting);
          showAvatar();

        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
    }
    void showAvatar(){
        Glide.with(this)
                .load(imageSrc)
                .into(imv_user_avatar_setting);
        if (imageSrc == null || imageSrc.equals("")){
            findViewById(R.id.ic_avatar_add).setVisibility(View.VISIBLE);
            findViewById(R.id.ic_avatar_remove).setVisibility(View.GONE);
        }else{
            findViewById(R.id.ic_avatar_add).setVisibility(View.VISIBLE);
            findViewById(R.id.ic_avatar_remove).setVisibility(View.GONE);
        }
    }
    @OnClick(R.id.btn_signout)
    void logOut(){
        logOutdialog();
    }

    void  logOutdialog(){
        dialog  = new Dialog(ProfileSettingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_log_out);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        Button btnok=(Button)dialog.findViewById(R.id.btn_select_logout);
        Button btncancel=(Button)dialog.findViewById(R.id.btn_select_cancel);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyPreference.with(ProfileSettingActivity.this)
                        .clearAll()
                        .save();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileSettingActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }
/////////////////////temp
    @OnClick(R.id.btn_update_profile)
    void updateProfile(){
        update_fname =  edt_profile_fname.getText().toString().trim();
        update_lname =  edt_profile_lname.getText().toString().trim();
        update_email = edt_profile_email.getText().toString().trim();
        update_phonenumber = edt_profile_phone.getText().toString().trim();
        update_adress = edt_profile_address.getText().toString().trim();
        update_pwd = edt_profile_pwd.getText().toString().trim();
        onUpdateProfileApi(update_fname,update_lname, update_email, update_phonenumber,update_adress,update_pwd);
    }

    void onUpdateProfileApi(String fname, String lname,String email, String phonenumber,String adress, String pwd){
        showProgress();
        String owner_id = EasyPreference.with(this).getString("easyUserId", "");
        if (owner_id == null || owner_id == ""){
            showAlertDialog("unknown user");
            return;
        }
//        String [] strNames = name.split(", ");
//        if (strNames.length > 1){
//            Common.user.setFirstName(strNames[0]);
//            String strLastName = "";
//            for (int i = 1; i < strNames.length; i++)
//                strLastName += strNames[i];
//            Common.user.setLastName(strLastName);
//        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), Common.user.password);
        Common.user.setFirstName(fname);
        Common.user.setLastName(lname);
        Common.user.setEmail(email);
        Common.user.setPhone(phonenumber);
        Common.user.setAddress(adress);
        Common.user.password = pwd;
        Common.user.setAvatarUrl("");

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(pwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    if (imageSrc == null){
                                                        if(owner_id == null || owner_id.equals("")) {
                                                            closeProgress();
                                                            return;
                                                        }
                                                        FirebaseDatabase.getInstance().getReference().child(ReqConst.API_USER).child(owner_id).setValue(Common.user)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        closeProgress();
                                                                        showToast("Update Success!");
                                                                        onBackPressed();
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        closeProgress();
                                                                        showAlertDialog("Update Error!");
                                                                    }
                                                                });
                                                    }
                                                    else {
                                                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                                                        StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
                                                        ref.putFile(imageSrc)
                                                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                    @Override
                                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                            @Override
                                                                            public void onSuccess(Uri uri) {
                                                                                if(owner_id == null || owner_id.equals("")) {
                                                                                    closeProgress();
                                                                                    return;
                                                                                }

                                                                                Common.user.setAvatarUrl(uri.toString());
                                                                                FirebaseDatabase.getInstance().getReference().child(ReqConst.API_USER).child(owner_id).setValue(Common.user)
                                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                closeProgress();
                                                                                                showToast("Update Success!");
                                                                                                onBackPressed();
                                                                                            }
                                                                                        })
                                                                                        .addOnFailureListener(new OnFailureListener() {
                                                                                            @Override
                                                                                            public void onFailure(@NonNull Exception e) {
                                                                                                closeProgress();
                                                                                                showAlertDialog("Update Error!");
                                                                                            }
                                                                                        });
                                                                            }
                                                                        }).addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e) {
                                                                                closeProgress();
                                                                                showAlertDialog("Get User Avatar Error!");
                                                                            }
                                                                        });
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        closeProgress();
                                                                        showAlertDialog("User Avatar Upload Error!");
                                                                    }
                                                                });
                                                    }
                                                }else{
                                                    closeProgress();
                                                }
                                            }
                                        });
                                    } else {
                                        closeProgress();
                                    }
                                }
                            });
                        } else {
                            closeProgress();
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        String userMembership = EasyPreference.with(this).getString("easyMembership", "");
        if (userMembership.length() == 0)
             {
                 Intent intent = new Intent(ProfileSettingActivity.this, UserProfileActivity.class);
                startActivity(intent);
                finish();
            }
        else{
            Intent intent = new Intent(ProfileSettingActivity.this, UserProfileBusinessActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
