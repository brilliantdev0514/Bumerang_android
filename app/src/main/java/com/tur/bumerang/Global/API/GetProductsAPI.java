package com.tur.bumerang.Global.API;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.tur.bumerang.Base.ReqConst;

import java.util.HashMap;
import java.util.Map;

public class GetProductsAPI extends StringRequest {

    private static  final String url =  ReqConst.SERVER_URL + "product/getCatProducts";
    private Map<String,String> params;

    public GetProductsAPI(String page_number, String category, String door_number, String room_number, String heating, String gender,
                          String size, String color, String furbished, String fuel_type, String gear_type, String deposit, String car_type, String bed_capacity,
                          String person_capacity, String captan, String price, String date_unit,
                          Response.Listener<String> listener, Response.ErrorListener error) {


        super(Method.POST, url, listener,error);

        params = new HashMap<>();

        params.put("page_number", page_number);
        params.put("door_number", door_number);
        params.put("category", category);
        params.put("room_number", room_number);
        params.put("heating", heating);
        params.put("gender", gender);
        params.put("size", size);
        params.put("color", color);
        params.put("furbished", furbished);
        params.put("fuel_type", fuel_type);
        params.put("gear_type", gear_type);
        params.put("car_type", car_type);
        params.put("bed_capacity", bed_capacity);
        params.put("person_capacity", person_capacity);
        params.put("captan", captan);
        params.put("price", price);
        params.put("date_unit", date_unit);
        params.put("deposit", deposit);

        Log.d("getproduct_url==", url);
        Log.d("product_api", params.toString());

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
