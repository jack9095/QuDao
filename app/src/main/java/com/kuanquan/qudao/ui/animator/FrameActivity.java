//package com.kuanquan.qudao.ui.animator;
//
//import android.graphics.drawable.AnimationDrawable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.example.fly.myapplication.R;
//
///**
// * 帧动画
// */
//public class FrameActivity extends AppCompatActivity {
//    private ImageView viewById;
//    private AnimationDrawable animationDrawable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.view_tsnackbar_layout.activity_frame);
//        viewById = findViewById(R.id.image);
//        animationDrawable = new AnimationDrawable();
//
//        // 为AnimationDrawable添加动画帧
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.brush_star_none),50);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.brush_star_one),50);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.brush_star_two),60);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.brush_star_three),90);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.bryant_cover),120);
//        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.huojian),150);
//
//        animationDrawable.setOneShot(false);  // 设置为循环播放，不设置默认为播放一次
//
//        // 给ImageView设置背景为帧动画  AnimationDrawable
//        viewById.setBackground(animationDrawable);
//    }
//
//    public void onClick(View view){
//        if (!animationDrawable.isRunning()) {
//            animationDrawable.start();  // 开启动画
//        }else{
//            animationDrawable.stop();  // 停止动画
//        }
//    }
//}
