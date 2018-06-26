package com.kuanquan.qudao.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.fragment.InteractiveNotificationsFragment;
import com.kuanquan.qudao.ui.fragment.MessageFragment;
import com.kuanquan.qudao.ui.fragment.SystemNotificationFragment;

/**
 * 消息主页 (fragment隐藏和显示解决问题)
 * https://blog.csdn.net/dl10210950/article/details/54889376
 */
public class MessageActivity extends AppCompatActivity {
    private FragmentTransaction mFragmentTransaction;//fragment事务
    private FragmentManager mFragmentManager;//fragment管理者

    private MessageFragment mMessageFragment;
    private InteractiveNotificationsFragment mInteractiveNotificationsFragment;
    private SystemNotificationFragment mSystemNotificationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initFragment();
    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();//获取到fragment的管理对象
        mMessageFragment = new MessageFragment();
        mInteractiveNotificationsFragment = new InteractiveNotificationsFragment();
        mSystemNotificationFragment = new SystemNotificationFragment();
    }
}
