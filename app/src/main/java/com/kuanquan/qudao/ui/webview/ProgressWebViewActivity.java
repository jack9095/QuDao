package com.kuanquan.qudao.ui.webview;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fly.baselibrary.widget.ProgressWebView;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.widget.JavaScriptObject;
import com.kuanquan.qudao.widget.MyWebView;

public class ProgressWebViewActivity extends AppCompatActivity implements JavaScriptObject.JsListener {
    private static final String TAG = ProgressWebViewActivity.class.getSimpleName();
    ProgressWebView myWebView;
    private boolean mIsLoading;
    public String mUrl = "https://www.baidu.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_progress);
        myWebView = findViewById(R.id.my_web_view_pro);
        //启用支持javascript
        myWebView.getSettings().setJavaScriptEnabled(true);
        //WebView加载web资源
        myWebView.loadUrl(mUrl);
        //当页面正在加载时，禁止链接的点击事件
//        myWebView.setOnTouchListener(new WebViewTouchListener());
//        myWebView.setWebViewClient(new MyWebViewClient());
//        myWebView.setWebChromeClient(new WebChromeClient());
    }

    private class WebViewTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return !mIsLoading;
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //判断重定向的方式一
//            WebView.HitTestResult hitTestResult = view.getHitTestResult();
//            if(hitTestResult == null) {
//                return false;
//            }
//            if(hitTestResult.getType() == WebView.HitTestResult.UNKNOWN_TYPE) {
//                return false;
//            }

            //判断重定向的方式二
            if(mIsLoading) {
                return false;
            }

            if(url != null && url.startsWith("http")) {
                myWebView.loadUrl(url);
                return true;
            } else {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    view.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mIsLoading = true;
            Log.d(TAG, "onPageStarted");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mIsLoading = false;
            Log.d(TAG, "onPageFinished");
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    }

    @Override
    public void setStyle(String json) {

    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void goToPage(String path, String json) {

    }

    @Override
    public void goLogin() {

    }

    @Override
    public void toPay(String type, String order, String body, String flag) {

    }

    @Override
    public void goShare(String id) {

    }

    @Override
    public void setInfo(String json) {

    }

    @Override
    public void goTitle(String title) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (myWebView.canGoBack()){  // 表示webview二级页面还有没关闭的
            myWebView.goBack();
        }else{
            finish();
        }
    }
}
