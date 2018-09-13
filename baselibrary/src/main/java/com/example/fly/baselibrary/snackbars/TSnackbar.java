package com.example.fly.baselibrary.snackbars;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.fly.baselibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * TSnackbar提供轻量级的反馈对一个操作。他们显示一个简短的信息
 * 包含一组动作是通过{ setAction(CharSequence进行View.OnClickListener)}。
 * 通知Snackbar已被证明或者被辞退的,您可以提供一个{ setCallback(回调)} }
 */
public final class TSnackbar {

    /**
     * 回调类的实例。setCallback(Callback)
     */
    public static abstract class Callback {
        /**
         * 表明TSnackbar通过猛击而消失。
         */
        public static final int DISMISS_EVENT_SWIPE = 0;
        /**
         * 表明TSnackbar通过操作点击而消失。
         */
        public static final int DISMISS_EVENT_ACTION = 1;
        /**
         * 表明TSnackbar通过超时而消失。
         */
        public static final int DISMISS_EVENT_TIMEOUT = 2;
        /**
         * 表明TSnackbar通过调用{ dismiss()}而消失
         */
        public static final int DISMISS_EVENT_MANUAL = 3;
        /**
         * 表明TSnackbar被另一个Tsnackbar出现而消失
         */
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;


        @IntDef({DISMISS_EVENT_SWIPE, DISMISS_EVENT_ACTION, DISMISS_EVENT_TIMEOUT,
                DISMISS_EVENT_MANUAL, DISMISS_EVENT_CONSECUTIVE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        /**
         * snackbar的事件导致消失。之一:
         * {   DISMISS_EVENT_SWIPE },{  DISMISS_EVENT_ACTION },
         * {  DISMISS_EVENT_TIMEOUT },{  DISMISS_EVENT_MANUAL }
         * {  DISMISS_EVENT_CONSECUTIVE }。
         */
        public void onDismissed(TSnackbar TSnackbar, @DismissEvent int event) {
        }

        /**
         * TSnackbar是可见的.
         */
        public void onShown(TSnackbar TSnackbar) {

        }
    }

    @IntDef({APPEAR_FROM_TOP_TO_DOWN, APPEAR_FROM_BOTTOM_TO_TOP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OverSnackAppearDirection {
    }

    /**
     * 显示TSnackbar从上到下
     */
    public static final int APPEAR_FROM_TOP_TO_DOWN = 0;

    /**
     * 显示TSnackbar从下到上
     */
    public static final int APPEAR_FROM_BOTTOM_TO_TOP = 1;

    @IntDef({LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    /**
     * 显示TSnackbar消失。这意味着TSnackbar将显示的时间
     * 这是{ show() },直到它被解职,或另一个TSnackbar显示。
     *
     * @see #setDuration
     */
    public static final int LENGTH_INDEFINITE = -2; //无限长

    /**
     * 显示TSnackbar很短的一段时间。
     *
     * @see #setDuration
     */
    public static final int LENGTH_SHORT = -1;  //短时间

    /**
     * 显示TSnackbar很长一段时间。
     *
     * @see #setDuration
     */
    public static final int LENGTH_LONG = 0;  //长时间

    private static final int ANIMATION_DURATION = 250;
    private static final int ANIMATION_FADE_DURATION = 180;

    private static final Handler sHandler;
    private static final int MSG_SHOW = 0;
    private static final int MSG_DISMISS = 1;

    private
    @OverSnackAppearDirection
    int appearDirection;

    static {
        sHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case MSG_SHOW:
                        ((TSnackbar) message.obj).showView();
                        return true;
                    case MSG_DISMISS:
                        ((TSnackbar) message.obj).hideView(message.arg1);
                        return true;
                }
                return false;
            }
        });
    }

    private final ViewGroup mParent;
    private final Context mContext;
    private final SnackbarLayout mView;
    private int mDuration;
    private Callback mCallback;

    private final AccessibilityManager mAccessibilityManager;

    private TSnackbar(ViewGroup parent) {
        appearDirection = APPEAR_FROM_TOP_TO_DOWN;

        mParent = parent;
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //显示Snackbar的布局
        mView = (SnackbarLayout) inflater.inflate(R.layout.view_tsnackbar_layout, mParent, false);
        mAccessibilityManager = (AccessibilityManager)
                mContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
    }

    private TSnackbar(ViewGroup parent, @OverSnackAppearDirection int appearDirection) {
        this(parent);
        this.appearDirection = appearDirection;
        if (appearDirection == APPEAR_FROM_TOP_TO_DOWN) {
            setMinHeight(0, 0);
        }
    }

    /**
     * 设置TSnackbar最小的高度
     */
    public TSnackbar setMinHeight(int stateBarHeight, int actionBarHeight) {
        if (appearDirection == APPEAR_FROM_TOP_TO_DOWN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (stateBarHeight > 0 || actionBarHeight > 0) {
                    mView.setPadding(0, stateBarHeight, 0, 0);
                    mView.setMinimumHeight(stateBarHeight + actionBarHeight);
                } else {
                    mView.setPadding(0, ScreenUtil.getStatusHeight(mContext), 0, 0);
                    mView.setMinimumHeight(ScreenUtil.getActionBarHeight(mContext) + ScreenUtil.getStatusHeight(mContext));
                }
            } else {
                if (stateBarHeight > 0 || actionBarHeight > 0) {
                    mView.setMinimumHeight(actionBarHeight);
                    ScreenUtil.setMargins(mView, 0, stateBarHeight, 0, 0);
                } else {
                    mView.setMinimumHeight(ScreenUtil.getActionBarHeight(mContext));
                    ScreenUtil.setMargins(mView, 0, ScreenUtil.getStatusHeight(mContext), 0, 0);
                }
            }
        }
        return this;
    }


