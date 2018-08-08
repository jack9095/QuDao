package com.retrofit.wangfei.viewpagertablayout.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.retrofit.wangfei.viewpagertablayout.R;
import com.retrofit.wangfei.viewpagertablayout.fragment.HomeFragment;
import com.retrofit.wangfei.viewpagertablayout.fragment.MessageFragment;
import com.retrofit.wangfei.viewpagertablayout.fragment.PicturesFragment;

/**
 * Created by Android Studio
 * User: fei.wang
 * Date: 2016-04-14
 * Time: 9:57
 * QQ: 929728742
 * Description: ViewPager选项卡的适配器
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return PicturesFragment.newInstance();
            case 2:
                return MessageFragment.newInstance();
            default:
                return HomeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.main_tab_news);
            case 1:
                return context.getResources().getString(R.string.main_tab_picture);
            case 2:
                return context.getResources().getString(R.string.main_tab_weather);
        }
        return null;
    }
}
