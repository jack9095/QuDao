package com.kuanquan.qudao.utils;

import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.bean.LiveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaodun on 2018/10/16.
 */

public class LiveDataUtil {


    public static List<LiveBean> getData() {
        List<LiveBean> lists = new ArrayList<>();
        lists.clear();
//        List<LiveBean> listChilds = new ArrayList<>();

        LiveBean liveBean;

        liveBean = new LiveBean();
        liveBean.itemType = 0;
        liveBean.title = "今日直播";
        lists.add(liveBean);

        for (int i = 0; i < 1; i++) {
            liveBean = new LiveBean();
            liveBean.itemType = 1;
            liveBean.imageUrl = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            liveBean.headTeacherImage = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            liveBean.title = "锄禾日当午";
            liveBean.content = "汗滴禾下土";
            liveBean.time = "14:30 开始";
            liveBean.number = "145 人预约";
            liveBean.id = "";
            lists.add(liveBean);
        }

        liveBean = new LiveBean();
        liveBean.itemType = 0;
        liveBean.title = "预告推荐";
        lists.add(liveBean);

        for (int i = 0; i < 3; i++) {
            liveBean = new LiveBean();
            liveBean.type = 1;
            liveBean.itemType = 2;

            if (i % 2 == 0) {
                LiveBean mLiveBean;
//                listChilds.clear();
                for (int j = 0; j < 2; j++) {
                    mLiveBean = new LiveBean();
                    if (j == 0) {
                        mLiveBean.imageUrl = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                        mLiveBean.headTeacherImage = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                        mLiveBean.title = "锄禾日当午   预告推荐" + i;
                        mLiveBean.content = "汗滴禾下土   预告推荐";
                        mLiveBean.time = "14:30 开始";
                        mLiveBean.number = "145 人预约";
                        mLiveBean.id = "";
                        liveBean.listLives.add(mLiveBean);
//                        listChilds.add(mLiveBean);
//                        liveBean.listLives.addAll(listChilds);
                    } else {
                        if (i + 1 < 3) {
                            mLiveBean.imageUrl = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                            mLiveBean.headTeacherImage = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                            mLiveBean.title = "锄禾日当午   预告推荐" + (i + 1);
                            mLiveBean.content = "汗滴禾下土   预告推荐";
                            mLiveBean.time = "14:30 开始";
                            mLiveBean.number = "145 人预约";
                            mLiveBean.id = "";
                            liveBean.listLives.add(mLiveBean);
                        }
                    }
                }
                lists.add(liveBean);
            }
        }

        liveBean = new LiveBean();
        liveBean.itemType = 0;
        liveBean.title = "热点专题";
        liveBean.content = "为您汇总特色专题";
        lists.add(liveBean);

        liveBean = new LiveBean();
        liveBean.itemType = 3;
        LiveBean mLiveBeanF;
//        listChilds.clear();
        for (int i = 0; i < 4; i++) {
            mLiveBeanF = new LiveBean();
            mLiveBeanF.imageUrl = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            mLiveBeanF.headTeacherImage = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            mLiveBeanF.title = "锄禾日当午";
            mLiveBeanF.content = "汗滴禾下土";
            mLiveBeanF.time = "14:30 开始";
            mLiveBeanF.number = "145 人预约";
            mLiveBeanF.id = "";

            if (i == 4-1) {
                mLiveBeanF.type = 3;
            }

            liveBean.listSpecials.add(mLiveBeanF);
//            listChilds.add(mLiveBeanF);
//            liveBean.lists.addAll(listChilds);
        }
        lists.add(liveBean);

        liveBean = new LiveBean();
        liveBean.itemType = 0;
        liveBean.title = "精彩回放";
        lists.add(liveBean);


        // 9
        for (int i = 0; i < 6; i++) {
            liveBean = new LiveBean();
            liveBean.itemType = 2;
            liveBean.type = 2;

            LiveBean mLiveBean;
//                listChilds.clear();
            if (i % 2 == 0) {
                for (int j = 0; j < 2; j++) {
                    mLiveBean = new LiveBean();
                    if (j == 0) {
                        LogUtil.e("精彩回放 i = ", i);
                        mLiveBean.imageUrl = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                        mLiveBean.headTeacherImage = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                        mLiveBean.title = "锄禾日当午   精彩回放" + i;
                        mLiveBean.content = "汗滴禾下土   精彩回放";
                        mLiveBean.time = "14:30 开始";
                        mLiveBean.number = "145 人预约";
                        mLiveBean.id = "";
                        liveBean.listBacks.add(mLiveBean);
//                        listChilds.add(mLiveBean);
//                        liveBean.lists.addAll(listChilds);
                    } else {
                        if (i + 1 < 6) {
                            LogUtil.e("精彩回放 i + 1 = ", i + 1);
                            mLiveBean.imageUrl = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                            mLiveBean.headTeacherImage = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
                            mLiveBean.title = "锄禾日当午   精彩回放" + (i + 1);
                            mLiveBean.content = "汗滴禾下土   精彩回放";
                            mLiveBean.time = "14:30 开始";
                            mLiveBean.number = "145 人预约";
                            mLiveBean.id = "";
                            liveBean.listBacks.add(mLiveBean);
                        }

                    }
                }

                lists.add(liveBean);
            }
        }

        LogUtil.e("adapter集合大小 = ",lists.size());
        return lists;
    }

}
