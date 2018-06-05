package com.example.fly.baselibrary;

import android.app.Application;

import com.example.fly.baselibrary.utils.base.ACache;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.example.fly.baselibrary.utils.useful.SPManager;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;

/**
 * Created by a on 2017/5/27.
 */

public class BaseApplication extends Application {

    protected static BaseApplication mAppContext;
    public ACache mACache;
    public ACache mACacheFile;
    public File file;

    @Override
    public void onCreate() {
        super.onCreate();
        onFastInit();
        mAppContext = this;
        mACache = ACache.get(getApplicationContext(), false);
        file = new File(getCacheDir().getAbsolutePath(),"fly");
        mACacheFile = ACache.get(file);
        SPManager.init(mAppContext);

        initLog();

        LeakCanary.install(mAppContext);
        Stetho.initializeWithDefaults(mAppContext);
    }

    protected void onFastInit() {

    }

    public static BaseApplication getAppContext() {
        return mAppContext;
    }

    /**
     * 初始化Log打印配置
     */
    private void initLog() {
        LogUtil.Builder builder = new LogUtil.Builder(mAppContext)
                .isLog(true) //是否开启打印
                .isLogBorder(true) //是否开启边框
                .setLogType(LogUtil.TYPE.E) //设置默认打印级别
                .setTag("lzh"); //设置默认打印Tag
        LogUtil.init(builder);
    }


}
