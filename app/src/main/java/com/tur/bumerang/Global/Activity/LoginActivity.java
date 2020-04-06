package com.tur.bumerang.Global.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.SignupBusinessActivity;
import com.tur.bumerang.Global.API.ChangePasswordAPI;
import com.tur.bumerang.Global.API.ForgotPasswordAPI;
import com.tur.bumerang.Global.API.LoginAPI;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.Global.Model.UserModel;
import com.tur.bumerang.R;
import com.facebook.CallbackManager;
import com.tur.bumerang.Standard.Activity.SignupStandardActivity;
import com.tur.bumerang.Utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.linSignStandard)
    RelativeLayout linSignStandard;
    @BindView(R.id.edt_login_email) EditText edt_login_email;
    @BindView(R.id.edt_login_pwd) EditText edit_login_pwd;
    @BindView(R.id.checkbox_remember) CheckBox checkbox_remember;
    String email, pwd, email_forgot, id;
    String auth_type;
    TextView signupuserType;
    Dialog phonnumberdialog, confirmdialog, changepasswordbox;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient; /// for google login
    public static CallbackManager callbackManager;  /// for facebook login
    UserModel user = null;

    private static final int RC_SIGN_IN = 9001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupuserType=findViewById(R.id.txv_not_account);
        ButterKnife.bind(this);

        ///////////////////// click View for hide keyboard //////

        hideKeyboard(linSignStandard);

        checkbox_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked)
                {
                    EasyPreference.with(LoginActivity.this).addInt("easyRememberStatus",1).save();
                }
                else
                {
                    EasyPreference.with(LoginActivity.this).addInt("easyRememberStatus", 0).save();
                }
            }
        });

        if(EasyPreference.with(this).getInt("easyRegistStatus",0) == 1){
            edt_login_email.setText(EasyPreference.with(this).getString("easyUserEmail", ""));
        }

        ///////////// facebook login //////////////
        callbackManager = CallbackManager.Factory.create();
//
//        try {
//
//            PackageInfo info = getPackageManager().getPackageInfo("com.sharemyfood", PackageManager.GET_SIGNATURES);
//
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//
//                md.update(signature.toByteArray());
//                Log.i("KeyHash::", Base64.encodeToString(md.digest(), Base64.DEFAULT));//will give developer key hash
//                // Toast.makeText(getApplicationContext(), Base64.encodeToString(md.digest(), Base64.DEFAULT), Toast.LENGTH_LONG).show(); //will give app key hash or release key hash
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//
        ////////////////////////// Google Login /////////////////////////////
