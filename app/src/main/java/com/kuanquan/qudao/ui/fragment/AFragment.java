package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fly.baselibrary.mvpEeventBus.EventCenter;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.HomeAdapter;
import com.kuanquan.qudao.utils.DataUtils;

import org.greenrobot.eventbus.Subscribe;

public class AFragment extends CommonFragment implements HomeAdapter.OnHomeListener{

    private HomeAdapter mHomeAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter(this);
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
        return true;
    }

    @Subscribe
    public void onEventMainThread(EventCenter event){
        switch (event.getEventCode()){
            case "recycler_unFocus":
                if (mRecyclerView != null) {
                    mRecyclerView.setFocusable(false);
                    mRecyclerView.scrollToPosition(0);
                }
                break;
        }
    }

    @Override
    public void onNotify() {

    }

    private int lastVisibleItem;
    private boolean isRefresh;  // true 是刷新
    private boolean isLoad; // true 是加载到数据可以继续加载数据（网络请求）
    private class RvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();  // 滑动到最后一个
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mHomeAdapter.getItemCount()) {
                if (DataUtils.getFindData() != null && DataUtils.getFindData().size() >= 5) {
                    if (isLoad) {
                        mHomeAdapter.loadProgress.setVisibility(View.VISIBLE);
                        mHomeAdapter.loadText.setVisibility(View.VISIBLE);
                        isRefresh = false;
                        // 网络请求
                    }
                }
            }
        }
    }

}
