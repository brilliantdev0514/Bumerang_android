package com.tur.bumerang.Global.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;


import android.provider.Settings;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;

import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.Position_Search_City;
import com.tur.bumerang.R;
import com.tur.bumerang.Standard.Activity.SignupStandardActivity;

import java.io.IOException;
import java.util.List;


public class MainFragment extends Fragment implements RadioButton.OnCheckedChangeListener, HomeFragment.OnHomeFragmentListener{
    HomeActivity homeActivity;
    View view;

    RadioButton radio_home,radio_car,radio_caravan, radio_vehicle,  radio_dress,  radio_bike, radio_camera, radio_spore, radio_kamp, radio_music, radio_more;
    ImageView imv_home,imv_car,imv_caravan, imv_vehicle,  imv_dress,  imv_bike, imv_camera, imv_spore, imv_kamp, imv_music, imv_more;
    FrameLayout frm_home,frm_car,frm_caravan, frm_vehicle,  frm_dress,  frm_bike, frm_camera, frm_spore, frm_kamp,frm_music, frm_more;
    SearchView serch_main, serch_main_city;

    LinearLayout lyt_container;
    LinearLayout LL_horizontal;
    LinearLayout search_city_part;
    LinearLayout ll_search;
    View viewBlackTransparent_search;
    ImageView position_city;
    TextView locationSearch;
    View viewLocation;
    ImageView removeLocation;
    OnMainFragmentChangeListener callback;
    public String findCity;

    public void setOnMainFragmentChangeListener(OnMainFragmentChangeListener callback) {
        this.callback = callback;
    }
    public interface OnMainFragmentChangeListener {
        public void hideShowNav(boolean bIsShow);
    }
    public void setSearchText(Address address){
        String strCountry = address.getCountryName();
        String strLocal = address.getLocality();
        if (strLocal == null)
            strLocal = address.getAdminArea();

        locationSearch.setText((strCountry == null ? "" : strCountry)+(strLocal == null ? "" : ", "+strLocal));
    }
    @Override
    public void hideShowNav(boolean bIsShow) {
        callback.hideShowNav(bIsShow);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_main, container, false);
        position_city = view.findViewById(R.id.position_city);
        serch_main=(SearchView) view.findViewById(R.id.serch_main);
        serch_main_city=(SearchView) view.findViewById(R.id.serch_main_city);
        viewBlackTransparent_search=(View) view.findViewById(R.id.viewBlackTransparent_search);
        viewBlackTransparent_search.setVisibility(View.GONE);
        lyt_container=(LinearLayout) view.findViewById(R.id.lyt_container);
        LL_horizontal=(LinearLayout) view.findViewById(R.id.LL_horizontal);
        ll_search = (LinearLayout)view.findViewById(R.id.ll_search);
        locationSearch = (TextView) view.findViewById(R.id.locationSearch);
        removeLocation = (ImageView) view.findViewById(R.id.removeLocation);

        search_city_part =(LinearLayout)view.findViewById(R.id.city_search_part) ;
        position_city = view.findViewById(R.id.position_city);
        ImageView imv_setting_profile = (ImageView)view.findViewById(R.id.imv_setting_profile);
        ImageView img_back = (ImageView)view.findViewById(R.id.img_back);
        TextView text_toolbar = (TextView)view.findViewById(R.id.text_toolbar);
        viewLocation = view.findViewById(R.id.lyLocation);

        loadlayout();
        check_non();

        serch_main.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                replaceTutorialFragment(12, "All");//search
                return false;
            }
        });
        serch_main.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeActivity, "Input Search Work", Toast.LENGTH_SHORT).show();
            }
        });
        serch_main.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("") || query.equals(null))
                    query = "All";
                replaceTutorialFragment(12, query);//search
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onQueryTextSubmit(newText);
                return false;
            }
        });

        viewBlackTransparent_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewBlackTransparent_search.setVisibility(View.GONE);
                lyt_container.setVisibility(View.VISIBLE);
                LL_horizontal.setVisibility(View.VISIBLE);
            }
        });


        position_city.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ////////////////here is city search function
                ll_search.setVisibility(View.GONE);
                lyt_container.setVisibility(View.GONE);
                LL_horizontal.setVisibility(View.GONE);
                search_city_part.setVisibility(View.VISIBLE);
                img_back.setVisibility(View.VISIBLE);
                imv_setting_profile.setVisibility(View.INVISIBLE);
                text_toolbar.setVisibility(View.VISIBLE);
                text_toolbar.setText(R.string.apartment);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_search.setVisibility(View.VISIBLE);
                lyt_container.setVisibility(View.VISIBLE);
                LL_horizontal.setVisibility(View.VISIBLE);
                search_city_part.setVisibility(View.GONE);
                img_back.setVisibility(View.GONE);
                imv_setting_profile.setVisibility(View.VISIBLE);
                text_toolbar.setVisibility(View.GONE);
                text_toolbar.setText(R.string.profile);
                CharSequence str_main = serch_main_city.getQuery();
                if (str_main != null || str_main.toString() != "" || str_main.toString() != null){
                    serch_main.setQuery(str_main, true);
                }
            }
        });

        findCity = getArguments().getString("findCity");
        removeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCity = null;
                viewLocation.setVisibility(View.GONE);
                replaceTutorialFragment(0,"All");
            }
        });
        if ( findCity != null){
            viewLocation.setVisibility(View.VISIBLE);
            locationSearch.setText(findCity);
            replaceTutorialFragment(13,findCity);//mapfilter
        }else{
            viewLocation.setVisibility(View.GONE);
            replaceTutorialFragment(0,"All");
        }
        return view;
    }


