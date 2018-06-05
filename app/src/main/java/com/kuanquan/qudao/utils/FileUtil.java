package com.kuanquan.qudao.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.MediaStore.MediaColumns;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

/**
 * SD卡是否存在,true为存在 ——isSDCard() <br>
 * <p/>
 * 创建文件或文件夹 ——makeFile(String path)<br>
 * <p/>
 * 初始化图片信息 ——initImagePath(String name) <br>
 * <p/>
 * SD卡路径 ——getPath4Sdcard() <br>
 * <p/>
 * 图片缓存目录，/WisdomTree/image/ ——getPath4CacheImg() <br>
 * <p/>
 * 附件图片所在目录，/WisdomTree/ImageFile/ ——getPath4Image() <br>
 * <p/>
 * 作业考试附件图片所在目录，/WisdomTree/ImageFile/homeworkImg/ ——getPath4HomeworkImg() <br>
 * <p/>
 * 检测视频下载路径，如果上次保存的路径是外置存储，本次外置存储卡被卸载，需要重新保存路径 ——checkPath4DownVideo(Context
 * context) <br>
 * <p/>
 * 检测视频下载路径，存储卡根目录 ——initPath4DownVideo(Context context) <br>
 * <p/>
 * 视频下载所在目录，?+/WisdomTree/Download/video/ ——getPath4DownVideo(Context context) <br>
 * <p/>
 * 文件下载所在目录，isSystemDown是否调用系统的下载，/WisdomTree/document/
 * ——getPath4DownDocument(boolean isSystemDown) <br>
 * <p/>
 * 删除文件 ——deleteFile(String path) <br>
 * <p/>
 * 删除所有本地存储的附件拍照图片 ——deleteImages() <br>
 * <p/>
 * 根据系统返回的UIR查询图片库里的图片资源 ——queryPicture(Context context, Intent data) <br>
 * <p/>
 * 图片按大小压缩 ——getBitmap(String path, int w, int h) <br>
 * <p/>
 * 获取压缩的图片文件路径 ——getCompressImagePath(String path, int w, int h) <br>
 * <p/>
 * 获取压缩的图片文件路径 ——getCompressImagePathSmall(String path, int w, int h) <br>
 * <p/>
 * 获取带白边圆图 ——getImageCircle(Context context, int imgId, int dis, String color) <br>
 * <p/>
 * 获取带白边圆图 ——getImageCircle(Context context, String path, int dis, String color,
 * int defId) <br>
 * <p/>
 * 获取带白边圆图 ——getImageCircle(Context context, Bitmap bm, int dis, String color) <br>
 * <p/>
 * 翻转图片 ——getBitmapScale(Context context, int id, int sx, int sy, int rotate) <br>
 * <p/>
 * 获取章节排列序号 ——getTextNumber(Context context, int type, int index) <br>
 * <p/>
 * 获取数字对应的中文名称 ——getText4Num(int i) <br>
 * <p/>
 * 获取日期-几月几日 星期几 ——getShowDate(Date d) <br>
 * <p/>
 * 格式化时间（分秒/时分秒）-- time为秒 ——formatTime(long time, boolean haveHour) <br>
 * <p/>
 * 获取网络类型，-1网络状态异常（没网络或者未连接网络），1WiFi，2手机网络 ——getNetWork(Context context) <br>
 * <p/>
 * 将时长转成秒 ——formatSize(String videoSize)<br>
 * <p/>
 * 获取手机的IP地址，用于视频日志初始化手机信息 ——getIP(Context context) <br>
 * <p/>
 * 获取IMEI号，IESI号，手机型号，手机品牌，手机号码 ——getPhoneInfo(Context context)<br>
 * <p/>
 * 获取当前时间点，用于视频日志 ——getLogTime() <br>
 * <p/>
 * 获取所有存储列表 ——getStorages(Context context) <br>
 * <p/>
 * 总大小 ——getSpaceSize(String path)<br>
 * <p/>
 * 剩余空间 ——getFreeSpace(String path) <br>
 * <p/>
 * 计算存储卡空间大小 ——getFormatSize(long size) <br>
 * <p/>
 * 检测是否是一件结束的课程，true为已结束 ——checkIsEndCourse(String recruitId)<br>
 *
 */
