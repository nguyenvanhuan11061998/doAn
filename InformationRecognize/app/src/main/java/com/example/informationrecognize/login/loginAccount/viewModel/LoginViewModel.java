package com.example.informationrecognize.login.loginAccount.viewModel;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.SharedPref;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.login.loginAccount.model.LoginAccount;
import com.example.informationrecognize.login.loginAccount.model.UserLogin;
import com.example.informationrecognize.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.informationrecognize.Utils.SharedPref.KeyShared.INUSER;

public class LoginViewModel extends ViewModelCommon {
    private SharedPref sharedPref;

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

    public void loginAccount(String username, String password) {
        Call<LoginAccount> callApiLogin = ApiUtils.getDataApi().loginAccount(username, password);
        callApiLogin.enqueue(new Callback<LoginAccount>() {
            @Override
            public void onResponse(Call<LoginAccount> call, Response<LoginAccount> response) {
                if ("0".equals(response.body().getErrorcode())) {
                    Toast.makeText(mActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    callApiLoginSuccess(response.body().getAccount());
                } else {
                    Toast.makeText(mActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginAccount> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiLoginSuccess(UserLogin account) {
        Utils.setUserLogin(account);
        sharedPref = new SharedPref(mActivity);
        sharedPref.setValue(INUSER, account.getIdUser());
        Intent intent = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
