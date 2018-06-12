package com.kuanquan.qudao.utils;

import android.text.TextUtils;

import com.example.fly.baselibrary.utils.base.ACache;
import com.kuanquan.qudao.app.QuApplication;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TreeMap;

/**
 * Created by qty on 2018/3/26.
 */

public class BannerAdvStatisticsUtil {
    private static BannerAdvStatisticsUtil instance = new BannerAdvStatisticsUtil();

    private final String ADV_STATISTICS_KEY = "adv_statistics_key";
    private final ACache mACache;
    private final SimpleDateFormat mSimpleDateFormat;
    private HashMap<String, AdvInfo> mMap;

    private BannerAdvStatisticsUtil() {
        super();
        mACache = ACache.get(QuApplication.getAppContext(), "Banner_Statistics", false);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" , Locale.CHINA);
        try {
            mMap = (HashMap<String, AdvInfo>) mACache.getAsObject(ADV_STATISTICS_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BannerAdvStatisticsUtil getInstance() {
        return instance;
    }

    public void saveAdvShowCount(String advertiseTitle , String advertiseURL) {
        try {
            if (mMap == null) {
                mMap = new HashMap<>();
            }
            AdvInfo advInfo = mMap.get(advertiseURL);
            if (advInfo == null) {
                advInfo = new AdvInfo();
                advInfo.advertiseTitle = advertiseTitle;
                advInfo.advertiseURL = advertiseURL;
                advInfo.deviceType = 1;
                advInfo.list = new ArrayList<>();
            }
            AdvTimeInfo timeInfo = new AdvTimeInfo();
            timeInfo.createTime = mSimpleDateFormat.format(new Date());
            timeInfo.eventType = 1;
            advInfo.list.add(timeInfo);

            mMap.put(advertiseURL ,advInfo);

            mACache.put(ADV_STATISTICS_KEY, mMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAdvClickCount(String advertiseTitle , String advertiseURL) {
        try {
            if (mMap == null) {
                mMap = new HashMap<>();
            }
            AdvInfo advInfo = mMap.get(advertiseURL);
            if (advInfo == null) {
                advInfo = new AdvInfo();
                advInfo.advertiseTitle = advertiseTitle;
                advInfo.advertiseURL = advertiseURL;
                advInfo.deviceType = 1;
                advInfo.list = new ArrayList<>();
            }
            AdvTimeInfo timeInfo = new AdvTimeInfo();
            timeInfo.createTime = mSimpleDateFormat.format(new Date());
            timeInfo.eventType = 0;
            advInfo.list.add(timeInfo);

            mMap.put(advertiseURL ,advInfo);

            mACache.put(ADV_STATISTICS_KEY, mMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateBannerAdvStatistics() {
        try {
//            HashMap<String, AdvInfo> hashMap = (HashMap<String, AdvInfo>) mACache.getAsObject(ADV_STATISTICS_KEY);
//            if (CollectionsUtil.isMapEmpty(hashMap)) {
//                return;
//            }
//
//            TreeMap<String, String> treeMap = new TreeMap<>();
//            treeMap.put("advertiseArray", GsonUtil.parseListToJson(hashMap.values()));
//
//            JsonCallback<DefaultJson> jsonCallback = new JsonCallback<DefaultJson>(DefaultJson.class) {
//                @Override
//                public void onFailure(String s) {
//
//                }
//                @Override
//                public void onSuccess(String s, DefaultJson defaultJson) {
//                    if (defaultJson.status != null && TextUtils.equals(defaultJson.status, "200")) {
//                        clear();
//                    } else {
//                        onFailure(s);
//                    }
//                }
//            };
//            RetrofitSender.sendPost(IP.APP2C + "/", "app_2c/appserver/advertise/saveAdvertiseRecord", false, treeMap, jsonCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void clear() {
        mMap = null;
        mACache.remove(ADV_STATISTICS_KEY);
    }

    public static class AdvInfo implements Serializable {
        public String deviceId;
        public String userId;
        public int deviceType; //设备类型，0/IOS 1/Android
        public String advertiseTitle;
        public String advertiseURL;
        public ArrayList<AdvTimeInfo> list;
    }

    public static class AdvTimeInfo implements Serializable {
        public String createTime;
        public int eventType;//事件类型，0/点击 1/展示
    }


}
