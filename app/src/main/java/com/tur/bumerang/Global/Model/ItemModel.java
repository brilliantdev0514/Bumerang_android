package com.tur.bumerang.Global.Model;

import com.tur.bumerang.Base.ReqConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class ItemModel{
    String id;
    int imagetype=-1;  // 0: car,  1: dress, 3: bike, 4: home, 5: camera
    boolean flag= false;
    String date ="";
    //private String description = "";
    private String rentstate = "";
    private String productname = "";
    private String cost;
    private String total_price;
    private String imageurl;


    public String   title,  heating, furbished, fuel_type, gear_type, car_type,
            captan, rental_state, gender, size, color,  date_unit, deposit, description, address, lat, lng, zip_code,  score, updated_at, created_at;

    public String room_number, door_number, bed_capacity, person_capacity, price, owner_id;
    public String category;
    public User owner_info= new User();

    public List<String> image_url;

    public ItemModel(String id, int imagetype) {
        this.id = id;
        this.imagetype = imagetype;
    }

    public ItemModel() {

    }

    public ItemModel(String id, int imagetype, boolean flag, String date, String description, String rentstate, String productname, String cost, String total_price, String imageurl) {
        this.id = id;
        this.imagetype = imagetype;
        this.flag = flag;
        this.date = date;
        this.description = description;
        this.rentstate = rentstate;
        this.productname = productname;
        this.cost = cost;
        this.total_price = total_price;
        this.imageurl = imageurl;
    }

    public ItemModel(Product mProduct) {
        this.owner_id = mProduct.owner_id;
        this.category=mProduct.category;
        // category = Integer.parseInt(object.getString("category"));
        this.title = mProduct.title;
        this.room_number = mProduct.room_number;
        this.heating = mProduct.heating;
        this.furbished = mProduct.furbished;
        this.fuel_type = mProduct.fuel_type;
        this.gear_type = mProduct.gear_type;
        this.door_number = mProduct.door_number;
        this.car_type = mProduct.car_type;
        this.bed_capacity = mProduct.bed_capacity;
        this.person_capacity = mProduct.person_capacity;
        this.captan = mProduct.captan;
        this.gender = mProduct.gender;
        this.size = mProduct.size;
        this.color = mProduct.color;
        this.price = mProduct.price;
        this.date_unit = mProduct.date_unit;
        this.deposit = mProduct.deposit;
        this.description = mProduct.description;
        this.image_url = mProduct.image_url;
        this.address = mProduct.address;
        this.lat = mProduct.lat;
        this.lng = mProduct.lng;
        this.zip_code = mProduct.zip_code;
        this.rental_state = mProduct.rental_state;
        this.score = mProduct.score;
        this.id = mProduct.id;
        this.updated_at = mProduct.updated_at;
        this.created_at = mProduct.created_at;
        //service_fee = object.getString("service_fee");

        this.owner_info = mProduct.owner_info;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRentstate() {
        return rentstate;
    }

    public void setRentstate(String rentstate) {
        this.rentstate = rentstate;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImagetype() {
        return imagetype;
    }

    public void setImagetype(int imagetype) {
        this.imagetype = imagetype;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
