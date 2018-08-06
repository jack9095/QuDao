package com.example.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.db.data.TestDAO;

/**
 * 数据库使用
 */
public class MainActivity extends AppCompatActivity {

    private TestDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = TestDAO.getInstance(DBApplication.getInstence());
    }

}
