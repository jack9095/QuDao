package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;
import java.util.List;

/**
 * Created by fei.wang on 2018/6/11.
 *
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    public HomeAdapter(List<HomeBean> data) {
        super(data);
        addItemType(HomeBean.ONE, R.layout.adapter_weex_layout);
        addItemType(HomeBean.TWO, R.layout.adapter_five_item_layout);
        addItemType(HomeBean.THREE, R.layout.adapter_live_open_layout);
        addItemType(HomeBean.FOUR, R.layout.adapter_live_layout);
        addItemType(HomeBean.FIVE, R.layout.adapter_discover_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        switch (helper.getItemViewType()) {
            case HomeBean.ONE:   // banner
                ImageView mImageView = helper.getView(R.id.banner_image);
                GlideUtil.setImageUrl(mContext,item.image,mImageView);
                break;
            case HomeBean.TWO:  // 5个item
//                ImageView elective_course_image = helper.getView(R.id.elective_course_image);
//                GlideUtil.setImage(mContext,item.lists.get(0).image,elective_course_image);
//                helper.setText(R.id.elective_course_text,item.lists.get(0).title);

//                ImageView live_image = helper.getView(R.id.live_image);
//                GlideUtil.setImage(mContext,item.lists.get(1).image,live_image);
//                helper.setText(R.id.live_text,item.lists.get(1).title);

//                ImageView bank_image = helper.getView(R.id.bank_image);
//                GlideUtil.setImage(mContext,item.lists.get(2).image,bank_image);
//                helper.setText(R.id.bank_text,item.lists.get(2).title);

//                ImageView answer_image = helper.getView(R.id.answer_image);
//                GlideUtil.setImage(mContext,item.lists.get(3).image,answer_image);
//                helper.setText(R.id.answer_text,item.lists.get(3).title);

//                ImageView member_image = helper.getView(R.id.member_image);
//                GlideUtil.setImage(mContext,item.lists.get(4).image,member_image);
//                helper.setText(R.id.member_text,item.lists.get(4).title);
                break;
            case HomeBean.THREE:  // 公开
                ImageView live_open_head_image = helper.getView(R.id.live_open_head_image);
                GlideUtil.setImageCircle(mContext,item.image,live_open_head_image);
                helper.setText(R.id.live_open_title,item.title);
                helper.setText(R.id.live_open_content,item.content);
                break;
            case HomeBean.FOUR:
                RecyclerView mRecyclerView = helper.getView(R.id.adapter_live_layout_recycler);
                LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                mRecyclerView.setLayoutManager(manager);
                HomeAdapterChild adapter = new HomeAdapterChild();
                mRecyclerView.setAdapter(adapter);
                adapter.setData(item.lists);
                break;
            case HomeBean.FIVE:
                helper.setText(R.id.text_discover_title,item.title);
                helper.setText(R.id.text_discover_bottom_content,item.content);
                ImageView text_discover_image = helper.getView(R.id.text_discover_image);
                GlideUtil.setImage(mContext,item.image,text_discover_image);
                if (TextUtils.equals("1",item.isDiscover)) {
                    helper.getView(R.id.text_discover).setVisibility(View.VISIBLE);
                    helper.getView(R.id.text_discover_more).setVisibility(View.VISIBLE);
                }else{
                    helper.getView(R.id.text_discover).setVisibility(View.GONE);
                    helper.getView(R.id.text_discover_more).setVisibility(View.GONE);
                }
                helper.getView(R.id.text_discover_image_close).setOnClickListener(new CloseOnClick(getParentPosition(item)));
                break;
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