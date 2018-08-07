package com.example.db;

import android.app.Application;

import com.example.fly.baselibrary.BaseApplication;

/**
 * Created by fei.wang on 2018/8/6.
 *
 */
public class DBApplication extends BaseApplication {

    private static DBApplication dbApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        dbApplication = this;
    }

    public static DBApplication getInstence(){
        return dbApplication;
    }

}
