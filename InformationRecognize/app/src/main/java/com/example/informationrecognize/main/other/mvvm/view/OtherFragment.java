package com.example.informationrecognize.main.other.mvvm.view;

import android.content.Intent;
import android.view.View;

import androidx.cardview.widget.CardView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.other.mvvm.viewModel.OtherViewModel;
import com.example.informationrecognize.main.other.OtherUtilsActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.informationrecognize.Utils.Constances.FLAG_FUNCTION_CHANGE_PASSWORD;
import static com.example.informationrecognize.Utils.Constances.FLAG_FUNCTION_PERSONAL_INFO;
import static com.example.informationrecognize.main.other.OtherUtilsActivity.FLAG_FUNCTION;

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

    @OnClick({R.id.cv_personal_infor, R.id.cv_change_password})
    void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.cv_personal_infor:
                intent = new Intent(getActivity(), OtherUtilsActivity.class);
                intent.putExtra(FLAG_FUNCTION, FLAG_FUNCTION_PERSONAL_INFO);
                startActivity(intent);
                break;

            case R.id.cv_change_password:
                intent = new Intent(getActivity(), OtherUtilsActivity.class);
                intent.putExtra(FLAG_FUNCTION, FLAG_FUNCTION_CHANGE_PASSWORD);
                startActivity(intent);
                break;
        }
    }
}
