package com.kuanquan.qudao.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.RecyclerFAdapter;
import com.kuanquan.qudao.widget.SwipeRefreshLayoutRecycler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 简单封装recyclerView的刷新和加载
 */
public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayoutRecycler.RefreshListener {
    SwipeRefreshLayoutRecycler mSwipeRefreshLayoutRecycler;
    RecyclerFAdapter mRecyclerFAdapter;
    List<String> lists = new ArrayList<>();
    HashMap<String,String> maps = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mSwipeRefreshLayoutRecycler = findViewById(R.id.swipe_refresh_layout_f);
        mSwipeRefreshLayoutRecycler.setRefreshListener(this);

        for(int i = 0; i < 10; i++){
            lists.add("" + i);
        }

        mRecyclerFAdapter = new RecyclerFAdapter();
        mSwipeRefreshLayoutRecycler.getRecyclerView().setAdapter(mRecyclerFAdapter);
        mRecyclerFAdapter.setData(lists);
    }

    @Override
    public void onRefreshListener() {

    }

    @Override
    public void onLoadListener(int newState, int lastVisibleItem) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRecyclerFAdapter.getItemCount()) {

            for(int i = 0; i < 10; i++){
                lists.add("" + i);
            }
            LogUtil.e("滑动到底部");

            mSwipeRefreshLayoutRecycler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerFAdapter.setData(lists);
                }
            },2000);
        }
    }
}
