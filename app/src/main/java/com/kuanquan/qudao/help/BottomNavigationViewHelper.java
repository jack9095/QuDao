package com.kuanquan.qudao.help;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import com.orhanobut.logger.Logger;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/6/4.
 *
 */
public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        //获取子View BottomNavigationMenuView的对象
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            //设置私有成员变量mShiftingMode可以修改
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                ////去除shift效果
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Logger.e("BNVHelper", "没有mShiftingMode这个成员变量", e);
        } catch (IllegalAccessException e) {
            Logger.e("BNVHelper", "无法修改mShiftingMode的值", e);
        }
    }
}
