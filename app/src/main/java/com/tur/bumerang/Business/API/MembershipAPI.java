package com.tur.bumerang.Business.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class MembershipAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "user/updateMembership";
    private Map<String,String> params;

    public MembershipAPI(int user_id, int membership,
                         Response.Listener<String> listener, Response.ErrorListener error) {




        super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put("user_id", String.valueOf(user_id));
        params.put("membership", String.valueOf(membership));

        Log.d("membershipUrl===", url);
        Log.d("membershipParam==", params.toString());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
