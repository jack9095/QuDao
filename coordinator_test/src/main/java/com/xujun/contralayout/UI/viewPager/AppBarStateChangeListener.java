package com.xujun.contralayout.UI.viewPager;

import android.support.design.widget.AppBarLayout;

/**
 * @author meitu.xujun  on 2017/5/1 11:27
 * @version 0.1
 */

public  class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    State mLastState;

    public State getCurrentState() {
        return mCurrentState;
    }

    private State mCurrentState = State.INTERNEDIATE;

    OnStateChangedListener mOnStateChangedListener;

    public void setCurrentState(State currentState) {
        mCurrentState = currentState;
    }

    public State getLastState() {
        return mLastState;
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        mOnStateChangedListener = onStateChangedListener;
    }

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                mOnStateChangedListener.onExpanded();
            }
            mLastState=mCurrentState;
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                mOnStateChangedListener.onCollapsed();
            }
            mLastState=mCurrentState;
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.INTERNEDIATE) {
                if (mCurrentState == State.COLLAPSED) {
                    //由折叠变为中间状态时
                    mOnStateChangedListener.onInternediateFromCollapsed();
                } else if (mCurrentState == State.EXPANDED) {
                    mOnStateChangedListener.onInternediateFromExpand();
                }
                mLastState=mCurrentState;
                mCurrentState = State.INTERNEDIATE;
            }
            mOnStateChangedListener.onInternediate();
        }
    }



    public interface OnStateChangedListener{
        //展开
        void onExpanded();

        //折叠
        void onCollapsed();

        //展开向折叠时的中间状态
        void onInternediateFromExpand();

        //折叠向展开时的中间状态
        void onInternediateFromCollapsed();

        //中间状态
        void onInternediate();
    }
}
