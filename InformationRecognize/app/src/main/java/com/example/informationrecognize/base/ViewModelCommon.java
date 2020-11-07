package com.example.informationrecognize.base;

import android.app.Activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

public class ViewModelCommon extends ViewModel {
    protected Activity mActivity;

    public ViewModelCommon setActivity(Activity mActivity) {
        this.mActivity = mActivity;
        return this;
    }
}
