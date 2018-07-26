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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuanquan.qudao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.wang on 2018/7/6.
 *
 */
public class HomeItemView extends FrameLayout {

    private View stick_view_item;
    private TextView tvOne, tvTwo, tvThree, tvFour, tvFive;
    private RelativeLayout rlOne, rlTwo, rlThree, rlFour, rlFive, rlOne1, rlTwo1, rlThree1, rlFour1, rlFive1;
    private ImageView ivOne, ivTwo, ivThree, ivFour, ivFive, ivOne1, ivTwo1, ivThree1, ivFour1, ivFive1;

    public List<View> views = new ArrayList<>();
    public List<View> viewOnes = new ArrayList<>();
    public List<TextView> textViews = new ArrayList<>();
    public List<ImageView> imageViews = new ArrayList<>();
    public List<ImageView> imageViewOnes = new ArrayList<>();

    public HomeItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public HomeItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HomeItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_item_layout, this, true);
        stick_view_item = view.findViewById(R.id.stick_view_item);
        tvOne = (TextView) view.findViewById(R.id.elective_course_text);
        tvTwo = (TextView) view.findViewById(R.id.live_text);
        tvThree = (TextView) view.findViewById(R.id.bank_text);
        tvFour = (TextView) view.findViewById(R.id.answer_text);
        tvFive = (TextView) view.findViewById(R.id.member_text);
        textViews.add(tvOne);
        textViews.add(tvTwo);
        textViews.add(tvThree);
        textViews.add(tvFour);
        textViews.add(tvFive);

        rlOne = (RelativeLayout) view.findViewById(R.id.course_item);
        rlTwo = (RelativeLayout) view.findViewById(R.id.live_item);
        rlThree = (RelativeLayout) view.findViewById(R.id.bank_item);
        rlFour = (RelativeLayout) view.findViewById(R.id.answer_item);
        rlFive = (RelativeLayout) view.findViewById(R.id.member_item);
        views.add(rlOne);
        views.add(rlTwo);
        views.add(rlThree);
        views.add(rlFour);
        views.add(rlFive);

        ivOne = (ImageView) view.findViewById(R.id.elective_course_image);
        ivTwo = (ImageView) view.findViewById(R.id.live_image);
        ivThree = (ImageView) view.findViewById(R.id.bank_image);
        ivFour = (ImageView) view.findViewById(R.id.answer_image);
        ivFive = (ImageView) view.findViewById(R.id.member_image);
        imageViews.add(ivOne);
        imageViews.add(ivTwo);
        imageViews.add(ivThree);
        imageViews.add(ivFour);
        imageViews.add(ivFive);

        /*rlOne1 = (RelativeLayout) view.findViewById(R.id.course_item_suspension);
        rlTwo1 = (RelativeLayout) view.findViewById(R.id.live_item_suspension);
        rlThree1 = (RelativeLayout) view.findViewById(R.id.bank_item_suspension);
        rlFour1 = (RelativeLayout) view.findViewById(R.id.answer_item_suspension);
        rlFive1 = (RelativeLayout) view.findViewById(R.id.member_item_suspension);
        viewOnes.add(rlOne1);
        viewOnes.add(rlTwo1);
        viewOnes.add(rlThree1);
        viewOnes.add(rlFour1);
        viewOnes.add(rlFive1);

        ivOne1 = (ImageView) view.findViewById(R.id.elective_course_image_suspension);
        ivTwo1 = (ImageView) view.findViewById(R.id.live_image_suspension);
        ivThree1 = (ImageView) view.findViewById(R.id.bank_image_suspension);
        ivFour1 = (ImageView) view.findViewById(R.id.answer_image_suspension);
        ivFive1 = (ImageView) view.findViewById(R.id.member_image_suspension);
        imageViewOnes.add(ivOne1);
        imageViewOnes.add(ivTwo1);
        imageViewOnes.add(ivThree1);
        imageViewOnes.add(ivFour1);
        imageViewOnes.add(ivFive1);*/
    }


//    public ImageView getCollection() {
//        return collection;
//    }
//
//    public ImageView getShare() {
//        return share;
//    }

    public void setOnClick(View.OnClickListener listener) {
        rlOne.setOnClickListener(listener);
        rlTwo.setOnClickListener(listener);
        rlThree.setOnClickListener(listener);
        rlFour.setOnClickListener(listener);
        rlFive.setOnClickListener(listener);
    }

    public void setShowhide(boolean b) {
        if (b) {
            stick_view_item.setVisibility(View.GONE);
        }else{
            stick_view_item.setVisibility(View.VISIBLE);
        }
    }

}
