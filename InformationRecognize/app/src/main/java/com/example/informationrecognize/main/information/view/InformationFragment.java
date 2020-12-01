package com.example.informationrecognize.main.information.view;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseFragment;

import butterknife.BindView;

public class InformationFragment extends BaseFragment {
    public static String URL_INFO = "https://tlus.edu.vn/thong-bao-thay-doi-trong-so-diem-qua-trinh-mon-hoc-ket-cau-be-tong-cot-thep/";

    @BindView(R.id.wv_info)
    WebView infoWebView;

    public static volatile InformationFragment fInstance;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initFragment() {
        WebSettings webSettings = infoWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        infoWebView.loadUrl(URL_INFO);
    }

    public static InformationFragment getInstance() {
        if (fInstance == null) {
            fInstance = new InformationFragment();
        }
        return fInstance;
    }
}