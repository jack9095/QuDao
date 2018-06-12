package com.kuanquan.qudao.utils;//package com.example.fly.myapplication.util;
//
//import com.example.able.myapplication.app.C;
//import com.example.able.myapplication.utils.security.MD5;
//import com.rayhahah.rbase.utils.useful.SPManager;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// * @author Rayhahah
// * @blog http://rayhahah.com
// * @time 2017/9/25
// * @tips 这个类是Object的子类
// * @fuction
// */
//public class HuPuHelper {
//
//    public static HashMap<String, String> getRequsetMap() {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("client", C.DEVICE_ID);
//        map.put("night", "0");
//        try {
//            map.put("token", URLEncoder.encode(SPManager.get().getStringValue(C.SP.HUPU_TOKEN), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    /**
//     * 虎扑url sign生成
//     *
//     * @param map url参数，按字典序拼接key和value
//     * @return sign值
//     */
//    public static String getRequestSign(Map<String, String> map) {
//        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, String>>() { // 字典序
//            @Override
//            public int compare(Map.Entry<String, String> lhs, Map.Entry<String, String> rhs) {
//                return lhs.getKey().compareTo(rhs.getKey());
//            }
//        });
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < list.size(); i = i + 1) {
//            if (builder.length() > 0) {
//                builder.append("&");
//            }
//            Map.Entry<String, String> map1 = list.get(i);
//            builder.append(map1.getKey()).append("=").append(map1.getValue());
//        }
//        builder.append("HUPU_SALT_AKJfoiwer394Jeiow4u309");
//        return MD5.getMD5(builder.toString());
//    }
//
//
//}
