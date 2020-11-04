package com.example.informationrecognize.main.checkIn.checkInStudent.viewModel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.ListStudentCheckIn;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInStudentViewModel extends ViewModelCommon {

    private MutableLiveData<List<StudentModel>> listStudent = new MutableLiveData<>();

    public MutableLiveData<List<StudentModel>> getListStudent() {
        return listStudent;
    }

    public void setListStudent(MutableLiveData<List<StudentModel>> listStudent) {
        this.listStudent = listStudent;
    }

    public void initViewModel(String idRoom) {
        Call<ListStudentCheckIn> getListStudentCheckIn = ApiUtils.getDataApi().getListStudentCheckIn(idRoom);
        getListStudentCheckIn.enqueue(new Callback<ListStudentCheckIn>() {
            @Override
            public void onResponse(Call<ListStudentCheckIn> call, Response<ListStudentCheckIn> response) {
                if (response.body() != null) {
                    if ("0".equals(response.body().getErrorCode())) {
                        listStudent.setValue(response.body().getStudentModels());
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
}
