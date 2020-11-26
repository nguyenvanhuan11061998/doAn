package com.example.informationrecognize.main.homeFunction.viewModel;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import com.example.informationrecognize.base.ViewModelCommon;
import com.example.informationrecognize.base.baseApi.ApiUtils;
import com.example.informationrecognize.main.homeFunction.model.AllHomeResponse;
import com.example.informationrecognize.main.homeFunction.model.BannerHomeResponse;
import com.example.informationrecognize.main.linkWebView.OpenWebViewActivity;



import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.example.informationrecognize.main.linkWebView.OpenWebViewActivity.URL_BANNER;

public class HomeViewModel extends ViewModelCommon {
    private MutableLiveData<List<BannerHomeResponse.BannerHome>> listBannerHome = new MutableLiveData<>();
    private MutableLiveData<Boolean> isDismissRefresh = new MutableLiveData<>();

    private Function<Object[], AllHomeResponse> getHomeResponseFun;

    public MutableLiveData<Boolean> getIsDismissRefresh() {
        return isDismissRefresh;
    }

    public void setIsDismissRefresh(MutableLiveData<Boolean> isDismissRefresh) {
        this.isDismissRefresh = isDismissRefresh;
    }

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

        List<Observable<?>> requerts = new ArrayList<>();
        requerts.add(ApiUtils.getDataApi().getListBannerHome()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()));

        getHomeResponseFun = new Function<Object[], AllHomeResponse>() {
            @Override
            public AllHomeResponse apply(Object[] objects) throws Exception {
                AllHomeResponse allHomeResponse = new AllHomeResponse();
                allHomeResponse.setBannerHomeResponse((BannerHomeResponse) objects[0]);
                return allHomeResponse;
            }
        };

        Observer<AllHomeResponse> observer = new Observer<AllHomeResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AllHomeResponse allHomeResponse) {
                if (allHomeResponse.getBannerHomeResponse() != null) {
                    listBannerHome.setValue(allHomeResponse.getBannerHomeResponse().getListBannerHome());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable.zip(requerts, getHomeResponseFun).subscribe(observer);

//        Call<BannerHomeResponse> getListBanner = ApiUtils.getDataApi().getListBannerHome();
//        getListBanner.enqueue(new Callback<BannerHomeResponse>() {
//            @Override
//            public void onResponse(Call<BannerHomeResponse> call, Response<BannerHomeResponse> response) {
//                if (response.body() != null && "0".equals(response.body().getErrorcode())
//                        && response.body().getListBannerHome() != null) {
//                    listBannerHome.setValue(response.body().getListBannerHome());
//                } else {
//                    Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BannerHomeResponse> call, Throwable t) {
//                Toast.makeText(mActivity, mActivity.getString(R.string.da_co_loi_xay_ra), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void linkWebView(String linkWeb) {
        Intent intent = new Intent(mActivity, OpenWebViewActivity.class);
        intent.putExtra(URL_BANNER, linkWeb);
        mActivity.startActivity(intent);
    }
}
