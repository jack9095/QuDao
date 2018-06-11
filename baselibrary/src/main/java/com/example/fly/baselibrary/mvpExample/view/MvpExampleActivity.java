package com.example.fly.baselibrary.mvpExample.view;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly.baselibrary.R;
import com.example.fly.baselibrary.mvpExample.base.BaseActivity;
import com.example.fly.baselibrary.mvpExample.presenter.Present;

import java.util.ArrayList;

/**
 * @author: fei.wang
 * @date: 2017.4.23
 * @des: 属于视图层
 */
public class MvpExampleActivity extends BaseActivity<IMainView,Present> implements IMainView {
    private TextView textView;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
//    private MyPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_example);
        textView = findViewById(R.id.text);
        mPresent.getRequestData();
    }

    @Override
    protected Present createPresent() {
        return new Present();
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"正在加载",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(Object data) {
        textView.setText(data.toString());
    }

}
