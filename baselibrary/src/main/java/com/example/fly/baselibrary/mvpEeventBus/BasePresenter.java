package com.example.fly.baselibrary.mvpEeventBus;


import com.example.fly.baselibrary.BaseApplication;
import com.example.fly.baselibrary.utils.base.ACache;

import org.greenrobot.eventbus.EventBus;

/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: T  对应着Activity 的UI抽象接口  视图
 */
public abstract class BasePresenter<T extends BaseModel> {

    protected  T mModel;
    protected ACache mACache;

    /**
     * 子类实现具体的构建过程
     * @return
     */
    protected abstract T createModel();

    protected void initModel(){
        mModel = createModel();
        if (mModel != null) {
            mModel.initHandler();
        }
        mACache = BaseApplication.getAppContext().mACache;
    }

    public abstract void initialize();      // 初始化方法

    public void postEventBus(String code){
        EventBus.getDefault().post(new EventCenter<Object>(code));
    }

    public void postEventBusSticky(String code){
        EventBus.getDefault().postSticky(new EventCenter<Object>(code));
    }

    public void postEventBus(String code,Object obj){
        EventBus.getDefault().post(new EventCenter<Object>(code,obj));
    }

}
