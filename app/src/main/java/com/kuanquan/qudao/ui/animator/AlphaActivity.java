package com.kuanquan.qudao.ui.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.kuanquan.qudao.R;


/**
 * 淡出淡入动画
 */
public class AlphaActivity extends AppCompatActivity {

    private ImageView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_view_animation);
        viewById = findViewById(R.id.image);
    }

    public void onClick(View view){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);  // 从显示到隐藏
        final AlphaAnimation alphaAnimationF = new AlphaAnimation(0.0f,1.0f);  // 从隐藏到显示

        alphaAnimation.setInterpolator(new LinearInterpolator());  // 匀速
        alphaAnimation.setDuration(2000);  // 设置动画显示时长
        alphaAnimation.setFillAfter(false); // 动画结束，停留在最后一帧
//        alphaAnimation.setRepeatMode (Animation.REVERSE);  // 重复的模式，反着来重复

        alphaAnimationF.setInterpolator(new LinearInterpolator());  // 匀速
        alphaAnimationF.setDuration(2000);  // 设置动画显示时长
        alphaAnimationF.setFillAfter(false); // 动画结束，停留在最后一帧

        viewById.startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {  // 从显示到隐藏  动画结束
                viewById.startAnimation(alphaAnimationF); // 开启 从隐藏到显示动画
            }

            @Override
            public void onAnimationRepeat(Animation animation) {  // 重复次数方法回掉
                animation.getRepeatCount();
            }
        });


    }

}
