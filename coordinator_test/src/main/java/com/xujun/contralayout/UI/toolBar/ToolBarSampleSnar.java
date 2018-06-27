package com.xujun.contralayout.UI.toolBar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.xujun.contralayout.R;
import com.xujun.contralayout.adapter.ItemAdapter;
import com.xujun.contralayout.recyclerView.divider.DividerItemDecoration;
import com.xujun.contralayout.utils.WriteLogUtil;

import java.util.ArrayList;
import java.util.List;

public class ToolBarSampleSnar extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<String> mDatas;
    private ItemAdapter mAdapter;

    Toolbar mToolbar;

    public static  final String TAG="xujun";

    private RelativeLayout mRlBottomSheet;
    private BottomSheetBehavior<RelativeLayout> mFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRlBottomSheet = (RelativeLayout) findViewById(R.id.rl_bottom_sheet);
        mFrom = BottomSheetBehavior.from(mRlBottomSheet);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // 该属性必须在setSupportActionBar之前 调用
        mToolbar.setTitle("toolBar");
        setSupportActionBar(mToolbar);

        initListener();
        initData();
    }

    private void initData() {
        WriteLogUtil.init(this);




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String s = String.format("我是第%d个item", i);
            mDatas.add(s);
        }
        mAdapter = new ItemAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {

        mFrom.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i(TAG, "onStateChanged: newState=" +newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onStateChanged: slideOffset=" +slideOffset);
            }
        });
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                                WriteLogUtil.i("Snackbar");

                            }
                        })
                        .show();
            }
        });
       mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
           }

           @Override
           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               Log.i(TAG, "onScrolled: dy=" +dy);
           }
       });
    }



}
