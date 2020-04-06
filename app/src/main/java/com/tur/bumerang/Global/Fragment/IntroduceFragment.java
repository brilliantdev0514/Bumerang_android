package com.tur.bumerang.Global.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.IntroduceActivity;
import com.tur.bumerang.R;

public class IntroduceFragment extends Fragment implements View.OnClickListener {
    IntroduceActivity introduceActivity_1;
    View view;
    Button btn_next;
    TextView subject, description;

    private String title;
    private int page;

    ImageView imv_introimage;

    public static IntroduceFragment newInstance(int page, String title) {
        IntroduceFragment fragmentFirst = new IntroduceFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_introduce, container, false);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        btn_next=(Button)view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        imv_introimage=(ImageView)view.findViewById(R.id.imv_introimage);
        subject = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);

        if(page==0)
        {
            imv_introimage.setImageResource(R.drawable.splash_new_1);
            subject.setText("Kiralamanın en iyi yolu");
            description.setText("Bumerang’la Türkiye’nin her yerinden kiralık ürün ve hizmet sağlayıcılarına bir tıkla ulaşmanın kolaylığını yaşa!");
        }
        else if(page==1)
        {
            imv_introimage.setImageResource(R.drawable.splash_new_2);
            subject.setText("Kiralayarak kazan");
            description.setText("İster bireysel olarak kiralayın, ister kurumsal olarak. Online mağazanızı açmak hiç bu kadar kolay olmamıştı. Bumerang herkese açık!");
        }
        else if(page==2)
        {
            imv_introimage.setImageResource(R.drawable.splash_new_3);
            subject.setText("Satın alma, kirala");
            description.setText("Satın almak geçmişte kaldı. Şimdi ihtiyacınız olan her şeye kısa veya uzun dönem kiralayarak ulaşın. Tonla para vermekten kurtulun!");
        }
        else if(page==3)
        {
            imv_introimage.setImageResource(R.drawable.splash_new_4);
            subject.setText("Güvenli mobil platform");
            description.setText("Doğrulamalı üyelik sayesinde güvenli bir platformda bulunmanın rahatlığını yaşayın!");
        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        introduceActivity_1 = (IntroduceActivity)context;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
                if(page<3) {
                    introduceActivity_1.viewpager.setCurrentItem(page + 1, true);
                }else{

                    Intent intent = new Intent(introduceActivity_1, HomeActivity.class);
                    startActivity(intent);
                }
    //            break;
        }
    }
}
