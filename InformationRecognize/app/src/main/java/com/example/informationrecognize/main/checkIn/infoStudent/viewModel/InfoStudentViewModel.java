package com.example.informationrecognize.main.checkIn.infoStudent.viewModel;

import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.infoStudent.model.CheckInResponse;
import com.example.informationrecognize.main.checkIn.infoStudent.view.ConfirmCheckInDialog;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoStudentViewModel extends ViewModelCommon implements ConfirmCheckInDialog.ClickListener {

    private MutableLiveData<StudentModel> studentModel = new MutableLiveData<>();
    private MutableLiveData<ClassItemModel> examRoomModel = new MutableLiveData<>();
    private MutableLiveData<Boolean> isReloadHomeCheckIn = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsReloadHomeCheckIn() {
        return isReloadHomeCheckIn;
    }

    public void setIsReloadHomeCheckIn(MutableLiveData<Boolean> isReloadHomeCheckIn) {
        this.isReloadHomeCheckIn = isReloadHomeCheckIn;
    }

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
        String nameStudent = "";
        String idStudent = "";
        nameStudent = studentModel.getValue().getNameStudent();
        idStudent = studentModel.getValue().getIdStudent();
        if (confirmCheckInDialog != null) {
            confirmCheckInDialog.setData(nameStudent, idStudent, this);
            confirmCheckInDialog.show(fragmentManager, "n");
        } else {
            confirmCheckInDialog = new ConfirmCheckInDialog();
            confirmCheckInDialog.setData(nameStudent, idStudent, this);
            confirmCheckInDialog.show(fragmentManager, "n");
        }
    }

    @Override
    public void onClickCheckIn() {
        String idStudent = studentModel.getValue().getIdStudent();
        String idExamRoom = examRoomModel.getValue().getIdExamRoom();
        Call<CheckInResponse> checkInStudent = ApiUtils.getDataApi().checkInStudent(idStudent, idExamRoom);
        checkInStudent.enqueue(new Callback<CheckInResponse>() {
            @Override
            public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {
                if (response.body() != null && "0".equals(response.body().getErrorCode()) && response.body().getData() != null) {
                    if (response.body().getData().getStatus().equals("1")) {
                        checkInStudentSuccess();
                    } else {
                        Toast.makeText(mActivity, mActivity.getString(R.string.diem_danh_sinh_vien_khong_thanh_cong), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.diem_danh_sinh_vien_khong_thanh_cong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckInResponse> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.diem_danh_sinh_vien_khong_thanh_cong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkInStudentSuccess() {
        StudentModel studentModelSuccess = studentModel.getValue();
        studentModelSuccess.setCheckIn(true);
        studentModel.postValue(studentModelSuccess);
        isReloadHomeCheckIn.postValue(true);
    }

    @Override
    public void onClickCancel() {
        if (confirmCheckInDialog != null && confirmCheckInDialog.getShowsDialog()) {
            confirmCheckInDialog.dismiss();
        }
    }
}
