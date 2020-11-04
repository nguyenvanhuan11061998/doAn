package com.example.informationrecognize.main.checkIn.checkInStudent.model;

public class StudentModel {
    // mã sinh viên
    private String idStudent;
    // tên sinh viên
    private String nameStudent;
    // ảnh sinh viên
    private String imageStudent;
    // chứng minh nhân dân
    private String idPeople;
    // khóa
    private String courseStudent;
    // lớp
    private String classRoom;
    //trường
    private String university;
    // giới tính
    private String sex;
    // khoa/ ngành
    private String branch;
    // lưu giá trị đã check in hay chưa
    private boolean isCheckIn;

    public String getClassRoom() {
        return classRoom;
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
