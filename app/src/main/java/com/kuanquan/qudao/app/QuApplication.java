package com.kuanquan.qudao.app;

import com.example.fly.baselibrary.BaseApplication;

/**
 * Created by Administrator on 2018/6/4.
 *
 */
public class QuApplication extends BaseApplication {

    private static QuApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static QuApplication getAppContext(){
        return mContext;
    }
}
