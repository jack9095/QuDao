package com.kuanquan.qudao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * description:
 * author: 崔国
 * date: 2017/11/16.
 */

public class TimeUtilsA {
    /**
     * 日期工具类(未特别说明的均为系统默认时区下的时间)
     * */

    /**
     * 1s==1000ms
     */
    private final static int TIME_MILLISECONDS = 1000;
    /**
     * 时间中的分、秒最大值均为60
     */
    private final static int TIME_NUMBERS = 60;
    /**
     * 时间中的小时最大值
     */
    private final static int TIME_HOURS = 24;
    /**
     * 时间中的周最大值
     */
    private final static int TIME_WEEKS = 24;
    /**
     * 格式化日期的标准字符串
     */
    private final static String FORMAT = "yyyy-MM-dd HH:mm:ss";


    private final static String FORMAT_1 = "MM-dd HH:mm";
    private final static String FORMAT_2 = "MM月dd日 HH:mm";
    private final static String FORMAT_3 = "yyyy年MM月dd日";


    /**
     * 专栏专用方法，输入时间戳
     * 时间显示规则：一天内的：X小时前
     * 大于一天的显示：显示月日，如图（不考虑年份）
     *
     * @return 时间
     */
    public static String columnString(long times) {
        SimpleDateFormat dateFormat;
        if (!isYesteryear(times)) {
            dateFormat = new SimpleDateFormat(FORMAT_2);
        } else {
            dateFormat = new SimpleDateFormat(FORMAT_3);
        }
        return dateFormat.format(times);
    }

    /**
     * 专栏专用方法，输入时间戳
     * 时间显示规则：一天内的：X小时前
     * 大于一天的显示：显示月日，如图（不考虑年份）
     *
     * @return 时间
     */
    public static String columnTime(long times) {
        if (System.currentTimeMillis() - times < (TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在1小时内，则显示“分钟”
            return "1小时前";
        }
        if (System.currentTimeMillis() - times < (TIME_WEEKS * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在24小时内，则显示“小时”
            return (System.currentTimeMillis() - times) / (TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS) + "小时前";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_1);
        return dateFormat.format(times);
    }

    /**
     * 专栏专用方法，输入时间戳
     * 时间显示规则：一天内的：X小时前
     * 大于一天的显示：显示月日，如图（不考虑年份）
     *
     * @return 时间
     */
    public static String replyTime(long times) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_1);
        return dateFormat.format(times);
    }

    /**
     * 学生汇列表专用方法，输入时间戳
     * 时间显示规则：一天内的：X小时前
     * 大于一天的显示：显示月日，如图（不考虑年份）
     *
     * @return 时间
     */
    public static String studentListTime(long times) {
        if (System.currentTimeMillis() - times < (TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在1分钟内，则显示“刚刚”
            return "刚刚";
        }
        if (System.currentTimeMillis() - times < (TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在1小时内，则显示“分钟”
            return (System.currentTimeMillis() - times) / (TIME_NUMBERS * TIME_MILLISECONDS) + "分钟前";
        }
        if (System.currentTimeMillis() - times < (TIME_WEEKS * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在24小时内，则显示“小时”
            return (System.currentTimeMillis() - times) / (TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS) + "小时前";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_1);
        return dateFormat.format(times);
    }

    //判断是否跨年
    public static boolean isYesteryear(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int lastYear = c.get(Calendar.YEAR) - 1;
        c.setTimeInMillis(timestamp);
        int inputYear = +c.get(Calendar.YEAR);
        return lastYear == inputYear;
    }

    public static String columnSubTime(long times) {
        if (System.currentTimeMillis() - times < ( TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在24小时内，则显示“小时”
            return  "1小时前更新";
        }else if (System.currentTimeMillis() - times < (24 * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在24小时内，则显示“小时”
            return (System.currentTimeMillis() - times) / (TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS) +  "小时前更新";
        } else if (System.currentTimeMillis() - times < (7 * TIME_HOURS * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {
            return (System.currentTimeMillis() - times) / (TIME_HOURS * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS) + "天前更新";
        } else {
            return "即将更新";
        }
    }
    public static String chapterSubTime(long times) {
        if (System.currentTimeMillis() - times < ( TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在24小时内，则显示“小时”
            return  "1小时前更新";
        }else if (System.currentTimeMillis() - times < (24 * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {//若发布时间在24小时内，则显示“小时”
            return (System.currentTimeMillis() - times) / (TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS) + "小时前更新";
        } else if (System.currentTimeMillis() - times < (7 * TIME_HOURS * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS)) {
            return (System.currentTimeMillis() - times) / (TIME_HOURS * TIME_NUMBERS * TIME_NUMBERS * TIME_MILLISECONDS) + "天前更新";
        } else {
            return "一周前更新";
        }
    }
}
