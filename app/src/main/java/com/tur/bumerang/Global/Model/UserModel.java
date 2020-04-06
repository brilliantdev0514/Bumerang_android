package com.tur.bumerang.Global.Model;

import com.tur.bumerang.Base.ReqConst;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserModel implements Serializable {
    public int id;
    public String firstName, lastName, email, phone, avatarUrl, googleEmail, facebookEmail, score, address, lat, lng, emailVerified, phoneVerified
            , idCardVerified, userType, membership, createdAt, updatedAt, city, password;

    public UserModel() { }

    public UserModel(JSONObject object) {
        try {
            id= object.getInt("id");
            firstName = object.getString(ReqConst.FIRST_NAME);
            lastName = object.getString(ReqConst.LAST_NAME);
            email = object.getString(ReqConst.EMAIL);
            phone = object.getString(ReqConst.PHONE);
            avatarUrl = object.getString(ReqConst.AVATAR_URL);
            googleEmail = object.getString(ReqConst.GOOGLE_MAIL);
            facebookEmail = object.getString(ReqConst.FACEBOOK_EMAIL);
            password = object.getString(ReqConst.PASSWORD);
            score = object.getString(ReqConst.SCORE);
            address = object.getString(ReqConst.ADDRESS);
            lat = object.getString(ReqConst.LAT);
            lng = object.getString(ReqConst.LNG);
            emailVerified = object.getString(ReqConst.EMAIL_VERIFIED);
            phoneVerified = object.getString(ReqConst.PHONE_VERIFIED);
            idCardVerified = object.getString(ReqConst.IDCARD_VERIFIED);
            userType = object.getString(ReqConst.USER_TYPE);
            membership = object.getString(ReqConst.MEMBERSHIP);
            createdAt = object.getString(ReqConst.CREATED_AT);
            updatedAt = object.getString(ReqConst.UPDATED_AT);
            city = object.getString(ReqConst.CITY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
