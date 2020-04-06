package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class LoginAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + ReqConst.API_USER + "login";
    private Map<String,String> params;

    public LoginAPI(String email, String pwd, String auth_type,
                    Response.Listener<String> listener, Response.ErrorListener error) {
     super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put(ReqConst.EMAIL, email);
        params.put(ReqConst.PASSWORD, pwd);
        params.put(ReqConst.AUTH_TYPE, auth_type);

        Log.d("loginurl*********", url);
        Log.d("loginparam*********", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
