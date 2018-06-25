package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.DetailsArticleBean;
import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 *
 */
public class DetailsArticleAdapter extends RecyclerView.Adapter {
    private static final int ITEM_TYPE_ONE = 0;
    private static final int ITEM_TYPE_TWO = 1;
    private static final int ITEM_TYPE_THREE = 2;
    private static final int ITEM_TYPE_FOUR = 3;
    private List<DetailsArticleBean> lists;
    private ViewGroup parentF;

    public void setData(List<DetailsArticleBean> homeBeans){
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentF = parent;
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case ITEM_TYPE_ONE:
                holder = new WebViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_webview, parent, false));
                return holder;
            case ITEM_TYPE_TWO:
                view = View.inflate(parent.getContext(), R.layout.share_view_layout, null);
                holder = new ShareHolder(view);
                return holder;
            case ITEM_TYPE_THREE:
                view = View.inflate(parent.getContext(), R.layout.adapter_discuss_title_layout, null);
                holder = new DiscussTitleHolder(view);
                return holder;
            case ITEM_TYPE_FOUR:
                view = View.inflate(parent.getContext(), R.layout.adapter_discuss_layout, null);
                holder = new DiscussHolder(view);
                return holder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DetailsArticleBean detailsArticleBean = lists.get(position);
        if (holder instanceof WebViewHolder) {   // 是webView
            WebViewHolder mBannerHolder = (WebViewHolder) holder;

        }else if (holder instanceof ShareHolder) {   // 分享
            ShareHolder mShareHolder = (ShareHolder) holder;

        }else if (holder instanceof DiscussHolder) {  // live
            DiscussHolder mDiscussHolder = (DiscussHolder) holder;
            GlideUtil.setImageCircle(parentF.getContext(),detailsArticleBean.headImg,mDiscussHolder.mImageView);

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
        if (itemType == 0) {        //是webView
            return ITEM_TYPE_ONE;
        } else if (itemType == 1) { //是分享
            return ITEM_TYPE_TWO;
        } else if (itemType == 2) { //是评论标题
            return ITEM_TYPE_THREE;
        } else if (itemType == 3) { //是评论列表
            return ITEM_TYPE_FOUR;
        } else {//其他位置返回正常的布局
            return ITEM_TYPE_FOUR;
        }
    }

    /**
     * 分享的ViewHolder
     */
    public static class ShareHolder extends RecyclerView.ViewHolder {
        private RelativeLayout fabulous;  // 点赞
        private ImageView fabulousImage;  // 点赞图标
        private RelativeLayout wechat_friend;  // 微信朋友
        private RelativeLayout circle_friend;  // 朋友圈
        public ShareHolder(View itemView) {
            super(itemView);
            fabulous = itemView.findViewById(R.id.share_view_layout_fabulous);
            fabulousImage = itemView.findViewById(R.id.share_view_layout_fabulous_image);
            wechat_friend = itemView.findViewById(R.id.share_view_layout_wechat_friend);
            circle_friend = itemView.findViewById(R.id.share_view_layout_circle_friend);
        }
    }

    /**
     * 评论标题的ViewHolder
     */
    public static class DiscussTitleHolder extends RecyclerView.ViewHolder {

        public DiscussTitleHolder(View itemView) {
            super(itemView);

        }
    }

    /**
     * 评论的ViewHolder
     */
    public static class DiscussHolder extends RecyclerView.ViewHolder {
        ImageView mImageView,fabulous;
        TextView name,content,time;
        public DiscussHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.adapter_discuss_layout_head_img);
            name = itemView.findViewById(R.id.adapter_discuss_layout_name);
            fabulous = itemView.findViewById(R.id.adapter_discuss_layout_fabulous);
            content = itemView.findViewById(R.id.adapter_discuss_layout_content);
            time = itemView.findViewById(R.id.adapter_discuss_layout_time);
        }
    }
}
