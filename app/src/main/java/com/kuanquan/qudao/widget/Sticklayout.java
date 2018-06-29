package com.kuanquan.qudao.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.example.fly.baselibrary.utils.useful.LogUtil;

/**
 * Created by Administrator on 2018/6/27.
 *
 */

public class Sticklayout extends LinearLayout {
    public Sticklayout(Context context) {
        super(context);
    }

    public Sticklayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Sticklayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Sticklayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    int y;
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                y = (int) ev.getY();
//                LogUtil.e("Sticklayout","按下事件   y = " + y);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int yUp = (int) ev.getY();
//                LogUtil.e("Sticklayout","移动事件     yUp = " + yUp);
//                if (yUp - y > 5) {
//                    mTouchListener.onTouchRListener();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                LogUtil.e("Sticklayout","抬起事件");
//
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);  // 请求父控件不拦截子控件的触摸事件
        return super.dispatchTouchEvent(ev);
    }

   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int yUp = (int) ev.getY();
                if (yUp - y > 10) {
                    mTouchListener.onTouchRListener();
                }
                break;
        }
        return true;
    }*/

    TouchListener mTouchListener;
    public void setTouchListener(TouchListener mTouchListener){
        this.mTouchListener = mTouchListener;
    }
    public interface TouchListener{
        void onTouchRListener();
    }
}
