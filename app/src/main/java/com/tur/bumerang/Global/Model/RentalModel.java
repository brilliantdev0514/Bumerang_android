package com.tur.bumerang.Global.Model;

public class RentalModel {

    public String user_id, product_id, owner_id, start_date, end_date, users_message;

    public RentalModel()
    {

    }

    public RentalModel(String user_id, String product_id, String owner_id, String start_date, String end_date, String users_message)
    {
        this.user_id = user_id;
        this.product_id = product_id;
        this.owner_id = owner_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.users_message = users_message;
    }

//    String changedateformat_toserver(String date){   //15/10/2019
//        String changeddate="";
//        if(date.length()>0){
//            String[] date_value = date.split("/");
//            String year = date_value[2];
//            String month = date_value[1];
//            String day= date_value[0];
//            changeddate = year+"-"+month+"-"+day;
//        }
//        return changeddate;
//    }


}


