package com.kuanquan.qudao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.kuanquan.qudao.R;

/**
 * 浏览器Layout,封装以及初始化WebView
 */
public class BrowserLayout extends LinearLayout {

    private Context mContext = null;
    private WebView mWebView = null;

    private ProgressBar mProgressBar = null;

    private String mLoadUrl = "";

    public BrowserLayout(Context context) {
        this(context, null);
    }

    public BrowserLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        mProgressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progress_horizontal, null);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);
        addView(mProgressBar, LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 5, getResources().getDisplayMetrics()));

        mWebView = new MyWebView(context);

        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        addView(mWebView, lps);
    }

    public void setWebClient(WebViewClient webClient) {

        if (mWebView != null) {
            mWebView.setWebViewClient(webClient);
        }
    }

    public void setChromeClient(WebChromeClient chromeClient) {
        if (mWebView != null) {
            mWebView.setWebChromeClient(chromeClient);
        }
    }

    /**
     * 设置加载进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (mProgressBar != null) {
            if (progress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                mProgressBar.setVisibility(VISIBLE);
                mProgressBar.setProgress(progress);
            }
        }
    }

    /**
     * 设置当前网页地址
     *
     * @param url
     */
    public void setLoadUrl(String url) {
        mLoadUrl = url;
    }

    public void destory() {
        //如此才能真正清除，不会导致内存泄漏
        if (mWebView != null) {
            //加载空内容
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            mWebView.clearHistory();
            mWebView.removeAllViews();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    /**
     * 刷新网页
     *
     * @param url
     */
    public void refresh(String url) {
        mWebView.reload();
    }

    /**
     * 是否能回退
     *
     * @return
     */
    public boolean canGoBack() {
        return null != mWebView ? mWebView.canGoBack() : false;
    }

    /**
     * 是否能前进
     *
     * @return
     */
    public boolean canGoForward() {
        return null != mWebView ? mWebView.canGoForward() : false;
    }


    /**
     * 回退
     */
    public void goBack() {
        if (null != mWebView) {
            mWebView.goBack();
        }
    }

    /**
     * 前进
     */
    public void goForward() {
        if (null != mWebView) {
            mWebView.goForward();
        }
    }

    public WebView getWebView() {
        return mWebView;
    }

}
