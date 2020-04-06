package com.tur.bumerang.Global.Model;

import com.tur.bumerang.Base.ReqConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserProductModel implements Serializable {

    public String   title,  heating, furbished, fuel_type, gear_type, car_type,
             captan, rental_state, gender, size, color,  date_unit, deposit, description, image, address, lat, lng, zip_code,  score, updated_at, created_at;

    public int room_number=0, door_number, bed_capacity, person_capacity, price, owner_id;
    public int category=-1;
    public int  id;
    public UserModel ownermodel= new UserModel();
    public UserProductModel() { }

    public UserProductModel(JSONObject object) {
        try {

            owner_id = object.getInt("owner_id");
            String  categoryvalue = object.getString("category");
            if(categoryvalue.length()==0) category=-1;
            else category= Integer.parseInt(categoryvalue);
            // category = Integer.parseInt(object.getString("category"));
            title = object.getString("title");
            String roomnumber = object.getString("room_number");
            if(!roomnumber.equals("null"))  room_number = object.getInt("room_number");
            heating = object.getString("heating");
            furbished = object.getString("furbished");
            fuel_type = object.getString(ReqConst.FUEL_TYPE);
            gear_type = object.getString(ReqConst.GEAR_TYPE);
            String doornumber = object.getString("door_number");
            if(!doornumber.equals("null")) door_number = object.getInt(ReqConst.DOOR_NUMBER);
            car_type = object.getString("car_type");
            String bedcapacity = object.getString("bed_capacity");
            if(!bedcapacity.equals("null"))  bed_capacity = object.getInt("bed_capacity");
            String personcapacity = object.getString("person_capacity");
            if(!personcapacity.equals("null"))  person_capacity = object.getInt("person_capacity");
            captan = object.getString("captan");
            gender = object.getString("gender");
            size = object.getString("size");
            color = object.getString("color");
            String price1 = object.getString("price");
            if(!price1.equals("null"))  price = object.getInt("price");
            date_unit = object.getString("date_unit");
            deposit = object.getString("deposit");
            description = object.getString(ReqConst.DESCTIPTION);
            image = object.getString("image_url");
            address = object.getString(ReqConst.ADDRESS);
            lat = object.getString(ReqConst.LAT);
            lng = object.getString(ReqConst.LNG);
            zip_code = object.getString("zip_code");
            rental_state = object.getString("rental_status");
            score = object.getString("score");
            id = object.getInt("id");
            updated_at = object.getString("updated_at");
            created_at = object.getString("created_at");
            //service_fee = object.getString("service_fee");

            if(object.has("owner_info")){
                JSONArray ownerinfoarray = object.getJSONArray("owner_info");
                if(ownerinfoarray.length()>0){
                    ownermodel= new UserModel(object.getJSONArray("owner_info").getJSONObject(0));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
