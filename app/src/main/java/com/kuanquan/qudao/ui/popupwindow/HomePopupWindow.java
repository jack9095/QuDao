package com.kuanquan.qudao.ui.popupwindow;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.fly.baselibrary.utils.base.ToastUtils;
import com.kuanquan.qudao.R;

/**
 * Created by fei.wang on 2018/6/13.
 *
 */

public class HomePopupWindow extends PopupWindow {
    //上下文
    private Context mContext;
    // PopupWindow中控件点击事件回调接口
    private IPopuWindowListener mOnClickListener;
    //PopupWindow布局文件中的ImageView
    private ImageView mImageView;
    private RelativeLayout mRelativeLayout;
    private int mShowMorePopupWindowWidth;
    private int mShowMorePopupWindowHeight;
    private int position;

    public HomePopupWindow(Context mContext,IPopuWindowListener listener) {
        super(mContext);

        this.mContext = mContext;
        this.mOnClickListener = listener;
        //获取布局文件
        View mContentView = LayoutInflater.from(mContext).inflate(R.layout.home_pop, null,false);
        //设置布局
        setContentView(mContentView);
        mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        mShowMorePopupWindowWidth = mContentView.getMeasuredWidth();
        mShowMorePopupWindowHeight = mContentView.getMeasuredHeight();
        // 设置弹窗的宽度和高度
        setWidth(mShowMorePopupWindowWidth);
        setHeight(mShowMorePopupWindowHeight);
        //设置能否获取到焦点
        setFocusable(false);
        //设置PopupWindow进入和退出时的动画效果
//        setAnimationStyle(R.style.popwindow_exit_anim_style);

        setTouchable(true); // 默认是true，设置为false，所有touch事件无响应，而被PopupWindow覆盖的Activity部分会响应点击
        // 设置弹窗外可点击,此时点击PopupWindow外的范围，Popupwindow不会消失
        setOutsideTouchable(true);
        //外部是否可以点击，设置Drawable原因可以参考：http://blog.csdn.net/harvic880925/article/details/49278705
        setBackgroundDrawable(null);
        // 设置弹窗的布局界面
        initUI();
    }

    /**
     * 初始化弹窗列表
     */
    private void initUI() {
        mRelativeLayout = getContentView().findViewById(R.id.delete_home_rl);
        //获取到按钮
        mImageView = getContentView().findViewById(R.id.delete_home_image);
        //设置按钮点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnClickListener) {
                    mOnClickListener.dispose(position);
                }
            }
        });
    }

    /**
     * 显示弹窗列表界面
     */
    public void show(View view,int position) {
        this.position = position;
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//        //Gravity.BOTTOM设置在view下方，还可以根据location来设置PopupWindowj显示的位置
//        showAtLocation(view, Gravity.BOTTOM, 0, 0); // 这个位置相对于整个屏幕的得自己计算
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRelativeLayout,"x",350.0f,0.0f);
        objectAnimator.setDuration(400);   // 动画执行时长
//        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
        int heightMoreBtnView = view.getHeight();
        showAsDropDown(view, -(mShowMorePopupWindowWidth) - 15,
                -(mShowMorePopupWindowHeight) / 2 - 30);
    }

    /**
     * @description 点击事件回调处理接口
     */
    public interface IPopuWindowListener {
        void dispose(int position);
    }
}
