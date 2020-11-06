package com.example.informationrecognize.main.checkIn.mvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClassItemModel implements Serializable {
    // tên lớp/ phòng thi
    @SerializedName("idRoom")
    @Expose
    private String idExamRoom;
    // tên lớp/ phòng thi
    @SerializedName("nameClass")
    @Expose
    private String nameClass;
    // môn thi
    @SerializedName("subject")
    @Expose
    private String subject;
    // khóa học
    @SerializedName("course")
    @Expose
    private String course;
    // ngành học
    @SerializedName("branch")
    @Expose
    private String branch;
    // sĩ số sinh viên
    @SerializedName("numberStudent")
    @Expose
    private String numberStudent;
    // trạng thái
    @SerializedName("status")
    @Expose
    private String status;
    // thời gian thi.
    @SerializedName("timeExam")
    @Expose
    private String timeExam;
    // thời gian bắt đầu
    @SerializedName("startTime")
    @Expose
    private String startTime;
    // mã giám thị coi thi 1:
    @SerializedName("idFirstSupervisor")
    @Expose
    private String idFirstSupervisor;
    // mã giám thị coi thi 2
    @SerializedName("idSecondSupervisor")
    @Expose
    private String idSecondSupervisor;

    public String getSubject() {
        return subject == null ? "" : subject;
    }

    public String getIdExamRoom() {
        return idExamRoom == null ? "" : idExamRoom;
    }

    public void setIdExamRoom(String idExamRoom) {
        this.idExamRoom = idExamRoom;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCourse() {
        return course == null ? "" : course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch == null ? "" : branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNameClass() {
        return nameClass == null ? "" : nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public String getNumberStudent() {
        return numberStudent == null ? "" : numberStudent;
    }

    public void setNumberStudent(String numberStudent) {
        this.numberStudent = numberStudent;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeExam() {
        return timeExam == null ? "" : timeExam;
    }

    public void setTimeExam(String timeExam) {
        this.timeExam = timeExam;
    }

    public String getIdFirstSupervisor() {
        return idFirstSupervisor == null ? "" : idFirstSupervisor;
    }

    public void setIdFirstSupervisor(String idFirstSupervisor) {
        this.idFirstSupervisor = idFirstSupervisor;
    }

    public String getIdSecondSupervisor() {
        return idSecondSupervisor == null ? "" : idSecondSupervisor;
    }

    public void setIdSecondSupervisor(String idSecondSupervisor) {
        this.idSecondSupervisor = idSecondSupervisor;
    }
}
