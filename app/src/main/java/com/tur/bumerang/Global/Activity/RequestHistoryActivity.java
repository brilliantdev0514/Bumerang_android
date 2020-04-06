package com.tur.bumerang.Global.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.iamhabib.easy_preference.EasyPreference;
import com.tur.bumerang.Base.BaseActivity;
import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Business.API.BusinessRequestHistoryAPI;
import com.tur.bumerang.Business.Model.RequestOwnerModel;
import com.tur.bumerang.Global.Fragment.MainFragment;
import com.tur.bumerang.R;
import com.tur.bumerang.Global.Adapter.Request_history_listAdapter;
import com.tur.bumerang.Utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestHistoryActivity extends BaseActivity {

   // Request_history_listAdapter request_history_listAdapter;

    ListView request_list;
    LinearLayout lyt_no_request;

    String user_type, page_number;
    String owner_id;
//    TextView txvbadge;

    ArrayList<RequestOwnerModel> requestOwnerModels = new ArrayList<>();
    Request_history_listAdapter requestHistoryListAdapter;

     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);

        ButterKnife.bind(this);
//         txvbadge = (TextView)findViewById(R.id.txv_messagebadge);

         lyt_no_request = findViewById(R.id.lyt_no_request);

        owner_id = EasyPreference.with(this).getString("easyUserId", "");
        page_number = "1";

        user_type = EasyPreference.with(this).getString("easyUserType","");

        Log.d("owner_id ====>", owner_id);
        Log.d("userType ===>", user_type);

         requestHistoryListAdapter= new Request_history_listAdapter(this);

        if(user_type.equals("Business")){
            getUserRequestHistory();
        }else if(user_type.equals("Standard")){

        }

        request_list=(ListView)findViewById(R.id.list_request_history);

        /*request_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Put in your code here, what you wanted trigger :)

                showToast("Request history item clicked!");

                Intent intent = new Intent(RequestHistoryActivity.this, ReciveRequestActivity.class);
                startActivity(intent);
            }
        });*/
        request_list.setAdapter(requestHistoryListAdapter);

    }




    void getUserRequestHistory(){

        final Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String json) {
                Log.d("response_reqhis==", json);
                closeProgress();
                try{

                    JSONObject res = new JSONObject(json);

                    if (res.getString(ReqConst.MSG).equals(ReqConst.SUCCESS)){

                        JSONArray req_array = res.getJSONArray("request_history");

                        for( int i=0 ; i<req_array.length(); i++){

                            JSONObject req = req_array.getJSONObject(i);

                            RequestOwnerModel one = new RequestOwnerModel(req);
                            requestOwnerModels.add(one);
                        }

                        requestHistoryListAdapter.loadData(requestOwnerModels);

                        LogUtil.e("aaaa===" + requestOwnerModels.size());

                        showToast("Getting request history success!");

                        if(requestHistoryListAdapter.getCount() == 0){
                            lyt_no_request.setVisibility(View.VISIBLE);
                        }else{
                            lyt_no_request.setVisibility(View.INVISIBLE);
                        }


                    }else {
                        Log.d("req_msg ====>", ReqConst.MSG);
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
        BusinessRequestHistoryAPI req = new BusinessRequestHistoryAPI(owner_id,page_number, res, error);
        req.setRetryPolicy(new DefaultRetryPolicy(ReqConst.TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(req);

    }

    @OnClick(R.id.imv_chart)
    void goMainHome()
    {
        Intent intent =  new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.imv_chat)
    void goChat()
    {
        super.removebadget();
        Intent intent =  new Intent(this,ChattingHistoryActivity.class );
        startActivity(intent);
        finish();
    }

//    @OnClick(R.id.imv_box)
//    void goBox(){
//        Intent intent =  new Intent(this, RequestHistoryActivity.class );
//        startActivity(intent);
//        finish();
//    }

    @OnClick(R.id.imv_userinfo)
    void goUserProfile(){

        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.imv_plus)
    void goSelectCategory(){
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
        finish();
    }
//
//    public void updatebagecount(int badgecount){
//        super.updatebagecount(badgecount);
//        if(badgecount>0){
//            txvbadge.setText(String.valueOf(badgecount));
//            txvbadge.setVisibility(View.VISIBLE);
//        }
//        else
//            txvbadge.setVisibility(View.INVISIBLE);
//    }
}
