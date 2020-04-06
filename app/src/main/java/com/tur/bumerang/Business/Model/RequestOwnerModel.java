package com.tur.bumerang.Business.Model;

import com.tur.bumerang.Base.ReqConst;
import com.tur.bumerang.Global.Model.ProductModel;
import com.tur.bumerang.Global.Model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class RequestOwnerModel implements Serializable {

    public String users_message, owner_received_state, user_received_state, owner_acceted_state, owners_message, rental_price,
            fee, score, review, id, product_id, user_id, owner_id, start_date, end_date, request_date;


    public UserModel userModel= new UserModel();
    public ProductModel productModel = new ProductModel();

    public RequestOwnerModel() { }

    public RequestOwnerModel(JSONObject object) {
        try {

            users_message = object.getString("users_message");
            owner_received_state = object.getString("owner_received_state");
            user_received_state = object.getString("user_received_state");
            owner_acceted_state = object.getString("owner_acceted_state");
            owners_message = object.getString("owners_message");
            rental_price = object.getString("rental_price");
            fee = object.getString("fee");
            score = object.getString("score");
            review = object.getString("review");
            id = object.getString("id");
            product_id = object.getString("product_id");
            user_id = object.getString("user_id");
            owner_id = object.getString("owner_id");
            start_date = object.getString("start_date");
            end_date = object.getString("end_date");
            request_date = object.getString("request_date");


            productModel = new ProductModel(object.getJSONArray("product_info").getJSONObject(0));
            userModel= new UserModel(object.getJSONArray("user_info").getJSONObject(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
