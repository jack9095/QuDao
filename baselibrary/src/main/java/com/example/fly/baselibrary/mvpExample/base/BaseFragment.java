package com.example.fly.baselibrary.mvpExample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fly.baselibrary.BaseApplication;
import com.example.fly.baselibrary.mvpEeventBus.EventCenter;
import com.example.fly.baselibrary.utils.base.ACache;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: V  对应着fragment 的UI抽象接口  视图
 */
public abstract  class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {
    protected  T mPresent;
    protected ACache mACache;
    protected Context context;
    protected View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mACache = BaseApplication.getAppContext().mACache;
        context = this.getActivity();
        mPresent = createPresent();
        mPresent.attachView((V) this);
        mPresent.initialize();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = initLayout(inflater, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        mPresent.deleteAttach();
        super.onDestroy();

    }

    /**
     * 子类实现具体的构建过程
     * @return
     */
    protected abstract T createPresent() ;

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract View initLayout(LayoutInflater inflater, ViewGroup container);

    /**
     * init all views and add events
     */
    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

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
}
