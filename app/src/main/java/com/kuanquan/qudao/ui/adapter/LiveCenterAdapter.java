package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.LiveBean;
import com.kuanquan.qudao.widget.live.weight.RoundImageView;

import java.util.List;

/**
 */
public class LiveCenterAdapter extends RecyclerView.Adapter {
    private static final int ITEM_TYPE_ONE = 0;
    private static final int ITEM_TYPE_TWO = 1;
    private static final int ITEM_TYPE_THREE = 2;
    private static final int ITEM_TYPE_FOUR = 3;
    private static final int ITEM_TYPE_FIVE = 4;
    private List<LiveBean> lists;
    private ViewGroup parentF;
    public RelativeLayout loadLayout;
    public TextView loadText;
    public ProgressBar loadProgress;

    public LiveCenterAdapter(OnShareListener mOnShareListener, List<LiveBean> homeBeans) {
        this.lists = homeBeans;
        this.mOnShareListener = mOnShareListener;
    }

    public void setData(List<LiveBean> homeBeans) {
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentF = parent;
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case ITEM_TYPE_ONE:
                holder = new ShareHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_one, parent, false));
                return holder;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_two, parent,false);
                holder = new DiscussHolder(view);
                return holder;
            case ITEM_TYPE_THREE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_three, parent,false);
                holder = new DiscussHolder(view);
                return holder;
            case ITEM_TYPE_FOUR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_four, parent,false);
                holder = new DiscussHolder(view);
                return holder;
            case ITEM_TYPE_FIVE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, parent, false);
                loadLayout = (RelativeLayout) view.findViewById(R.id.load_layout);
                loadLayout.setVisibility(View.VISIBLE);
//                loadLayout.setBackgroundColor(Color.parseColor("#efefef"));
                loadText = (TextView) view.findViewById(R.id.listview_foot_text);
                loadText.setText("正在加载...");
                loadText.setVisibility(View.GONE);
                loadProgress = (ProgressBar) view.findViewById(R.id.listview_foot_progress);
                loadProgress.setVisibility(View.GONE);
                return new FootViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= lists.size()) {
            return;
        }
        LiveBean integralBean = lists.get(position);
        if (holder instanceof ShareHolder) {   // 第一个item
            ShareHolder mShareHolder = (ShareHolder) holder;
//            CollectionsUtil.setTextView(mShareHolder.title_view_text,integralBean.score);
//            mShareHolder.title_view_text.setText(Html.fromHtml("<strong><font color= '#ffffff'><big><big><big>" + integralBean.score + "</big></big></big></font></strong>" + "   积分"));
            mShareHolder.right_view_text.setOnClickListener(new MyOnClick(integralBean,position,2));
        } else if (holder instanceof DiscussHolder) {  // 列表
            DiscussHolder mDiscussHolder = (DiscussHolder) holder;



        }
    }

    @Override
    public int getItemCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size() + 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        int itemType = lists.get(position).itemType;
        int itemType;
        if (position < lists.size()) {
            itemType = lists.get(position).itemType;
        } else {
            itemType = ITEM_TYPE_THREE;
        }
        if (itemType == 0) {        //第一个item
            return ITEM_TYPE_ONE;
        } else if (itemType == 1) { //列表
            return ITEM_TYPE_TWO;
        } else if (itemType == 2) { //脚部
            return ITEM_TYPE_THREE;
        } else {//其他位置返回正常的布局
            return ITEM_TYPE_TWO;
        }
    }

    /**
     * One的ViewHolder
     */
    public static class ShareHolder extends RecyclerView.ViewHolder{
        private TextView left_view_text,content_view_text,right_view_text;
        public ShareHolder(View itemView) {
            super(itemView);
            left_view_text = (TextView) itemView.findViewById(R.id.item_live_adapter_left);
            content_view_text = (TextView) itemView.findViewById(R.id.item_live_adapter_content);
            right_view_text = (TextView) itemView.findViewById(R.id.item_live_adapter_right);
        }
    }

    /**
     * two
     */
    public static class DiscussHolder extends RecyclerView.ViewHolder {
        TextView title_view, left_text_view, right_text_view;
        RoundImageView bigRound,smallCircle;

        public DiscussHolder(View itemView) {
            super(itemView);
//            bigRound = itemView.findViewById(R.id.)
//            title_view = (TextView) itemView.findViewById(R.id.title_view); // 日期
            left_text_view = (TextView) itemView.findViewById(R.id.left_text_view);
            right_text_view = (TextView) itemView.findViewById(R.id.right_text_view);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public FootViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.listview_foot_text);
        }
    }

    private class MyOnClick implements View.OnClickListener{
        int position;
        LiveBean mIntegralBean;
        int type; // 1 积分商城     2 赚取积分

        public MyOnClick(LiveBean mIntegralBean,int position,int type) {
            this.position = position;
            this.mIntegralBean = mIntegralBean;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            switch (type) {
                case 1:
                    mOnShareListener.shoppingMall(mIntegralBean);  // 积分商城
                    break;
                case 2:
                    mOnShareListener.earn(mIntegralBean);  // 赚取积分
                    break;
            }
        }
    }

    private OnShareListener mOnShareListener;
    public interface OnShareListener{
        void shoppingMall(LiveBean mIntegralBean);
        void earn(LiveBean mIntegralBean);
    }
}
