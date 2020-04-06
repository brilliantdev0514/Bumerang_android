package com.tur.bumerang.Global.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Global.Model.UserProductModel;
import com.tur.bumerang.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User_Product_listAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserProductModel> sonses = new ArrayList<>();

    public User_Product_listAdapter(Context context) {
        super();
        this.context = context;
    }

    public void loadData( ArrayList<UserProductModel> data){
//        this.sonses.removeAll(data);
        this.sonses.clear();
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
            convertView = inflater.inflate(R.layout.item_my_products, parent, false);

            holder.imv_myproduct_image = (RoundedImageView)convertView.findViewById(R.id.imv_myproduct_image);
            holder.txv_myproduct_dateunit = (TextView)convertView.findViewById(R.id.txv_myproduct_dateunit);
            holder.txv_myproduct_price = (TextView)convertView.findViewById(R.id.txv_myproduct_price);
            holder.txv_myproduct_rentalstatus = (TextView)convertView.findViewById(R.id.txv_myproduct_rentstatus);
            holder.txv_created_date = (TextView)convertView.findViewById(R.id.txv_created_date);
            holder.txv_updated_date = (TextView)convertView.findViewById(R.id.txv_updated_date);
            holder.txv_myproduct_title = (TextView)convertView.findViewById(R.id.txv_myproduct_title);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        UserProductModel userProductModel = (UserProductModel) sonses.get(position);
        Glide
                .with(context)
                .load(userProductModel.image)
                .centerCrop()
                .into(holder.imv_myproduct_image);
        holder.txv_myproduct_title.setText(userProductModel.title);
        holder.txv_myproduct_rentalstatus.setText(String.valueOf(userProductModel.rental_state));
        holder.txv_myproduct_price.setText(String.valueOf(userProductModel.price) + "₺");
        holder.txv_myproduct_dateunit.setText( "/" + " " + userProductModel.date_unit);
        holder.txv_created_date.setText(userProductModel.created_at);
        holder.txv_updated_date.setText(userProductModel.updated_at);

        String mytime=userProductModel.updated_at;
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

        holder.txv_updated_date.setText(finalDate);

        String mytime1=userProductModel.created_at;
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date myDate1 = null;
        try {
            myDate1 = dateFormat1.parse(mytime1);
            Log.d("11111=>", myDate1.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        String finalDate1 = timeFormat1.format(myDate1);

        holder.txv_created_date.setText(finalDate1);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "List item clicked!", Toast.LENGTH_SHORT);
//                Constants.reqOwnerModel=requestOwnerModel;
//                Intent intent = new Intent(context, ReciveRequestActivity.class);
//                context.startActivity(intent);
            }
        });

        return convertView;
    }


    public class CustomHolder {

        RoundedImageView imv_myproduct_image;
        TextView txv_myproduct_title;
        TextView txv_myproduct_price;
        TextView txv_myproduct_dateunit;
        TextView txv_myproduct_rentalstatus;
        TextView txv_updated_date;
        TextView txv_created_date;

    }


}
