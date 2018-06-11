package com.kuanquan.qudao.core.mvp.view;

/**
 * @author: fei.wang
 * @date: 2018.6.11
 * @des:
 *
 * ps:用EventBus或者Otto代替  效果更好
 */
public interface IHomeView {
    /**
     * UI业务逻辑   加载进度条
     */
    void showLoading();

    /**
     * presenter 把数据回调到View层
     * @param data
     */
    void showData(Object data);
}
