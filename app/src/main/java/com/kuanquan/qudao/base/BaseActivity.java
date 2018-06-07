package com.kuanquan.qudao.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.event.RxEvent;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;


public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected FrameLayout mContainerLayout;
    private ProgressDialog loadingDialog = null;
    private PublishSubject mSubject;
    private DisposableObserver mDisposableObserver;
    private RxEvent mRxEvent;

    @Override
    protected void onCreate(Bundle bundle) {
        if (bundle != null) {
            //如果系统回收Activity,但是系统却保留了Fragment,当Activity被重新初始化,此时,系统保存的Fragment 的getActivity为空，
            //所以要移除旧的Fragment,重新初始化新的Fragment
            String FRAGMENTS_TAG = "android:support:fragments";
            bundle.remove(FRAGMENTS_TAG);
        }
        super.onCreate(bundle);
        //如果有底部导航栏，将内容布局设置在导航栏上方
//        boolean isHas = checkDeviceHasNavigationBar();
//        if (isHas) {
//            Log.e(this.getClass().getSimpleName(), "onCreate: isHas " + isHas);
//            getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, getNavigationBarHeight());
//        }
        setContentView(R.layout.activity_base);
        Intent intent = getIntent();
        if (intent != null)
            getIntent(intent);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mContainerLayout = (FrameLayout) findViewById(R.id.frameLayout);
//        if (isHas && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            mToolbar.setFitsSystemWindows(false);
//        }
        //初始化ToolBar
        boolean isToolbar = initToolbar();
        if (isToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                //必须要在setSupportActionBar之后,不然不起作用
                @Override
                public void onClick(View v) {
                    onNavigationClick();
                }
            });
        } else {
            mToolbar.setVisibility(View.GONE);
        }
        //初始化Content
        initContent(getLayoutId());

        mRxEvent = RxEvent.getInstance();
        //注册事件
        mSubject = mRxEvent.registerEvent(registerEvent());
        mDisposableObserver = new ReceiveEvent();
        mSubject.subscribe(mDisposableObserver);
    }





    private class ReceiveEvent extends DisposableObserver {
        @Override
        public void onNext(Object o) {
            receiveEvent(o);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件
        RxEvent.getInstance().unRegisterEvent(registerEvent(), mSubject, mDisposableObserver);
    }

    protected abstract void receiveEvent(Object object);

    protected abstract String registerEvent();

    protected void onNavigationClick() {
        finish();
    }

    protected abstract int getLayoutId();

    protected abstract boolean initToolbar();

    protected abstract void getIntent(Intent intent);

    protected abstract void initViews();

    private void initContent(int layoutId) {
        if (layoutId != 0) {
            View contentView = LayoutInflater.from(this).inflate(layoutId, mContainerLayout, false);
            mContainerLayout.addView(contentView);
            initViews();
        }
    }

    /**
     * 显示带消息的进度框
     *
     * @param title 提示
     */
    protected void showLoadingDialog(String title) {
        createLoadingDialog();
        loadingDialog.setMessage(title);
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        createLoadingDialog();
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    /**
     * 创建LodingDialog
     */
    private void createLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