    /**
     * TSnackbar显示一条消息
     * 这是定义为一个{ @link CoordinatorLayout }或窗口装饰的内容来看,以先到期者作准。
     * 有一个{ CoordinatorLayout }在视图层次结构允许TSnackbar启用某些特性,
     * 如swipe-to-dismiss和自动移动的小部件{ @link FloatingActionButton }。
     *
     * @param视图的父控件。
     * @param文本显示的文本。可以格式化文本。
     * @param持续时间多久来显示信息。{ LENGTH_SHORT }或{ LENGTH_LONG }
     */
    @NonNull
    public static TSnackbar make(@NonNull View view, @NonNull CharSequence text, @Duration int duration) {
        TSnackbar tSnackbar = new TSnackbar(findSuitableParent(view), APPEAR_FROM_TOP_TO_DOWN);
        tSnackbar.setText(text);
        tSnackbar.setDuration(duration);
        return tSnackbar;
    }

    @NonNull
    public static TSnackbar make(@NonNull View view, @NonNull CharSequence text, @Duration int duration, @OverSnackAppearDirection int appearDirection) {
        TSnackbar tSnackbar = new TSnackbar(findSuitableParent(view), appearDirection); //出现的方向
        tSnackbar.setText(text);
        tSnackbar.setDuration(duration);
        return tSnackbar;
    }


    @NonNull
    public static TSnackbar make(@NonNull View view, @StringRes int resId, @Duration int duration) {
        return make(view, view.getResources().getText(resId), duration);
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {

                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    return (ViewGroup) view;
                } else {
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        return fallback;
    }


    /**
     * 给TSnackbar上添加图标
     *
     * @param resource_id
     * @return
     */
    public TSnackbar addIcon(int resource_id) {
        final TextView tv = mView.getMessageView();
        tv.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(resource_id), null, null, null);
        return this;
    }

    /**
     * @param resource_id image id
     * @param width       image width
     * @param height      image height
     * @return
     */
    public TSnackbar addIcon(int resource_id, int width, int height) {
        final TextView tv = mView.getMessageView();
        if (width > 0 || height > 0) {
            tv.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(Bitmap.createScaledBitmap(((BitmapDrawable) (mContext.getResources().getDrawable(resource_id))).getBitmap(), width, height, true)), null, null, null);
        } else {
            addIcon(resource_id);
        }
        return this;
    }

