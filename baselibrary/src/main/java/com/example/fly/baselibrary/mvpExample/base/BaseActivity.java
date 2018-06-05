package com.example.fly.baselibrary.mvpExample.base;

import android.app.Activity;
import android.os.Bundle;

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
    }

    @Override
    protected void onDestroy() {
        mPresent.deleteAttach();
        super.onDestroy();

    }

    /**
     * 子类实现具体的构建过程
     * @return
     */
    protected abstract T createPresent() ;
}
