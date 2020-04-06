package com.tur.bumerang.Business.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.billingclient.api.Purchase;
import com.chahinem.pageindicator.PageIndicator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Global.Activity.PaymentsUtil;
import com.tur.bumerang.Standard.Activity.SignupStandardActivity;
import com.tur.bumerang.Utils.IabHelper;
import com.tur.bumerang.Utils.IabResult;
import com.tur.bumerang.Utils.Inventory;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Business.Adapter.Membership_setmembertypeAdapter;
import com.tur.bumerang.Global.Model.RegisterModel;
import com.tur.bumerang.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Optional;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MembershipActivity extends BaseActivity {

    private PaymentsClient mPaymentsClient;
    private View mGooglePayButton;
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 991;

    private TextView mGooglePayStatusText;

    private IabHelper mIabHelper;

    private String mBase64EncodedPublicKey;

    private Button mBtnBuySubscription;
    private TextView mTxtStatus;

    private String TAG="BuyIAP";
    private boolean isInAppSupported=false;

    private boolean isSubscriptionDone=false;


//    @BindView(R.id.btn_member_select)
//    Button btn_member_select;

    DiscreteScrollView itemPicker;
    LinearLayout lyt_start, lyt_plus, lyt_prime,LL_main;
    String strType = "";



    String user_id, membership;

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
                // handle error here
            }
            else {
                // does the user have the premium upgrade?


                //Now here we can check if Purchase Already Brought
                isSubscriptionDone = inventory.hasPurchase(Constants.SKU_SUBSCRIPTION);

                Log.i(TAG,"Purchase of Subscription is"+ isSubscriptionDone);

                // update UI accordingly
            }
        }
    };

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership1);
        ButterKnife.bind(this);

        ////////////////pament

        mGooglePayButton = findViewById(R.id.googlepay_button);
        mGooglePayStatusText = findViewById(R.id.googlepay_status);

        // Initialize a Google Pay API client for an environment suitable for testing.
        // It's recommended to create the PaymentsClient object inside of the onCreate method.
        mPaymentsClient = PaymentsUtil.createPaymentsClient(this);
        possiblyShowGooglePayButton();

        mGooglePayButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      //  requestPayment(view);
                        buySubscription();
                    }
                });


        ////////////////payment end
        strType = getIntent().getStringExtra("type");

        user_id = EasyPreference.with(this).getString("easyUserId", "");

        lyt_start = (LinearLayout)findViewById(R.id.lyt_membership_start);
        LL_main = (LinearLayout)findViewById(R.id.LL_main);
        lyt_plus = (LinearLayout)findViewById(R.id.lyt_membership_plus);
        lyt_prime = (LinearLayout)findViewById(R.id.lyt_membership_prime);

        GradientDrawable back_start = (GradientDrawable)lyt_start.getBackground();
        GradientDrawable back_plus = (GradientDrawable)lyt_plus.getBackground();
        GradientDrawable back_prime = (GradientDrawable)lyt_prime.getBackground();

        PageIndicator indicator = (PageIndicator) findViewById(R.id.pageIndicator);
        itemPicker = (DiscreteScrollView) findViewById(R.id.item_register_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);

//        itemPicker.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
//                Log.d("postions ===", String.valueOf(adapterPosition));
//                if(adapterPosition == 0){
//                    membership = adapterPosition + "";
//                    back_start.setColor(Color.parseColor("#ff5c5c"));
//                    back_plus.setColor(Color.parseColor("#fffd36"));
//                    back_prime.setColor(Color.parseColor("#246dff"));
//                   // LL_main.set(Color.parseColor("#eeeeee"));
//                    LL_main.setBackgroundColor(Color.parseColor("#7159E2"));
//                    btn_member_select.setBackgroundColor(Color.parseColor("#6342CE"));
//                }
//                if(adapterPosition == 1){
//                    membership = adapterPosition + "";
//                    back_start.setColor(Color.parseColor("#246dff"));
//                    back_plus.setColor(Color.parseColor("#ff5c5c"));
//                    back_prime.setColor(Color.parseColor("#fffd36"));
//
//                    LL_main.setBackgroundColor(Color.parseColor("#1BC489"));
//                    btn_member_select.setBackgroundColor(Color.parseColor("#21AD8C"));
//                }
//                if(adapterPosition == 2){
//                    membership = adapterPosition + "";
//                    back_start.setColor(Color.parseColor("#fffd36"));
//                    back_plus.setColor(Color.parseColor("#246dff"));
//                    back_prime.setColor(Color.parseColor("#ff5c5c"));
//
//                    LL_main.setBackgroundColor(Color.parseColor("#FB3862"));
//                    btn_member_select.setBackgroundColor(Color.parseColor("#AA2642"));
//                }
//            }
//        });
        ArrayList<RegisterModel> data = new ArrayList<>();

