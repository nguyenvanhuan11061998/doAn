package com.example.informationrecognize.main.checkIn.model;

public class ClassItemModel {
    // tên lớp
    private String nameClass;
    // khóa học
    private String course;
    // ngành học
    private String branch;
    // sĩ số sinh viên
    private String numberStudent;
    // trạng thái
    private String status;
    // thời gian thi.
    private String timeExam;
    // thời gian bắt đầu
    private String startTime;
    // mã giám thị coi thi 1:
    private String idFirstSupervisor;
    // mã giám thị coi thi 2
    private String idSecondSupervisor;

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
