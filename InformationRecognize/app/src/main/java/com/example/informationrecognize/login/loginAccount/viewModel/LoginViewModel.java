package com.example.informationrecognize.login.loginAccount.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.informationrecognize.base.ViewModelCommon;

public class LoginViewModel extends ViewModelCommon {
    public MutableLiveData<Boolean> isShowPass = new MutableLiveData<>();

    public void initViewModel(){
        isShowPass.postValue(false);
    }

    public MutableLiveData<Boolean> getIsShowPass() {
        return isShowPass;
    }

    public void setIsShowPass(MutableLiveData<Boolean> isShowPass) {
        this.isShowPass = isShowPass;
    }
}
