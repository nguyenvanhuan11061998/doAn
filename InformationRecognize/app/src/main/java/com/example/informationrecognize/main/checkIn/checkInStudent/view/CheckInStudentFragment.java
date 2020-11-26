package com.example.informationrecognize.main.checkIn.checkInStudent.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.baseBinding.BaseBindingFragment;
import com.example.informationrecognize.databinding.FragmentCheckInStudentBinding;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.StudentModel;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInSharedViewModel;
import com.example.informationrecognize.main.checkIn.checkInStudent.viewModel.CheckInStudentViewModel;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.informationrecognize.main.checkIn.CheckInStudentActivity.ID_ROOM;
import static com.example.informationrecognize.main.checkIn.CheckInStudentActivity.ROOM;

public class CheckInStudentFragment extends BaseBindingFragment<FragmentCheckInStudentBinding>
        implements CheckInStudentAdapter.ClickItemListener {

    @BindView(R.id.btn_check_in)
    Button btnCheckIn;

    private CheckInStudentViewModel viewModel;
    private CheckInStudentAdapter adapter;
    private String idRoom;
    private ClassItemModel examRoomModel;
    private CheckInStudentListener checkInStudentListener;
    private CheckInSharedViewModel sharedViewModel;

    public static CheckInStudentFragment getInstance(Bundle bundle) {
        CheckInStudentFragment checkInStudentFragment = new CheckInStudentFragment();
        checkInStudentFragment.setArguments(bundle);
        return checkInStudentFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idRoom = getArguments().getString(ID_ROOM);
        examRoomModel = (ClassItemModel) getArguments().getSerializable(ROOM);
    }

    @Override
    protected void initFragment() {
        initViewModel();
        initView();
    }

    private void initView() {
        checkInStudentListener = new CheckInStudentListener(this);
        binding.setListener(checkInStudentListener);
    }

    private void initViewModel() {
        viewModel = getViewModel(CheckInStudentViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity()).get(CheckInSharedViewModel.class);
        viewModel.initViewModel(idRoom, examRoomModel);

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

        viewModel.getNumStudentCheckIn().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String countCheckIn) {
                binding.tvNumberStudentCheckin.setText(countCheckIn);
            }
        });

        viewModel.getExamRoomModel().observe(this, new Observer<ClassItemModel>() {
            @Override
            public void onChanged(ClassItemModel examRoomModel) {
                if (examRoomModel != null) {
                    binding.setExamRoom(examRoomModel);
                }
            }
        });

        sharedViewModel.getIsReload().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isReload) {
                if (isReload) {
                    viewModel.initViewModel(idRoom, examRoomModel);
                }
            }
        });

        viewModel.getIsEnableButtonCheckIn().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isEnableButton) {
                setStatusButton(isEnableButton);
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_in_student;
    }

    @Override
    public void onClickItem(int position) {
        viewModel.openInforStudent(position);
    }

    public class CheckInStudentListener {
        public CheckInStudentListener(CheckInStudentFragment context) {
        }

        public void backImageViewClick(View view) {
            viewModel.back();
        }
    }

    @OnClick({R.id.btn_check_in})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_in:
                    viewModel.openCamera();
                break;
        }
    }

    public void setStatusButton(boolean isEnable) {
        if (isEnable) {
            btnCheckIn.setBackground(getContext().getDrawable(R.drawable.custom_round_corner_button));
            btnCheckIn.setEnabled(true);
        } else {
            btnCheckIn.setBackground(getContext().getDrawable(R.drawable.custom_round_corner_button_disable));
            btnCheckIn.setEnabled(false);
        }
    }
}
