package com.tur.bumerang.Global.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tur.bumerang.Global.Activity.UserProfileActivity;
import com.tur.bumerang.Global.Adapter.Profile_review_listAdapter;
import com.tur.bumerang.Global.Model.ReviewModel;
import com.tur.bumerang.R;

import java.util.ArrayList;


public class UserProfileReviewFragment extends Fragment {

    UserProfileActivity userProfileActivity;
    Profile_review_listAdapter profile_review_listAdapter;
    View view;

    ListView review_list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profile_review, container, false);
        review_list=(ListView)view.findViewById(R.id.list_view);
        loadLayout();

        return view;
    }

    private  void loadLayout(){

        ArrayList<ReviewModel> reviewModels = new ArrayList<>();

        for(int i=0; i<21; i++){
            ReviewModel reviewModel = new ReviewModel();
//            reviewModel.setId(i);
//            int min = 0;
//            int max = 9;
//            int random = new Random().nextInt((max - min) + 1) + min;
//            reviewModel.setImagetype(random);
//
//            reviewModel.setId(i);
//            if(i%3 == 0) reviewModel.setFlag(true);

            reviewModels.add(reviewModel);

        }


        profile_review_listAdapter= new Profile_review_listAdapter(userProfileActivity, reviewModels);
        review_list.setAdapter(profile_review_listAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userProfileActivity = (UserProfileActivity)context;
    }

}

