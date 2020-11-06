package com.example.informationrecognize.main.checkIn.infoStudent.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

public class InfoStudentViewModel extends ViewModelCommon {

    private MutableLiveData<StudentModel> studentModel = new MutableLiveData<>();
    private MutableLiveData<ClassItemModel> examRoomModel = new MutableLiveData<>();

    public MutableLiveData<ClassItemModel> getExamRoomModel() {
        return examRoomModel;
    }

    public void setExamRoomModel(MutableLiveData<ClassItemModel> examRoomModel) {
        this.examRoomModel = examRoomModel;
    }

    public MutableLiveData<StudentModel> getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(MutableLiveData<StudentModel> studentModel) {
        this.studentModel = studentModel;
    }


}
