package com.kuanquan.qudao.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.adapter.MainFragmentpagerAdapter;
import com.kuanquan.qudao.ui.fragment.HomeFragment;
import com.kuanquan.qudao.ui.fragment.LiveFragment;
import com.kuanquan.qudao.ui.fragment.MyFragment;
import com.kuanquan.qudao.ui.fragment.NotiFragment;
import com.kuanquan.qudao.help.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * 主页面
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> fragmentList;
    private ViewPager mViewPager;
    private Fragment homeFragment,liveFragment,myFragment,notiFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // 小红点
                    Badge badge = new QBadgeView(MainActivity.this).bindTarget(findViewById(R.id.navigation_home)).setBadgeNumber(5);
                    badge.setGravityOffset(15,0,true);  // 设置外边距
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notifications_f:
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_home);
                    break;
                case 1:
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
                    break;
                case 2:
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_notifications);
                    break;
                case 3:
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_notifications_f);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*if (getSupportActionBar() != null) {
            getSupportActionBar().hide();  //去掉标题栏
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentList = new ArrayList<>();

        homeFragment = new HomeFragment();
        liveFragment = new LiveFragment();
        notiFragment = new NotiFragment();
        myFragment = new MyFragment();

        fragmentList.add(homeFragment);
        fragmentList.add(liveFragment);
        fragmentList.add(notiFragment);
        fragmentList.add(myFragment);

//        Toolbar mToolBar = findViewById(R.id.toolbar);
//        setSupportActionBar(mToolBar);
//        mToolBar.setTitle(getResources().getString(R.string.app_name));
//        mToolBar.setNavigationIcon(R.mipmap.left_back);
//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        mViewPager = findViewById(R.id.viewpager);
        mBottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //给ViewPager设置适配器
        MainFragmentpagerAdapter adapter = new MainFragmentpagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);//设置当前显示标签页为第一页
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

}
