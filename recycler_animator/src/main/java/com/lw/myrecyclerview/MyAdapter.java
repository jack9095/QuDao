package com.lw.myrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 11158 on 2016-11-29.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{
    private List<String> list;
    private Context context;
    private onChildListener listener;
    private RecyclerView recyclerView;


    public void setListener(onChildListener listener) {
        this.listener = listener;
    }

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.imageView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.item_image){
            listener.onImageClick(recyclerView.getChildAdapterPosition((View) view.getParent()));
        }else{
            int position = recyclerView.getChildAdapterPosition(view);
            if (recyclerView!=null&&listener!=null&&!recyclerView.getItemAnimator().isRunning()) {
                listener.onChildClick(recyclerView,view,position,list.get(position));
            }
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }

    /**
     * 当连接到RecyclerView后，提供数据的时候调用这个方法
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView =recyclerView;
    }

    /**
     * 解绑
     * @param recyclerView
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recyclerView = null;
    }

    public interface onChildListener{
        void onChildClick(RecyclerView parent, View view, int position, String data);

        void onImageClick(int position);
    }
    public void Add(int position,String data){
        list.add(position, data);
        notifyItemInserted(position);
    }
    public void Remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void Change(int position,String data){
        list.remove(position);
        list.add(position,data);
        notifyItemChanged(position);
    }
}
