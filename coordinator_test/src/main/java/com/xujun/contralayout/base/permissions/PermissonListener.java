package com.xujun.contralayout.base.permissions;

import java.util.List;

/**
 * @author xujun  on 2016/12/27.
 *  gdutxiaoxu@163.com
 */

public interface PermissonListener {

    void onGranted();

    void onDenied(List<String> permisons);

    void onParmanentDenied(List<String> parmanentDeniedPer);
}
