package com.kuanquan.qudao.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.kuanquan.qudao.R;

/**
 * Created by fei.wang on 2018/6/20.
 * 文章详情分享控件
 */
public class ShareView extends FrameLayout {

    private RelativeLayout fabulous;  // 点赞
    private ImageView fabulousImage;  // 点赞图标
    private RelativeLayout wechat_friend;  // 微信朋友
    private RelativeLayout circle_friend;  // 朋友圈

    public ShareView(@NonNull Context context) {
        super(context);
        init();
    }

    public ShareView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShareView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShareView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.share_view_layout, this, true);
        fabulous = rootView.findViewById(R.id.share_view_layout_fabulous);
        fabulousImage = rootView.findViewById(R.id.share_view_layout_fabulous_image);
        wechat_friend = rootView.findViewById(R.id.share_view_layout_wechat_friend);
        circle_friend = rootView.findViewById(R.id.share_view_layout_circle_friend);
    }



}
