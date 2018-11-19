package com.retrofit.wangfei.viewpagertablayout.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.wangfei.viewpagertablayout.R;
import com.retrofit.wangfei.viewpagertablayout.adapter.MyRecycleViewAdapter;
import com.retrofit.wangfei.viewpagertablayout.view.SimpleLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BlankFragment extends Fragment {

    SimpleLayoutManager layoutManager;
    RecyclerView mRecyclerView;
    MyRecycleViewAdapter adapter;
    private List<String> lists = new ArrayList<>();

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new SimpleLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecycleViewAdapter(lists,getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < 20; i++) {
            lists.add("" + i);
        }
        adapter.notifyDataSetChanged();
    }
}
