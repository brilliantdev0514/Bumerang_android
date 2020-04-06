package com.tur.bumerang.Global.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Business.Activity.ReciveRequestActivity;
import com.tur.bumerang.Business.Model.RequestOwnerModel;
import com.tur.bumerang.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Request_history_listAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RequestOwnerModel> sonses = new ArrayList<>();

    public Request_history_listAdapter(Context context) {
        super();
        this.context = context;
    }

    public void loadData( ArrayList<RequestOwnerModel> data){
        this.sonses.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return sonses.size();
    }

    @Override
    public Object getItem(int position) {
        return sonses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final CustomHolder holder;


        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_request_history, parent, false);

            holder.imv_req_his = (RoundedImageView)convertView.findViewById(R.id.imv_req_his);
            holder.txv_req_his_dateunit = (TextView)convertView.findViewById(R.id.txv_req_his_dateunit);
            holder.txv_req_his_reqdate = (TextView)convertView.findViewById(R.id.txv_req_his_reqdate);
            holder.txv_req_his_price = (TextView)convertView.findViewById(R.id.txv_req_his_price);
            holder.txv_req_his_rentstatus = (TextView)convertView.findViewById(R.id.txv_req_his_rentstatus);
            holder.txv_req_title = (TextView)convertView.findViewById(R.id.txv_req_title);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        RequestOwnerModel requestOwnerModel = (RequestOwnerModel) sonses.get(position);
        Glide
                .with(context)
                .load(requestOwnerModel.productModel.image)
                .centerCrop()
                .into(holder.imv_req_his);
        holder.txv_req_title.setText(requestOwnerModel.productModel.title);
        holder.txv_req_his_rentstatus.setText(String.valueOf(requestOwnerModel.owner_acceted_state));
        holder.txv_req_his_price.setText(String.valueOf(requestOwnerModel.productModel.price) + "₺");
        holder.txv_req_his_dateunit.setText( "/" + " " + requestOwnerModel.productModel.date_unit);

        String mytime=requestOwnerModel.request_date;
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

        holder.txv_req_his_reqdate.setText(finalDate);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.reqOwnerModel=requestOwnerModel;

                Intent intent = new Intent(context, ReciveRequestActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }


    public class CustomHolder {

        RoundedImageView imv_req_his;
        TextView txv_req_title;
        TextView txv_req_his_price;
        TextView txv_req_his_dateunit;
        TextView txv_req_his_reqdate;
        TextView txv_req_his_rentstatus;

    }


}
