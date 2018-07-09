package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeData;
import com.kuanquan.qudao.ui.popupwindow.HomePopupWindow;
import com.kuanquan.qudao.utils.CollectionsUtil;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeData> lists;
    private ViewGroup parentF;
    private HomePopupWindow mHomePopupWindow;

    public HomeAdapter(OnHomeListener mOnHomeListener) {
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
        view = View.inflate(parent.getContext(), R.layout.adapter_discover_layout, null);
        holder = new DiscoverHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeData homeBean = lists.get(position);
        DiscoverHolder mDiscoverHolder = (DiscoverHolder) holder;
        if (homeBean != null) {
            CollectionsUtil.setTextView(mDiscoverHolder.content, homeBean.content + " · " + homeBean.click_num + "阅读");
            CollectionsUtil.setTextView(mDiscoverHolder.title, homeBean.title);
            CollectionsUtil.setTextView(mDiscoverHolder.text_discover_time, homeBean.time);
            GlideUtil.setImage(parentF.getContext(), homeBean.image, mDiscoverHolder.image);
        }
        mDiscoverHolder.colse.setOnClickListener(new CloseOnClick(homeBean, position, mDiscoverHolder.colse, 1));
        mDiscoverHolder.findRelativeLayout.setOnClickListener(new CloseOnClick(homeBean, position, mDiscoverHolder.findRelativeLayout, 4));
        mDiscoverHolder.find_rl_root.setOnClickListener(new CloseOnClick(homeBean, position, mDiscoverHolder.find_rl_root, 5));

        mDiscoverHolder.headerTv.setText("发现");
        mDiscoverHolder.moreTv.setText("设置偏好");
        if (position == 0) {
            mDiscoverHolder.headerLayout.setVisibility(View.VISIBLE);
        } else {
            mDiscoverHolder.headerLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    /**
     * discover的ViewHolder
     */
    public static class DiscoverHolder extends RecyclerView.ViewHolder {
        RelativeLayout headerLayout;
        TextView headerTv, moreTv, text_discover_time;

        TextView title, content, discover, more;
        ImageView image, colse;
        RelativeLayout text_discover_rl;
        RelativeLayout findRelativeLayout;  // 设置偏好
        RelativeLayout find_rl_root;

        public DiscoverHolder(View itemView) {
            super(itemView);
            findRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.text_discover_more_rl);
            find_rl_root = (RelativeLayout) itemView.findViewById(R.id.find_rl_root);
            title = (TextView) itemView.findViewById(R.id.text_discover_title);
            content = (TextView) itemView.findViewById(R.id.text_discover_bottom_content);
            image = (ImageView) itemView.findViewById(R.id.text_discover_image);
            colse = (ImageView) itemView.findViewById(R.id.text_discover_image_close);

            headerLayout = (RelativeLayout) itemView.findViewById(R.id.stick_rl_adapter);
            headerTv = (TextView) itemView.findViewById(R.id.text_live_open);
            moreTv = (TextView) itemView.findViewById(R.id.text_live_more_open);
            text_discover_time = (TextView) itemView.findViewById(R.id.text_discover_time);
        }
    }

    private class CloseOnClick implements View.OnClickListener, HomePopupWindow.IPopuWindowListener {
        int position;
        View close;
        HomeData homeBean;
        int type; // 1 删除发现item     2 查看更多      3 live详情     4 设置偏好    5文章详情

        public CloseOnClick(HomeData homeBean, int position, View close, int type) {
            this.close = close;
            this.position = position;
            this.homeBean = homeBean;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            switch (type) {
                case 1:
                    if (mHomePopupWindow == null) {
                        mHomePopupWindow = new HomePopupWindow(parentF.getContext(), this);
                    }
                    mHomePopupWindow.show(close, position);
                    break;
                case 2:
                    mOnHomeListener.goLive();
                    break;
                case 3:
                    mOnHomeListener.goLiveDetail(homeBean);
                    break;
                case 4:
                    mOnHomeListener.goSetPreference();
                    break;
                case 5:
                    mOnHomeListener.goFindDetail(homeBean, position);
                    break;
            }
        }

        @Override
        public void dispose(int position) {
            if (mHomePopupWindow.isShowing()) {
                mHomePopupWindow.dismiss();
                mOnHomeListener.onNotify(1);

                notifyItemRemoved(position);
                lists.remove(position);
                if (position != lists.size()) {
                    notifyItemRangeChanged(position, lists.size() - position);
                }
            }
        }
    }

    private OnHomeListener mOnHomeListener;

    public interface OnHomeListener {
        void onNotify(int type);  // 1表示删除

        void goLive();

        void goLiveDetail(HomeData homeBean);

        void goSetPreference(); // 设置偏好

        void goFindDetail(HomeData homeBean, int position); // 文章详情
    }
}
