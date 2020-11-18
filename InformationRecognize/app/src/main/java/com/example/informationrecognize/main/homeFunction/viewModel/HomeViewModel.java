package com.example.informationrecognize.main.homeFunction.viewModel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.homeFunction.model.BannerHomeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModelCommon {
    private MutableLiveData<List<BannerHomeResponse.BannerHome>> listBannerHome = new MutableLiveData<>();

    public MutableLiveData<List<BannerHomeResponse.BannerHome>> getListBannerHome() {
        return listBannerHome;
    }

    public void setListBannerHome(MutableLiveData<List<BannerHomeResponse.BannerHome>> listBannerHome) {
        this.listBannerHome = listBannerHome;
    }

    public void initDataViewModel() {
        getListBanner();
    }

    private void getListBanner() {
        Call<BannerHomeResponse> getListBanner = ApiUtils.getDataApi().getListBannerHome();
        getListBanner.enqueue(new Callback<BannerHomeResponse>() {
            @Override
            public void onResponse(Call<BannerHomeResponse> call, Response<BannerHomeResponse> response) {
                if (response.body() != null && "0".equals(response.body().getErrorcode())
                        && response.body().getListBannerHome() != null) {
                    listBannerHome.setValue(response.body().getListBannerHome());
                } else {
                    Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BannerHomeResponse> call, Throwable t) {
                Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void linkWebView(String linkWeb) {

    }
}
