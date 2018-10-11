//package com.kuanquan.qudao.widget;
//
///**
// * Created by July on 2017/5/20.
// * Universal-Image-Loader中RoundedBitmapDisplayer的增强版，可以自定义图片 4 个角中的指定角为圆角
// * <p>
// * 思路是先画一个圆角矩形把这个图片变成圆角，然后想让那个角不是圆角，就把对应角位置那部分的原图画出来即可，画一个矩形就可以把原来的角显示出来，用的方法是drawRect()
// * </p>
// */
//public class FlexibleRoundedBitmapDisplayer implements BitmapDisplayer {
//    protected int cornerRadius;
//
//    protected int corners;
//
//    // 无圆角，不建议使用
//    public static final int CORNER_NONE = 0;
//
//    // 左上角为圆角
//    public static final int CORNER_TOP_LEFT = 1;
//
//    // 右上角为圆角
//    public static final int CORNER_TOP_RIGHT = 1 << 1;
//
//    // 右下角为圆角
//    public static final int CORNER_BOTTOM_LEFT = 1 << 2;
//
//    // 右下角为圆角
//    public static final int CORNER_BOTTOM_RIGHT = 1 << 3;
//
//    public static final int CORNER_ALL = CORNER_TOP_LEFT | CORNER_TOP_RIGHT | CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT;
//
//    /**
//     * 构造方法说明：设定圆角像素大小，所有角都为圆角
//     *
//     * @param cornerRadiusPixels 圆角像素大小
//     */
//
//    public FlexibleRoundedBitmapDisplayer(int cornerRadiusPixels) {
//        this.cornerRadius = cornerRadiusPixels;
//        this.corners = CORNER_ALL;
//    }
//
//    /**
//     * 构造方法说明：设定圆角像素大小，指定角为圆角
//     *
//     * @param cornerRadiusPixels 圆角像素大小
//     * @param corners            自定义圆角 CORNER_NONE 无圆角 CORNER_ALL 全为圆角 CORNER_TOP_LEFT |
//     *                           CORNER_TOP_RIGHT | CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT
//     *                           指定圆角（选其中若干组合 ）
//     */
//    public FlexibleRoundedBitmapDisplayer(int cornerRadiusPixels, int corners) {
//        this.cornerRadius = cornerRadiusPixels;
//        this.corners = corners;
//    }
//
//    @Override
//    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
//        if (!(imageAware instanceof ImageViewAware)) {
//            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
//        } else {
//            imageAware.setImageDrawable(new FlexibleRoundedDrawable(bitmap, cornerRadius, corners));
//        }
//    }
//
//    public static class FlexibleRoundedDrawable extends Drawable {
//        protected final float cornerRadius;
//
//        protected final RectF mRect = new RectF(), mBitmapRect;
//
//        protected final BitmapShader bitmapShader;
//
//        protected final Paint paint;
//
//        private int corners;
//
//        public FlexibleRoundedDrawable(Bitmap bitmap, int cornerRadius, int corners) {
//            this.cornerRadius = cornerRadius;
//            this.corners = corners;
//
//            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            mBitmapRect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
//
//            paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setShader(bitmapShader);
//        }
//
//        @Override
//        protected void onBoundsChange(Rect bounds) {
//            super.onBoundsChange(bounds);
//            mRect.set(0, 0, bounds.width(), bounds.height());
//            Matrix shaderMatrix = new Matrix();
//            shaderMatrix.setRectToRect(mBitmapRect, mRect, Matrix.ScaleToFit.FILL);
//            bitmapShader.setLocalMatrix(shaderMatrix);
//        }
//
//        @Override
//        public void draw(Canvas canvas) {
//            // 先画一个圆角矩形将图片显示为圆角
//            canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
//            // 异或，相同为0，不同为1
//            int notRoundedCorners = corners ^ CORNER_ALL;
//            // 哪个角不是圆角再用小矩形画出来（原理类似图层加了一个白蒙板，然后用不透明度100%的黑色画笔去在蒙板上绘制，画笔所过之处你原来的图层就会显现出来）
//            try {
//                if ((notRoundedCorners & CORNER_TOP_LEFT) != 0) {
//                    // 左上角恢复为直角
//                    canvas.drawRect(0, 0, cornerRadius, cornerRadius, paint);
//                }
//                if ((notRoundedCorners & CORNER_TOP_RIGHT) != 0) {
//                    // 右上角恢复为直角
//                    canvas.drawRect(mRect.right - cornerRadius, 0, mRect.right, cornerRadius, paint);
//                }
//                if ((notRoundedCorners & CORNER_BOTTOM_LEFT) != 0) {
//                    // 左下角恢复为直角
//                    canvas.drawRect(0, mRect.bottom - cornerRadius, cornerRadius, mRect.bottom, paint);
//                }
//                if ((notRoundedCorners & CORNER_BOTTOM_RIGHT) != 0) {
//                    // 右下角恢复为直角
//                    canvas.drawRect(mRect.right - cornerRadius, mRect.bottom - cornerRadius, mRect.right, mRect.bottom,
//                            paint);
//                }
//            } catch (Exception e) {
//                Log.e("FlexibleRoundedBitmapDisplayer ", e + " ");
//            }
//        }
//
//        @Override
//        public int getOpacity() {
//            return PixelFormat.TRANSLUCENT;
//        }
//
//        @Override
//        public void setAlpha(int alpha) {
//            paint.setAlpha(alpha);
//        }
//
//        @Override
//        public void setColorFilter(ColorFilter cf) {
//            paint.setColorFilter(cf);
//        }
//    }
//}