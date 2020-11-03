package com.example.informationrecognize.main.checkIn.checkInStudent.view;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.baseBinding.BaseBindingFragment;
import com.example.informationrecognize.databinding.FragmentCheckInStudentBinding;

public class CheckInStudentFragment extends BaseBindingFragment<FragmentCheckInStudentBinding> {
    public static CheckInStudentFragment getInstance() {
        return new CheckInStudentFragment();
    }

    @Override
    protected void initFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_in_student;
    }
}
