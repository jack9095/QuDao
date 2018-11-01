package com.kuanquan.qudao.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuanquan.qudao.R;

import java.util.List;


public class RecyclerFAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE = 0;
    private final int TWO = 1;
    List<String> lists;
    public RelativeLayout loadLayout;
    public TextView loadText;
    public ProgressBar loadProgress;
    private ViewGroup parentF;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ONE:
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
                return new MyViewHolder(inflate);
            case TWO:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, parent, false);
                loadLayout = (RelativeLayout) view.findViewById(R.id.load_layout);
                loadText = (TextView) view.findViewById(R.id.listview_foot_text);
                loadText.setText("正在加载...");
                loadText.setVisibility(View.VISIBLE);
                loadProgress = (ProgressBar) view.findViewById(R.id.listview_foot_progress);
                loadProgress.setVisibility(View.VISIBLE);
                return new FootViewHolder(view);
                default:
                    return null;
        }

    }

    public void setData(List<String> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.textView.setText(lists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return lists.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (position < lists.size()) {
            return ONE;
        }else{
            return TWO;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public FootViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.listview_foot_text);
        }
    }

}
