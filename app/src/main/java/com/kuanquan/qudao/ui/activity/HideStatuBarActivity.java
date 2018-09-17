package com.kuanquan.qudao.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.fly.baselibrary.snackbars.TSnackbar;
import com.kuanquan.qudao.R;

/**
 * Created by fei.wang on 2018/9/15.
 * 显示隐藏状态栏
 */

public class HideStatuBarActivity extends Activity {

    private TSnackbar suSnackbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hide_statu_bar_activity);
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
    }

    public void barHide(View view){
        hideStatusBar();
        suSnackbar = TSnackbar.make(getWindow().getDecorView(), "状态栏", TSnackbar.LENGTH_LONG);
        suSnackbar.setBackgroundColor(Color.parseColor("#44cb7f"));//设置suSnackbar背景色
        suSnackbar.show();
    }

    public void barShow(View view){
        showStatusBar();
    }

    //显示系统状态栏
//    private void showStatusBar() {
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.VISIBLE);
//    }
//
//    //隐藏系统状态栏
//    private void hideStatusBar() {
//        View decorView = getWindow().getDecorView();
//        //设置成低调模式
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
//    }

    //显示系统状态栏
    private void showStatusBar() {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//隐藏状态栏的布局
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//内容布局填满屏幕
        );

    }

    //隐藏系统状态栏
    private void hideStatusBar() {

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//隐藏状态栏的布局
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//内容布局填满屏幕
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隐藏状态栏
                        | View.SYSTEM_UI_FLAG_FULLSCREEN//内容全屏展示
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }
}
