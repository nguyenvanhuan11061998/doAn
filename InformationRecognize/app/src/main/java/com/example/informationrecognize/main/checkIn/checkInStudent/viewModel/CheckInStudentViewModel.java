package com.example.informationrecognize.main.checkIn.checkInStudent.viewModel;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.ListStudentCheckIn;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInStudentViewModel extends ViewModelCommon {

    public static final String STUDENT_MODEL = "STUDENT_MODEL";
    private MutableLiveData<List<StudentModel>> listStudent = new MutableLiveData<>();
    private MutableLiveData<ClassItemModel> examRoomModel=  new MutableLiveData<>();

    private MutableLiveData<String> numStudentCheckIn = new MutableLiveData<>();

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

    public void initViewModel(String idExamRoom, ClassItemModel examRoom) {
        examRoomModel.postValue(examRoom);

        Call<ListStudentCheckIn> getListStudentCheckIn = ApiUtils.getDataApi().getListStudentCheckIn(idExamRoom);
        getListStudentCheckIn.enqueue(new Callback<ListStudentCheckIn>() {
            @Override
            public void onResponse(Call<ListStudentCheckIn> call, Response<ListStudentCheckIn> response) {
                if (response.body() != null) {
                    if ("0".equals(response.body().getErrorCode())) {
                        listStudent.setValue(response.body().getStudentModels());
                        countStudentCheckIn();
                    } else {
                        Toast.makeText(mActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListStudentCheckIn> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back() {
        mActivity.finish();
    }

    public void openInforStudent(int position) {
        StudentModel studentModel = listStudent.getValue().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(STUDENT_MODEL, studentModel);
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
    }
}
