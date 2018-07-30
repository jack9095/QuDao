package com.kuanquan.qudao.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.kuanquan.qudao.R;

/**
 * Created by fei.wang on 2018/7/24.
 *
 */
public class HomeTitleView extends FrameLayout{

    private ImageView leftImage, rightImage;
    private TextView leftTitle, centerTitle;

    public HomeTitleView(Context context) {
        super(context);
        initView();
    }

    public HomeTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public HomeTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        View root = LayoutInflater.from(getContext()).inflate(R.layout.home_title_bar, this, true);

        leftImage = (ImageView) root.findViewById(R.id.home_title_rl_left_image);
        rightImage = (ImageView) root.findViewById(R.id.home_title_rl_right_image);
        leftTitle = (TextView) root.findViewById(R.id.home_title_rl_left_text);
        centerTitle = (TextView) root.findViewById(R.id.home_title_rl_center_text);
    }


    public void setOnClick(OnClickListener listener){
        leftImage.setOnClickListener(listener);
        rightImage.setOnClickListener(listener);
    }

    // 控件显示和隐藏
    public void changeState(boolean home) {
        if (home) {  // 标题在中间
            leftImage.setVisibility(View.VISIBLE);
            centerTitle.setVisibility(View.VISIBLE);
            leftTitle.setVisibility(View.GONE);
        }else{
            leftImage.setVisibility(View.GONE);
            centerTitle.setVisibility(View.GONE);
            leftTitle.setVisibility(View.VISIBLE);
        }
    }
}
