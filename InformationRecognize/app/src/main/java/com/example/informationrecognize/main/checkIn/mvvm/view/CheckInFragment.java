package com.example.informationrecognize.main.checkIn.mvvm.view;

import android.content.Intent;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.checkIn.CheckInStudentActivity;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;
import com.example.informationrecognize.main.checkIn.mvvm.viewModel.CheckInViewModel;

import java.util.List;

import butterknife.BindView;

import static com.example.informationrecognize.main.checkIn.CheckInStudentActivity.ID_ROOM;
import static com.example.informationrecognize.main.checkIn.CheckInStudentActivity.ROOM;

public class CheckInFragment extends BaseFragment implements ClassListAdapter.ClickItemListener {
    private static volatile CheckInFragment fInstance;

    @BindView(R.id.list_room)
    RecyclerView roomList;

    private CheckInViewModel viewModel;
    private ClassListAdapter adapter;
    private List<ClassItemModel> listExamRoom;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_in;
    }

    public static CheckInFragment getInstance() {
        if (fInstance == null) {
            fInstance = new CheckInFragment();
        }
        return fInstance;
    }

    @Override
    protected void initFragment() {
        initViewModel();
        initView();
    }

    private void initView() {
    }

    private void initViewModel() {
        viewModel = getViewModel(CheckInViewModel.class);
        viewModel.initData();

        viewModel.getListItemClass().observe(this, new Observer<List<ClassItemModel>>() {
            @Override
            public void onChanged(List<ClassItemModel> classItemModels) {
                if (classItemModels != null) {
                    if (adapter  == null) {
                        adapter = new ClassListAdapter(getActivity(), classItemModels, CheckInFragment.this);
                        roomList.setAdapter(adapter);
                    } else {
                        adapter.setData(classItemModels);
                    }
                    listExamRoom = classItemModels;
                }
            }
        });
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getActivity(), CheckInStudentActivity.class);
        intent.putExtra(ID_ROOM, listExamRoom.get(position).getIdExamRoom());
        intent.putExtra(ROOM, listExamRoom.get(position));
        startActivity(intent);

    }
}