//
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
//
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();

        signupuserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, userTypeSelect.class));
                finish();
            }
        });
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        // check for existing Google Sign in account
////        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        updateUI(account);
//
//
//
//    }
//

    @OnClick(R.id.btn_login)
    void goHome(){

        email =  edt_login_email.getText().toString().trim();
        if (email.length() ==  0){
            showToast(getResources().getString(R.string.inputErrorEmail));
            return;
        }

        pwd = edit_login_pwd.getText().toString().trim();
        if (pwd.length() == 0){
            showToast(getResources().getString(R.string.inputErrorPassword));
            return;
        }

        auth_type = getResources().getString(R.string.email);

        onLoginApi(null, email, pwd, auth_type);

    }

    @OnClick(R.id.RR_login_google)
    void onGmail() {loginWithGoogle(); }

    @OnClick(R.id.RR_login_facebook)
    void onFacebook() {
        loginWithFB();
    }

    private void loginWithFB() {
        // set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();

                // Facebook Email address
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted( JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity Response ", response.toString());

                                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                                Log.d("IsLoggedIn???", String.valueOf(isLoggedIn));

                                Log.d("Login Token!!!", loginResult.getAccessToken().getToken());

                                try {

                                    user = new UserModel();

                                    email = object.getString("email");
//                                    Image_url = "http://graph.facebook.com/(Id)/picture?type=large";
//                                    Image_url = URLEncoder.encode(Image_url);
//                                    user.setPicture(Image_url);

                                    Log.d("Email = ", " " + email);

                                    user.setEmail(email);
//                                    user.setLatLng(myLatLang);
//                                    user.setCountry(country);
//                                    user.setCity(city);
//                                    user.setAddress(getAddress(myLatLang.latitude, myLatLang.longitude));
//                                    user.setOption("facebook");

                                    LoginManager.getInstance().logOut();

                                    pwd = "";
                                    auth_type = "Facebook";

                                    onLoginApi(null, email, pwd, auth_type);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, first_name, last_name, email, gender, birthday, picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void loginWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);

        } catch (Exception e) {
            Log.w("LoginActivity", "signInResult:failed code=" + e.getMessage());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account){

        if(account != null){

            String id = account.getId();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String photo = account.getPhotoUrl().toString();

            Log.d("Google Info: ", account.getEmail());
            Log.d("Google Info: ", account.getFamilyName());
            Log.d("Google Info: ", account.getGivenName());
            Log.d("Google Info: ", account.getPhotoUrl().toString());

            user = new UserModel();
            user.firstName = name;
            user.setEmail(email);
            user.avatarUrl = photo;
//            user.setCountry(country);
//            user.setCity(city);
//            user.setLatLng(myLatLang);
//            user.setAddress(getAddress(myLatLang.latitude, myLatLang.longitude));
//            user.setOption("google");

            pwd = "";
            auth_type = "Google";

            signOut();

            onLoginApi(account.getIdToken(), email, pwd, auth_type);

        } else {
            showToast("Google account is null");
        }
    }


    @OnClick(R.id.txv_forgot)
    void forgot(){
        phonenumberdialogbox();
    }
    void  phonenumberdialogbox(){
        EditText editAlertEmail;
        Button btnSend;

        phonnumberdialog= new Dialog(this);
        phonnumberdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        phonnumberdialog.setContentView(R.layout.alert_forgot_send);
        phonnumberdialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        phonnumberdialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        editAlertEmail=(EditText)phonnumberdialog.findViewById(R.id.edit_alert_mail);
        btnSend=(Button)phonnumberdialog.findViewById(R.id.btn_send);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editAlertEmail.getText().toString().isEmpty()){
                    showToast("Please insert Email or Phone number!");
                    return;
                }
                phonnumberdialog.dismiss();

                onForgotApi();
                showProgress();

            }
        });
        phonnumberdialog.show();

    }

    void verfiydialogbox(String verifycode){
        confirmdialog  = new Dialog(LoginActivity.this);
        confirmdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmdialog.setContentView(R.layout.alert_forgot_verify);
        confirmdialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        confirmdialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        EditText editVerify=(EditText)confirmdialog.findViewById(R.id.edit_alert_verify);
        Button btnVerify=(Button)confirmdialog.findViewById(R.id.btn_verify);
        Button btnback=(Button)confirmdialog.findViewById(R.id.btn_back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenumberdialogbox();
                confirmdialog.dismiss();
            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editVerify.getText().toString().equals(verifycode)){
                    changepasswordbox();
                    confirmdialog.dismiss();
                }else{
                    Toast.makeText(LoginActivity.this, "Please enter correct verify code", Toast.LENGTH_SHORT).show();
                }

            }
        });
        confirmdialog.show();
    }

    void changepasswordbox(){
        EditText edt_newpwd, edt_confirmpwd;
        Button btnChange;

        changepasswordbox = new Dialog(LoginActivity.this);
        changepasswordbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        changepasswordbox.setContentView(R.layout.alert_forgot_changepwd);
        changepasswordbox.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        changepasswordbox.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));

        edt_newpwd=(EditText)changepasswordbox.findViewById(R.id.edit_alert_newpwd);
        edt_confirmpwd = (EditText)changepasswordbox.findViewById(R.id.edit_alert_confirmpwd);
        btnChange=(Button)changepasswordbox.findViewById(R.id.btn_change);



        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changepasswordbox.dismiss();

                pwd = edt_newpwd.getText().toString();
                id = String.valueOf(EasyPreference.with(LoginActivity.this).getInt("easyUserId",0));
                String conpwd = edt_confirmpwd.getText().toString();

                if(pwd.equals(conpwd)){
                    onChangePasswordApi();
                }else{
                    showToast("Please insert confirm password correctly!");
                }

            }
        });
        changepasswordbox.show();
    }

