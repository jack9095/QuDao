package com.example.fly.baselibrary.snackbars;


import com.example.fly.baselibrary.R;

/**
 * 弹出Snackbar时，图标已经背景色的改变
 */
public enum Prompt {//枚举定义的常量
    /**
     * 红色,错误
     */
    ERROR(R.drawable.ic_broken_image_black_24dp, R.color.colorAccent),

    /**
     * 红色,警告
     */
    WARNING(R.drawable.ic_broken_image_black_24dp, R.color.colorAccent),

    /**
     * 绿色,成功
     */
    SUCCESS(R.drawable.ic_broken_image_black_24dp, R.color.colorPrimary);

    private int resIcon;
    private int backgroundColor;

    Prompt(int resIcon, int backgroundColor) {
        this.resIcon = resIcon;
        this.backgroundColor = backgroundColor;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}