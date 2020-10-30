package com.example.informationrecognize.main.homeFunction.view;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    private static volatile HomeFragment fInstance;

    public static HomeFragment getInstance() {
        if (fInstance == null) {
            fInstance = new HomeFragment();
        }
        return fInstance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initFragment() {

    }
}
