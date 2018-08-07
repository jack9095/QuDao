package com.example.db;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.db.bean.Recommend;
import com.example.db.bean.TestOne;
import com.example.db.data.RecommendDAO;
import com.example.db.data.TestDAO;
import com.example.fly.baselibrary.utils.base.GsonUtils;
import com.example.fly.baselibrary.utils.useful.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数据库使用
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    private TestDAO dao;
    private RecommendDAO mRecommendDAO;
    private List<Recommend> lists = new ArrayList<>();
    private int page;
    private int startNum; // 从哪里开始查询

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
//        dao = TestDAO.getInstance(DBApplication.getInstence());
//
//        TestOne testOne = new TestOne();
//        testOne.setColor(Color.parseColor("#ffffff"));
//        testOne.setDay(1);
//        testOne.setEventSetId(10);
//        testOne.setDesc("db_test");
//        testOne.setLocation("hhfhfh");
//        testOne.setId(9);
//        testOne.setMonth(2);
//        testOne.setTime(1l);
//        testOne.setYear(1998);
//        testOne.setState(98);
//        testOne.setTitle("测试数据");
//        dao.addSchedule(testOne);

        mRecommendDAO = RecommendDAO.getInstance(DBApplication.getInstence());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Recommend recommend;
                lists.clear();
                for (int i = 0; i < 200; i++) {
                    recommend = new Recommend();
                    recommend.id = i + "";
                    recommend.title = "haha" + i;
                    recommend.content = "北京" + i;
                    recommend.image = "image" + i;
                    recommend.num = "num" + i;
                    recommend.time = "time" + i;
                    lists.add(recommend);
                }
                Collections.reverse(lists);
                boolean b = mRecommendDAO.insertBySql(lists);
//                boolean b = mRecommendDAO.insert(lists);
                LogUtil.e("MainActivity","批量插入 b = " + b);
                break;
            case R.id.button2:
                List<Recommend> recommends = mRecommendDAO.queryAllRecommendData();
                Collections.reverse(recommends);
                LogUtil.json(GsonUtils.toJson(recommends));
                break;
            case R.id.button3:
                LogUtil.json("page = " + page);
                List<Recommend> recommends1 = mRecommendDAO.queryLimitRecommendDataDesc(startNum, 100);
                LogUtil.e("MainActivity","查询数据 = " + recommends1.size());
                LogUtil.json(GsonUtils.toJson(recommends1));
                page++;
                startNum = page * 100;
                break;
            case R.id.button4:
//                Recommend recommend2 = new Recommend();
//                    recommend2.id = 1 + "";
//                    recommend2.title = "haha" + 1;
//                    recommend2.content = "北京" + 1;
//                    recommend2.image = "image" + 1;
//                    recommend2.num = "num" + 1;
//                    recommend2.time = "time" + 1;
//                int w = mRecommendDAO.addRecommend(recommend2);
//                LogUtil.e("MainActivity","插入 = " + w);

//                mRecommendDAO.removeSchedule(0);
                break;
        }
    }
}
