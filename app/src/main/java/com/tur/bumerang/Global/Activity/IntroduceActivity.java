package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Global.Fragment.IntroduceFragment;
import com.tur.bumerang.R;
import com.tur.bumerang.Services.GpsService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class IntroduceActivity extends BaseActivity {

    @BindView(R.id.txvSkip)
    TextView txvSkip;

    public ViewPager viewpager;

    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intoduce_1);

        ButterKnife.bind(this);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        MyPagerAdapter myPagerAdapter= new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(myPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewpager);

        startService(new Intent(this, GpsService.class));
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return IntroduceFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return IntroduceFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return IntroduceFragment.newInstance(2, "Page # 3");
                case 3: // Fragment # 1 - This will show SecondFragment
                    return IntroduceFragment.newInstance(3, "Page # 3");
                default:
                    return null;
            }
        }
        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }

    @OnClick(R.id.txvSkip) void gotoHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
