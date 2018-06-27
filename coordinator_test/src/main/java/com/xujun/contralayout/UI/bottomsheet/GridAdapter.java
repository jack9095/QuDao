package com.xujun.contralayout.UI.bottomsheet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;
import com.xujun.contralayout.R;

import java.util.List;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 * @author  xujun  on 2017/5/1 10:50
 * @version 0.1
 */


public  class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Meizi> datas;

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    public GridAdapter(Context context, List<Meizi> datas) {
        mContext=context;
        this.datas=datas;
    }

    @Override
    public GridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
            View view = LayoutInflater.from(mContext
                    ).inflate(R.layout.grid_meizi_item, parent,
                    false);
            MyViewHolder holder = new MyViewHolder(view);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            return holder;
    }

    @Override
    public void onBindViewHolder(GridAdapter.MyViewHolder holder, int position) {
            Picasso.with(mContext).load(datas.get(position).getUrl()).into(holder.iv);

    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(v);
        }

    }


    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageButton iv;

        public MyViewHolder(View view)
        {
            super(view);
            iv = (ImageButton) view.findViewById(R.id.iv);
        }
    }

}
