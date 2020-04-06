package com.tur.bumerang.UserProfileMoreProductGrid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

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
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.API.UserProductAPI;
import com.tur.bumerang.Global.Model.ItemModel;
import com.tur.bumerang.Global.Model.Product;
import com.tur.bumerang.R;
import com.tur.bumerang.Utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserProfileMoreProductAll extends BaseActivity {

    Profile_history_gridAdapter_UsermoreProduct profile_history_gridAdapter;
    ArrayList<ItemModel> itemModels = new ArrayList<>();
    View view;
    LinearLayout lyt_no_product;

    GridView history_grid;

    String category, page_number;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_more_product_all);

        lyt_no_product = findViewById(R.id.lyt_no_product);

        history_grid=(GridView) findViewById(R.id.history_grid);
        user_id = EasyPreference.with(this).getString("easyUserId", "");
        category = "0";
        page_number = "1";

        profile_history_gridAdapter= new Profile_history_gridAdapter_UsermoreProduct(this, itemModels);
        history_grid.setAdapter(profile_history_gridAdapter);

//        loadLayout();

        getUserProducts(category);

    }

    private void getUserProducts(String category) {

        /*final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_myproduct==", json);
                      closeProgress();
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
                    showAlertDialog(e.getMessage());
                }
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               closeProgress();
               showAlertDialog(getString(R.string.serverFailed));
            }
        };

       showProgress();
        UserProductAPI req = new UserProductAPI(user_id, category, page_number, res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);*/

        showProgress();
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child(ReqConst.API_PRODUCT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                closeProgress();
                itemModels = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Product mProduct = ds.getValue(Product.class);
                    if (String.valueOf(mProduct.owner_id).equals(String.valueOf(user_id))) {
                        ItemModel one = new ItemModel(mProduct);
                        itemModels.add(one);

                    }
                }

                profile_history_gridAdapter.loadData(itemModels);



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