public class FileUtil {

    /**
     * SD卡路径
     */
    private static String pathSdcard;

    /**
     * 图片缓存目录
     */
    private static String pathCacheImg;

    /**
     * 视频下载所在目录
     */
    public static String pathDownVideo;

    /**
     * 文档下载所在目录，用于资料下载
     */
    private static String pathDownDocument;

    /**
     * 附件图片所在目录
     */
    private static String pathImage;

    /**
     * 作业考试附件图片所在目录
     */
    private static String pathHomeworkImg;

    /**
     * 请求异常日志路径
     */
    private static String pathLog;

    /**
     * 图片完整路径和名称
     */
    private static String imagePath;
    public static final String imagePath1 = "imagePath";

    /**
     * 图片名带后缀
     */
    private static String imageNameSuffix;
    public static final String imageNameSuffix1 = "imageNameSuffix";

    /**
     * 图片名不带后缀
     */
    private static String imageName;
    public static final String imageName1 = "imageName";

    /**
     * 图片后缀名
     */
    private static String imageSuffix = ".png";
    public static final String imageSuffix1 = "imageSuffix";

    /**
     * 获取章节序号参数
     */
    public static final int numText = -1, chapter = 0, lesson = chapter + 1,
            little = lesson + 1;
    /**
     * 数字对应的汉字名称，用于数字转换成中文名
     */
    private static String[] num = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};

    /**
     * SD卡是否存在,true为存在
     */
    public static boolean isSDCard() {
        try {
            // TODO SD卡是否存在,true为存在
            if (Environment.MEDIA_MOUNTED.equals(Environment
                    .getExternalStorageState())) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;

    }

    /**
     * 创建文件或文件夹
     */
    public static void makeFile(String path) {
        // TODO 创建文件或文件夹
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }


    /**
     * SD卡路径
     */
    public static String getPath4Sdcard() {
        if (TextUtils.isEmpty(pathSdcard)) {
            pathSdcard = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        }
        return pathSdcard;
    }

    /**
     * 删除文件
     */
    public static void deleteFile(String path) {
        // TODO 删除文件
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                delete(file);
            }
        }
    }

    /**
     * 删除文件或文件夹
     */
    private static void delete(File f) {
        if (f.isFile()) {
            f.delete();
        } else {
            File[] fs = f.listFiles();
            if (fs == null || fs.length == 0) {
                f.delete();
            } else {
                for (int i = 0; i < fs.length; i++) {
                    delete(fs[i]);
                }
            }
            f.delete();
        }
    }



    /**
     * 图片按大小压缩
     */
    public static Bitmap getBitmap(String path, int w, int h)
            throws FileNotFoundException {
        // TODO 图片按大小压缩
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        float ow = options.outWidth;
        float oh = options.outHeight;
        int inSampleSize = 1;
        float sw = ow / w;
        float sh = oh / h;
        if (sw > sh) {
            inSampleSize = (int) Math.ceil(sw);
        } else {
            inSampleSize = (int) Math.ceil(sh);
        }
        inSampleSize = inSampleSize % 2 == 0 ? inSampleSize : inSampleSize + 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeStream(new FileInputStream(new File(path)),
                new Rect(), options);
    }

    /**
     * 获取压缩的图片文件路径
     */
    public static String getCompressImagePath(String path, int w, int h) {
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int ow = options.outWidth;
            int oh = options.outHeight;
            if (ow <= w && oh <= h) {
                return "";
            }
            Bitmap bm = getBitmap(path, w, h);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(imagePath));
            bm.compress(CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
            return imagePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取压缩的图片文件路径
     */
    public static String getCompressImagePathSmall(String path, int w, int h) {
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int ow = options.outWidth;
            int oh = options.outHeight;
            if (ow <= w && oh <= h) {
                return "";
            }
            Bitmap bm = getBitmap(path, w, h);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(imagePath));
            // ---------压缩后的新图片
            int sample = 80, size = 50 * 1024;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(CompressFormat.PNG, 100, baos);
            for (int os = 100; baos.toByteArray().length > size && os > 0; ) {
                os = os - 5;
                baos.reset();
                bm.compress(CompressFormat.JPEG, os, baos);
                sample = os;
            }
            baos.close();

            bm.compress(CompressFormat.JPEG, sample, bos);
            bos.flush();
            bos.close();
            return imagePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取带白边圆图<br>
     * imgId图片资源ID<br>
     * dis为白边宽度<br>
     * color格式为(#RRGGBB，#AARRGGBB，'red'，'blue'，'green'，'black'，'white'，
     * 'gray'，'cyan'，'magenta'，'yellow'，'lightgray'，'darkgray')
     */
    public static Bitmap
    getImageCircle(Context context, int imgId, int dis,
                   String color) {
        // TODO 获取带白边圆图
        try {
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),
                    imgId);
            return getImageCircle(context, bm, dis, color);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取带白边圆图<br>
     * dis为白边宽度<br>
     * color格式为(#RRGGBB，#AARRGGBB，'red'，'blue'，'green'，'black'，'white'，
     * 'gray'，'cyan'，'magenta'，'yellow'，'lightgray'，'darkgray')
     */
    public static Bitmap getImageCircle(Context context, Bitmap bm, int dis,
                                        String color) throws Exception {
        // TODO 获取带白边圆图
        if (bm == null) {
            return null;
        }
        int radius = 0;
        dis = dis <= 0 ? 5 : dis;
        int w = bm.getWidth();
        int h = bm.getHeight();
        radius = h / 2;
        if (w > h) {
            bm = Bitmap.createBitmap(bm, (w - h) / 2, 0, h, h);
        } else if (w < h) {
            radius = w / 2;
            bm = Bitmap.createBitmap(bm, 0, (h - w) / 2, w, w);
        }
        Bitmap bw = Bitmap.createBitmap(radius * 2, radius * 2, bm.getConfig());
        Canvas c = new Canvas(bw);
        Paint p = new Paint();
        p.setAntiAlias(true);
        c.drawCircle(radius, radius, radius, p);
        // 设置图像的叠加模式
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.drawBitmap(bm, 0, 0, p);

        bm = Bitmap.createBitmap((radius + dis) * 2, (radius + dis) * 2,
                bm.getConfig());
        c = new Canvas(bm);
        p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.parseColor(color));
        c.drawCircle(radius + dis, radius + dis, radius + dis, p);
        c.drawBitmap(bw, dis, dis, p);

        if (bw != null && !bw.isRecycled()) {
            bw.recycle();
            bw = null;
        }
        return bm;
    }

    /**
     * 翻转图片 <br>
     * id 图片id<br>
     * sx,sy 缩放比例<br>
     * rotate 旋转角度
     */
    public static Bitmap getBitmapScale(Context context, int id, int sx,
                                        int sy, int rotate) {
        // TODO 翻转图片
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), id);
        Matrix mx = new Matrix();
        mx.setScale(sx, sy);
        Bitmap bw = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
                bm.getHeight(), mx, true);
        mx.setRotate(rotate);
        Bitmap rbw = Bitmap.createBitmap(bw, 0, 0, bw.getWidth(),
                bw.getHeight(), mx, true);
        if (bm != null) {
            bm.recycle();
            bm = null;
        }
        if (bw != null) {
            bw.recycle();
            bw = null;
        }
        if (mx != null) {
            mx = null;
        }
        return rbw;
    }


    /**
     * 获取数字对应的中文名称
     */
    public static String getText4Num(int i) {
        if (i < 0) {
            return "" + i;
        }
        if (i < 9) {
            return num[i];
        } else if (i == 9) {
            return "十";
        } else if (i < 19) {
            return "十" + num[i % 10];
        } else if (i < 99) {
            i = i + 1;
            return num[i / 10 - 1] + "十" + (i % 10 == 0 ? "" : num[i % 10 - 1]);
        } else if (i == 99) {
            return "一百";
        } else if (i < 999) {
            i = i + 1;
            if (i % 100 == 0) {
                return num[i / 100 - 1] + "百";
            }
            return num[i / 100 - 1] + "百"
                    + (i % 100 / 10 == 0 ? "零" : num[i % 100 / 10 - 1] + "十")
                    + (i % 100 % 10 == 0 ? "" : num[i % 100 % 10 - 1]);
        } else if (i == 999) {
            return "一千";
        }
        return "";
    }

    /**
     * 获取日期-几月几日 星期几
     */
    @SuppressWarnings("deprecation")
    public static String getShowDate(Date d) {
        // TODO 获取日期-几月几日 星期几
        String showDate = (d.getMonth() > 8 ? d.getMonth() + 1 : "0"
                + (d.getMonth() + 1))
                + "-" + (d.getDate() > 9 ? d.getDate() : "0" + d.getDate());
        if (d.getDay() == 0) {
            showDate += "\t星期天";
        } else if (d.getDay() == 1) {
            showDate += "\t星期一";
        } else if (d.getDay() == 2) {
            showDate += "\t星期二";
        } else if (d.getDay() == 3) {
            showDate += "\t星期三";
        } else if (d.getDay() == 4) {
            showDate += "\t星期四";
        } else if (d.getDay() == 5) {
            showDate += "\t星期五";
        } else if (d.getDay() == 6) {
            showDate += "\t星期六";
        }
        return showDate;
    }

    /**
     * 格式化时间（分秒/时分秒）-- time为秒
     */
    public static String formatTime(long time, boolean haveHour) {
        long mt = time > 0 ? time : 0; //总秒数
        long s = mt % 60; //剩余秒数 00:00:xx
        long h = mt / 3600; //总小时数  xx:00:00
        long m = (mt - s - h * 3600) / 60; // 00:xx:00
        if (haveHour) {
            return (h > 9 ? h : "0" + h) + ":" + (m > 9 ? m : "0" + m) + ":"
                    + (s > 9 ? s : "0" + s);
        } else {
            if (h > 0) {
                m = h * 60 + m;
            }
            return (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
        }
//        return (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
    }

    /**
     * 格式化时间（时分）-- time为分
     */
    public static String minTime(int time, boolean haveHour) {
        int mt = time > 0 ? time : 0; //总分钟数
        int h = mt / 60;
        int m = mt % 60;
        if (haveHour) {
            return (h > 9 ? h : "0" + h) + ":" + (m > 9 ? m : "0" + m) + ":00";
        } else {
            if (h > 0) {
                m = h * 60 + m;
            }
            return (m > 9 ? m : "0" + m) + "" + ":00";
        }
    }

    /**
     * 格式化时间（分秒/时分秒）-- time为秒
     */
    public static String formatTimeMore(long time) {
        long mt = time > 0 ? time : 0;
        long s = mt % 60;
        long m = (mt - s) / 60;
        return (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
    }

    /**
     * 格式化时间（xx小时xx分xx秒）-- time为秒
     */
    public static String formatTime(long time) {
        long mt = time > 0 ? time : 0;
        long s = mt % 60;
        long h = mt / 3600;
        long m = (mt - s - h * 3600) / 60;
        return (h > 9 ? h : "0" + h) + "小时" + (m > 9 ? m : "0" + m) + "分"
                + (s > 9 ? s : "0" + s) + "秒";
    }

    /**
     * 获取网络类型，-1网络状态异常（没网络或者未连接网络），1WiFi，2手机网络
     */
    public static int getNetWork(Context context) {
        // TODO 获取网络类型，-1没网络，1WiFi，2手机网络
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo ni = cm.getActiveNetworkInfo();

        if (ni == null) {// 没有网络
            return -1;
        } else if (ni.getType() == ConnectivityManager.TYPE_WIFI) {// wifi网络
            return 1;
        } else if (ni.getType() == ConnectivityManager.TYPE_MOBILE) {// 手机网络
            return 2;
        } else {
            return -1;
        }
    }

    /**
     * 将时长转成秒
     */
    public static int formatSize(String videoSize) {
        // TODO 将时长转成秒
        try {
            if (videoSize.contains(":")) {
                String[] vs = videoSize.split(":");
                int size = 0;
                if (vs.length == 2) {
                    size += Integer.parseInt(vs[0]) * 60;
                    size += Integer.parseInt(vs[1]);
                } else if (vs.length == 3) {
                    size = Integer.parseInt(vs[0]) * 3600;
                    size += Integer.parseInt(vs[1]) * 60;
                    size += Integer.parseInt(vs[2]);
                }
                return size;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    /**
     * 获取手机的IP地址，用于视频日志初始化手机信息
     */
    public static String getIP(Context context) {
        try {
            int state = getNetWork(context);
            if (state == 1) {
                // 取得WifiManager对象
                WifiManager wm = (WifiManager) context
                        .getSystemService(Context.WIFI_SERVICE);
                // 取得WifiInfo对象
                @SuppressLint("MissingPermission") WifiInfo wi = wm.getConnectionInfo();
                return initIP(wi.getIpAddress());
            } else if (state == 2) {
                for (Enumeration<NetworkInterface> en = NetworkInterface
                        .getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf
                            .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress().toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0.0.0";
        }
        return "0.0.0.0";
    }

    /**
     * 获取IP地址
     */
    private static String initIP(int ip) {
        return (ip & 0xFF) + "." + (ip >> 8 & 0xFF) + "." + (ip >> 16 & 0xFF)
                + "." + (ip >> 24 & 0xFF);
    }

    /**
     * 获取IMEI号，IESI号，手机型号，手机品牌，手机号码
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String str2 = "";
        String[] cpu = {"", ""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpu[0] = cpu[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpu[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String info = "IMEI=" + tm.getDeviceId();
        info += "/IMSI=" + tm.getSubscriberId();
        info += "/Number=" + tm.getLine1Number();// 手机号码
        info += "/CPU_MODEL=" + cpu[0];// cpu型号
        info += "/CPU_HZ=" + cpu[1];// cpu功率
        info += "/MODEL=" + android.os.Build.MODEL;// 手机型号
        info += "/BRAND=" + android.os.Build.BRAND;// 手机品牌
        info += "/CODENAME=" + android.os.Build.VERSION.CODENAME;// 当前开发代号
        info += "/INCREMENTAL=" + android.os.Build.VERSION.INCREMENTAL;// 源码控制版本号
        info += "/RELEASE=" + android.os.Build.VERSION.RELEASE;// 版本字符串
        info += "/SDK=" + android.os.Build.VERSION.SDK_INT;// 版本号

        return info;
    }

    /**
     * 获取当前时间点，用于视频日志
     */
    public static String getLogTime() {
        Calendar c = Calendar.getInstance();
        String h = c.get(Calendar.HOUR_OF_DAY) + "";
        int mi = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);
        int m = c.get(Calendar.MILLISECOND);
        return h + mi + s + m;
    }

    /**
     * 获取挂载路径
     */
    private static String getStoragePath(Object obj) throws Exception {
        Method getPath = obj.getClass().getMethod("getPath", new Class[0]);
        getPath.setAccessible(true);
        return (String) getPath.invoke(obj, new Object[0]);
    }

    /**
     * 检查是否挂载
     */
    private static boolean isMounted(StorageManager storageManager, String path)
            throws Exception {
        Method getVolumeState = storageManager.getClass().getMethod(
                "getVolumeState", String.class);
        getVolumeState.setAccessible(true);
        String state = (String) getVolumeState.invoke(storageManager, path);
        return Environment.MEDIA_MOUNTED.equals(state);// "mounted"
    }

    /**
     * 挂载点是否可移除
     */
    private static boolean isRemovable(Object obj, String path)
            throws Exception {
        File file = new File(path);
        if ((file.exists()) && (file.isDirectory()) && (file.canWrite())) {
            Method isRemovable = obj.getClass().getMethod("isRemovable",
                    new Class[0]);
            isRemovable.setAccessible(true);
            return ((Boolean) isRemovable.invoke(obj, new Object[0]));
        }
        return false;
    }

    /**
     * 存储空间总大小
     */
    @SuppressWarnings("deprecation")
    public static long getSpaceSize(String path) throws Exception {
        StatFs statFs = null;
        try {// ---YQliang 2-18
            statFs = new StatFs(path);
        } catch (Exception e) {// 没有找到文件或者目录不存在
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }
        long blockSize = statFs.getBlockSize();// 每个block 占字节数
        long totalBlocks = statFs.getBlockCount();// block总数
        return blockSize * totalBlocks;
    }

    /**
     * 剩余存储空间
     */
    @SuppressWarnings("deprecation")
    public static long getFreeSpace(String path) throws Exception {
        StatFs statFs = null;
        try {// ---YQliang 2-18
            statFs = new StatFs(path);
        } catch (Exception e) {// 没有找到文件或者目录不存在
            // TODO: handle exception
            e.printStackTrace();
            return 0;
        }
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

    /**
     * 计算存储卡空间大小<br>
     * long size<br>
     * int floatSize 小数点后保留位数<br>
     * String endUnit
     */
    public static String getFormatSize(long size, int floatSize, String endUnit) {
        float f = 1024f * 1024 * 1024;// 1G
        if (size < 1024) {
            return size + ("B".equals(endUnit) ? endUnit : "B");
        } else if (size < 1024 * 1024) {
            String result = String.format("%." + floatSize + "f", size / 1024f);
//            if (result.endsWith(".00")) {
//                return result.replace(".00", "") + "K" + endUnit;
//            } else if (result.endsWith("0")) {// xx.x0 末尾一个0
//                return result.substring(0, result.length() - 1) + "K" + endUnit;
//            }
            return result + "K" + endUnit;
        } else if (size < f) {
            String result = String.format("%." + floatSize + "f", size
                    / (1024f * 1024));
//            if (result.endsWith(".00")) {
//                return result.replace(".00", "") + "M" + endUnit;
//            } else if (result.endsWith("0")) {// xx.x0 末尾一个0
//                return result.substring(0, result.length() - 1) + "M" + endUnit;
//            }
            return result + "M" + endUnit;
        } else {
            String result = String.format("%." + floatSize + "f", size / f);
//            if (result.endsWith(".00")) {
//                return result.replace(".00", "") + "G" + endUnit;
//            } else if (result.endsWith("0")) {// xx.x0 末尾一个0
//                return result.substring(0, result.length() - 1) + "G" + endUnit;
//            }
            return result + "G" + endUnit;
        }
    }

    /**
     * MD5加密 输入一个String(需要加密的文本)，得到一个加密输出String（加密后的文本）
     */
    public static String getMD5(String s) throws Exception {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] btInput = s.getBytes();
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    /**
     * 获取系统语言
     */
    public static int getSysLanguage(Context context) {
        String sysLanguage = context.getResources().getConfiguration().locale
                .getLanguage();
        if (!TextUtils.isEmpty(sysLanguage)) {
            if ("en".equals(sysLanguage)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 图片模糊 <br>
     * radius 模糊度
     */

    public static Bitmap doBlur(Bitmap sentBitmap, int radius) {
        Bitmap bitmap;
        if (sentBitmap == null) {
            return null;
        }
        bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (bitmap == null) {
            return null;
        }
        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    public static String showWatchNum(int num) {
        try {
            DecimalFormat df = new DecimalFormat("#,###");
            return df.format(num);
        } catch (Exception e) {
            e.printStackTrace();
            return num + "";
        }
    }

    public static String showStringCount(int num) {
        try {
            if (num >= 10000) {
                DecimalFormat df = new DecimalFormat("#.0");
                return df.format(num / 10000.0) + "w";
            } else {
                return num + "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return num + "";
        }
    }

    public static boolean urlIsFile(String url) {
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(url.trim()) && url.trim().endsWith(".apk")) {
            return true;
        }
        return false;
    }

    public static boolean urlNoStartWithHttp(String url) {
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(url.trim()) && !url.trim().startsWith("http")) {
            return true;
        }
        return false;
    }
}

