package com.xujun.contralayout.UI.drawlayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xujun.contralayout.R;
import com.xujun.contralayout.UI.ItemFragement;

public class DrawLayoutSample extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);

        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_navigation);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        //<activity android:name=".NavigationActivity" android:theme="@style/AppThemeNoActionBar"></activity>  
        //<style name="AppThemeNoActionBar" parent="Theme.AppCompat.Light.NoActionBar">  
        //初始化toolbar，这里得使用NoActionBar的主题，使用ToolBar替换系统自带的ActionBar达到自己的需求  
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        //关联图标和侧滑栏  
        actionBar.setHomeAsUpIndicator(R.drawable.menu);
        //设置actionBar和侧滑栏关联  
        actionBar.setDisplayHomeAsUpEnabled(true);
        //初始化drawerlayout和navigationview  
        if (mNavigationView != null) {
            //设置监听回调  
            mNavigationView.setNavigationItemSelectedListener(new NavigationView
                    .OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //根据选中不同的选项来进行不同的操作  
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content,
                                    ItemFragement.newInstance("主页")).commit();
                            mToolbar.setTitle("主页");
                            break;
                        case R.id.nav_friends:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content,
                                    ItemFragement.newInstance("我的好友")).commit();
                            mToolbar.setTitle("我的好友");
                            break;
                        case R.id.nav_discussion:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content, 
                                    ItemFragement.newInstance("热文论坛")).commit();
                            mToolbar.setTitle("热文论坛");
                            break;
                        case R.id.nav_messages:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content,
                                    ItemFragement.newInstance("我的消息")).commit();
                            mToolbar.setTitle("我的消息");
                            break;
                        case R.id.sub1:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content,
                                    ItemFragement.newInstance("子项1")).commit();
                            mToolbar.setTitle("子项1");
                            break;
                        case R.id.sub2:
                            getSupportFragmentManager().beginTransaction().replace(R.id.content,
                                    ItemFragement.newInstance("子项2")).commit();
                            mToolbar.setTitle("子项2");
                            break;
                    }
                    //设置选项选中效果  
                    item.setChecked(true);
                    //选了侧边栏选项之后，关闭侧边栏  
                    mDrawerLayout.closeDrawers();
                    //这里返回true有选中的效果，源码中有解释  
                    return true;
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //点击左上角的菜单选项，这是在actionBar.setHomeAsUpIndicator(R.drawable.center_image_collection);
            // 这儿设置的。  
            case android.R.id.home:
                //点击之后打开侧滑栏  
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
