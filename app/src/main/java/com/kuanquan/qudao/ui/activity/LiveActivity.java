package com.kuanquan.qudao.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.LiveBean;
import com.kuanquan.qudao.ui.adapter.LiveAdapter;
import com.kuanquan.qudao.ui.adapter.LiveCenterAdapter;
import com.kuanquan.qudao.utils.LiveDataUtil;
import com.kuanquan.qudao.utils.TimeUtils;
import com.kuanquan.qudao.widget.Constance;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播中心页面
 */
public class LiveActivity extends AppCompatActivity implements LiveCenterAdapter.OnShareListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean isRefresh;
    private boolean isLoad;  // 表示上次接口拿到数据了
    private LinearLayoutManager linearLayoutManager;
    private LiveCenterAdapter adapter;
    private List<LiveBean> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        initView();
        initData();
    }

    private void initData() {
        adapter = new LiveCenterAdapter(this, lists);
        mRecyclerView.setAdapter(adapter);
        lists.clear();
        lists.addAll(LiveDataUtil.getliveData());
        adapter.setData(lists);
    }

    private void initView() {
        findViewById(R.id.activity_integral_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                startActivity(new Intent(LiveActivity.this,SpecialActivity.class));
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new RvScrollListener());

        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(Constance.colors);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private int lastVisibleItem;

    @Override
    public void shoppingMall(LiveBean mIntegralBean) {

    }

    @Override
    public void earn(LiveBean mIntegralBean) {

    }

    private class RvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                if (lists != null && lists.size() >= 15) {
                    if (isLoad) { // 有数据可加载
                        try {
                            adapter.loadProgress.setVisibility(View.VISIBLE);
                            adapter.loadText.setVisibility(View.VISIBLE);
                            adapter.loadText.setText("正在加载");
                            LogUtil.e("显示底部加载");
                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtil.e("异常 = ", e);
                        }
                        isRefresh = false;
//                        requestListData(startData, endData);
                    }
                } else {
                    LogUtil.e("集合大小 = ", lists.size());
                }
            } else {
//                LogUtil.e("lastVisibleItem + 1 = ",lastVisibleItem + 1);
            }
        }
    }
}