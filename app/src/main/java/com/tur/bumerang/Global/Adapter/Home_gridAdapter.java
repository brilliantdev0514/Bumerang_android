package com.tur.bumerang.Global.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.CustomTabMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iamhabib.easy_preference.EasyPreference;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.Activity.UploadApartmentActivity;
import com.tur.bumerang.Business.Activity.UploadBikeActivity;
import com.tur.bumerang.Business.Activity.UploadCameraActivity;
import com.tur.bumerang.Business.Activity.UploadCaravanActivity;
import com.tur.bumerang.Business.Activity.UploadDressActivity;
import com.tur.bumerang.Business.Activity.UploadKampActivity;
import com.tur.bumerang.Business.Activity.UploadMusicActivity;
import com.tur.bumerang.Business.Activity.UploadOtherActivity;
import com.tur.bumerang.Business.Activity.UploadRentalcarActivity;
import com.tur.bumerang.Business.Activity.UploadVehicleActivity;
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
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Fragment.HomeFragment;
import com.tur.bumerang.Global.Fragment.MainFragment;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.Global.Model.ProductModel;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;

import java.util.ArrayList;

import butterknife.OnClick;

public class Home_gridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> sonses = new ArrayList<>();

    public  Home_gridAdapter(Context context, ArrayList<Product> orders) {

        super();
        this.context = context;
        sonses=orders;
    }
    public  Home_gridAdapter(Context context) {
        super();
        this.context = context;
    }
    public void Additem(Product productModel){
        sonses.add(productModel);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final CustomHolder holder;
        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_griditem, parent, false);
            holder.roundedImageView=(RoundedImageView) convertView.findViewById(R.id.imageView);
            holder.businessLayout = (FrameLayout) convertView.findViewById(R.id.businessLayout);
            holder.txv_griditem_title = (TextView) convertView.findViewById(R.id.txv_griditem_title);
            holder.txv_griditem_price = (TextView) convertView.findViewById(R.id.txv_griditem_price);
            holder.txv_griditem_dateunit = (TextView) convertView.findViewById(R.id.txv_griditem_dateunit);
            holder.imv_rent_bookmark = (ImageView) convertView.findViewById(R.id.imv_rent_bookmark);
            holder.txv_no_deposit = (TextView)convertView.findViewById(R.id.txv_no_deposit);

            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final Product trip1 = (Product) sonses.get(position);
        int loadimage=-1;
        if(trip1.category == null)loadimage = R.mipmap.default_house_img;
        else if(trip1.category.equals("1"))loadimage = R.mipmap.default_house_img;
        else if(trip1.category.equals("2"))loadimage = R.mipmap.default_car_img;
        else if(trip1.category.equals("3"))loadimage = R.mipmap.default_car_img;
        else if(trip1.category.equals("4"))loadimage = R.mipmap.default_house_img;
        else if(trip1.category.equals("5"))loadimage = R.mipmap.default_dress_img;
        else if(trip1.category.equals("6"))loadimage = R.mipmap.default_bike_img;
        else loadimage = R.mipmap.default_camera_img;

        if (trip1.deposit != null && trip1.deposit.equals("Yes"))
            holder.txv_no_deposit.setVisibility(View.GONE);
        else
            holder.txv_no_deposit.setVisibility(View.GONE);
        holder.businessLayout.setVisibility(View.VISIBLE);
        if (trip1.membershipState == null || trip1.membershipState.length() == 0)
            holder.businessLayout.setVisibility(View.GONE);
        if(trip1.rental_state != null && trip1.rental_state.equals("Yes")){
            holder.imv_rent_bookmark.setVisibility(View.VISIBLE);
        }else{
            holder.imv_rent_bookmark.setVisibility(View.INVISIBLE);
        }

        Glide
                .with(context)
                .load(trip1.image_url.get(0))
                .centerCrop()
                .placeholder(loadimage)
                .into(holder.roundedImageView);

        holder.txv_griditem_title.setText(trip1.title);
        holder.txv_griditem_price.setText(trip1.price+"₺"+" "+"/"+" ");
        holder.txv_griditem_dateunit.setText(trip1.date_unit);

      //  if(trip1.rental_state.equals("Yes")) holder.imv_flag.setVisibility(View.VISIBLE);
       // else holder.imv_flag.setVisibility(View.INVISIBLE);

        View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.itemModel=trip1;
                try{
                    Constants.itemModel.price = String.valueOf(Double.valueOf(Constants.itemModel.price));
                }catch (Exception e){
                    Constants.itemModel.price = "0";
                }
                if (trip1.category == null)
                    return;

                Animation animZoomIn = AnimationUtils.loadAnimation(context,R.anim.scaledown);
                finalConvertView.startAnimation(animZoomIn);

                    /*   final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
                    view.startAnimation(buttonClick);*/
