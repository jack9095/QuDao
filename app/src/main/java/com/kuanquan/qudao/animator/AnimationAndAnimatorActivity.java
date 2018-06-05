package com.kuanquan.qudao.animator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.fly.myapplication.R;

/**
 * 动画总入口
 */
public class AnimationAndAnimatorActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_and_animator);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:   // 移动动画
                startActivity(TranslateActivity.class);
                break;
            case R.id.button2:  // 旋转动画
                startActivity(RotateActivity.class);
                break;
            case R.id.button3:  // 渐入渐出  淡出淡入
                startActivity(AlphaActivity.class);
                break;
            case R.id.button4:  // 缩放
                startActivity(ScaleActivity.class);
                break;
            case R.id.button5:  // 组合动画  (补间动画组合)
                startActivity(CombinationActivity.class);
                break;
            case R.id.button6:  // 帧动画
                startActivity(FrameActivity.class);
                break;
            case R.id.button7:   // 属性动画
                startActivity(PropertyActivity.class);
                break;
            case R.id.button8:   // 多重结合复杂动画
                startActivity(ComplexActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> className){
        Intent intent = new Intent(this,className);
        startActivity(intent);
    }
}
