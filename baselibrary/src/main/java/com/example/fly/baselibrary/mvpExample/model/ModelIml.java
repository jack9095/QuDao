package com.example.fly.baselibrary.mvpExample.model;


/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: 模型层
 */
public class ModelIml implements IModel {
    @Override
    public void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        onLoadDataListener.requestSuccessListener("","上海");
    }
}
