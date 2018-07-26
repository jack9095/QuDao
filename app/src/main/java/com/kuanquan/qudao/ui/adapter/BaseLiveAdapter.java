package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.widget.HomeBanner;
import com.kuanquan.qudao.widget.HomeItemView;

import java.util.List;

/**
 * Created by fei.wang on 2018/7/26.
 *
 */

public class BaseLiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE = 0;
    private final int TWO = 1;
    private final int THREE = 2;
    private final int FOUR = 3;
    private final int FIVE = 4;
    private final int SIX = 5;
    List<HomeBean> lists;
    ViewGroup parentF;

    public void setData(List<HomeBean> homeBeans){
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentF = parent;
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case ONE:
                view = getView(R.layout.adapter_banner_layout,parent);
                holder = new BannerHolder(view);    // banner
                return holder;
            case TWO:
                view = getView(R.layout.adapter_tab_item_layout,parent);
                holder = new FiveItemHolder(view);      // 5个tab
                return holder;
            case THREE:
                view = getView(R.layout.adapter_view_pager_layout,parent);
                holder = new ProjectItemHolder(view,parent);   // 项目item
                return holder;
            case FOUR:
                view = getView(R.layout.adapter_live_layout,parent);
                holder = new LiveScrollHolder(view,parent);  // 名师直播
                return holder;
            case FIVE:
                view = getView(R.layout.adapter_discover_title_layout,parent);
                holder = new DiscoverTitleHolder(view);  // 高顿头条
                return holder;
            case SIX:
                view = getView(R.layout.adapter_discover_layout,parent);
                holder = new DiscoverHolder(view);       // 咨询
                return holder;
            default:
                return null;
        }
    }

    /**
     * 用来引入布局的方法
     */
    private View getView(int view,ViewGroup parent) {
        return View.inflate(parent.getContext(), view, null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size();
        }else{
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        int itemType = lists.get(position).itemType;
        if (itemType == 0) {
            return ONE;
        } else if (itemType == 1) {
            return TWO;
        } else if (itemType == 2) {
            return THREE;
        } else if (itemType == 3) {
            return FOUR;
        } else if (itemType == 4) {
            return FIVE;
        } else if (itemType == 5) {
            return SIX;
        } else {
            return SIX;
        }
    }

    /**
     * banner的ViewHolder
     */
    public static class BannerHolder extends RecyclerView.ViewHolder {
        HomeBanner mHomeBanner;
        public BannerHolder(View itemView) {
            super(itemView);
            mHomeBanner = itemView.findViewById(R.id.banner_layout);
        }
    }

    /**
     * 5个item的ViewHolder
     */
    public static class FiveItemHolder extends RecyclerView.ViewHolder {
        HomeItemView mHomeItemView;
        public FiveItemHolder(View itemView) {
            super(itemView);
            mHomeItemView = itemView.findViewById(R.id.home_item_view);
        }
    }

    /**
     * 项目 的ViewHolder
     */
    public static class ProjectItemHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        public ProjectItemHolder(View itemView,ViewGroup parent) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.adapter_project_item_recycler);
            LinearLayoutManager manager = new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false);
            mRecyclerView.setLayoutManager(manager);
        }
    }

    /**
     * 名师直播Live (scroll) 的ViewHolder
     */
    public static class LiveScrollHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        public LiveScrollHolder(View itemView,ViewGroup parent) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.adapter_live_layout_recycler);
            LinearLayoutManager manager = new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false);
            mRecyclerView.setLayoutManager(manager);
        }
    }

    /**
     * 高顿头条 title的ViewHolder
     */
    public static class DiscoverTitleHolder extends RecyclerView.ViewHolder {

        public DiscoverTitleHolder(View itemView) {
            super(itemView);

        }
    }

    /**
     *  咨询discover的ViewHolder
     */
    public static class DiscoverHolder extends RecyclerView.ViewHolder {
        TextView title,content,discover,more;
        ImageView image,colse;
        RelativeLayout text_discover_rl;
        public DiscoverHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_discover_title);
            content = itemView.findViewById(R.id.text_discover_bottom_content);
            image = itemView.findViewById(R.id.text_discover_image);
            colse = itemView.findViewById(R.id.text_discover_image_close);
            text_discover_rl = itemView.findViewById(R.id.text_discover_rl);

            more = itemView.findViewById(R.id.text_discover_more);
            discover = itemView.findViewById(R.id.text_discover);

        }
    }

}
