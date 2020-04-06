package com.tur.bumerang.Global.Model;

public class RegisterModel {

    int id;
    int imagename;
    String userType;
    String description;

    public RegisterModel(int id, int imagename, String userType, String description) {
        this.id = id;
        this.imagename = imagename;
        this.userType = userType;
        this.description = description;
}

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagename() {
        return imagename;
    }

    public void setImagename(int imagename) {
        this.imagename = imagename;
    }
}
