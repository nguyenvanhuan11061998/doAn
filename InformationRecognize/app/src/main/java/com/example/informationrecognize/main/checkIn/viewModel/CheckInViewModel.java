package com.example.informationrecognize.main.checkIn.viewModel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.checkIn.model.ClassItemModel;
import com.example.informationrecognize.main.checkIn.model.ExamRoomModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInViewModel extends ViewModelCommon {

    private MutableLiveData<List<ClassItemModel>> listItemClass = new MutableLiveData<>();

    public MutableLiveData<List<ClassItemModel>> getListItemClass() {
        return listItemClass;
    }

    public void setListItemClass(MutableLiveData<List<ClassItemModel>> listItemClass) {
        this.listItemClass = listItemClass;
    }

    public void initData() {
        getListExamRoom();
    }

    private void getListExamRoom() {
        String idUser = Utils.getUserLogin().getIdUser();
        Call<ExamRoomModel> getListExamRoom = ApiUtils.getDataApi().getListExamRoom(idUser);
        getListExamRoom.enqueue(new Callback<ExamRoomModel>() {
            @Override
            public void onResponse(Call<ExamRoomModel> call, Response<ExamRoomModel> response) {
                if (response.body() != null) {
                    if ("0".equals(response.body().getErrorcode())) {
                        listItemClass.setValue(response.body().getListExamRoom());
                    } else {
                        Toast.makeText(mActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ExamRoomModel> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
