package com.example.fly.baselibrary.mvpExample.base;

import java.lang.ref.WeakReference;

/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: T  对应着Activity(fragment) 的UI抽象接口  视图
 */
public abstract class BasePresenter<T> {
    /**
     * 持有model接口的引用
     */
    protected IBaseModel  model = createModel();

    /**
     * 持有UI接口的弱引用
     */
    protected WeakReference<T> mViewRef;

    /**
     * 将需要关联的view层与presenter层建立关系
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 如果Activity、Fragment等View控件销毁时调用该方法，解除他们之间的关联,避免内存泄漏
     */
    public void deleteAttach(){
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * 判断是否与View建立了关联
     * @return true 有关联   false 没关联
     */
    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 持有视图层 UI接口的引用
     * @return V
     */
    public T getView(){
        if(mViewRef != null){
            return mViewRef.get();
        }
        return  null;
    }

    public abstract void initialize();      // 初始化方法

//    public abstract void getRequestData();  // 获取数据（本地或网络）

    protected abstract IBaseModel createModel();
}
