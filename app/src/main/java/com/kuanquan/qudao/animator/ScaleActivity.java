package com.kuanquan.qudao.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.fly.myapplication.R;

/**
 * 缩放大小动画
 */
public class ScaleActivity extends AppCompatActivity {
    private ImageView viewById;
    private int intrinsicWidth;
    private int intrinsicHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        viewById = findViewById(R.id.image);
        intrinsicWidth = viewById.getDrawable().getIntrinsicWidth();  // 图片真实宽度
        intrinsicHeight = viewById.getDrawable().getIntrinsicHeight();// 图片真实高度

    }

    public void onClick(View view){
        /**
         * 参数1，参数2：表示X坐标从无到有
         * 参数3，参数4：表示Y坐标从无到有
         * 参数5，参数7：表示位置的模式（绝对位置、相对位置、靠左、靠右）
         * 参数6，参数8：表示动画从图片的那个位置开始显示或隐藏
         */
        final ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.0f,1.0f,
                0.0f,1.0f,
                Animation.ABSOLUTE,intrinsicWidth / 2,
                Animation.ABSOLUTE,intrinsicHeight / 2);   // 图片从小到大实现

        final ScaleAnimation scaleAnimationF = new ScaleAnimation(
                1.0f,0.0f,
                1.0f,0.0f,
                Animation.ABSOLUTE,intrinsicWidth,
                Animation.ABSOLUTE,intrinsicHeight);   // 图片从大到小实现

        scaleAnimation.setDuration(2000); // 设置动画执行时间
        scaleAnimation.setFillAfter(true); // 动画播放完成，停留在最后一帧
        scaleAnimation.setInterpolator(new LinearInterpolator()); // 动画匀速
        //        scaleAnimation.setRepeatMode (Animation.REVERSE);  // 重复的模式，反着来重复

        scaleAnimationF.setDuration(2000); // 设置动画执行时间
        scaleAnimationF.setFillAfter(true); // 动画播放完成，停留在最后一帧
        scaleAnimationF.setInterpolator(new LinearInterpolator()); // 动画匀速

        viewById.startAnimation(scaleAnimation);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewById.startAnimation(scaleAnimationF);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
