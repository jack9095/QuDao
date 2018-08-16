package com.example.androiderasure.weigts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FingerDrawingBoardView extends View {

 // 绘制线条的Paint,即用户手指绘制Path
    private Paint mFingerPaint = null;

    // 记录用户绘制的Path
    private Path mPath = null;

    // 内存中创建的Canvas
    private Canvas mCanvas;

    // mCanvas绘制内容在其上
    private Bitmap mBitmap;

    private int mLastX;
    private int mLastY;

    public FingerDrawingBoardView(Context context) {
        this(context, null);
    }

    public FingerDrawingBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FingerDrawingBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPath = new Path();
        mFingerPaint = new Paint();
        
        setUpOutPaint();
    }

    /**
     * 设置画笔的一些参数(手指绘制部分)
     */
    private void setUpOutPaint() {
        mFingerPaint.setAlpha(0);
        mFingerPaint.setColor(Color.parseColor("#ff0000EE"));
        mFingerPaint.setAntiAlias(true); // 去锯齿
        mFingerPaint.setDither(true); // 防抖动
        mFingerPaint.setStyle(Paint.Style.STROKE);
        mFingerPaint.setStrokeJoin(Paint.Join.ROUND); // 圆角
        mFingerPaint.setStrokeCap(Paint.Cap.ROUND); // 圆角
        mFingerPaint.setStrokeWidth(30); // 设置画笔宽度
    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawPath();
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        
        // 初始化bitmap
        mBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    /**
     * 绘制线条
     */
    private void drawPath() {
        mFingerPaint.setStyle(Paint.Style.STROKE);
//        mFingerPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT)); // 设置两张图片相交时的模式(取下层绘制非交集部分)
        mCanvas.drawPath(mPath, mFingerPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            actionMotionEventDown(x, y);
            break;
            
        case MotionEvent.ACTION_MOVE:
            actionMotionEventMove(x, y);
            break;
        }

        invalidate();
        return true;
    }

    private void actionMotionEventDown(int x, int y) {
        mLastX = x;
        mLastY = y;
        mPath.moveTo(mLastX, mLastY);
    }
    
    private void actionMotionEventMove(int x, int y) {
        int dx = Math.abs(x - mLastX);
        int dy = Math.abs(y - mLastY);

        if (dx > 3 || dy > 3) {
            mPath.lineTo(x, y);
        }

        mLastX = x;
        mLastY = y;
    }
}
