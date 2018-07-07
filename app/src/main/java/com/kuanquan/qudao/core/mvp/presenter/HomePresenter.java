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
        mHomeBean.itemType = 1;
        HomeBeanChild banner;
        for (int i = 0; i < 5; i++) {
            banner = new HomeBeanChild();
            banner.title = "趣到";
            banner.image = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            listChilds.add(banner);
        }
        mHomeBean.lists.addAll(listChilds);
        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 2;
        listChilds.clear();
        HomeBeanChild mHomeBeanChild;
        for (int i = 0; i < 5; i++) {
            mHomeBeanChild = new HomeBeanChild();
            mHomeBeanChild.title = "趣到";
            mHomeBeanChild.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            listChilds.add(mHomeBeanChild);
        }
        LogUtil.e(listChilds.size());
        mHomeBean.lists.addAll(listChilds);
        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 3;
        mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
        mHomeBean.title = "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
        mHomeBean.content = "主要栏目与内容包括：经济科学新论、经济热点分析、部门经济、地区经济、财政金融研究、学术探讨、会议综述、学术资料、经济体制改革、企业管理、调查与建议、中外学术交流、外国经济理论动态与述评、世界经济、书刊评介等";
        lists.add(mHomeBean);

        // 水平滑动
//        mHomeBean = new HomeBean();
//        mHomeBean.itemType = 3;
//        HomeBeanChild mHomeBeanChildF;
//        listChilds.clear();
//        for (int i = 0; i < 7; i++) {
//            mHomeBeanChildF = new HomeBeanChild();
//            if (i == 6) {
//                mHomeBeanChildF.type = "1";
//            }
//            mHomeBeanChildF.title = "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
//            mHomeBeanChildF.content = "主要栏目与内容包括：经济科学新论、经济热点分析、部门经济、地区经济、财政金融研究、学术探讨、会议综述、学术资料、经济体制改革、企业管理、调查与建议、中外学术交流、外国经济理论动态与述评、世界经济、书刊评介等";
//            mHomeBeanChildF.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
//            listChilds.add(mHomeBeanChildF);
//        }
//        mHomeBean.lists.addAll(listChilds);
//        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 4;
        lists.add(mHomeBean);


        // 发现
        for (int i = 0; i < 3; i++) {
            mHomeBean = new HomeBean();
            mHomeBean.isDiscover = "1";   // 表示发现
            mHomeBean.itemType = 5;
            mHomeBean.position = lists.size();
            mHomeBean.title = i + "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
            mHomeBean.content = i + "《经济学动态》 · 3211阅读";
            mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            lists.add(mHomeBean);
        }

        getView().showData(lists);
    }
}
