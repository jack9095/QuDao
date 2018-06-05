package com.example.fly.baselibrary.mvpEeventBus;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by fei.wang on 2017/7/4.
 *
 */
public abstract class BaseModel {
    protected Handler mHandler;

    public void initHandler(){
        this.mHandler = new Handler(Looper.getMainLooper());
    }

//    protected CompositeDisposable compositeDisposable; //管理事件订阅
//    protected ArrayMap<String, Disposable> disposableMap;
//
//    public void onDestory() {
//        onUnsubscribe();
//    }
//
//    /**
//     * 添加事件监听处理到 事件管理类
//     *
//     * @param disposable 上流事件
//     */
//    protected void addSubscription(Disposable disposable) {
//        if (compositeDisposable == null) {
//            compositeDisposable = new CompositeDisposable();
//        }
//        compositeDisposable.add(disposable);
//    }
//
//    /**
//     * 添加事件监听处理到 事件管理类
//     *
//     * @param tag        标识符
//     * @param disposable 上流事件
//     */
//    protected void addSubscription(String tag, Disposable disposable) {
//        if (compositeDisposable == null) {
//            compositeDisposable = new CompositeDisposable();
//        }
//        if (disposableMap == null) {
//            disposableMap = new ArrayMap<>();
//        }
//        disposableMap.put(tag, disposable);
//        compositeDisposable.add(disposable);
//    }

//    /**
//     * RxJava取消注册，避免内存泄露
//     * 取消以后就只能重新新建一个了
//     */
//    protected void onUnsubscribe() {
//        if (compositeDisposable != null) {
//            // Using clear will clear all, but can accept new disposable
////            compositeDisposable.clear();
//            // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
//            compositeDisposable.dispose();
//            compositeDisposable = null;
//        }
//        if (disposableMap != null && disposableMap.size() > 0) {
//            disposableMap.clear();
//        }
//    }
//
//    /**
//     * 根据标识符移除Disposable
//     *
//     * @param tags 标识符
//     */
//    protected void removeDisposableByTag(String... tags) {
//        if (disposableMap != null && disposableMap.size() > 0) {
//            for (String tag : tags) {
//                if (disposableMap.containsKey(tag)) {
//                    compositeDisposable.remove(disposableMap.get(tag));
//                    disposableMap.remove(tag);
//                }
//            }
//        }
//    }

    // 回调到presenter的接口
    public RequestNetDataListener mRequestNetDataListener;

    public void setRequestNetDataListener(RequestNetDataListener mRequestNetDataListener){
        this.mRequestNetDataListener = mRequestNetDataListener;
    }

    public interface RequestNetDataListener{
        void requestNetDataSuccessListener(String requestCode, Object data);  // 参数一：请求码  参数二：接口返回数据
        void requestNetDataErrorListener(String requestCode, String errorMessage); // 错误信息
    }
}
