package com.example.db.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.bean.TestOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.wang on 2018/8/6.
 * 操作数据库的类
 */
public class TestDAO {
    private SQLiteHelper mHelper;

    private TestDAO(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public static TestDAO getInstance(Context context) {
        return new TestDAO(context);
    }

    public int addSchedule(TestOne schedule) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConfig.SCHEDULE_TITLE, schedule.getTitle());
        values.put(DbConfig.SCHEDULE_COLOR, schedule.getColor());
        values.put(DbConfig.SCHEDULE_DESC, schedule.getDesc());
        values.put(DbConfig.SCHEDULE_STATE, schedule.getState());
        values.put(DbConfig.SCHEDULE_LOCATION, schedule.getLocation());
        values.put(DbConfig.SCHEDULE_TIME, schedule.getTime());
        values.put(DbConfig.SCHEDULE_YEAR, schedule.getYear());
        values.put(DbConfig.SCHEDULE_MONTH, schedule.getMonth());
        values.put(DbConfig.SCHEDULE_DAY, schedule.getDay());
        values.put(DbConfig.SCHEDULE_EVENT_SET_ID, schedule.getEventSetId());
        long row = db.insert(DbConfig.SCHEDULE_TABLE_NAME, null, values);
        db.close();
        return row > 0 ? getLastScheduleId() : 0;
    }

    private int getLastScheduleId() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbConfig.SCHEDULE_TABLE_NAME, null, null, null, null, null, null, null);
        int id = 0;
        if (cursor.moveToLast()) {
            id = cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_ID));
        }
        cursor.close();
        db.close();
        mHelper.close();
        return id;
    }

    public List<TestOne> getScheduleByDate(int year, int month, int day) {
        List<TestOne> schedules = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbConfig.SCHEDULE_TABLE_NAME, null,
                String.format("%s=? and %s=? and %s=?", DbConfig.SCHEDULE_YEAR,
                        DbConfig.SCHEDULE_MONTH, DbConfig.SCHEDULE_DAY), new String[]{String.valueOf(year), String.valueOf(month), String.valueOf(day)}, null, null, null);
        TestOne schedule;
        while (cursor.moveToNext()) {
            schedule = new TestOne();
            schedule.setId(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_ID)));
            schedule.setColor(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_COLOR)));
            schedule.setTitle(cursor.getString(cursor.getColumnIndex(DbConfig.SCHEDULE_TITLE)));
            schedule.setLocation(cursor.getString(cursor.getColumnIndex(DbConfig.SCHEDULE_LOCATION)));
            schedule.setDesc(cursor.getString(cursor.getColumnIndex(DbConfig.SCHEDULE_DESC)));
            schedule.setState(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_STATE)));
            schedule.setYear(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_YEAR)));
            schedule.setMonth(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_MONTH)));
            schedule.setDay(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_DAY)));
            schedule.setTime(cursor.getLong(cursor.getColumnIndex(DbConfig.SCHEDULE_TIME)));
            schedule.setEventSetId(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_EVENT_SET_ID)));
            schedules.add(schedule);
        }
        cursor.close();
        db.close();
        mHelper.close();
        return schedules;
    }

    public List<Integer> getTaskHintByMonth(int year, int month) {
        List<Integer> taskHint = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbConfig.SCHEDULE_TABLE_NAME, new String[]{DbConfig.SCHEDULE_DAY},
                String.format("%s=? and %s=?", DbConfig.SCHEDULE_YEAR,
                        DbConfig.SCHEDULE_MONTH), new String[]{String.valueOf(year), String.valueOf(month)}, null, null, null);
        while (cursor.moveToNext()) {
            taskHint.add(cursor.getInt(0));
        }
        cursor.close();
        db.close();
        mHelper.close();
        return taskHint;
    }

    public List<Integer> getTaskHintByWeek(int firstYear, int firstMonth, int firstDay, int endYear, int endMonth, int endDay) {
        List<Integer> taskHint = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor1 = db.query(DbConfig.SCHEDULE_TABLE_NAME, new String[]{DbConfig.SCHEDULE_DAY},
                String.format("%s=? and %s=? and %s>=?", DbConfig.SCHEDULE_YEAR, DbConfig.SCHEDULE_MONTH, DbConfig.SCHEDULE_DAY),
                new String[]{String.valueOf(firstYear), String.valueOf(firstMonth), String.valueOf(firstDay)}, null, null, null);
        while (cursor1.moveToNext()) {
            taskHint.add(cursor1.getInt(0));
        }
        cursor1.close();
        Cursor cursor2 = db.query(DbConfig.SCHEDULE_TABLE_NAME, new String[]{DbConfig.SCHEDULE_DAY},
                String.format("%s=? and %s=? and %s<=?", DbConfig.SCHEDULE_YEAR, DbConfig.SCHEDULE_MONTH, DbConfig.SCHEDULE_DAY),
                new String[]{String.valueOf(endYear), String.valueOf(endMonth), String.valueOf(endDay)}, null, null, null);
        while (cursor2.moveToNext()) {
            taskHint.add(cursor2.getInt(0));
        }
        cursor2.close();
        db.close();
        mHelper.close();
        return taskHint;
    }

    public boolean removeSchedule(long id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int row = db.delete(DbConfig.SCHEDULE_TABLE_NAME, String.format("%s=?", DbConfig.SCHEDULE_ID), new String[]{String.valueOf(id)});
        db.close();
        mHelper.close();
        return row != 0;
    }

    public void removeScheduleByEventSetId(int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(DbConfig.SCHEDULE_TABLE_NAME, String.format("%s=?", DbConfig.SCHEDULE_EVENT_SET_ID), new String[]{String.valueOf(id)});
        db.close();
        mHelper.close();
    }

    public boolean updateSchedule(TestOne schedule) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbConfig.SCHEDULE_TITLE, schedule.getTitle());
        values.put(DbConfig.SCHEDULE_COLOR, schedule.getColor());
        values.put(DbConfig.SCHEDULE_DESC, schedule.getDesc());
        values.put(DbConfig.SCHEDULE_STATE, schedule.getState());
        values.put(DbConfig.SCHEDULE_LOCATION, schedule.getLocation());
        values.put(DbConfig.SCHEDULE_YEAR, schedule.getYear());
        values.put(DbConfig.SCHEDULE_MONTH, schedule.getMonth());
        values.put(DbConfig.SCHEDULE_TIME, schedule.getTime());
        values.put(DbConfig.SCHEDULE_DAY, schedule.getDay());
        values.put(DbConfig.SCHEDULE_EVENT_SET_ID, schedule.getEventSetId());
        int row = db.update(DbConfig.SCHEDULE_TABLE_NAME, values, String.format("%s=?", DbConfig.SCHEDULE_ID), new String[]{String.valueOf(schedule.getId())});
        db.close();
        mHelper.close();
        return row > 0;
    }

    public List<TestOne> getScheduleByEventSetId(int id) {
        List<TestOne> schedules = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbConfig.SCHEDULE_TABLE_NAME, null,
                String.format("%s=?", DbConfig.SCHEDULE_EVENT_SET_ID), new String[]{String.valueOf(id)}, null, null, null);
        TestOne schedule;
        while (cursor.moveToNext()) {
            schedule = new TestOne();
            schedule.setId(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_ID)));
            schedule.setColor(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_COLOR)));
            schedule.setTitle(cursor.getString(cursor.getColumnIndex(DbConfig.SCHEDULE_TITLE)));
            schedule.setDesc(cursor.getString(cursor.getColumnIndex(DbConfig.SCHEDULE_DESC)));
            schedule.setLocation(cursor.getString(cursor.getColumnIndex(DbConfig.SCHEDULE_LOCATION)));
            schedule.setState(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_STATE)));
            schedule.setYear(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_YEAR)));
            schedule.setMonth(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_MONTH)));
            schedule.setDay(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_DAY)));
            schedule.setTime(cursor.getLong(cursor.getColumnIndex(DbConfig.SCHEDULE_TIME)));
            schedule.setEventSetId(cursor.getInt(cursor.getColumnIndex(DbConfig.SCHEDULE_EVENT_SET_ID)));
            schedules.add(schedule);
        }
        cursor.close();
        db.close();
        mHelper.close();
        return schedules;
    }
}
