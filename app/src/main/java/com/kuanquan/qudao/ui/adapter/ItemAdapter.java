package com.kuanquan.qudao.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.app.BaseRecyclerAdapter;
import com.kuanquan.qudao.app.BaseRecyclerHolder;

import java.util.List;


public class ItemAdapter extends BaseRecyclerAdapter<String> {

    public ItemAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_string, datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position) {
        TextView  tv=holder.getView(R.id.tv);
        tv.setText(item);

    }
}
