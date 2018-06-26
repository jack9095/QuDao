package com.kuanquan.qudao.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.DetailsArticleBean;

import java.util.List;

/**
 * Created by fei.wang on 2018/6/26.
 * 评论详情
 */
public class DiscussDetailAdapter extends BaseAdapter {
    private List<DetailsArticleBean> lists;

    public void setData(List<DetailsArticleBean> homeBeans) {
        this.lists = homeBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup viewGroup) {
        ViewHolder holder;
        DetailsArticleBean detailsArticleBean = lists.get(position);
        if (itemView == null) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_discuss_layout,null,false);
            holder = new ViewHolder();
            holder.mImageView = itemView.findViewById(R.id.adapter_discuss_layout_head_img);
            holder.name = itemView.findViewById(R.id.adapter_discuss_layout_name);
            holder.fabulous = itemView.findViewById(R.id.adapter_discuss_layout_fabulous);
            holder.content = itemView.findViewById(R.id.adapter_discuss_layout_content);
            holder.time = itemView.findViewById(R.id.adapter_discuss_layout_time);
            itemView.setTag(holder);
        }else{
            holder = (ViewHolder) itemView.getTag();
        }

        GlideUtil.setImageCircle(viewGroup.getContext(), detailsArticleBean.headImg, holder.mImageView);
        setTextView(holder.content,detailsArticleBean.content);
        setTextView(holder.name,detailsArticleBean.name);
        setTextView(holder.time,detailsArticleBean.time + detailsArticleBean.replyNum);

        if (detailsArticleBean.isFabulous) {
            holder.fabulous.setImageResource(R.mipmap.wz_detail_zan_seleted);
        }else{
            holder.fabulous.setImageResource(R.mipmap.wz_detail_zan);
        }
        
        return itemView;
    }

    class ViewHolder{
        ImageView mImageView, fabulous;
        TextView name, content, time;
    }

    private void setTextView(TextView mTextView, String str) {
        if (mTextView != null) {
            if (!TextUtils.isEmpty(str)) {
                mTextView.setText(str);
            }else{
                mTextView.setText("");
            }
        }
    }
}
