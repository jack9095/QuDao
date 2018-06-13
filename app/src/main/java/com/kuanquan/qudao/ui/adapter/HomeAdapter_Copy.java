package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 *
 */
public class HomeAdapter_Copy extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE = 0x1109;
    private final int TWO = 0x1110;
    private final int THREE = 0x1111;
    private final int FOUR = 0x1112;
    private final int FIVE = 0x1113;
    private List<HomeBean> lists;
    private ViewGroup parentF;

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
                view = getView(R.layout.adapter_weex_layout,parent);
                holder = new BannerHolder(view);
                return holder;
            case FOUR:
                view = getView(R.layout.adapter_live_layout,parent);
                holder = new LiveHolder(view,parent);
                return holder;
            case FIVE:
                view = getView(R.layout.adapter_discover_layout,parent);
                holder = new DiscoverHolder(view);
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
        HomeBean homeBean = lists.get(position);
        if (holder instanceof BannerHolder) {
            BannerHolder mBannerHolder = (BannerHolder) holder;

        }else if (holder instanceof LiveHolder) {
            LiveHolder mLiveHolder = (LiveHolder) holder;
            HomeAdapterChild adapter = new HomeAdapterChild();
            mLiveHolder.mRecyclerView.setAdapter(adapter);
        }else if (holder instanceof DiscoverHolder) {
            DiscoverHolder mDiscoverHolder = (DiscoverHolder) holder;
            if (homeBean != null) {
                if (!TextUtils.isEmpty(homeBean.content)) {
                    mDiscoverHolder.content.setText(homeBean.content);
                }
                if (!TextUtils.isEmpty(homeBean.title)) {
                    mDiscoverHolder.title.setText(homeBean.title);
                }
                if (!TextUtils.isEmpty(homeBean.image)) {
                    GlideUtil.setImage(parentF.getContext(),homeBean.image,mDiscoverHolder.image);
                }
                if (TextUtils.equals("1",homeBean.isDiscover)) {
                    mDiscoverHolder.discover.setVisibility(View.VISIBLE);
                    mDiscoverHolder.more.setVisibility(View.VISIBLE);
                }else{
                    mDiscoverHolder.discover.setVisibility(View.GONE);
                    mDiscoverHolder.more.setVisibility(View.GONE);
                }
            }
            mDiscoverHolder.colse.setOnClickListener(new CloseOnClick(position));
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

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        int itemType = lists.get(position).itemType;
        if (itemType == 0) {        //是轮播图
            return ONE;
        } else if (itemType == 1) { //是5个item布局
            return TWO;
        } else if (itemType == 2) { //是直播公开课布局
            return THREE;
        } else if (itemType == 3) { //是直播布局 (水平滚动)
            return FOUR;
        } else if (itemType == 4) { //是发现布局
            return FIVE;
        } else {//其他位置返回正常的布局
            return FIVE;
        }
    }

    /**
     * banner的ViewHolder
     */
    public static class BannerHolder extends RecyclerView.ViewHolder {
        FrameLayout mFrameLayout;
        public BannerHolder(View itemView) {
            super(itemView);
            mFrameLayout = itemView.findViewById(R.id.frame_layout);
        }
    }

    /**
     * 5个item的ViewHolder
     */
    public static class FiveItemHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        public FiveItemHolder(View itemView,ViewGroup parent) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.adapter_live_layout_recycler);
            LinearLayoutManager manager = new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false);
            mRecyclerView.setLayoutManager(manager);
        }
    }

    /**
     * Live (scroll) 的ViewHolder
     */
    public static class LiveHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;
        public LiveHolder(View itemView,ViewGroup parent) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.adapter_live_layout_recycler);
            LinearLayoutManager manager = new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false);
            mRecyclerView.setLayoutManager(manager);
        }
    }

    /**
     * discover的ViewHolder
     */
    public static class DiscoverHolder extends RecyclerView.ViewHolder {
        TextView title,content,discover,more;
        ImageView image,colse;
        public DiscoverHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_discover_title);
            content = itemView.findViewById(R.id.text_discover_bottom_content);
            more = itemView.findViewById(R.id.text_discover_more);
            discover = itemView.findViewById(R.id.text_discover);
            image = itemView.findViewById(R.id.text_discover_image);
            colse = itemView.findViewById(R.id.text_discover_image_close);
        }
    }

    class CloseOnClick implements View.OnClickListener{
        int position;
        public CloseOnClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {

        }
    }
}
