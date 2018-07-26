package com.kuanquan.qudao.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.kuanquan.qudao.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.wang on 2018/7/6.
 *
 */
public class HomeProjectItemView extends FrameLayout {

    private TextView tvOne, tvTwo, tvThree, tvFour, tvFive,tvSix,tvSeven,tvEight;

    public List<TextView> textViews = new ArrayList<>();

    public HomeProjectItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public HomeProjectItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeProjectItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HomeProjectItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view_pager_child_layout, this, true);
        tvOne = (TextView) view.findViewById(R.id.one);
        tvTwo = (TextView) view.findViewById(R.id.two);
        tvThree = (TextView) view.findViewById(R.id.three);
        tvFour = (TextView) view.findViewById(R.id.four);
        tvFive = (TextView) view.findViewById(R.id.five);
        tvSix = (TextView) view.findViewById(R.id.six);
        tvSeven = (TextView) view.findViewById(R.id.seven);
        tvEight = (TextView) view.findViewById(R.id.eight);
        textViews.add(tvOne);
        textViews.add(tvTwo);
        textViews.add(tvThree);
        textViews.add(tvFour);
        textViews.add(tvFive);
        textViews.add(tvSix);
        textViews.add(tvSix);
        textViews.add(tvSeven);
        textViews.add(tvEight);
    }

    public void setOnClick(OnClickListener listener) {
        tvOne.setOnClickListener(listener);
        tvTwo.setOnClickListener(listener);
        tvThree.setOnClickListener(listener);
        tvFour.setOnClickListener(listener);
        tvFive.setOnClickListener(listener);
        tvSix.setOnClickListener(listener);
        tvSeven.setOnClickListener(listener);
        tvEight.setOnClickListener(listener);
    }

}
