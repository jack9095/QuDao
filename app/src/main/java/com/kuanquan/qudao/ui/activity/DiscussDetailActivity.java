package com.kuanquan.qudao.ui.activity;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.DetailsArticleBean;
import com.kuanquan.qudao.ui.adapter.DiscussDetailAdapter;
import com.kuanquan.qudao.ui.dialog.KeyMapDailog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 评论详情
 */
public class DiscussDetailActivity extends FragmentActivity implements View.OnClickListener{
    private ListView mListView;
    private TextView title;
    private ImageView fabulous;
    private KeyMapDailog dialog;
    private List<DetailsArticleBean> lists = new ArrayList<>();
    private DetailsArticleBean mDetailsArticleBean;
    private DiscussDetailAdapter mDiscussDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss_detail);

        setFinishOnTouchOutside(true);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.height = WindowManager.LayoutParams.MATCH_PARENT;
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.alpha = 1.0f;
        p.dimAmount = 0.5f;
        getWindow().setAttributes(p);

        for (int i = 0; i < 10; i++) {
            mDetailsArticleBean = new DetailsArticleBean();
            mDetailsArticleBean.itemType = 3;
            mDetailsArticleBean.headImg = "http://t2.hddhhn.com/uploads/tu/201707/5774/e983f93bfb_2.jpg";
            mDetailsArticleBean.name = "露西";
            mDetailsArticleBean.content = "听过很多大道理，却依然过不好这一生，生活的轨道总是偏离设定的路径，在绝望中发现生机，总是徘徊在人生的十字路口，我不是一个会选择的人，往往也总错失良机......";
            mDetailsArticleBean.time = "03-09 17:00";
            mDetailsArticleBean.replyNum = " · 回复(" + 3 + ")";
            mDetailsArticleBean.isFabulous = false;
            lists.add(mDetailsArticleBean);
        }

        initView();
        initData();

        dialog = new KeyMapDailog("评论：", new KeyMapDailog.SendBackListener() {
            @Override
            public void sendBack(final String inputText) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.hideProgressdialog();
                        Toast.makeText(DiscussDetailActivity.this, "发表成功", Toast.LENGTH_LONG).show();
//                        showTv.setText(inputText);
                        dialog.dismiss();
                    }
                }, 2000);
            }
        });
    }

    private void initData() {
        mDiscussDetailAdapter = new DiscussDetailAdapter();
        mListView.setAdapter(mDiscussDetailAdapter);
        mDiscussDetailAdapter.setData(lists);
    }

    private void initView() {
        mListView = findViewById(R.id.activity_discuss_detail_list_view);
        title = findViewById(R.id.activity_discuss_detail_title);
        fabulous = findViewById(R.id.activity_discuss_detail_fabulous_image);

        fabulous.setOnClickListener(this);
        findViewById(R.id.activity_discuss_detail_back).setOnClickListener(this);
        findViewById(R.id.activity_discuss_detail_share_image).setOnClickListener(this);
        findViewById(R.id.activity_discuss_detail_bottom_discuss_layout_ll).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_discuss_detail_back:
                finish();
                break;
            case R.id.activity_discuss_detail_bottom_discuss_layout_ll:
                dialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.activity_discuss_detail_fabulous_image:
                if (Objects.equals(fabulous.getDrawable().getCurrent().getConstantState(), getResources().getDrawable(R.mipmap.wz_detail_zan).getConstantState())) {
                    fabulous.setImageResource(R.mipmap.wz_detail_zan_seleted);
                } else {
                    fabulous.setImageResource(R.mipmap.wz_detail_zan);
                }
                break;
            case R.id.activity_discuss_detail_share_image:

                break;
        }
    }
}
