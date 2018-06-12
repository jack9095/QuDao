package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.utils.glide.GlideRoundTransform;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 *
 */
public class HomeAdapterChild extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeBeanChild> lists;
    private ViewGroup parentF;
    public void setData(List<HomeBeanChild> homeBeans){
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(getView(R.layout.adapter_live_child_layout,parent));
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
            if (homeBeanChild != null) {
                if (!TextUtils.isEmpty(homeBeanChild.content)) {
                    mRecyclerHolder.content.setText(homeBeanChild.content);
                }
                if (!TextUtils.isEmpty(homeBeanChild.title)) {
                    mRecyclerHolder.title.setText(homeBeanChild.title);
                }
                if (!TextUtils.isEmpty(homeBeanChild.image)) {
                    Glide.with(parentF.getContext())
                            .load(homeBeanChild.image)
                            .asBitmap()
                            .transform(new GlideRoundTransform(parentF.getContext(),10))
                            .into(mRecyclerHolder.image);
                }

                if (TextUtils.equals(homeBeanChild.type,"1")) {
                    LogUtil.e(homeBeanChild.type);
                    mRecyclerHolder.view.setVisibility(View.VISIBLE);
                }else{
                    LogUtil.e(homeBeanChild.type);
                    mRecyclerHolder.view.setVisibility(View.GONE);
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
        ImageView image;
        TextView title,content;
        View view;
        public RecyclerHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.live_child_image);
            title = itemView.findViewById(R.id.live_child_title);
            content = itemView.findViewById(R.id.live_child_content);
            view = itemView.findViewById(R.id.live_child_right_view);
        }
    }
}
