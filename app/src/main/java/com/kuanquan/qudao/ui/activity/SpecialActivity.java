package com.kuanquan.qudao.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.LiveBean;
import com.kuanquan.qudao.ui.adapter.LiveCenterAdapter;
import com.kuanquan.qudao.ui.adapter.SpecialAdapter;
import com.kuanquan.qudao.utils.LiveDataUtil;
import com.kuanquan.qudao.widget.Constance;

import java.util.ArrayList;
import java.util.List;

/**
 * 专题详情页面
 */
public class SpecialActivity extends AppCompatActivity implements SpecialAdapter.OnShareListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean isRefresh;
    private boolean isLoad;  // 表示上次接口拿到数据了
    private LinearLayoutManager linearLayoutManager;
    private SpecialAdapter adapter;
    private List<LiveBean> lists = new ArrayList<>();  // 适配器数据
    private boolean isVisi;  // 标题是否显示  true 显示
    private RelativeLayout titleLayout; // 标题布局
    private TextView titleView; // 标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        initView();
        initData();
    }

    private void initData() {
        adapter = new SpecialAdapter(this, lists);
        mRecyclerView.setAdapter(adapter);
        lists.clear();
        lists.addAll(LiveDataUtil.getSpecialData());
        adapter.setData(lists);
    }

    private void initView() {
        findViewById(R.id.activity_special_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleLayout = (RelativeLayout) findViewById(R.id.activity_special_rl);
        titleView = (TextView) findViewById(R.id.activity_special_title);
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

            if (!recyclerView.canScrollVertically(-1)) {
                isVisi = false;
                titleView.setVisibility(View.GONE);
                titleLayout.setBackgroundColor(Color.parseColor("#00000000"));
            }
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

            try {
                if (!isVisi && getScollYDistance() >= 40) {
                    titleLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                    titleView.setVisibility(View.VISIBLE);
                    isVisi = true;
                }
            }catch (Exception e){

            }
        }
    }

    /**
     * 获取RecyclerView滑动的距离
     * @return
     */
    private int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }
}
