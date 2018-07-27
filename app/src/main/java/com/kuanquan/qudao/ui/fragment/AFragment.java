package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.HomeAdapter_release;
import com.kuanquan.qudao.utils.DataUtils;

public class AFragment extends CommonFragment implements HomeAdapter_release.OnHomeListener{

    private HomeAdapter_release mHomeAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter_release(this);
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setData(DataUtils.getFindData());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(context, "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1200);
            }
        });
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onNotify() {

    }
}
