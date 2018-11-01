package com.kuanquan.qudao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.activity.RecyclerViewActivity;

/**
 * 我的页面
 */
public class MyFragment extends CommonFragment {

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView() {
        view.findViewById(R.id.rl_changeAvatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),RecyclerViewActivity.class));
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

}
