package com.tur.bumerang.Global.Model;

public class ReviewModel {

    String photo = "";
    String title = "";
    double rating = 0.0;
    String review = "";
    String name = "";
    String date = "";

    public ReviewModel(String photo, String title, double rating, String review, String name, String date) {
        this.photo = photo;
        this.title = title;
        this.rating = rating;
        this.review = review;
        this.name = name;
        this.date = date;
    }

    public ReviewModel() {

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
}
