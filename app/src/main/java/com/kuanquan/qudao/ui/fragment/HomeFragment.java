package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.HomeAdapter_Copy;

/**
 * 首页
 */
public class HomeFragment extends CommonFragment implements HomeAdapter_Copy.OnHomeListener {

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_copy, container, false);
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onNotify() {

    }
}
