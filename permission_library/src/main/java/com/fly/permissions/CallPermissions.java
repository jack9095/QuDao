package com.fly.permissions;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 提供给用户使用的权限类
 */
public final class CallPermissions {

    private final static SparseArray<OnPermissionListener> sContainer = new SparseArray<>();
    private static long sRequestTime;

    private Activity mActivity;
    private String[] mPermissions;

    /**
     * 不能被外部实例化
     */
    private CallPermissions(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 设置请求的对象
     */
    public static CallPermissions with(Activity activity) {
        return new CallPermissions(activity);
    }

    /**
     * 设置权限组
     */
    public CallPermissions permission(String... permissions){
        this.mPermissions = permissions;
        return this;
    }

    /**
     * 设置权限组
     */
    public CallPermissions permission(String[]... permissions){
        List<String> permissionList = new ArrayList<>();
        for (String[] group : permissions) {
            permissionList.addAll(Arrays.asList(group));
        }
        this.mPermissions = permissionList.toArray(new String[permissionList.size() - 1]);
        return this;
    }

    /**
     * 设置权限组
     */
    public CallPermissions permission(List<String> permissions){
        this.mPermissions = permissions.toArray(new String[permissions.size() - 1]);
        return this;
    }

    /**
     * 请求权限
     */
    public void request(OnPermissionListener call) {
        //如果没有指定请求的权限，就使用清单注册的权限进行请求
        if (mPermissions == null || mPermissions.length == 0) this.mPermissions = PermissionUtils.getManifestPermissions(mActivity);
        if (mPermissions == null || mPermissions.length == 0) throw new IllegalArgumentException("The requested permission cannot be empty");
        //使用isFinishing方法Activity在熄屏状态下会导致崩溃
        //if (mActivity == null || mActivity.isFinishing()) throw new IllegalArgumentException("Illegal Activity was passed in");
        if (mActivity == null) throw new IllegalArgumentException("The activity is empty");
        if (call == null) throw new IllegalArgumentException("The permission request callback interface must be implemented");

        int requestCode;

        //请求码随机生成，避免随机产生之前的请求码，必须进行循环判断
        do {
            //requestCode = new Random().nextInt(65535);//Studio编译的APK请求码必须小于65536
            requestCode = new Random().nextInt(255);//Eclipse编译的APK请求码必须小于256
        } while (sContainer.get(requestCode) != null);

        ArrayList<String> failPermissions = PermissionUtils.getFailPermissions(mActivity, mPermissions);

        if (failPermissions == null) {
            //证明权限已经全部授予过
            call.onPermissionResult(Arrays.asList(mPermissions), true);
        } else {
            //将当前的请求码和对象添加到集合中
            sContainer.put(requestCode, call);
            //记录本次申请时间
            sRequestTime = System.currentTimeMillis();
            //检测权限有没有在清单文件中注册
            PermissionUtils.checkPermissions(mActivity, mPermissions);
            //申请没有授予过的权限
            PermissionFragment.newInstant(failPermissions, requestCode).requestPermission(mActivity);
        }
    }

    /**
     * 检查某些权限是否全部授予了
     *
     * @param context           上下文对象
     * @param permissions       需要请求的权限组
     */
    public static boolean isPermission(Context context, String... permissions) {
        return PermissionUtils.getFailPermissions(context, permissions) == null;
    }

    /**
     * 检查某些权限是否全部授予了
     *
     * @param context           上下文对象
     * @param permissions       需要请求的权限组
     */
    public static boolean isPermission(Context context, String[]... permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : permissions) {
            permissionList.addAll(Arrays.asList(group));
        }
        return PermissionUtils.getFailPermissions(context, permissionList.toArray(new String[permissionList.size() - 1])) == null;
    }

    /**
     * 跳转到应用权限设置页面
     *
     * @param context           上下文对象
     */
    public static void gotoPermissionSettings(Context context) {
        PermissionSettingPage.start(context, false);
    }

    /**
     * 跳转到应用权限设置页面
     *
     * @param context           上下文对象
     * @param newTask           是否使用新的任务栈启动
     */
    public static void gotoPermissionSettings(Context context, boolean newTask) {
        PermissionSettingPage.start(context, newTask);
    }

    /**
     * 在Activity或Fragment中的同名同参方法调用此方法
     */
    static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        OnPermissionListener call = sContainer.get(requestCode);

        //根据请求码取出的对象为空，就直接返回不处理
        if (call == null) return;

        //获取授予权限
        List<String> succeedPermissions = PermissionUtils.getSucceedPermissions(permissions, grantResults);
        //如果请求成功的权限集合大小和请求的数组一样大时证明权限已经全部授予
        if (succeedPermissions.size() == permissions.length) {
            //代表申请的所有的权限都授予了
            call.onPermissionResult(succeedPermissions, true);
        }else {
            //获取拒绝权限
            List<String> failPermissions = PermissionUtils.getFailPermissions(permissions, grantResults);
            //代表申请的权限中有不同意授予的，如果拒绝的时间过快证明是系统自动拒绝
            call.errorPermission(failPermissions, System.currentTimeMillis() - sRequestTime < 200);
            //证明还有一部分权限被成功授予，回调成功接口
            if (!succeedPermissions.isEmpty()) {
                call.onPermissionResult(succeedPermissions, false);
            }
        }

        //权限回调结束后要删除集合中的对象，避免重复请求
        sContainer.remove(requestCode);
    }
}
