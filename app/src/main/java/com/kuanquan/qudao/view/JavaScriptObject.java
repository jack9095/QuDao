package com.kuanquan.qudao.view;

import android.webkit.JavascriptInterface;

import com.example.fly.baselibrary.utils.base.GsonUtils;
import com.example.fly.myapplication.bean.JsBean;

import java.util.HashMap;

/**
 * 通用供H5页面Js调用的方法
 */
public class JavaScriptObject {

    public JavaScriptObject(JsListener mJsListener) {
        this.mJsListener = mJsListener;
    }

    @JavascriptInterface
    public void setStyle(String json) {   // 获取显示样式
        mJsListener.setStyle(json);
    }

    @JavascriptInterface
    public String getInfo() {  // js从APP获取信息
        HashMap<String,String> map = new HashMap<>();
        map.put("uuid","1209875643");
        map.put("userId","1209875643");
        map.put("appType","1");
        map.put("osType","2");
        return GsonUtils.toJson(map);
    }

    @JavascriptInterface
    public void goBack() {  // 关闭H5页面
        mJsListener.goBack();
    }

    @JavascriptInterface  // 去跳转的路径方法
    public void goToPage(String path,String json){  // 跳转到指定页面 path （包名+类名）
        mJsListener.goToPage(path,json);
    }

    @JavascriptInterface
    public void goLogin(){   // 去登陆
        mJsListener.goLogin();
    }

    @JavascriptInterface
    public void toPay(String type,String order,String body,String flag) {  // 跳转到支付页面
        mJsListener.toPay(type,order,body,flag);
    }

    @JavascriptInterface
    public void goShare(String id) {  // 调用原生分享
        mJsListener.goShare(id);
    }

    @JavascriptInterface   // APP从js获取信息
    public void setInfo(String json) { // APP从js获取信息，返回的是json字符串
        mJsListener.setInfo(json);
    }

    @JavascriptInterface
    public void goTitle(String title) {
        mJsListener.goTitle(title);
    }


    private JsListener mJsListener;
    public interface JsListener{
//        void jsListener(JsBean obj);
        void setStyle(String json);
        void goBack();
        void goToPage(String path, String json);
        void goLogin();
        void toPay(String type, String order, String body, String flag);
        void goShare(String id);
        void setInfo(String json);
        void goTitle(String title);
    }
}
