package com.xujun.contralayout.UI.zhihu;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xujun.contralayout.R;
import com.xujun.contralayout.UI.ItemFragement;
import com.xujun.contralayout.adapter.ZhiHuAdapter;
import com.xujun.contralayout.utils.AnimatorUtil;

import java.util.ArrayList;
import java.util.List;

public class ZhiHuHomeActivity extends AppCompatActivity {

    FrameLayout mFl;
    RadioGroup mRg;

    private AppBarLayout mAppBarLayout;

    public static final String TAG = "xujun";

    private int mCurrentTab = 0;

    public static final String[] mTiltles = new String[]{
            "home", "course", "direct", "me"
    };
    private List<Fragment> mFragments;
    private Fragment mCurFragment;
    private ZhiHuAdapter mZhiHuAdapter;

    private int mAppBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_home);
        initView();
        initEvent();
        initHeaderAndFooter();
    }

    private void initHeaderAndFooter() {
        mAppBarLayout.post(new Runnable() {
            @Override
            public void run() {
                mAppBarHeight = mAppBarLayout.getHeight();
            }
        });
    }

    private void initEvent() {
        ((RadioButton) mRg.getChildAt(mCurrentTab)).setChecked(true);
        mFragments = new ArrayList<>();


        mFragments.add(new HomeFragment());
        mFragments.add(ItemFragement.newInstance(mTiltles[1]));
        mFragments.add(ItemFragement.newInstance(mTiltles[2]));
        mFragments.add(new FourFragment());

        mCurFragment = mFragments.get(mCurrentTab);


        mZhiHuAdapter = new ZhiHuAdapter(this, mFragments, R.id.fl);
        mRg.setOnCheckedChangeListener(mZhiHuAdapter);
        mZhiHuAdapter.setFragmentToogleListener(new ZhiHuAdapter.FragmentToogleListener() {
            @Override
            public void onToogleChange(Fragment fragment, int currentTab) {
                if (currentTab == 0) {
                    setAppLayoutHeight(mAppBarHeight);
                } else {
                    setAppLayoutHeight(0);
                }
                Log.i(TAG, "onToogleChange: " + currentTab);
                mCurrentTab = currentTab;
            }
        });
        replace(mCurFragment);


    }

    private void setAppLayoutHeight(int height) {
        ViewGroup.LayoutParams layoutParams = mAppBarLayout.getLayoutParams();
        layoutParams.height = height;
        mAppBarLayout.setLayoutParams(layoutParams);
    }

    private void initView() {
        mFl = (FrameLayout) findViewById(R.id.fl);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
    }

    public void replace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (isBottomHide()) {
            ((RadioButton) mRg.getChildAt(0)).setChecked(true);
            if (mCurrentTab == 0) {
                AnimatorUtil.showHeight(mAppBarLayout, 0, mAppBarHeight);
            }
            float translationY = mRg.getTranslationY();
            Log.i(TAG, "onBackPressed: translationY=" + translationY);
            AnimatorUtil.tanslation(mRg, mRg.getTranslationY(), 0);
        } else {
            super.onBackPressed();
        }


    }

    public boolean isBottomHide() {
        //     这里mRg的TranslationY之所以会改变，是因为我们改变了他的值
        float translationY = mRg.getTranslationY();
        return translationY > 0;

    }
}
