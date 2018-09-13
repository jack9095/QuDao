package com.kuanquan.qudao.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.fly.baselibrary.snackbars.Prompt;
import com.example.fly.baselibrary.snackbars.TSnackbar;
import com.example.fly.baselibrary.top_snackbar.BaseTransientBottomBar;
import com.example.fly.baselibrary.top_snackbar.TopSnackBar;
import com.kuanquan.qudao.R;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;


/**
 * Created by fei.wang on 2018/9/13.
 */

public class SnackBarActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout mRr;

    private TSnackbar suSnackbar;
    //定时
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (suSnackbar != null) {
                        suSnackbar.setPromptThemBackground(Prompt.SUCCESS).setText("登录成功").setDuration(TSnackbar.LENGTH_LONG).show();

                    }
                    break;
                case 1:
                    if (suSnackbar != null) {
                        suSnackbar.setPromptThemBackground(Prompt.ERROR).setText("登录失败").setDuration(TSnackbar.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.fore).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.show_top_snack_bar).setOnClickListener(this);
        findViewById(R.id.show_snack_bar).setOnClickListener(this);

        mRr = findViewById(R.id.rr);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                suSnackbar = TSnackbar.make(mRr, "第一种展示的形式", TSnackbar.LENGTH_LONG);
                suSnackbar.addIcon(R.mipmap.ic_launcher);//suSnackbar上添加图标
                suSnackbar.setBackgroundColor(Color.RED);//设置suSnackbar背景色
                suSnackbar.addIconProgressLoading(0, false, true);//suSnackbar上添加图标以及图标的位置
                suSnackbar.show();
                break;
            case R.id.two:
                suSnackbar = TSnackbar.make(mRr, "第二种展示的形式", TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
                suSnackbar.setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                suSnackbar.setActionTextColor(Color.BLACK);//设置Action上文字的颜色
                suSnackbar.setPromptThemBackground(Prompt.SUCCESS);//设置样式
                suSnackbar.show();
                break;
            case R.id.three:
                suSnackbar = TSnackbar.make(mRr, "正在登录，请稍后...", TSnackbar.LENGTH_INDEFINITE, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
                suSnackbar.setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                suSnackbar.setPromptThemBackground(Prompt.SUCCESS);
                suSnackbar.addIconProgressLoading(0, true, false);
                suSnackbar.show();
                mHandler.postDelayed(gotoLoginActSuccess, 5000);//5秒后执行任务
                break;
            case R.id.fore:
                suSnackbar = TSnackbar.make(mRr, "第四种展示的形式", TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
                suSnackbar.setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                suSnackbar.setPromptThemBackground(Prompt.SUCCESS);
                suSnackbar.show();
                break;
            case R.id.five:
                suSnackbar = TSnackbar.make(mRr, "第五种展示的形式...", TSnackbar.LENGTH_LONG, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
                suSnackbar.addIcon(R.mipmap.ic_launcher, 100, 100);//设置图标，以及图标的宽高
                suSnackbar.setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                suSnackbar.setPromptThemBackground(Prompt.WARNING);
                suSnackbar.show();
                break;
            case R.id.show_top_snack_bar:  // 顶部显示
                TopSnackBar.make(getWindow().getDecorView(), "我是顶部SnackBar", BaseTransientBottomBar.LENGTH_SHORT).show();
                break;
            case R.id.show_snack_bar:  // 显示源版SnackBar
                Snackbar.make(view, "我是源版SnackBar", BaseTransientBottomBar.LENGTH_SHORT).show();
                break;
        }
    }

    Runnable gotoLoginActSuccess = new Runnable() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }
    };
}
