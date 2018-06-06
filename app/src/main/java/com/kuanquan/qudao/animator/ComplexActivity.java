//package com.kuanquan.qudao.animator;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.example.fly.myapplication.R;
//
///**
// * 多冲重合复杂动画
// */
//public class ComplexActivity extends AppCompatActivity {
//    private ImageView viewById;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_complex);
//        viewById = findViewById(R.id.image);
//    }
//
//    public void onClick(View view){
//
//    }
//
//    /**
//     * 分析1：onUserInteraction()
//     * 作用：实现屏保功能
//     * 注：
//     *    a. 该方法为空方法
//     *    b. 当此activity在栈顶时，触屏点击按home，back，menu键等都会触发此方法
//     */
//    @Override
//    public void onUserInteraction() {
//        super.onUserInteraction();
//    }
//}
