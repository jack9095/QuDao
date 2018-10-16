package com.kuanquan.qudao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class LiveBean implements Serializable{

    public int itemType;

    public String imageUrl;
    public String title;
    public String content;
    public String id;
    public String number;
    public String time;
    public String headTeacherImage;
    public int type; // 1 预告 2 回放  3 专题列表最后一个

    public List<LiveBean> listLives = new ArrayList<>(); // 直播预告
    public List<LiveBean> listSpecials = new ArrayList<>(); // 专题
    public List<LiveBean> listBacks = new ArrayList<>(); // 回放

//    public String imageUrlRight;
//    public String titleRight;
//    public String contentRight;
//    public String idRight;
//    public String numberRight;
//    public String timeRight;
//    public String headTeacherImageRight;

}