///////////////////




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeActivity = (HomeActivity)context;
    }

    public void replaceTutorialFragment(int i, String str) {
        HomeFragment fragment = HomeFragment.newInstance(i, str);
        fragment.setOnHomeFragmentListener(this);
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.fragment_animation_fade_in, R.animator.fragment_animation_fade_out)
                .replace(R.id.lyt_container, fragment)
                .commit();
    }


    void loadlayout(){
        radio_car= view.findViewById(R.id.radio_car);
        radio_car.setOnCheckedChangeListener(this);
        radio_dress= view.findViewById(R.id.radio_dress);
        radio_dress.setOnCheckedChangeListener(this);
        radio_bike= view.findViewById(R.id.radio_bike);
        radio_bike.setOnCheckedChangeListener(this);
        radio_home= view.findViewById(R.id.radio_home);
        radio_home.setOnCheckedChangeListener(this);
        radio_camera= view.findViewById(R.id.radio_camera);
        radio_camera.setOnCheckedChangeListener(this);
        radio_more= view.findViewById(R.id.radio_more);
        radio_more.setOnCheckedChangeListener(this);
        radio_caravan=view.findViewById(R.id.radio_caravan);
        radio_caravan.setOnCheckedChangeListener(this);
        radio_vehicle=view.findViewById(R.id.radio_vehicle);
        radio_vehicle.setOnCheckedChangeListener(this);
//        radio_spore=view.findViewById(R.id.radio_spore);
//        radio_spore.setOnCheckedChangeListener(this);
        radio_kamp=view.findViewById(R.id.radio_kamp);
        radio_kamp.setOnCheckedChangeListener(this);
        radio_music=view.findViewById(R.id.radio_music);
        radio_music.setOnCheckedChangeListener(this);


        imv_car= view.findViewById(R.id.imv_car);
        imv_dress= view.findViewById(R.id.imv_dress);
        imv_bike= view.findViewById(R.id.imv_bike);
        imv_home = view.findViewById(R.id.imv_home);
        imv_camera= view.findViewById(R.id.imv_camera);
        imv_more= view.findViewById(R.id.imv_more);
        imv_caravan= view.findViewById(R.id.imv_caravan);
        imv_vehicle= view.findViewById(R.id.imv_vehicle);
        imv_kamp= view.findViewById(R.id.imv_kamp);
//        imv_spore= view.findViewById(R.id.imv_spore);
        imv_music= view.findViewById(R.id.imv_music);


        frm_car= view.findViewById(R.id.frm_car);
        frm_dress= view.findViewById(R.id.frm_dress);
        frm_bike= view.findViewById(R.id.frm_bike);
        frm_home= view.findViewById(R.id.frm_home);
        frm_camera= view.findViewById(R.id.frm_camera);
        frm_more= view.findViewById(R.id.frm_more);
        frm_caravan= view.findViewById(R.id.frm_caravan);
        frm_vehicle= view.findViewById(R.id.frm_vehicle);
        frm_kamp= view.findViewById(R.id.frm_kamp);
//        frm_spore= view.findViewById(R.id.frm_spore);
        frm_music= view.findViewById(R.id.frm_music);


    }



    //========================== Check Radio Group Module =====================================
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.radio_home:
                if(b){
                     //check_home();
                    radio_home.setChecked(false);
                    replaceTutorialFragment(1,"home");
                }
                break;
            case R.id.radio_car:
                if(b){
                   // check_car();
                    radio_car.setChecked(false);
                    replaceTutorialFragment(2,"car");
                }
                break;
            case R.id.radio_caravan:
                if(b){
                  //  check_caravan();
                    radio_caravan.setChecked(false);
                    replaceTutorialFragment(3,"caravan");
                }
                break;
            case R.id.radio_vehicle:
                if(b){
                   // check_vehicle();
                    radio_vehicle.setChecked(false);
                    replaceTutorialFragment(4,"vehicle");
                }
                break;
            case R.id.radio_dress:
                if(b){
                   // check_dress();
                    radio_dress.setChecked(false);
                    replaceTutorialFragment(5,"dress");
                }
                break;
            case R.id.radio_bike:
                if(b){
                   // check_bike();
                    radio_bike.setChecked(false);
                    replaceTutorialFragment(6,"bike");
                }
                break;
            case R.id.radio_camera:
                if(b){
                    //check_camera();
                    radio_camera.setChecked(false);
                    replaceTutorialFragment(7,"camera");
                }
                break;
