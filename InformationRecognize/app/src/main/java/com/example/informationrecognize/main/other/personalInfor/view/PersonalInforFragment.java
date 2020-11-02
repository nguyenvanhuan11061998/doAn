package com.example.informationrecognize.main.other.personalInfor.view;

import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.informationrecognize.R;
import com.example.informationrecognize.Utils.Utils;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.login.loginAccount.model.UserLogin;
import com.example.informationrecognize.main.other.personalInfor.viewModel.PersonalInforViewModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalInforFragment extends BaseFragment {

    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.tv_id_user)
    TextView tvIdUser;
    @BindView(R.id.tv_name_user)
    TextView tvNameUser;
    @BindView(R.id.tv_number_phone)
    TextView tvNumberPhone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    private PersonalInforViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_infor;
    }

    @Override
    protected void initFragment() {
        initViewModel();
    }

    public static PersonalInforFragment getInstance() {
        return new PersonalInforFragment();
    }

    private void initViewModel() {
        viewModel = getViewModel(PersonalInforViewModel.class);
        viewModel.initData();

        viewModel.getUserLogin().observe(this, new Observer<UserLogin>() {
            @Override
            public void onChanged(UserLogin userLogin) {
                if (userLogin != null) {
                    initData(userLogin);
                }
            }
        });
    }

    private void initData(UserLogin userLogin) {
        if (!Utils.stringNullOrEmpty(userLogin.getImageUser())) {
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getActivity()));
            ImageLoader.getInstance().displayImage(userLogin.getImageUser(), imgUser, Utils.optionAvatar);
        }
        tvIdUser.setText(userLogin.getIdUser());
        tvNameUser.setText(userLogin.getNameUser());
        tvNumberPhone.setText(userLogin.getPhoneUser());
        tvEmail.setText(userLogin.getEmailUser());
    }
}
