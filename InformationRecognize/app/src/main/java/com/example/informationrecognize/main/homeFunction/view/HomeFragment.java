package com.example.informationrecognize.main.homeFunction.view;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;
import com.example.informationrecognize.main.homeFunction.adapter.ListBannerAdapter;
import com.example.informationrecognize.main.homeFunction.model.BannerHomeResponse;
import com.example.informationrecognize.main.homeFunction.viewModel.HomeViewModel;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rv_list_banner)
    RecyclerView listBannerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeViewModel viewModel;
    private ListBannerAdapter bannerAdapter;

    private static volatile HomeFragment fInstance;

    public static HomeFragment getInstance() {
        if (fInstance == null) {
            fInstance = new HomeFragment();
        }
        return fInstance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initFragment() {
        initViewModel();
        initView();
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViewModel();
                viewModel.getIsDismissRefresh().setValue(false);
            }
        });
    }

    private void initViewModel() {
        viewModel = getViewModel(HomeViewModel.class);
        viewModel.initDataViewModel();

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1,
                GridLayoutManager.HORIZONTAL, false);

        viewModel.getListBannerHome().observe(this, new Observer<List<BannerHomeResponse.BannerHome>>() {
            @Override
            public void onChanged(List<BannerHomeResponse.BannerHome> bannerHomes) {
                if (bannerAdapter == null) {
                    bannerAdapter = new ListBannerAdapter(getContext(), bannerHomes, new ListBannerAdapter.OnClickListener() {
                        @Override
                        public void onClickItem(int position) {
                            viewModel.linkWebView(bannerAdapter.getListData().get(position).getLinkWeb());
                        }
                    });
                    listBannerView.setAdapter(bannerAdapter);
                    listBannerView.setLayoutManager(layoutManager);
                    final SnapHelper snapHelperCenter = new LinearSnapHelper();
                    snapHelperCenter.attachToRecyclerView(listBannerView);
                } else {
                    bannerAdapter.setListData(bannerHomes);
                }
            }
        });

        viewModel.getIsDismissRefresh().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDismissRefresh) {
                if (isDismissRefresh) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }
}
