package com.example.informationrecognize.startView.viewModel;

import android.content.Intent;
import android.os.Handler;

import com.example.informationrecognize.Utils.SharedPref;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.login.LoginActivity;
import com.example.informationrecognize.main.MainActivity;

public class StartViewModel  extends ViewModelCommon {

    private Handler handler = new Handler();
    private SharedPref sharedPref;

    // kiểm tra xem đã có tài khoản login hay chưa
    public void initDataLogin() {
        sharedPref = new SharedPref(mActivity);
        String idUser = sharedPref.getValue(SharedPref.KeyShared.INUSER, "");
        if (Utils.stringNullOrEmpty(idUser)) {
            goToLoginApp();
        } else {
            callApiLoadDataUser(idUser);
        }
    }

    private void callApiLoadDataUser(String idUser) {

    }

    private void goToLoginApp() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    public void showProgress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataLogin();
            }
        }, 2000);
    }
}
