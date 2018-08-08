package com.retrofit.wangfei.viewpagertablayout.util;

import android.support.design.widget.AppBarLayout;

/**
 * Created by Administrator on 2018/6/7.
 * CollapsingToolbarLayout折叠展开监听类
 *
 * 使用方法：
    mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
         @Override
         public void onStateChanged(AppBarLayout appBarLayout, State state) {
             Log.d("STATE", state.name());
             if( state == State.EXPANDED ) {
                //展开状态
             }else if(state == State.COLLAPSED){
                //折叠状态
             }else {
                //中间状态
             }
         }
    });
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE);
            }
            mCurrentState = State.IDLE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}

