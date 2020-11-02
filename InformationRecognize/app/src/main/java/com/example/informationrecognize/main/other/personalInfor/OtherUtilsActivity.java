package com.example.informationrecognize.main.other.personalInfor;

import com.example.informationrecognize.base.BaseActivity;
import com.example.informationrecognize.main.other.personalInfor.view.PersonalInforFragment;

public class OtherUtilsActivity extends BaseActivity {

    @Override
    protected void initAct() {
        pushView(PersonalInforFragment.getInstance());
    }
}
