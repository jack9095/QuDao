package com.hhl.flowlayoutdemo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagClickListener;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private FlowTagLayout mColorFlowTagLayout;
    private TagAdapter<String> mColorTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mColorFlowTagLayout = (FlowTagLayout) findViewById(R.id.color_flow_layout);
        //颜色
        mColorTagAdapter = new TagAdapter<>(this);
        mColorFlowTagLayout.setAdapter(mColorTagAdapter);
        mColorFlowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                Snackbar.make(view, "点击:" + parent.getAdapter().getItem(position), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initColorData();
    }

    private void initColorData() {
        List<String> dataSource = new ArrayList<>();
        dataSource.add("红色");
        dataSource.add("黑色");
        dataSource.add("花边色");
        dataSource.add("深蓝色");
        dataSource.add("白色");
        dataSource.add("玫瑰红色");
        dataSource.add("会当凌绝顶，一览众山下");
        dataSource.add("葡萄红色");
        dataSource.add("屎黄色");
        dataSource.add("绿色");
        dataSource.add("彩虹色");
        dataSource.add("牡丹色");
        dataSource.add("离离原上草");
        dataSource.add("一岁一枯荣，野火烧不尽");
        dataSource.add("一岁一枯荣，野火烧不尽");
        dataSource.add("牡丹色");
        mColorTagAdapter.onlyAddAll(dataSource);
    }
}
