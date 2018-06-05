package com.kuanquan.qudao.animator;

import android.graphics.Interpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.example.fly.myapplication.R;

/**
 * 位移动画
 */
public class TranslateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        final TextView tv  = findViewById(R.id.tv);
        Button btn  = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Animation.ABSOLUTE   绝对位置
                 * 位移动画
                 */
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.ABSOLUTE,0,
                        Animation.ABSOLUTE,600,
                        Animation.ABSOLUTE,0,
                        Animation.ABSOLUTE,800);

                // 动画结束时，停留在最后一帧
                translateAnimation.setFillAfter(true);
                translateAnimation.setFillBefore(false);

//                translateAnimation.setInterpolator(new OvershootInterpolator(2.0f));  // 向前甩一定值后再回到原来位置
//                translateAnimation.setInterpolator(new AccelerateInterpolator());   // 加速
//                translateAnimation.setInterpolator(new DecelerateInterpolator(15000.0f));   // 减速
                translateAnimation.setInterpolator(new BounceInterpolator());   // 动画结束的时候弹起
//                translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());   // 在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
//                translateAnimation.setInterpolator(new AnticipateInterpolator());   // 开始的时候向后然后向前甩
//                translateAnimation.setInterpolator(new AnticipateOvershootInterpolator());   // 开始的时候向后然后向前甩一定值后返回最后的值
//                translateAnimation.setInterpolator(new CycleInterpolator(2.0f));   // 动画循环播放特定的次数，速率改变沿着正弦曲线
//                translateAnimation.setInterpolator(new LinearInterpolator());    // 匀速移动

                // 动画结束时，停留在第一帧
//                translateAnimation.setFillBefore(true);
//                translateAnimation.setFillAfter(false);
                translateAnimation.setDuration(1000);  // 动画执行时长，单位毫秒
//                translateAnimation.setRepeatCount(2);   // 动画的重复次数
                //        translateAnimation.setRepeatMode (Animation.REVERSE);  // 重复的模式，反着来重复
                tv.startAnimation(translateAnimation);


                /**
                 * 动画结束后控件隐藏,这时候你会发现隐藏不了，这是因为控件改变了位置，其实原来的位置已经隐藏了
                 */
//                tv.setVisibility(View.GONE);

                // 这时候就需要清除动画，刷新界面就可以隐藏了
//                tv.clearAnimation();
//                tv.invalidate();
            }
        });


    }
}
