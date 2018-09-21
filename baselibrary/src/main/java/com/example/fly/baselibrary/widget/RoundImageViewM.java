package com.example.fly.baselibrary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public final class RoundImageViewM extends ImageView
{
	private Bitmap img;

	public RoundImageViewM(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// 强制使用这个模式
		this.setScaleType(ScaleType.CENTER_CROP);
	}

	@Override
	protected final void onDraw(Canvas canvas)
	{
		createBuffer();
		if (img != null)
		{
			canvas.drawBitmap(img, getPaddingLeft(), this.getPaddingTop(), null);
		}
	}

	private final void createBuffer()
	{
		int dstW = this.getWidth();
		int dstH = this.getHeight();
		dstW -= this.getPaddingLeft();
		dstW -= this.getPaddingRight();
		dstH -= this.getPaddingTop();
		dstH -= this.getPaddingBottom();

		int rr;
		if (dstW < dstH)
		{
			rr = dstW;
		}
		else
		{
			rr = dstH;
		}

		int srcW;
		int srcH;
		if (img == null)
		{
			srcW = 0;
			srcH = 0;
		}
		else
		{
			srcW = img.getWidth();
			srcH = img.getHeight();
		}

		if ((dstW == srcW) && (dstH == srcH))
		{
			if ((dstW == 0) || dstH == 0)
			{
				return;
			}
			img.eraseColor(0);
		}
		else
		{
			img = Bitmap.createBitmap(dstW, dstH, Config.ARGB_8888);
		}
		Canvas c = new Canvas(img);
		// //////////////////////////////////////////////////////////////////////////////
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setColor(0xFFFFFFFF);
		c.drawCircle(dstW >> 1, dstH >> 1, rr >> 1, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		if (getDrawable() != null)
		{
			Bitmap b = ((BitmapDrawable) getDrawable()).getBitmap();
			c.drawBitmap(b, this.getImageMatrix(), paint);
		}
	}
}