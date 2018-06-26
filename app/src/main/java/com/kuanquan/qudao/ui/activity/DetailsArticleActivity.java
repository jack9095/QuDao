package com.kuanquan.qudao.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fly.baselibrary.utils.base.ToastUtils;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.DetailsArticleBean;
import com.kuanquan.qudao.ui.adapter.DetailsArticleAdapter;
import com.kuanquan.qudao.ui.dialog.KeyMapDailog;
import com.kuanquan.qudao.widget.BottomDiscussView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章详情
 */
public class DetailsArticleActivity extends AppCompatActivity implements View.OnClickListener,DetailsArticleAdapter.OnShareListener {
    private DetailsArticleAdapter mDetailsArticleAdapter;
    private RecyclerView recyclerView;
    private List<DetailsArticleBean> lists = new ArrayList<>();
    private DetailsArticleBean mDetailsArticleBean;
    private TextView title;
    private BottomDiscussView mBottomDiscussView;
    private KeyMapDailog dialog;
    String url = "https://m.baidu.com/from=1012852s/s?word=%E5%B7%B4%E5%8E%98%E5%B2%9B&ts=0307421&t_kt=0&ie=utf-8&fm_kl=b26a42b666&" +
            "rsv_iqid=1090308116&rsv_t=c2dbEJhJPefAJ0vOMwixV" +
            "WqB6iOhhVxxQLyQquz%252F5XUgPAfwO9liAVJ%252BXki86eQ&sa=is_4&rsv_pq=1090308116&rsv_sug4=10218&inputT=3904&ss=100&rq=%E5%B7%B4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_article);

        mDetailsArticleBean = new DetailsArticleBean();
        mDetailsArticleBean.itemType = 0;
        mDetailsArticleBean.h5Url = url;
        lists.add(mDetailsArticleBean);

        mDetailsArticleBean = new DetailsArticleBean();
        mDetailsArticleBean.itemType = 1;
        lists.add(mDetailsArticleBean);

        mDetailsArticleBean = new DetailsArticleBean();
        mDetailsArticleBean.itemType = 2;
        lists.add(mDetailsArticleBean);

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

        initRecycler();
        initView();

        dialog = new KeyMapDailog("评论：", new KeyMapDailog.SendBackListener() {
            @Override
            public void sendBack(final String inputText) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.hideProgressdialog();
                        Toast.makeText(DetailsArticleActivity.this, "发表成功", Toast.LENGTH_LONG).show();
//                        showTv.setText(inputText);
                        dialog.dismiss();
                    }
                }, 2000);
            }
        });
    }

    private void initView() {
        findViewById(R.id.activity_details_article_back).setOnClickListener(this);
        title = findViewById(R.id.activity_details_article_title);
        mBottomDiscussView = findViewById(R.id.activity_details_article_bottom_discuss_view);
        mBottomDiscussView.setDiscussNum(5);
        mBottomDiscussView.getCollection().setOnClickListener(this);
        mBottomDiscussView.getDiscuss().setOnClickListener(this);
        mBottomDiscussView.getEditText().setOnClickListener(this);
        mBottomDiscussView.getShare().setOnClickListener(this);
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.activity_details_article_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mDetailsArticleAdapter = new DetailsArticleAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mDetailsArticleAdapter);
        mDetailsArticleAdapter.setData(lists);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_details_article_back:
                finish();
                break;
            case R.id.bottom_discuss_layout_ll:
                dialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.bottom_discuss_layout_discuss_image:
                startActivity(new Intent(this,DiscussDetailActivity.class));
                break;
            case R.id.bottom_discuss_layout_fabulous_image:
                mBottomDiscussView.setCollectionImage();
                break;
            case R.id.bottom_discuss_layout_share_image:
                break;
        }
    }

    @Override
    public void wechatFriend() {
        ToastUtils.showShort("微信朋友");
    }

    @Override
    public void circleFriend() {
        ToastUtils.showShort("朋友圈");
    }
}