package com.xujun.contralayout.UI.FloatingActiobButtton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xujun.contralayout.R;
import com.xujun.contralayout.adapter.ItemAdapter;
import com.xujun.contralayout.recyclerView.divider.DividerItemDecoration;

import java.util.ArrayList;

public class FloatingActionButtonActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private ItemAdapter mItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String s = String.format("I am %d item", i);
            mDatas.add(s);
        }
        mItemAdapter = new ItemAdapter(this, mDatas);
        mRecyclerView.setAdapter(mItemAdapter);
    }
}
