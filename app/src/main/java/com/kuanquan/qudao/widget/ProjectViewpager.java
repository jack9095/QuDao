package com.kuanquan.qudao.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.bean.HomeTab;
import com.kuanquan.qudao.utils.CollectionsUtil;

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

    public void setData(List<HomeTab> bannerList, OnPageItemClickListener clickListener) {
        mAdapter = new MyAdapter(getContext(), bannerList);
        onPageClickListener = clickListener;
        setAdapter(mAdapter);
    }


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
                if (onPageClickListener != null) {
                    onPageClickListener.onTabPageSelected(position);
                }
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

    public class MyAdapter extends PagerAdapter {
        private Context mContext;
        private List<HomeTab> mList;

        public MyAdapter(Context context, List<HomeTab> list) {
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
            HomeTab info = mList.get(position);
            View view = LayoutInflater.from(mContext).inflate(R.layout.new_home_banner_item_f, container, false);
            view.setTag(info);
//            view.setOnClickListener(this);
            HomeItemView mHomeItemView = view.findViewById(R.id.project_view_pager_layout);
            if (info.lists != null && info.lists.size() > 0) {
                if (mList.size() == 1) {
                    for (int i = 0; i < 5; i++) {
                        mHomeItemView.views.get(i).setVisibility(View.GONE);
                    }

                    if (onPageClickListener != null) {
                        onPageClickListener.onTabOther(1);
                    }
                }else{
                    if (onPageClickListener != null) {
                        onPageClickListener.onTabOther(2);
                    }
                }
                for (int i = 0; i < info.lists.size(); i++) {
                    if (i < 5) {
                        mHomeItemView.views.get(i).setVisibility(View.VISIBLE);
                        mHomeItemView.views.get(i).setOnClickListener(new MyClick(info.lists.get(i)));
                        CollectionsUtil.setTextView(mHomeItemView.textViews.get(i), info.lists.get(i).title);
                        GlideUtil.setImageCircle(getContext(), info.lists.get(i).image, mHomeItemView.imageViews.get(i));
                    }
                }
//                }else {
//                    for (int i = 0; i < info.lists.size(); i++) {
//                        if (i < 5) {
//                            mHomeItemView.views.get(i).setVisibility(View.VISIBLE);
//                            mHomeItemView.views.get(i).setOnClickListener(new MyClick(info.lists.get(i)));
//                            CollectionsUtil.setTextView(mHomeItemView.textViews.get(i), info.lists.get(i).title);
//                            GlideUtil.setImage(getContext(), info.lists.get(i).image, mHomeItemView.imageViews.get(i));
//                        }
//                    }
//                }
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public List<HomeTab> getData() {
            return mList;
        }

        class MyClick implements OnClickListener {
            HomeBeanChild info;

            public MyClick(HomeBeanChild info) {
                this.info = info;
            }

            @Override
            public void onClick(View view) {
                if (onPageClickListener != null) {
                    onPageClickListener.onPageItemClick(info);
                }
            }
        }
    }


    public OnPageItemClickListener onPageClickListener;

    public interface OnPageItemClickListener {
        void onPageItemClick(HomeBeanChild info);

        void onTabPageSelected(int position);
        void onTabOther(int type);  // 1 隐藏  2 显示
    }

}
