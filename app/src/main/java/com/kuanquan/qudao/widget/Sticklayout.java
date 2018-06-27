package com.kuanquan.qudao.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/6/27.
 */

public class Sticklayout extends RelativeLayout {
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

    int y;
    @Override
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
        return super.dispatchTouchEvent(ev);
    }

    TouchListener mTouchListener;
    public void setTouchListener(TouchListener mTouchListener){
        this.mTouchListener = mTouchListener;
    }
    public interface TouchListener{
        void onTouchRListener();
    }
}
