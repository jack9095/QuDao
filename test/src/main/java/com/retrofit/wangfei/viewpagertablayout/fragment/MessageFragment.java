package com.retrofit.wangfei.viewpagertablayout.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.retrofit.wangfei.viewpagertablayout.R;
import com.retrofit.wangfei.viewpagertablayout.util.RecyclerViewUtil;
import java.util.ArrayList;
import java.util.Random;


public class MessageFragment extends Fragment {

    private RecyclerViewAdapter mAdapter;
    private ArrayList<String> data;
    private int START_POS = 0;
    private RecyclerViewUtil mRecyclerViewUtil;
    private Random random = new Random();
    private int MAX_COUNT = 20;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        int count = random.nextInt(MAX_COUNT);
        for (int i = 0; i < count; i++) {
            data.add(String.valueOf(i));
        }

        START_POS = START_POS + count;

        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerViewUtil = new RecyclerViewUtil(getActivity(), mRecyclerView);
        mRecyclerViewUtil.setOnLoadMoreListener(new RecyclerViewUtil.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "已经到底，加载更多...", Toast.LENGTH_SHORT).show();
                mRecyclerViewUtil.setLoadMoreEnable(false);
                load(START_POS, random.nextInt(MAX_COUNT));
            }
        });

        mRecyclerViewUtil.setOnItemClickListener(new RecyclerViewUtil.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(getActivity(), "单击" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerViewUtil.setOnItemLongClickListener(new RecyclerViewUtil.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position, View view) {
                Toast.makeText(getActivity(), "长按" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void load(int startPos, int count) {
        for (int i = 0; i < count; i++) {
            data.add(startPos + i + "");
        }

        mAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "加载了" + count + " 条数据", Toast.LENGTH_SHORT).show();

        START_POS = startPos + count;
        mRecyclerViewUtil.setLoadMoreEnable(true);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(android.R.id.text1);
        }
    }
}
