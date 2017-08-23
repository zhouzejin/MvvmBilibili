package com.sunny.mvvmbilibili.ui.browser;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sunny.mvvmbilibili.R;
import com.sunny.mvvmbilibili.databinding.ActivityBrowserBinding;
import com.sunny.mvvmbilibili.ui.base.BaseActivity;
import com.sunny.mvvmbilibili.utils.ClipboardUtil;
import com.sunny.mvvmbilibili.utils.ToastUtil;
import com.sunny.mvvmbilibili.utils.factory.DialogFactory;

import javax.inject.Inject;

/**
 * The type Browser activity.
 * Created by Zhou Zein on 2017/8/23.
 */
public class BrowserActivity extends BaseActivity {

    private static final String EXTRA_URL = "activity.browser.extra.URL";
    private static final String EXTRA_TITLE = "activity.browser.extra.TITLE";

    private static final String LABEL_URL = "activity.browser.label.URL";

    private String mUrl;
    private String mTitle;

    private ActivityBrowserBinding mBinding;

    @Inject ClipboardUtil mClipboardUtil;

    public static Intent getStartIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

    @Override
    public void initComponent() {
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_browser);
        setContentView(mBinding.getRoot());

        initToolbar(mBinding.toolbar);
        setWebView(mBinding.webView);
    }

    private void initToolbar(Toolbar toolbar) {
        toolbar.setTitle(mTitle);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 支持JavaScript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true); // 扩大比例的缩放
        webSettings.setSupportZoom(true); // 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true); // 设置出现缩放工具
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 自适应屏幕
        webSettings.setGeolocationEnabled(true);

        // 如果不设置WebViewClient，请求会跳转系统浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgress();
                // 加快页面加载速度，在页面加载完毕后需要设置为false
                view.getSettings().setBlockNetworkImage(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgress();
                view.getSettings().setBlockNetworkImage(false);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                hideProgress();
                String errorHtml = "<html><body><h2>找不到网页</h2></body></html>";
                view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (TextUtils.isEmpty(mTitle) && !TextUtils.isEmpty(title)) {
                    mBinding.toolbar.setTitle(title);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                Dialog dialog = DialogFactory.createSimpleOkErrorDialog(
                        view.getContext(),
                        getString(R.string.app_name),
                        message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        });
                dialog.setCancelable(false);
                dialog.show();
                return true;
            }
        });
        webView.requestFocus(View.FOCUS_DOWN);
        webView.loadUrl(mUrl);
    }

    public void showProgress() {
        mBinding.progressView.setVisibility(View.VISIBLE);
        mBinding.progressView.spin();
        mBinding.webView.setVisibility(View.GONE);
    }

    public void hideProgress() {
        mBinding.progressView.setVisibility(View.GONE);
        mBinding.progressView.stopSpinning();
        mBinding.webView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.webView.onResume();
    }

    @Override
    protected void onPause() {
        mBinding.webView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mBinding.webView.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        WebView webView = mBinding.webView;
        if (webView.canGoBack() && webView.copyBackForwardList().getSize() > 0 && !webView.getUrl()
                .equals(webView.copyBackForwardList().getItemAtIndex(0).getOriginalUrl())) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_share:
                share(mBinding.webView.getTitle(), mBinding.webView.getUrl());
                break;
            case R.id.action_open:
                open(mBinding.webView.getUrl());
                break;
            case R.id.action_copy:
                copy(mBinding.webView.getUrl());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void share(String title, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_hint) + content);
        startActivity(Intent.createChooser(intent, title));
    }

    private void open(String content) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(content));
        startActivity(intent);
    }

    private void copy(String content) {
        mClipboardUtil.setText(LABEL_URL, content);
        ToastUtil.showShort(this, getString(R.string.copied));
    }

}
