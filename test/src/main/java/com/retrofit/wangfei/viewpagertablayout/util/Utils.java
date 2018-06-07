package com.retrofit.wangfei.viewpagertablayout.util;

import android.content.Context;
import android.content.res.TypedArray;

import com.retrofit.wangfei.viewpagertablayout.R;

public class Utils {

    /**获取Toolbar的高度*/
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    /**获取自定义Tab的高度*/
//    public static int getTabsHeight(Context context) {
//        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
//    }
}
