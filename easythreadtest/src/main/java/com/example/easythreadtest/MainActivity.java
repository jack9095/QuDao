package com.example.easythreadtest;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fly.easythread.AsyncCallback;
import com.fly.easythread.Callback;
import com.fly.easythread.EasyThread;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/yjfnypeu/EasyThread
 * 线程池的简单应用
 */
public class MainActivity extends AppCompatActivity {

    EasyThread executor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建一个独立的实例进行使用
        executor = EasyThread.Builder
                .createFixed(4)  // 创建4个线程
                .setPriority(Thread.MAX_PRIORITY)  // 设置优先级
                .setCallback(new ToastCallback())  // 设置回调
                .build();
        ThreadManager.getCalculator().setDelay(5000,TimeUnit.MILLISECONDS)
                .setCallback(new Callback() {
                    @Override
                    public void onError(String threadName, Throwable t) {
                        LogUtil.e("线程任务执行异常");
                    }

                    @Override
                    public void onCompleted(String threadName) {
                        LogUtil.e("线程任务执行完成");
                    }

                    @Override
                    public void onStart(String threadName) {
                        LogUtil.e("线程任务开始执行");
                    }
                })
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.e("延时5秒");
                    }
                });

        User aa;
                aa = new User("","");
        User bb = new User("","");

        if (Objects.equals(aa,bb)) {
            LogUtil.e("这两个对象相等");
        }else{
            LogUtil.e("这两个对象不相等");
        }
    }

    // 启动普通Runnable任务
    public void runnableTask(View view) {
        executor.setName("Runnable task")
                .execute(new NormalTask());
    }

    // 普通Callable任务
    public void callableTask(View view) {
        Future<User> submit = executor.setName("Callable task")
                .submit(new NormalTask());

        try {
            User user = submit.get(3, TimeUnit.SECONDS); // 延时3秒执行
            toast("获取到任务返回信息：%s", user);
        } catch (Exception e) {
            toast("获取Callable任务信息失败");
        }
    }

    // 异步回调任务
    public void asyncTask(View view) {
        executor.setName("Async task")
                .async(new NormalTask(), new AsyncCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        toast("执行异步回调任务成功：%s", user);
                    }

                    @Override
                    public void onFailed(Throwable t) {
                        toast("执行异步回调任务失败：%s", t.getMessage());
                    }
                });
    }

    // 配置当前线程任务名(当前任务配置)
    public void onExceptionClick(View view) {
        executor.setName("un catch task")
                .execute(new UnCatchTask());
    }

    // 延迟后台任务
    public void delayTask(View view) {
        for (int i = 0; i < 6; i++) {
            executor.setName("delay task" + i)
                    .setDelay(i, TimeUnit.SECONDS)
                    .execute(new NormalTask());
        }
    }

    // 消息派发器 选择线程
    public void switchThread(View view) {
        executor.setName("switch thread task")
                .setDeliver(executor.getExecutor())// 回调到自身的线程池任务中
                .execute(new NormalTask());
    }

    public void multiThreadTask(View view) {
        // 多线程并发启动任务测试
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    executor.setName("MultiTask" + index)
                            .setCallback(new LogCallback())
                            .execute(new Runnable() {
                                @Override
                                public void run() {}
                            });
                }
            }).start();
        }
    }

    private class NormalTask implements Runnable, Callable<User> {
        @Override
        public void run() {
            toast("正在执行Runnable任务：%s", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public User call() throws Exception {
            toast("正在执行Callable任务：%s", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new User("Haoge", "123456");
        }
    }

    private class UnCatchTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            throw new RuntimeException("故意的");
        }
    }

    private class LogCallback implements Callback {

        private final String TAG = "LogCallback";

        @Override
        public void onError(String name, Throwable t) {
            Log.e(TAG, String.format("[任务线程%s]/[回调线程%s]执行失败: %s", name, Thread.currentThread(), t.getMessage()), t);
        }

        @Override
        public void onCompleted(String name) {
            Log.d(TAG, String.format("[任务线程%s]/[回调线程%s]执行完毕：", name, Thread.currentThread()));
        }

        @Override
        public void onStart(String name) {
            Log.d(TAG, String.format("[任务线程%s]/[回调线程%s]执行开始：", name, Thread.currentThread()));
        }
    }

    private class ToastCallback extends LogCallback {

        @Override
        public void onError(String name, Throwable t) {
            super.onError(name, t);
            toast("线程%s运行出现异常，异常信息为：%s", name, t.getMessage());
        }

        @Override
        public void onCompleted(String name) {
            super.onCompleted(name);
            toast("线程%s运行完毕", name);
        }
    }

    private void toast(final String message, final Object... args) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(this, String.format(message, args), Toast.LENGTH_SHORT).show();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, String.format(message, args), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
