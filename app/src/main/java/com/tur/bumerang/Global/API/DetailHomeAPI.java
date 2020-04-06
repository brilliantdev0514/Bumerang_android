package com.tur.bumerang.Global.API;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class DetailHomeAPI extends StringRequest {

    private static  final String url = ReqConst.SERVER_URL + "flat/getFlatDetails";
    private Map<String,String> params;

    public DetailHomeAPI(String flat_id,
                         Response.Listener<String> listener, Response.ErrorListener error) {


        super(Method.POST, url, listener,error);
        params = new HashMap<>();
        params.put("flat_id", flat_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
