package com.example.easythreadtest;

import com.fly.easythread.Callback;
import com.fly.easythread.EasyThread;

/**
 * 对于APP来说。线程资源是宝贵的。为了避免创建过多额外的线程，所以建议对每个app。提供一个统一的管理器维护所有的线程池
 */

public final class ThreadManager {
    private final static EasyThread io;
    private final static EasyThread cache;
    private final static EasyThread calculator; // 计算器
    private final static EasyThread file;

    public static EasyThread getIO () {
        return io;
    }

    public static EasyThread getCache() {
        return cache;
    }

    public static EasyThread getCalculator() {
        return calculator;
    }

    public static EasyThread getFile() {
        return file;
    }

    static {
        io = EasyThread.Builder.createFixed(6).setName("IO").setPriority(7).setCallback(new DefaultCallback()).build();
        cache = EasyThread.Builder.createCacheable().setName("cache").setCallback(new DefaultCallback()).build();
        calculator = EasyThread.Builder.createFixed(4).setName("calculator").setPriority(Thread.MAX_PRIORITY).setCallback(new DefaultCallback()).build();
        file = EasyThread.Builder.createFixed(4).setName("file").setPriority(3).setCallback(new DefaultCallback()).build();
    }

    private static class DefaultCallback implements Callback {

        @Override
        public void onError(String threadName, Throwable t) {
            LogUtil.e("Task with thread %s has occurs an error: %s", threadName, t.getMessage());
        }

        @Override
        public void onCompleted(String threadName) {
            LogUtil.d("Task with thread %s completed", threadName);
        }

        @Override
        public void onStart(String threadName) {
            LogUtil.d("Task with thread %s start running!", threadName);
        }
    }
}
