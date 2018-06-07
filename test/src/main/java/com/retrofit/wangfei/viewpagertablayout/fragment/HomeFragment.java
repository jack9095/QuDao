package com.retrofit.wangfei.viewpagertablayout.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.retrofit.wangfei.viewpagertablayout.util.Constance;
import com.retrofit.wangfei.viewpagertablayout.R;
import com.retrofit.wangfei.viewpagertablayout.adapter.MyRecycleViewAdapter;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView recyclerview;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressView progress_loading_main;    // 加载数据是显示的进度圆圈
    private LinearLayoutManager mRecycleViewLayoutManager;

    private int mPageNum = 1;

    private List<String> lists = new ArrayList<>();
    private MyRecycleViewAdapter mAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        progress_loading_main = (ProgressView) view.findViewById(R.id.progress_loading_main);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new MyRecycleViewAdapter(lists,getActivity());
        initRecyclerView();
        swipeRefreshLayout.setColorSchemeResources(Constance.colors);//设置下拉刷新控件变换的四个颜色
        recyclerview.setAdapter(mAdapter);
        recyclerViewOnItemClickListener();
        refresh();
        loadMore(mAdapter);
        progress_loading_main.setVisibility(View.VISIBLE);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**进入页面的初始化数据*/
    private void initData(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                netNewsList(true);
                progress_loading_main.setVisibility(View.GONE);
            }
        }, 2000);
    }

    /**RecyclerView每个item的点击事件*/
    private void recyclerViewOnItemClickListener() {
        mAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "fly", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
//        recyclerview.setItemAnimator(new DefaultItemAnimator());
//        recyclerview.setHasFixedSize(true);
        mRecycleViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(mRecycleViewLayoutManager);  // 设置RecycleView，显示是ListView还是gridView还是瀑布流

    }

    /**下拉刷新*/
    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        netNewsList(true);
                        swipeRefreshLayout.setRefreshing(false); // 停止刷新
                    }
                }, 2000);
            }
        });
    }

    /**
     * 设置上拉加载更多
     *
     * @param adapter RecyclerView适配器
     */
    public void loadMore(final MyRecycleViewAdapter adapter) {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mRecycleViewLayoutManager.findLastVisibleItemPosition();  // 滑动到最后一个
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //  效果在暂停时显示, 否则会导致重绘异常
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {

                    if (lists != null && lists.size() >= 10) {  // 真实开发中要设置mNews.size()大于加载分页显示的个数
                        adapter.loadLayout.setVisibility(View.VISIBLE);
                        //加载更多
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                netNewsList(false);
                            }
                        }, 2000);
                    }
                }
            }
        });
    }

    /**
     * 从网络加载数据列表
     *
     * @param isRefresh 是否刷新  true 为刷新，false为不刷新
     */
    private void netNewsList(boolean isRefresh) {
//        viewDelegate.showLoading();
        if (isRefresh) {
            mPageNum = 1;
        } else {
            mPageNum++;
        }

        if (isRefresh) {
            if (!lists.isEmpty()) {
                lists.clear();
            }
        }
        // TODO 这里把页数mPageNum上传到服务端
        lists.clear();
        lists.addAll(getData());
        mAdapter.notifyDataSetChanged();
    }

    private List<String> list = new ArrayList<>();
    private List<String> getData() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        return list;
    }
}
