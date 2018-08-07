package com.example.db.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fei.wang on 2018/8/6.
 * 数据库帮助类
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, DbConfig.DATABASE_NAME, null, DbConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建两张表出来
        db.execSQL(DbConfig.CREATE_TABLE_SQL);
//        db.execSQL(DbConfig.CREATE_SCHEDULE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(DbConfig.DROP_TABLE_SQL);
//            db.execSQL(DbConfig.DROP_SCHEDULE_TABLE_SQL);
            onCreate(db);
        }
    }
}
