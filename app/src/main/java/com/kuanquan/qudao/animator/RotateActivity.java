package com.kuanquan.qudao.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.fly.myapplication.R;

/**
 * 旋转
 */
public class RotateActivity extends AppCompatActivity {

    private ImageView viewById;
    private int width;
    private int intrinsicHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        viewById = findViewById(R.id.image);
        width = viewById.getDrawable().getIntrinsicWidth();   // 获取图片的真是宽度
        intrinsicHeight = viewById.getDrawable().getIntrinsicHeight();  // 获取图片的真实高度
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View view){
        /**
            float fromDegrees：旋转的开始角度。
            float toDegrees：旋转的结束角度。
            int pivotXType：X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            float pivotXValue：X坐标的伸缩值。 图片沿着X轴的哪一点旋转
            int pivotYType：Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
            float pivotYValue：Y坐标的伸缩值。  图片沿着Y轴的哪一点旋转
         */
        RotateAnimation rotateAnimation = new RotateAnimation(
                0,359,
                Animation.ABSOLUTE,width / 2 ,
                Animation.ABSOLUTE,0);

        rotateAnimation.setDuration(1000);   // 设置动画执行时长
        rotateAnimation.setRepeatCount(-1);   // 设置动画重复次数   -1 表示不断旋转
        rotateAnimation.setRepeatMode (Animation.REVERSE);  // 重复的模式，反着来重复
        rotateAnimation.setFillAfter(true);  // 动画结束，停留在最后一帧
        rotateAnimation.setInterpolator(new LinearInterpolator()); // 匀速旋转
        viewById.startAnimation(rotateAnimation);
//        viewById.clearAnimation(); // 清除动画

        //  利用属性动画实现立体的旋转动画
        //  属性动画ObjectAnimation实现旋转，主要用到rotationX和rotationY这两个属性，没有rotationZ这个属性
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewById,"rotationY",0.0f,359.0f);
        objectAnimator.setRepeatCount(-1);  // 设置动画重复次数   -1 表示不断旋转
        objectAnimator.setRepeatMode (ValueAnimator.REVERSE);  // 重复的模式，反着来重复
        objectAnimator.setDuration(2000);
        objectAnimator.start();   // 开启动画
//        objectAnimator.end();  // 动画结束并重置
//        objectAnimator.pause(); // 暂停旋转
//        objectAnimator.resume(); // 继续旋转
    }
}
