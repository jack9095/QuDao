package com.kuanquan.qudao.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kuanquan.qudao.R;
import com.kuanquan.qudao.ui.activity.MainActivity;

import java.util.Objects;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by fei.wang on 2018/6/20.
 * 文章详情底部控件
 */
public class BottomDiscussView extends FrameLayout {

    private RelativeLayout bottom_discuss_layout_discuss_image_rl;  // 评论
    private LinearLayout bottom_discuss_layout_ll;  // 输入控件
    private ImageView discuss_image;  // 评论
    private ImageView collection;  // 收藏
    private ImageView share;  // 分享

    public BottomDiscussView(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomDiscussView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomDiscussView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomDiscussView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_discuss_layout, this, true);
        bottom_discuss_layout_discuss_image_rl = rootView.findViewById(R.id.bottom_discuss_layout_discuss_image_rl);
        bottom_discuss_layout_ll = rootView.findViewById(R.id.bottom_discuss_layout_ll);
        discuss_image = rootView.findViewById(R.id.bottom_discuss_layout_discuss_image);
        collection = rootView.findViewById(R.id.bottom_discuss_layout_fabulous_image);
        share = rootView.findViewById(R.id.bottom_discuss_layout_share_image);
    }

    public LinearLayout getEditText() {
        return bottom_discuss_layout_ll;
    }

    public ImageView getDiscuss() {
        return discuss_image;
    }

    public ImageView getCollection() {
        return collection;
    }

    public ImageView getShare() {
        return share;
    }

    public void setCollectionImage() {
        if (Objects.equals(collection.getDrawable().getCurrent().getConstantState(), getResources().getDrawable(R.mipmap.wz_detail_collection_bottom).getConstantState())) {
            collection.setImageResource(R.mipmap.wz_detail_collection_bottom_selected);
        } else {
            collection.setImageResource(R.mipmap.wz_detail_collection_bottom);
        }
    }

    public void setDiscussNum(int number) {
        Badge badge = new QBadgeView(getContext()).bindTarget(bottom_discuss_layout_discuss_image_rl).setBadgeNumber(number);
        badge.setGravityOffset(7, 2, true);  // 设置外边距
        badge.setBadgeBackgroundColor(Color.parseColor("#3cbda3"));
        badge.setBadgeTextSize(9f, true);
        badge.setBadgePadding(3f, true);
        badge.setShowShadow(true);
    }

}
