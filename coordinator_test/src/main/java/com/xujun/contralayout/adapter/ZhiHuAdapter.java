package com.xujun.contralayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.List;

/**
 * @author xujun  on 2016/12/2.
 * @email gdutxiaoxu@163.com
 */

public class ZhiHuAdapter implements RadioGroup
        .OnCheckedChangeListener {

    int currentTab = 0;
    FragmentActivity mFragmentActivity;
    int mContentId;

    List<Fragment> mFragmentList;

    protected FragmentToogleListener mFragmentToogleListener;

    public ZhiHuAdapter(FragmentActivity fragmentActivity, List<Fragment> fragmentList, int
            contentId) {
        this.mFragmentList = fragmentList;
        this.mFragmentActivity = fragmentActivity;
        this.mContentId = contentId;
    }

    public void setFragmentToogleListener(FragmentToogleListener fragmentToogleListener) {
        this.mFragmentToogleListener = fragmentToogleListener;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if (radioGroup.getChildAt(i).getId() == checkId) {
                //  即将要展示的Fragment
                Fragment target = mFragmentList.get(i);
                Fragment currentFragment = getCurrentFragment();
                currentFragment.onPause();

                FragmentTransaction fragmentTransaction = getFragmentTransaction();
                if (target.isAdded()) {
                    target.onResume();
                    fragmentTransaction.show(target).hide(currentFragment);

                } else {
                    fragmentTransaction.add(mContentId, target).show(target).hide(currentFragment);
                }
                fragmentTransaction.commit();
                currentTab = i;

                if (mFragmentToogleListener != null) {
                    mFragmentToogleListener.onToogleChange(target, currentTab);
                }

            }
        }

    }

    private FragmentTransaction getFragmentTransaction() {
        return mFragmentActivity
                .getSupportFragmentManager().beginTransaction();
    }

    public Fragment getCurrentFragment() {
        return mFragmentList.get(currentTab);
    }

    public interface FragmentToogleListener {
        void onToogleChange(Fragment fragment, int currentTab);
    }
}
