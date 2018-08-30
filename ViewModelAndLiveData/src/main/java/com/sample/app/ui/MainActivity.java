package com.sample.app.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sample.app.R;
import com.sample.app.bean.AccountBean;
import com.sample.app.lifeCycle.TestLifeCycle;
import com.sample.app.model.AccountModel;

/**
 * Android框架组件--Lifecycle的使用:
 * https://blog.csdn.net/u011810352/article/details/81203095
 *
 * Android框架组件--LiveData的使用及与ViewModel的结合
 * https://blog.csdn.net/u011810352/article/details/81334339
 */
public class MainActivity extends AppCompatActivity {

    private AccountModel mModel;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Activity中添加观察者来监听此Activity的生命周期
        getLifecycle().addObserver(new TestLifeCycle());

        mText = findViewById(R.id.textView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_1, new TopFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_2, new BottomFragment()).commit();

        mModel = ViewModelProviders.of(this).get(AccountModel.class);

        findViewById(R.id.main_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.setAccount("google 谷歌", "182*****008", "https://blog.csdn.net/u011810352/article/details/81203095");
            }
        });
        mModel.getAccount().observe(this, new Observer<AccountBean>() {
            @Override
            public void onChanged(@Nullable AccountBean accountBean) {
                mText.setText(AccountModel.getFormatContent(accountBean.getName(), accountBean.getPhone(), accountBean.getBlog()));
            }
        });
    }
}
