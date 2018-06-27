package com.xujun.contralayout.UI.viewPager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xujun.contralayout.R;
import com.xujun.contralayout.UI.ListFragment;
import com.xujun.contralayout.base.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerParallaxSnap extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;

    String[]  mTitles=new String[]{
            "主页","微博","相册"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_parallax_snap);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("唐嫣");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupViewPager();
    }

    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mFragments=new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter =
                new BaseFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);



        viewPager.setAdapter(adapter);
    }
}
