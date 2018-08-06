package com.example.db;

import android.app.Application;

/**
 * Created by fei.wang on 2018/8/6.
 *
 */
public class DBApplication extends Application {

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
