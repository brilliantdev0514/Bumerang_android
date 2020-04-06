package com.tur.bumerang.Global.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tur.bumerang.Global.Model.Product;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.API.GetProductsAPI;
import com.tur.bumerang.Global.Activity.HomeActivity;
import com.tur.bumerang.Global.Activity.Position_Search_City;
import com.tur.bumerang.Global.Adapter.Home_gridAdapter;
import com.tur.bumerang.Global.Adapter.HomeRecycleAdapter;
import com.tur.bumerang.Global.CustomeView.HeaderGridView;
import com.tur.bumerang.Global.Model.ProductModel;
import com.tur.bumerang.Global.Model.RecycleModel;
import com.tur.bumerang.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    HomeActivity homeActivity;
    Context context;
    View view;
    private String title;
    private int filter_type;
    static ArrayList<Boolean> isBusiness;

//    private XRefreshView xRefreshView;
    DiscreteScrollView itemPicker;

    Home_gridAdapter home_gridAdapter;
    HeaderGridView headerGridView;
    HorizontalScrollView scrollView_filter;

    String  room_number="", heating="", gender="", size="", color="", furbished="", fuel_type="", gear_type = "", deposit = "",door_number = "",
    car_type="", bed_capacity="", person_capacity="", captan="", price="", date_unit="", price_plate = "";

    int page_number=1;

    View filterView=null;
    LinearLayout linearLayout;
    LinearLayout LL_trns;

    Spinner spinner_roomnumber_apart,spinner_heating_apart,spinner_furbished_apart,spinner_price_apart,spinner_deposit_apart,
            spinner_fueltype_rental,spinner_geartype_rental,spinner_doornumber_rental, spinner_cartype_rental ,spinner_price_rental,spinner_deposit_rental,
            spinner_bedcapacity_caravan,spinner_fueltype_caravan,spinner_price_caravan,spinner_deposit_caravan,
            spinner_personcapacity_vehicle, spinner_captan_vehicle,spinner_price_vehicle,
            spinner_gender_dress,spinner_size_dress,spinner_color_dress,spinner_price_dress;

    ArrayList<Product> productModels = new ArrayList<>();
    ArrayList<Product> filteredModels = new ArrayList<>();
    String[] filterOptions = {"All", "All", "All", "All", "All", "All", "All", "All", "All", "All", "All", "All", "All" ,"All","All","All", "All", "All", "All"};

    public static HomeFragment instance = null;
    public static HomeFragment newInstance(int page, String title) {
        String [] filter = null;
        ArrayList<Product> tmpProduct = null;
        if (instance != null) {
            filter = instance.filterOptions;
            tmpProduct = instance.productModels;
        }
        instance = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        instance.setArguments(args);
        if (filter != null)
            instance.filterOptions = filter;
        if (tmpProduct != null)
            instance.productModels = tmpProduct;
        return instance;
    }


    OnHomeFragmentListener callback;

    public void setOnHomeFragmentListener(OnHomeFragmentListener callback) {
        this.callback = callback;
    }
    public interface OnHomeFragmentListener {
        public void hideShowNav(boolean bIsShow);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        filter_type = getArguments().getInt("someInt", 0);  // 0: all, 1: home, 2...
        title = getArguments().getString("someTitle");
        linearLayout = view.findViewById(R.id.lyt_no_product);


        headerGridView=(HeaderGridView)view.findViewById(R.id.headergrid);
        scrollView_filter = view.findViewById(R.id.scrollView_filter);
//
//

        if (filter_type == 0)
            for (int i = 0; i < filterOptions.length; i++)
                filterOptions[i] = "All";

//        if(filter_type!=0)  loadAds();
        loadFilter(filter_type);

        if(filter_type==1){
            spinner_heating_apart=(Spinner)filterView.findViewById(R.id.spinner_heating_apart);
            spinner_heating_apart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[1] = getResources().getStringArray(R.array.spinner_heating)[position];
                    if (position == 0)
                        filterOptions[1] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_furbished_apart=(Spinner)filterView.findViewById(R.id.spinner_furbished_apart);
            spinner_furbished_apart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[2] = getResources().getStringArray(R.array.spinner_furbished)[position];
                    //filterOptions[2] = furbished;
                    if (position == 0)
                        filterOptions[2] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_deposit_apart=(Spinner)filterView.findViewById(R.id.spinner_deposit_apart);
            spinner_deposit_apart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[5] = getResources().getStringArray(R.array.spinner_deposit)[position];
                    if (position == 0)
                        filterOptions[5] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_roomnumber_apart=(Spinner)filterView.findViewById(R.id.spinner_roomnumber_apart);
            spinner_roomnumber_apart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[0] =  getResources().getStringArray(R.array.spinner_roomnumber)[position];
                    if (position == 0)
                        filterOptions[0] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_price_apart=(Spinner)filterView.findViewById(R.id.spinner_price_apart);
            spinner_price_apart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[3] = getResources().getStringArray(R.array.spinner_price_flat)[position];
                    if (position == 0)
                        filterOptions[3] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

           /* heating = spinner_heating.getSelectedItem().toString();
            if (heating.equals("All")){
                heating = "";
            }

            furbished = spinner_furbished.getSelectedItem().toString();
            if (furbished.equals("All")){
                furbished = "";
            }
            deposit = spinner_deposit.getSelectedItem().toString();
            if (deposit.equals("All")){
                deposit = "";
            }
            price_plate = spinner_price_flat.getSelectedItem().toString();
            if (price_plate.equals("All")){
                price_plate = "";
            }
            room_number = spinner_roomnumber.getSelectedItem().toString();
            if (room_number.equals("All")){
                room_number = "";
            }*/
        }
        if(filter_type == 2){
            spinner_fueltype_rental=(Spinner)filterView.findViewById(R.id.spinner_fueltype_rental);
            spinner_fueltype_rental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[6] = getResources().getStringArray(R.array.spinner_fueltype)[position];
                    if (position == 0)
                        filterOptions[6] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_geartype_rental=(Spinner)filterView.findViewById(R.id.spinner_geartype_rental);
            spinner_geartype_rental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[7] = getResources().getStringArray(R.array.spinner_gear)[position];
                    if (position == 0)
                        filterOptions[7] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_doornumber_rental=(Spinner)filterView.findViewById(R.id.spinner_doornumber_rental);
            spinner_doornumber_rental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[8] = getResources().getStringArray(R.array.spinner_doornumber)[position];
                    if (position == 0)
                        filterOptions[8] = "All";
                    getallproductdata(true);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_cartype_rental=(Spinner)filterView.findViewById(R.id.spinner_cartype_rental);
            spinner_cartype_rental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[9] = getResources().getStringArray(R.array.spinner_cartype)[position];
                    if (position == 0)
                        filterOptions[9] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_price_rental=(Spinner)filterView.findViewById(R.id.spinner_price_rental);
            spinner_price_rental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[4] = getResources().getStringArray(R.array.spinner_price)[position];
                    if (position == 0)
                        filterOptions[4] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_deposit_rental=(Spinner)filterView.findViewById(R.id.spinner_deposit_rental);
            spinner_deposit_rental.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[5] = getResources().getStringArray(R.array.spinner_deposit)[position];
                    if (position == 0)
                        filterOptions[5] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

//            gear_type = spinner_geartype_rental.getSelectedItem().toString();
//            if (gear_type.equals("All")){
//                gear_type = "";
//            }
//
//            fuel_type = spinner_fueltype_rental.getSelectedItem().toString();
//            if (fuel_type.equals("All")){
//                fuel_type = "";
//            }
//
//            door_number = spinner_doornumber_rental.getSelectedItem().toString();
//            if (door_number.equals("All")){
//                door_number = "";
//            }
//
//            car_type = spinner_cartype_rental.getSelectedItem().toString();
//            if (car_type.equals("All")){
//                car_type = "";
//            }
//
//            price = spinner_price_rental.getSelectedItem().toString();
//            if (price.equals("All")){
//                price = "";
//            }
//
//            deposit = spinner_deposit_rental.getSelectedItem().toString();
//            if (deposit.equals("All")){
//                deposit = "";
//            }

        }
        if(filter_type == 3){

            spinner_bedcapacity_caravan=(Spinner)filterView.findViewById(R.id.spinner_bedcapacity_caravan);
            spinner_bedcapacity_caravan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     filterOptions[10] = getResources().getStringArray(R.array.spinner_bedcap)[position];
                    if (position == 0)
                        filterOptions[10] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            spinner_fueltype_caravan=(Spinner)filterView.findViewById(R.id.spinner_fueltype_caravan);
            spinner_fueltype_caravan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[6] = getResources().getStringArray(R.array.spinner_fueltype)[position];
                    if (position == 0)
                        filterOptions[6] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });



            spinner_price_caravan=(Spinner)filterView.findViewById(R.id.spinner_price_caravan);
            spinner_price_caravan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[4] = getResources().getStringArray(R.array.spinner_price)[position];
                    if (position == 0)
                        filterOptions[4] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            spinner_deposit_caravan=(Spinner)filterView.findViewById(R.id.spinner_deposit_caravan);
            spinner_deposit_caravan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[5] = getResources().getStringArray(R.array.spinner_deposit)[position];
                    if (position == 0)
                        filterOptions[5] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });



