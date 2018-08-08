//package com.retrofit.wangfei.viewpagertablayout;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import com.gaodun.account.task.GetGdssidTask;
//import com.gaodun.constant.AppConfig;
//import com.gaodun.course.fragment.MyKeListFragment;
//import com.gaodun.course.request.RecCourseReq;
//import com.gaodun.db.shared.SharderManager;
//import com.gaodun.option.fragment.MySpaceFragment;
//import com.gaodun.util.BackgroundManager;
//import com.gaodun.util.Global;
//import com.gaodun.util.network.INetEventListener;
//import com.gaodun.utils.GdwxUtils;
//import com.newPage.FindFragment;
//import com.newPage.HomeFragment;
//import com.newPage.request.UrlDataStream;
//import com.newPage.widget.BottomTabView;
//import com.umeng.analytics.MobclickAgent;
//import com.umeng.message.PushAgent;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *  EmptyView    CourseDirectoryFragment 475
// */
//public final class MainActivity extends AppCompatActivity implements View.OnClickListener,INetEventListener {
//    private BottomTabView mBottomTabView;
//    private FragmentManager fragmentManager;
//    private List<Fragment> fragmentList = new ArrayList<>();
//    private int currentIndex = 0;
//    private GetGdssidTask gdssidTask;  // 保持原有不动（改动太大）
//    private RecCourseReq recCourseReq; // 保持原有不动（改动太大）
//    public static MainActivity lastMain;  // 保持原有不动（改动太大）
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_new);
//        PushAgent.getInstance(this).onAppStart();  // 启动页面统计
//        Global.initUmeng(this);
//        fragmentList.add(new HomeFragment());
//        fragmentList.add(new FindFragment());
//        fragmentList.add(new MyKeListFragment());
//        fragmentList.add(new MySpaceFragment());
//        mBottomTabView = (BottomTabView) findViewById(R.id.bottom_tab_view);
//        assert mBottomTabView != null;
//        mBottomTabView.setOnClick(this);
//        fragmentManager = getSupportFragmentManager();
//        showFragment();
//        gdssidTask = new GetGdssidTask(this, UrlDataStream.EIGHT, this);
//        gdssidTask.start();
//        if (lastMain != null) {
//            lastMain.finish();
//            lastMain = null;
//        }
//        lastMain = this;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.homepage:
//                currentIndex = 0;
//                mBottomTabView.changeState(true, false, false, false);
//                break;
//            case R.id.find_page:
//                currentIndex = 1;
//                mBottomTabView.changeState(false, true, false, false);
//                break;
//            case R.id.course_page:
//                currentIndex = 2;
//                mBottomTabView.changeState(false, false, true, false);
//                break;
//            case R.id.user_page:
//                currentIndex = 3;
//                mBottomTabView.changeState(false, false, false, true);
//                break;
//        }
//        showFragment();
//    }
//
//    private void showFragment() {
//        Fragment currentFragment = fragmentList.get(currentIndex);
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        if (!currentFragment.isAdded()) {
//            ft.add(R.id.fragment_container, currentFragment);
//        }
//        for (int x = 0; x < fragmentList.size(); x++) {
//            if (fragmentList.get(x) != currentFragment) {
//                ft.hide(fragmentList.get(x));
//            } else {
//                ft.show(fragmentList.get(x));
//            }
//        }
//        ft.commit();
//    }
//
//    protected void onSaveInstanceState(Bundle outState) {
////        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected final void onPause() {
//        if (AppConfig.HAS_UMENG) {
//            MobclickAgent.onPause(this);
//        }
//        super.onPause();
//    }
//
//    @Override
//    protected final void onResume() {
//        if (AppConfig.HAS_UMENG) {
//            MobclickAgent.onResume(this);
//        }
//        BackgroundManager.getInstance().setContext(this).deviceToken();
////        if (BackgroundManager.getInstance().shouldDo(BackgroundManager.REFRESH_RECOMM_COURSE)) {
////            recCourseReq = new RecCourseReq(this, CourseCenterControl.CODE_REC_COURSE, this);
////            recCourseReq.start();
////        }
//        super.onResume();
//    }
//
//    private long exitTime = 0;
//    @Override
//    public final void onBackPressed() {
//        if ((System.currentTimeMillis() - exitTime) > 2000) {
//            GdwxUtils.getToastManger(this).show(R.string.press_again_exit);
//            exitTime = System.currentTimeMillis();
//        } else {
//            finish();
//            lastMain = null;
//        }
//    }
//
//    @Override
//    public void onTaskBack(short which) {
//        switch (which) {
////            case CourseCenterControl.CODE_REC_COURSE:
////                if (recCourseReq == null) {
////                    return;
////                }
////                switch (recCourseReq.resultCode) {
////                    case AbsJsonReq.TODO_SUCCESS:
////                        List<CourseInfo> reclist = recCourseReq.getList();
////                        CourseCenterControl.mine().recCourse = reclist;
////                        break;
////                    default:
////                        break;
////                }
////                recCourseReq = null;
////                break;
//            case UrlDataStream.EIGHT:
//                if (gdssidTask != null) {
//                    String gdssid = gdssidTask.getGdssid();
//                    SharderManager.setSharedPreData(this, SharderManager.GDSSID, gdssid);
//                }
//                break;
//            default:
//                break;
//        }
//    }
//}
