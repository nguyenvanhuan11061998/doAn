package com.example.informationrecognize.main.checkIn.checkInStudent.viewModel;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.dialog.CheckInByAiSuccessDialog;
import com.example.informationrecognize.dialog.NotificationDialog;
import com.example.informationrecognize.main.checkIn.CameraCheckinFragment;
import com.example.informationrecognize.main.checkIn.CheckInStudentActivity;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.ListStudentCheckIn;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.infoStudent.model.CheckInResponse;
import com.example.informationrecognize.main.checkIn.infoStudent.view.InfoStudentFragment;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.informationrecognize.main.checkIn.CheckInStudentActivity.ROOM;

public class CheckInStudentViewModel extends ViewModelCommon implements NotificationDialog.ClickDialogListener {

    public static final int PHOTO_REQUEST = 1998;
    public static final String STUDENT_MODEL = "STUDENT_MODEL";

    private CheckInByAiSuccessDialog checkInByAiSuccessDialog;
    private FragmentManager fragmentManager;
    private String idExamRoom;

    private MutableLiveData<List<StudentModel>> listStudent = new MutableLiveData<>();
    private MutableLiveData<ClassItemModel> examRoomModel=  new MutableLiveData<>();
    private MutableLiveData<String> numStudentCheckIn = new MutableLiveData<>();
    private MutableLiveData<Boolean> isEnableButtonCheckIn = new MutableLiveData<>();
    private MutableLiveData<Boolean> isOpenCamera = new MutableLiveData<>();
    private MutableLiveData<Boolean> isFinish = new MutableLiveData<>();
    private MutableLiveData<Activity> mActivity = new MutableLiveData<>();

    public MutableLiveData<Activity> getmActivity() {
        return mActivity;
    }

    public void setmActivity(MutableLiveData<Activity> mActivity) {
        this.mActivity = mActivity;
    }

    public MutableLiveData<Boolean> getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(MutableLiveData<Boolean> isFinish) {
        this.isFinish = isFinish;
    }

    public MutableLiveData<Boolean> getIsOpenCamera() {
        return isOpenCamera;
    }

    public void setIsOpenCamera(MutableLiveData<Boolean> isOpenCamera) {
        this.isOpenCamera = isOpenCamera;
    }

    public MutableLiveData<Boolean> getIsEnableButtonCheckIn() {
        return isEnableButtonCheckIn;
    }

    public void setIsEnableButtonCheckIn(MutableLiveData<Boolean> isEnableButtonCheckIn) {
        this.isEnableButtonCheckIn = isEnableButtonCheckIn;
    }

    public MutableLiveData<String> getNumStudentCheckIn() {
        return numStudentCheckIn;
    }

    public void setNumStudentCheckIn(MutableLiveData<String> numStudentCheckIn) {
        this.numStudentCheckIn = numStudentCheckIn;
    }

    public MutableLiveData<ClassItemModel> getExamRoomModel() {
        return examRoomModel;
    }

    public void setExamRoomModel(MutableLiveData<ClassItemModel> examRoomModel) {
        this.examRoomModel = examRoomModel;
    }

    public MutableLiveData<List<StudentModel>> getListStudent() {
        return listStudent;
    }

    public void setListStudent(MutableLiveData<List<StudentModel>> listStudent) {
        this.listStudent = listStudent;
    }

    public void initViewModel(String idExamRoom, ClassItemModel examRoom, FragmentManager fragmentManager) {
        examRoomModel.postValue(examRoom);
        this.idExamRoom = idExamRoom;
        this.fragmentManager = fragmentManager;

        Call<ListStudentCheckIn> getListStudentCheckIn = ApiUtils.getDataApi().getListStudentCheckIn(idExamRoom);
        getListStudentCheckIn.enqueue(new Callback<ListStudentCheckIn>() {
            @Override
            public void onResponse(Call<ListStudentCheckIn> call, Response<ListStudentCheckIn> response) {
                if (response.body() != null) {
                    if ("0".equals(response.body().getErrorCode())) {
                        listStudent.setValue(response.body().getStudentModels());
                        countStudentCheckIn();
                    } else {
                        Toast.makeText(mActivity.getValue(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListStudentCheckIn> call, Throwable t) {
                Toast.makeText(mActivity.getValue(), mActivity.getValue().getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back() {
        isFinish.setValue(true);
    }

    public void openInforStudent(int position) {
        StudentModel studentModel = listStudent.getValue().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(STUDENT_MODEL, studentModel);
        bundle.putSerializable(ROOM, examRoomModel.getValue());

        CheckInStudentActivity activity = (CheckInStudentActivity) mActivity.getValue();
        activity.pushView(InfoStudentFragment.getInstance(bundle));
    }


    public void countStudentCheckIn() {
        List<StudentModel> students = new ArrayList<>();
        students = listStudent.getValue();
        String numStudent = students.size() + "";

        int countCheckin = 0;
        for (StudentModel student: students) {
            if (student.isCheckIn()) {
                countCheckin ++;
            }
        }

        String countCheckIn = countCheckin + "/" + numStudent;
        numStudentCheckIn.postValue(countCheckIn);

        if (String.valueOf(countCheckin).equals(numStudent)) {
            isEnableButtonCheckIn.setValue(false);
        } else {
            isEnableButtonCheckIn.setValue(true);
        }
    }

    public void openCamera() {
        isOpenCamera.setValue(true);
    }

    public void callApiCheckInByAI (Uri uri) {
        String idRoom = examRoomModel.getValue().getIdExamRoom();
        Call<CheckInResponse> callCheckInByAI = ApiUtils.getDataApi().checkInStudentByAI(uri, idRoom);
        callCheckInByAI.enqueue(new Callback<CheckInResponse>() {
            @Override
            public void onResponse(Call<CheckInResponse> call, Response<CheckInResponse> response) {
                if (response.body() != null && "0".equals(response.body().getErrorCode())) {
                    showPopupCheckInSuccess(response.body().getData().getIdStudent());
                } else {
                    Toast.makeText(mActivity.getValue(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckInResponse> call, Throwable t) {

            }
        });
    }

    private void showPopupCheckInSuccess(String idStudent) {
        StudentModel studentModel = null;
        for (StudentModel student: listStudent.getValue()) {
            if (student.getIdStudent().equals(idStudent)) {
                studentModel = student;
            }
        }

        if (checkInByAiSuccessDialog != null) {
            checkInByAiSuccessDialog.setData(studentModel, this);
            checkInByAiSuccessDialog.show(fragmentManager, "");
        } else {
            checkInByAiSuccessDialog = new CheckInByAiSuccessDialog();
            checkInByAiSuccessDialog.setData(studentModel, this);
            checkInByAiSuccessDialog.show(fragmentManager, "");
        }
    }

    @Override
    public void onClickButtonConfirm() {
        initViewModel(idExamRoom, examRoomModel.getValue(), fragmentManager);
    }
}
