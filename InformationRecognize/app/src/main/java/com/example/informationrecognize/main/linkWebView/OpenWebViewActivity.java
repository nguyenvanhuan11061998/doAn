package com.example.informationrecognize.main.linkWebView;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.informationrecognize.R;
import com.example.informationrecognize.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class OpenWebViewActivity extends BaseActivity {
    public static String URL_BANNER = "URL_BANNER";

    @BindView(R.id.wv_web_view)
    WebView webView;

    private String urlBanner = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initAct() {
        Intent intent = getIntent();
        if (intent != null) {
            urlBanner = intent.getStringExtra(URL_BANNER);
        }
        showWebView();
    }

    private void showWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(urlBanner);
    }

    @OnClick({R.id.img_back})
    void onClick (View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

}
