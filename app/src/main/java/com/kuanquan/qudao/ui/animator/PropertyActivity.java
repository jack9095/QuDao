package com.kuanquan.qudao.ui.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.widget.MyImageView;

/**
 * 属性动画
 */
public class PropertyActivity extends AppCompatActivity {
    private ImageView viewById;
    ObjectAnimator objectAnimator;
    ObjectAnimator scaleX;
    ObjectAnimator scaleY;
    ObjectAnimator translateX;
    ObjectAnimator translateX1;
    ObjectAnimator translateY;
    ObjectAnimator translateY1;
    ValueAnimator valueAnimator;
    AnimatorSet animatorSet;
    int count;
    private int intrinsicHeight;
    private int intrinsicWidth;
    private MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        viewById = findViewById(R.id.image);
        myImageView = findViewById(R.id.pointview);
        intrinsicHeight = viewById.getDrawable().getIntrinsicHeight(); // 图片的高度
        intrinsicWidth = viewById.getDrawable().getIntrinsicWidth();   // 图片的宽度
    }

    public void onClick(View view){
        if (objectAnimator != null) {
            objectAnimator.end();
        }
        switch (count){
            case 0:
                levelRotate();
                count++;
                break;
            case 1:
                vertical();
                count++;
                break;
            case 2:
                alphaF();
                count++;
                break;
            case 3:
                alpha();
                count++;
                break;
            case 4:
                scaleY();
                count++;
                break;
            case 5:
                scaleX();
                count++;
                break;
            case 6:
                translateLinearInterpolateX();
                count++;
                break;
            case 7:
                translateLinearInterpolateY();
                count++;
                break;
            case 8:
                translateLinearInterpolateSetX();
                count++;
                break;
            case 9:
                translateLinearInterpolateSetY();
                count++;
                break;
            case 10:
                rectAnimatorSet();
                count++;
                break;
            case 11:
                pathPropertyAnimator();
                count++;
                break;
            case 12:
                customPropertyAnimator();
                count = 0;
                break;
        }
    }

    // 沿着X轴方向的旋转动画
    private void levelRotate(){
        objectAnimator = ObjectAnimator.ofFloat(viewById,"rotationX",0.0f,359.0f);
        objectAnimator.setDuration(1000);   // 动画执行时长
        objectAnimator.setRepeatCount(-1);  // 重复次数 ，无限次
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);// 重复模式，一个方向
        objectAnimator.start();
    }

    // 沿着Y轴方向的旋转动画，立体旋转
    private void vertical(){
        objectAnimator = ObjectAnimator.ofFloat(viewById,"rotationY",0.0f,359.0f);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);  // 重复模式，反方向
        objectAnimator.setRepeatCount(-1);  // 重复次数 ，无限次
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    // 淡出淡入  从无到有
    private void alpha(){
        objectAnimator = ObjectAnimator.ofFloat(viewById,"alpha",0.0f,1.0f);
        objectAnimator.setDuration(1000);   // 动画执行时长
//        objectAnimator.setRepeatCount(-1);  // 重复次数 ，无限次
//        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);// 重复模式，一个方向
        objectAnimator.start();
    }

    // 淡出淡入  从有到无
    private void alphaF(){
        objectAnimator = ObjectAnimator.ofFloat(viewById,"alpha",1.0f,0.0f);
        objectAnimator.setDuration(1000);   // 动画执行时长
        objectAnimator.setRepeatCount(-1);  // 重复次数 ，无限次
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);// 重复模式，一个方向
        objectAnimator.start();
    }

    // 缩放动画   从无到有
    private void scaleX(){
        animatorSet = new AnimatorSet();  // 组合动画
        scaleX = ObjectAnimator.ofFloat(viewById,"scaleX",0,1f);
        scaleY = ObjectAnimator.ofFloat(viewById,"scaleY",0,1f);
        animatorSet.setDuration(1000);   // 动画执行时长
        animatorSet.setInterpolator(new DecelerateInterpolator()); // 减速
        animatorSet.play(scaleX).with(scaleY);  // 两个动画同时播放
        animatorSet.start();
    }

    // 缩放动画   从有到无
    private void scaleY(){
        animatorSet = new AnimatorSet();   // 组合动画
        scaleX = ObjectAnimator.ofFloat(viewById,"scaleX",1f,0);
        scaleY = ObjectAnimator.ofFloat(viewById,"scaleY",1f,0);
        animatorSet.setDuration(1000);   // 动画执行时长
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }

    // 位移动画  匀速  从左到右
    private void translateLinearInterpolateX(){
        objectAnimator = ObjectAnimator.ofFloat(viewById,"x",0.0f,700.0f);
        objectAnimator.setDuration(1000);   // 动画执行时长
        objectAnimator.setInterpolator(new LinearInterpolator());
//        objectAnimator.setRepeatCount(-1);  // 重复次数 ，无限次
//        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);// 重复模式，一个方向
        objectAnimator.start();
    }

    // 位移动画  匀速  从上到下
    private void translateLinearInterpolateY(){
        objectAnimator = ObjectAnimator.ofFloat(viewById,"y",700.0f - intrinsicHeight,1200.0f);
        objectAnimator.setDuration(1000);   // 动画执行时长
        objectAnimator.setInterpolator(new LinearInterpolator());
//        objectAnimator.setRepeatCount(-1);  // 重复次数 ，无限次
//        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);// 重复模式，一个方向
        objectAnimator.start();
    }

    // 位移动画  匀速  从左到右   组合动画
    private void translateLinearInterpolateSetX(){
        animatorSet = new AnimatorSet();
        translateX = ObjectAnimator.ofFloat(viewById,"x",0.0f,700.0f);
        translateY = ObjectAnimator.ofFloat(viewById,"y",60.0f,60.0f);
        animatorSet.setDuration(1000);   // 动画执行时长
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(translateX).with(translateY);
        animatorSet.start();
    }


    // 位移动画  匀速  从上到下    组合动画
    private void translateLinearInterpolateSetY(){
        animatorSet = new AnimatorSet();
//        translateX = ObjectAnimator.ofFloat(viewById,"x",700.0f - intrinsicHeight,1200.0f);
        translateY = ObjectAnimator.ofFloat(viewById,"y",60.0f,1200.0f);
        animatorSet.setDuration(1000);   // 动画执行时长
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(translateY);
        animatorSet.start();
    }

    // 动画矩形
    private void rectAnimatorSet(){
        animatorSet = new AnimatorSet();
        translateX = ObjectAnimator.ofFloat(viewById,"x",0.0f,800.0f);  // 向右移动
        translateX.setDuration(1000);
        ObjectAnimator translateY2 = ObjectAnimator.ofFloat(viewById,"y",60.0f,60.0f);
        translateY2.setDuration(1000);
        translateY = ObjectAnimator.ofFloat(viewById,"y",60.0f,1300.0f); // 向下移动
        translateY.setDuration(1000);
        translateX1 = ObjectAnimator.ofFloat(viewById,"x",800.0f,0.0f);      //向左移动
        translateX1.setDuration(1000);
        translateY1 = ObjectAnimator.ofFloat(viewById,"y",1300.0f,60.0f);    // 向上移动
        translateY1.setDuration(1000);
        animatorSet.setInterpolator(new LinearInterpolator());

        animatorSet.play(translateX).with(translateY2).before(translateY); // 在播放动画translateY之前同时播放动画translateX和translateY2
        animatorSet.play(translateY).before(translateX1);   // 在播放动画translateX1之前播放动画translateY
        animatorSet.play(translateX1).before(translateY1);  // 在播放动画translateY1之前播放动画translateX1

        animatorSet.start();
    }

    /**
     * 自定义属性 （宽高）
     */
    private void customPropertyAnimator(){
//        ImageViewWrapper imageViewWrapper = new ImageViewWrapper(viewById);
////        Property p = Property.of(ImageViewWrapper.class, Float.class, "width");
//        Property p = Property.of(ImageViewWrapper.class, Float.class, "height");
////        objectAnimator = ObjectAnimator.ofFloat(imageViewWrapper,"height",0.0f,100);
//        objectAnimator = ObjectAnimator.ofFloat(imageViewWrapper,p,0,100);
//        objectAnimator.setDuration(3000);
//        objectAnimator.start();

        /**
         * 着重看ObjectAnimator的构造方法，首先要操作的控件对象是mPointView，然后相应的属性是pointRadius，然后值是从0到300再到100；
         * 所以在动画開始以后。ObjectAnimator就会实时地把动画中产生的值做为參数传给MyPointView类中的setPointRadius(int radius)函数，
         * 然后调用setPointRadius(int radius)。因为我们在setPointRadius(int radius)中实时地设置圆形的半径值然后强制重绘当前界面，
         * 所以能够看到圆形的半径会随着动画的进行而改变。
         */
        // 设置半径从0到300到100
        ObjectAnimator animator = ObjectAnimator.ofInt(myImageView, "radius", 0, 300, 100);
        animator.setDuration(2000);
        animator.setRepeatCount(-1);
        animator.start();
    }

    /**
     * 路劲动画
     */
    private void pathPropertyAnimator(){
//        Path path = new Path();
//        path.addCircle(400,300,100,Path.Direction.CW);
        Path path = new Path();
        path.moveTo(200, 200);

        path.quadTo(800, 200, 800, 800);

//        ObjectAnimator animator4 = ObjectAnimator.ofFloat(viewById,"X","Y",path);
        @SuppressLint({"NewApi", "LocalSuppress"}) ObjectAnimator animator4 = ObjectAnimator.ofFloat(viewById,"X","Y",path);
        animator4.setDuration(3000);
        animator4.setInterpolator(new LinearInterpolator());
//        objectAnimator = ObjectAnimator.ofFloat(viewById,"X","Y",path);
//        objectAnimator.setDuration(5000);
        animator4.start();
    }
}
