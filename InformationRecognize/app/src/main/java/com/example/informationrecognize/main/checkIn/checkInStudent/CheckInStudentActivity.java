package com.example.informationrecognize.main.checkIn.checkInStudent;

import com.example.informationrecognize.base.baseBinding.BaseBindingActivity;
import com.example.informationrecognize.databinding.ActivityBaseBindingBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.view.CheckInStudentFragment;

public class CheckInStudentActivity extends BaseBindingActivity<ActivityBaseBindingBinding> {

    @Override
    protected void intAct() {
        pushView(CheckInStudentFragment.getInstance());
    }
}
