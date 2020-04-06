package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class RentRequestAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "rentalProduct/requestRental";
    private Map<String,String> params;

    public RentRequestAPI(int user_id, String product_id, int owner_id, String start_date, String end_date, String users_message,
                          Response.Listener<String> listener, Response.ErrorListener error) {

        super(Method.POST, url, listener,error);
        params = new HashMap<>();

        params.put("user_id", String.valueOf(user_id));
        params.put("product_id", product_id);
        params.put("owner_id", String.valueOf(owner_id));
        params.put("start_date", start_date);
        params.put("end_date", end_date);
        params.put("users_message", users_message);
        //params.put("service_fee", service_fee);


        Log.d("rentinurl===", url);
        Log.d("rentparam==", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
