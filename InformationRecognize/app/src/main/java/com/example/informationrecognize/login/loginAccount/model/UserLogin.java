package com.example.informationrecognize.login.loginAccount.model;

import com.google.gson.annotations.SerializedName;

public class UserLogin {

    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("nameUser")
    private String nameUser;
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("phoneUser")
    private String phoneUser;
    @SerializedName("imageUser")
    private String imageUser;
    @SerializedName("email")
    private String emailUser;

    public String getEmailUser() {
        return emailUser == null ? "" : emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public UserLogin() {
    }

    public UserLogin(String userName, String password, String nameUser, String idUser, String phoneUser, String imageUser) {
        this.userName = userName;
        this.password = password;
        this.nameUser = nameUser;
        this.idUser = idUser;
        this.phoneUser = phoneUser;
        this.imageUser = imageUser;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUser() {
        return nameUser == null ? "" : nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getIdUser() {
        return idUser == null ? "" : idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPhoneUser() {
        return phoneUser == null ? "" : phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getImageUser() {
        return imageUser == null ? "" : imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }
}
