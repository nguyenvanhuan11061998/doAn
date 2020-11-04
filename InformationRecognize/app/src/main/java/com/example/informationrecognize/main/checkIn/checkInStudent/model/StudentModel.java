package com.example.informationrecognize.main.checkIn.checkInStudent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentModel {
    // mã sinh viên
    @SerializedName("idStudent")
    @Expose
    private String idStudent;
    // tên sinh viên
    @SerializedName("nameStudent")
    @Expose
    private String nameStudent;
    // ảnh sinh viên
    @SerializedName("imageStudent")
    @Expose
    private String imageStudent;
    // chứng minh nhân dân
    @SerializedName("idPeople")
    @Expose
    private String idPeople;
    // khóa
    @SerializedName("courseStudent")
    @Expose
    private String courseStudent;
    // lớp
    @SerializedName("classRoom")
    @Expose
    private String classRoom;
    //trường
    @SerializedName("university")
    @Expose
    private String university;
    // giới tính
    @SerializedName("sex")
    @Expose
    private String sex;
    // khoa/ ngành
    @SerializedName("branch")
    @Expose
    private String branch;
    // khoa/ ngành
    @SerializedName("idExamRoom")
    @Expose
    private String idExamRoom;
    // lưu giá trị đã check in hay chưa
    @SerializedName("isCheckIn")
    @Expose
    private boolean isCheckIn;

    public String getUniversity() {
        return university == null ? "" : university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBranch() {
        return branch == null ? "" : branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIdExamRoom() {
        return idExamRoom == null ? "" : idExamRoom;
    }

    public void setIdExamRoom(String idExamRoom) {
        this.idExamRoom = idExamRoom;
    }

    public String getClassRoom() {
        return classRoom == null ? "" : classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getIdStudent() {
        return idStudent == null ? "" : idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent == null ? "" : nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getImageStudent() {
        return imageStudent == null ? "" : imageStudent;
    }

    public void setImageStudent(String imageStudent) {
        this.imageStudent = imageStudent;
    }

    public String getIdPeople() {
        return idPeople == null ? "" : idPeople;
    }

    public void setIdPeople(String idPeople) {
        this.idPeople = idPeople;
    }

    public String getCourseStudent() {
        return courseStudent == null ? "" : courseStudent;
    }

    public void setCourseStudent(String courseStudent) {
        this.courseStudent = courseStudent;
    }

    // check sinh viên đã được điểm danh hay chưa
    public boolean isCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }
}
