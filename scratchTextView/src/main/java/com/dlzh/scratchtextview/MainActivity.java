package com.dlzh.scratchtextview;

import android.app.Activity;
import android.os.Bundle;

import java.util.Random;

/**
 * 安卓刮刮乐效果
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScratchTextView mStv = (ScratchTextView) findViewById(R.id.tv_Scratch);
        String[] array = {"谢谢惠顾", "一等奖", "再买一瓶"};
        mStv.setText(array[0]);
        mStv.initScratchCard(0xFFCECED1, 50, 0f);//调用方法，初始化scartchTextView
    }

//    /**
//     * 随机生成一个数
//     */
//    private int getRandom() {
//        Random random = new Random();
//        int number = random.nextInt(2);
//        return number;
//    }
}
