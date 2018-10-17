package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * 直播适配器
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
                holder = new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_one, parent, false));
                return holder;
            case ITEM_TYPE_TWO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_two, parent,false);
                holder = new LiveHolder(view);
                return holder;
            case ITEM_TYPE_THREE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_three, parent,false);
                holder = new GridHolder(view);
                return holder;
            case ITEM_TYPE_FOUR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_adapter_four, parent,false);
                holder = new RecyclerHolder(view);
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
        LogUtil.e("adapter集合大小&*&*&* = ",lists.size());
        if (integralBean == null) {
            return;
        }
        if (holder instanceof TitleHolder) {   // 第一个item
            TitleHolder titleHolder = (TitleHolder) holder;
            CollectionsUtil.setTextView(titleHolder.left_view_text,integralBean.title);
            CollectionsUtil.setTextView(titleHolder.content_view_text,integralBean.content);
//            mShareHolder.title_view_text.setText(Html.fromHtml("<strong><font color= '#ffffff'><big><big><big>" + integralBean.score + "</big></big></big></font></strong>" + "   积分"));
        } else if (holder instanceof LiveHolder) {  //
            LiveHolder liveHolder = (LiveHolder) holder;
            GlideUtil.setImageCircle(parentF.getContext(),integralBean.headTeacherImage,liveHolder.smallCircle);
            GlideUtil.loadPicture(parentF.getContext(),integralBean.imageUrl,liveHolder.bigRound);
            CollectionsUtil.setTextView(liveHolder.title_view,integralBean.title);
            CollectionsUtil.setTextView(liveHolder.content_text_view,integralBean.content);
            CollectionsUtil.setTextView(liveHolder.time,integralBean.time);
            CollectionsUtil.setTextView(liveHolder.number,integralBean.number);
            liveHolder.item_layout_root.setOnClickListener(new MyOnClick(integralBean,position,1));
        } else if (holder instanceof GridHolder) {  // 预约 (回放)
            GridHolder gridHolder = (GridHolder) holder;
            if (integralBean.type == 1) {
                List<LiveBean> listChilds = integralBean.listLives;
                LogUtil.e("预约集合大小 = ",listChilds.size());
                if (listChilds != null && listChilds.size() > 1) {
                    GlideUtil.loadPicture(parentF.getContext(),listChilds.get(0).imageUrl,gridHolder.imageViewLeft);
                    CollectionsUtil.setTextView(gridHolder.title_left,listChilds.get(0).title);
                    CollectionsUtil.setTextView(gridHolder.left_date,listChilds.get(0).time);
                    CollectionsUtil.setTextView(gridHolder.left_number,listChilds.get(0).number);

                    GlideUtil.loadPicture(parentF.getContext(),listChilds.get(1).imageUrl,gridHolder.imageViewRight);
                    CollectionsUtil.setTextView(gridHolder.title_right,listChilds.get(1).title);
                    CollectionsUtil.setTextView(gridHolder.right_date,listChilds.get(1).time);
                    CollectionsUtil.setTextView(gridHolder.right_number,listChilds.get(1).number);
                }else if (listChilds != null && listChilds.size() > 0) {
                    GlideUtil.loadPicture(parentF.getContext(),listChilds.get(0).imageUrl,gridHolder.imageViewLeft);
                    CollectionsUtil.setTextView(gridHolder.title_left,listChilds.get(0).title);
                    CollectionsUtil.setTextView(gridHolder.left_date,listChilds.get(0).time);
                    CollectionsUtil.setTextView(gridHolder.left_number,listChilds.get(0).number);
                }
            }else if (integralBean.type == 2) {
                List<LiveBean> listChilds = integralBean.listBacks;
                LogUtil.e("回放集合大小 = ",listChilds.size());
                if (listChilds != null && listChilds.size() > 1) {
                    GlideUtil.loadPicture(parentF.getContext(),listChilds.get(0).imageUrl,gridHolder.imageViewLeft);
                    CollectionsUtil.setTextView(gridHolder.title_left,listChilds.get(0).title);
                    CollectionsUtil.setTextView(gridHolder.left_date,listChilds.get(0).time);
                    CollectionsUtil.setTextView(gridHolder.left_number,listChilds.get(0).number);

                    GlideUtil.loadPicture(parentF.getContext(),listChilds.get(1).imageUrl,gridHolder.imageViewRight);
                    CollectionsUtil.setTextView(gridHolder.title_right,listChilds.get(1).title);
                    CollectionsUtil.setTextView(gridHolder.right_date,listChilds.get(1).time);
                    CollectionsUtil.setTextView(gridHolder.right_number,listChilds.get(1).number);
                }else if (listChilds != null && listChilds.size() > 0) {
                    GlideUtil.loadPicture(parentF.getContext(),listChilds.get(0).imageUrl,gridHolder.imageViewLeft);
                    CollectionsUtil.setTextView(gridHolder.title_left,listChilds.get(0).title);
                    CollectionsUtil.setTextView(gridHolder.left_date,listChilds.get(0).time);
                    CollectionsUtil.setTextView(gridHolder.left_number,listChilds.get(0).number);
                }
            }

//            GridHolder gridHolder = (GridHolder) holder;
//            GlideUtil.loadPicture(parentF.getContext(),integralBean.imageUrl,gridHolder.imageViewLeft);
//            CollectionsUtil.setTextView(gridHolder.title_left,integralBean.title);
//            CollectionsUtil.setTextView(gridHolder.left_date,integralBean.time);
//            CollectionsUtil.setTextView(gridHolder.left_number,integralBean.number);
//
//            GlideUtil.loadPicture(parentF.getContext(),integralBean.imageUrlRight,gridHolder.imageViewRight);
//            CollectionsUtil.setTextView(gridHolder.title_right,integralBean.titleRight);
//            CollectionsUtil.setTextView(gridHolder.right_date,integralBean.timeRight);
//            CollectionsUtil.setTextView(gridHolder.right_number,integralBean.numberRight);
        } else if (holder instanceof RecyclerHolder) {  // 专题
            RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
            recyclerHolder.adapterChild.setData(integralBean.listSpecials);
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
            itemType = ITEM_TYPE_FIVE;
        }
        if (itemType == 0) {        //第一个item
            return ITEM_TYPE_ONE;
        } else if (itemType == 1) { //列表
            return ITEM_TYPE_TWO;
        } else if (itemType == 2) { //
            return ITEM_TYPE_THREE;
        } else if (itemType == 3) { //
            return ITEM_TYPE_FOUR;
        } else if (itemType == 4) { //
            return ITEM_TYPE_FIVE;
        } else {//其他位置返回正常的布局
            return ITEM_TYPE_FIVE;
        }
    }

    /**
     * One的ViewHolder
     */
    public static class TitleHolder extends RecyclerView.ViewHolder{
        private TextView left_view_text,content_view_text,right_view_text;
        public TitleHolder(View itemView) {
            super(itemView);
            left_view_text = (TextView) itemView.findViewById(R.id.item_live_adapter_left);
            content_view_text = (TextView) itemView.findViewById(R.id.item_live_adapter_content);
            right_view_text = (TextView) itemView.findViewById(R.id.item_live_adapter_right);
        }
    }

    /**
     * two
     */
    public static class LiveHolder extends RecyclerView.ViewHolder {
        TextView title_view, content_text_view, time,number;
        RoundedImageView bigRound;
        ImageView smallCircle;
        LinearLayout item_layout_root;
        public LiveHolder(View itemView) {
            super(itemView);
            item_layout_root = (LinearLayout) itemView.findViewById(R.id.item_layout_root);
            bigRound = (RoundedImageView) itemView.findViewById(R.id.round_image);
            smallCircle = (ImageView) itemView.findViewById(R.id.circle_round_image);
            title_view = (TextView) itemView.findViewById(R.id.left_text_view_title);
            content_text_view = (TextView) itemView.findViewById(R.id.left_text_view);
            time = (TextView) itemView.findViewById(R.id.right_text_view_time);
            number = (TextView) itemView.findViewById(R.id.right_text_view);
        }
    }

    /**
     * three
     */
    public static class GridHolder extends RecyclerView.ViewHolder {
        TextView title_left, title_right, left_date,right_date,left_number, right_number;
        RoundedImageView imageViewLeft,imageViewRight;
        LinearLayout left_root,right_root;
        View item_live_adapter_three_view;
        public GridHolder(View itemView) {
            super(itemView);
            item_live_adapter_three_view = itemView.findViewById(R.id.item_live_adapter_three_view);
            imageViewLeft = (RoundedImageView) itemView.findViewById(R.id.item_live_adapter_three_image_view);
            imageViewRight = (RoundedImageView) itemView.findViewById(R.id.item_live_adapter_three_image_view_one);
            title_left = (TextView) itemView.findViewById(R.id.item_live_adapter_three_text_view);
            title_right = (TextView) itemView.findViewById(R.id.item_live_adapter_three_text_view_one);
            left_date = (TextView) itemView.findViewById(R.id.item_live_adapter_three_text_view_left_date);
            right_date = (TextView) itemView.findViewById(R.id.item_live_adapter_three_text_view_right_date);
            left_number = (TextView) itemView.findViewById(R.id.item_live_adapter_three_text_view_left_number);
            right_number = (TextView) itemView.findViewById(R.id.item_live_adapter_three_text_view_right_number);
            left_root = (LinearLayout) itemView.findViewById(R.id.item_live_adapter_three_one);
            right_root = (LinearLayout) itemView.findViewById(R.id.item_live_adapter_three_two);
        }
    }

    /**
     * four
     */
    public class RecyclerHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        HomeAdapterChild adapterChild;

        public RecyclerHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.live_item_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(parentF.getContext(),LinearLayoutManager.HORIZONTAL,false));
            adapterChild = new HomeAdapterChild();
            recyclerView.setAdapter(adapterChild);
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
