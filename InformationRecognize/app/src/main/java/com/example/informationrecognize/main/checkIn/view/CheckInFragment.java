package com.example.informationrecognize.main.checkIn.view;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;

public class CheckInFragment extends BaseFragment {
    private static volatile CheckInFragment fInstance;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_in;
    }

    @Override
    protected void initFragment() {

    }

    public static CheckInFragment getInstance() {
        if (fInstance == null) {
            fInstance = new CheckInFragment();
        }
        return fInstance;
    }
}
