package com.kuanquan.stickydemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kuanquan.stickydemo.adapter.RecyclerViewAdapter;
import com.kuanquan.stickydemo.adapter.StockAdapter;
import com.kuanquan.stickydemo.callback.OnItemClickListener;
import com.kuanquan.stickydemo.entitiy.StickyHeadEntity;
import com.kuanquan.stickydemo.entitiy.StockEntity;
import com.kuanquan.stickyitemhead.StickyHeadContainer;
import com.kuanquan.stickyitemhead.StickyItemDecoration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends Activity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private StickyHeadContainer mStickyHeadContainer;
    private int mStickyPosition;
    private StockAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                initView();
            }

            @Override
            protected String doInBackground(Void... voids) {
                return getStrFromAssets(TestActivity.this, "rasking.json");
            }

            @Override
            protected void onPostExecute(String result) {
                parseAndSetData(result);
            }

        }.execute();
        initView();
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mStickyHeadContainer = (StickyHeadContainer) findViewById(R.id.stick_head);

        final TextView tvLiveName = (TextView) findViewById(R.id.tv_live_name);
        final TextView tvMore = (TextView) findViewById(R.id.tv_more);
        final ImageView ivRight = (ImageView) findViewById(R.id.iv_right);

        mStickyHeadContainer.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                mStickyPosition = pos;
                Log.e("position = ",pos + "");
                StockEntity.StockInfo item = mAdapter.getData().get(pos).getData();
                tvLiveName.setText(item.stickyHeadName);
            }
        });
        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "点击了粘性头部的更多", Toast.LENGTH_SHORT).show();
            }
        });
        mStickyHeadContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "点击了粘性头部：" + tvLiveName.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        mRecyclerView.setLayoutManager(new LinearLayoutManager(TestActivity.this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new StockAdapter(null);
        mAdapter.setItemClickListener(new OnItemClickListener<StockEntity.StockInfo>() {
            @Override
            public void onItemClick(View view, StockEntity.StockInfo data, int position) {
                Toast.makeText(TestActivity.this, "点击了Item" , Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void parseAndSetData(String result) {
        Gson gson = new Gson();

        final StockEntity stockEntity = gson.fromJson(result, StockEntity.class);

        List<StockEntity.StockInfo> data = new ArrayList<>();

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "涨幅榜"));
        for (StockEntity.StockInfo info : stockEntity.increase_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "跌幅榜"));
        for (StockEntity.StockInfo info : stockEntity.down_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "换手率"));
        for (StockEntity.StockInfo info : stockEntity.change_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        data.add(new StockEntity.StockInfo(RecyclerViewAdapter.TYPE_STICKY_HEAD, "振幅榜"));
        for (StockEntity.StockInfo info : stockEntity.amplitude_list) {
            info.setItemType(RecyclerViewAdapter.TYPE_DATA);
            data.add(info);
        }

        List<StickyHeadEntity<StockEntity.StockInfo>> list = new ArrayList<>(data.size());
        list.add(new StickyHeadEntity<StockEntity.StockInfo>(null, StockAdapter.TYPE_HEAD, null));
        for (StockEntity.StockInfo info : data) {
            list.add(new StickyHeadEntity<>(info, info.getItemType(), info.stickyHeadName));
        }

        mAdapter.setData(list);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * @return Json数据（String）
     * @description 通过assets文件获取json数据，这里写的十分简单，没做循环判断。
     */
    public static String getStrFromAssets(Context context, String name) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
