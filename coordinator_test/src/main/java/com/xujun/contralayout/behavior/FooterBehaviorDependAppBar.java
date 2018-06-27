package com.xujun.contralayout.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 知乎效果底部behavior 依赖于 AppBarLayout
 *
 * @author xujun  on 2016/11/30.
 * @email gdutxiaoxu@163.com
 */

public class FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View> {

    public static final String TAG = "xujun";

    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //当 dependency instanceof AppBarLayout 返回TRUE，将会调用onDependentViewChanged（）方法
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //根据dependency top值的变化改变 child 的 translationY
        float translationY = Math.abs(dependency.getTop());
        child.setTranslationY(translationY);
        Log.i(TAG, "onDependentViewChanged: " + translationY);
        return true;

    }
}