package com.example.fly.baselibrary.utils.base;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.fly.baselibrary.bean.ResponseBean;
import com.example.fly.baselibrary.utils.useful.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by fei.wang on 2018/3/20.
 * 一个写入文件的日志记录类
 * 用法：
 * 1.在Application 的 onCreate方法中调用 BaseFileUtil.init(this);
 * 2.写入：BaseFileUtil.write("你们好啊");
 * 3.读取：BaseFileUtil.getToDate("")
 */
public class BaseFileUtil {

    /**
     * 上下文对象
     */
    private static Context mContext;
    /**
     * 以调用者的类名作为TAG
     */
    protected static String tag;
    /**
     * 默认TAG
     */
    protected static final String MY_TAG = "BaseFileUtil";
    /**
     * FileLogUtils类的实例
     */
    protected static FileLogCatUtils instance;
    /**
     * 用于保存日志的文件
     */
    protected static File logFile;
    /**
     * 日志中的时间显示格式
     */
    @SuppressLint("SimpleDateFormat")
    protected static SimpleDateFormat logSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 初始化日志库
     *
     * @param context
     */
    public static void init(Context context) {
        if (null == mContext || null == instance) {
            mContext = context;
            instance = new FileLogCatUtils();
        } else {
            LogUtil.e(MY_TAG, "FileLogUtils has been init ...");
        }
    }

    /**
     * 创建根目录文件
     *
     * @return 根目录文件
     */
    public static File getMkDir() {
        //使用内部存储器   \data\data\包名\files\Log\logs.txt
//        File file = new File(mContext.getFilesDir().getPath() + "/Log/");
        File file = new File(mContext.getCacheDir(), "xsd_zd");
        // 若目录不存在则创建目录
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /**
     * 根据文件名称获取对应的文件  进行存储和读取
     *
     * @return 文件
     */
    public static File getFile(String fileName) {
        File file = new File(getMkDir().getPath() + "/" + System.currentTimeMillis() + ".txt");
//        File file = new File(getMkDir().getPath() + "/" + fileName + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                LogUtil.e(MY_TAG, "创建文件失败异常 = " + e);
            }
        }
        logFile = file;
        return file;
    }

    /**
     * 清除根目录下面所有文件
     */
    public static void clearAll() {
        File[] files = getMkDir().listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    /**
     * 根据文件名清除文件
     *
     * @param fileName 文件名称
     * @return true 删除成功
     */
    public static boolean clear(String fileName) {
        File logFile = new File(getMkDir().getPath() + "/" + fileName + ".txt");
        boolean deleteB;
        if (logFile == null || !logFile.exists()) {
            deleteB = false;
        }else{
            deleteB = logFile.delete();
        }
        return deleteB;
    }

    /**
     * 写入日志文件的数据
     *
     * @param str 需要写入的数据
     */
    protected void write(Object str) {
        // 判断是否初始化或者初始化是否成功
        if (null == mContext || null == instance || null == logFile || !logFile.exists()) {
            LogUtil.e(MY_TAG, "创建文件失败");
            return;
        }
        LogUtil.e(MY_TAG, "日志路径：" + logFile.getPath() + "   \n当前日志文件大小 = " + getFileSize(logFile));
//        String logStr = getFunctionInfo() + " ----> " + str.toString();
        String logStr = str.toString();
        LogUtil.e(tag, logStr);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
            bw.write(logStr);
            bw.write("\r\n");
            bw.flush();
            bw.close();
        } catch (Exception e) {
            LogUtil.e(tag, "Write failure !!! " + e);
        }
    }

    /**
     * 获取文件中的数据
     */
    public static String getToDate(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(getFile(fileName));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String message = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                message += line;
            }
            //这个地方会显示这个是存储数据
            LogUtil.e("打印存储数据", message);
            bufferedReader.close();
            fis.close();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("打印存储数据异常", e);
            return "";
        }
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小
     */
    protected static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            } catch (Exception e) {
                LogUtil.e(MY_TAG, e);
            }
        }
        return size;
    }

    /**
     * 获取当前函数的信息
     *
     * @return 当前函数的信息
     */
    protected static String getFunctionInfo() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(instance.getClass().getName())) {
                continue;
            }
            tag = st.getFileName();
            return "[" + logSDF.format(new java.util.Date()) + " " + st.getClassName() + " " + st
                    .getMethodName() + " Line:" + st.getLineNumber() + "]";
        }
        return null;
    }

}
