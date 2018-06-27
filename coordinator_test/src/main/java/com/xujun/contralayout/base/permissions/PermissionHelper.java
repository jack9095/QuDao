package com.xujun.contralayout.base.permissions;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.List;

import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;

/**
 * @author meitu.xujun  on 2017/4/7 09:53
 * @version 0.1
 */

public class PermissionHelper {

    public static boolean isM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 处理是否有权限被永久拒绝
     *
     * @param activity
     * @param deniedPermissions    被拒绝的权限（包括永久被拒绝的权限和只被拒绝一次的权限），处理完之后变成只被拒绝一次的权限
     * @param permanentPermissions 永久被拒绝的权限（勾选了不再提醒）
     * @return 如果有权限被永久拒绝，返回 true，否则返回 false。
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean handlePermissionPermanentlyDenied(@NonNull Activity activity,
                                                            @NonNull List<String>
                                                                    deniedPermissions, List<String>
                                                                    permanentPermissions) {
        for (String deniedPermission : deniedPermissions) {
            if (permissionPermanentlyDenied(activity, deniedPermission)) {
                permanentPermissions.add(deniedPermission);
                deniedPermissions.remove(deniedPermission);
            }

        }
        if (!permanentPermissions.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断权限是否被永久拒绝
     *
     * @param activity
     * @param deniedPermission
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean permissionPermanentlyDenied(@NonNull Activity activity,
                                                      @NonNull String deniedPermission) {
        return !shouldShowRequestPermissionRationale(activity, deniedPermission);
    }
}
