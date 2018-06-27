package com.xujun.contralayout.UI.viewPager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.xujun.contralayout.R;
import com.xujun.contralayout.UI.ListFragment;
import com.xujun.contralayout.base.BaseFragmentAdapter;
import com.xujun.contralayout.base.WriteLogUtil;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerNew extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;

    AppBarLayout mAppBarLayout;

    View mView;

    private static final String TAG = "ViewPagerNew";


    String[]  mTitles=new String[]{
            "主页","微博","相册"
    };


    private int mHeight;
    private View mSearchTitle;

    View ll_contain;
    View  ll_content;
    private int mHeightContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mView=findViewById(R.id.view);
        ll_content=findViewById(R.id.ll_content);
        ll_contain=findViewById(R.id.ll_contain);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mAppBarLayout=(AppBarLayout) findViewById(R.id.appBar);
        mSearchTitle = findViewById(R.id.search_title);
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight=findViewById(R.id.headview).getHeight();
                mHeightContent = ll_content.getHeight()-findViewById(R.id.tabs).getHeight();

                WriteLogUtil.i(" mHeight="+mHeight);
                WriteLogUtil.i(" mHeightContent="+mHeightContent);
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int abs = Math.abs(verticalOffset);
                WriteLogUtil.i(TAG," abs="+abs);
                if(abs<mHeight){
                    ll_contain.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = mSearchTitle.getLayoutParams();
                    layoutParams.height=0;
                    mSearchTitle.setLayoutParams(layoutParams);
                    mSearchTitle.setVisibility(View.GONE);
                }else{
                    mSearchTitle.setVisibility(View.VISIBLE);
                    ll_contain.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layoutParams = mSearchTitle.getLayoutParams();
                    layoutParams.height=100;
                    mSearchTitle.setLayoutParams(layoutParams);

                }
            /*    if(mHeightContent <=0){
                    return;
                }
                if(  abs< mHeightContent){

                    ll_content.setPadding(0,0,0,0);
                }else{

                    ll_content.setPadding(0,100,0,0);
                }*/
            }
        });





        setupViewPager();
    }



    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

       /* TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tab2);
        tabLayout2.setupWithViewPager(viewPager);*/
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
