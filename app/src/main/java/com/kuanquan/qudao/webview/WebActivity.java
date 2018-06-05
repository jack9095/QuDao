package com.kuanquan.qudao.webview;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.fly.baselibrary.mvpEeventBus.CommonActivity;
import com.example.fly.baselibrary.mvpEeventBus.EventCenter;
import com.example.fly.myapplication.R;
import com.example.fly.myapplication.app.MyApplication;
import com.example.fly.myapplication.presenter.WebPresenter;
import com.example.fly.myapplication.sonic.SonicRuntimeImpl;
import com.example.fly.myapplication.sonic.SonicSessionClientImpl;
import com.example.fly.myapplication.view.BrowserLayout;
import com.tencent.sonic.sdk.SonicCacheInterceptor;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicConstants;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.tencent.sonic.sdk.SonicSessionConnection;
import com.tencent.sonic.sdk.SonicSessionConnectionInterceptor;
import org.greenrobot.eventbus.Subscribe;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebActivity extends CommonActivity<WebPresenter> implements View.OnClickListener{

    private SonicSession sonicSession;
    private SonicSessionClientImpl sonicSessionClient;
    private BrowserLayout mBrowserLayout;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initEventAndData();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                return false;
            }
        });
        getPackageName();
        getLocalClassName();
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected WebPresenter createPresent() {
        return new WebPresenter();
    }

    @Subscribe
    public void onEventMainThread(EventCenter eventCenter) {
    }

    protected void initEventAndData() {
        findViewById(R.id.iv_toolbar_back).setOnClickListener(this);
        findViewById(R.id.iv_toolbar_refresh).setOnClickListener(this);
        mBrowserLayout = findViewById(R.id.bl_webview);
        initSonic(mPresent.mUrl);
        mBrowserLayout.setWebClient(new MonitorWebClient(mBrowserLayout));
        mBrowserLayout.setChromeClient(new AppCacheWebChromeClient(mBrowserLayout));
        mBrowserLayout.setLoadUrl(mPresent.mUrl);
        mBrowserLayout.setIsShowController(false);
        // webview is ready now, just tell session client to bind
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mBrowserLayout.getWebView());
            sonicSessionClient.clientReady();
        } else { // default mode
            mBrowserLayout.getWebView().loadUrl(mPresent.mUrl);
        }
    }

    private void initSonic(String url) {

        // init sonic engine if necessary, or maybe u can do this when application created
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicConfig build = new SonicConfig.Builder().build();
            SonicEngine.createInstance(new SonicRuntimeImpl(MyApplication.getInstance()), build);
        }

        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        offLineMode(sessionConfigBuilder);

        // create sonic session and run sonic flow
        sonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build());
        if (null != sonicSession) {
            sonicSessionClient = new SonicSessionClientImpl();
            sonicSession.bindClient(sonicSessionClient);
        } else {
            // this only happen when a same sonic session is already running,
            // u can comment following codes to feedback as a default mode.
            // throw new UnknownError("create session fail!");
            Toast.makeText(MyApplication.getInstance(), "create sonic session fail!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 离线模式
     *
     * @param sessionConfigBuilder
     */
    private void offLineMode(SonicSessionConfig.Builder sessionConfigBuilder) {
        // if it's offline pkg mode, we need to intercept the session connection
        sessionConfigBuilder.setCacheInterceptor(new SonicCacheInterceptor(null) {
            @Override
            public String getCacheData(SonicSession session) {
                return null; // offline pkg does not need cache
            }
        });

        sessionConfigBuilder.setConnectionInterceptor(new SonicSessionConnectionInterceptor() {
            @Override
            public SonicSessionConnection getConnection(SonicSession session, Intent intent) {
                return new OfflinePkgSessionConnection(WebActivity.this, session, intent);
            }
        });
    }

    @Override
    protected void onResume() {
        if (mBrowserLayout.getWebView() != null) {
            mBrowserLayout.getWebView().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mBrowserLayout.getWebView() != null) {
            mBrowserLayout.getWebView().onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        mBrowserLayout.destory();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_toolbar_back:
                finish();
                break;
            case R.id.iv_toolbar_refresh:
                Intent intent = new Intent();
//                intent.setClassName(this, "com.example.fly.myapplication.service.ServiceActivity");
                intent.setClassName(this, "com.example.fly.baselibrary.MainActivity");
                startActivity(intent);
                break;
        }
    }

    private class MonitorWebClient extends WebViewClient {  // 超链接监听

        private BrowserLayout mBrowserLayout;

        public MonitorWebClient(BrowserLayout browserLayout) {
            mBrowserLayout = browserLayout;
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                url = request.getUrl().toString().toLowerCase();
            }
            if (!mPresent.hasAd(url)) {  // 不需要过滤广告字集
                return shouldInterceptRequest(view, url);
            } else {
                return new WebResourceResponse(null, null, null);
            }
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (sonicSession != null) {
                //step 6: Call sessionClient.requestResource when host allow the application
                // to return the local data .
                return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
            }
            return null;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //错误提示
            Toast toast = Toast.makeText(getBaseContext(), "Oh no! " + description + " " + failingUrl, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
            toast.show();
            //错误处理
            try {
                mBrowserLayout.getWebView().stopLoading();
            } catch (Exception ignored) {
            }
            try {
                mBrowserLayout.getWebView().loadUrl("about:blank");
            } catch (Exception ignored) {
            }
            if (mBrowserLayout.getWebView().canGoBack()) {
                mBrowserLayout.getWebView().goBack();
            }
            //  super.onReceivedError(view, errorCode, description, failingUrl);
        }

        //当load有ssl层的https页面时，如果这个网站的安全证书在Android无法得到认证，WebView就会变成一个空白页，而并不会像PC浏览器中那样跳出一个风险提示框
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            //忽略证书的错误继续Load页面内容
            handler.proceed();
            //handler.cancel(); // Android默认的处理方式
            //handleMessage(Message msg); // 进行其他处理
            //  super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mBrowserLayout.setLoadUrl(url);
            //页面加载完毕再去加载图片
            if (!view.getSettings().getLoadsImagesAutomatically()) {
                view.getSettings().setLoadsImagesAutomatically(true);
            }
            if (sonicSession != null) {
                sonicSession.getSessionClient().pageFinish(url);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }

    private class AppCacheWebChromeClient extends WebChromeClient {

        private BrowserLayout mBrowserLayout;

        public AppCacheWebChromeClient(BrowserLayout browserLayout) {
            mBrowserLayout = browserLayout;
        }

        @Override
        public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
            //    Log.e(APP_CACHE, "onReachedMaxAppCacheSize reached, increasing space: " + spaceNeeded);
            quotaUpdater.updateQuota(spaceNeeded * 2);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mBrowserLayout.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }


    /**
     * 离线模式
     */
    private static class OfflinePkgSessionConnection extends SonicSessionConnection {

        private final WeakReference<Context> context;

        public OfflinePkgSessionConnection(Context context, SonicSession session, Intent intent) {
            super(session, intent);
            this.context = new WeakReference<Context>(context);
        }

        @Override
        protected int internalConnect() {
            Context ctx = context.get();
            if (null != ctx) {
                try {
                    InputStream offlineHtmlInputStream = ctx.getAssets().open("sonic-demo-index.html");
                    responseStream = new BufferedInputStream(offlineHtmlInputStream);
                    return SonicConstants.ERROR_CODE_SUCCESS;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            return SonicConstants.ERROR_CODE_UNKNOWN;
        }

        @Override
        protected BufferedInputStream internalGetResponseStream() {
            return responseStream;
        }

        @Override
        public void disconnect() {
            if (null != responseStream) {
                try {
                    responseStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getResponseCode() {
            return 200;
        }

        @Override
        public Map<String, List<String>> getResponseHeaderFields() {
            return new HashMap<>(0);
        }

        @Override
        public String getResponseHeaderField(String key) {
            return "";
        }
    }
}
