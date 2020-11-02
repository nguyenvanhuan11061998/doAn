package com.example.informationrecognize.main.other.changePassword.viewModel;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.other.changePassword.model.ChangePasswordModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordViewModel extends ViewModelCommon {

    private MutableLiveData<String> oldPasword = new MutableLiveData<>();
    private MutableLiveData<String> newPassword = new MutableLiveData<>();
    private MutableLiveData<String> newPasswordAgain = new MutableLiveData<>();

    public MutableLiveData<String> getOldPasword() {
        return oldPasword;
    }

    public void setOldPasword(MutableLiveData<String> oldPasword) {
        this.oldPasword = oldPasword;
    }

    public MutableLiveData<String> getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(MutableLiveData<String> newPassword) {
        this.newPassword = newPassword;
    }

    public MutableLiveData<String> getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(MutableLiveData<String> newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }


    public void changePassword() {

        if (Utils.stringNullOrEmpty(oldPasword.getValue()) || Utils.stringNullOrEmpty(newPassword.getValue())
                || Utils.stringNullOrEmpty(newPasswordAgain.getValue())) {
            Toast.makeText(mActivity, mActivity.getString(R.string.vui_long_nhap_du_thong_tin), Toast.LENGTH_SHORT).show();
            return;
        } else if (!oldPasword.getValue().equals(Utils.getUserLogin().getPassword())){
            Toast.makeText(mActivity, mActivity.getString(R.string.mat_khau_hien_tai_khong_chinh_xac), Toast.LENGTH_SHORT).show();
            return;
        } else if (newPassword.getValue().length() < 8) {
            Toast.makeText(mActivity, mActivity.getString(R.string.mat_khau_moi_phai_du_8_ky_tu), Toast.LENGTH_SHORT).show();
            return;
        } else if (!newPassword.getValue().equals(newPasswordAgain.getValue())) {
            Toast.makeText(mActivity, mActivity.getString(R.string.nhap_lai_mat_khau_khong_chinh_xac), Toast.LENGTH_SHORT).show();
            return;
        } else {
            callApiChangePassword(newPassword.getValue());
        }
    }

    private void callApiChangePassword(String newPassword) {
        String userName = Utils.getUserLogin().getUserName();
        String oldPassword = Utils.getUserLogin().getPassword();
        Call<ChangePasswordModel> callChangePass = ApiUtils.getDataApi().changePasword(userName, oldPassword, newPassword);
        callChangePass.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                Toast.makeText(mActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                Toast.makeText(mActivity,mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
