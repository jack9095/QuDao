package com.kuanquan.qudao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.activity.DetailsArticleActivity;
import com.kuanquan.qudao.ui.activity.NotifyActivity;
import com.kuanquan.qudao.ui.adapter.LiveAdapter;
import com.kuanquan.qudao.utils.DataUtils;

/**
 * 直播
 */
public class LiveFragment extends CommonFragment {
    private RecyclerView mRecyclerView;
    private LiveAdapter mLiveAdapter;


    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    protected void initView() {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(manager);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(100);
        mRecyclerView.setItemAnimator(animator);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollThreshold;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
//                        onScrollUp();
                    } else {
//                        onScrollDown();
                    }
                }
            }

            public void setScrollThreshold(int scrollThreshold) {
                mScrollThreshold = scrollThreshold;
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mLiveAdapter = new LiveAdapter(this);
        mRecyclerView.setAdapter(mLiveAdapter);
        mLiveAdapter.setData(DataUtils.getData());
    }


    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}
