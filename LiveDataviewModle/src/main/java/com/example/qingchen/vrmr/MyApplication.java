package com.example.qingchen.vrmr;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;


public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
