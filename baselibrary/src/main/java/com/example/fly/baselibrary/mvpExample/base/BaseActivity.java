package com.example.fly.baselibrary.mvpExample.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;

import com.example.fly.baselibrary.mvpEeventBus.EventCenter;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: V  对应着Activity 的UI抽象接口  视图
 */
public abstract  class BaseActivity<V,T extends BasePresenter<V>> extends Activity {
    protected  T mPresent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent = createPresent();
        mPresent.attachView((V) this);
        mPresent.initialize();

        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        mPresent.deleteAttach();
        super.onDestroy();

    }

    protected void addOnClickListeners(View.OnClickListener listener, @IdRes int... ids) {
        if (ids != null) {
            for (@IdRes int id : ids) {
                findViewById(id).setOnClickListener(listener);
            }
        }
    }

    /**
     * 子类实现具体的构建过程
     * @return
     */
    protected abstract T createPresent() ;

    /**
     * is bind eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
