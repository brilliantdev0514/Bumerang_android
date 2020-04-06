package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "user/forgotPassword";
    private Map<String,String> params;

    public ForgotPasswordAPI(String email_forgot, Response.Listener<String> listener, Response.ErrorListener error) {




        super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put("email", email_forgot);

        Log.d("forgoturl===", url);
        Log.d("forgotparam==", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
