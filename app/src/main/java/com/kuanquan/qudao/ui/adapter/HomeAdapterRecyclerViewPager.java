package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.utils.glide.GlideRoundTransform;
import com.kuanquan.qudao.widget.HomeProjectItemView;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 *  项目布局
 */
public class HomeAdapterRecyclerViewPager extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeBeanChild> lists;
    private ViewGroup parentF;
    public void setData(List<HomeBeanChild> homeBeans){
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(getView(R.layout.adapter_view_pager_child_layout_f,parent));
    }

    /**
     * 用来引入布局的方法
     */
    private View getView(int view,ViewGroup parent) {
        parentF = parent;
        return View.inflate(parent.getContext(), view, null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerHolder) {
            HomeBeanChild homeBeanChild = lists.get(position);
            RecyclerHolder mRecyclerHolder = (RecyclerHolder) holder;
            if (homeBeanChild != null && homeBeanChild.tabItems != null && homeBeanChild.tabItems.size() > 0) {
                for (int i = 0; i < homeBeanChild.tabItems.size(); i++) {
                    if (!TextUtils.isEmpty(homeBeanChild.tabItems.get(i).content)) {
                        mRecyclerHolder.mHomeProjectItemView.textViews.get(i).setText(homeBeanChild.tabItems.get(i).content);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size();
        }else{
            return 0;
        }
    }

    /**
     * Recycler的ViewHolder
     */
    public static class RecyclerHolder extends RecyclerView.ViewHolder {
        HomeProjectItemView mHomeProjectItemView;
        public RecyclerHolder(View itemView) {
            super(itemView);
            mHomeProjectItemView = itemView.findViewById(R.id.ll_home_project_item_view);
        }
    }
}
