package com.tur.bumerang.Standard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.pdfviewer.PDFView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.GenerateRandomString;
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.LoginActivity;
import com.tur.bumerang.Global.Activity.RegisterCompletedActivity;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupStandardActivity extends BaseActivity {

     @BindView(R.id.linSignStandard)
    LinearLayout linSignStandard;
    // @BindView(R.id.btnSignup)  Button btnSignup;
    @BindView(R.id.edtFirstName)
    EditText edtFirstName;
    @BindView(R.id.edtLastName) EditText edtLastName;
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPwd) EditText edtPwd;
    @BindView(R.id.edtConfirmPwd) EditText edtConfirmPwd;
    private FirebaseAuth mAuth;

    String first_name, last_name, email, pwd, user_type, auth_type, lat, lng;

    GoogleSignInClient mGoogleSignInClient; /// for google login
    GoogleApiClient mGoogleApiClient;   // for google login
    public static CallbackManager callbackManager;  /// for facebook login
    User user = null;

    private static final int RC_SIGN_IN = 9001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_standard);
        FirebaseAuth.getInstance().signOut();

        ButterKnife.bind(this);

        hideKeyboard(linSignStandard);

        ///////////// facebook login //////////////
        callbackManager = CallbackManager.Factory.create();

        ////////////////////////// Google Login /////////////////////////////

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.imv_signup_google)
    void onEmail() {
        signIn();
    }

    @OnClick(R.id.imv_signup_facebook)
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

                                    user = new User();

                                    first_name = object.getString("first_name");
                                    last_name = object.getString("last_name");
                                    email = object.getString("email");
                                    pwd = object.getString("pwd");
//                                    Image_url = "http://graph.facebook.com/(Id)/picture?type=large";
//                                    Image_url = URLEncoder.encode(Image_url);
//                                    user.setPicture(Image_url);

                                    Log.d("Email = ", " " + email);
//                                    Log.d("Image====",Image_url.toString());
                                    Log.d("firstName======", first_name);
                                    Log.d("lastName======", last_name);
                                    Log.d("Object=====>", object.toString());

//                                    if (object.has("picture")) {
//                                        JSONObject jsonPicture = object.getJSONObject("picture");
//                                        if (jsonPicture.has("data")) {
//                                            JSONObject jsonData = jsonPicture.getJSONObject("data");
//                                            if (jsonData.has("url"))
//                                                user.setPicture(jsonData.getString("url"));
//                                        }
//                                    }

                                    user.setLastName(last_name);
                                    user.setFirstName(first_name);
                                    user.setEmail(email);


