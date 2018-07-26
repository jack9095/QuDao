package com.kuanquan.qudao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class HomeBeanChild implements Serializable {
    public String title;
    public String content;
    public String id;
    public String type;  // 1 显示右边的空包view

    public String ext  ;
    public String image  ;   // banner 图片
    public String action_type  ;  // 跳转类型 native h5 weex
    public String action  ;

    public List<HomeBeanChild> tabItems = new ArrayList<>();   // 5个item

}
