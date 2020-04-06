package com.tur.bumerang.Global.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Global.Activity.DetailCarActivity;
import com.tur.bumerang.Global.Activity.DetailbikeActivity;
import com.tur.bumerang.Global.Activity.DetailcameraActivity;
import com.tur.bumerang.Global.Activity.DetailcaravanActivity;
import com.tur.bumerang.Global.Activity.DetaildressActivity;
import com.tur.bumerang.Global.Activity.DetailhomeActivity;
import com.tur.bumerang.Global.Activity.DetailkampActivity;
import com.tur.bumerang.Global.Activity.DetailmusicActivity;
import com.tur.bumerang.Global.Activity.DetailsporeActivity;
import com.tur.bumerang.Global.Activity.DetailvehicleActivity;
import com.tur.bumerang.Global.Activity.UserProfileActivity;
import com.tur.bumerang.Global.Model.ItemModel;
import com.tur.bumerang.R;
import com.tur.bumerang.UserProfileMoreProductGrid.UserProfileMoreProductAll;

import java.util.ArrayList;

public class Profile_history_gridAdapter extends BaseAdapter {
    private UserProfileActivity activity;
    private UserProfileMoreProductAll activity1;
    private ArrayList<ItemModel> sonses = new ArrayList<>();

    public Profile_history_gridAdapter(UserProfileActivity context, ArrayList<ItemModel> orders) {

        super();
        this.activity = context;
    }

    public Profile_history_gridAdapter(UserProfileMoreProductAll context, ArrayList<ItemModel> orders) {

        super();
        this.activity1 = context;
    }


    public void loadData( ArrayList<ItemModel> data){
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
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_userprofile_history, parent, false);
            holder.roundedImageView=(RoundedImageView) convertView.findViewById(R.id.imv_userprofile_his_image);
            holder.imv_flag=(ImageView)convertView.findViewById(R.id.imv_rent_bookmark);
            holder.txv_title = (TextView)convertView.findViewById(R.id.txv_userprofile_his_title);
            holder.txv_deposit = (TextView)convertView.findViewById(R.id.txv_userprofile_his_deposit);
            holder.txv_price = (TextView)convertView.findViewById(R.id.txv_userprofile_his_price);
            holder.txv_dateunit = (TextView)convertView.findViewById(R.id.txv_userprofile_his_dateunit);

            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final ItemModel trip1 = (ItemModel) sonses.get(position);
        int loadimage=-1;
        if(trip1.getImagetype()==0)loadimage = R.mipmap.default_house_img;
        else if(trip1.getImagetype()==1)loadimage = R.mipmap.default_car_img;
        else if(trip1.getImagetype()==2)loadimage = R.mipmap.default_car_img;
        else if(trip1.getImagetype()==3)loadimage = R.mipmap.default_house_img;
        else if(trip1.getImagetype()==4)loadimage = R.mipmap.default_dress_img;
        else if(trip1.getImagetype()==5)loadimage = R.mipmap.default_bike_img;
        else if(trip1.getImagetype()==6)loadimage = R.mipmap.default_camera_img;
        else if(trip1.getImagetype()==7)loadimage = R.mipmap.default_camera_img;
        else if(trip1.getImagetype()==8)loadimage = R.mipmap.default_camera_img;
        else if(trip1.getImagetype()==9)loadimage = R.mipmap.default_camera_img;

        Log.d("imagetype===", String.valueOf(position)+"____"+String.valueOf(trip1.getImagetype()));
//        Glide
//                .with(context)
//                .load(trip1.image_url.get(0))
//                .centerCrop()
//                .placeholder(loadimage)
//                .into(holder.roundedImageView);
//
//        holder.txv_griditem_title.setText(trip1.title);
//        holder.txv_griditem_price.setText(trip1.price+"₺"+" "+"/"+" ");
//        holder.txv_griditem_dateunit.setText(trip1.date_unit);



        Glide
                .with(activity)
                .load(trip1.image_url.get(0))
                .centerCrop()
                .placeholder(loadimage)
                .into(holder.roundedImageView);
        holder.txv_title.setText(trip1.title);
        holder.txv_deposit.setText(trip1.deposit);
        holder.txv_price.setText(trip1.price+"₺"+" "+"/"+" ");
        holder.txv_dateunit.setText(trip1.date_unit);


        if(trip1.getFlag()) holder.imv_flag.setVisibility(View.VISIBLE);
        else holder.imv_flag.setVisibility(View.INVISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Constants.itemModel=trip1;
                if(trip1.getImagetype()==0){
                    Intent intent = new Intent(activity, DetailhomeActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==1){
                    Intent intent = new Intent(activity, DetailCarActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==2){
                    Intent intent = new Intent(activity, DetailcaravanActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==3){
                    Intent intent = new Intent(activity, DetailvehicleActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==4){
                    Intent intent = new Intent(activity, DetaildressActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==5){
                    Intent intent = new Intent(activity, DetailbikeActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==6){
                    Intent intent = new Intent(activity, DetailcameraActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==7){
                    Intent intent = new Intent(activity, DetailsporeActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==8){
                    Intent intent = new Intent(activity, DetailkampActivity.class);
                    activity.startActivity(intent);
                }else if(trip1.getImagetype()==9){
                    Intent intent = new Intent(activity, DetailmusicActivity.class);
                    activity.startActivity(intent);
                }
            }
        });


        return convertView;
    }


    public class CustomHolder {
        RoundedImageView roundedImageView;
        ImageView imv_flag;
        TextView txv_title, txv_deposit, txv_price, txv_dateunit;
    }


}
