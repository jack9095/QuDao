package com.xujun.contralayout;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ImageView;

/**
 * @author xujun  on 2016/11/30.
 * @email gdutxiaoxu@163.com
 */

public class CustomBevior extends CoordinatorLayout.Behavior<ImageView> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent,
                                   ImageView child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View
            dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
