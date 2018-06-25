package com.kuanquan.qudao.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/25.
 *
 */
public class DetailsArticleBean implements Serializable {
    public String id;
    public String headImg;  // 头像
    public String name;  // 名称
    public String content;  // 内容
    public String time;  // 时间
    public String isFabulous;  // 是否点赞
    public String replyNum;  // 回复数
    public int itemType;   // 0 webView  1 分享  2 评论标题  3 评论列表
}
