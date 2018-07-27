package com.kuanquan.qudao.ui.fragment;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.ui.adapter.BaseLiveAdapter;
import com.kuanquan.qudao.utils.DataUtils;
import com.kuanquan.qudao.widget.HomeBanner;
import com.kuanquan.qudao.widget.ProjectViewpager;
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

    private ProjectViewpager project_view_tab;

    private MyPagerAdapter mAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private AFragment mAFragment; // 课程
    private BFragment mBFragment; // 文章
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"课程", "文章"};
    private LinearLayoutManager manager;
    private LinearLayout mLinearLayout; // 小圆点
    private ArrayList<ImageView> dotsList = new ArrayList<ImageView>();

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
        headerLayout = (LinearLayout) view.findViewById(R.id.home_header_layout);  // 隐藏的头部布局
        mViewPager = (ViewPager) view.findViewById(R.id.activity_collection_vp);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.activity_collection_tab_layout);
        project_view_tab = (ProjectViewpager) view.findViewById(R.id.project_view_tab);
//        manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
//        mRecyclerView.setLayoutManager(manager);

        project_view_tab.setData(DataUtils.getBannerData());

        mHomeBanner = view.findViewById(R.id.home_banner_layout);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_view);
        if (DataUtils.getBannerData() != null && DataUtils.getBannerData().size() > 0) {
            try {
                dotsList.clear();
                mLinearLayout.removeAllViews();
                for (int i = 0; i < DataUtils.getBannerData().size(); i++) {
                    ImageView view = new ImageView(getContext());
                    if (i == 0) {
                        view.setImageResource(R.drawable.dots_focus);
                    } else {
                        view.setImageResource(R.drawable.dots_normal);
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18, 18);
                    params.setMargins(5, 0, 5, 0);
                    mLinearLayout.addView(view, params);
                    dotsList.add(view);
                }
            }catch (Exception e){
                LogUtil.e("小圆点异常 = " + e);
            }
        }

        mHomeBanner.setData(DataUtils.getBannerData(),this);
        mHomeBanner.setScrollSpeed(mHomeBanner);

        initAppBarLayout();
    }

    // 初始化AppBarLayout
    private void initAppBarLayout(){
        LayoutTransition mTransition = new LayoutTransition();
        ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, "translationY", 0, 1.f).
                setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, addAnimator);

        //header layout height
        final int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_home_height);
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.home_appbar);
        mAppBarLayout.setLayoutTransition(mTransition);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                verticalOffset = Math.abs(verticalOffset);
                if ( verticalOffset >= headerHeight ){
                    isHideHeaderLayout = true;
                    LogUtil.e("移出屏幕了");
                    //当偏移量超过顶部layout的高度时，我们认为他已经完全移动出屏幕了
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
//                            mParams.setScrollFlags(0);
//                            headerLayout.setLayoutParams(mParams);
//                            headerLayout.setVisibility(View.GONE);
                        }
                    },100);
                }
            }
        });
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
        if ( isHideHeaderLayout ){
            isHideHeaderLayout = false;
//            ((MainTabFragment)mainTabFragmentAdapter.getFragments().get(0)).getRvList().scrollToPosition(0);
            headerLayout.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) headerLayout.getLayoutParams();
                    mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                    headerLayout.setLayoutParams(mParams);
                }
            },300);
        }
    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<dotsList.size();i++){
            if(position%dotsList.size() == i){
                dotsList.get(i).setImageResource(R.drawable.dots_focus);
            }else{
                dotsList.get(i).setImageResource(R.drawable.dots_normal);
            }
        }
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
