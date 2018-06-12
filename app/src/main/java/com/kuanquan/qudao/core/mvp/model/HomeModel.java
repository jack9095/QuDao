package com.kuanquan.qudao.core.mvp.model;

import com.example.fly.baselibrary.mvpExample.base.BaseModel;
import com.example.fly.baselibrary.mvpExample.base.IBaseModel;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 *
 */

public class HomeModel implements IBaseModel{

   private List<HomeBean> lists = new ArrayList<>();
   private List<HomeBeanChild> listChilds = new ArrayList<>();

   public List<HomeBean> getData(){
       HomeBean mHomeBean;
       mHomeBean = new HomeBean();
       mHomeBean.itemType = 0;
       mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
       lists.add(mHomeBean);

       mHomeBean = new HomeBean();
       mHomeBean.itemType = 1;
       HomeBeanChild mHomeBeanChild;
       for (int i = 0; i < 5; i++) {
           mHomeBeanChild = new HomeBeanChild();
           mHomeBeanChild.title = "直播";
           mHomeBeanChild.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
           listChilds.add(mHomeBeanChild);
       }
       mHomeBean.lists = listChilds;
       lists.add(mHomeBean);

       mHomeBean = new HomeBean();
       mHomeBean.itemType = 2;
       mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
       mHomeBean.title = "新三板——真正意义的中国版纳斯达克";
       mHomeBean.content = "明晚7点，飞利浦财物总监介绍投行的酸甜苦辣";
       lists.add(mHomeBean);

       mHomeBean = new HomeBean();
       mHomeBean.itemType = 3;
       HomeBeanChild mHomeBeanChildF;
       listChilds.clear();
       for (int i = 0; i < 7; i++) {
           mHomeBeanChildF = new HomeBeanChild();
           mHomeBeanChildF.title = "新三板——真正意义的中国版纳斯达克";
           mHomeBeanChildF.content = "明晚7点，飞利浦财物总监介绍投行的酸甜苦辣";
           mHomeBeanChildF.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
           listChilds.add(mHomeBeanChildF);
       }
       mHomeBean.lists = listChilds;
       lists.add(mHomeBean);

       mHomeBean = new HomeBean();
       mHomeBean.itemType = 4;
       listChilds.clear();
       for (int i = 0; i < 7; i++) {
           mHomeBean.title = "新三板——真正意义的中国版纳斯达克";
           mHomeBean.content = "明晚7点，飞利浦财物总监介绍投行的酸甜苦辣";
           mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
       }
       lists.add(mHomeBean);

       return lists;
   }

    @Override
    public void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {

    }
}
