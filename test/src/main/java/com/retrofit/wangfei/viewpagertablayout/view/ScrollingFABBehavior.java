package com.retrofit.wangfei.viewpagertablayout.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import com.retrofit.wangfei.viewpagertablayout.util.Utils;

/**
 * FloatingActionButton悬浮按钮随着RecycleView滑动隐藏显示
 * 在有FloatingActionButton的XML中添加
 * app:layout_behavior="com.retrofit.wangfei.viewpagertablayout.view.ScrollingFABBehavior
 * 就好了
 */
public class ScrollingFABBehavior extends FloatingActionButton.Behavior {
    private int toolbarHeight;

    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super();
        this.toolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        return super.layoutDependsOn(parent, fab, dependency) || (dependency instanceof AppBarLayout);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        boolean returnValue = super.onDependentViewChanged(parent, fab, dependency);
        if (dependency instanceof AppBarLayout) {
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                int fabBottomMargin = lp.bottomMargin;
                int distanceToScroll = fab.getHeight() + fabBottomMargin;
                float ratio = dependency.getY()/(float)toolbarHeight;
                fab.setTranslationY(-distanceToScroll * ratio);
        }
        return returnValue;
    }
}