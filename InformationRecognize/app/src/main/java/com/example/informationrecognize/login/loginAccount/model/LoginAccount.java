package com.example.informationrecognize.login.loginAccount.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginAccount {
    @SerializedName("errorcode")
    @Expose
    private String errorcode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("account")
    @Expose
    private UserLogin account;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserLogin getAccount() {
        return account;
    }

    public void setAccount(UserLogin account) {
        this.account = account;
    }
}