//            case R.id.radio_spore:
//                if(b){
//                    check_spore();
//                    replaceTutorialFragment(new HomeFragment().newInstance(8,"spore"));
//                }
//                break;
            case R.id.radio_kamp:
                if(b){
                  //  check_kamp();
                    radio_kamp.setChecked(false);
                    replaceTutorialFragment(9,"kamp");
                }
                break;
            case R.id.radio_music:
                if(b){
                    //check_music();
                    radio_music.setChecked(false);
                    replaceTutorialFragment(10,"music");
                }
                break;
            case R.id.radio_more:
                if(b){
                 //   check_more();
                    radio_more.setChecked(false);
                    replaceTutorialFragment(11,"other");
                }
                break;
        }
    }
    void check_non(){
        radio_car.setChecked(false);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);



        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);

        setDimension_unselect(frm_music);
    }

    void check_car(){
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);

    }
    void check_dress(){
        radio_car.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_bike(){
        radio_car.setChecked(false);
        radio_dress.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_home(){
        radio_home.setChecked(false);
        radio_car.setChecked(false);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_camera(){
        radio_car.setChecked(true);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_more(){
        radio_car.setChecked(false);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_caravan(){
        radio_car.setChecked(true);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_vehicle(){
        radio_car.setChecked(true);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_kamp(){
        radio_car.setChecked(true);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_music.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
//        setDimension_unselect(frm_spore);
        setDimension_unselect(frm_music);
    }
    void check_music(){
        radio_car.setChecked(false);
        radio_dress.setChecked(false);
        radio_bike.setChecked(false);
        radio_home.setChecked(false);
        radio_camera.setChecked(false);
        radio_caravan.setChecked(false);
        radio_vehicle.setChecked(false);
//        radio_spore.setChecked(false);
        radio_kamp.setChecked(false);
        radio_more.setChecked(false);


        setDimension_unselect(frm_car);
        setDimension_unselect(frm_bike);
        setDimension_unselect(frm_dress);
        setDimension_unselect(frm_home);
        setDimension_unselect(frm_camera);
        setDimension_unselect(frm_more);
        setDimension_unselect(frm_caravan);
        setDimension_unselect(frm_vehicle);
        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_spore);
    }
//    void check_spore(){
//        radio_car.setChecked(false);
//        radio_dress.setChecked(false);
//        radio_bike.setChecked(false);
//        radio_home.setChecked(false);
//        radio_camera.setChecked(false);
//        radio_caravan.setChecked(false);
//        radio_vehicle.setChecked(false);
//        radio_kamp.setChecked(false);
//        radio_music.setChecked(false);
//        radio_more.setChecked(false);
//
//
//        setDimension_unselect(frm_car);
//        setDimension_unselect(frm_bike);
//        setDimension_unselect(frm_dress);
//        setDimension_unselect(frm_home);
//        setDimension_unselect(frm_camera);
//        setDimension_unselect(frm_more);
//        setDimension_unselect(frm_caravan);
//        setDimension_unselect(frm_vehicle);
//        setDimension_unselect(frm_kamp);
//        setDimension_unselect(frm_music);
//    }


    public void setDimension_unselect(FrameLayout frm){

        Display display = homeActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int px= width/6;

        ViewGroup.LayoutParams layoutParams = frm.getLayoutParams();
        layoutParams.width = px;
        layoutParams.height = px;
        frm.setLayoutParams(layoutParams);
    }
    public void setDimension_select(FrameLayout frm){
        Display display = homeActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int px= width/7;


        ViewGroup.LayoutParams layoutParams = frm.getLayoutParams();
        //layoutParams.width = convertDpToPx(getResources().getDimension(R.dimen.oval_size1));
        layoutParams.width = px;
        layoutParams.height = px;
        frm.setLayoutParams(layoutParams);
    }


}
