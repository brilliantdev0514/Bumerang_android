package com.tur.bumerang.Business.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class BusinessRequestHistoryAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "rentalProduct/getOwnerHistory";
    private Map<String,String> params;

    public BusinessRequestHistoryAPI(String owner_id, String page_number,
                                     Response.Listener<String> listener, Response.ErrorListener error) {




        super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put("owner_id", owner_id);
        params.put("page_number", page_number);

        Log.d("userReqHistoryUrl===", url);
        Log.d("userReqHistoryParam==", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
