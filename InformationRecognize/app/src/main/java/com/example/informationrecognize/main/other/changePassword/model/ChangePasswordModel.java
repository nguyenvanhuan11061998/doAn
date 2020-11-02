package com.example.informationrecognize.main.other.changePassword.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordModel {
    @SerializedName("errorcode")
    @Expose
    private String errorcode;
    @SerializedName("message")
    @Expose
    private String message;

    public String getErrorcode() {
        return errorcode == null ? "" : errorcode;
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
}
