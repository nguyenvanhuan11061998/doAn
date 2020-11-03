package com.example.informationrecognize.main.checkIn.mvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExamRoomModel {
    @SerializedName("errorcode")
    @Expose
    private String errorcode;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("listExamRoom")
    @Expose
    private List<ClassItemModel> listExamRoom;

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

    public List<ClassItemModel> getListExamRoom() {
        return listExamRoom;
    }

    public void setListExamRoom(List<ClassItemModel> listExamRoom) {
        this.listExamRoom = listExamRoom;
    }
}
