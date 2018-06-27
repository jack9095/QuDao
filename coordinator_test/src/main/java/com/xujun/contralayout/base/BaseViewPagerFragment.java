package com.xujun.contralayout.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xujun.contralayout.R;

/**
 * @author xujun  on 2016/12/2.
 * @email gdutxiaoxu@163.com
 */

public abstract class BaseViewPagerFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    protected BaseFragmentAdapter mAdapter;


    @Override
    protected void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager;
    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter= getViewPagerAdapter();
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    protected abstract BaseFragmentAdapter getViewPagerAdapter() ;

    @Override
    public void fetchData() {

    }
}
