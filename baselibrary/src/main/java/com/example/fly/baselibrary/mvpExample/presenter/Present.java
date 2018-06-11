package com.example.fly.baselibrary.mvpExample.presenter;

import com.example.fly.baselibrary.mvpExample.base.BasePresenter;
import com.example.fly.baselibrary.mvpExample.base.IBaseModel;
import com.example.fly.baselibrary.mvpExample.model.ModelIml;
import com.example.fly.baselibrary.mvpExample.view.IMainView;

/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: present层
 */
public class Present extends BasePresenter<IMainView> implements IBaseModel.OnLoadDataListener {

    @Override
    public void initialize() {
        getView().showLoading();
        model.setOnLoadDataListener(this);
    }

//    @Override
//    public void getRequestData() {
//        getView().showLoading();
//        model.setOnLoadDataListener(this);
//    }

    @Override
    protected IBaseModel createModel() {
        return new ModelIml();
    }

    @Override
    public void requestSuccessListener(String requestCode, Object data) {
        getView().showData(data);
    }

    @Override
    public void requestErrorListener(String requestCode, String errorMessage) {

    }
}
