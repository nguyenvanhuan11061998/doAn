package com.example.informationrecognize.startView.viewModel;

import android.content.Intent;
import android.os.Handler;

import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.login.LoginActivity;

public class StartViewModel  extends ViewModelCommon {

    private Handler handler = new Handler();

    public void showProgress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivity(intent);
                mActivity.finish();
            }
        }, 2000);
    }
}