//        RegisterModel registerModel = new RegisterModel(0,R.mipmap.membership_start, "Start Membership", "Start member can upload 20 products in this app and can advertise one of them!");
//        data.add(registerModel);
//
        RegisterModel registerModel1 = new RegisterModel(1,R.mipmap.membership_plus, "Plus Membership", "Plus member can upload 100 products in this app and can advertise one of them.");
        data.add(registerModel1);
//
//        RegisterModel registerModel2 = new RegisterModel(1,R.mipmap.membership_prime, "Prime Membership", "Prime member can upload 300 products in this app and can advertise one of them.");
//        data.add(registerModel2);

        itemPicker.setAdapter(new Membership_setmembertypeAdapter(data, this));
        indicator.attachTo(itemPicker);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.85f)
                .build());

        initIAB();
    }


    private void possiblyShowGooglePayButton() {
        final Optional<JSONObject> isReadyToPayJson = PaymentsUtil.getIsReadyToPayRequest();
        if (!isReadyToPayJson.isPresent()) {
            return;
        }
        IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
        if (request == null) {
            return;
        }

        // The call to isReadyToPay is asynchronous and returns a Task. We need to provide an
        // OnCompleteListener to be triggered when the result of the call is known.
        Task<Boolean> task = mPaymentsClient.isReadyToPay(request);
        task.addOnCompleteListener(this,
                new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            setGooglePayAvailable(task.getResult());
                        } else {
                            Log.w("isReadyToPay failed", task.getException());
                        }
                    }
                });
    }

    private void setGooglePayAvailable(boolean available) {
        if (available) {
            mGooglePayStatusText.setVisibility(View.GONE);
            mGooglePayButton.setVisibility(View.VISIBLE);
        } else {
            mGooglePayStatusText.setText(R.string.googlepay_status_unavailable);
        }
    }

    /**
     * Handle a resolved activity from the Google Pay payment sheet.
     *
     * @param requestCode Request code originally supplied to AutoResolveHelper in requestPayment().
     * @param resultCode Result code returned by the Google Pay API.
     * @param data Intent from the Google Pay API containing payment or error data.
     * @see <a href="https://developer.android.com/training/basics/intents/result">Getting a result
     *     from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // value passed in AutoResolveHelper
            case LOAD_PAYMENT_DATA_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        PaymentData paymentData = PaymentData.getFromIntent(data);
                        handlePaymentSuccess(paymentData);
                        break;
                    case Activity.RESULT_CANCELED:
                        // Nothing to here normally - the user simply cancelled without selecting a
                        // payment method.
                        break;
                    case AutoResolveHelper.RESULT_ERROR:
                        Status status = AutoResolveHelper.getStatusFromIntent(data);
                        handleError(status.getStatusCode());
                        break;
                    default:
                        // Do nothing.
                }

                // Re-enables the Google Pay payment button.
                mGooglePayButton.setClickable(true);
                break;
        }

        if(requestCode==Constants.REQUEST_BUY_SUBSCRIPTION ){
            mIabHelper.handleActivityResult(requestCode,resultCode,data);
        }
    }

    private void handlePaymentSuccess(PaymentData paymentData) {
        String paymentInformation = paymentData.toJson();

        // Token will be null if PaymentDataRequest was not constructed using fromJson(String).
        if (paymentInformation == null) {
            return;
        }
        JSONObject paymentMethodData;

        try {
            paymentMethodData = new JSONObject(paymentInformation).getJSONObject("paymentMethodData");
            // If the gateway is set to "example", no payment information is returned - instead, the
            // token will only consist of "examplePaymentMethodToken".
            if (paymentMethodData
                    .getJSONObject("tokenizationData")
                    .getString("type")
                    .equals("PAYMENT_GATEWAY")
                    && paymentMethodData
                    .getJSONObject("tokenizationData")
                    .getString("token")
                    .equals("examplePaymentMethodToken")) {
                AlertDialog alertDialog =
                        new AlertDialog.Builder(this)
                                .setTitle("Warning")
                                .setMessage(
                                        "Gateway name set to \"example\" - please modify "
                                                + "Constants.java and replace it with your own gateway.")
                                .setPositiveButton("OK", null)
                                .create();
                alertDialog.show();
            }

            String billingName =
                    paymentMethodData.getJSONObject("info").getJSONObject("billingAddress").getString("name");
            Log.d("BillingName", billingName);
            Toast.makeText(this, getString(R.string.payments_show_name, billingName), Toast.LENGTH_LONG)
                    .show();

            // Logging token string.
            Log.d("GooglePaymentToken", paymentMethodData.getJSONObject("tokenizationData").getString("token"));
        } catch (JSONException e) {
            Log.e("handlePaymentSuccess", "Error: " + e.toString());
            return;
        }
    }

    /**
     * At this stage, the user has already seen a popup informing them an error occurred. Normally,
     * only logging is required.
     *
     * @param statusCode will hold the value of any constant from CommonStatusCode or one of the
     *     WalletConstants.ERROR_CODE_* constants.
     * @see <a
     *     href="https://developers.google.com/android/reference/com/google/android/gms/wallet/WalletConstants#constant-summary">
     *     Wallet Constants Library</a>
     */
    private void handleError(int statusCode) {
        Log.w("loadPaymentData failed", String.format("Error code: %d", statusCode));
    }

    // This method is called when the Pay with Google button is clicked.
    public void requestPayment(View view) {
        // Disables the button to prevent multiple clicks.
        mGooglePayButton.setClickable(false);

        // The price provided to the API should include taxes and shipping.
        // This price is not displayed to the user.
        String price = PaymentsUtil.microsToString(5);

        // TransactionInfo transaction = PaymentsUtil.createTransaction(price);
        Optional<JSONObject> paymentDataRequestJson = PaymentsUtil.getPaymentDataRequest(price);
        if (!paymentDataRequestJson.isPresent()) {
            return;
        }
        PaymentDataRequest request =
                PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());

        // Since loadPaymentData may show the UI asking the user to select a payment method, we use
        // AutoResolveHelper to wait for the user interacting with it. Once completed,
        // onActivityResult will be called with the result.
        if (request != null) {
            AutoResolveHelper.resolveTask(
                    mPaymentsClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE);

        }
    }




