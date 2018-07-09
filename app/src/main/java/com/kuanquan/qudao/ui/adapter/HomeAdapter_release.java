package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.base.GsonUtils;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeData;
import com.kuanquan.qudao.ui.popupwindow.HomePopupWindow;
import com.kuanquan.qudao.utils.CollectionsUtil;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 *
 */
public class HomeAdapter_release extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE = 0;
    private final int THREE = 2;
    private List<HomeData> lists;
    private ViewGroup parentF;
    private HomePopupWindow mHomePopupWindow;
    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;

    public HomeAdapter_release(OnHomeListener mOnHomeListener) {
        this.mOnHomeListener = mOnHomeListener;
    }

    public void setData(List<HomeData> homeBeans) {
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentF = parent;
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case ONE:
                view = View.inflate(parent.getContext(), R.layout.adapter_live_open_layout, null);
                holder = new LiveHolder(view);
                return holder;
            case THREE:
                view = View.inflate(parent.getContext(), R.layout.adapter_discover_layout, null);
                holder = new DiscoverHolder(view);
                return holder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeData homeBean = lists.get(position);
        if (holder instanceof LiveHolder) {  // live
            LiveHolder mLiveHolder = (LiveHolder) holder;
            GlideUtil.setImageCircle(parentF.getContext(), homeBean.image, mLiveHolder.live_open_head_image);
            mLiveHolder.live_open_title.setText(homeBean.title);
            mLiveHolder.live_open_content.setText(homeBean.content);
//            mLiveHolder.itemView.setContentDescription("直播");
        } else if (holder instanceof DiscoverHolder) {  // 发现
            DiscoverHolder mDiscoverHolder = (DiscoverHolder) holder;
            if (homeBean != null) {
                CollectionsUtil.setTextView(mDiscoverHolder.content, homeBean.content);
                CollectionsUtil.setTextView(mDiscoverHolder.title, homeBean.title);
                GlideUtil.setImage(parentF.getContext(), homeBean.image, mDiscoverHolder.image);
            }
            mDiscoverHolder.colse.setOnClickListener(new CloseOnClick(position, mDiscoverHolder.colse));
            if (position == 0) {
                mDiscoverHolder.text_discover_title_stick.setVisibility(View.VISIBLE);
            }else{
                mDiscoverHolder.text_discover_title_stick.setVisibility(View.GONE);
            }
//            mDiscoverHolder.headerTv.setText("发现");
//            mDiscoverHolder.moreTv.setText("设置偏好");
//
//            mDiscoverHolder.itemView.setContentDescription("发现");
//            if (position == 0) {
//                mDiscoverHolder.headerLayout.setVisibility(View.GONE);
//                mDiscoverHolder.itemView.setTag(NONE_STICKY_VIEW);
//            } else {
//                if (homeBean.head.equals(lists.get(position - 1).head)) { //当前Item头部与上一个Item头部相同，则隐藏头部
//                    mDiscoverHolder.headerLayout.setVisibility(View.GONE);
//                    mDiscoverHolder.itemView.setTag(NONE_STICKY_VIEW);
//                } else {
//                    mDiscoverHolder.headerLayout.setVisibility(View.VISIBLE);
//                    mDiscoverHolder.itemView.setTag(HAS_STICKY_VIEW);
//                }
//            }
        }
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = lists.get(position).itemType;
        if (itemType == 0) {        //是直播布局
            return ONE;
        } else if (itemType == 2) { //是发现布局
            return THREE;
        } else {//其他位置返回正常的布局
            return THREE;
        }
    }

    /**
     * Live 的ViewHolder
     */
    public static class LiveHolder extends RecyclerView.ViewHolder {
        ImageView live_open_head_image;
        TextView live_open_title, live_open_content;

        public LiveHolder(View itemView) {
            super(itemView);
            live_open_head_image = itemView.findViewById(R.id.live_open_head_image);
            live_open_title = itemView.findViewById(R.id.live_open_title);
            live_open_content = itemView.findViewById(R.id.live_open_content);
        }
    }

    /**
     * discover的ViewHolder
     */
    public static class DiscoverHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView image, colse;
        RelativeLayout text_discover_title_stick;

        public DiscoverHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_discover_title);
            content = itemView.findViewById(R.id.text_discover_bottom_content);
            image = itemView.findViewById(R.id.text_discover_image);
            colse = itemView.findViewById(R.id.text_discover_image_close);
            text_discover_title_stick = itemView.findViewById(R.id.text_discover_title_stick);
        }
    }

    class CloseOnClick implements View.OnClickListener, HomePopupWindow.IPopuWindowListener {
        int position;
        View close;

        public CloseOnClick(int position, View close) {
            this.close = close;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (mHomePopupWindow == null) {
                mHomePopupWindow = new HomePopupWindow(parentF.getContext(), this);
            }
            mHomePopupWindow.show(close, position);
            LogUtil.e("点击的角标 = " + position);
        }

        @Override
        public void dispose(int position) {
            if (mHomePopupWindow.isShowing()) {
                mHomePopupWindow.dismiss();
                mOnHomeListener.onNotify();
                LogUtil.e("删除的角标 = " + position);
                LogUtil.e("删除的数据 = " + GsonUtils.toJson(lists.get(position)));

                int counntDiscover = 0;
                if (lists.size() > 0) {
                    for (int i = 0; i < lists.size(); i++) {
                        if ("1".equals(lists.get(i).isDiscover)) {
                            counntDiscover++;
                        }
                    }
                }

                if (counntDiscover == 1) {
                    notifyItemRemoved(position);
                    lists.remove(position);
                    if (position != lists.size()) {
                        notifyItemRangeChanged(position, lists.size() - position);
                    }
//                    if (position - 1 >= 0 && position - 1 < lists.size()) {
//                        lists.remove(position - 1);
//                        notifyDataSetChanged();
//                    }
                } else {
                    notifyItemRemoved(position);
                    lists.remove(position);
                    if (position != lists.size()) {
                        notifyItemRangeChanged(position, lists.size() - position);
                    }
                }
            }
        }
    }

    private OnHomeListener mOnHomeListener;

    public interface OnHomeListener {
        void onNotify();
    }
}
