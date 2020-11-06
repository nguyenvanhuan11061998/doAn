package com.example.informationrecognize.main.checkIn.infoStudent.view;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.baseBinding.BaseBindingFragment;
import com.example.informationrecognize.databinding.FragmentInfoStudentBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.infoStudent.viewModel.InfoStudentViewModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

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
                    initData();
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

    private void initData() {

    }
}
