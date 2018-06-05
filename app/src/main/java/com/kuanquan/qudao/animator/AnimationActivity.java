package com.kuanquan.qudao.animator;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fly.baselibrary.mvpEeventBus.CommonActivity;
import com.example.fly.baselibrary.mvpEeventBus.EventCenter;
import com.example.fly.baselibrary.utils.base.DisplayUtil;
import com.example.fly.baselibrary.utils.base.ScreenUtils;
import com.example.fly.myapplication.R;
import com.example.fly.myapplication.costomview.RoundProgressActivity;
import com.example.fly.myapplication.presenter.MainPresenter;

import org.greenrobot.eventbus.Subscribe;

public class AnimationActivity extends CommonActivity<MainPresenter> {

    private int screenHeight;
    private TextView mTimeNum;
    private LinearLayout mTimeLayout;
    private TextView mWorkNum;
    private LinearLayout mWorkLayout;
    private LinearLayout mBlueBg;
    private ImageView mReadyGo;
    private LinearLayout mHuoJianLayout;
    private ImageView mHuojian;
    private ImageView mQipao;

    private int huojianVisiableAnimationTime = 500; //火箭无→有的时间
    private int blueBgStartDely = huojianVisiableAnimationTime + 500;//蓝色背景开始掉落的时间
    private int blueBgGoneTime = 1000;//蓝色背景掉落时间
    private int huojianFlyTime = 1000;//火箭起飞时间
    private Runnable mRunnable;
    private ViewPropertyAnimatorListener mAnimatorListener;
//    private Typeface mTypeface;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huo_jian);
        initView();
        initData();
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected MainPresenter createPresent() {
        return new MainPresenter();
    }


    @Subscribe  @Override
    public void onEventMainThread(EventCenter eventCenter) {
        super.onEventMainThread(eventCenter);
    }

    protected void initView() {
        screenHeight = ScreenUtils.getScreenHeight(this);
//        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/MFYueHei_Noncommercial_Regular.otf");
        mHuoJianLayout = findViewById(R.id.huojian_layout);
        mHuojian = findViewById(R.id.huojian);
        mQipao = findViewById(R.id.qipao);
        mHuoJianLayout.setScaleX(0);
        mHuoJianLayout.setScaleY(0);

        mReadyGo = findViewById(R.id.ready_go);
        mTimeNum = (TextView) findViewById(R.id.time_num);
//        mTimeNum.setTypeface(mTypeface);
        mTimeLayout = (LinearLayout) findViewById(R.id.time_layout);
        mWorkNum = (TextView) findViewById(R.id.work_num);
//        mWorkNum.setTypeface(mTypeface);
        mWorkLayout = (LinearLayout) findViewById(R.id.work_layout);
        mBlueBg = (LinearLayout) findViewById(R.id.blue_bg);
        //设置蓝色渐变背景的渐变位置超出屏幕
        ViewGroup.LayoutParams params = mBlueBg.getLayoutParams();
        params.height = (int) (screenHeight * 2);
        mBlueBg.setLayoutParams(params);
        mBlueBg.setTranslationY((float) (-screenHeight));
        //设置readyGo的位置
        mReadyGo.setTranslationY(DisplayUtil.dip2px(mContext,50));

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mReadyGo.setVisibility(View.VISIBLE);
            }
        };
        mAnimatorListener = new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {}
            @Override
            public void onAnimationEnd(View view) {
                mQipao.setImageResource(R.drawable.qipao_animation);
                ((Animatable)mQipao.getDrawable()).start();
            }
            @Override
            public void onAnimationCancel(View view) {}
        };
        //隐藏学习时间和作业次数
        setTimeLayoutVisiable(View.GONE);
        setWorkLayoutVisiable(View.GONE);
    }

    protected void initData() {
        doAnimatior();
    }

    public void doAnimatior() {
        //火箭显示
        ViewCompat.animate(mHuoJianLayout).scaleX(1).scaleY(1).setDuration(huojianVisiableAnimationTime).setListener(mAnimatorListener).start();
        //背景掉落
        ViewCompat.animate(mBlueBg).translationY(screenHeight).setDuration(blueBgGoneTime).setStartDelay(blueBgStartDely).start();
        //字体隐藏
        setWorkLayoutVisiable(View.GONE);
        setTimeLayoutVisiable(View.GONE);
        //背景掉落到1/3处 readyGo显示
        mHandler.postDelayed(mRunnable, blueBgGoneTime / 3 + blueBgStartDely);
        ViewCompat.animate(mReadyGo).translationY(0).setDuration(500).setStartDelay(blueBgGoneTime / 3 + blueBgStartDely).setInterpolator(new OvershootInterpolator()).start();
        //背景快掉完时 火箭起飞
        ViewCompat.animate(mHuoJianLayout).translationY(-(screenHeight)).setDuration(huojianFlyTime).setStartDelay(blueBgGoneTime - blueBgGoneTime / 8).setInterpolator(new AnticipateInterpolator()).start();


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                cancelAnimation();
                // TODO 跳转
               startActivity(new Intent(AnimationActivity.this,RoundProgressActivity.class));
            }
        }, (int) ((float)getAnimationTotalTime()));
    }

    public int getAnimationTotalTime() {
        return (blueBgGoneTime - blueBgGoneTime / 8) + huojianFlyTime;
    }
    public void setTimeNum(int num) {
        mTimeNum.setText(num + "");
    }

    public void setWorkNum(int num) {
        mWorkNum.setText(num + "");
    }

    // 数据获取失败
    public void showErrorView() {
        View errorView = findViewById(R.id.error);
        errorView.setScaleY(0);
        errorView.setScaleX(0);
        errorView.setVisibility(View.VISIBLE);
        ViewCompat.animate(errorView).setDuration(1000).setInterpolator(new OvershootInterpolator()).scaleX(1).scaleY(1).rotation(720).start();
    }


    public void setTimeLayoutVisiable(int visiable) {
        if (visiable == View.VISIBLE) {
            mTimeLayout.setAlpha(0);
            mTimeLayout.setVisibility(visiable);
            ViewCompat.animate(mTimeLayout).alpha(1).setDuration(300).start();
        } else {
            if (mTimeLayout.getVisibility() == View.VISIBLE) {
                ViewCompat.animate(mTimeLayout).alpha(0).setDuration(200).setStartDelay(blueBgStartDely).start();
            } else {
                mTimeLayout.setVisibility(visiable);
            }
        }
    }

    public void setWorkLayoutVisiable(int visiable) {
        if (visiable == View.VISIBLE) {
            mWorkLayout.setAlpha(0);
            mWorkLayout.setVisibility(visiable);
            ViewCompat.animate(mWorkLayout).alpha(1).setDuration(300).start();
        } else {
            if (mWorkLayout.getVisibility() == View.VISIBLE) {
                ViewCompat.animate(mWorkLayout).alpha(0).setDuration(200).setStartDelay(blueBgStartDely).start();
            } else {
                mWorkLayout.setVisibility(visiable);
            }
        }
    }


    @Override
    protected void onDestroy() {
//        mTypeface = null;
        mAnimatorListener = null;
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
