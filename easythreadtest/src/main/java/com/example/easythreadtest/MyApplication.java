package com.example.easythreadtest;

import android.app.Application;

/**
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    /**
     * 初始化Log打印配置
     */
    private void initLog() {
        LogUtil.Builder builder = new LogUtil.Builder(getApplicationContext())
                .isLog(true) //是否开启打印
                .isLogBorder(true) //是否开启边框
                .setLogType(LogUtil.TYPE.E) //设置默认打印级别
                .setTag("fly"); //设置默认打印Tag
        LogUtil.init(builder);
    }
}
