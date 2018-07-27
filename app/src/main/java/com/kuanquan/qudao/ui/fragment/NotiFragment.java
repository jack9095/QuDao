package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.utils.DataUtils;
import com.kuanquan.qudao.widget.HomeBanner;
import com.kuanquan.qudao.widget.Sticklayout;

import java.util.ArrayList;

/**
 * 主页的消息页面
 */
public class NotiFragment extends CommonFragment implements HomeBanner.OnPageClickListener {
    //AppBarLayout
    private AppBarLayout mAppBarLayout;
    //顶部HeaderLayout
    private LinearLayout headerLayout;
    //是否隐藏了头部
    private boolean isHideHeaderLayout = false;

    private HomeBanner mHomeBanner;

    private MyPagerAdapter mAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private AFragment mAFragment; // 课程
    private BFragment mBFragment; // 文章
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"课程", "文章"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_noti, container, false);
    }

    @Override
    protected void initView() {
        headerLayout = (LinearLayout) view.findViewById(R.id.home_header_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.activity_collection_vp);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.activity_collection_tab_layout);

        mHomeBanner = view.findViewById(R.id.home_banner_layout);
        mHomeBanner.setData(DataUtils.getBannerData(),this);
        mHomeBanner.setScrollSpeed(mHomeBanner);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAFragment = new AFragment();
        mBFragment = new BFragment();
        mFragments.clear();
        mFragments.add(mAFragment);
        mFragments.add(mBFragment);

//        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mAdapter = new MyPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onPageClick(HomeBeanChild info) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
