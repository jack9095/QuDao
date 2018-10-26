package com.kuanquan.qudao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaodun on 2018/10/17.
 * http://t.apidea.gaodun.com/Zhibo?act=liveHomeTwo&student_id=13183&is_handle_token=no
 */

public class LiveResponseData implements Serializable  {
    public String ret  ;
    public String status  ;
    public  Data data;
    public class Data implements Serializable  {
        public List<ProjectList> projectList;  // 直播分类
        public class ProjectList implements Serializable  {
            public String pname  ;  // 名称
            public String pCourseStatus  ;
            public String pTkStatus  ;
            public String pid  ;
        }
        public List<TopOneLive> TopOneLive;  // 今日直播
        public class TopOneLive implements Serializable  {

            public String subject_id  ;     // 科目id
            public String isBuy  ;          // 1 已购买  0 未购买
            public String gensee_room_id  ;
            public String livePwd  ;        // 直播密码
            public String title  ;         // 直播课程标题
            public int iscleanplay  ;
            public String vid  ;          // 直播回放的vid
            public String liveConfirm  ;  // 验证地址
            public String livePPT  ;      // ppt主地址
            public String project_id  ;   // 项目id
            public String teacherPic  ;   // 老师头像
            public String price  ;       // 付费价格
            public String live_url  ;
            public String startTime  ;   // 直播开始时间
            public String isPlayback  ;  // 1 有回放
            public String isnewlive  ;   // 0 展示互动  1 bbb  2 小班课bbb直播
            public String liveSalt  ;     // 盐值
            public String isNeedPay  ;    // 是否需要付费参加 0 不需要 1 需要
            public String subjectName  ;   // 科目名称
            public String liveConfig  ;   // 配置地址
            public String summary  ;      // 直播详情
            public String teacherName  ;   // 教师名称
            public String nowStatus  ;    // 1 15分钟内开始 未预约  2 15分钟内开始 已预约  3 正在直播 4 直播结束 有回放 5 直播结束 无回放 6 大于15分钟 已预约 7大于15分钟 无预约
            public String isseries  ;   // 1 系列直播  0 非系列直播
            public String liveId  ;    // 直播id
            public String imgUrl  ;    // 直播课程图片url
            public String countInlive  ;   // 参与直播人数
            public String countBespeak  ;  // 预约人数
            public String endTime  ;       // 直播结束时间
            public String projectName  ;   // 项目名称
        }
        public List<ZbList> zbList;  // 预告推荐
        public class ZbList implements Serializable  {

            public String subject_id  ;     // 科目id
            public String isBuy  ;          // 1 已购买  0 未购买
            public String gensee_room_id  ;
            public String livePwd  ;        // 直播密码
            public String title  ;         // 直播课程标题
            public int iscleanplay  ;
            public String vid  ;          // 直播回放的vid
            public String liveConfirm  ;  // 验证地址
            public String livePPT  ;      // ppt主地址
            public String project_id  ;   // 项目id
            public String teacherPic  ;   // 老师头像
            public String price  ;       // 付费价格
            public String live_url  ;
            public String startTime  ;   // 直播开始时间
            public String isPlayback  ;  // 1 有回放
            public String isnewlive  ;   // 0 展示互动  1 bbb  2 小班课bbb直播
            public String liveSalt  ;     // 盐值
            public String isNeedPay  ;    // 是否需要付费参加 0 不需要 1 需要
            public String subjectName  ;   // 科目名称
            public String liveConfig  ;   // 配置地址
            public String summary  ;      // 直播详情
            public String teacherName  ;   // 教师名称
            public String nowStatus  ;    // 1 15分钟内开始 未预约  2 15分钟内开始 已预约  3 正在直播 4 直播结束 有回放 5 直播结束 无回放 6 大于15分钟 已预约 7大于15分钟 无预约
            public String isseries  ;   // 1 系列直播  0 非系列直播
            public String liveId  ;    // 直播id
            public String imgUrl  ;    // 直播课程图片url
            public String countInlive  ;   // 参与直播人数
            public String countBespeak  ;  // 预约人数
            public String endTime  ;       // 直播结束时间
            public String projectName  ;   // 项目名称
        }
    }
}