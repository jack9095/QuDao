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
        void requestSuccessListener(String requestCode, Object data);  // 参数一：请求码  参数二：接口返回数据
        void requestErrorListener(String requestCode, String errorMessage); // 错误信息
    }
}
