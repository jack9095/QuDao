package com.retrofit.wangfei.viewpagertablayout.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.wang on 2018/11/13.
 * 自定义 recycleViewLayoutManager
 */
public class SimpleLayoutManager extends RecyclerView.LayoutManager {

    private int mDecoratedChildWidth;
    private int mDecoratedChildHeight;
    private int interval;
    private int middle;
    private int offset;
    private List<Integer> offsetList;
    public SimpleLayoutManager(Context context) {
        offsetList = new ArrayList<>();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public SimpleLayoutManager() {

    }

    /**
     * 初始布局 onLayoutChildren
     *
     * 这个方法会在一个view 第一次执行layout的时候调用，同时也会在adaper的数据集改变并通知观察者（也就是view）的时候调用。
     * 所以在其中每一次布局的时候，要先将之前放置的无用的View放回recycler中，因为这些View我们在后续还可能使用，
     * 为了减少初始化以及bind的时间，我们调用detachAndScrapAttachedViews。
     * 此外，对于不会再用到的View，可以调用removeAndRecycleView进行回收
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        if (getItemCount() == 0) {
            offset = 0;
            detachAndScrapAttachedViews(recycler);
            return;
        }

        // 初始化的过程，还没有childView，先取出一个测绘。 认为每个item的大小是一样的
        if (getChildCount() == 0) {
            View scrap = recycler.getViewForPosition(0);
            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);
            mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap);
            mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap);
            interval = 10;
            middle = (getVerticalSpace() - mDecoratedChildHeight) / 2;
            detachAndScrapView(scrap, recycler);
        }
        // 回收全部attach 的 view 到 recycler 并重新排列
        int property = 0;
        // 用一个全局变量 List offsetList 来存储每一个item的偏移量，并在onLayoutChildren中进行初始化
        for (int i = 0; i < getItemCount(); i++) {
            offsetList.add(property);
            property += mDecoratedChildHeight + interval;
        }
        detachAndScrapAttachedViews(recycler);
        layoutItems(recycler, state, 0);
    }

    @Override
    public boolean canScrollVertically() {  // 表示默认垂直滑动
        return true;
    }

    // 有关滑动的方法
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        return super.scrollVerticallyBy(dy, recycler, state);
        int willScroll = dy;
        offset += willScroll;
        if (offset < 0 || offset > offsetList.get(offsetList.size() - 1)) return 0;
        layoutItems(recycler, state, dy);
        return willScroll;
    }

    /**
     * 对每一个item记录了一下它的位置，然后滑动过程中offset+=dy，
     * 并且每次滑动后都出发LayoutItems方法，并且每个item在初始化y值的基础上减去offset，得到新的布局的位置
     * 带有缩放效果的
     * @param recycler
     * @param state
     * @param dy
     */
    private void layoutItems(RecyclerView.Recycler recycler, RecyclerView.State state, int dy) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            int pos = getPosition(view);
            if (outOfRange(offsetList.get(pos) - offset)) {
                removeAndRecycleView(view, recycler);
            }
        }
        detachAndScrapAttachedViews(recycler);
        int left = 100;
        View selectedView = null;
        float maxScale = Float.MIN_VALUE;
        for (int i = 0; i< getItemCount(); i++) {
            int top = offsetList.get(i);
            if (outOfRange(top - offset)) continue;
            View scrap = recycler.getViewForPosition(i);
            measureChildWithMargins(scrap, 0, 0);
            if (dy >= 0)
                addView(scrap);
            else
                addView(scrap, 0);

            int deltaY = Math.abs(top - offset - middle);
            scrap.setScaleX(1);
            scrap.setScaleY(1);
            float scale = 1 + (mDecoratedChildHeight / (deltaY + 1));
            if (scale > maxScale) {
                maxScale = scale;
                selectedView = scrap;
            }

            layoutDecorated(scrap, left, top - offset, left + mDecoratedChildWidth, top - offset + mDecoratedChildHeight);
        }

        if (selectedView != null) {
            maxScale = maxScale > 2 ? 2 : maxScale;
            selectedView.setScaleX(maxScale);
            selectedView.setScaleY(maxScale);
        }
    }


    private boolean outOfRange(float targetOffSet) {
        return targetOffSet > getVerticalSpace() + mDecoratedChildHeight ||
                targetOffSet < -mDecoratedChildHeight;
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }
}