//                    final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
//                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 10);
//                    myAnim.setInterpolator(interpolator);
//                    finalConvertView.startAnimation(myAnim);
                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
                mRef.child(ReqConst.API_USER).child(trip1.owner_id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            User user = dataSnapshot.getValue(User.class);
                            if (user == null)
                                Constants.itemModel.owner_info = new User("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                            else
                                Constants.itemModel.owner_info = user;
                            productclick(trip1.category);
                        }catch (Exception e){
                            Log.e("e", String.valueOf(e));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(Common.user == null || Common.user.id == null || trip1 == null || trip1.owner_id == null)
                    return false;
                if (!(Common.user.id.equals(trip1.owner_id)))
                    return false;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Dialog");
                alertDialogBuilder
                        .setCancelable(false)
                        .setItems(R.array.arrChooseType, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                                if (which == 1){
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                    alertDialogBuilder.setTitle("Confirm");
                                    alertDialogBuilder
                                            .setMessage("Comfirm the delete ?")
                                            .setCancelable(false)
                                            .setPositiveButton(R.string.yes,new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    deleteData(trip1);
                                                }
                                            })
                                            .setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                }else{
                                    edtDataPage(trip1);
                                }
                            }
                        })
                        .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });

        return convertView;
    }

    public void productclick(String strCategory){
        Intent intent = null;
        switch (strCategory){
            case "1":
                intent = new Intent(context, DetailhomeActivity.class);
                break;
            case "2":
                intent = new Intent(context, DetailCarActivity.class);
                break;
            case "3":
                intent = new Intent(context, DetailcaravanActivity.class);
                break;
            case "4":
                intent = new Intent(context, DetailvehicleActivity.class);
                break;
            case "5":
                intent = new Intent(context, DetaildressActivity.class);
                break;
            case "6":
                intent = new Intent(context, DetailbikeActivity.class);
                break;
            case "7":
                intent = new Intent(context, DetailcameraActivity.class);
                break;
            case "8":
                intent = new Intent(context, DetailsporeActivity.class);
                break;
            case "9":
                intent = new Intent(context, DetailkampActivity.class);
                break;
            case "10":
                intent = new Intent(context, DetailmusicActivity.class);
                break;
        }
        context.startActivity(intent);
    }

    public void edtDataPage(Product product){
        String strCategory = product.category;
        Intent intent = null;
        switch (strCategory){
            case "1":
                intent = new Intent(context, UploadApartmentActivity.class);
                break;
            case "2":
                intent = new Intent(context, UploadRentalcarActivity.class);
                break;
            case "3":
                intent = new Intent(context, UploadCaravanActivity.class);
                break;
            case "4":
                intent = new Intent(context, UploadVehicleActivity.class);
                break;
            case "5":
                intent = new Intent(context, UploadDressActivity.class);
                break;
            case "6":
                intent = new Intent(context, UploadBikeActivity.class);
                break;
            case "7":
                intent = new Intent(context, UploadCameraActivity.class);
                break;
            case "8":
                intent = new Intent(context, UploadKampActivity.class);
                break;
            case "9":
                intent = new Intent(context, UploadMusicActivity.class);
                break;
            case "10":
                intent = new Intent(context, UploadOtherActivity.class);
                break;
            default:
                return;
        }
        intent.putExtra("productId", product.id);
        context.startActivity(intent);
    }
    public void deleteData(Product product){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);
        Query deleteQuery = mDatabase.orderByChild("id").equalTo(product.id);
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot delData: dataSnapshot.getChildren()){
                    delData.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public class CustomHolder {
        FrameLayout businessLayout;
        RoundedImageView roundedImageView;
        //TextView txvuniqueid, txvstatus,  txvorderdate, btn_confirm;
        TextView txv_griditem_title;
        TextView txv_griditem_price;
        TextView txv_griditem_dateunit;
        LinearLayout linearLayout;
        ImageView imv_rent_bookmark;
        TextView txv_no_deposit;

    }
    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 15;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }
}
