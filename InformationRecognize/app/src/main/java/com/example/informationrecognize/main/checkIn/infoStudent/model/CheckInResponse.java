package com.example.informationrecognize.main.checkIn.infoStudent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckInResponse {
    @SerializedName("errorcode")
    @Expose
    private String errorCode;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private DataCheckIn data;

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

    public DataCheckIn getData() {
        return data;
    }

    public void setData(DataCheckIn data) {
        this.data = data;
    }

    public class DataCheckIn {
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("idStudent")
        @Expose
        private String idStudent;

        public String getStatus() {
            return status == null ? "" : status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIdStudent() {
            return idStudent == null ? "" : idStudent;
        }

        public void setIdStudent(String idStudent) {
            this.idStudent = idStudent;
        }
    }
}
