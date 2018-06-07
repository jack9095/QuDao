package com.kuanquan.qudao.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Map;

public class MyWebView extends WebView {

    private String basicUA;  // 告诉网站数据的特殊字符串头、标识
    private Map<String, String> header;  // WebView加载链接的头部

    public MyWebView(Context context) {
        this(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings settings = getSettings();   // 获取WebSettings
        settings.setBuiltInZoomControls(false); // 进行控制缩放
        settings.setSupportZoom(false);      // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放。
        settings.setJavaScriptEnabled(true); // 如果访问的页面中有JavaScript，则WebView必须设置支持JavaScript，否则显示空白页面
        settings.setMediaPlaybackRequiresUserGesture(true); // 设置WebView是否通过手势触发播放媒体，默认是true，需要手势触发。
        settings.setDisplayZoomControls(true); // 设置WebView使用内置缩放机制时，是否展现在屏幕缩放控件上，默认true，展现在控件上。
        settings.setAllowFileAccess(true); // 设置在WebView内部是否允许访问文件，默认允许访问。
        settings.setSupportMultipleWindows(false); // 设置WebView是否支持多屏窗口，参考WebChromeClient#onCreateWindow，默认false，不支持
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 设置脚本是否允许自动打开弹窗，默认false，不允许
        settings.setDomStorageEnabled(true);  // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        /*  重写缓存被使用到的方法，该方法基于Navigation Type，加载普通的页面，将会检查缓存同时重新验证是否需要加载，
            如果不需要重新加载，将直接从缓存读取数据，允许客户端通过指定LOAD_DEFAULT、
            LOAD_CACHE_ELSE_NETWORK、LOAD_NO_CACHE、LOAD_CACHE_ONLY其中之一重写该行为方法，默认值LOAD_DEFAULT*/
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        /*设置WebView是否使用viewport，当该属性被设置为false时，加载页面的宽度总是适应WebView控件宽度；当被设置为true，当前页面包含viewport属性标签，
        在标签中指定宽度值生效，如果页面不包含viewport标签，无法提供一个宽度值，这个时候该方法将被使用*/
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT > 6) {
            settings.setAppCacheEnabled(true); // 设置Application缓存API是否开启，默认false
            settings.setLoadWithOverviewMode(true); //设置WebView是否使用预览模式加载界面。
        }
        String path = getContext().getFilesDir().getPath();
        settings.setGeolocationEnabled(true);   // 设置是否开启定位功能，默认true，开启定位
        settings.setGeolocationDatabasePath(path); // 设置WebView保存地理位置信息数据路径，指定的路径Application具备写入权限
        getSettings().setDefaultTextEncodingName("UTF-8");
        getSettings().setAllowContentAccess(true); // 设置WebView是否使用其内置的变焦机制，该机制结合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        //图片加载
        if(Build.VERSION.SDK_INT >= 19){
            getSettings().setLoadsImagesAutomatically(true);
        }else {
            getSettings().setLoadsImagesAutomatically(false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); // 设置当一个安全站点企图加载来自一个不安全站点资源时WebView的行为
        }
        this.basicUA = settings.getUserAgentString() + " fly/7.05.6303/7059";
        setBackgroundColor(0);
        initWebViewClient();
        setWebChromeClient(new MyChromeClient());
        try {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setCookie("http://bbs.mobileapi.hupu.com", "_inKanqiuApp=1");
            cookieManager.setCookie("http://bbs.mobileapi.hupu.com", "_kanqiu=1");
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initWebViewClient() {
        CookieManager.getInstance().setAcceptCookie(true);
        setWebViewClient(new MyWebClient());
    }

    // WebChromeClient—–当影响浏览器的事件到来时，就会通过WebChromeClient中的方法回调通知用法。
    public class MyChromeClient extends WebChromeClient {
        // 当html中调用console相关输出的时候，就会通过onConsoleMessage进行通知
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            //保存着当前消息的类型和消息内容返回值：如果返回true时，就表示拦截了console输出，
            // 系统就不再通过console输出出来了，如果返回false则表示没有拦截console输出，调用系统默认处理。
            return true;
        }
    }

    // WebViewClient—–在影响View的事件到来时，会通过WebViewClient中的方法回调通知用户。
    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 超链接监听
            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            if (scheme != null) {
                handleScheme(scheme, url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mMyWebViewCallBack != null) {
                mMyWebViewCallBack.onFinish();
            }
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (mMyWebViewCallBack != null) {
                mMyWebViewCallBack.onError();
            }
        }
    }

    /**
     * 解析网页超链接
     *
     * @param scheme
     * @param url
     */
    private void handleScheme(String scheme, String url) {
        if (scheme != null) {
            if ("browser".equalsIgnoreCase(scheme)
                    || "http".equalsIgnoreCase(scheme)
                    || "https".equalsIgnoreCase(scheme)) {

                // 跳转页面
            }
        }
    }

    // 它是一个特殊字符串头，是一种向访问网站提供你所使用的浏览器类型及版本、操作系统及版本、浏览器内核、等信息的标识,可以告诉网站你是APP
    private void setUA(int i) {
        if (this.basicUA != null) {
            getSettings().setUserAgentString(this.basicUA + " isp/" + i + " network/" + i);
        }
    }

    // WebView加载连接
    @Override
    public void loadUrl(String url) {
        setUA(-1);
        if (header == null) {
            header = new HashMap<>();
            header.put("Accept-Encoding", "gzip");
        }
        super.loadUrl(url, header);
    }

    private MyWebViewCallBack mMyWebViewCallBack;

    public void setMyWebViewCallBack(MyWebViewCallBack mMyWebViewCallBack) {
        this.mMyWebViewCallBack = mMyWebViewCallBack;
    }

    // 监听H5页面状态的回调
    public interface MyWebViewCallBack {

        void onFinish();   // 页面加载完成

        void onUpdatePager(int page, int total);  // 更新页面

        void onError();    // 页面加载错误
    }

    /**
     * Webview滑动监听的接口，在activity/fragment/view 中实现这个接口
     */
    public interface OnScrollChangedCallback {
        void onScroll(int dx, int dy, int y, int oldy);
    }

    // 声明滚动监听成员变量
    private OnScrollChangedCallback mOnScrollChangedCallback;

    // 重写系统的滚动监听
    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l - oldl, t - oldt, t, oldt);
        }
    }

    // 获取监听接口
    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    // 设置监听接口
    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }
}
