package com.retrofit.wangfei.viewpagertablayout;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

/**
 * 可以折叠的标题栏
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init(){
        //设置Toolbar，显示Home键
        Toolbar mToolBar = findViewById(R.id.personal_info_toolbar);
        setSupportActionBar(mToolBar);
//        mToolBar.setNavigationIcon(R.mipmap.download);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //设置CollapsingToolbarLayout标题、设置图片
        CollapsingToolbarLayout mToolbarLayout = findViewById(R.id.personal_info_collapsingbar_layout);
        mToolbarLayout.setTitle("趣到"); //设置标题的名字
        mToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);//设置收缩后标题的位置
//        mToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);////设置展开后标题的位置
//        mToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
//        mToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后标题的颜色

        ImageView mBarLayoutBg = findViewById(R.id.personal_info_bar_background);
        Glide.with(this).load(R.mipmap.luchibao0).into(mBarLayoutBg);
        //悬浮下载菜单
        FloatingActionButton mFloatBtn = findViewById(R.id.personal_info_float_btn);
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://apk.hiapk.com/appinfo/com.zxkj.luchibao");
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                startActivity(intent);
            }
        });
        //添加详情
        TextView mTvDetail = findViewById(R.id.personal_info_detail_tv);
        mTvDetail.setText(getResources().getString(R.string.my_txt));
    }
}
