package com.kuanquan.qudao.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.ItemAdapter;
import com.kuanquan.qudao.widget.Sticklayout;
import com.kuanquan.qudao.widget.live.behavior.LiveHeaderPagerBehavior;
import java.util.ArrayList;
import java.util.List;

/**
 * 直播
 */
public class LiveFragment extends CommonFragment implements LiveHeaderPagerBehavior.OnPagerStateListener,Sticklayout.TouchListener {

    RecyclerView mRecyclerView;
    List<String> mDatas = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FrameLayout mHeaderView;
    private LiveHeaderPagerBehavior mHeaderPagerBehavior;
    private ImageView mIvBack;
    private Sticklayout mSticklayout;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    protected void initView() {
        mHeaderView = view.findViewById(R.id.id_hide_header);
        mIvBack = view.findViewById(R.id.iv_back);
        mSticklayout = view.findViewById(R.id.stick_rl);
        mSticklayout.setTouchListener(this);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBack();
            }
        });
        mIvBack.setVisibility(View.INVISIBLE);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.live_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.live_swipeRefreshLayout);
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        for (int i = 0; i < 50; i++) {
            @SuppressLint("DefaultLocale")
            String s = String.format("我是第%d个" + "item", i);
            mDatas.add(s);
        }

        mRecyclerView.setAdapter(new ItemAdapter(context, mDatas));
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
    protected void initData(Bundle savedInstanceState) {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mHeaderView.getLayoutParams();
        mHeaderPagerBehavior = (LiveHeaderPagerBehavior) layoutParams.getBehavior();
        assert mHeaderPagerBehavior != null;
        mHeaderPagerBehavior.setPagerStateListener(this);
    }

    public void tooglePager(boolean isOpen) {
        if (isOpen) {
            setRefreshEnable(false);
            scrollToFirst(false);
        } else {
            setRefreshEnable(true);
        }
    }

    public void scrollToFirst(boolean isSmooth) {
        if (mRecyclerView == null) {
            return;
        }
        if (isSmooth) {
            mRecyclerView.smoothScrollToPosition(0);
        } else {
            mRecyclerView.scrollToPosition(0);
        }
    }

    public void setRefreshEnable(boolean enabled) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setEnabled(enabled);
        }
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onPagerClosed() {
        Toast.makeText(context, "关闭了", Toast.LENGTH_SHORT).show();
        mIvBack.setVisibility(View.VISIBLE);
        tooglePager(false);
    }

    @Override
    public void onPagerOpened() {
        Toast.makeText(context, "打开了", Toast.LENGTH_SHORT).show();
        mIvBack.setVisibility(View.INVISIBLE);
       tooglePager(true);
    }

//    @Override
//    public void onBackPressed() {
//        handleBack();
//    }
//
    private void handleBack() {
        if(mHeaderPagerBehavior.isClosed()){
            mHeaderPagerBehavior.openPager();
            return;
        }
//        finish();
    }

    @Override
    public void onTouchRListener() {
        mHeaderPagerBehavior.openPager();
    }
}
