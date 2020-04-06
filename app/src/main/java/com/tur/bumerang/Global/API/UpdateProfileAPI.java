package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfileAPI extends StringRequest {
    private static  final String url =  ReqConst.SERVER_URL + ReqConst.API_USER + "/updateUserInfo";
    private Map<String,String> params;

    public UpdateProfileAPI(String name, String email, String phonenumber,String address,String pwd,
                    Response.Listener<String> listener, Response.ErrorListener error) {
        super(Method.POST, url, listener, error);
        params = new HashMap<>();
        params.put(ReqConst.NAME, name);
        params.put(ReqConst.EMAIL, email);
        params.put(ReqConst.PHONE, phonenumber);
        params.put(ReqConst.ADDRESS, address);
        params.put(ReqConst.PASSWORD, pwd);

        Log.d("updateurl*********", url);
        Log.d("updateparam*********", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
