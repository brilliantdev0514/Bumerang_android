package com.tur.bumerang.Global.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tur.bumerang.R;

public class SliderImageFragment extends Fragment {
    Context mContext = null;
    View mView = null;
    String [] imgUrls = null;
    ImageView mImageView = null;
    TextView btn_left = null;
    TextView btn_right = null;
    int iImgIndex = 0;
    int iImgCount = 0;

    public SliderImageFragment(Context context, String [] urls) {
        this.mContext = context;
        this.imgUrls = urls;
        iImgCount = urls.length;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_slider_image, container, false);
        mImageView = (ImageView)mView.findViewById(R.id.imgSlider);
        btn_left = (TextView)mView.findViewById(R.id.btn_left);
        btn_right = (TextView)mView.findViewById(R.id.btn_right);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left();
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right();
            }
        });
        showSliderImage();
        return mView;
    }
    public void left(){
        iImgIndex--;
        showSliderImage();
    }
    public void right(){
        iImgIndex++;
        showSliderImage();
    }
    public void showSliderImage(){
        if (iImgIndex < 0)
            iImgIndex = iImgCount-1;
        if (iImgIndex > iImgCount-1)
            iImgIndex = 0;
        String strImgUrl = "";
        if (iImgCount > 0) {
            strImgUrl = imgUrls[iImgIndex];
        }
        if (iImgCount <= 1){
            btn_left.setVisibility(View.GONE);
            btn_right.setVisibility(View.GONE);
        }

        Glide
                .with(this)
                .load(strImgUrl)
                .centerCrop()
                .placeholder(R.mipmap.default_gallery)
                .into(mImageView);
    }
}
