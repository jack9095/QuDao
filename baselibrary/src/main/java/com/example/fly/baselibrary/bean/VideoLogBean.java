package com.example.fly.baselibrary.bean;

import java.io.Serializable;

public class VideoLogBean implements Serializable {

    public String videoId;   // 视屏id
    public String userId;   // 用户id
    public String iemi;   // 移动设备识别码(串号)
    public String timestamp;   // 操作时间戳(整数，13位，精确到毫秒)
    public String channel;   // 线路：1校内(万根)、2网宿、3阿里
    public String resolution;   // 视频清晰度(目前为统一值)
    public String startPlayElapsed;   // 视频起播耗时，单位：毫秒
    public String action;   // 动作(操作方式)，如：play(播放)、pause(暂停)、exit(离开)、full(全屏)、exitFull(退出全屏)等
    public String playTime;   // 播放时间(播放的时间点，如：00:00:05)
    public String pauseTime;   // 暂停时间(暂停时间点，如：00:03:17)
    public String exitType;   // 离开方式：return(返回)、toggle(切换)等
    public String errorType;   // 错误类型
    public String errorMessage;   // 错误消息
    public String courseId;   // 视频所属课程ID
    public String schoolId;   // 用户所属学校ID
}
