package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "user/changePassword";
    private Map<String,String> params;

    public ChangePasswordAPI(String id, String pwd, Response.Listener<String> listener, Response.ErrorListener error) {




        super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put("id", id);
        params.put("pwd", pwd);

        Log.d("forgoturl===", url);
        Log.d("forgotparam==", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
