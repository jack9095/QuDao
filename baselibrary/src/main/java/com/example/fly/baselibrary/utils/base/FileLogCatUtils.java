package com.example.fly.baselibrary.utils.base;

import android.support.annotation.NonNull;

import com.example.fly.baselibrary.BaseApplication;
import com.example.fly.baselibrary.bean.ResponseBean;
import com.example.fly.baselibrary.bean.VideoLogBean;
import com.example.fly.baselibrary.utils.useful.LogUtil;

import java.io.File;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 视屏日志使用指南
 * 1.在Application 的 onCreate方法中调用 BaseFileUtil.init(this);
 * 2.new FileLogCatUtils().createFile(),  // 这个方法调用的时机，播放器一个周期调用一次，从播放到结束播放
 * 3.new FileLogCatUtils().put();
 * 4.在APP启动的页面调用BaseFileUtil.request();
 */
public class FileLogCatUtils extends BaseFileUtil {

    /**
     * 写入日志文件的数据（扩展）
     *
     * @param videoId  视屏id
     * @param channel  线路
     * @param exitType 退出还是进来
     * @param other    其他需要记录的事件，具体添加请严格参照文档上的描述，否则后果自负
     */
    public void put(String videoId, String channel, String exitType, String other) {
        String writeStr = "videoId," + videoId + ", "
                + "\nappType, 知到" + ", "
                + "\nappPlatform, Android" + ", "
                + "\nappVersion," + VersionUtil.getLocalVersionName(BaseApplication.getAppContext()) + ", "
                + "\nchannel, " + channel + ", "
                + "\nplayTime," + System.currentTimeMillis() + ", "
                + "\npauseTime, " + System.currentTimeMillis() + ", "
                + "\nexitType, " + exitType + ", "
                + "\n" + other + ", ";
        write(writeStr);
    }

    /**
     * 写入日志文件的数据（扩展）
     *
     * @param videoLogBean  视屏日志封装的bean
     */
    public void put(VideoLogBean videoLogBean) {
        String writeStr = "videoId, " + videoLogBean.videoId + ", "
                + "\nuserId, " + videoLogBean.userId + ", "
                + "\nappType, " + "知到" + ", "
                + "\nappVersion, " + VersionUtil.getLocalVersionName(BaseApplication.getAppContext()) + ", "
                + "\niemi, " + videoLogBean.iemi + ", "
//                + "\nnetworkOperator, " + videoLogBean.networkOperator + ", "
                + "\nnetworkType, " + NetWorkUtil.getNetworkInfoType(BaseApplication.getAppContext()) + ", "
                + "\naddr, " + SystemUtil.getIPAddress(BaseApplication.getAppContext()) + ", "
                + "\nbrand, " + SystemUtil.getDeviceBrand() + ", "
                + "\nmodel, " + SystemUtil.getSystemModel() + ", "
                + "\nsystemVersion, " + SystemUtil.getSystemVersion() + ", "
                + "\ntimestamp, " + videoLogBean.timestamp + ", "
                + "\nchannel, " + videoLogBean.channel + ", "
                + "\nresolution, " + videoLogBean.resolution + ", "
                + "\nstartPlayElapsed, " + videoLogBean.startPlayElapsed + ", "
                + "\naction, " + videoLogBean.action + ", "
                + "\nplayTime, " + videoLogBean.playTime + ", "
                + "\npauseTime, " + videoLogBean.pauseTime + ", "
                + "\nexitType, " + videoLogBean.exitType + ", "
                + "\nerrorType, " + videoLogBean.errorType + ", "
                + "\nerrorMessage, " + videoLogBean.errorMessage + ", "
                + "\ncourseId, " + videoLogBean.courseId + ", "
                + "\nschoolId, " + videoLogBean.schoolId + ", "
                + "\nappPlatform, Android" + ", "
                ;
        write(writeStr);
    }

    /**
     * 根据文件名称获取对应的文件  进行存储和读取
     *
     * @return 文件
     */
    public File createFile(String fileName) {
        return getFile(fileName);
    }

    public void test(){
//        Retrofit build = new Retrofit.Builder()
//                .baseUrl("http://114.55.245.140:8088/")
//                .build();
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), logFile);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", logFile.getName(), requestFile);
//        TreeMap<String,String> treeMap = new TreeMap<>();
//        treeMap.put("file", "182683141");
//        Call<ResponseBody> stringCall = build.create(ApiService.class).post(treeMap);
//        stringCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
//                try {
//                    ResponseBody body = response.body();
//                    assert body != null;
//                    String trim = body.string().trim();
//                    LogUtil.e("FileLogCatUtils"," trim = " + trim);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                LogUtil.e("FileLogCatUtils"," call = " + call + " response = " + response);
//                LogUtil.e("FileLogCatUtils"," ThreadName = " + Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
//                LogUtil.e("FileLogCatUtils"," call = " + call + " Throwable = " + t);
//            }
//        });
    }
}