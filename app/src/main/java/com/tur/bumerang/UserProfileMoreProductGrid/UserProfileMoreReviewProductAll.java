package com.tur.bumerang.UserProfileMoreProductGrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tur.bumerang.Global.Model.ReviewModel;
import com.tur.bumerang.R;

import java.util.ArrayList;

public class UserProfileMoreReviewProductAll extends AppCompatActivity {


    //UserProfileActivity userProfileActivity;
    Profile_review_listAdapter_morereview profile_review_listAdapter;
    View view;

    ListView review_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_more_review_product_all);

        review_list=(ListView)findViewById(R.id.list_view);
        loadLayout();

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


        profile_review_listAdapter= new Profile_review_listAdapter_morereview(UserProfileMoreReviewProductAll.this, reviewModels);

        review_list.setAdapter(profile_review_listAdapter);

    }
}
