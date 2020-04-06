package com.tur.bumerang.Global.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tur.bumerang.Business.Activity.MembershipActivity;
import com.tur.bumerang.Global.Model.RegisterModel;
import com.tur.bumerang.R;

import java.util.List;

public class Membership_setmembertypeAdapter extends RecyclerView.Adapter<Membership_setmembertypeAdapter.ViewHolder> {
    private List<RegisterModel> data;
    public MembershipActivity context;

    public Membership_setmembertypeAdapter(List<RegisterModel> data, MembershipActivity mainActivity) {
        this.data = data;
        context = mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_recycle_membership, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageResource(data.get(position).getImagename());
        holder.txv_membertype.setText(data.get(position).getUserType());
        holder.txv_des.setText(data.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txv_membertype, txv_des;
        private LinearLayout lyt_parent;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_membertype);
            txv_membertype = (TextView) itemView.findViewById(R.id.txv_member_type);
            txv_des = (TextView) itemView.findViewById(R.id.txv_member_des);

        }
    }
}
