package com.tur.bumerang.Global.Activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iamhabib.easy_preference.EasyPreference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;

import com.tur.bumerang.Global.Fragment.MainFragment;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
@SuppressLint("NewApi")

public class ChattingActivity extends BaseActivity {

    LinearLayout layout;
    LinearLayout layout_2;
    ImageView sendButton, cameraButton;
    EditText messageArea;
    ScrollView scrollView;
    TextView statusView, history, typeStatus;
    ImageView ok, cancel;
    Firebase reference1, reference2, reference3, reference4, reference5, reference7, reference6;
    CircleImageView photo;
    int is_talking=0;
    int is_talkingR=0;
    boolean is_typing=false;
    ProgressBar progressBar;
    boolean startTalking=false;

    LinearLayout pictureItemFrame;
    FrameLayout alertBackground;

    ImageView image;
    LinearLayout imagePortion;
    String imageFile="";
    String messageReceivedTime = "";
    Uri downloadUri = null;
    String videoThumbStr = "";
    String imageStr = "";
    boolean userOnlineF = false;
    User CurUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        String otherUid = getIntent().getStringExtra("owner_id");
        CurUser = Common.user;
        String strOwner = getIntent().getStringExtra("owner_id");
        if (strOwner != null && !strOwner.equals("")) {
            Common.Other_user = com.tur.bumerang.Base.Constants.itemModel.owner_info;
            if (com.tur.bumerang.Base.Constants.itemModel.owner_info.getId() == null || Constants.itemModel.owner_info.getId().equals("")) {
                showToast(getString(R.string.user_info_no));
                finish();
                return;
            }
        }



        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Firebase.setAndroidContext(this);
        reference1 = new Firebase(ReqConst.FIREBASE_URL + "message/" + Common.user.getId() + "_" + Common.Other_user.getId());
        reference2 = new Firebase(ReqConst.FIREBASE_URL + "message/" + Common.Other_user.getId() + "_" + Common.user.getId());
        reference3 = new Firebase(ReqConst.FIREBASE_URL + "notification/" + Common.Other_user.getId() + "/" + Common.user.getId());
        reference6 = new Firebase(ReqConst.FIREBASE_URL + "notification/" + Common.user.getId() + "/" + Common.Other_user.getId());
        //TODO; status refs
        reference5 = new Firebase(ReqConst.FIREBASE_URL + "status/" + Common.Other_user.getId() + "_" + Common.user.getId());
        reference4 = new Firebase(ReqConst.FIREBASE_URL + "status/" + Common.user.getId() + "_" + Common.Other_user.getId());


