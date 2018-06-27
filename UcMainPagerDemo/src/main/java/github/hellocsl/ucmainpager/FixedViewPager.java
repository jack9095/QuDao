package github.hellocsl.ucmainpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author meitu.xujun  on 2017//2 15:33
 * @version 0.1
 */

public class FixedViewPager extends ViewPager {
    private boolean mIsScrollable=true;

    public FixedViewPager(Context context) {
        super(context);
    }

    public FixedViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean isScrollable) {
        this.mIsScrollable = isScrollable;
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent arg0) { if (mIsScrollable) return super.onInterceptTouchEvent(arg0); else return false; }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mIsScrollable) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }

    }
}
