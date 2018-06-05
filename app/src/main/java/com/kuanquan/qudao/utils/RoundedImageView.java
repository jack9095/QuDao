package com.kuanquan.qudao.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RoundedImageView extends android.support.v7.widget.AppCompatImageView {

	/*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    private float[] rids = {17.0f,17.0f,17.0f,17.0f,0.0f,0.0f,0.0f,0.0f,};

	public RoundedImageView(Context context) {
		super(context);
	}

	public RoundedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RoundedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	protected void onDraw(Canvas canvas) {

		Path path = new Path();
		int w = this.getWidth();
		int h = this.getHeight();

		/*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
		path.addRoundRect(new RectF(0,0,w,h),rids, Path.Direction.CW);
		canvas.clipPath(path);

		super.onDraw(canvas);
	}
}