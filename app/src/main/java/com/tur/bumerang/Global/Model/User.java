package com.tur.bumerang.Global.Model;

import com.tur.bumerang.Base.ReqConst;

public class User {

    public String id;
    public String firstName, lastName, email, phone, avatarUrl, googleEmail, facebookEmail, score, address, lat, lng, emailVerified, phoneVerified
            , idCardVerified, userType, membership, createdAt, updatedAt, city, password;

    public User() { }

    public User(String id, String fName, String lName, String email, String phone, String avatarUrl,
                String gmail, String facebookEmail, String pwd, String score, String address, String lat, String lng,
                String emailVerified, String phoneVerified, String idCardVerified, String userType, String membership,
            String createdAt, String updatedAt, String city) {
        this.id= id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.googleEmail = gmail;
        this.facebookEmail = facebookEmail;
        this.password = pwd;
        this.score = score;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.idCardVerified = idCardVerified;
        this.userType = userType;
        this.membership = membership;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGoogleEmail() {
        return googleEmail;
    }

    public void setGoogleEmail(String googleEmail) {
        this.googleEmail = googleEmail;
    }

    public String getFacebookEmail() {
        return facebookEmail;
    }

    public void setFacebookEmail(String facebookEmail) {
        this.facebookEmail = facebookEmail;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(String phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getIdCardVerified() {
        return idCardVerified;
    }

    public void setIdCardVerified(String idCardVerified) {
        this.idCardVerified = idCardVerified;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
