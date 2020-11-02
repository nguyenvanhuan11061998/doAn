package com.example.informationrecognize.main.other;

import android.content.Intent;

import com.example.informationrecognize.base.BaseActivity;
import com.example.informationrecognize.main.other.changePassword.view.ChangePasswordFragment;
import com.example.informationrecognize.main.other.personalInfor.view.PersonalInforFragment;

import static com.example.informationrecognize.Utils.Constances.FLAG_FUNCTION_CHANGE_PASSWORD;
import static com.example.informationrecognize.Utils.Constances.FLAG_FUNCTION_PERSONAL_INFO;

public class OtherUtilsActivity extends BaseActivity {

    public static String FLAG_FUNCTION = "";

    @Override
    protected void initAct() {
        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra(FLAG_FUNCTION) != null) {
            switch (intent.getStringExtra(FLAG_FUNCTION)) {
                case FLAG_FUNCTION_PERSONAL_INFO:
                    pushView(PersonalInforFragment.getInstance());
                    break;
                case FLAG_FUNCTION_CHANGE_PASSWORD:
                    pushView(ChangePasswordFragment.getInstance());
                    break;
            }
        }
    }
}
