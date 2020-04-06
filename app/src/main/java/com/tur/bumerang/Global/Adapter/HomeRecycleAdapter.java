package com.tur.bumerang.Global.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.tur.bumerang.Business.Activity.AdsSplashActivity;
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Model.RecycleModel;
import com.tur.bumerang.R;

import java.util.List;

public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.ViewHolder> {
    private List<RecycleModel> data;
    public HomeActivity context;

    public HomeRecycleAdapter(List<RecycleModel> data, HomeActivity mainActivity) {
        this.data = data;
        context = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_recycle, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       /* holder.colorfulRingProgressView.setPercent(data.get(position).getProgress());
        if (data.get(position).getAllwedbookstatus() == true) {
            holder.imageView.setImageResource(data.get(position).getImageuri());
            holder.imvrock.setVisibility(View.INVISIBLE);
        } else {
            holder.imageView.setImageResource(data.get(position).getImageuri());
            holder.imvrock.setVisibility(View.VISIBLE);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        if(position == 4){
         //   holder.imageView.setImageResource(R.mipmap.ic_membership_car);
            holder.imageView.setImageResource(R.mipmap.default_car_img);
            holder.ads_title.setVisibility(View.INVISIBLE);
            //holder.ads_title.setText("You can upload ads here!");
        }else{
            holder.imageView.setImageResource(R.mipmap.default_car_img);
            holder.ads_title.setVisibility(View.VISIBLE);
        }


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(context, AdsSplashActivity.class);
                    context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button ads_title;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imv_ads_product);
            ads_title = (Button) itemView.findViewById(R.id.btn_ads_title);

        }
    }
}
