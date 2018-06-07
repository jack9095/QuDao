package com.kuanquan.qudao.widget;

import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * ImageView包装类，实现以幕布下拉的方式来展现一张图片
 * 假设从Y轴向下展示，此时我要做一个包装类出来，传入要动画的view作为参数，通过设置view的高度来不断更新view。
 */
public class ImageViewWrapper {
    private ImageView imageView;
    private float height;
    private float width;
    private ViewGroup.LayoutParams vl;

    public ImageViewWrapper(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setScaleType(ImageView.ScaleType.CENTER);
        vl = this.imageView.getLayoutParams();
        this.height = vl.height;
        this.width = vl.width;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setHeight(int height){
        vl.height = height;
        imageView.setLayoutParams(vl);
    }

    public void setWidth(int width){
        vl.width = width;
        imageView.setLayoutParams(vl);
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return this.width;
    }
}
