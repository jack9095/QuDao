package com.example.fly.baselibrary.mvpExample.base;


/**
 * Created by fei.wang on 2017/7/4.
 *
 */
public abstract class BaseModel implements IBaseModel{

    // 回调到presenter的接口
    public OnLoadDataListener mOnLoadDataListener;

    @Override
    public void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        this.mOnLoadDataListener = onLoadDataListener;
    }
}
