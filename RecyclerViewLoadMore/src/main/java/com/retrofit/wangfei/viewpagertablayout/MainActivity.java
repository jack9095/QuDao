package com.retrofit.wangfei.viewpagertablayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.retrofit.wangfei.viewpagertablayout.adapter.SectionsPagerAdapter;


/**
 * Created by Android Studio
 * User: fei.wang
 * Date: 2016-04-14
 * Time: 9:57
 * QQ: 929728742
 * Description: ViewPager和TabLayout结合使用
 *
 * 在有Toolbar控件的xml布局中添加app:layout_scrollFlags="scroll|enterAlways"就可以显示隐藏Toolbar
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppBarLayout appbar;
    Toolbar toolbar;
    TabLayout mTabLayout;
    ViewPager mViewpager;
    FloatingActionButton fab;

    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();  //去掉标题栏
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewpager = (ViewPager) findViewById(R.id.container);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(this);
        }
        initToolbar();
        initViewPagerAndTabs();
        // AppBar的监听
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                toolbar.setBackgroundColor(Color.argb((int) (percentage * 255), 19, 121, 214));
            }
        });
    }

    private void initViewPagerAndTabs() {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this);
        mViewpager.setOffscreenPageLimit(3);//设置viewpager预加载页面数
        mViewpager.setAdapter(sectionsPagerAdapter);  // 给Viewpager设置适配器
//        mViewpager.setCurrentItem(1); // 设置当前显示在哪个页面
        mTabLayout.setupWithViewPager(mViewpager);
    }

    /**初始化Toolbar*/
    private void initToolbar() {
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}
