package com.xujun.contralayout.UI;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xujun.contralayout.R;
import com.xujun.contralayout.adapter.ItemAdapter;
import com.xujun.contralayout.base.BaseFragment;
import com.xujun.contralayout.recyclerView.divider.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/10/15 11:09
 * @ email：gdutxiaoxu@163.com
 */
public class ItemFragement extends BaseFragment {


    RecyclerView mRecyclerView;
    private static final String KEY="key";
    private String title="测试";

    List<String> mDatas=new ArrayList<>();
    private ItemAdapter mAdapter;

    public static ItemFragement newInstance(String text){
        ItemFragement itemFragement = new ItemFragement();
        Bundle bundle = new Bundle();
        if(text!=null){
            bundle.putString(KEY,text);
            itemFragement.setArguments(bundle);
        }
        return itemFragement;
    }
    private static final String TAG = "xujun";

    @Override
    protected void initView(View view) {
        Bundle arguments = getArguments();
        if(arguments!=null){
            title=arguments.getString(KEY);
        }
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);


    }

    @Override
    protected void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext,
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);

        for(int i=0;i<30;i++){
            String s = String.format("I am %d " + title, i);
            mDatas.add(s);
        }

        mAdapter = new ItemAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_item;
    }

    @Override
    public void fetchData() {

    }
}
