package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.LiveBean;
import com.kuanquan.qudao.utils.CollectionsUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.List;

/**
 * Created by fei.wang on 2018/7/26.
 * 专题适配器
 */
public class SpecialAdapter extends RecyclerView.Adapter {
    private static final int ITEM_TYPE_ONE = 0;
    private static final int ITEM_TYPE_TWO = 1;
    private static final int ITEM_TYPE_THREE = 2;
    private List<LiveBean> lists;
    private ViewGroup parentF;
    public RelativeLayout loadLayout;
    public TextView loadText;
    public ProgressBar loadProgress;

    public SpecialAdapter(OnShareListener mOnShareListener, List<LiveBean> homeBeans) {
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
                holder = new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_adapter_one, parent, false));
                return holder;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_adapter_two, parent,false);
                holder = new LiveHolder(view);
                return holder;
            case ITEM_TYPE_THREE:
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
        LogUtil.e("adapter集合大小&*&*&* = ",lists.size());
        if (integralBean == null) {
            return;
        }
        if (holder instanceof TitleHolder) {   // 第一个item
            TitleHolder titleHolder = (TitleHolder) holder;
            GlideUtil.loadPicture(parentF.getContext(),integralBean.imageUrl,titleHolder.imageview);
            CollectionsUtil.setTextView(titleHolder.titleTextView,integralBean.title);
            CollectionsUtil.setTextView(titleHolder.contentTextView,integralBean.content);
//            mShareHolder.title_view_text.setText(Html.fromHtml("<strong><font color= '#ffffff'><big><big><big>" + integralBean.score + "</big></big></big></font></strong>" + "   积分"));
        } else if (holder instanceof LiveHolder) {  //
            LiveHolder liveHolder = (LiveHolder) holder;
            GlideUtil.loadPicture  (parentF.getContext(),integralBean.imageUrl,liveHolder.imageView);
            CollectionsUtil.setTextView(liveHolder.title_view,integralBean.title);
            CollectionsUtil.setTextView(liveHolder.number,integralBean.number);
            liveHolder.item_layout_root.setOnClickListener(new MyOnClick(integralBean,position,1));
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
        } else if (itemType == 2) { //
            return ITEM_TYPE_THREE;
        } else {
            return ITEM_TYPE_THREE;
        }
    }

    /**
     * One的ViewHolder
     */
    public static class TitleHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView,contentTextView;
        private ImageView imageview;
        public TitleHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.item_special_adapter_one_image);
            titleTextView = (TextView) itemView.findViewById(R.id.item_special_adapter_one_title);
            contentTextView = (TextView) itemView.findViewById(R.id.item_special_adapter_one_content);
        }
    }

    /**
     * two
     */
    public static class LiveHolder extends RecyclerView.ViewHolder {
        TextView title_view, time,number;
        RoundedImageView imageView;
        RelativeLayout item_layout_root;
        public LiveHolder(View itemView) {
            super(itemView);
            item_layout_root = (RelativeLayout) itemView.findViewById(R.id.item_special_adapter_two_root);
            imageView = (RoundedImageView) itemView.findViewById(R.id.item_special_adapter_two_round_image);
            title_view = (TextView) itemView.findViewById(R.id.item_special_adapter_two_title);
            number = (TextView) itemView.findViewById(R.id.item_special_adapter_two_number);
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
        int type; // 1 详情     2 专题

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