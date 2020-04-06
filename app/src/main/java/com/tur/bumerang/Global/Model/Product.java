package com.tur.bumerang.Global.Model;

import com.tur.bumerang.Base.ReqConst;

import java.util.List;

public class Product {

    public String   title, membershipState,  heating, furbished, fuel_type, gear_type, car_type,
            captan, rental_state, gender, size, color,  date_unit, deposit, description, address, lat, lng, zip_code,  score, updated_at, created_at;

    public String room_number, door_number, bed_capacity, person_capacity, price, owner_id;
    public String category;
    public String  id;
    public User owner_info= new User();
    public List<String> image_url;
    public Product() { }

    public Product(String owner_id, String category,String title, String room_number, String heating, String furbished,
                   String fuel_type, String gear_type, String door_number, String car_type, String bed_capacity,
                   String person_capacity, String captan, String gender, String size, String color, String price,
                   String date_unit, String deposit, String description, List<String> image_url, String address,
                   String lat, String lng, String zip_code, String rental_state, String score, String id, String updated_at,
                   String created_at, User owner_info, String membershipState) {
        this.owner_id = owner_id;
        this.category= category;
        this.title = title;
        this.room_number = room_number;
        this.heating = heating;
        this.furbished = furbished;
        this.fuel_type = fuel_type;
        this.gear_type = gear_type;
        this.door_number = door_number;
        this.car_type = car_type;
        this.bed_capacity = bed_capacity;
        this.person_capacity = person_capacity;
        this.captan = captan;
        this.gender = gender;
        this.size = size;
        this.color = color;
        try {
            this.price = price;
        }catch(Exception e){
            this.price = "0";
        }
        this.date_unit = date_unit;
        this.deposit = deposit;
        this.description = description;
        this.image_url = image_url;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.zip_code = zip_code;
        this.rental_state = rental_state;
        this.score = score;
        this.id = id;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.membershipState = membershipState;
        //service_fee = object.getString("service_fee");

        if(owner_info != null){
            this.owner_info = owner_info;
        }
    }
}
