package com.example.db.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.db.bean.Recommend;
import com.example.fly.baselibrary.utils.useful.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.wang on 2018/8/7.
 */

public class RecommendDAO {
    private SQLiteHelper mHelper;

    private RecommendDAO(Context context) {
        mHelper = new SQLiteHelper(context);
        LogUtil.e("初始化数据库");
    }

    public static RecommendDAO getInstance(Context context) {
        return new RecommendDAO(context);
    }

    // 插入数据
    public int addRecommend(Recommend recommend) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConfig.ITEM_ID, recommend.id);
        values.put(DbConfig.TITLE, recommend.title);
        values.put(DbConfig.CONTENT, recommend.content);
        values.put(DbConfig.IMAGE, recommend.image);
        values.put(DbConfig.NUM, recommend.num);
        values.put(DbConfig.TIME, recommend.time);
        long row = db.insert(DbConfig.TABLE_NAME, null, values);
        db.close();
        return row > 0 ? getLastRecommendId() : 0;
    }

    private int getLastRecommendId() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbConfig.TABLE_NAME, null, null, null, null, null, null, null);
        int id = 0;
        if (cursor.moveToLast()) {
            id = cursor.getInt(cursor.getColumnIndex(DbConfig.KE_ID));
        }
        cursor.close();
        db.close();
        mHelper.close();
        return id;
    }

    /**
     * 查询全部数据
     */
    public List<Recommend> queryAllRecommendData(){
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //查询全部数据
        Cursor cursor = db.query(DbConfig.TABLE_NAME,null,null,null,null,null,null,null);
        List<Recommend> list = new ArrayList<>();
        if(cursor.getCount() > 0) {
            //移动到首位
            cursor.moveToFirst();
            Recommend model;
            for (int i = 0; i < cursor.getCount(); i++) {

                model = new Recommend();
                model.id = cursor.getString(cursor.getColumnIndex(DbConfig.ITEM_ID));
                model.title = cursor.getString(cursor.getColumnIndex(DbConfig.TITLE));
                model.content = cursor.getString(cursor.getColumnIndex(DbConfig.CONTENT));
                model.image = cursor.getString(cursor.getColumnIndex(DbConfig.IMAGE));
                model.num = cursor.getString(cursor.getColumnIndex(DbConfig.NUM));
                model.time = cursor.getString(cursor.getColumnIndex(DbConfig.TIME));

                list.add(model);
                //移动到下一位
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        mHelper.close();
        return list;
    }

    /**
     * 分页查询数据
     * https://blog.csdn.net/lu1024188315/article/details/51734514
     * @param page      页数
     * @param pageSize  每页查询多少条
     * @return
     */
    public List<Recommend> queryLimitRecommendData(int page,int pageSize){
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 查询的SQ语句
        String sql= "select * from " + DbConfig.TABLE_NAME + " Limit " + pageSize + " Offset " + page;

        Cursor cursor = db.rawQuery(sql, null);

        List<Recommend> list = new ArrayList<>();
        if(cursor.getCount() > 0) {
            //移动到首位
            cursor.moveToFirst();
            Recommend model;
            for (int i = 0; i < cursor.getCount(); i++) {

                model = new Recommend();
                model.id = cursor.getString(cursor.getColumnIndex(DbConfig.ITEM_ID));
                model.title = cursor.getString(cursor.getColumnIndex(DbConfig.TITLE));
                model.content = cursor.getString(cursor.getColumnIndex(DbConfig.CONTENT));
                model.image = cursor.getString(cursor.getColumnIndex(DbConfig.IMAGE));
                model.num = cursor.getString(cursor.getColumnIndex(DbConfig.NUM));
                model.time = cursor.getString(cursor.getColumnIndex(DbConfig.TIME));

                list.add(model);
                //移动到下一位
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        mHelper.close();
        return list;
    }


    /**
     * 分页查询数据（倒叙）
     * https://blog.csdn.net/lu1024188315/article/details/51734514
     * @param page      页数
     * @param pageSize  每页查询多少条
     * @return
     */
    public List<Recommend> queryLimitRecommendDataDesc(int page,int pageSize){
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 查询的SQ语句
        String sql= "select * from " + DbConfig.TABLE_NAME + " order by " + DbConfig.KE_ID + " desc" + " Limit " + pageSize + " Offset " + page;

        Cursor cursor = db.rawQuery(sql, null);
        List<Recommend> list = new ArrayList<>();
        if(cursor.getCount() > 0) {
            //移动到首位
            cursor.moveToFirst();
            Recommend model;
            for (int i = 0; i < cursor.getCount(); i++) {

                model = new Recommend();
                model.id = cursor.getString(cursor.getColumnIndex(DbConfig.ITEM_ID));
                model.title = cursor.getString(cursor.getColumnIndex(DbConfig.TITLE));
                model.content = cursor.getString(cursor.getColumnIndex(DbConfig.CONTENT));
                model.image = cursor.getString(cursor.getColumnIndex(DbConfig.IMAGE));
                model.num = cursor.getString(cursor.getColumnIndex(DbConfig.NUM));
                model.time = cursor.getString(cursor.getColumnIndex(DbConfig.TIME));

                list.add(model);
                //移动到下一位
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        mHelper.close();
        return list;
    }

    // 根据主键移除数据
    public boolean removeSchedule(int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int row = db.delete(DbConfig.TABLE_NAME, String.format("%s=?", DbConfig.KE_ID), new String[]{String.valueOf(id)});
        db.close();
        mHelper.close();
        return row != 0;
    }

    // 根据id更新数据
    public boolean updateSchedule(Recommend recommend) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConfig.ITEM_ID, recommend.id);
        values.put(DbConfig.TITLE, recommend.title);
        values.put(DbConfig.CONTENT, recommend.content);
        values.put(DbConfig.IMAGE, recommend.image);
        values.put(DbConfig.NUM, recommend.num);
        values.put(DbConfig.TIME, recommend.time);
        int row = db.update(DbConfig.TABLE_NAME, values, String.format("%s=?", DbConfig.ITEM_ID), new String[]{recommend.id});
        db.close();
        mHelper.close();
        return row > 0;
    }

    // TODO 开启事务批量插入，使用SQLiteStatement  速度最快
    public boolean insertBySql(List<Recommend> list) {
        if (null == mHelper || null == list || list.size() <= 0) {
            return false;
        }
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            String sql = "insert into " + DbConfig.TABLE_NAME + "("
                    + DbConfig.ITEM_ID + ","
                    + DbConfig.TITLE + ","
                    + DbConfig.CONTENT + ","
                    + DbConfig.IMAGE + ","
                    + DbConfig.NUM + ","
                    + DbConfig.TIME
                    + ")" + " values(?,?,?,?,?,?)";
            SQLiteStatement stat = db.compileStatement(sql);
            db.beginTransaction();
            for (Recommend recommend : list) {
                stat.bindString(1, recommend.id);
                stat.bindString(2, recommend.title);
                stat.bindString(3, recommend.content);
                stat.bindString(4, recommend.image);
                stat.bindString(5, recommend.num);
                stat.bindString(6, recommend.time);
                long result = stat.executeInsert();
                if (result < 0) {
                    return false;
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (null != db) {
                    db.endTransaction();
                    db.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // TODO 开启事务批量插入，使用SqliteDateBase中的insert(String table, String nullColumnHack, ContentValues values)方法 速度比上面的慢点
    public boolean insert(List<Recommend> list) {
        boolean result = true;
        if (null == list || list.size() <= 0) {
            return true;
        }
        SQLiteDatabase db = null;

        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            for (Recommend recommend : list) {
                ContentValues values = recommend.getContentValues(recommend);
                if (db.insert(DbConfig.TABLE_NAME, null, values) < 0) {
                    result = false;
                    break;
                }
            }
            if (result) {
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (null != db) {
                    db.endTransaction();
                    db.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
