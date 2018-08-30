package com.example.jetpack.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.jetpack.R;
import com.example.jetpack.bean.DetailsArticleBean;
import java.util.List;

/**
 * Created by fei.wang on 2018/7/9.
 *
 */
public class MainAdapter extends RecyclerView.Adapter {
    private List<DetailsArticleBean> lists;

    public void setData(List<DetailsArticleBean> homeBeans) {
        this.lists = homeBeans;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_discover_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DetailsArticleBean detailsArticleBean = lists.get(position);
        if (holder instanceof ArticleHolder) {  // 文章详情
            ArticleHolder mArticleHolder = (ArticleHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size()+1;
        } else {
            return 0;
        }
    }

    private static class ArticleHolder extends RecyclerView.ViewHolder{
        RelativeLayout find_rl_root;
        public ArticleHolder(View itemView) {
            super(itemView);

        }
    }
}
