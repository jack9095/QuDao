package com.kuanquan.qudao.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fly.myapplication.R;
import com.example.fly.myapplication.view.JavaScriptObject;
import com.example.fly.myapplication.view.MyWebView;

public class WebViewActivity extends AppCompatActivity implements JavaScriptObject.JsListener {
    MyWebView myWebView;
    public String mUrl = "https://www.baidu.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        myWebView = findViewById(R.id.my_web_view);
        myWebView.addJavascriptInterface(new JavaScriptObject(this), "jsObj");
        myWebView.loadUrl(mUrl);
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
}
