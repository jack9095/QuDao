package com.xujun.contralayout.UI.weibo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.xujun.contralayout.R;
import com.xujun.contralayout.UI.ListFragment;
import com.xujun.contralayout.UI.weibo.behavior.WeiboHeaderPagerBehavior;
import com.xujun.contralayout.base.BaseFragmentAdapter;
import com.xujun.contralayout.base.BaseMVPActivity;
import com.xujun.contralayout.base.mvp.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

public class WeiboSampleActivity extends BaseMVPActivity implements WeiboHeaderPagerBehavior.OnPagerStateListener {

    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;

    String[] mTitles = new String[]{
            "主页", "微博", "相册"
    };
    private View mHeaderView;
    private WeiboHeaderPagerBehavior mHeaderPagerBehavior;
    private View mIvBack;

    @Override
    protected IBasePresenter setPresenter() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_weibo_sample;
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mHeaderView = findViewById(R.id.id_weibo_header);
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleBack();
            }
        });
        mIvBack.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setupViewPager();
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)
                mHeaderView.getLayoutParams();
        mHeaderPagerBehavior = (WeiboHeaderPagerBehavior) layoutParams.getBehavior();
        mHeaderPagerBehavior.setPagerStateListener(this);
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);


        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPagerClosed() {
        Toast.makeText(this, "关闭了", Toast.LENGTH_SHORT).show();
        mIvBack.setVisibility(View.VISIBLE);
        for(Fragment fragment:mFragments){
            ListFragment listFragment= (ListFragment) fragment;
            listFragment.tooglePager(false);
        }
    }

    @Override
    public void onPagerOpened() {
        Toast.makeText(this, "打开了", Toast.LENGTH_SHORT).show();
        mIvBack.setVisibility(View.INVISIBLE);
        for(Fragment fragment:mFragments){
            ListFragment listFragment= (ListFragment) fragment;
            listFragment.tooglePager(true);
        }
    }

    @Override
    public void onBackPressed() {
        handleBack();
    }

    private void handleBack() {
        if(mHeaderPagerBehavior.isClosed()){
            mHeaderPagerBehavior.openPager();
            return;
        }
        finish();
    }
}
