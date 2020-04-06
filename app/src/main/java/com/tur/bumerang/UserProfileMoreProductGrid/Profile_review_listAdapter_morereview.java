package com.tur.bumerang.UserProfileMoreProductGrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tur.bumerang.Global.Model.ReviewModel;
import com.tur.bumerang.R;

import java.util.ArrayList;

public class Profile_review_listAdapter_morereview extends BaseAdapter {

    private UserProfileMoreReviewProductAll activity;

    private ArrayList<ReviewModel> sonses = new ArrayList<>();

    public Profile_review_listAdapter_morereview(UserProfileMoreReviewProductAll context, ArrayList<ReviewModel> orders) {

        super();
        this.activity = context;
        sonses=orders;
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
            convertView = inflater.inflate(R.layout.item_userprofile_review, parent, false);
//            holder.roundedImageView=(RoundedImageView) convertView.findViewById(R.id.imageView);
//            holder.imv_flag=(ImageView)convertView.findViewById(R.id.imv_rent_bookmark);
//           /* holder.txvuniqueid=(TextView)convertView.findViewById(R.id.txv_uniqueid);
//            holder.txvorderdate=(TextView)convertView.findViewById(R.id.txv_orderdate);
//            holder.btn_confirm=(TextView)convertView.findViewById(R.id.btn_confirm);*/
//            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

//        final ReviewModel trip1 = (ReviewModel) sonses.get(position);
//        int loadimage=-1;
//        if(trip1.getImagetype()==0)loadimage = R.mipmap.default_house_img;
//        else if(trip1.getImagetype()==1)loadimage = R.mipmap.default_car_img;
//        else if(trip1.getImagetype()==2)loadimage = R.mipmap.default_car_img;
//        else if(trip1.getImagetype()==3)loadimage = R.mipmap.default_house_img;
//        else if(trip1.getImagetype()==4)loadimage = R.mipmap.default_dress_img;
//        else if(trip1.getImagetype()==5)loadimage = R.mipmap.default_bike_img;
//        else if(trip1.getImagetype()==6)loadimage = R.mipmap.default_camera_img;
//        else if(trip1.getImagetype()==7)loadimage = R.mipmap.default_camera_img;
//        else if(trip1.getImagetype()==8)loadimage = R.mipmap.default_camera_img;
//        else if(trip1.getImagetype()==9)loadimage = R.mipmap.default_camera_img;
//
//        Log.d("imagetype===", String.valueOf(position)+"____"+String.valueOf(trip1.getImagetype()));
//
//        Glide
//                .with(activity)
//                .load(loadimage)
//                .centerCrop()
//                .placeholder(loadimage)
//                .into(holder.roundedImageView);
//
//        if(trip1.getFlag()) holder.imv_flag.setVisibility(View.VISIBLE);
//        else holder.imv_flag.setVisibility(View.INVISIBLE);
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Constants.ReviewModel=trip1;
//                if(trip1.getImagetype()==0){
//                    Intent intent = new Intent(activity, DetailhomeActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==1){
//                    Intent intent = new Intent(activity, DetailCarActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==2){
//                    Intent intent = new Intent(activity, DetailcaravanActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==3){
//                    Intent intent = new Intent(activity, DetailvehicleActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==4){
//                    Intent intent = new Intent(activity, DetaildressActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==5){
//                    Intent intent = new Intent(activity, DetailbikeActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==6){
//                    Intent intent = new Intent(activity, DetailcameraActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==7){
//                    Intent intent = new Intent(activity, DetailsporeActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==8){
//                    Intent intent = new Intent(activity, DetailkampActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }else if(trip1.getImagetype()==9){
//                    Intent intent = new Intent(activity, DetailmusicActivity.class);
//                    activity.startActivity(intent);
//                    activity.finish();
//                }
//            }
//        });


        return convertView;
    }


    public class CustomHolder {
//        RoundedImageView roundedImageView;
//        ImageView imv_flag;
        //TextView txvuniqueid, txvstatus,  txvorderdate, btn_confirm;

    }


}
