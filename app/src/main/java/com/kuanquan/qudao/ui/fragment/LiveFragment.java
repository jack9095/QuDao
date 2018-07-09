package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kuanquan.qudao.R;

/**
 * 直播
 */
public class LiveFragment extends CommonFragment {

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_live, container, false);
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
