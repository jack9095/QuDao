package com.kuanquan.qudao.bean;

import java.io.Serializable;

/**
 * Created by fei.wang on 2018/6/28.
 *
 */
public class HomeData implements Serializable {

    public String id;
    public String title;
    public String content;
    public String isDiscover;  // 1 表示显示发现的标题栏   2 不显示
    public String image;
    public String head;  // 头部
    public int position;  // 角标

    public int itemType;   // 0 banner  1 (5个item布局)  2 (直播公开课布局)  3 直播布局 (水平滚动) 4 (发现布局)

    public String action_type  ;
    public String action  ;


    public int like_num  ;    // 点赞数
    public int dede_aid  ;
    public String arcurl  ;    // 链接
    public String share_url  ; // 分享链接
    public String description  ;  // 内容
    public int click_num  ;  // 点击次数
    public String time  ;   // 时间

}
