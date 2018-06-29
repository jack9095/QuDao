package com.kuanquan.qudao.ui.fragment;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.ui.adapter.HomeAdapter_Copy;
import com.kuanquan.qudao.ui.adapter.HomeAdapter_release;
import com.kuanquan.qudao.ui.adapter.ItemAdapter;
import com.kuanquan.qudao.utils.DataUtils;
import com.kuanquan.qudao.widget.HomeBanner;
import com.kuanquan.qudao.widget.Sticklayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends CommonFragment implements HomeAdapter_release.OnHomeListener,Sticklayout.TouchListener,HomeBanner.OnPageClickListener {
    //AppBarLayout
    private AppBarLayout mAppBarLayout;
    //顶部HeaderLayout
    private LinearLayout headerLayout;
    //滑动固定标题布局
    private Sticklayout mSticklayout;
    //是否隐藏了头部
    private boolean isHideHeaderLayout = false;

    private HomeBanner mHomeBanner;

    private HomeAdapter_release mHomeAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RelativeLayout headerView;
    private TextView headerTv,moreTv;

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_copy, container, false);
    }

    @Override
    protected void initView() {
        headerLayout = (LinearLayout) view.findViewById(R.id.home_header_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notify_recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.notify_swipeRefreshLayout);
        mSticklayout = (Sticklayout) view.findViewById(R.id.notify_stick_rl);
        mSticklayout.setTouchListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addOnScrollListener(new RvScrollListener());
        mHomeBanner = view.findViewById(R.id.home_banner_layout);
        mHomeBanner.setData(DataUtils.getBannerData(),this);
        mHomeBanner.setScrollSpeed(mHomeBanner);

        // 粘性头部
        headerView = (RelativeLayout) view.findViewById(R.id.stick_rl_adapter);
        moreTv = (TextView) view.findViewById(R.id.text_live_more_open);
        headerTv = (TextView) view.findViewById(R.id.text_live_open);
        headerTv.setText("发现");

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
        final int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_home_height);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.home_appbar);
        mAppBarLayout.setLayoutTransition(mTransition);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                verticalOffset = Math.abs(verticalOffset);
                if ( verticalOffset >= headerHeight ){
                    isHideHeaderLayout = true;
                    LogUtil.e("移出屏幕了");
                    //当偏移量超过顶部layout的高度时，我们认为他已经完全移动出屏幕了
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
//                            mParams.setScrollFlags(0);
//                            headerLayout.setLayoutParams(mParams);
//                            headerLayout.setVisibility(View.GONE);
//                        }
//                    },100);
                }
            }
        });
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

    /**
     * 监听RecyclerView滚动，实现粘性头部
     */
    private class RvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            View stickyInfoView = recyclerView.getChildAt(0);//获取头部View
            if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                if ("发现".equals(String.valueOf(stickyInfoView.getContentDescription()))) {
                    headerView.setVisibility(View.VISIBLE);
                    headerTv.setText(String.valueOf(stickyInfoView.getContentDescription()));
                    moreTv.setText("设置偏好");
                }else{
                    headerView.setVisibility(View.GONE);
                    headerTv.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }
            }
            View transInfoView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2
                    , headerView.getMeasuredHeight() + 1);//位于headerView下方的itemView（该坐标是否在itemView内）
            if (transInfoView != null && transInfoView.getTag() != null) {
                int tag = (int) transInfoView.getTag();
                int deltaY = transInfoView.getTop() - headerView.getMeasuredHeight();
                if (tag == HomeAdapter_release.HAS_STICKY_VIEW){//当Item包含粘性头部一类时
                    if (transInfoView.getTop() > 0){//当Item还未移动出顶部时
                        headerView.setVisibility(View.GONE);
                        headerView.setTranslationY(deltaY);
                    } else{//当Item移出顶部，粘性头部复原
                        headerView.setVisibility(View.VISIBLE);
                        headerView.setTranslationY(0);
                    }
                } else{//当Item不包含粘性头部时
                    headerView.setTranslationY(0);
                    headerView.setVisibility(View.VISIBLE);
//                    headerView.setTranslationY(deltaY);
                }
            }
        }
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onNotify() {  // 删除发现item

    }

    @Override
    public void onTouchRListener() {  // 滑动显示头部
        if (isHideHeaderLayout){
            isHideHeaderLayout = false;
//            mRecyclerView.scrollToPosition(0);
            headerLayout.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
                    mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                    headerLayout.setLayoutParams(mParams);
                }
            },600);
        }
    }

    @Override
    public void onPageClick(HomeBeanChild info) {  // banner 点击回调

    }
}