//    @OnClick(R.id.btn_member_select)
//    void onSelectMembership(){
//
//        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
//        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//        myAnim.setInterpolator(interpolator);
//        btn_member_select.startAnimation(myAnim);
//
//        updateMembership();
//    }

    private void updateMembership() {

        /*final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_membership==", json);
                closeProgress();
                try{

                    JSONObject res = new JSONObject(json);

                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){

                        showToast("Membership update success!");

                        EasyPreference.with(MembershipActivity.this).addInt("easyMembership",membership).save();

                        Intent intent =  new Intent(MembershipActivity.this, RegisterActivity.class );
                        intent.putExtra(Constants.SHOW_USER_REGISTER_PAGE, Constants.SHOW_BUSINESS_REGISTER_PAGE);
                        startActivity(intent);
                        finish();

                    }else {
                        Log.d("req_msg ====>", ReqConst.MSG);
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
        MembershipAPI req = new MembershipAPI(user_id, membership, res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);*/
        membership = "1";
       EasyPreference.with(MembershipActivity.this).addString("easyMembership",membership).save();
        Intent intent = new Intent(this, SignupStandardActivity.class);
        if (strType != null && strType.equals("upgrade"))
            intent = new Intent(this, SignupBusinessActivity.class);
        startActivity(intent);
       finish();

//        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
//        mRef.child(ReqConst.API_USER).child(user_id).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    @OnClick(R.id.txv_back)
    void goBack(){
        finish();
    }



    private void initIAB(){

        mIabHelper=new IabHelper(this,mBase64EncodedPublicKey);
        // enable debug logging (for a production application, you should set this to false).
        mIabHelper.enableDebugLogging(true);

        mIabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh no, there was a problem.
                    Log.d(TAG, "Problem setting up In-app Billing: " + result);
                }else{
                    isInAppSupported=true;

                    //Query if Subscription already buy
                    mIabHelper.queryInventoryAsync(true,mGotInventoryListener);

                }
                // Hooray, IAB is fully set up!
            }
        });
    }

    private void buySubscription(){

        mIabHelper.launchPurchaseFlow(this,Constants.SKU_SUBSCRIPTION,Constants.REQUEST_BUY_SUBSCRIPTION,mPurchaseFinishedListener);
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, com.tur.bumerang.Utils.Purchase info) {

            if (result.isFailure()) {
                Log.d(TAG, "Error purchasing: " + result);

                return;
            }
            else if(info.getSku().equalsIgnoreCase(Constants.SKU_SUBSCRIPTION)){

                //User Buy Subscription Do your stuff related to it

                mTxtStatus.setText("You are subscribe user of "+ Constants.SKU_SUBSCRIPTION);
            }
        }

        public void onIabPurchaseFinished(IabResult result, Purchase purchase)
        {
            if (result.isFailure()) {
                Log.d(TAG, "Error purchasing: " + result);

                return;
            }
            else if(purchase.getSku().equalsIgnoreCase(Constants.SKU_SUBSCRIPTION)){

                //User Buy Subscription Do your stuff related to it

                mTxtStatus.setText("You are subscribe user of "+ Constants.SKU_SUBSCRIPTION);
            }
        }
    };



}
