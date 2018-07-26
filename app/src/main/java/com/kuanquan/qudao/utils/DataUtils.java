package com.kuanquan.qudao.utils;

import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.bean.HomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/29.
 * 网络请求或者数据工具类
 */
public class DataUtils {

    /**
     * 获取banner数据
     * @return
     */
    public static List<HomeBeanChild> getBannerData(){
        List<HomeBeanChild> listChilds = new ArrayList<>();
        HomeBeanChild banner;
        for (int i = 0; i < 5; i++) {
            banner = new HomeBeanChild();
            banner.title = "趣到";
            banner.image = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            listChilds.add(banner);
        }
        return listChilds;
    }

    /**
     * 获取发现列表数据
     * @return
     */
    public static List<HomeData> getFindData(){
        List<HomeData> lists = new ArrayList<>();
        HomeData mHomeData;

//        mHomeData = new HomeData();
//        mHomeData.itemType = 0;
//        mHomeData.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
//        mHomeData.title = "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
//        mHomeData.content = "主要栏目与内容包括：经济科学新论、经济热点分析、部门经济、地区经济、财政金融研究、学术探讨、会议综述、学术资料、经济体制改革、企业管理、调查与建议、中外学术交流、外国经济理论动态与述评、世界经济、书刊评介等";
//        lists.add(mHomeData);

        for (int i = 0; i < 5; i++) {
            mHomeData = new HomeData();
            mHomeData.head = "发现";   // 表示发现
            mHomeData.isDiscover = "1";   // 表示发现
            mHomeData.itemType = 2;
            mHomeData.title = i + "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
            mHomeData.content = i + "《经济学动态》 · 3211阅读";
            mHomeData.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            lists.add(mHomeData);
        }
        return lists;
    }

    /**
     * 获取列表数据
     * @return
     */
    public static List<HomeData> getData(){
        List<HomeData> lists = new ArrayList<>();
        HomeData mHomeData;
        HomeBeanChild banner;
        for (int i = 0; i < 5; i++) {
            banner = new HomeBeanChild();
            banner.title = "趣到";
            banner.image = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
//            lists.listChilds.add(banner);
        }

//        mHomeData = new HomeData();
//        mHomeData.itemType = 0;
//        mHomeData.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
//        mHomeData.title = "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
//        mHomeData.content = "主要栏目与内容包括：经济科学新论、经济热点分析、部门经济、地区经济、财政金融研究、学术探讨、会议综述、学术资料、经济体制改革、企业管理、调查与建议、中外学术交流、外国经济理论动态与述评、世界经济、书刊评介等";
//        lists.add(mHomeData);

        for (int i = 0; i < 5; i++) {
            mHomeData = new HomeData();
            mHomeData.head = "发现";   // 表示发现
            mHomeData.isDiscover = "1";   // 表示发现
            mHomeData.itemType = 2;
            mHomeData.title = i + "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
            mHomeData.content = i + "《经济学动态》 · 3211阅读";
            mHomeData.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            lists.add(mHomeData);
        }
        return lists;
    }
}
