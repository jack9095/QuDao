package com.kuanquan.qudao.utils;

import com.example.fly.baselibrary.utils.base.ACache;
import com.kuanquan.qudao.app.QuApplication;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class BannerUtil {
    private static BannerUtil instance = new BannerUtil();

    private final String KEY = "key";
    private final ACache mACache;
    private final SimpleDateFormat mSimpleDateFormat;
    private HashMap<String, Info> mMap;

    private BannerUtil() {
        super();
        mACache = ACache.get(QuApplication.getAppContext(), "Banner_Statistics", false);
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" , Locale.CHINA);
        try {
            mMap = (HashMap<String, Info>) mACache.getAsObject(KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BannerUtil getInstance() {
        return instance;
    }

    public void saveShowCount(String advertiseTitle , String advertiseURL) {
        try {
            if (mMap == null) {
                mMap = new HashMap<>();
            }
            Info Info = mMap.get(advertiseURL);
            if (Info == null) {
                Info = new Info();
                Info.advertiseTitle = advertiseTitle;
                Info.advertiseURL = advertiseURL;
                Info.deviceType = 1;
                Info.list = new ArrayList<>();
            }
            TimeInfo timeInfo = new TimeInfo();
            timeInfo.createTime = mSimpleDateFormat.format(new Date());
            timeInfo.eventType = 1;
            Info.list.add(timeInfo);

            mMap.put(advertiseURL ,Info);

            mACache.put(KEY, mMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveClickCount(String advertiseTitle , String advertiseURL) {
        try {
            if (mMap == null) {
                mMap = new HashMap<>();
            }
            Info Info = mMap.get(advertiseURL);
            if (Info == null) {
                Info = new Info();
                Info.advertiseTitle = advertiseTitle;
                Info.advertiseURL = advertiseURL;
                Info.deviceType = 1;
                Info.list = new ArrayList<>();
            }
            TimeInfo timeInfo = new TimeInfo();
            timeInfo.createTime = mSimpleDateFormat.format(new Date());
            timeInfo.eventType = 0;
            Info.list.add(timeInfo);

            mMap.put(advertiseURL ,Info);

            mACache.put(KEY, mMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateBannerStatistics() {
        try {
//            HashMap<String, Info> hashMap = (HashMap<String, Info>) mACache.getAsObject(KEY);
//            if (CollectionsUtil.isMapEmpty(hashMap)) {
//                return;
//            }
//
//            TreeMap<String, String> treeMap = new TreeMap<>();
//            treeMap.put("Array", GsonUtil.parseListToJson(hashMap.values()));
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
//            RetrofitSender.sendPost(treeMap, jsonCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void clear() {
        mMap = null;
        mACache.remove(KEY);
    }

    public static class Info implements Serializable {
        public String deviceId;
        public String userId;
        public int deviceType; //
        public String advertiseTitle;
        public String advertiseURL;
        public ArrayList<TimeInfo> list;
    }

    public static class TimeInfo implements Serializable {
        public String createTime;
        public int eventType;//事件类型，0/点击 1/展示
    }


}
