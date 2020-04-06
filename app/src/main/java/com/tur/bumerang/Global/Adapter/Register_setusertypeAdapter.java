package com.tur.bumerang.Global.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tur.bumerang.Global.Activity.RegisterActivity;
import com.tur.bumerang.Global.Model.RegisterModel;
import com.tur.bumerang.R;

import java.util.List;

public class Register_setusertypeAdapter extends RecyclerView.Adapter<Register_setusertypeAdapter.ViewHolder> {
    private List<RegisterModel> data;
    public RegisterActivity context;

    public Register_setusertypeAdapter(List<RegisterModel> data, RegisterActivity mainActivity) {
        this.data = data;
        context = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_recycle_register, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageResource(data.get(position).getImagename());
        holder.txv_usertype.setText(data.get(position).getUserType());
        holder.txv_des.setText(data.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txv_usertype, txv_des;
        private LinearLayout lyt_parent;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_usertype);
            txv_usertype = (TextView) itemView.findViewById(R.id.txv_user_type);
            txv_des = (TextView) itemView.findViewById(R.id.txv_user_des);

        }
    }
}
