package com.kuanquan.qudao.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fly.myapplication.R;

/**
 * 补间动画的  组合动画     所有补间动画的内容，都可以通过属性动画实现。
 */
public class CombinationActivity extends AppCompatActivity {
    private ImageView viewById;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination);
        viewById = findViewById(R.id.image);
    }

    public void onClick(View view){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.combination_animation);
        viewById.startAnimation(animation);
    }
}
