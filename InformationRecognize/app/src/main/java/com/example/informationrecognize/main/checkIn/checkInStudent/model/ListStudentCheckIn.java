package com.example.informationrecognize.main.checkIn.checkInStudent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListStudentCheckIn {
    @SerializedName("errorcode")
    @Expose
    private String errorCode;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("listStudent")
    @Expose
    private List<StudentModel> studentModels;

    public String getErrorCode() {
        return errorCode == null ? "" : errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StudentModel> getStudentModels() {
        return studentModels;
    }

    public void setStudentModels(List<StudentModel> studentModels) {
        this.studentModels = studentModels;
    }
}
