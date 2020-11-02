package com.example.informationrecognize.main.other.mvvm.view;

import android.content.Intent;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.MainActivity;
import com.example.informationrecognize.main.other.mvvm.viewModel.OtherViewModel;
import com.example.informationrecognize.main.other.personalInfor.OtherUtilsActivity;
import com.example.informationrecognize.main.other.personalInfor.view.PersonalInforFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class OtherFragment extends BaseFragment {
    public static volatile  OtherFragment fInstance;

    @BindView(R.id.cv_personal_infor)
    CardView personalInforView;

    private OtherViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initFragment() {
        initViewModel();
    }

    private void initViewModel() {
        viewModel = getViewModel(OtherViewModel.class);
    }

    public static OtherFragment getInstance() {
        if (fInstance == null) {
            fInstance = new OtherFragment();
        }
        return fInstance;
    }

    @OnClick({R.id.cv_personal_infor})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_personal_infor:
                Intent intent = new Intent(getActivity(), OtherUtilsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
