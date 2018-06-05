package com.example.fly.baselibrary.mvpExample.base;



/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des:
 */
public interface IBaseModel {

    void setOnLoadDataListener(OnLoadDataListener onLoadDataListener);

    // 数据回调监听(presenter 实现)
    interface OnLoadDataListener {
        /**
         * 把数据回调到presenter
         *
         * @param data
         */
        void onComplete(Object data);
    }
}
