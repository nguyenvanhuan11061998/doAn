package com.example.informationrecognize.login.viewModel;

import androidx.lifecycle.MutableLiveData;

public class LoginViewModel {
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
