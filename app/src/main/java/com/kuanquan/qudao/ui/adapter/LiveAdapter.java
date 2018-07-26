package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.utils.CollectionsUtil;
import com.kuanquan.qudao.widget.HomeBanner;

/**
 * Created by fei.wang on 2018/7/26.
 *
 */
public class LiveAdapter extends BaseLiveAdapter implements HomeBanner.OnPageClickListener {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HomeBean homeBean = lists.get(position);
        if (holder instanceof BannerHolder) {   // bannre
            BannerHolder mBannerHolder = (BannerHolder) holder;
            mBannerHolder.mHomeBanner.setData(homeBean.lists,this);
            mBannerHolder.mHomeBanner.setScrollSpeed(mBannerHolder.mHomeBanner);

        }else if (holder instanceof FiveItemHolder) {   // 5个tab

            FiveItemHolder mFiveItemHolder = (FiveItemHolder) holder;
            if (homeBean.tabItems != null && homeBean.tabItems.size() <= mFiveItemHolder.mHomeItemView.views.size() && homeBean.tabItems.size() > 0) {
                for (int i = 0; i < homeBean.tabItems.size(); i++) {
                    mFiveItemHolder.mHomeItemView.views.get(i).setVisibility(View.VISIBLE);
                    CollectionsUtil.setTextView(mFiveItemHolder.mHomeItemView.textViews.get(i), homeBean.tabItems.get(i).title);
                    GlideUtil.setImage(parentF.getContext(), homeBean.tabItems.get(i).image, mFiveItemHolder.mHomeItemView.imageViews.get(i));
                }
            }

        }else if (holder instanceof ProjectItemHolder) {  // project

            ProjectItemHolder ProjectItemHolder = (ProjectItemHolder) holder;
            HomeAdapterRecyclerViewPager adapter = new HomeAdapterRecyclerViewPager();
            ProjectItemHolder.mRecyclerView.setAdapter(adapter);

        }else if (holder instanceof LiveScrollHolder) {  // 名师直播

            LiveScrollHolder mLiveScrollHolder = (LiveScrollHolder) holder;
            HomeAdapterRecyclerViewPager adapter = new HomeAdapterRecyclerViewPager();
            mLiveScrollHolder.mRecyclerView.setAdapter(adapter);

        }else if (holder instanceof DiscoverTitleHolder) {  // 高顿头条

            DiscoverTitleHolder mDiscoverTitleHolder = (DiscoverTitleHolder) holder;


        }else if (holder instanceof DiscoverHolder) {  // 咨询

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
            }
        }
    }

    @Override
    public void onPageClick(HomeBeanChild info) {

    }

}
