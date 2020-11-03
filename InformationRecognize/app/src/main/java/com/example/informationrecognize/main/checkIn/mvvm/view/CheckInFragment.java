package com.example.informationrecognize.main.checkIn.mvvm.view;

import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.checkIn.mvvm.model.ClassItemModel;
import com.example.informationrecognize.main.checkIn.mvvm.viewModel.CheckInViewModel;

import java.util.List;

import butterknife.BindView;

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
        Toast.makeText(getActivity(), "====: "+listExamRoom.get(position).getIdExamRoom(), Toast.LENGTH_SHORT).show();

    }
}