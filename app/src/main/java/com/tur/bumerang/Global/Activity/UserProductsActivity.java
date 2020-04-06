package com.tur.bumerang.Global.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.API.UserProductAPI;
import com.tur.bumerang.Global.Adapter.User_Product_listAdapter;
import com.tur.bumerang.Global.Model.UserProductModel;
import com.tur.bumerang.R;
import com.tur.bumerang.Utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProductsActivity extends BaseActivity {

    ListView request_list;
    
    ArrayList<UserProductModel> userProductModels = new ArrayList<>();
    User_Product_listAdapter user_product_listAdapter;
    LinearLayout lyt_no_product;
    ImageView imvMore;

    String category, page_number;
    String user_id;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_product);
        
        ButterKnife.bind(this);
        lyt_no_product = findViewById(R.id.lyt_no_product);


        user_id = EasyPreference.with(this).getString("easyUserId", "");
        category = "100";
        page_number = "1";
        
        getUserProducts(category);

        user_product_listAdapter= new User_Product_listAdapter(this);

        request_list=(ListView)findViewById(R.id.list_myproducts);
        request_list.setAdapter(user_product_listAdapter);

        imvMore = (ImageView)findViewById(R.id.imvMore);
        imvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(UserProductsActivity.this, imvMore);
                popup.getMenuInflater().inflate(R.menu.sort_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){

                            case R.id.menuall:
                                userProductModels.clear();
                                getUserProducts("100");
                                break;
                            case R.id.menuhouse:
                                userProductModels.clear();
                                getUserProducts("1");
                                break;
                            case R.id.menucar:
                                userProductModels.clear();
                                getUserProducts("2");
                                break;
                            case R.id.menucaravan:
                                userProductModels.clear();
                                getUserProducts("3");
                                break;
                            case R.id.menuvehicle:
                                userProductModels.clear();
                                getUserProducts("4");
                                break;
                            case R.id.menudress:
                                userProductModels.clear();
                                getUserProducts("5");
                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

    }

    private void getUserProducts(String category) {

        final Response.Listener<String> res = new Response.Listener<String>() {
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

                            UserProductModel one = new UserProductModel(req);
                            userProductModels.add(one);
                        }

                        user_product_listAdapter.loadData(userProductModels);

                        LogUtil.e("aaaa===" + userProductModels.size());



                        if(user_product_listAdapter.getCount() == 0){
                            lyt_no_product.setVisibility(View.VISIBLE);
                        }else{
                            lyt_no_product.setVisibility(View.INVISIBLE);
                        }


                    }else {
                        Log.d("request_mag===>", ReqConst.MSG);
                        user_product_listAdapter.loadData(userProductModels);

                        if(user_product_listAdapter.getCount() == 0){
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
        request.add(req);

    }


    @OnClick(R.id.imv_back)
    void goBack(){
        finish();
    }

}
