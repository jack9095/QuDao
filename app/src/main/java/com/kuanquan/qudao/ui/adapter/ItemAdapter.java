package com.kuanquan.qudao.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.fly.baselibrary.utils.useful.LogUtil;
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

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.e("RecyclerView的点击事件");
            }
        });

    }
}
