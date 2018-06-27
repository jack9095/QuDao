package com.xujun.contralayout.base;

import android.app.Application;

/*import com.meitu.library.analytics.AnalyticsAgent;*/
//Add begin by meitu.yijiabin for
//Add end
//Add begin by meitu.zhanghong
//Add end

public class BaseAPP extends Application {

    private static BaseAPP mBaseAPP;

    public static BaseAPP getInstance() {
        return mBaseAPP;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseAPP = this;

    }

    public  static  BaseAPP getAppContext(){
        return mBaseAPP;
    }


}
