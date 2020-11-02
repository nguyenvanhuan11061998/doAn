package com.example.informationrecognize.main.other.changePassword.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.other.changePassword.viewModel.ChangePasswordViewModel;

import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordFragment extends BaseFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edt_old_password)
    EditText edtOldPassword;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_again_password)
    EditText edtAgainPassword;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private ChangePasswordViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected void initFragment() {
        initViewModel();
    }

    private void initViewModel() {
        viewModel = getViewModel(ChangePasswordViewModel.class);
    }

    public static ChangePasswordFragment getInstance() {
        return new ChangePasswordFragment();
    }

    @OnClick({R.id.btn_confirm, R.id.img_back})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                viewModel.getNewPassword().setValue(edtPassword.getText().toString().trim());
                viewModel.getOldPasword().setValue(edtOldPassword.getText().toString().trim());
                viewModel.getNewPasswordAgain().setValue(edtAgainPassword.getText().toString().trim());

                viewModel.changePassword();
                break;
            case R.id.img_back:
                getActivity().finish();
                break;
        }
    }
}
