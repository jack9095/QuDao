//package com.kuanquan.qudao.utils;
//
//import android.content.Context;
//import android.graphics.Rect;
//import android.os.Build;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.bumptech.glide.Glide;
//import com.example.fly.aaa.R;
//import com.example.fly.aaa.bean.BannersAndHeadlineResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class WellChoosenViewPager extends ViewPager {
//    private MyAdapter mAdapter;
//    private Handler mHandler;
//    private Runnable mRunnable;
//    private int time = 5000;
//    private Rect mRect;
//    private boolean isFmVisiable = true;
//
//
//    public WellChoosenViewPager(Context context) {
//        super(context);
//        init();
//    }
//
//    public WellChoosenViewPager(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public void setData(ArrayList<BannersAndHeadlineResponse.BannersBean> bannerList, OnPageClickListener clickListener) {
//        if (mAdapter == null || isDataChanged(mAdapter.getData(), bannerList)) {
//            mAdapter = new MyAdapter(getContext(), bannerList);
//            mAdapter.onPageClickListener = clickListener;
//            setAdapter(mAdapter);
//            setCurrentItem(bannerList.size() * 10000);
//        }
//        mHandler.removeCallbacksAndMessages(null);
//        mHandler.postDelayed(mRunnable, time);
//    }
//
//
//    public List getData() {
//        if (mAdapter != null) {
//            return mAdapter.getData();
//        }
//        return null;
//    }
//
//    /**
//     * 判断banner页数据是否有变化
//     *
//     * @param old
//     * @param newData
//     * @return
//     */
//    private boolean isDataChanged(ArrayList<BannersAndHeadlineResponse.BannersBean> old, ArrayList<BannersAndHeadlineResponse.BannersBean> newData) {
//        try {
//            if (newData == null || newData.isEmpty()) {
//                return false;
//            } else if (old == null || old.isEmpty()) {
//                return true;
//            } else if (old.size() != newData.size()) {
//                return true;
//            } else {
//                for (int i = 0; i < old.size(); i++) {
//                    BannersAndHeadlineResponse.BannersBean oldInfo = old.get(i);
//                    if (i < newData.size()) {
//                        BannersAndHeadlineResponse.BannersBean newInfo = newData.get(i);
//                        if (!oldInfo.equals(newInfo)) {
//                            return true;
//                        }
//                    } else {
//                        return true;
//                    }
//
//                }
//            }
//            return false;
//        } catch (Exception e) {
//            Log.e("crash", "isDataChanged method");
//            return true;
//        }
//    }
//
//    private void init() {
//        mHandler = new Handler(Looper.getMainLooper());
////        setOffscreenPageLimit(3);
////        setPageMargin(-75);
////        setPageTransformer(true, new ScalePageTransformer());
//
//        setPageTransformer(false, new ScalePageTransformer());
//        mRunnable = new Runnable() {
//            @Override
//            public void run() {
//                int currentItem = getCurrentItem();//返回了当前界面的索引
//                //跳转到下一页
//                if (currentItem == mAdapter.getCount() - 1) {
//                    setCurrentItem(0);
//                } else {
//                    setCurrentItem(currentItem + 1);
//                }
//                if (mHandler != null) {
//                    mHandler.postDelayed(mRunnable, time);
//                }
//            }
//        };
//
//        mRect = new Rect();
//
//        addOnPageChangeListener(new OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
//            @Override
//            public void onPageSelected(int position) {
//                BannersAndHeadlineResponse.BannersBean bannersBean = mAdapter.getItem(position);
//                if (bannersBean != null && !TextUtils.isEmpty(bannersBean.h5Url) && isFmVisiable) {
//                    getLocalVisibleRect(mRect);
//                    if (mRect.top >= 0) {
//                        BannerAdvStatisticsUtil.getInstance().saveAdvShowCount(bannersBean.videoName,bannersBean.h5Url);
//                    }
//                }
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
////        setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                switch (event.getAction()) {
////                    case MotionEvent.ACTION_DOWN://手指按下
////                        //暂停轮播
////                        mHandler.removeCallbacksAndMessages(null);
////                        break;
////                    case MotionEvent.ACTION_UP://手指抬起
////                        //继续轮播
////                        mHandler.postDelayed(mRunnable, 5000);
////                        break;
////                }
////                return false;
////            }
////        });
//    }
//
//    public void setFmVisiable(boolean isVisiable) {
//        this.isFmVisiable = isVisiable;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_UP://手指抬起
//                //继续轮播
//                mHandler.removeCallbacksAndMessages(null);
//                mHandler.postDelayed(mRunnable, time);
////                System.out.println("继续轮播啊啊啊啊");
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN://手指按下
//                //暂停轮播
//                mHandler.removeCallbacksAndMessages(null);
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    public void onDestroy() {
//        mHandler.removeCallbacksAndMessages(null);
//    }
//
//
//    public class MyAdapter extends PagerAdapter implements OnClickListener {
//        public OnPageClickListener onPageClickListener;
//        private Context mContext;
//        private ArrayList<BannersAndHeadlineResponse.BannersBean> mList;
//
//        public MyAdapter(Context context, ArrayList<BannersAndHeadlineResponse.BannersBean> list) {
//            mList = list;
//            mContext = context;
//        }
//
//
//        @Override
//        public int getCount() {
//            if (mList == null || mList.isEmpty()) {
//                return 0;
//            } else {
//                return Integer.MAX_VALUE;
//
//            }
//        }
//
//        public BannersAndHeadlineResponse.BannersBean getItem(int position) {
//            int pos = position % mList.size();
//            if (pos >= 0 && pos < mList.size()) {
//                BannersAndHeadlineResponse.BannersBean info = mList.get(pos);
//                return info;
//            }
//            return null;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            int pos = position % mList.size();
//            if (pos >= 0 && pos < mList.size()) {
//                BannersAndHeadlineResponse.BannersBean info = mList.get(pos);
//                View view = LayoutInflater.from(mContext).inflate(R.layout.new_main_course_banner_item, container, false);
//                view.setTag(info);
//                view.setOnClickListener(this);
//                ImageView picture = (ImageView) view.findViewById(R.id.imageview);
//                ImageView vipIcon = (ImageView) view.findViewById(R.id.vip_ico);
//                TextView desc = (TextView) view.findViewById(R.id.desc);
//                if (!TextUtils.isEmpty(info.videoName)) {
//                    desc.setVisibility(VISIBLE);
//                    desc.setText(info.videoName);
//                } else {
//                    desc.setVisibility(GONE);
//                }
//
//                if (info.watchPermission == 1) {
//                    vipIcon.setVisibility(VISIBLE);
//                    vipIcon.setImageResource(R.drawable.ic_launcher_background);
//                } else if (info.watchPermission == 2) {
//                    vipIcon.setVisibility(VISIBLE);
//                    vipIcon.setImageResource(R.drawable.ic_launcher_background);
//                } else {
//                    vipIcon.setVisibility(GONE);
//                }
//
//                if (!TextUtils.isEmpty(info.videoImg) && !info.videoImg.contains(".gif")) {
//                    Glide.with(mContext).load(info.videoImg).asBitmap().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).centerCrop().transform(new GlideRoundTransform(mContext, 1)).into(picture);
//                } else {
//                    Glide.with(mContext).load(info.videoImg).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).centerCrop().into(picture);
//                }
//                container.addView(view);
//                return view;
//            } else {
//                return null;
//            }
//
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        public ArrayList<BannersAndHeadlineResponse.BannersBean> getData() {
//            return mList;
//        }
//
//        @Override
//        public void onClick(View v) {
//            mHandler.removeCallbacksAndMessages(null);
//            mHandler.postDelayed(mRunnable, time);
////            System.out.println("点击后继续轮播啊啊啊啊");
//
//            if (v.getTag() instanceof BannersAndHeadlineResponse.BannersBean) {
//                BannersAndHeadlineResponse.BannersBean info = (BannersAndHeadlineResponse.BannersBean) v.getTag();
//                if (onPageClickListener != null) {
//                    onPageClickListener.onPageClick(info);
//                    if (info != null && !TextUtils.isEmpty(info.h5Url)) {
//                        BannerAdvStatisticsUtil.getInstance().saveAdvClickCount(info.videoName,info.h5Url);
//                    }
//                }
//            }
//        }
//    }
//
//
//    /**
//     * viewPager实现中间放大,两边缩小
//     */
//
//    public class ScalePageTransformer implements PageTransformer {
//        /**
//         * 最大的item
//         */
//        public static final float MAX_SCALE = 1f;
//        /**
//         * 最小的item
//         */
//        public static final float MIN_SCALE = 0.9f;
//
//        /**
//         * 核心就是实现transformPage(View page, float position)这个方法
//         **/
//        @Override
//        public void transformPage(View page, float position) {
//
//            if (position < -1) {
//                position = -1;
//            } else if (position > 1) {
//                position = 1;
//            }
//
//            float tempScale = position < 0 ? 1 + position : 1 - position;
//
//            float slope = (MAX_SCALE - MIN_SCALE) / 1;
//            //一个公式
//            float scaleValue = MIN_SCALE + tempScale * slope;
//            page.setScaleX(scaleValue);
//            page.setScaleY(scaleValue);
//
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//                page.getParent().requestLayout();
//            }
//        }
//    }
//
//    public interface OnPageClickListener {
//        void onPageClick(BannersAndHeadlineResponse.BannersBean info);
//    }
//
//
//}