package com.kuanquan.qudao.utils;

import android.text.TextUtils;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */

public class CollectionsUtil {
    public static boolean isListEmpty(List list) {
        if (list == null) {
            return true;
        } else {
            return list.isEmpty();
        }
    }

    public static boolean isMapEmpty(Map map) {
        if (map == null) {
            return true;
        } else {
            return map.isEmpty();
        }
    }

    public static boolean isSetEmpty(Set set) {
        if (set == null) {
            return true;
        } else {
            return set.isEmpty();
        }
    }


    public static void setTextView(TextView mTextView, String str) {
        if (mTextView != null) {
            if (!TextUtils.isEmpty(str)) {
                mTextView.setText(str);
            }else{
                mTextView.setText("");
            }
        }
    }

    /**
     * 时间格式转换
     *
     * @param ltime  - 相对于1970年1月1日零时的毫秒数  MM-dd HH:mm
     * @param format - 格式字符串
     * @return String - 带格式的时间字符串
     */
    public static String getTimeWithFormat(long ltime, String format) {
//        Date dateTemp = new Date(ltime * 1000);
        Date dateTemp = new Date(ltime);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(dateTemp);
    }
}
