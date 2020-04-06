package com.tur.bumerang.Business.Activity;

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
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.LoginActivity;
import com.tur.bumerang.Global.Activity.RegisterCompletedActivity;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.Global.Model.UserModel;
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

public class SignupBusinessActivity extends BaseActivity {


    @BindView(R.id.linSignBusiness)
    LinearLayout linSignBusiness;
   // @BindView(R.id.btnSignup)  Button btnSignup;
    @BindView(R.id.edtFirstName)
   EditText edtFirstName;
    @BindView(R.id.edtLastName) EditText edtLastName;
    @BindView(R.id.edtEmail) EditText edtEmail;
    @BindView(R.id.edtPwd) EditText edtPwd;
    @BindView(R.id.edtConfirmPwd) EditText edtConfirmPwd;

    String first_name, last_name, email, pwd, auth_type, user_type;

    GoogleSignInClient mGoogleSignInClient; /// for google login
    GoogleApiClient mGoogleApiClient;   // for google login
    public static CallbackManager callbackManager;  /// for facebook login
    UserModel user = null;

    private static final int RC_SIGN_IN = 9001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_business);
        FirebaseAuth.getInstance().signOut();

        ButterKnife.bind(this);

        hideKeyboard(linSignBusiness);

        ///////////// facebook login //////////////
        callbackManager = CallbackManager.Factory.create();

        ////////////////////////// Google Login /////////////////////////////

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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

                                    user = new UserModel();

                                    first_name = object.getString("first_name");
                                    last_name = object.getString("last_name");
                                    email = object.getString("email");
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
                                    user_type = ReqConst.USER_BUSINESS;
                                    auth_type = "Facebook";

                                    LoginManager.getInstance().logOut();

                                    onSignupBusinessApi(first_name, last_name, email, pwd, user_type, auth_type);


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
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
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
            String user_type = "Business";
            String auth_type = "Google";

            Log.d("Google Info: ", account.getEmail());
            Log.d("Google Info: ", account.getFamilyName());
            Log.d("Google Info: ", account.getGivenName());
            Log.d("Google Info: ", account.getPhotoUrl().toString());

            user = new UserModel();
            user.firstName = first_name;
            user.setEmail(email);
            user.avatarUrl = photo;
//            user.setCountry(country);
//            user.setCity(city);
//            user.setLatLng(myLatLang);
//            user.setAddress(getAddress(myLatLang.latitude, myLatLang.longitude));
//            user.setOption("google");

            signOut();

            onSignupBusinessApi(first_name, last_name, email, pwd, user_type, auth_type);

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

    @OnClick(R.id.btnSignup1)
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

        user_type = getResources().getString(R.string.business);
        auth_type = getResources().getString(R.string.email);

        EasyPreference.with(SignupBusinessActivity.this).addInt("easyRegistState",1).save();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        showProgress();
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            onSignupBusinessApi(first_name, last_name, email, pwd, user_type, auth_type);
                        } else {
                            showToast(task.getException().getMessage());
                            closeProgress();
                        }

                    }
                });
//        onSignupBusinessApi(first_name, last_name, email, pwd, user_type, auth_type);
    }

    void onSignupBusinessApi(String first_name, String last_name, String  email, String pwd, String user_type, String auth_type){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        if (uid == null || uid.equals(""))
            uid = GenerateRandomString.randomString(28);

        String membership = EasyPreference.with(SignupBusinessActivity.this).getString("easyMembership", "");
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        User mUser = new User(uid, first_name, last_name, email, "", "", "", "", pwd, "", "", "", "", "", "", "", user_type,
                membership, "", "", "");


        mRef.child(ReqConst.API_USER).child(uid).setValue(mUser);
        Common.user = mUser;

        EasyPreference.with(SignupBusinessActivity.this).addInt("easyRegistStatus",1).save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyAuthType","Email").save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyUserEmail",mUser.email).save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyUserPassword",mUser.password).save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyUserId", mUser.id).save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyUserType", mUser.userType).save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyUserFirstName", Common.user.firstName).save();
        EasyPreference.with(SignupBusinessActivity.this).addString("easyUserLastName", Common.user.lastName).save();

        closeProgress();

        if (auth_type.equals("Google"))
        {
            signOut();
        } else if (auth_type.equals("Facebook"))
        {
            LoginManager.getInstance().logOut();
        }

        showToast("You are registered as Business successfully!");
        Intent intent =  new Intent(SignupBusinessActivity.this, RegisterCompletedActivity.class);
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
