package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class UserProductAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "product/getSelfProducts";
    private Map<String,String> params;

    public UserProductAPI(String user_id, String category, String page_number,
                          Response.Listener<String> listener, Response.ErrorListener error) {

        super(Method.POST, url, listener,error);

        params = new HashMap<>();

        params.put("user_id", user_id);
        params.put("category", category);
        params.put("page_number", page_number);

        Log.d("userReqHistoryUrl===", url);
        Log.d("userReqHistoryParam==", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
