package com.xujun.contralayout.UI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Toast;

import com.xujun.contralayout.R;
import com.xujun.contralayout.UI.FloatingActiobButtton.FloatingActionButtonActivity;
import com.xujun.contralayout.UI.FloatingActiobButtton.HorizontalSample;
import com.xujun.contralayout.UI.bottomsheet.BottomSheetActivity;
import com.xujun.contralayout.UI.cardView.CardViewSample;
import com.xujun.contralayout.UI.drawlayout.DrawLayoutSample;
import com.xujun.contralayout.UI.toolBar.ToolBarSample;
import com.xujun.contralayout.UI.toolBar.ToolBarSampleSnar;
import com.xujun.contralayout.UI.viewPager.ViewPagerNew;
import com.xujun.contralayout.UI.viewPager.ViewPagerParallax;
import com.xujun.contralayout.UI.viewPager.ViewPagerParallaxSnap;
import com.xujun.contralayout.UI.viewPager.ViewPagerSample;
import com.xujun.contralayout.UI.weibo.WeiboSampleActivity;
import com.xujun.contralayout.UI.zhihu.ZhiHuActivity;
import com.xujun.contralayout.UI.zhihu.ZhiHuHomeActivity;
import com.xujun.contralayout.base.BaseMVPActivity;
import com.xujun.contralayout.base.mvp.IBasePresenter;
import com.xujun.contralayout.base.permissions.PermissonListener;

import java.util.List;

public class MainActivity extends BaseMVPActivity {

    public static final String TAG = "xujun";

    String[] mPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected IBasePresenter setPresenter() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                decorView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Rect rect = new Rect();
                //getWindow().getDecorView()得到的View是Window中的最顶层View，可以从Window中获取到该View，
                //然后该View有个getWindowVisibleDisplayFrame()方法可以获取到程序显示的区域，
                //包括标题栏，但不包括状态栏。
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                //1.获取状态栏高度：
                //根据上面所述，我们可以通过rect对象得到手机状态栏的高度
                int statusBarHeight = rect.top;
                //2.获取标题栏高度：
                getWindow().findViewById(Window.ID_ANDROID_CONTENT);
                //该方法获取到的View是程序不包括标题栏的部分，这样我们就可以计算出标题栏的高度了。
                int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
                //statusBarHeight是上面所求的状态栏的高度
                int titleBarHeight = contentTop - statusBarHeight;
                Log.i(TAG, "onGlobalLayout: titleBarHeight=" + titleBarHeight);
                Log.i(TAG, "onGlobalLayout: statusBarHeight=" + statusBarHeight);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        requestPermissions(mPermissions, new PermissonListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> permisons) {
                Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onParmanentDenied(List<String> parmanentDeniedPer) {
                Toast.makeText(MainActivity.this, "权限被永久拒绝", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //屏幕
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.e("WangJ", "屏幕高:" + dm.heightPixels);

        //应用区域
        Rect outRect1 = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);
        Log.e("WangJ", "应用区顶部" + outRect1.top);
        Log.e("WangJ", "应用区高" + outRect1.height());

        //View绘制区域
        Rect outRect2 = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect2);
        Log.e("WangJ", "View绘制区域顶部-错误方法：" + outRect2.top);
        //不能像上边一样由outRect2.top获取，这种方式获得的top是0，可能是bug吧
        int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();   //要用这种方法
        Log.e("WangJ", "View绘制区域顶部-正确方法：" + viewTop);
        Log.e("WangJ", "View绘制区域高度：" + outRect2.height());
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recycler_snap:
                jump(ToolBarSampleSnar.class);
                break;

            case R.id.btn_toolBar:
                jump(ToolBarSample.class);
                break;

            case R.id.btn_viewPager:
                jump(ViewPagerSample.class);
                break;

            case R.id.btn_parallax:
                jump(ViewPagerParallax.class);
                break;

            case R.id.btn_parallax_snap:
                jump(ViewPagerParallaxSnap.class);
                break;

            case R.id.btn_floatingAction:
                jump(FloatingActionButtonActivity.class);
                break;

            case R.id.btn_floatingAction_horizontal:
                jump(HorizontalSample.class);
                break;

            case R.id.btn_zhihu:
                jump(ZhiHuActivity.class);
                break;

            case R.id.btn_zhihu_home:
                jump(ZhiHuHomeActivity.class);
                break;

            case R.id.btn_jianshu:
                jump(JianShuActivity.class);
                break;

            case R.id.btn_cardView:
                jump(CardViewSample.class);
                break;


            case R.id.btn_drawlayout:
                jump(DrawLayoutSample.class);
                break;

            case R.id.btn_bottom_sheet:
                jump(BottomSheetActivity.class);
                break;

            case R.id.btn_viewpager_new:
                jump(ViewPagerNew.class);
                break;


            case R.id.btn_weibo_sample:
                jump(WeiboSampleActivity.class);
                break;


            default:
                break;
        }
    }

    public void jump(Class<? extends Activity> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
}
