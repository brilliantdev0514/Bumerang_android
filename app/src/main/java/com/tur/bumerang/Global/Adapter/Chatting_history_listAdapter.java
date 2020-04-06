package com.tur.bumerang.Global.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tur.bumerang.Base.Common;
import com.tur.bumerang.Base.Constants;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.Activity.ChattingActivity;
import com.tur.bumerang.Global.Activity.ChattingHistoryActivity;
import com.tur.bumerang.Global.Model.ChattingModel;
import com.tur.bumerang.Global.Model.User;
import com.tur.bumerang.R;
import java.util.ArrayList;

public class Chatting_history_listAdapter extends BaseAdapter {
    private ChattingHistoryActivity activity;
    private ArrayList<ChattingModel> sonses = new ArrayList<>();

    public Chatting_history_listAdapter(ChattingHistoryActivity context, ArrayList<ChattingModel> orders) {
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
        try {
            return Long.valueOf(sonses.get(position).getSender_id());
        }catch (Exception e){
            return 0;
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CustomHolder holder;
        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chatting_history, parent, false);
            holder.roundedImageView=(RoundedImageView) convertView.findViewById(R.id.imv_photo);
            holder.txvuniqueid=(TextView)convertView.findViewById(R.id.txv_uniqueid);
            holder.txvorderdate=(TextView)convertView.findViewById(R.id.txv_orderdate);
            holder.txvstatus=(TextView)convertView.findViewById(R.id.txvstatus);
            holder.txvMsg=(TextView)convertView.findViewById(R.id.txvMsg);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }

        final ChattingModel trip1 = (ChattingModel) sonses.get(position);
        Glide
                .with(activity)
                .load(trip1.getPhoto())
                .centerCrop()
                .placeholder(R.mipmap.ic_user_photo)
                .into(holder.roundedImageView);
        holder.txvuniqueid.setText(trip1.getName());
        holder.txvorderdate.setText(trip1.getDate());
        holder.txvMsg.setText(trip1.getText());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
                mRef.child(ReqConst.API_USER).child(trip1.getSender_id()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            User user = dataSnapshot.getValue(User.class);
                            if (user != null) {
                                Common.Other_user = user;
                                Intent intent = new Intent(activity, ChattingActivity.class);

                                activity.startActivity(intent);
                            }
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
        return convertView;
    }


    public class CustomHolder {
        RoundedImageView roundedImageView;
        TextView txvuniqueid,  txvorderdate, txvstatus, txvMsg;
    }


}
