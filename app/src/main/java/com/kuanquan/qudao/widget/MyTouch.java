package com.kuanquan.qudao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by able on 2018/4/3.
 */

public class MyTouch extends View {
    private static String TAG = MyTouch.class.getSimpleName();
    public MyTouch(Context context) {
        super(context);
    }

    public MyTouch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTouch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTouch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#000000"));
        paint.setAntiAlias(true);

//        canvas.drawLine(100,100,400,100,paint);
//        canvas.save();
//        canvas.rotate(60,100,100);
//        canvas.drawLine(100,100,400,100,paint);
//        canvas.save();
////        canvas.translate(400,100);
//        canvas.rotate(60,400,100);
//        canvas.drawLine(400,100,100,100,paint);
//        canvas.restore();
        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(400,100);
        path.lineTo(250,320);
        path.close();
        canvas.drawPath(path,paint);
    }
}
