package com.example.jetpack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.example.jetpack.adapter.MainAdapter;

/**
 * https://blog.csdn.net/wangcheng_/article/details/79385417
 * ViewModel+LiveData 使用
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        adapter = new MainAdapter();
        mRecyclerView.setAdapter(adapter);
    }
}
