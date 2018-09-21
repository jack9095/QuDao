package com.kuanquan.qudao.ui.layout;

import android.app.Dialog;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.fly.baselibrary.widget.RoundImageViewM;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gaodun on 2018/9/21.
 *
 */
public class MyFragmentLayout {

    protected TextView nickNameTv, sexTv, biirthdayTv, locationTv, currentStateTv, schoolTv, majorTv, educationTv;
    protected TextView enrollment_YearTv, graduation_YearTv, occupationTv, companyTv, tv_positionTv;
    protected RoundImageViewM iv_avatar;
    protected RelativeLayout viewRoot;
//    public Customer mUser;
    public final static short TAKE_PICTURE = 233;  // 拍照
    public final static short CHOOSE_PICTURE = 244; // 相册选择
//    protected PersonalDataRequest mPersonalDataRequest;
    protected HashMap<String, String> userDataMap;  // 临时存储用户改变的信息的集合
//    protected ToastManager mToastManager;

//    protected ArrayList<Project> projectList;

    protected String changeItem = "";  // 改变的是哪一个item
    protected static final String btn_nickName_f = "btn_nickName_f";
    protected static final String btn_birthday_f = "btn_birthday_f";
    protected static final String btn_sex_f = "btn_sex_f";
    protected static final String btn_currentState_f = "btn_currentState_f";
    protected static final String btn_Education_f = "btn_Education_f";

    protected Dialog bottomPhotoDialog;   //头像选择弹层

    protected TimePickerView pvTime,yndTime;
    protected OptionsPickerView pvEducationOptions,pvCurrentOptions,pvSexOptions,pvOptions;
    protected ArrayList<String> sexLists = new ArrayList<>();
    protected ArrayList<String> currentStateLists = new ArrayList<>();
    protected ArrayList<String> duecationLists = new ArrayList<>(); // 学历

    // 所在地
//    protected ArrayList<JsonBean> options1Items = new ArrayList<>();
    protected ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    protected ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    protected Thread thread;
    protected static final int MSG_LOAD_DATA = 0x0001;
    protected static final int MSG_LOAD_SUCCESS = 0x0002;
    protected static final int MSG_LOAD_FAILED = 0x0003;
    protected boolean isLoaded = false; // true 表示成功解析
    protected boolean isInDate; // true 表示入学时间选择

}
