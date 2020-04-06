package com.tur.bumerang.Global.Model;

public class ChattingHistoryModel {

    String photo = "";
    String text = "";
    String name = "";
    String date = "";
    String number = "";

    public ChattingHistoryModel(String photo, String text, String name, String date, String number) {
        this.photo = photo;
        this.text = text;
        this.name = name;
        this.date = date;
        this.number = number;
    }

    public ChattingHistoryModel() {

    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
