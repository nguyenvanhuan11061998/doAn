package com.example.informationrecognize.main.information.view;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;

public class InformationFragment extends BaseFragment {
    public static volatile InformationFragment fInstance;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initFragment() {

    }

    public static InformationFragment getInstance() {
        if (fInstance == null) {
            fInstance = new InformationFragment();
        }
        return fInstance;
    }
}