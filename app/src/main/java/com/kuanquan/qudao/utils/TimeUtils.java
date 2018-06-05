package com.kuanquan.qudao.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * 
 * @author YQliang
 * 
 * @data 2016-5-10 日期工具类
 */
public class TimeUtils {
	private SimpleDateFormat sf;
	private static TimeUtils timeUtil;
	private long timeMillis;
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd",
			Locale.CHINA);
	private Calendar c = Calendar.getInstance();

	public static TimeUtils getInstance() {
		if (timeUtil == null) {
			timeUtil = new TimeUtils();
		}
		return timeUtil;
	}

	/**
	 * 判断当前日期是星期几， 格式2015-11-05 09:10:00
	 */
	@SuppressLint("SimpleDateFormat")
	public String getWeek(String pTime) {
		if (TextUtils.isEmpty(pTime)) {
			return "";
		}
		String Week = "";
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			Week += "星期天";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Week += "星期一";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Week += "星期二";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Week += "星期三";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Week += "星期四";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Week += "星期五";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Week += "星期六";
		}
		return Week;
	}

	/**
	 * 
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return x月x日
	 */
	@SuppressLint("SimpleDateFormat")
	public String getFormatTime(String pTime) {
		if (TextUtils.isEmpty(pTime)) {
			return "";
		}
		timeMillis = strToMillio(pTime);
		if (sf == null) {
			sf = new SimpleDateFormat("MM/dd");
		}
		StringBuffer sb = new StringBuffer();
		if (!TextUtils.isEmpty(pTime)) {
			sb.append(sf.format(new Date(Long.valueOf(timeMillis))));
		}
		return sb.toString().replaceFirst("/", "月") + "日";
	}

	public String getMonthAndDay(String pTime) {
		String mday = "";
		try {
			mday = pTime.substring(5, 10).toString().replaceFirst("-", "月")
					+ "日";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mday;
	}

	/** 时间字符串yyyy-MM-dd HH:mm:ss转为毫秒 */
	public long strToMillio(String pTime) {
		if (TextUtils.isEmpty(pTime)) {
			return 0;
		}
		try {
			c.setTime(format.parse(pTime));
			timeMillis = c.getTimeInMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeMillis;

	}

	/** 该时间yyyy-MM-dd HH:mm:ss是否是今天 */
	public boolean isToday(String pTime) {
		if (TextUtils.isEmpty(pTime)) {
			return false;
		}
		Date curDate = new Date(strToMillio(getFormatedDateString(8)));// 获取当前时间
		String today = formatDay.format(curDate);
		if (pTime.contains(today)) {
			return true;
		}
		return false;
	}

	/** 判断两个时间yyyy-MM-dd HH:mm:ss是否是同一天 */
	public boolean isSameday(String time1, String time2) {
		if (TextUtils.isEmpty(time1) || TextUtils.isEmpty(time2)) {
			return false;
		}
		if (TextUtils.equals(getFormatTime(time1), getFormatTime(time2))) {
			return true;
		}
		return false;
	}

	/**
	 * 当前时间是否到了指定时间<br/>
	 * advanceTime:提前多少时间，没有提前时间的传"",单位秒
	 */
	public boolean timeToThisTime(String pTime, String advanceTime) {
		// TODO Auto-generated method stub
		int advan = 0;
		try {
			advan = Integer.parseInt(advanceTime);
		} catch (Exception e) {
			// TODO: handle exception
			advan = 0;
		}
		if (TextUtils.isEmpty(pTime)) {
			return false;
		}
		long mTime = strToMillio(pTime);
		if (strToMillio(getFormatedDateString(8)) >= mTime - advan * 1000) {
			return true;
		}
		return false;
	}

	/**
	 * 当前时间是否到了预设结束时间<br/>
	 */
	public boolean timeToThisTime(String Time) {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(Time)) {
			return false;
		}
		long mTime = strToMillio(Time);
		if (strToMillio(getFormatedDateString(8)) >= mTime) {
			return true;
		}
		return false;
	}

	private String getFormatedDateString(float timeZoneOffset) {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}

		int newTime = (int) (timeZoneOffset * 60 * 60 * 1000);
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(newTime);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(newTime, ids[0]);
		}

		format.setTimeZone(timeZone);
		return format.format(new Date());
	}
}