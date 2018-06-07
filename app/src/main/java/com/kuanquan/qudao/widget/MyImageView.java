package com.kuanquan.qudao.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kuanquan.qudao.widget.FlyPoint;


public class MyImageView extends View {

    private FlyPoint mPoint = new FlyPoint(100);

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPoint != null){
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300,300,mPoint.getRadius(),paint);
        }
        super.onDraw(canvas);
    }

    /**
     * 下面的set方法对应  ObjectAnimator animator = ObjectAnimator.ofInt(myImageView, "radius", 0, 300, 100);
     * 中的 "radius"
     *  如果是setPointRadius 那么属性动画中就应该是 "pointRadius"
     *
      */
//    void setPointRadius(int radius){
    void setRadius(int radius){
        mPoint.setRadius(radius);
        invalidate();
    }
}
