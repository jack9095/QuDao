package com.example.db.bean;

import android.content.ContentValues;
import com.example.db.data.DbConfig;
import java.io.Serializable;

/**
 * Created by fei.wang on 2018/8/7.
 */

public class Recommend implements Serializable {
    public String id;         // id
    public String title;      // 标题
    public String content;    // 内容
    public String image;      // 图片
    public String num;        // 阅读数
    public String time;       // 时间

    // 根据主键更新数据
    public ContentValues getContentValues(Recommend recommend) {
        ContentValues values = new ContentValues();
        values.put(DbConfig.ITEM_ID, recommend.id);
        values.put(DbConfig.TITLE, recommend.title);
        values.put(DbConfig.CONTENT, recommend.content);
        values.put(DbConfig.IMAGE, recommend.image);
        values.put(DbConfig.NUM, recommend.num);
        values.put(DbConfig.TIME, recommend.time);
        return values;
    }
}
