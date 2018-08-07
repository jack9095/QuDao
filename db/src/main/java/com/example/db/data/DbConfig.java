package com.example.db.data;

/**
 * Created by fei.wang on 2018/8/6.
 * 数据库配置
 */
public interface DbConfig {
    int DATABASE_VERSION = 1;  // 数据库版本

    String DATABASE_NAME = "FlyDB";  // 数据库名称

    // 创建一张表
    String KE_ID = "id";         // 主键
    String ITEM_ID = "item_id";       // 这条内容对应的id
    String TITLE = "title";      // 标题
    String CONTENT = "content";  // 内容
    String IMAGE = "image";      // 图片
    String NUM = "num";          // 阅读素
    String TIME = "time";        // 时间

    String TABLE_NAME = "Recommend";  // 表名

    String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + "("
            + KE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_ID + " VARCHAR(256), "
            + TITLE + " VARCHAR(128), "
            + CONTENT + " VARCHAR(256), "
            + IMAGE + " VARCHAR(128), "
            + NUM + " VARCHAR(128), "
            + TIME + " VARCHAR(256)"
            + ")";

    String DROP_TABLE_SQL = "DROP TABLE " + TABLE_NAME;  // 删除表


    // 另外一张表
    String SCHEDULE_ID = "id";
    String SCHEDULE_COLOR = "color";
    String SCHEDULE_TITLE = "title";
    String SCHEDULE_DESC = "desc";
    String SCHEDULE_STATE = "state";
    String SCHEDULE_TIME = "time";
    String SCHEDULE_YEAR = "year";
    String SCHEDULE_MONTH = "month";
    String SCHEDULE_DAY = "day";
    String SCHEDULE_LOCATION = "location";
    String SCHEDULE_EVENT_SET_ID = "eid";

    String SCHEDULE_TABLE_NAME = "TestOne";

    String CREATE_SCHEDULE_TABLE_SQL = "CREATE TABLE " + SCHEDULE_TABLE_NAME + "("
            + SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SCHEDULE_COLOR + " INTEGER, "
            + SCHEDULE_TITLE + " VARCHAR(128), "
            + SCHEDULE_LOCATION + " VARCHAR(48), "
            + SCHEDULE_DESC + " VARCHAR(255), "
            + SCHEDULE_STATE + " INTEGER, "
            + SCHEDULE_TIME + " LONG, "
            + SCHEDULE_YEAR + " INTEGER, "
            + SCHEDULE_MONTH + " INTEGER, "
            + SCHEDULE_DAY + " INTEGER, "
            + SCHEDULE_EVENT_SET_ID + " INTEGER" + ")";

    String DROP_SCHEDULE_TABLE_SQL = "DROP TABLE " + SCHEDULE_TABLE_NAME;
}
