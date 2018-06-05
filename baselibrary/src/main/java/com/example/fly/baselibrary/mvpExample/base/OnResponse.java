package com.example.fly.baselibrary.mvpExample.base;


/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: 网络接口返回的数据接口
 */
public interface OnResponse {

    void success(int requestCode, String url, String data);

    void fail(Exception e);
}