        messageArea = (EditText)findViewById(R.id.messageArea);
        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (LinearLayout) findViewById(R.id.layout2);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        statusView=(TextView)findViewById(R.id.status);
        imagePortion=(LinearLayout)findViewById(R.id.imagePortion);
        image=(ImageView) findViewById(R.id.image);
        ((ImageView)findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pictureItemFrame = (LinearLayout) findViewById(R.id.pictureItemFrame);
        alertBackground = (FrameLayout) findViewById(R.id.layout);
        cancel = (ImageView) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePortion.setVisibility(View.GONE);
            }
        });
        ok = (ImageView) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
                imagePortion.setVisibility(View.GONE);
            }
        });
        photo=(CircleImageView) findViewById(R.id.imv_photo);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        if (Common.Other_user.getAvatarUrl() != null && !Common.Other_user.getAvatarUrl().equals("")) {
            Picasso.get()
                    .load(Common.Other_user.getAvatarUrl())
                    .error(R.mipmap.virtualphoto)
                    .placeholder(R.mipmap.virtualphoto)
                    .into(photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(View.INVISIBLE);
                        }
                    });
        }else{
            ((ProgressBar) findViewById(R.id.progressBar2)).setVisibility(View.INVISIBLE);
        }
        cameraButton=(ImageView)findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertPhoto();
            }
        });

        TextView name = (TextView)findViewById(R.id.txv_name);

        name.setText(Common.Other_user.getFirstName()+" "+Common.Other_user.getLastName());

        typeStatus = (TextView)findViewById(R.id.typeStatus);

        messageArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String messageText = messageArea.getText().toString().trim();
                if(!is_typing){
                    is_typing=true;
                    if(messageText.length() > 0){
                        sendButton.setVisibility(View.VISIBLE);
                        cameraButton.setVisibility(View.GONE);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("online", "typing");
                        map.put("time", String.valueOf(new Date().getTime()));
                        map.put("sender_id", String.valueOf(Common.user.getId()));
                        reference4.removeValue();
                        reference4.push().setValue(map);
                    }
                }else {
                    if(messageText.length()==0){
                        sendButton.setVisibility(View.GONE);
                        cameraButton.setVisibility(View.VISIBLE);
                        is_typing=false;
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("online", "online");
                        map.put("time", String.valueOf(new Date().getTime()));
                        map.put("sender_id", String.valueOf(Common.user.getId()));
                        reference4.removeValue();
                        reference4.push().setValue(map);
                    }
                }
            }


        });

        online("true");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString().trim();
                if(messageText.length() > 0){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("time", String.valueOf(new Date().getTime()));
                    map.put("image", "");
                    map.put("sender_id", String.valueOf(Common.user.getId()));
                    map.put("name", Common.user.getFirstName() + " "+Common.user.getLastName());
                    map.put("photo", Common.user.getAvatarUrl());

                    online("true");
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                    is_typing=false;

                    Map<String, String> map2 = new HashMap<String, String>();
                    map2.put("message", messageText);
                    map2.put("time", String.valueOf(new Date().getTime()));
                    map2.put("sender_id", String.valueOf(Common.user.getId()));
//                    map.put("image", "");
                    map2.put("sender_name", Common.user.getFirstName() + " "+Common.user.getLastName());
                    map2.put("sender_photo", Common.user.getAvatarUrl());


                    reference3.removeValue();
                    reference3.push().setValue(map2);
//                    reference7.removeValue();

//                    reference7.push().setValue(map2);

//                    sendFcmNotification(messageText);

                }
                sendButton.setVisibility(View.GONE);
                cameraButton.setVisibility(View.VISIBLE);
            }
        });

        reference5.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String online = map.get("online").toString();
                String time = map.get("time").toString();

                if(online.equals("online")){
                    statusView.setVisibility(View.VISIBLE);
                    statusView.setText("Online");
                    typeStatus.setVisibility(View.GONE);
                    userOnlineF = true;
                }else if(online.equals("offline")){
                    statusView.setVisibility(View.VISIBLE);
                    statusView.setText("Last seen at " + DateFormat.format("MM/dd/yyyy hh:mm a", Long.parseLong(time)));
                    userOnlineF = false;
                }else {

                    statusView.setVisibility(View.VISIBLE);
                    statusView.setText("Yazıyor...");
                    typeStatus.setVisibility(View.VISIBLE);
                    try{
                        if(Common.Other_user.getFirstName()!=null){
                            typeStatus.setText(Common.Other_user.getFirstName() + Common.Other_user.getLastName() +" Yazıyor...");
                        }
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }catch (Exception e){}

                    userOnlineF = true;

                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });

                }

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

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String image = map.get("image").toString();
                String senderId = map.get("sender_id").toString();
                String name = map.get("name").toString();
               // String photo = map.get("photo").toString();
                String time = map.get("time").toString();
                LatLng latLng=null;
                String key = dataSnapshot.getKey();
                messageReceivedTime = time;

                if(senderId.equals(String.valueOf(Common.user.getId()))){
                    addMessageBox(message, time, image, key, 1);
                }
                else{
                    addMessageBox(message, time, image, key, 2);
                }
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

        reference3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try{
                    Map map = dataSnapshot.getValue(Map.class);
                    String dateTime = map.get("time").toString();
//                    if(Math.abs(Long.parseLong(dateTime) - Long.parseLong(messageReceivedTime)) < 5000) reference3.child(dataSnapshot.getKey()).removeValue();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }catch (FirebaseException e){
                    e.printStackTrace();
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
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

        contact();

        setupUI(findViewById(R.id.chatFrame), this);

    }


    public void online(String status){
        Map<String, String> map = new HashMap<String, String>();
        if(status.equals("true"))
            map.put("online", "online");
        else map.put("online", "offline");
        map.put("time", String.valueOf(new Date().getTime()));
        map.put("sender_id", String.valueOf(Common.user.getId()));
        reference4.removeValue();
        reference4.push().setValue(map);
        if(status.equals("false")){
            reference7 = null;
            finish();
        }
    }

    public void addMessageBox(final String message, String time, final String imageStr, String key, final int type){

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.chat_history_area, null);

        FrameLayout photoFrame = (FrameLayout)dialogView.findViewById(R.id.photoFrame);
        final CircleImageView photo=(CircleImageView) dialogView.findViewById(R.id.photo);
        if (Common.Other_user.getAvatarUrl() != null && !Common.Other_user.getAvatarUrl().equals("")) {
            Picasso.get()
                    .load(Common.Other_user.getAvatarUrl())
                    .error(R.mipmap.virtualphoto)
                    .placeholder(R.mipmap.virtualphoto)
                    .into(photo);
        }
        final LinearLayout read=(LinearLayout)dialogView.findViewById(R.id.read);
        final LinearLayout write=(LinearLayout)dialogView.findViewById(R.id.write);
        final LinearLayout dotrec=(LinearLayout)dialogView.findViewById(R.id.receiverdots);
        final LinearLayout dotsend=(LinearLayout)dialogView.findViewById(R.id.senderdots);
        final TextView text = (TextView) dialogView.findViewById(R.id.text);
        final TextView text2 = (TextView) dialogView.findViewById(R.id.text2);
        final TextView datetime = (TextView) dialogView.findViewById(R.id.datetime);
        final TextView datetime2 = (TextView) dialogView.findViewById(R.id.datetime2);
        final TextView writespace = (TextView) dialogView.findViewById(R.id.writespace);
        ImageView image=(ImageView) dialogView.findViewById(R.id.image);
        ImageView image2=(ImageView) dialogView.findViewById(R.id.image2);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {

            if(is_talking==1){
                dotsend.setVisibility(View.INVISIBLE);
            }else {
                dotsend.setVisibility(View.VISIBLE);
            }

            photoFrame.setVisibility(View.GONE);
            read.setVisibility(View.GONE);
            dotrec.setVisibility(View.GONE);
            writespace.setVisibility(View.VISIBLE);
            write.setVisibility(View.VISIBLE);
            text2.setText(message);
            text2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(text2.getText().length() > 0){
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, text2.getText().toString());
                        startActivity(Intent.createChooser(shareIntent, "Share via..."));
                    }
                }
            });

            text2.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // TODO Auto-generated method stub
