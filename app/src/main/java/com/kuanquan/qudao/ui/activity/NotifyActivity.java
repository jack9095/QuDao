package com.kuanquan.qudao.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.bean.HomeBeanChild;
import com.kuanquan.qudao.ui.adapter.HomeAdapter_Copy;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息主页
 */
public class NotifyActivity extends AppCompatActivity implements HomeAdapter_Copy.OnHomeListener {
    private RecyclerView mRecyclerView;
    private HomeAdapter_Copy mHomeAdapter;
    private ImageView mImageView;
    private RelativeLayout mRelativeLayout;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        getData();
        initView();
        initData();
    }

    protected void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRelativeLayout = findViewById(R.id.home_head_bar);
        mTextView = findViewById(R.id.home_im_text);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        //设置默认动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        //设置动画时间
//        animator.setAddDuration(2000);
        animator.setRemoveDuration(100);
        mRecyclerView.setItemAnimator(animator);
//        mRecyclersetItemAnimator(new SlideInLeftAnimator());
//        mRecyclergetItemAnimator().setRemoveDuration(1000);

        mImageView = findViewById(R.id.bar_head_image);
        GlideUtil.setImageCircle(NotifyActivity.this, "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3909870665,3259015587&fm=27&gp=0.jpg", mImageView);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotifyActivity.this, DetailsArticleActivity.class));
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollThreshold;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        onScrollUp();
                    } else {
                        onScrollDown();
                    }
                }
            }

            public void setScrollThreshold(int scrollThreshold) {
                mScrollThreshold = scrollThreshold;
            }
        });
    }

    /**
     * 下滑监听
     */
    private void onScrollDown() {
        LogUtil.e("下滑监听");
        //下滑时要执行的代码
//        ByeBurgerBehavior.from(mRelativeLayout).show();
    }

    /**
     * 上滑监听
     */
    private void onScrollUp() {
        //上滑时要执行的代码

    }

    protected void initData() {
        mHomeAdapter = new HomeAdapter_Copy(this);
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setData(lists);
    }


    @Override
    public void onNotify() {
        LogUtil.e("onNotify = " + "onNotify");
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"y",0.0f,134.0f);
        objectAnimator.setDuration(500);   // 动画执行时长
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {//便利类，只要实现需要的方法
            @Override
            public void onAnimationEnd(Animator animation) {
                //在动画结束后回调
                mTextView.clearAnimation();
            }
        });
        objectAnimator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"y",134.0f,0.0f);
                objectAnimator.setDuration(500);   // 动画执行时长
                objectAnimator.setRepeatCount(0);
                objectAnimator.setInterpolator(new LinearInterpolator());
//                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.addListener(new AnimatorListenerAdapter() {//便利类，只要实现需要的方法
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //在动画结束后回调
                        mTextView.clearAnimation();
                    }
                });
                objectAnimator.start();
            }
        },4000);
    }

    // ********************************数据*************************************************************
    private List<HomeBean> lists = new ArrayList<>();
    private List<HomeBeanChild> listChilds = new ArrayList<>();

    public void getData(){
        HomeBean mHomeBean;

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 1;
        HomeBeanChild banner;
        for (int i = 0; i < 5; i++) {
            banner = new HomeBeanChild();
            banner.title = "趣到";
            banner.image = "http://img5.imgtn.bdimg.com/it/u=3532743473,184108530&fm=200&gp=0.jpg";
            listChilds.add(banner);
        }
        mHomeBean.lists.addAll(listChilds);
        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 2;
        listChilds.clear();
        HomeBeanChild mHomeBeanChild;
        for (int i = 0; i < 5; i++) {
            mHomeBeanChild = new HomeBeanChild();
            mHomeBeanChild.title = "趣到";
            mHomeBeanChild.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            listChilds.add(mHomeBeanChild);
        }
        LogUtil.e(listChilds.size());
        mHomeBean.lists.addAll(listChilds);
        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 3;
        mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
        mHomeBean.title = "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
        mHomeBean.content = "主要栏目与内容包括：经济科学新论、经济热点分析、部门经济、地区经济、财政金融研究、学术探讨、会议综述、学术资料、经济体制改革、企业管理、调查与建议、中外学术交流、外国经济理论动态与述评、世界经济、书刊评介等";
        lists.add(mHomeBean);

        // 水平滑动
//        mHomeBean = new HomeBean();
//        mHomeBean.itemType = 3;
//        HomeBeanChild mHomeBeanChildF;
//        listChilds.clear();
//        for (int i = 0; i < 7; i++) {
//            mHomeBeanChildF = new HomeBeanChild();
//            if (i == 6) {
//                mHomeBeanChildF.type = "1";
//            }
//            mHomeBeanChildF.title = "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
//            mHomeBeanChildF.content = "主要栏目与内容包括：经济科学新论、经济热点分析、部门经济、地区经济、财政金融研究、学术探讨、会议综述、学术资料、经济体制改革、企业管理、调查与建议、中外学术交流、外国经济理论动态与述评、世界经济、书刊评介等";
//            mHomeBeanChildF.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
//            listChilds.add(mHomeBeanChildF);
//        }
//        mHomeBean.lists.addAll(listChilds);
//        lists.add(mHomeBean);

        mHomeBean = new HomeBean();
        mHomeBean.itemType = 4;
        lists.add(mHomeBean);


        // 发现
        for (int i = 0; i < 6; i++) {
            mHomeBean = new HomeBean();
            mHomeBean.isDiscover = "1";   // 表示发现
            mHomeBean.itemType = 5;
            mHomeBean.position = lists.size();
            mHomeBean.title = i + "《经济学动态》是中国社会科学院经济研究所主办的向国内外发行的经济类月刊";
            mHomeBean.content = i + "《经济学动态》 · 3211阅读";
            mHomeBean.image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528784678961&di=3d8861c62ef509d7eecf123b99c74dad&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fdcc451da81cb39dbd7c13dcbda160924ab18302d.jpg";
            lists.add(mHomeBean);
        }
    }
}
