package com.example.fly.baselibrary.mvpEeventBus;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.fly.baselibrary.BaseApplication;
import com.example.fly.baselibrary.utils.base.ACache;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 创建人：fei.wang
 */
public abstract class CommonFragment<T extends BasePresenter> extends Fragment {

    protected Context context;
    protected View view;
    protected ACache mACache;

    protected T mPresent;
//    protected LinearLayoutManager mLinearLayoutManager;
//    protected MyProgressDialog md;

    /**
     * 子类实现具体的构建过程
     *
     * @return
     */
    protected abstract T createPresent();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
//        if (md == null) {
//            md = new MyProgressDialog(context, R.style.MyProgressDialog);
//        }
//        md.setMessage("正在加载！");
        mACache = BaseApplication.getAppContext().mACache;
        mPresent = createPresent();
        mPresent.initialize();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

//    protected void setRecyclerViewLayout(RecyclerView mRecyclerView) {
//        mLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        mRecyclerView.smoothScrollBy(0, 0);
//    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initView(inflater, container);
        return view;
    }

    @Nullable
    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndEvents();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    protected <T extends View> T genericFindViewById(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    protected abstract void initData(Bundle savedInstanceState);

    /**
     * is bind eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * when event comming
     *
     * @param eventCenter
     */
    @Subscribe
    public abstract void onEventMainThread(EventCenter eventCenter);

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz, String key, int value) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz, String key, String value) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    protected void setWebView(WebView webview) {
        if (webview != null) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setBuiltInZoomControls(true);
            webview.getSettings().setDisplayZoomControls(false);
            webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //取消滚动条白边效果
            webview.setWebChromeClient(new WebChromeClient());
            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setDefaultTextEncodingName("UTF-8");
            webview.getSettings().setBlockNetworkImage(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webview.getSettings().setMixedContentMode(webview.getSettings()
                        .MIXED_CONTENT_ALWAYS_ALLOW);  //注意安卓5.0以上的权限
            }
//            webview.loadDataWithBaseURL(null, getNewContent(content), "text/html", "UTF-8", null);
        }
    }

    /**
     * show toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void postEventBus(String code) {
        EventBus.getDefault().post(new EventCenter<Object>(code));
    }

    public void postEventBusSticky(String code) {
        EventBus.getDefault().postSticky(new EventCenter<Object>(code));
    }

    public void postEventBusSticky(String code, Object obj) {
        EventBus.getDefault().postSticky(new EventCenter<Object>(code, obj));
    }

    public void postEventBus(String code, Object obj) {
        EventBus.getDefault().post(new EventCenter<Object>(code, obj));
    }
}