//                    showEditTextAlert(key, text2, dialogView);
                    return false;
                }
            });

            datetime2.setText(DateFormat.format("MM/dd/yyyy hh:mm a",
                    Long.parseLong(time)));

            if(imageStr.length()>0){
                image2.setVisibility(View.VISIBLE);
                ((ProgressBar)dialogView.findViewById(R.id.prog2)).setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(imageStr)
                        .error(R.mipmap.not_found)
                        .placeholder(R.mipmap.not_found)
                        .into(image2, new Callback() {
                            @Override
                            public void onSuccess() {
                                ((ProgressBar)dialogView.findViewById(R.id.prog2)).setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onError(Exception e) {
                                ((ProgressBar)dialogView.findViewById(R.id.prog2)).setVisibility(View.INVISIBLE);
                            }

                        });
                image2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                        intent.putExtra("image", imageStr);
                        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(ChattingActivity.this, image2, getString(R.string.transition));
                        startActivity(intent, transitionActivityOptions.toBundle());
                    }
                });
                text2.setVisibility(View.GONE);
                image2.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
//                        showDeleteFileAlert(key, dialogView);
                        return false;
                    }
                });
            }else {
                image2.setVisibility(View.GONE);
                text2.setVisibility(View.VISIBLE);
            }

            if(is_talking==0){
                is_talking=1; is_talkingR=0;
            }
        }
        else{
            if(is_talkingR==1){
                photoFrame.setVisibility(View.INVISIBLE);
                dotrec.setVisibility(View.INVISIBLE);
            }else {
                photoFrame.setVisibility(View.VISIBLE);
                dotrec.setVisibility(View.VISIBLE);
            }

            read.setVisibility(View.VISIBLE);
            dotsend.setVisibility(View.GONE);

            writespace.setVisibility(View.GONE);
            write.setVisibility(View.GONE);

            text.setText(message);
            //        reference.removeValue();
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(text.getText().length()>0){
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
                        startActivity(Intent.createChooser(shareIntent, "Share via..."));
                    }
                }
            });
            datetime.setText(DateFormat.format("MM/dd/yyyy hh:mm a",
                    Long.parseLong(time)));

            if(imageStr.length()>0){
                image.setVisibility(View.VISIBLE);
                ((ProgressBar)dialogView.findViewById(R.id.prog)).setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(imageStr)
                        .error(R.mipmap.not_found)
                        .placeholder(R.mipmap.not_found)
                        .into(image, new Callback() {
                            @Override
                            public void onSuccess() {
                                ((ProgressBar)dialogView.findViewById(R.id.prog)).setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onError(Exception e) {
                                ((ProgressBar)dialogView.findViewById(R.id.prog)).setVisibility(View.INVISIBLE);
                            }

                        });
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                        intent.putExtra("image", imageStr);
                        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(ChattingActivity.this, image, getString(R.string.transition));
                        startActivity(intent, transitionActivityOptions.toBundle());
                    }
                });
                text.setVisibility(View.GONE);
            }else {
                image.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
            }

            if(is_talkingR==0){
                is_talking=0; is_talkingR=1;
            }
        }

        layout.addView(dialogView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startTalking=true;
            }
        }, 2000);

    }

    public void showAlertPhoto(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                //From here you can load the image however you need to, I recommend using the Glide library
                File file = new File(resultUri.getPath());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    imagePortion.setVisibility(View.VISIBLE);
                    image.setImageBitmap(bitmap);
                    imageToDonwloadUrl(file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void uploadImage(){

        if(imageStr.length()>0){

            Map<String, String> map = new HashMap<String, String>();
            map.put("message", "");
            map.put("time", String.valueOf(new Date().getTime()));
            map.put("sender_id", String.valueOf(Common.user.getId()));
            map.put("name", Common.user.getFirstName() + " "+Common.user.getLastName());
            map.put("photo", Common.user.getAvatarUrl());
            map.put("image", imageStr);

            online("true");
            reference1.push().setValue(map);
            reference2.push().setValue(map);
            is_typing=false;

            Map<String, String> map2 = new HashMap<String, String>();
            map2.put("message", "Shared a file");
            map2.put("time", String.valueOf(new Date().getTime()));
            map2.put("sender_id", String.valueOf(Common.user.getId()));
            map2.put("sender_name", Common.user.getFirstName() + " " +Common.user.getLastName());
            map2.put("sender_photo", Common.user.getAvatarUrl());
            ////

            reference5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String status = (String) dataSnapshot.child(dataSnapshot.getKey()).child("online").getValue();
                        if (status.equals("offline")) {
                            reference3.removeValue();
                            reference3.push().setValue(map2);
                        }
                    }else {
                        reference3.removeValue();
                        reference3.push().setValue(map2);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

//            reference7.removeValue();
//            reference7.push().setValue(map2);

            //       sendFcmNotification("Shared a file");

            imageStr="";
        }
    }

    private String saveImage(Bitmap finalBitmap, String image_name) {
        String path = "";
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            path = file.getPath();
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public void sendFcmNotification(String message) {
        /*AndroidNetworking.post(ReqConst.SERVER + "submitFCM")
                .addBodyParameter("member_id", String.valueOf(Common.Other_user.getId()))
                .addBodyParameter("sender_id", String.valueOf(Common.user.getId()))
                .addBodyParameter("notitext", message)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                    @Override
                    public void onError(ANError error) {

                    }
                });*/

    }

    public void contact() {


    }

    private void imageToDonwloadUrl(String path){
        progressBar.setVisibility(View.VISIBLE);
        final String[] url = {""};
        final Uri[] uri = {Uri.fromFile(new File(path))};
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://sharemyfood-1567613607368.appspot.com");
        StorageReference fileReference = firebaseStorage.getReference();

        UploadTask uploadTask = fileReference.child(uri[0].getLastPathSegment()+ ".jpg").putFile(uri[0]);

        // Listen for state changes, errors, and completion of the upload.
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressBar.setVisibility(View.GONE);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Handle successful uploads on complete
                Log.d("IMAGE===>",imageStr);
                fileReference.child(uri[0].getLastPathSegment()+ ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("onSuccess: uri= ", uri.toString());
                        imageStr = uri.toString();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        online("false");
        reference6.removeValue();
        Intent intent = new Intent(ChattingActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
