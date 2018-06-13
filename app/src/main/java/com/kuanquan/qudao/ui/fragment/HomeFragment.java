package com.kuanquan.qudao.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fly.baselibrary.mvpExample.base.BaseFragment;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.core.mvp.presenter.HomePresenter;
import com.kuanquan.qudao.core.mvp.view.IHomeView;
import com.kuanquan.qudao.ui.adapter.HomeAdapter;

import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<IHomeView,HomePresenter> implements IHomeView{
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private List<HomeBean> lists;
    private ImageView mImageView;

    @Override
    protected HomePresenter createPresent() {
        return new HomePresenter();
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initView() {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mImageView = view.findViewById(R.id.bar_head_image);
        GlideUtil.setImageCircle(getContext(),"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3909870665,3259015587&fm=27&gp=0.jpg",mImageView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter(lists);
        mRecyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showData(Object data) {
        lists = (List<HomeBean>) data;
    }
}
