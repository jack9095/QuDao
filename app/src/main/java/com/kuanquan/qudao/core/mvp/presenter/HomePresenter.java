package com.kuanquan.qudao.core.mvp.presenter;

import com.example.fly.baselibrary.mvpExample.base.BasePresenter;
import com.example.fly.baselibrary.mvpExample.base.IBaseModel;
import com.kuanquan.qudao.core.mvp.model.HomeModel;
import com.kuanquan.qudao.core.mvp.view.IHomeView;

/**
 * Created by Administrator on 2018/6/11.
 *
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IBaseModel.OnLoadDataListener {

    @Override
    public void initialize() {
        getView().showLoading();
        model.setOnLoadDataListener(this);
    }

    @Override
    protected IBaseModel createModel() {
        return new HomeModel();
    }

    @Override
    public void requestSuccessListener(String requestCode, Object data) {
        getView().showData(data);
    }

    @Override
    public void requestErrorListener(String requestCode, String errorMessage) {

    }
}
