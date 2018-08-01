package com.kuanquan.qudao.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.fragment.HomeFragment;
import com.kuanquan.qudao.ui.fragment.LiveFragment;
import com.kuanquan.qudao.ui.fragment.MyFragment;
import com.kuanquan.qudao.ui.fragment.NotiFragment;
import com.kuanquan.qudao.widget.BottomTabView;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private BottomTabView mBottomTabView;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LiveFragment());
        fragmentList.add(new NotiFragment());
        fragmentList.add(new MyFragment());
        mBottomTabView = findViewById(R.id.bottom_tab_view);
        mBottomTabView.setOnClick(this);
        fragmentManager = getSupportFragmentManager();
        showFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage://首页
                currentIndex = 0;
                mBottomTabView.changeState(true, false, false, false);
                break;
            case R.id.goods_page://商品
                currentIndex = 1;
                mBottomTabView.changeState(false, true, false, false);
                break;
            case R.id.shopping_cart_page://进货单
                currentIndex = 2;
                mBottomTabView.changeState(false, false, true, false);
                break;
            case R.id.user_page://会员
                currentIndex = 3;
                mBottomTabView.changeState(false, false, false, true);
                break;
        }
        showFragment();
    }

    // 显示选中的 Fragment
    private void showFragment() {
        Fragment currentFragment = fragmentList.get(currentIndex);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(!currentFragment.isAdded()){ //如果当前的fragment没有添加，添加当前的fragment
            ft.add(R.id.fragment_container, currentFragment);
        }
        for(int x=0;x<fragmentList.size();x++){
            if(fragmentList.get(x) != currentFragment){
                ft.hide(fragmentList.get(x));
            }else {
                ft.show(fragmentList.get(x));
            }
        }
        ft.commit();
    }

    @Override @SuppressLint("MissingSuperCall")
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}