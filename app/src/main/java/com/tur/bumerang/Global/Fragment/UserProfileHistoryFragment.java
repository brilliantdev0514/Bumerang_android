package com.tur.bumerang.Global.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.API.UserProductAPI;
import com.tur.bumerang.Global.Activity.UserProfileActivity;
import com.tur.bumerang.Global.Adapter.Profile_history_gridAdapter;
import com.tur.bumerang.Global.Model.ItemModel;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.R;
import com.tur.bumerang.Utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class UserProfileHistoryFragment extends Fragment {

    UserProfileActivity userProfileActivity;
    Profile_history_gridAdapter profile_history_gridAdapter;
    ArrayList<ItemModel> itemModels = new ArrayList<>();
    View view;
    LinearLayout lyt_no_product;

    GridView history_grid;

    String category, page_number;
    String user_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profile_history, container, false);
        lyt_no_product = view.findViewById(R.id.lyt_no_product);

        history_grid=(GridView)view.findViewById(R.id.history_grid);
        user_id = EasyPreference.with(userProfileActivity).getString("easyUserId", "");
        category = "0";
        page_number = "1";

        profile_history_gridAdapter= new Profile_history_gridAdapter(userProfileActivity, itemModels);
        history_grid.setAdapter(profile_history_gridAdapter);

//        loadLayout();

        getUserProducts(category);

        return view;
    }

//    private  void loadLayout(){
//
//        ArrayList<ItemModel> itemModels = new ArrayList<>();
//
//        for(int i=0; i<21; i++){
//            ItemModel itemModel = new ItemModel();
//            itemModel.setId(i);
//            int min = 0;
//            int max = 9;
//            int random = new Random().nextInt((max - min) + 1) + min;
//            itemModel.setImagetype(random);
//
//            itemModel.setId(i);
//            if(i%3 == 0) itemModel.setFlag(true);
//
//            itemModels.add(itemModel);
//
//        }
//
//
//       profile_history_gridAdapter= new Profile_history_gridAdapter(userProfileActivity, itemModels);
//       history_grid.setAdapter(profile_history_gridAdapter);
//
//    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userProfileActivity = (UserProfileActivity)context;
    }


    private void getUserProducts(String category) {

       /* final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_myproduct==", json);
                userProfileActivity.closeProgress();
                try{

                    JSONObject res = new JSONObject(json);

                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){

                        JSONArray req_array = res.getJSONArray("product_info");

                        for( int i=0 ; i<req_array.length(); i++){

                            JSONObject req = req_array.getJSONObject(i);

                            ItemModel one = new ItemModel(req);
                            itemModels.add(one);
                        }

                        profile_history_gridAdapter.loadData(itemModels);

                        LogUtil.e("aaaa===" + itemModels.size());

                        userProfileActivity.

                        if(profile_history_gridAdapter.getCount() == 0){
                            lyt_no_product.setVisibility(View.VISIBLE);
                        }else{
                            lyt_no_product.setVisibility(View.INVISIBLE);
                        }


                    }else {
                        Log.d("request_mag===>", ReqConst.MSG);
                        profile_history_gridAdapter.loadData(itemModels);

                        if(profile_history_gridAdapter.getCount() == 0){
                            lyt_no_product.setVisibility(View.VISIBLE);
                        }else{
                            lyt_no_product.setVisibility(View.INVISIBLE);
                        }
                    }

                }catch (JSONException e){
                    userProfileActivity.showAlertDialog(e.getMessage());
                }
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userProfileActivity.closeProgress();
                userProfileActivity.showAlertDialog(getString(R.string.serverFailed));
            }
        };

        userProfileActivity.showProgress();
        UserProductAPI req = new UserProductAPI(user_id, category, page_number, res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(userProfileActivity);
        request.add(req); */

       userProfileActivity.showProgress();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child(ReqConst.API_PRODUCT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfileActivity.closeProgress();
                itemModels = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Product mProduct = ds.getValue(Product.class);
                    if (mProduct.owner_id.equals(user_id) && mProduct.category.equals(category))
                    {
                        ItemModel one = new ItemModel(mProduct);
                        itemModels.add(one);

                    }
                }

                profile_history_gridAdapter.loadData(itemModels);

//                userProfileActivity.

                if(profile_history_gridAdapter.getCount() == 0){
                    lyt_no_product.setVisibility(View.VISIBLE);
                }else{
                    lyt_no_product.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
