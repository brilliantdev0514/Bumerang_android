package com.tur.bumerang.Global.Model;

public class ChattingModel {

    String image = "";
    String name = "";
    String photo = "";
    String sender_id = "";
    String time = "";
    String message = "";

    public ChattingModel(String image, String name, String photo, String sender_id, String date, String text) {
        this.image = image;
        this.message = text;
        this.name = name;
        this.time = date;
        this.photo = photo;
        this.sender_id =  sender_id;
    }

    public ChattingModel() {

    }

    public String getPhoto() {
        return image;
    }

    public void setPhoto(String photo) {
        this.image = photo;
    }

    public String getText() {
        return message;
    }

    public void setText(String text) {
        this.message = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return time;
    }

    public void setDate(String date) {
        this.time = date;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String senderId) {
        this.sender_id = senderId;
    }
    public String getimg() {
        return photo;
    }

    public void setimg(String senderId) {
        this.photo = senderId;
    }
}
