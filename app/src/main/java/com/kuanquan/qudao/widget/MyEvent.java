package com.kuanquan.qudao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.fly.baselibrary.utils.useful.LogUtil;

/**
 * Created by able on 2018/4/3.
 */

public class MyEvent extends LinearLayout {
    private static String TAG = MyEvent.class.getSimpleName();
    public MyEvent(Context context) {
        super(context);
    }

    public MyEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEvent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e(TAG,"*********MyEvent*******onTouchEvent********************" + super.onTouchEvent(event));
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.e(TAG,"********MyEvent********dispatchTouchEvent********************" + super.dispatchTouchEvent(ev));
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.e(TAG,"********MyEvent********dispatchTouchEvent********************" + super.onInterceptTouchEvent(ev));
        return false;
    }
}
