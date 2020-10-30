package com.example.informationrecognize.startView.view;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseActivity;
import com.example.informationrecognize.startView.viewModel.StartViewModel;

public class StartViewActivity  extends BaseActivity {
    private StartViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void initAct() {
        initViewModel();
        viewModel.showProgress();
    }

    private void initViewModel() {
        viewModel = getViewModel(StartViewModel.class);
    }
}
