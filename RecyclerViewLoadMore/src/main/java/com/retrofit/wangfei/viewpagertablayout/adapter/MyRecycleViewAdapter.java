package com.retrofit.wangfei.viewpagertablayout.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.retrofit.wangfei.viewpagertablayout.R;

import java.util.List;


/**
 * Created by Android Studio
 * User: fei.wang
 * Date: 2016-04-14
 * Time: 9:57
 * QQ: 929728742
 * Description: RecycleView的适配器
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter {

    private final static int TYPE_ITEM = 0X01;
    private final static int TYPE_FOOTER = 0x02;

    private List<String> lists;
    private Activity context;

    public LinearLayout loadLayout;

    private OnItemClickListener mOnItemClickListener;  // 声明接口

    public MyRecycleViewAdapter(List<String> lists, Activity context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_ITEM == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent,false);
            ItemViewHolder itemViewHolder = new ItemViewHolder(view);
            return itemViewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, parent,false);
            loadLayout = (LinearLayout) view.findViewById(R.id.load_layout);
            return new FootViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            String text = lists.get(position);
            ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.text.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return lists.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(v,getPosition());
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**调到外部使用*/
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    /**定义接口*/
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}