//            bed_capacity = spinner_bedcapacity_caravan.getSelectedItem().toString();
//            if (bed_capacity.equals("All")){
//                bed_capacity = "";
//            }
//
//            fuel_type = spinner_fueltype_caravan.getSelectedItem().toString();
//            if (fuel_type.equals("All")){
//                fuel_type = "";
//            }
//            price = spinner_price_caravan.getSelectedItem().toString();
//            if (price.equals("All")){
//                price = "";
//            }
//
//            deposit = spinner_deposit_caravan.getSelectedItem().toString();
//            if (deposit.equals("All")){
//                deposit = "";
//            }
        }
        if(filter_type==4){
            spinner_personcapacity_vehicle=(Spinner)filterView.findViewById(R.id.spinner_personcapacity_vehicle);
            spinner_personcapacity_vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[11] = getResources().getStringArray(R.array.spinner_person)[position];
                    //filterOptions[2] = furbished;
                    if (position == 0)
                        filterOptions[11] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_captan_vehicle=(Spinner)filterView.findViewById(R.id.spinner_captan_vehicle);
            spinner_captan_vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[12] = getResources().getStringArray(R.array.spinner_captan)[position];
                    if (position == 0)
                        filterOptions[12] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


            spinner_price_vehicle=(Spinner)filterView.findViewById(R.id.spinner_price_vehicle);
            spinner_price_vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[4] =  getResources().getStringArray(R.array.spinner_price)[position];
                    if (position == 0)
                        filterOptions[4] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
           /* heating = spinner_heating.getSelectedItem().toString();
            if (heating.equals("All")){
                heating = "";
            }

            furbished = spinner_furbished.getSelectedItem().toString();
            if (furbished.equals("All")){
                furbished = "";
            }
            deposit = spinner_deposit.getSelectedItem().toString();
            if (deposit.equals("All")){
                deposit = "";
            }
            price_plate = spinner_price_flat.getSelectedItem().toString();
            if (price_plate.equals("All")){
                price_plate = "";
            }
            room_number = spinner_roomnumber.getSelectedItem().toString();
            if (room_number.equals("All")){
                room_number = "";
            }*/
        }
        if(filter_type==5){
            spinner_gender_dress=(Spinner)filterView.findViewById(R.id.spinner_gender_dress);
            spinner_gender_dress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[13] = getResources().getStringArray(R.array.spinner_gender)[position];
                    if (position == 0)
                        filterOptions[13] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_size_dress=(Spinner)filterView.findViewById(R.id.spinner_size_dress);
            spinner_size_dress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[14] = getResources().getStringArray(R.array.spinner_size)[position];
                    //filterOptions[2] = furbished;
                    if (position == 0)
                        filterOptions[14] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            spinner_color_dress=(Spinner)filterView.findViewById(R.id.spinner_color_dress);
            spinner_color_dress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[15] = getResources().getStringArray(R.array.spinner_color)[position];
                    if (position == 0)
                        filterOptions[15] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });



            spinner_price_dress=(Spinner)filterView.findViewById(R.id.spinner_price_dress);
            spinner_price_dress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    filterOptions[4] = getResources().getStringArray(R.array.spinner_price)[position];
                    if (position == 0)
                        filterOptions[4] = "All";
                    getallproductdata(true);
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

           /* heating = spinner_heating.getSelectedItem().toString();
            if (heating.equals("All")){
                heating = "";
            }

            furbished = spinner_furbished.getSelectedItem().toString();
            if (furbished.equals("All")){
                furbished = "";
            }
            deposit = spinner_deposit.getSelectedItem().toString();
            if (deposit.equals("All")){
                deposit = "";
            }
            price_plate = spinner_price_flat.getSelectedItem().toString();
            if (price_plate.equals("All")){
                price_plate = "";
            }
            room_number = spinner_roomnumber.getSelectedItem().toString();
            if (room_number.equals("All")){
                room_number = "";
            }*/
        }
        if (filter_type == 12){
            filterOptions[16] = title;
            getallproductdata(true);
        }
        if (filter_type == 13){
            filterOptions[18] = title;
            getallproductdata(true);
        }

