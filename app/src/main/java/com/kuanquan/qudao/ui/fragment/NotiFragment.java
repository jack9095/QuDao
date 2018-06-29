package com.kuanquan.qudao.ui.fragment;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.ItemAdapter;
import com.kuanquan.qudao.widget.Sticklayout;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页的消息页面
 */
public class NotiFragment extends CommonFragment implements View.OnClickListener,Sticklayout.TouchListener{
    //AppBarLayout
    private AppBarLayout mAppBarLayout;
    //顶部HeaderLayout
    private LinearLayout headerLayout;
    //滑动固定标题布局
    private Sticklayout mSticklayout;
    //是否隐藏了头部
    private boolean isHideHeaderLayout = false;

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_noti, container, false);
    }

    @Override
    protected void initView() {
        headerLayout = (LinearLayout) view.findViewById(R.id.ll_header_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notify_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.notify_swipeRefreshLayout);
        mSticklayout = (Sticklayout) view.findViewById(R.id.notify_stick_rl);
        mSticklayout.setTouchListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        initAppBarLayout();
    }

    // 初始化AppBarLayout
    private void initAppBarLayout(){
        LayoutTransition mTransition = new LayoutTransition();
        /**
         * 添加View时过渡动画效果
         */
        ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, "translationY", 0, 1.f).
                setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, addAnimator);

        //header layout height
        final int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_height);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.notify_appbar);
        mAppBarLayout.setLayoutTransition(mTransition);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                verticalOffset = Math.abs(verticalOffset);
                if ( verticalOffset >= headerHeight ){
                    isHideHeaderLayout = true;
                    //当偏移量超过顶部layout的高度时，我们认为他已经完全移动出屏幕了
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
                            mParams.setScrollFlags(0);
                            headerLayout.setLayoutParams(mParams);
                            headerLayout.setVisibility(View.GONE);
                        }
                    },100);
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
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
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTouchRListener() {
        if (isHideHeaderLayout){
            isHideHeaderLayout = false;
            mRecyclerView.scrollToPosition(0);
            headerLayout.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
                    mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                    headerLayout.setLayoutParams(mParams);
                }
            },300);
        }
    }
}
