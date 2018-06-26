package com.kuanquan.qudao.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.fly.baselibrary.mvpExample.base.BaseFragment;
import com.example.fly.baselibrary.utils.useful.GlideUtil;
import com.example.fly.baselibrary.utils.useful.LogUtil;
import com.example.fly.baselibrary.widget.AnimationUtil;
import com.kuanquan.qudao.R;
import com.kuanquan.qudao.bean.HomeBean;
import com.kuanquan.qudao.core.mvp.presenter.HomePresenter;
import com.kuanquan.qudao.core.mvp.view.IHomeView;
//import com.kuanquan.qudao.ui.adapter.HomeAdapter;
import com.kuanquan.qudao.ui.activity.DetailsArticleActivity;
import com.kuanquan.qudao.ui.adapter.HomeAdapter_Copy;
import com.kuanquan.qudao.utils.AnimatorUtil;
import com.wingsofts.byeburgernavigationview.ByeBurgerBehavior;

import java.util.List;

//import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView,HomeAdapter_Copy.OnHomeListener {
    private RecyclerView mRecyclerView;
    private HomeAdapter_Copy mHomeAdapter;
    private List<HomeBean> lists;
    private ImageView mImageView;
    private RelativeLayout mRelativeLayout;
    private TextView mTextView;

    @Override
    protected HomePresenter createPresent() {
        return new HomePresenter();
    }

    @Override
    protected View initLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_copy, container, false);
    }

    @Override
    protected void initView() {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRelativeLayout = view.findViewById(R.id.home_head_bar);
        mTextView = view.findViewById(R.id.home_im_text);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //设置默认动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        //设置动画时间
//        animator.setAddDuration(2000);
        animator.setRemoveDuration(100);
        mRecyclerView.setItemAnimator(animator);
//        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
//        mRecyclerView.getItemAnimator().setRemoveDuration(1000);

        mImageView = view.findViewById(R.id.bar_head_image);
        GlideUtil.setImageCircle(getContext(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3909870665,3259015587&fm=27&gp=0.jpg", mImageView);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DetailsArticleActivity.class));
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

    @Override
    protected void initData(Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter_Copy(this);
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setData(lists);
//        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                LogUtil.d("onItemClick: ");
//                Toast.makeText(context, "onItemClick" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showData(Object data) {
        lists = (List<HomeBean>) data;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNotify() {
        LogUtil.e("onNotify = " + "onNotify");
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"y",0.0f,134.0f);
        objectAnimator.setDuration(500);   // 动画执行时长
//        objectAnimator.setRepeatCount(0);
        objectAnimator.setInterpolator(new LinearInterpolator());
//        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {//便利类，只要实现需要的方法
            @Override
            public void onAnimationEnd(Animator animation) {
                //在动画结束后回调
//                mTextView.setVisibility(View.GONE);
                mTextView.clearAnimation();
            }
        });
        objectAnimator.start();
//        mTextView.setVisibility(View.VISIBLE);
//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                Animation.ABSOLUTE,0,
//                Animation.ABSOLUTE,0,
//                Animation.ABSOLUTE,0,
//                Animation.ABSOLUTE,134);
//        translateAnimation.setDuration(1000);  // 动画执行时长，单位毫秒
//        translateAnimation.setFillAfter(true);
//        translateAnimation.setFillBefore(false);
//        mTextView.startAnimation(translateAnimation);
//        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                mTextView.clearAnimation();
////                        mTextView.invalidate();
////                        mTextView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"y",0.0f,130.0f);
//        objectAnimator.setDuration(400);   // 动画执行时长
////        objectAnimator.setInterpolator(new LinearInterpolator());
//        objectAnimator.setInterpolator(new AccelerateInterpolator());
//        objectAnimator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                TranslateAnimation translateAnimation = new TranslateAnimation(
//                        Animation.ABSOLUTE,0,
//                        Animation.ABSOLUTE,0,
//                        Animation.ABSOLUTE,134,
//                        Animation.ABSOLUTE,0);
//                translateAnimation.setDuration(1000);  // 动画执行时长，单位毫秒
//                translateAnimation.setFillAfter(true);
//                translateAnimation.setFillBefore(false);
//                mTextView.startAnimation(translateAnimation);
//                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        mTextView.clearAnimation();
////                        mTextView.invalidate();
////                        mTextView.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });

//                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);  // 从显示到隐藏
//                alphaAnimation.setInterpolator(new LinearInterpolator());  // 匀速
//        alphaAnimation.setDuration(2000);  // 设置动画显示时长
//        alphaAnimation.setFillAfter(true); // 动画结束，停留在最后一帧
//                mTextView.startAnimation(alphaAnimation);

//                AnimatorUtil.animAplhaOut(mTextView,500);
//                AnimationUtil.with().moveToViewTop(mTextView,500);
//                AnimationUtil.with().moveToViewBottom(mTextView,500);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"y",134.0f,0.0f);
                objectAnimator.setDuration(500);   // 动画执行时长
                objectAnimator.setRepeatCount(0);
                objectAnimator.setInterpolator(new LinearInterpolator());
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.addListener(new AnimatorListenerAdapter() {//便利类，只要实现需要的方法
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //在动画结束后回调
//                        mTextView.setVisibility(View.GONE);
                        mTextView.clearAnimation();
                    }
                });
                objectAnimator.start();
            }
        },4000);
    }
}
