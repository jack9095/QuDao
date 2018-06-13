package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.ui.popupwindow.HomePopupWindow;
import com.kuanquan.qudao.widget.HomeBanner;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 *
 */
public class HomeAdapter_Copy extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements HomeBanner.OnPageClickListener{
    private final int ONE = 0;
    private final int TWO = 1;
    private final int THREE = 2;
    private final int FOUR = 3;
//    private final int FIVE = 0x1113;
    private List<HomeBean> lists;
    private ViewGroup parentF;
    private HomePopupWindow mHomePopupWindow;

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
            case TWO:
                view = getView(R.layout.adapter_five_item_layout,parent);
                holder = new FiveItemHolder(view);
                return holder;
            case THREE:
                view = getView(R.layout.adapter_live_open_layout,parent);
                holder = new LiveHolder(view);
                return holder;
            case FOUR:
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
            mBannerHolder.mHomeBanner.setData(homeBean.lists,this);
        }else if (holder instanceof FiveItemHolder) {   // 5
            FiveItemHolder mFiveItemHolder = (FiveItemHolder) holder;
            List<HomeBeanChild> listChilds = homeBean.lists;

        }else if (holder instanceof LiveHolder) {  // live
            LiveHolder mLiveHolder = (LiveHolder) holder;
            GlideUtil.setImageCircle(parentF.getContext(),homeBean.image,mLiveHolder.live_open_head_image);
            mLiveHolder.live_open_title.setText(homeBean.title);
            mLiveHolder.live_open_content.setText(homeBean.content);
        }else if (holder instanceof DiscoverHolder) {  // 发现
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
                    mDiscoverHolder.text_discover_rl.setVisibility(View.VISIBLE);
                }else{
                    mDiscoverHolder.text_discover_rl.setVisibility(View.GONE);
                }
            }
            mDiscoverHolder.colse.setOnClickListener(new CloseOnClick(position,mDiscoverHolder.colse));
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
        } else if (itemType == 3) { //是发现布局
            return FOUR;
        } else {//其他位置返回正常的布局
            return FOUR;
        }
    }

    @Override
    public void onPageClick(HomeBeanChild info) {

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
        ImageView elective_course_image,live_image,bank_image,answer_image,member_image;
        TextView elective_course_text,live_text,bank_text,answer_text,member_text;
        public FiveItemHolder(View itemView) {
            super(itemView);
            elective_course_image = itemView.findViewById(R.id.elective_course_image);
            elective_course_text = itemView.findViewById(R.id.elective_course_text);

            live_image = itemView.findViewById(R.id.live_image);
            live_text = itemView.findViewById(R.id.live_text);

            bank_image = itemView.findViewById(R.id.bank_image);
            bank_text = itemView.findViewById(R.id.bank_text);

            answer_image = itemView.findViewById(R.id.answer_image);
            answer_text = itemView.findViewById(R.id.answer_text);

            member_image = itemView.findViewById(R.id.member_image);
            member_text = itemView.findViewById(R.id.member_text);
        }
    }

    /**
     * Live 的ViewHolder
     */
    public static class LiveHolder extends RecyclerView.ViewHolder {
        ImageView live_open_head_image;
        TextView live_open_title,live_open_content;
        public LiveHolder(View itemView) {
            super(itemView);
            live_open_head_image = itemView.findViewById(R.id.live_open_head_image);
            live_open_title = itemView.findViewById(R.id.live_open_title);
            live_open_content = itemView.findViewById(R.id.live_open_content);
        }
    }

    /**
     * Live (scroll) 的ViewHolder
     */
//    public static class LiveHolder extends RecyclerView.ViewHolder {
//        RecyclerView mRecyclerView;
//        public LiveHolder(View itemView,ViewGroup parent) {
//            super(itemView);
//            mRecyclerView = itemView.findViewById(R.id.adapter_live_layout_recycler);
//            LinearLayoutManager manager = new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false);
//            mRecyclerView.setLayoutManager(manager);
//        }
//    }

    /**
     * discover的ViewHolder
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

    class CloseOnClick implements View.OnClickListener,HomePopupWindow.IPopuWindowListener{
        int position;
        View close;
        public CloseOnClick(int position,View close) {
            this.close = close;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (mHomePopupWindow == null) {
                mHomePopupWindow = new HomePopupWindow(parentF.getContext(),this);
            }
            mHomePopupWindow.show(close);
        }

        @Override
        public void dispose() {

        }
    }
}
