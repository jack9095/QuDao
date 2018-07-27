package com.kuanquan.qudao.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
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

    public void setData(List<HomeBeanChild> bannerList) {
        mAdapter = new MyAdapter(getContext(), bannerList);
        setAdapter(mAdapter);
    }

//    public void setData(List<HomeBeanChild> bannerList, OnPageClickListener clickListener) {
//        mAdapter = new MyAdapter(getContext(), bannerList);
//        mAdapter.onPageClickListener = clickListener;
//        setAdapter(mAdapter);
//    }


    public List getData() {
        if (mAdapter != null) {
            return mAdapter.getData();
        }
        return null;
    }

    private void init() {

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
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

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            HomeBeanChild info = mList.get(position);
            View view = LayoutInflater.from(mContext).inflate(R.layout.new_home_banner_item_f, container, false);
            view.setTag(info);
            view.setOnClickListener(this);
            HomeItemView mHomeItemView = view.findViewById(R.id.project_view_pager_layout);
//            GlideUtil.setImageUrl(getContext(), info.image, banner_image);
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
//                if (onPageClickListener != null) {
//                    onPageClickListener.onPageClick(info);
//                }
            }
        }
    }


//    public OnPageClickListener onPageClickListener;
//    public interface OnPageClickListener {
//        void onPageClick(HomeBeanChild info);
//    }

}
