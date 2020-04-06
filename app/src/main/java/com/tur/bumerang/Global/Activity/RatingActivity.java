package com.tur.bumerang.Global.Activity;

import android.os.Bundle;
import android.widget.RatingBar;

import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.R;

import butterknife.ButterKnife;

public class RatingActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ButterKnife.bind(this);

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setRating(0);
//        ratingBar.setSpacing(10);
        ratingBar.setIsIndicator(false);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(0.5F);
//        ratingBar.setProgress(null);
 //       ratingBar.setProgressed(null);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

    }

}
