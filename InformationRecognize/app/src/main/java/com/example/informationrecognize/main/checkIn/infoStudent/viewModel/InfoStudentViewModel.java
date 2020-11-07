package com.example.informationrecognize.main.checkIn.infoStudent.viewModel;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.main.MainActivity;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.infoStudent.view.ConfirmCheckInDialog;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

public class InfoStudentViewModel extends ViewModelCommon implements ConfirmCheckInDialog.ClickListener {

    private MutableLiveData<StudentModel> studentModel = new MutableLiveData<>();
    private MutableLiveData<ClassItemModel> examRoomModel = new MutableLiveData<>();
    private ConfirmCheckInDialog confirmCheckInDialog;

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


    public void showDialogCheckInStudent(FragmentManager fragmentManager) {
        if (confirmCheckInDialog != null) {
            confirmCheckInDialog.setData(this);
            confirmCheckInDialog.show(fragmentManager, "n");
        } else {
            confirmCheckInDialog = new ConfirmCheckInDialog();
            confirmCheckInDialog.setData(this);
            confirmCheckInDialog.show(fragmentManager, "n");
        }
    }

    @Override
    public void onClickCheckIn() {

    }

    @Override
    public void onClickCancel() {
        if (confirmCheckInDialog != null && confirmCheckInDialog.getShowsDialog()) {
            confirmCheckInDialog.dismiss();
        }
    }
}
