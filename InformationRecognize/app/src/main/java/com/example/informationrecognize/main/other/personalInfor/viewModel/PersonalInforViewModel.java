package com.example.informationrecognize.main.other.personalInfor.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.login.loginAccount.model.UserLogin;

public class PersonalInforViewModel extends ViewModelCommon {

    private MutableLiveData<UserLogin>  userLogin = new MutableLiveData<>();

    public MutableLiveData<UserLogin> getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(MutableLiveData<UserLogin> userLogin) {
        this.userLogin = userLogin;
    }

    public void initData() {
        userLogin.postValue(Utils.getUserLogin());
    }
}
