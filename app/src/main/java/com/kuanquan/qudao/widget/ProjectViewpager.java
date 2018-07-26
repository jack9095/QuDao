package com.kuanquan.qudao.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by fei.wang on 2018/6/13.
 */

public class ProjectViewpager extends ViewPager {

    private MyAdapter mAdapter;


    public ProjectViewpager(Context context) {
        super(context);
        init();
    }

    public ProjectViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setData(List<HomeBeanChild> bannerList, OnPageClickListener clickListener) {
        if (mAdapter == null || isDataChanged(mAdapter.getData(), bannerList)) {
            mAdapter = new MyAdapter(getContext(), bannerList);
            mAdapter.onPageClickListener = clickListener;
            setAdapter(mAdapter);
        }
    }


    public List getData() {
        if (mAdapter != null) {
            return mAdapter.getData();
        }
        return null;
    }

    /**
     * 判断banner页数据是否有变化
     *
     * @param old
     * @param newData
     * @return
     */
    private boolean isDataChanged(List<HomeBeanChild> old, List<HomeBeanChild> newData) {
        try {
            if (newData == null || newData.isEmpty()) {
                return false;
            } else if (old == null || old.isEmpty()) {
                return true;
            } else if (old.size() != newData.size()) {
                return true;
            } else {
                for (int i = 0; i < old.size(); i++) {
                    HomeBeanChild oldInfo = old.get(i);
                    if (i < newData.size()) {
                        HomeBeanChild newInfo = newData.get(i);
                        if (!oldInfo.equals(newInfo)) {
                            return true;
                        }
                    } else {
                        return true;
                    }

                }
            }
            return false;
        } catch (Exception e) {
            LogUtil.e("crash", "isDataChanged method");
            return true;
        }
    }

    private void init() {

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                HomeBeanChild bannersBean = mAdapter.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP://手指抬起
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, true);
    }

    public class MyAdapter extends PagerAdapter implements OnClickListener {
        public OnPageClickListener onPageClickListener;
        private Context mContext;
        private List<HomeBeanChild> mList;

        public MyAdapter(Context context, List<HomeBeanChild> list) {
            mList = list;
            mContext = context;
        }


        @Override
        public int getCount() {
            if (mList == null || mList.isEmpty()) {
                return 0;
            } else {
                return mList.size();

            }
        }

        public HomeBeanChild getItem(int position) {
            return mList.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            HomeBeanChild info = mList.get(position);
            View view = LayoutInflater.from(mContext).inflate(R.layout.new_home_banner_item, container, false);
            view.setTag(info);
            view.setOnClickListener(this);
            ImageView banner_image = view.findViewById(R.id.banner_image);
            GlideUtil.setImageUrl(getContext(), info.image, banner_image);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public List<HomeBeanChild> getData() {
            return mList;
        }

        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof HomeBeanChild) {
                HomeBeanChild info = (HomeBeanChild) v.getTag();
                if (onPageClickListener != null) {
                    onPageClickListener.onPageClick(info);
                }
            }
        }
    }


    public interface OnPageClickListener {
        void onPageClick(HomeBeanChild info);
    }


    /**
     * Viewpager 加入淡出淡入动画
     */
    public class TranslatePageTransformer implements PageTransformer {

        /**
         * 核心就是实现transformPage(View page, float position)这个方法
         **/
        @Override
        public void transformPage(View page, float position) {

            if (position < -1) {
                position = -1;
            } else if (position > 1) {
                position = 1;
            }

            float tempScale = position < 0 ? 1 + position : 1 - position;

            final float normalizedposition = Math.abs(Math.abs(position) - 1);
            page.setAlpha(normalizedposition);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                page.getParent().requestLayout();
            }
        }
    }

    public void setScrollSpeed(ViewPager mViewPager) {
        try {
            Class clazz = Class.forName("android.support.v4.view.ViewPager");
            Field f = clazz.getDeclaredField("mScroller");
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(getContext(), new LinearOutSlowInInterpolator());
            fixedSpeedScroller.setmDuration(1000);
            f.setAccessible(true);
            f.set(mViewPager, fixedSpeedScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 轮播滚动的时候实现平滑效果
     */
    public class FixedSpeedScroller extends Scroller {
        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }

}
