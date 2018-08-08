package com.retrofit.wangfei.viewpagertablayout.util;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/7/9.
 */

public class MyRecyclerView extends RecyclerView {

    private int lastVisibleItem;
    private LinearLayoutManager mLinearLayoutManager;
    private int count;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addOnScrollListener(OnScrollListener listener) {
        super.addOnScrollListener(listener);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();  // 滑动到最后一个
//        if (isSlideToBottom()) {
//            mRecyclerViewListener.onRecyclerViewLoadMore();
//        }
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        //  效果在暂停时显示, 否则会导致重绘异常
        if (screenState == SCROLL_STATE_IDLE
                && lastVisibleItem == count) {
            mRecyclerViewListener.onRecyclerViewLoadMore();
//            if (lists != null && lists.size() >= 10) {  // 真实开发中要设置mNews.size()大于加载分页显示的个数
//                adapter.loadLayout.setVisibility(View.VISIBLE);
//                //加载更多
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        netNewsList(false);
//                    }
//                }, 2000);
//            }
        }

//        if (isSlideToBottom() && screenState == RecyclerView.SCROLL_STATE_IDLE) {
////            mRecyclerViewListener.onRecyclerViewLoadMore();
//        }
    }

    public void setParams(LinearLayoutManager mLinearLayoutManager,int count,RecyclerViewListener mRecyclerViewListener){
        this.mLinearLayoutManager = mLinearLayoutManager;
        this.count = count;
        this.mRecyclerViewListener = mRecyclerViewListener;
    }

    public void setCount(int count){
        this.count = count;
    }

    private RecyclerViewListener mRecyclerViewListener;
    public interface RecyclerViewListener{
        void onRecyclerViewLoadMore();
    }

    /**
     * 其实就是它在起作用。
     */
    public boolean isSlideToBottom() {
        return this != null
                && this.computeVerticalScrollExtent() + this.computeVerticalScrollOffset()
                >= this.computeVerticalScrollRange();
    }
}
