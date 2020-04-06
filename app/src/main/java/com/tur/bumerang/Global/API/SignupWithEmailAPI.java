package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class SignupWithEmailAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + ReqConst.API_USER + "signup";
    private Map<String,String> params;

    public SignupWithEmailAPI(String first_name, String last_name, String email, String password, String user_type, String auth_type, String lat, String lng,
                              Response.Listener<String> listener, Response.ErrorListener error) {


        super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put(ReqConst.FIRST_NAME, first_name);
        params.put(ReqConst.LAST_NAME, last_name);
        params.put(ReqConst.EMAIL, email);
        params.put(ReqConst.PASSWORD, password);
        params.put(ReqConst.USER_TYPE, user_type);
        params.put(ReqConst.AUTH_TYPE, auth_type);
        params.put(ReqConst.LAT, lat);
        params.put(ReqConst.LNG, lng);

        Log.d("serverurl==", url);
        Log.d("parameter==", params.toString());

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
