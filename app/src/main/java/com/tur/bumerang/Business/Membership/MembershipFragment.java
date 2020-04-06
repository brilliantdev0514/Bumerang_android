package com.tur.bumerang.Business.Membership;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tur.bumerang.Business.Activity.MembershipActivity;
import com.tur.bumerang.R;

public class MembershipFragment extends Fragment implements View.OnClickListener {
    MembershipActivity membershipActivity;
    View view;
    Button btn_upgrade;

    private String title;
    private int page;

    ImageView img_membertype;
    TextView txv_back;

    public static MembershipFragment newInstance(int page, String title) {
        MembershipFragment fragmentFirst = new MembershipFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_recycle_membership, container, false);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");


//        btn_upgrade.setOnClickListener(this);
//        txv_back=(TextView)view.findViewById(R.id.txv_back);
//        txv_back.setOnClickListener(this);


        img_membertype=(ImageView)view.findViewById(R.id.img_membertype);
        if(page==0) img_membertype.setImageResource(R.mipmap.ic_medal_copper);
        else if(page==1) img_membertype.setImageResource(R.mipmap.ic_medal_sliver);
        else if(page==2) img_membertype.setImageResource(R.mipmap.ic_medal_gold);


        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        membershipActivity = (MembershipActivity) context;
//    }


    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_next:
//                if(page<3)
//                    membershipActivity.viewpager.setCurrentItem(page+1, true);
//                else{
//                   gotoHomepage();
//                }
//                break;
//            case R.id.txv_skip:
//                gotoHomepage();
//                break;
//        }
    }

    void gotoHomepage(){
       // Toast.makeText(introduceActivity_1, "Next Page", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(membershipActivity, HomeActivity.class);
//        startActivity(intent);

    }
}
