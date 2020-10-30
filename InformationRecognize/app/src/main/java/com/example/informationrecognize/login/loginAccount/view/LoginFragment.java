package com.example.informationrecognize.login.loginAccount.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.AsteriskPasswordTransformationMethod;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.login.loginAccount.viewModel.LoginViewModel;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.edt_user_name)
    EditText userNameEditText;
    @BindView(R.id.edt_password)
    EditText passwordEditText;
    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.img_show_password)
    ImageView showPasswordImageView;

    private LoginViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    protected void initFragment() {
        viewModel = new LoginViewModel();
        viewModel.initViewModel();
        initLiveData();
    }

    private void initLiveData() {
        viewModel.getIsShowPass().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isShowPassword) {
                if (isShowPassword){
                    showPasswordImageView.setImageResource(R.drawable.ic_hide_password_login);
                    passwordEditText.setTransformationMethod(null);
                } else {
                    showPasswordImageView.setImageResource(R.drawable.ic_show_password_login);
                    passwordEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                }
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.img_show_password})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (Utils.stringNullOrEmpty(userNameEditText.getText().toString())){
                    Toast.makeText(getContext(), R.string.ban_chua_nhap_ten_dang_nhap,Toast.LENGTH_SHORT).show();
                    return;
                } else if (Utils.stringNullOrEmpty(passwordEditText.getText().toString())){
                    Toast.makeText(getContext(), R.string.ban_chua_nhap_mat_khau,Toast.LENGTH_SHORT).show();
                    return;
                } else {

                }
                break;
            case R.id.img_show_password:
                if (viewModel.getIsShowPass().getValue()) {
                    viewModel.isShowPass.postValue(false);
                } else {
                    viewModel.isShowPass.postValue(true);
                }
                break;
        }
    }
}
