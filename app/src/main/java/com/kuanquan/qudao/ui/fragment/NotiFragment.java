package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kuanquan.qudao.R;
/**
 * 主页的消息页面
 */
public class NotiFragment extends CommonFragment {

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_noti, container, false);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}
