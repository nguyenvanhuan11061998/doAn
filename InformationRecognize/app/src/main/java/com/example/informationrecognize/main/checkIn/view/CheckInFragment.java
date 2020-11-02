package com.example.informationrecognize.main.checkIn.view;

import androidx.recyclerview.widget.RecyclerView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;

import butterknife.BindView;

public class CheckInFragment extends BaseFragment {
    private static volatile CheckInFragment fInstance;

    @BindView(R.id.list_room)
    RecyclerView roomList;

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

    }
}
