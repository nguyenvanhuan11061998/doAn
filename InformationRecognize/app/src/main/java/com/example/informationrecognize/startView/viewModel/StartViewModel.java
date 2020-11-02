package com.example.informationrecognize.startView.viewModel;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.SharedPref;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.login.LoginActivity;
import com.example.informationrecognize.login.loginAccount.model.LoginAccount;
import com.example.informationrecognize.login.loginAccount.model.UserLogin;
import com.example.informationrecognize.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Call<LoginAccount> callApiGetUserData = ApiUtils.getDataApi().getUserData(idUser);
        callApiGetUserData.enqueue(new Callback<LoginAccount>() {
            @Override
            public void onResponse(Call<LoginAccount> call, Response<LoginAccount> response) {
                if ("0".equals(response.body().getErrorcode())) {
                    callApiGetUserDataSuccess(response.body().getAccount());
                }
                Toast.makeText(mActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginAccount> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiGetUserDataSuccess(UserLogin account) {
        Utils.setUserLogin(account);
        Intent intent = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    private void goToLoginApp() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    public void showProgress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initDataLogin();
            }
        }, 4000);
    }
}
