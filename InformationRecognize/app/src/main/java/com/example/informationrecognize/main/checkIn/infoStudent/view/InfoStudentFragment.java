package com.example.informationrecognize.main.checkIn.infoStudent.view;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.baseBinding.BaseBindingFragment;
import com.example.informationrecognize.databinding.FragmentInfoStudentBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.infoStudent.viewModel.InfoStudentViewModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

import butterknife.OnClick;

import static com.example.informationrecognize.main.checkIn.CheckInStudentActivity.ROOM;
import static com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel.STUDENT_MODEL;

public class InfoStudentFragment extends BaseBindingFragment<FragmentInfoStudentBinding> {
    private InfoStudentViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info_student;
    }

    public static InfoStudentFragment getInstance(Bundle bundle) {
        InfoStudentFragment infoStudentFragment = new InfoStudentFragment();
        infoStudentFragment.setArguments(bundle);
        return infoStudentFragment;
    }

    @Override
    protected void initFragment() {
        initViewModel();
        initView();
    }

    private void initViewModel() {
        viewModel = getViewModel(InfoStudentViewModel.class);

        viewModel.getStudentModel().observe(this, new Observer<StudentModel>() {
            @Override
            public void onChanged(StudentModel studentModel) {
                if (studentModel != null) {
                    binding.setStudent(studentModel);
                    setViewCheckIn(studentModel.isCheckIn());
                }
            }
        });

        viewModel.getExamRoomModel().observe(this, new Observer<ClassItemModel>() {
            @Override
            public void onChanged(ClassItemModel classItemModel) {
                if (classItemModel != null) {
                    binding.setExamRoom(classItemModel);
                }
            }
        });
    }

    private void initView() {
        StudentModel studentModel = null;
        ClassItemModel examRoomModel = null;
        if (getArguments() != null) {
            studentModel = (StudentModel) getArguments().getSerializable(STUDENT_MODEL);
            examRoomModel = (ClassItemModel) getArguments().getSerializable(ROOM);
        }
        viewModel.getStudentModel().postValue(studentModel);
        viewModel.getExamRoomModel().postValue(examRoomModel);
    }


    @OnClick({R.id.img_back, R.id.btn_check_in})
    void onClick (View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackFragment();
                break;
            case R.id.btn_check_in:
                viewModel.showDialogCheckInStudent(getActivity().getSupportFragmentManager());
                break;
        }
    }

    public void setViewCheckIn(boolean isCheckIn) {
        if (isCheckIn) {
            binding.imgStatus.setImageResource(R.drawable.ic_green_round);
            binding.tvStatus.setText(getString(R.string.da_diem_danh));
            binding.tvStatus.setTextColor(getResources().getColor(R.color.colorGreen));

            binding.btnCheckIn.setEnabled(false);
            binding.btnCheckIn.setText(getString(R.string.da_diem_danh));
            binding.btnCheckIn.setBackground(getResources().getDrawable(R.drawable.custom_round_corner_button_disable));
        } else {
            binding.imgStatus.setImageResource(R.drawable.ic_yellow_round);
            binding.tvStatus.setText(getString(R.string.chua_diem_danh));
            binding.tvStatus.setTextColor(getResources().getColor(R.color.colorYellow_v2));

            binding.btnCheckIn.setEnabled(true);
            binding.btnCheckIn.setText(getString(R.string.diem_danh));
            binding.btnCheckIn.setBackground(getResources().getDrawable(R.drawable.custom_round_corner_button));
        }
    }

}
