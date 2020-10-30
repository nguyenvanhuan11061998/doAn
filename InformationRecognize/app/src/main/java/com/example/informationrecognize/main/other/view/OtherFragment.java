package com.example.informationrecognize.main.other.view;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;

public class OtherFragment extends BaseFragment {
    public static volatile  OtherFragment fInstance;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initFragment() {

    }

    public static OtherFragment getInstance() {
        if (fInstance == null) {
            fInstance = new OtherFragment();
        }
        return fInstance;
    }
}
