package com.example.lttechdemo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.lttechdemo.R;
import com.example.lttechdemo.adapter.StickyHeaderAdapter;
import com.example.lttechdemo.bean.StickModel;
import java.util.ArrayList;
import java.util.List;

/**
 * 粘性头部界面
 */
public class StickyHeaderActivityDemo extends BaseActivity {

    private RecyclerView mRecyclerView;
    private StickyHeaderAdapter mAdapter;
    private LinearLayout headerView;
    private TextView headerViewText;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_stickyheader_demo);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initViews() {
        headerView = (LinearLayout) findViewById(R.id.sticky_header);
        headerViewText = (TextView) findViewById(R.id.header_textview);
        headerViewText.setText(getDatas().get(0).getHeader());
        initRecycler();
    }

    /**
     * 初始化自定义动画的RecyclerView
     */
    private void initRecycler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.stickyheader_recyclerview);
        mAdapter = new StickyHeaderAdapter(getDatas(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RvScrollListener());
    }

    /**
     * 监听RecyclerView滚动，实现粘性头部
     */
    private class RvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            View stickyInfoView = recyclerView.getChildAt(0);//获取头部View
            if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                headerView.setVisibility(View.VISIBLE);
                headerViewText.setText(String.valueOf(stickyInfoView.getContentDescription()));
            }
            View transInfoView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2
                    , headerView.getMeasuredHeight() + 1);//位于headerView下方的itemView（该坐标是否在itemView内）
            if (transInfoView != null && transInfoView.getTag() != null) {
                int tag = (int) transInfoView.getTag();
                int deltaY = transInfoView.getTop() - headerView.getMeasuredHeight();
                if (tag == StickyHeaderAdapter.HAS_STICKY_VIEW){//当Item包含粘性头部一类时
                    if (transInfoView.getTop() > 0){//当Item还未移动出顶部时
                        headerView.setVisibility(View.GONE);
                        headerView.setTranslationY(deltaY);
                    } else{//当Item移出顶部，粘性头部复原
                        headerView.setVisibility(View.VISIBLE);
                        headerView.setTranslationY(0);
                    }
                } else{//当Item不包含粘性头部时
                    headerView.setTranslationY(0);
                    headerView.setVisibility(View.VISIBLE);
//                    headerView.setTranslationY(deltaY);
                }
            }
        }
    }

    private List<StickModel> getDatas() {
        List<StickModel> dataList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            StickModel model = new StickModel();
            model.setHeader("2018.01.01");
            model.setContent("下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦下班啦吃饭啦");
            model.setTime("18:00");
            dataList.add(model);
        }
        for (int i = 0; i < 10; i++) {
            StickModel model = new StickModel();
            model.setHeader("2018.02.02");
            model.setContent("上班了苦逼了上班了苦逼了上班了苦逼了上班了苦逼了上班了苦逼了上班了苦逼了上班了苦逼了上班了苦逼了上班了苦逼了");
            model.setTime("08:00");
            dataList.add(model);
        }
        return dataList;
    }
}
