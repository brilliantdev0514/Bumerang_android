package com.tur.bumerang.Standard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.API.RentRequestAPI;
import com.tur.bumerang.Global.Activity.RequestSentCompletedActivity;
import com.tur.bumerang.Global.Model.RentalModel;
import com.tur.bumerang.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RentPageActivity extends BaseActivity {

    @BindView(R.id.btn_rent) Button btn_rent;
    @BindView(R.id.edt_rent_msg) EditText edt_rent_msg;

    @BindView(R.id.total_date)  TextView total_date;
    @BindView(R.id.from_date) TextView from_date;
    @BindView(R.id.to_date) TextView to_date;
    @BindView(R.id.total_price) TextView total_price;
    @BindView(R.id.rental_price) TextView rental_price;
    //@BindView(R.id.txv_service_fee) TextView txv_service_fee;

    String  product_id,  start_date, end_date, users_message, service_fee;
    String product_price, date_unit;
    String user_id, owner_id;

    int selecteddate_position =0;
    Date startdate=new Date();
    Date enddate= new Date();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentpage);

        ButterKnife.bind(this);

        com.applandeo.materialcalendarview.CalendarView calendarView = (com.applandeo.materialcalendarview.CalendarView) findViewById(R.id.calendarView);

        //service_fee = Constants.itemModel.service_fee;
        product_price = String.valueOf(Constants.itemModel.price);
        date_unit = String.valueOf(Constants.itemModel.date_unit);

        rental_price.setText(String.valueOf(product_price) + "₺" + "/" + date_unit);
        //txv_service_fee.setText(String.valueOf(service_fee));

        btn_rent.setOnClickListener(v -> {
            for (Calendar calendar : calendarView.getSelectedDates()) {
                System.out.println(calendar.getTime().toString());
            }
            if(start_date == null || end_date == null){
                showToast("You have to select date!");
                return;
            }
            final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            myAnim.setInterpolator(interpolator);
            btn_rent.startAnimation(myAnim);
            users_message = edt_rent_msg.getText().toString();
            rentRequest();
        });

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                Log.d("start==", clickedDayCalendar.getTime().toString());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String selectedDate = sdf.format(clickedDayCalendar.getTime());
                Log.d("formateddate==", selectedDate);

                if(selecteddate_position==0){
                    from_date.setText(selectedDate);
                    to_date.setText("");
                    total_date.setText("");
                    selecteddate_position++;
                    startdate=clickedDayCalendar.getTime();
                }else {
                    to_date.setText(selectedDate);
                    selecteddate_position=0;
                    enddate=clickedDayCalendar.getTime();

                    long diff = Math.abs(enddate.getTime() - startdate.getTime());

                    long seconds = diff / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;
                    total_date.setText(String.valueOf((int)days+1));


                    double total_p, one_p;
                    one_p = Double.parseDouble(Constants.itemModel.price);

                    if(date_unit.equals("Günlük")) {
                        total_p = (one_p * ((double) days + 1));
                        total_price.setText(String.format("%.2f", total_p) + " ₺");
                    }else if(date_unit.equals("Haftalık")){
                        total_p = ((one_p / 7) * ((double) days + 1));
                        total_price.setText(String.format("%.2f", total_p) + " ₺");
                    }else if(date_unit.equals("Aylık")){
                        total_p = ((one_p / 30) * ((double) days + 1));
                        total_price.setText(String.format("%.2f", total_p) + " ₺");
                    }

                }

//                String mytime=from_date.getText().toString();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//                Date myDate = null;
//                try {
//                    myDate = dateFormat.parse(mytime);
//                    Log.d("11111=>", myDate.toString());
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String finalDate = timeFormat.format(myDate);
//
//                start_date = finalDate;
               start_date = from_date.getText().toString();
               Log.d("startDate ====>", start_date);


//                String mytime1=to_date.getText().toString();
//                Log.d("11111+1=>", to_date.getText().toString());
//                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/mm/yyyy");
//                Date myDate1 = null;
//                try {
//                    myDate1 = dateFormat.parse(mytime1);
//                    Log.d("11111=>", myDate.toString());
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                SimpleDateFormat timeFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//                String finalDate1 = timeFormat.format(myDate1);
//
//                end_date = finalDate1;
                end_date = to_date.getText().toString();
                Log.d("endDate ====>", end_date);
            }
        });

        user_id = EasyPreference.with(this).getString("easyUserId","");
        product_id = String.valueOf(Constants.itemModel.id);
        owner_id = Constants.itemModel.owner_id;
        /*start_date = from_date.getText().toString();
        end_date = to_date.getText().toString();*/
        users_message = edt_rent_msg.getText().toString();

        //service_fee = Constants.itemModel.service_fee;


    }

    void rentRequest(){

        /*final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_rent==", json);
                closeProgress();
                try{
                    JSONObject res = new JSONObject(json);
                        if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){
//                        UserModel user = new UserModel(res.getJSONObject("user_info"));
////                        Common.user = user;

                        Intent intent = new Intent(RentPageActivity.this, RequestSentCompletedActivity.class);
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
            }
        };

        showProgress();
        RentRequestAPI req = new RentRequestAPI(user_id, product_id, owner_id, changedateformat_toserver(start_date), changedateformat_toserver(end_date), users_message, res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);*/

        showProgress();
        RentalModel mRentalModel = new RentalModel(user_id, product_id, owner_id, start_date, end_date, users_message);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child("rentalProduct").child(product_id).setValue(mRentalModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DatabaseReference mRef2 = FirebaseDatabase.getInstance().getReference();
                        mRef2.child(ReqConst.API_PRODUCT)
                                .child(product_id)
                                .child("rental_state")
                                .setValue("Yes")
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        closeProgress();
                                        Intent intent = new Intent(RentPageActivity.this, RequestSentCompletedActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        closeProgress();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        closeProgress();
                        showAlertDialog(getString(R.string.serverFailed));
                    }
                });
    }

    String changedateformat_toserver(String date){   //15/10/2019
        String changeddate="";
        if(date.length()>0){
            String[] date_value = date.split("/");
            String year = date_value[2];
            String month = date_value[1];
            String day= date_value[0];
            changeddate = year+"-"+month+"-"+day;
        }
        return changeddate;
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