package com.kuanquan.qudao.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.kuanquan.qudao.R;

/**
 * Created by fei.wang on 2018/7/24.
 *
 */

public class BottomTabView extends FrameLayout{

    private RadioButton btnHome, btnGoods, btnShopCart, btnUserVip;
    private RelativeLayout homePage, goodsPage, shoppingPage, usePage;

    public BottomTabView(Context context) {
        super(context);
        initView();
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public BottomTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        View root = LayoutInflater.from(getContext()).inflate(R.layout.view_main_tab, this, true);

        homePage = (RelativeLayout) root.findViewById(R.id.homepage);
        goodsPage = (RelativeLayout) root.findViewById(R.id.goods_page);
        shoppingPage = (RelativeLayout) root.findViewById(R.id.shopping_cart_page);
        usePage = (RelativeLayout) root.findViewById(R.id.user_page);

        btnHome = (RadioButton) root.findViewById(R.id.home_button);
        btnGoods = (RadioButton) root.findViewById(R.id.goods_button);
        btnShopCart = (RadioButton) root.findViewById(R.id.shopping_cart_button);
        btnUserVip = (RadioButton) root.findViewById(R.id.user_button);
    }


    public void setOnClick(OnClickListener listener){
        homePage.setOnClickListener(listener);
        goodsPage.setOnClickListener(listener);
        shoppingPage.setOnClickListener(listener);
        usePage.setOnClickListener(listener);
    }

    //改变RadioButton的状态
    public void changeState(boolean homeB, boolean goodsB, boolean shopB, boolean userB) {
        btnHome.setChecked(homeB);
        btnGoods.setChecked(goodsB);
        btnShopCart.setChecked(shopB);
        btnUserVip.setChecked(userB);
    }
}
