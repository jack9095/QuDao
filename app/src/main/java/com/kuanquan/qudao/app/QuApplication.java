package com.kuanquan.qudao.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2018/6/4.
 */
public class QuApplication extends Application {

    private static QuApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        mContext = this;
    }

    public static QuApplication getAppContext(){
        return mContext;
    }
}
