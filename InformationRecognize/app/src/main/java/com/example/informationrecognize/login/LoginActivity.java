package com.example.informationrecognize.login;

import com.example.informationrecognize.base.BaseActivity;
import com.example.informationrecognize.login.loginAccount.view.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void initAct() {
        pushView(LoginFragment.getInstance());
    }
}
