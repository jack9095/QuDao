package com.kuanquan.qudao.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/11.
 */

public class HomeBean implements Serializable {
    public String title;
    public String content;
    public String image;
    public String isDiscover;  // 1 表示显示发现的标题栏   2 不显示
}