//        xRefreshView.setPinnedTime(1000);
//        xRefreshView.setAutoLoadMore(false);
//		xRefreshView.setCustomHeaderView(new SmileyHeaderView(homeActivity));
//        xRefreshView.setCustomFooterView(new CustomFooterView(homeActivity));
//
//        xRefreshView.setMoveForHorizontal(true);

//        home_gridAdapter = new Home_gridAdapter(homeActivity);
//        headerGridView.setAdapter(home_gridAdapter);

//        xRefreshView.startRefresh();
        if (filter_type > 0 && filter_type < 12){
            filterOptions[17] = String.valueOf(filter_type);
            getallproductdata(true);
        }else if(filter_type != 12 && filter_type != 13)
            getallproductdata(false);  // pull down

//        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
//            @Override
//            public void onRefresh(boolean isPullDown) {
//                if(isPullDown){
//                    page_number=1;
//                    getallproductdata(true);  // pull down
//                }
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//                page_number++;
//                getallproductdata(false);
//            }
//        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //xRefreshView.startRefresh();
    }


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
        homeActivity = (HomeActivity)context;
    }

    void loadAds(){
        View headerView = homeActivity.getLayoutInflater().inflate(R.layout.home_ads, null);
        itemPicker = (DiscreteScrollView) headerView.findViewById(R.id.item_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

            }
        });
        ArrayList<RecycleModel> data = new ArrayList<>();
        for(int i=0; i<12; i++)
            data.add(new RecycleModel());
        itemPicker.setAdapter(new HomeRecycleAdapter(data, homeActivity));
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.95f)
                .build());
        headerGridView.addHeaderView(headerView);
    }


    void loadFilter(int filter_type){

//        View filterView_1 = homeActivity.getLayoutInflater().inflate(R.layout.home_filter_1, null);

        if (filter_type == 1) {
            filterView = homeActivity.getLayoutInflater().inflate(R.layout.home_filter_1, null);
            headerGridView.addHeaderView(filterView);
        }else if (filter_type == 2){
            filterView = homeActivity.getLayoutInflater().inflate(R.layout.home_filter_2, null);
            headerGridView.addHeaderView(filterView);
        }else if (filter_type == 3){
            filterView = homeActivity.getLayoutInflater().inflate(R.layout.home_filter_3, null);
            headerGridView.addHeaderView(filterView);
        }else if (filter_type == 4){
            filterView = homeActivity.getLayoutInflater().inflate(R.layout.home_filter_4, null);
            headerGridView.addHeaderView(filterView);
        }else if (filter_type == 5){
            filterView = homeActivity.getLayoutInflater().inflate(R.layout.home_filter_5, null);
            headerGridView.addHeaderView(filterView);
        }

    }

    void getallproductdata(boolean nIsFilter){
        Log.d("getallproduct==","start");
        /*final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_getProduct==", json);
                if(swipe_direction==true){
                    xRefreshView.stopRefresh();
                    productModels= new ArrayList<>();
                }else{
                    xRefreshView.stopLoadMore();
                }
                try{
                    JSONObject res = new JSONObject(json);

                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){

                        JSONArray product_array = res.getJSONArray("all_products");
                        for (int i=0; i<product_array.length(); i++){

                            int num = product_array.length();

                            JSONObject one = product_array.getJSONObject(i);
                            ProductModel oneproduct = new ProductModel(one);
                            productModels.add(oneproduct);
                            if(page_number!=1){
                                home_gridAdapter.Additem(oneproduct);
                            }
                        }

                        if(page_number==1){
                            home_gridAdapter = new Home_gridAdapter(homeActivity, productModels);
                            headerGridView.setAdapter(home_gridAdapter);
                        }


                    }else {
                        //homeActivity.showAlertDialog(res.getString(ReqConst.MSG));
                        homeActivity.showToast(res.getString(ReqConst.MSG));
                    }

                    if(home_gridAdapter.getCount() == 0){
                        linearLayout.setVisibility(View.VISIBLE);
                    }else{
                        linearLayout.setVisibility(View.INVISIBLE);
                    }

                }catch (JSONException e){
                    homeActivity.showAlertDialog(e.getMessage());
                }
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error==*********8", error.getMessage());

                homeActivity.showAlertDialog(getString(R.string.serverFailed));
            }
        };

        GetProductsAPI req = new GetProductsAPI(String.valueOf(page_number), String.valueOf(filter_type), door_number, room_number, heating, gender, size, color, furbished, fuel_type, gear_type,
                deposit, car_type, bed_capacity, person_capacity, captan, price, date_unit, res, error);
//        Log.d("response from server", String.valueOf(error));
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(homeActivity);
        request.add(req);*/
        if(nIsFilter == true){
            onFilterProduct(filterOptions);
            return;
        }
        homeActivity.showProgress();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(ReqConst.API_PRODUCT);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productModels= new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    Product mProduct = snapshot.getValue(Product.class);
                    productModels.add(mProduct);
                }

                homeActivity.closeProgress();
                setListView(productModels);
                home_gridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

    public Integer str2int(String str){
        Integer iResult = 0;
        try{
            iResult = Double.valueOf(str).intValue();
        }catch (Exception e){
            iResult = 0;
        }
        return  iResult;
    }
    public boolean onMapSearch(String location, LatLng latLng) {
        List<Address> addressList = null;
        if (location != null && !location.equals("")) {
            String[] locations = location.split(", ");
            Geocoder geocoder = new Geocoder(context);
            try {
                geocoder.getFromLocation(latLng.latitude, latLng.longitude, 10);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addressList == null || addressList.size() <= 0) {
                return false;
            }

            for (int i = 0; i < addressList.size(); i++) {
                for (int j= 0; j < locations.length; j++) {
                    if (addressList.get(i).getCountryName().equals(locations[i]) || addressList.get(i).getFeatureName().equals(locations[i]) ||
                            addressList.get(i).getAdminArea().equals(locations[i]) || addressList.get(i).getLocality().equals(locations[i]))
                        return true;
                }
            }
        }
        return false;
    }

    void onFilterProduct(String[] filterOptions){
        filteredModels = new ArrayList<Product>();

        if(productModels.size()==0) return;
        for(int j=0; j < productModels.size();j++){
            Product productModel = productModels.get(j);
            boolean match_status = false;


            if ((!filterOptions[17].equals("All") && filterOptions[17].equals(String.valueOf(productModel.category))) || filterOptions[17].equals("All"))
                if ((!filterOptions[0].equals("All") && filterOptions[0].equals(String.valueOf(productModel.room_number))) || filterOptions[0].equals("All"))
                    if ((!filterOptions[1].equals("All") && filterOptions[1].equals(productModel.heating)) || filterOptions[1].equals("All"))
                        if ((!filterOptions[2].equals("All") && filterOptions[2].equals(productModel.furbished)) || filterOptions[2].equals("All"))
                            if ((!filterOptions[3].equals("All") && filterOptions[3].equals(productModel.price)) || filterOptions[3].equals("All"))
                                if ((!filterOptions[4].equals("All") && filterOptions[4].equals(productModel.price)) || filterOptions[4].equals("All"))
                                    if ((!filterOptions[5].equals("All") && filterOptions[5].equals(productModel.deposit)) || filterOptions[5].equals("All"))
                                        if ((!filterOptions[6].equals("All") && filterOptions[6].equals(productModel.fuel_type)) || filterOptions[6].equals("All"))
                                            if ((!filterOptions[7].equals("All") && filterOptions[7].equals(productModel.gear_type)) || filterOptions[7].equals("All"))
                                                if ((!filterOptions[8].equals("All") && filterOptions[8].equals(productModel.door_number)) || filterOptions[8].equals("All"))
                                                    if ((!filterOptions[9].equals("All") && filterOptions[9].equals(productModel.car_type)) || filterOptions[9].equals("All"))
                                                        if ((!filterOptions[10].equals("All") && filterOptions[10].equals(productModel.bed_capacity)) || filterOptions[10].equals("All"))
                                                            if ((!filterOptions[11].equals("All") && filterOptions[11].equals(productModel.person_capacity)) || filterOptions[11].equals("All"))
                                                                if ((!filterOptions[12].equals("All") && filterOptions[12].equals(productModel.captan)) || filterOptions[12].equals("All"))
                                                                    if ((!filterOptions[13].equals("All") && filterOptions[13].equals(productModel.gender)) || filterOptions[13].equals("All"))
                                                                        if ((!filterOptions[14].equals("All") && filterOptions[14].equals(productModel.size)) || filterOptions[14].equals("All"))
                                                                            if ((!filterOptions[15].equals("All") && filterOptions[15].equals(productModel.color)) || filterOptions[15].equals("All"))
                                                                                if (!filterOptions[16].equals("All") && (
                                                                                        String.valueOf(productModel.title).toLowerCase().contains(filterOptions[16].toLowerCase()) ||
                                                                                        String.valueOf(productModel.address).toLowerCase().contains(filterOptions[16].toLowerCase()) ||
                                                                                        String.valueOf(productModel.price).toLowerCase().contains(filterOptions[16].toLowerCase())) ||
                                                                                        filterOptions[16].equals("All"))
                                                                                    if (filterOptions[18].equals("All") || (!filterOptions[18].equals("All") && productModel.address != null && productModel.address.toLowerCase().contains(filterOptions[18].toLowerCase())))
                                                                                        match_status = true;

            if(match_status==true) filteredModels.add(productModel);
        }

        setListView(filteredModels);
    }
    public void setListView(ArrayList<Product> productModels){
        home_gridAdapter = new Home_gridAdapter(homeActivity, productModels);
        headerGridView.setAdapter(home_gridAdapter);
        if(home_gridAdapter.getCount() == 0){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.INVISIBLE);
            headerGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                private int mLastFirstVisibleItem;
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }
                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (mLastFirstVisibleItem > firstVisibleItem)
                        callback.hideShowNav(false);
                    else if(mLastFirstVisibleItem < firstVisibleItem)
                        callback.hideShowNav(true);
                    mLastFirstVisibleItem = firstVisibleItem;
                }
            });
        }
    }
}