    /**
     * 显示加载progressBar
     *
     * @param resource_id image id
     * @param left        show textview left
     * @param right       show textview right
     * @return TSnackbar
     */
    public TSnackbar addIconProgressLoading(int resource_id, boolean left, boolean right) {
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_broken_image_black_24dp);//加载进度条
        if (resource_id > 0) {
            drawable = mContext.getResources().getDrawable(resource_id);
        }
        addIconProgressLoading(drawable, left, right);
        return this;
    }

    /**
     * 添加加载的图标
     * @param drawable
     * @param left true 表示在左边
     * @param right
     * @return
     */
    @SuppressLint("WrongConstant")
    public TSnackbar addIconProgressLoading(Drawable drawable, boolean left, boolean right) {
        final ObjectAnimator animator = ObjectAnimator.ofInt(drawable, "level", 0, 10000);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.INFINITE);
        mView.setBackgroundColor(mContext.getResources().getColor(Prompt.SUCCESS.getBackgroundColor()));
        if (left) {
            mView.getMessageView().setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        if (right) {
            mView.getMessageView().setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mCallback != null) {
                    mCallback.onShown(TSnackbar.this);
                }
                SnackbarManager.getInstance().onShown(mManagerCallback);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
        return this;
    }


    /**
     * 默认风格{错误、警告成功} {ERROR , WARNING , SUCCESS}
     */
    public TSnackbar setPromptThemBackground(Prompt prompt) {
        if (prompt == Prompt.SUCCESS) {
            setBackgroundColor(mContext.getResources().getColor(Prompt.SUCCESS.getBackgroundColor()));
            addIcon(Prompt.SUCCESS.getResIcon(), 0, 0);
        } else if (prompt == Prompt.ERROR) {
            setBackgroundColor(mContext.getResources().getColor(Prompt.ERROR.getBackgroundColor()));
            addIcon(Prompt.ERROR.getResIcon(), 0, 0);
        } else if (prompt == Prompt.WARNING) {
            setBackgroundColor(mContext.getResources().getColor(Prompt.WARNING.getBackgroundColor()));
            addIcon(Prompt.WARNING.getResIcon(), 0, 0);
        }
        return this;
    }

    /**
     * 设置TSnackbar的背景色
     */
    public TSnackbar setBackgroundColor(int colorId) {
        mView.setBackgroundColor(colorId);
        return this;
    }

    /**
     * 设置TSnackbar上的动作
     */
    @NonNull
    public TSnackbar setAction(@StringRes int resId, View.OnClickListener listener) {
        return setAction(mContext.getText(resId), listener);
    }

    @NonNull
    public TSnackbar setAction(CharSequence text, final View.OnClickListener listener) {
        final TextView tv = mView.getActionView();
        if (TextUtils.isEmpty(text) || listener == null) {
            tv.setVisibility(View.GONE);
            tv.setOnClickListener(null);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);

                    dispatchDismiss(Callback.DISMISS_EVENT_ACTION);
                }
            });
        }
        return this;
    }

    /**
     * 设置这个动作上文字的颜色
     */
    @NonNull
    public TSnackbar setActionTextColor(ColorStateList colors) {
        final TextView tv = mView.getActionView();
        tv.setTextColor(colors);
        return this;
    }

    @NonNull
    public TSnackbar setActionTextColor(@ColorInt int color) {
        final TextView tv = mView.getActionView();
        tv.setTextColor(color);
        return this;
    }

    /**
     * 更改TSnackbar的文本
     */
    @NonNull
    public TSnackbar setText(@NonNull CharSequence message) {
        final TextView tv = mView.getMessageView();
        tv.setText(message);
        return this;
    }

    @NonNull
    public TSnackbar setText(@StringRes int resId) {
        return setText(mContext.getText(resId));
    }

    /**
     * 设置多长时间显示的视图。
     */
    @NonNull
    public TSnackbar setDuration(@Duration int duration) {
        mDuration = duration;
        return this;
    }

    /**
     * 得到时间
     */
    @Duration
    public int getDuration() {
        return mDuration;
    }

    /**
     * 得到view
     */

    @NonNull
    public View getView() {
        return mView;
    }

    /**
     * 显示TSnackbar
     */
    public void show() {
        SnackbarManager.getInstance().show(mDuration, mManagerCallback);
    }

    /**
     * TSnackbar消失
     */
    public void dismiss() {
        dispatchDismiss(Callback.DISMISS_EVENT_MANUAL);
    }

    private void dispatchDismiss(@Callback.DismissEvent int event) {
        SnackbarManager.getInstance().dismiss(mManagerCallback, event);
    }

    /**
     * 设置回调时TSnackbar的可见性变化。
     */
    @NonNull
    public TSnackbar setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    /**
     * 返回TSnackbar 是否目前显示。
     */
    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(mManagerCallback);
    }

    /**
     * 返回TSnackbar 是否 目前显示,还是排队显示
     */
    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(mManagerCallback);
    }

    private final SnackbarManager.Callback mManagerCallback = new SnackbarManager.Callback() {
        @Override
        public void show() {
            sHandler.sendMessage(sHandler.obtainMessage(MSG_SHOW, TSnackbar.this));
        }

        @Override
        public void dismiss(int event) {
            sHandler.sendMessage(sHandler.obtainMessage(MSG_DISMISS, event, 0, TSnackbar.this));
        }
    };

    final void showView() {
        if (mView.getParent() == null) {
            final ViewGroup.LayoutParams lp = mView.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                // If our LayoutParams are from a CoordinatorLayout, we'll setup our Behavior

                final Behavior behavior = new Behavior();
                behavior.setStartAlphaSwipeDistance(0.1f);
                behavior.setEndAlphaSwipeDistance(0.6f);
                behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
                behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        view.setVisibility(View.GONE);
                        dispatchDismiss(Callback.DISMISS_EVENT_SWIPE);
                    }

                    @Override
                    public void onDragStateChanged(int state) {
                        switch (state) {
                            case SwipeDismissBehavior.STATE_DRAGGING:
                            case SwipeDismissBehavior.STATE_SETTLING:

                                SnackbarManager.getInstance().cancelTimeout(mManagerCallback);
                                break;
                            case SwipeDismissBehavior.STATE_IDLE:

                                SnackbarManager.getInstance().restoreTimeout(mManagerCallback);
                                break;
                        }
                    }
                });
                ((CoordinatorLayout.LayoutParams) lp).setBehavior(behavior);

                ((CoordinatorLayout.LayoutParams) lp).setMargins(0, -30, 0, 0);
            }
            mParent.addView(mView);
        }
        if (ViewCompat.isLaidOut(mView)) {
            animateViewIn();
        } else {

            mView.setOnLayoutChangeListener(new SnackbarLayout.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom) {
                    animateViewIn();
                    mView.setOnLayoutChangeListener(null);
                }
            });
        }

        mView.setOnAttachStateChangeListener(new SnackbarLayout.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                if (isShownOrQueued()) {
                    // If we haven't already been dismissed then this event is coming from a
                    // non-user initiated action. Hence we need to make sure that we callback
                    // and keep our state up to date. We need to post the call since removeView()
                    // will call through to onDetachedFromWindow and thus overflow.
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onViewHidden(Callback.DISMISS_EVENT_MANUAL);
                        }
                    });
                }
            }
        });

        if (ViewCompat.isLaidOut(mView)) {
            if (shouldAnimate()) {
                // If animations are enabled, animate it in
                animateViewIn();
            } else {
                // Else if anims are disabled just call back now
                onViewShown();
            }
        } else {
            // Otherwise, add one of our view_tsnackbar_layout change listeners and show it in when laid out
            mView.setOnLayoutChangeListener(new SnackbarLayout.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom) {
                    mView.setOnLayoutChangeListener(null);

                    if (shouldAnimate()) {
                        // If animations are enabled, animate it in
                        animateViewIn();
                    } else {
                        // Else if anims are disabled just call back now
                        onViewShown();
                    }
                }
            });
        }
    }

    /**
     * 动画就入
     */
    private void animateViewIn() {
        Animation anim;
        if (appearDirection == APPEAR_FROM_TOP_TO_DOWN) {
            anim = getAnimationInFromTopToDown();
        } else {
            anim = getAnimationInFromBottomToTop();
        }
//        anim.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);//快速插入
        anim.setDuration(ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onViewShown();
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mView.startAnimation(anim);
    }

    /**
     * 动画出去
     */
    private void animateViewOut(final int event) {
        Animation anim;

        if (appearDirection == APPEAR_FROM_TOP_TO_DOWN) {
            anim = getAnimationOutFromTopToDown();
        } else {
            anim = getAnimationOutFromBottomToTop();
        }
//        anim.setInterpolator(snackbarutil.AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        anim.setDuration(ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                onViewHidden(event);
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mView.startAnimation(anim);
    }


    private Animation getAnimationInFromTopToDown() {
        return AnimationUtils.loadAnimation(mView.getContext(), R.anim.snackbar_on);
    }

    private Animation getAnimationInFromBottomToTop() {
        return AnimationUtils.loadAnimation(mView.getContext(), R.anim.design_snackbar_in);
    }

    private Animation getAnimationOutFromTopToDown() {
        return AnimationUtils.loadAnimation(mView.getContext(), R.anim.snackbar_out);
    }

    private Animation getAnimationOutFromBottomToTop() {
        return AnimationUtils.loadAnimation(mView.getContext(), R.anim.design_snackbar_out);
    }

    final void hideView(@Callback.DismissEvent final int event) {
        if (shouldAnimate() && mView.getVisibility() == View.VISIBLE) {
            animateViewOut(event);
        } else {
            // If anims are disabled or the view isn't visible, just call back now
            onViewHidden(event);
        }
    }

    private void onViewShown() {
        SnackbarManager.getInstance().onShown(mManagerCallback);
        if (mCallback != null) {
            mCallback.onShown(this);
        }
    }

    private void onViewHidden(int event) {
        // First tell the SnackbarManager that it has been dismissed
        SnackbarManager.getInstance().onDismissed(mManagerCallback);
        // Now call the dismiss listener (if available)
        if (mCallback != null) {
            mCallback.onDismissed(this, event);
        }
        // Lastly, remove the view from the parent (if attached)
        final ViewParent parent = mView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(mView);
        }
    }

    /**
     * 返回true,动画。
     */
    private boolean shouldAnimate() {
        return !mAccessibilityManager.isEnabled();
    }

    private boolean isBeingDragged() {
        final ViewGroup.LayoutParams lp = mView.getLayoutParams();
        if (lp instanceof CoordinatorLayout.LayoutParams) {
            final CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) lp;
            final CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
            if (behavior instanceof SwipeDismissBehavior) {
                return ((SwipeDismissBehavior) behavior).getDragState() != SwipeDismissBehavior.STATE_IDLE;
            }
        }
        return false;
    }


    public static class SnackbarLayout extends LinearLayout {
        private TextView mMessageView;
        private Button mActionView;

        private int mMaxWidth;
        private int mMaxInlineActionWidth;

        interface OnLayoutChangeListener {
            void onLayoutChange(View view, int left, int top, int right, int bottom);
        }

        interface OnAttachStateChangeListener {
            void onViewAttachedToWindow(View v);

            void onViewDetachedFromWindow(View v);
        }

        private OnLayoutChangeListener mOnLayoutChangeListener;
        private OnAttachStateChangeListener mOnAttachStateChangeListener;

        public SnackbarLayout(Context context) {
            this(context, null);
        }

        public SnackbarLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SnackbarLayout);
            mMaxWidth = a.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
            mMaxInlineActionWidth = a.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            if (a.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, a.getDimensionPixelSize(
                        R.styleable.SnackbarLayout_elevation, 0));
            }
            a.recycle();
            setClickable(true);

            // Now inflate our content. We need to do this manually rather than using an <include>
            // in the view_tsnackbar_layout since older versions of the Android do not inflate includes with
            // the correct Context.
            /**
             * 弹出snackbar，显示文本
             */
            LayoutInflater.from(context).inflate(R.layout.view_tsnackbar_layout_include, this);
            ViewCompat.setAccessibilityLiveRegion(this,
                    ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE);
            ViewCompat.setImportantForAccessibility(this,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            mMessageView = (TextView) findViewById(R.id.snackbar_text);
            mActionView = (Button) findViewById(R.id.snackbar_action);
        }

        TextView getMessageView() {
            return mMessageView;
        }

        Button getActionView() {
            return mActionView;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (mMaxWidth > 0 && getMeasuredWidth() > mMaxWidth) {
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.EXACTLY);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
            final int multiLineVPadding = getResources().getDimensionPixelSize(
                    R.dimen.design_snackbar_padding_vertical_2lines);
            final int singleLineVPadding = getResources().getDimensionPixelSize(
                    R.dimen.design_snackbar_padding_vertical);
            final boolean isMultiLine = mMessageView.getLayout().getLineCount() > 1;
            boolean remeasure = false;
            if (isMultiLine && mMaxInlineActionWidth > 0
                    && mActionView.getMeasuredWidth() > mMaxInlineActionWidth) {
                if (updateViewsWithinLayout(VERTICAL, multiLineVPadding,
                        multiLineVPadding - singleLineVPadding)) {
                    remeasure = true;
                }
            } else {
                final int messagePadding = isMultiLine ? multiLineVPadding : singleLineVPadding;
                if (updateViewsWithinLayout(HORIZONTAL, messagePadding, messagePadding)) {
                    remeasure = true;
                }
            }
            if (remeasure) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        void animateChildrenIn(int delay, int duration) {
            ViewCompat.setAlpha(mMessageView, 0f);
            ViewCompat.animate(mMessageView).alpha(1f).setDuration(duration)
                    .setStartDelay(delay).start();
            if (mActionView.getVisibility() == VISIBLE) {
                ViewCompat.setAlpha(mActionView, 0f);
                ViewCompat.animate(mActionView).alpha(1f).setDuration(duration)
                        .setStartDelay(delay).start();
            }
        }

        void animateChildrenOut(int delay, int duration) {
            ViewCompat.setAlpha(mMessageView, 1f);
            ViewCompat.animate(mMessageView).alpha(0f).setDuration(duration)
                    .setStartDelay(delay).start();
            if (mActionView.getVisibility() == VISIBLE) {
                ViewCompat.setAlpha(mActionView, 1f);
                ViewCompat.animate(mActionView).alpha(0f).setDuration(duration)
                        .setStartDelay(delay).start();
            }
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            if (changed && mOnLayoutChangeListener != null) {
                mOnLayoutChangeListener.onLayoutChange(this, l, t, r, b);
            }
        }

        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (mOnAttachStateChangeListener != null) {
                mOnAttachStateChangeListener.onViewAttachedToWindow(this);
            }
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (mOnAttachStateChangeListener != null) {
                mOnAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
        }

        void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            mOnLayoutChangeListener = onLayoutChangeListener;
        }

        void setOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
            mOnAttachStateChangeListener = listener;
        }

        private boolean updateViewsWithinLayout(final int orientation,
                                                final int messagePadTop, final int messagePadBottom) {
            boolean changed = false;
            if (orientation != getOrientation()) {
                setOrientation(orientation);
                changed = true;
            }
            if (mMessageView.getPaddingTop() != messagePadTop
                    || mMessageView.getPaddingBottom() != messagePadBottom) {
                updateTopBottomPadding(mMessageView, messagePadTop, messagePadBottom);
                changed = true;
            }
            return changed;
        }

        private static void updateTopBottomPadding(View view, int topPadding, int bottomPadding) {
            if (ViewCompat.isPaddingRelative(view)) {
                ViewCompat.setPaddingRelative(view,
                        ViewCompat.getPaddingStart(view), topPadding,
                        ViewCompat.getPaddingEnd(view), bottomPadding);
            } else {
                view.setPadding(view.getPaddingLeft(), topPadding,
                        view.getPaddingRight(), bottomPadding);
            }
        }
    }

    final class Behavior extends SwipeDismissBehavior<SnackbarLayout> {
        public boolean canSwipeDismissView(View child) {
            return child instanceof SnackbarLayout;
        }

        @Override
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, SnackbarLayout child,
                                             MotionEvent event) {
            // We want to make sure that we disable any Snackbar timeouts if the user is
            // currently touching the Snackbar. We restore the timeout when complete
            if (parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY())) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        SnackbarManager.getInstance().cancelTimeout(mManagerCallback);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        SnackbarManager.getInstance().restoreTimeout(mManagerCallback);
                        break;
                }
            }

            return super.onInterceptTouchEvent(parent, child, event);
        }
    }
}