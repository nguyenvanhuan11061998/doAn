package com.example.informationrecognize.main.checkIn.checkInStudent.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.baseBinding.BaseBindingFragment;
import com.example.informationrecognize.databinding.FragmentCheckInStudentBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel;

import java.util.List;

import static com.example.informationrecognize.main.checkIn.checkInStudent.CheckInStudentActivity.ID_ROOM;

public class CheckInStudentFragment extends BaseBindingFragment<FragmentCheckInStudentBinding> implements CheckInStudentAdapter.ClickItemListener {

    private CheckInStudentViewModel viewModel;
    private CheckInStudentAdapter adapter;
    private String idRoom;

    public static CheckInStudentFragment getInstance(Bundle bundle) {
        CheckInStudentFragment checkInStudentFragment = new CheckInStudentFragment();
        checkInStudentFragment.setArguments(bundle);
        return checkInStudentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idRoom = getArguments().getString(ID_ROOM);
    }

    @Override
    protected void initFragment() {
        initViewModel();
    }

    private void initViewModel() {
        viewModel = getViewModel(CheckInStudentViewModel.class);
        viewModel.initViewModel(idRoom);

        viewModel.getListStudent().observe(this, new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(List<StudentModel> studentModels) {
                if (studentModels != null) {
                    if (adapter == null) {
                        adapter = new CheckInStudentAdapter(getActivity(), studentModels, CheckInStudentFragment.this);
                        binding.rvListStudent.setAdapter(adapter);
                    } else {
                        adapter.setListData(studentModels);
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_in_student;
    }

    @Override
    public void onClickItem(int position) {

    }
}
