package com.tur.bumerang.Standard.Model;

public class RequestHistoryModel {

    String photo = "";
    String title = "";
    String price = "";
    String date_unit = "";
    String date = "";
    Boolean status = false;

    public RequestHistoryModel(String photo, String title, String price, String date_unit, String date, Boolean status) {
        this.photo = photo;
        this.title = title;
        this.price = price;
        this.date_unit = date_unit;
        this.date = date;
        this.status = status;
    }

    public RequestHistoryModel() {

    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate_unit() {
        return date_unit;
    }

    public void setDate_unit(String date_unit) {
        this.date_unit = date_unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
