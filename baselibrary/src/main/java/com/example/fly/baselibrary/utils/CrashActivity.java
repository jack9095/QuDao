package com.example.fly.baselibrary.utils;

import android.os.Bundle;
import android.view.View;

import com.example.fly.baselibrary.R;
import com.example.fly.baselibrary.base.ActivityCollector;
import com.example.fly.baselibrary.base.BaseActivity;
import com.example.fly.baselibrary.base.BasePresenter;

public class CrashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

    }

    public void crashClick(View v) {
        ActivityCollector.finishAll();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_crash;
    }

    @Override
    protected int setFragmentContainerResId() {
        return 0;
    }
}