//    @OnClick(R.id.txv_not_account)
//    void goRegister(){
//
//        Intent intent = new Intent(this, userTypeSelect.class);
//        startActivity(intent);
//        finish();
//
//      /*  Intent intent = new Intent(this, RegisterActivity.class);
//        startActivity(intent);
//        finish();*/
//    }


    public void onForgotApi(){

        FirebaseAuth.getInstance().sendPasswordResetEmail(email_forgot)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                        closeProgress();
                    }
                });

    }

    public void onChangePasswordApi(){



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });

        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }


    void onLoginApi(String strTockn, String email, String pwd, String auth_type){
        /*final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_login==", json);
                closeProgress();
                try{
                    JSONObject res = new JSONObject(json);
                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
                        UserModel user = new UserModel(res.getJSONObject("user_info"));
                        Common.user = user;
                         showToast("Login Succeeded!");

                        //String pwd = edit_login_pwd.getText().toString().trim();

                        EasyPreference.with(LoginActivity.this).addInt("easyRegistStatus",1).save();
                        EasyPreference.with(LoginActivity.this).addString("easyAuthType", "Email").save();
                        EasyPreference.with(LoginActivity.this).addInt("easyUserId", user.id).save();
                        EasyPreference.with(LoginActivity.this).addString("easyUserEmail",user.email).save();
                        EasyPreference.with(LoginActivity.this).addString("easyUserPassword",pwd).save();
                        EasyPreference.with(LoginActivity.this).addString("easyUserType", user.userType).save();

                        Log.d("easyUserEmail==",  EasyPreference.with(LoginActivity.this).getString("easyUserEmail",""));
                        Log.d("easyUserPassword==", EasyPreference.with(LoginActivity.this).getString("easyUserPassword",""));

                        Intent intent =  new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        showAlertDialog(res.getString(ReqConst.MSG));
                    }

                }catch (JSONException e){
                    showAlertDialog(e.getMessage());
                }
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                closeProgress();
                showAlertDialog(getString(R.string.serverFailed));
            }
        };

        showProgress();
        LoginAPI req = new LoginAPI(email, pwd, auth_type,res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);*/


        showProgress();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Task<AuthResult> authResultTask = null;

        if(auth_type == null || auth_type.equals("")){

        }else if(auth_type.equals("Google")){
            AuthCredential credential = GoogleAuthProvider.getCredential(strTockn, null);
            authResultTask = mAuth.signInWithCredential(credential);
            EasyPreference.with(LoginActivity.this).addInt("easyRememberStatus", 0).save();
        }else{
            authResultTask = mAuth.signInWithEmailAndPassword(email, pwd);
        }

        if (authResultTask == null){
            closeProgress();
            Toast.makeText(LoginActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        authResultTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            handleSigninWithEmailPassword(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            closeProgress();
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }

                    private void handleSigninWithEmailPassword(FirebaseUser user) {
                        if (user != null){
//                            Common.user.setAvatarUrl(user.getPhotoUrl().toString());
                            Common.user.setFirstName(user.getDisplayName());
                            Common.user.setPhone(user.getPhoneNumber());
                            Common.user.setId(user.getUid());
                            Common.user.setEmail(user.getEmail());
                            Common.user.setUserType(getString(R.string.standard));
                            Common.user.password = "";
                        }
                        String currentUid = user.getUid();
                        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
                        mRef.child(ReqConst.API_USER).child(currentUid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                closeProgress();
                                User mUser = dataSnapshot.getValue(User.class);
                                if (mUser != null) {
                                    Common.user = mUser;
                                    if (mUser.getUserType() == getString(R.string.business)) {
                                        showToast("You can't login!");
                                        return;
                                    }
                                }
//                                showToast("Login Succeeded!");

                                String strLogType = "Email";
                                if (mUser == null) {
                                    Common.user.membership = "";
                                    strLogType = "Google";
                                }
                                EasyPreference.with(LoginActivity.this).addString("easyMembership", Common.user.membership).save();
                                EasyPreference.with(LoginActivity.this).addInt("easyRegistStatus", 1).save();
                                EasyPreference.with(LoginActivity.this).addString("easyAuthType", strLogType).save();
                                EasyPreference.with(LoginActivity.this).addString("easyUserId", Common.user.id).save();
                                EasyPreference.with(LoginActivity.this).addString("easyUserEmail", Common.user.email).save();
                                EasyPreference.with(LoginActivity.this).addString("easyUserPassword", Common.user.password).save();
                                EasyPreference.with(LoginActivity.this).addString("easyUserType", Common.user.userType).save();
                                EasyPreference.with(LoginActivity.this).addString("easyUserFirstName", Common.user.firstName).save();
                                EasyPreference.with(LoginActivity.this).addString("easyUserLastName", Common.user.lastName).save();

                                Log.d("easyUserEmail==",  EasyPreference.with(LoginActivity.this).getString("easyUserEmail",""));
                                Log.d("easyUserPassword==", EasyPreference.with(LoginActivity.this).getString("easyUserPassword",""));

                                Intent intent =  new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                closeProgress();
                            }
                        });
                    }
                });

    }

}
