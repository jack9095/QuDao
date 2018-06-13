package com.kuanquan.qudao.core.mvp.presenter;

import com.example.fly.baselibrary.mvpExample.base.BasePresenter;
import com.example.fly.baselibrary.mvpExample.base.IBaseModel;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.core.mvp.model.HomeModel;
import com.kuanquan.qudao.core.mvp.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 *
 */

public class HomePresenter extends BasePresenter<IHomeView> implements IBaseModel.OnLoadDataListener {

    @Override
    public void initialize() {
        getView().showLoading();
        model.setOnLoadDataListener(this);
        getData();
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

    private List<HomeBean> lists = new ArrayList<>();
    private List<HomeBeanChild> listChilds = new ArrayList<>();

    public void getData(){
        HomeBean mHomeBean;
        mHomeBean = new HomeBean();
        mHomeBean.itemType = 0;
        mHomeBean.position = 0;
        HomeBeanChild banner;
        for (int i = 0; i < 5; i++) {
            banner = new HomeBeanChild();
            banner.title = "直播";
            banner.image = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            listChilds.add(banner);
        }
        mHomeBean.lists.addAll(listChilds);
        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 1;
        mHomeBean.position = 1;
        listChilds.clear();
        HomeBeanChild mHomeBeanChild;
        for (int i = 0; i < 5; i++) {
            mHomeBeanChild = new HomeBeanChild();
            mHomeBeanChild.title = "直播";
            mHomeBeanChild.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            listChilds.add(mHomeBeanChild);
        }
        LogUtil.e(listChilds.size());
        mHomeBean.lists.addAll(listChilds);
        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 2;
        mHomeBean.position = 2;
        mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
        mHomeBean.title = "新三板——真正意义的中国版纳斯达克,飞利浦财物总监介绍投行的酸甜苦辣";
        mHomeBean.content = "明晚7点，飞利浦财物总监介绍投行的酸甜苦辣,飞利浦财物总监介绍投行的酸甜苦辣";
        lists.add(mHomeBean);

//        mHomeBean = new HomeBean();
//        mHomeBean.itemType = 3;
//        HomeBeanChild mHomeBeanChildF;
//        listChilds.clear();
//        for (int i = 0; i < 7; i++) {
//            mHomeBeanChildF = new HomeBeanChild();
//            if (i == 6) {
//                mHomeBeanChildF.type = "1";
//            }
//            mHomeBeanChildF.title = "新三板——真正意义的中国版纳斯达克,飞利浦财物总监介绍投行的酸甜苦辣";
//            mHomeBeanChildF.content = "明晚7点，飞利浦财物总监介绍投行的酸甜苦辣,飞利浦财物总监介绍投行的酸甜苦辣";
//            mHomeBeanChildF.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
//            listChilds.add(mHomeBeanChildF);
//        }
//        mHomeBean.lists.addAll(listChilds);
//        lists.add(mHomeBean);


        for (int i = 0; i < 7; i++) {
            mHomeBean = new HomeBean();
            if (i == 0) {
                mHomeBean.isDiscover = "1";
            }
            mHomeBean.itemType = 4;
            mHomeBean.position = lists.size();
            mHomeBean.title = i + "新三板——真正意义的中国版纳斯达克";
            mHomeBean.content = i + "财会资讯 · 3211阅读";
            mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            lists.add(mHomeBean);
        }

        getView().showData(lists);
    }
}