//                                    user.setLatLng(myLatLang);
//                                    user.setCountry(country);
//                                    user.setCity(city);
//                                    user.setAddress(getAddress(myLatLang.latitude, myLatLang.longitude));
//                                    user.setOption("facebook");

                                    pwd = "";
                                    user_type = ReqConst.USER_STANDARD;
                                    auth_type = "Facebook";



                                    onSignupStandardApi(first_name, last_name, email, pwd, user_type, auth_type);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


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


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);

        } catch (ApiException e) {
            Log.w("LoginActivity", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account){

        if(account != null){
            String id = account.getId();
            String last_name = account.getFamilyName();
            String first_name = account.getGivenName();
            String email = account.getEmail();
            String photo = account.getPhotoUrl().toString();

            String pwd = "";
            String user_type = "Standard";
            String auth_type = "Google";

            Log.d("Google Info: ", account.getEmail());
            Log.d("Google Info: ", account.getFamilyName());
            Log.d("Google Info: ", account.getGivenName());
            Log.d("Google Info: ", account.getPhotoUrl().toString());

            user = new User();
            user.firstName = first_name;
            user.setEmail(email);
            user.avatarUrl = photo;
//            user.setCountry(country);
//            user.setCity(city);
//            user.setLatLng(myLatLang);
//            user.setAddress(getAddress(myLatLang.latitude, myLatLang.longitude));
//            user.setOption("google");

            onSignupStandardApi(first_name, last_name, email, pwd, user_type, auth_type);

        } else {

            showToast("Google account is null");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {

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

    @OnClick(R.id.txv_have_account)
    void goLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_signup2)
    void signupWithEmail(){
        first_name = edtFirstName.getText().toString().trim();
        if (first_name.length() == 0) {
            showToast(getResources().getString(R.string.inputErrorFirstName));
            return;
        }

        last_name = edtLastName.getText().toString().trim();
        if (last_name.length() == 0){
            showToast(getResources().getString(R.string.inputErrorLastName));
            return;
        }

        email =  edtEmail.getText().toString().trim();
        if (email.length() ==  0){
            showToast(getResources().getString(R.string.inputErrorEmail));
            return;
        }

        pwd = edtPwd.getText().toString().trim();
        if (pwd.length() == 0){
            showToast(getResources().getString(R.string.inputErrorPassword));
            return;
        }

        if (!pwd.equals(edtConfirmPwd.getText().toString().trim())){
            showToast(getResources().getString(R.string.inputErrorConfirmPwd));
            return;
        }

        user_type = getResources().getString(R.string.standard);
        auth_type = getResources().getString(R.string.email);

        lat = String.valueOf(ReqConst.defaultLat);
        lng = String.valueOf(ReqConst.defaultlng);
//
//        Intent intent =  new Intent(SignupStandardActivity.this, HomeActivity.class);
//        startActivity(intent);
//        finish();

        mAuth = FirebaseAuth.getInstance();

        showProgress();
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onSignupStandardApi(first_name, last_name, email, pwd, user_type, auth_type);
                            closeProgress();
                        } else {
                            showToast(task.getException().getMessage());
                            closeProgress();
                        }

                    }
                });



    }

    void onSignupStandardApi(String first_name, String last_name, String email, String pwd, String user_type, String  auth_type){

        /*
        final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("signup_response===", json);
                closeProgress();
                try{
                    JSONObject res = new JSONObject(json);

                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
                        UserModel user = new UserModel(res.getJSONObject("user_info"));
                        Common.user = user;

                        EasyPreference.with(SignupStandardActivity.this).addString("easyAuthType","Email").save();
                        EasyPreference.with(SignupStandardActivity.this).addString("easyUserEmail",user.email).save();
                        EasyPreference.with(SignupStandardActivity.this).addString("easyUserPassword",user.password).save();
                        EasyPreference.with(SignupStandardActivity.this).addInt("easyUserId", user.id).save();
                        EasyPreference.with(SignupStandardActivity.this).addString("easyUserType", user.userType).save();
                        EasyPreference.with(SignupStandardActivity.this).addInt("easyRegistStatus",1).save();

                        showToast("You are registered as Standard successfully!");
                        Intent intent =  new Intent(SignupStandardActivity.this, HomeActivity.class);
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
                showAlertDialog(error.getMessage());


            }
        };


        showProgress();
        SignupWithEmailAPI req = new SignupWithEmailAPI(first_name, last_name, email, pwd, user_type, auth_type, String.valueOf(Common.lat), String.valueOf(Common.lng), res, error);



        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);
*/

        // write DB
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = "";
        if(user != null){
            uid = user.getUid();
        }
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        String membership = EasyPreference.with(SignupStandardActivity.this).getString("easyMembership", "");
        user_type = EasyPreference.with(SignupStandardActivity.this).getString("easyUserType", "");

        if(uid == null || uid.equals("")){
            uid = GenerateRandomString.randomString(28);
        }
        User mUser = new User(uid, first_name, last_name, email, "", "", "", "", pwd, "", "", "", "", "", "", "", user_type,
                "", "", "", "");
        mRef.child(ReqConst.API_USER).child(uid).setValue(mUser);

        EasyPreference.with(SignupStandardActivity.this).addString("easyAuthType",auth_type).save();
        EasyPreference.with(SignupStandardActivity.this).addString("easyUserEmail",mUser.email).save();
        EasyPreference.with(SignupStandardActivity.this).addString("easyUserPassword",mUser.password).save();
        EasyPreference.with(SignupStandardActivity.this).addString("easyUserId", mUser.id).save();
        EasyPreference.with(SignupStandardActivity.this).addString("easyUserType", mUser.userType).save();
        EasyPreference.with(SignupStandardActivity.this).addString("easyUserFirstName", Common.user.firstName).save();
        EasyPreference.with(SignupStandardActivity.this).addString("easyUserLastName", Common.user.lastName).save();

        EasyPreference.with(SignupStandardActivity.this).addInt("easyRegistStatus", 1).save();
        Common.user = mUser;

        closeProgress();

        if (auth_type.equals("Google"))
        {
            signOut();
        } else if (auth_type.equals("Facebook"))
        {
            LoginManager.getInstance().logOut();
        } else
        {

        }

        String roleMsg = membership.length() > 0 ? "Business":"Standard";
        String toastMsg = "You are registered as " + roleMsg + " successfully!";
        showToast(toastMsg);
        Intent intent =  new Intent(SignupStandardActivity.this, RegisterCompletedActivity.class);

        startActivity(intent);
        finish();
    }

    @OnClick(R.id.txv_terms)
    void terms(){

        File file = new File(getCacheDir(), "terms.pdf");
        if (!file.exists()) {

            try {
                InputStream asset = getAssets().open("terms.pdf");
                FileOutputStream output = null;
                output = new FileOutputStream(file);
                final byte[] buffer = new byte[1024];
                int size;
                while ((size = asset.read(buffer)) != -1) {
                    output.write(buffer, 0, size);
                }
                asset.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        PDFView.with(this)
                .setfilepath(file.getAbsolutePath())
                .setSwipeOrientation(1)
                .start();

    }

    @OnClick(R.id.txv_policy)
    void policy(){

        File file = new File(getCacheDir(), "policy.pdf");
        if (!file.exists()) {

            try {
                InputStream asset = getAssets().open("policy.pdf");
                FileOutputStream output = null;
                output = new FileOutputStream(file);
                final byte[] buffer = new byte[1024];
                int size;
                while ((size = asset.read(buffer)) != -1) {
                    output.write(buffer, 0, size);
                }
                asset.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        PDFView.with(this)
                .setfilepath(file.getAbsolutePath())
                .setSwipeOrientation(1)
                .start();

    }

}
