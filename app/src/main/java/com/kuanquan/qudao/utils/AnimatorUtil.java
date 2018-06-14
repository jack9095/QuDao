package com.kuanquan.qudao.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by a on 2017/6/22.
 */

public class AnimatorUtil {


//·使用属性占位符PropertyValueHolder：
//    PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("scaleX", 0, 2, 1);
//    PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleY", 0, 2,1);
//    PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
//
//    ObjectAnimator objectAnimator5 = ObjectAnimator.ofPropertyValuesHolder(iv, pvh1, pvh2,pvh3);
//        	objectAnimator5.setDuration(5000);
//        	objectAnimator5.start();
//
//·在动画组中同时播放：
//    ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(iv, "rotationX", 0,360);
//    ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(iv, "rotationY", 0, 360);
//    ObjectAnimator objectAnimator7 = ObjectAnimator.ofFloat(iv, "alpha", 0, 1);
//
//    AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.setDuration(3000);
//		animatorSet.playTogether(objectAnimator5,objectAnimator6,objectAnimator7);


    public static final int DEFAULT_DURATION = 1000;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Anim{
        String ALPHA = "alpha";
        String TRANSLATION_X = "translationX";
        String TRANSLATION_Y = "translationY";
        String X = "x";
        String Y = "Y";
        String ROTATION = "rotation";
        String ROTATION_X = "rotationX";
        String ROTATION_Y = "rotationY";
        String SCALE_X = "scaleX";
        String SCALE_Y = "scaleY";
    }

    /**
     * 淡入效果
     *
     * @param target 动画载体
     */
    public static void animAplhaIn(View target, long duration, final AnimListener listener) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(target, Anim.ALPHA, 0, 1);
        oa = baseOption(oa, duration
                , new AccelerateDecelerateInterpolator()
                , 0, ValueAnimator.RESTART
                , new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (listener != null) {
                            listener.onStart(animation);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (listener != null) {
                            listener.onEnd(animation);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if (listener != null) {
                            listener.onCancel(animation);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        if (listener != null) {
                            listener.onRepeat(animation);
                        }
                    }
                }, new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        if (listener != null) {
                            listener.onUpdate(animation);
                        }
                    }
                });
        oa.start();
    }

    /**
     * 淡出效果
     *
     * @param target 动画载体
     */
    public static void animAplhaOut(View target, long duration, final AnimListener listener) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(target, Anim.ALPHA, 1, 0);
        oa = baseOption(oa, duration
                , new AccelerateDecelerateInterpolator()
                , 0, ValueAnimator.RESTART
                , new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (listener != null) {
                            listener.onStart(animation);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (listener != null) {
                            listener.onEnd(animation);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if (listener != null) {
                            listener.onCancel(animation);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        if (listener != null) {
                            listener.onRepeat(animation);
                        }
                    }
                }, new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        if (listener != null) {
                            listener.onUpdate(animation);
                        }
                    }
                });
        oa.start();
    }

    /**
     * 淡出效果
     *
     * @param target 动画载体
     */
    public static void animAplhaOut(final View target, long duration) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(target, Anim.ALPHA, 1, 0);
        oa = baseOption(oa, duration
                , new AccelerateDecelerateInterpolator()
                , 0, ValueAnimator.RESTART
                , new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        target.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }, new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                    }
                });
        oa.start();
    }


    private static ObjectAnimator baseOption(ObjectAnimator animator, long duration
            , TimeInterpolator timeInterpolator, int repeatCount, int repeatMode
            , Animator.AnimatorListener animatorListener
            , ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        animator.setDuration(duration);
        animator.setInterpolator(timeInterpolator);
        animator.setRepeatCount(repeatCount);
        animator.setRepeatMode(repeatMode);
        animator.addListener(animatorListener);
        animator.addUpdateListener(animatorUpdateListener);
        return animator;
    }

    public abstract static class AnimListener {
        public void onEnd(Animator animation) {

        }

        public void onStart(Animator animation) {

        }

        public void onCancel(Animator animation) {

        }

        public void onRepeat(Animator animation) {

        }

        public void onUpdate(ValueAnimator animation) {

        }
    }


    private static final String TAG = AnimatorUtil.class.getSimpleName();

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }

    // 用法如下：
//    fly.setVisibility(View.GONE);
//    fly_one.setVisibility(View.VISIBLE);
//    fly.setAnimation(moveToViewBottom());
//    fly_one.setAnimation(moveToViewLocation());

}
