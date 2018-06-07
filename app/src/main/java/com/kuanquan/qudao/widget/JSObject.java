package com.kuanquan.qudao.widget;

import android.webkit.JavascriptInterface;

/**
 * 通用供H5页面Js调用的方法
 */
public class JSObject {

    public JSObject(JsListener mJsListener) {
        this.mJsListener = mJsListener;
    }

    // 必须加上这行注解，否则在sdk17版本以上Android系统无法被调用
    @JavascriptInterface
    public String getappUserUUID() {
       return "uuid";
    }

    // 客户端类型(1.学生端知到  2:教师端  3:会员端  4:学生端智慧树网
    @JavascriptInterface
    public String getappClientTypeApp() {   // 获取显示样式
        return "1";
    }

    // 系统类型 1:iOS  2:android
    @JavascriptInterface
    public String getappSystemTypeApp() {  // js从APP获取登录信息
        return "2";
    }

    @JavascriptInterface
    public void finish() {  // 关闭H5页面
        mJsListener.goBack();
    }

    @JavascriptInterface
    public void goToPage(String path,String json){  // 跳转到指定页面 path （包名+类名）
        mJsListener.goToPage(path,json);
    }

    @JavascriptInterface
    public void goLogin(){   // 去登陆
        mJsListener.goLogin();
    }

    @JavascriptInterface
    public void toPay(String type,String order) {  // 跳转到会员支付页面
        mJsListener.toPay(type,order);
    }

    @JavascriptInterface
    public void goShare(String id) {  // 调用原生分享
        mJsListener.goShare(id);
    }

    @JavascriptInterface
    public void setInfo(String json) { // APP从js获取信息，返回的是json字符串
        mJsListener.setInfo(json);
    }

    private JsListener mJsListener;
    public interface JsListener{
//        void jsListener(JsBean obj);
        void setStyle();
        void goBack();
        void goToPage(String path, String json);
        void goLogin();
        void toPay(String type, String order);
        void goShare(String id);
        void setInfo(String json);
    }
}
