package com.tur.bumerang.Business.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReciveRequestActivity extends BaseActivity {

    @BindView(R.id.imv_req_product1) ImageView imv_req_product1;
    @BindView(R.id.txv_req_title1)   TextView txv_req_title1;
    @BindView(R.id.txv_req_price1) TextView txv_req_price1;
    @BindView(R.id.txv_req_dateunit1) TextView txv_req_dateunit1;
    @BindView(R.id.imv_req_avatar1) ImageView imv_req_avatar1;
    @BindView(R.id.txv_req_name1) TextView txv_req_name1;
    @BindView(R.id.txv_req_address1) TextView txv_req_address1;
    @BindView(R.id.txv_req_email1) TextView txv_req_email1;
    @BindView(R.id.txv_req_phonenum1) TextView txv_req_phonenum1;
    @BindView(R.id.imv_req_google1) ImageView imv_req_google1;
    @BindView(R.id.imv_req_facebook1) ImageView imv_req_facebook1;
    @BindView(R.id.txv_req_score1) TextView txv_req_score1;
    @BindView(R.id.txv_req_fromdate1) TextView txv_req_fromdate1;
    @BindView(R.id.txv_req_todate1) TextView txv_req_todate1;
    @BindView(R.id.txv_req_totalprice1) TextView txv_req_totalprice1;
    @BindView(R.id.txv_req_message1) TextView txv_req_message1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_request);

        ButterKnife.bind(this);


        Glide
                .with(this)
                .load(Constants.reqOwnerModel.productModel.image)
                .centerCrop()
                .placeholder(R.mipmap.default_car_img)
                .into(imv_req_product1);

        txv_req_title1.setText(Constants.reqOwnerModel.productModel.title);
        txv_req_price1.setText(Constants.reqOwnerModel.productModel.price + "₺" + "/" + " ");
        txv_req_dateunit1.setText(Constants.reqOwnerModel.productModel.date_unit);

        Glide
                .with(this)
                .load(Constants.reqOwnerModel.userModel.avatarUrl)
                .centerCrop()
                .placeholder(R.mipmap.default_car_img)
                .into(imv_req_avatar1);
        txv_req_name1.setText(Constants.reqOwnerModel.userModel.lastName + Constants.reqOwnerModel.userModel.firstName);
        txv_req_address1.setText(Constants.reqOwnerModel.userModel.address);
        txv_req_email1.setText(Constants.reqOwnerModel.userModel.email);
        txv_req_phonenum1.setText(Constants.reqOwnerModel.userModel.phone);
        txv_req_score1.setText(Constants.reqOwnerModel.userModel.score);

        String mytime=Constants.reqOwnerModel.start_date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(mytime);
            Log.d("11111=>", myDate.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
        String finalDate = timeFormat.format(myDate);

        txv_req_fromdate1.setText(finalDate);

        String mytime1=Constants.reqOwnerModel.end_date;
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date myDate1 = null;
        try {
            myDate1 = dateFormat.parse(mytime1);
            Log.d("11111=>", myDate.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        String finalDate1 = timeFormat.format(myDate1);

        txv_req_todate1.setText(finalDate1);


        txv_req_totalprice1.setText(Constants.reqOwnerModel.rental_price);

        txv_req_message1.setText(Constants.reqOwnerModel.users_message);


    }

}
