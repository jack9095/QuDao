package com.lw.myrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MyAdapter.onChildListener,View.OnClickListener{

    private RecyclerView myRecyclerVIew;
    private ArrayList<String> list;
    private MyAdapter myAdapter;
    private Button btn_list,btn_grid,btn_flow,btn_add,btn_del,btn_change,btn_delanim,btn_addanim,btn_moveanim,btn_changeanim;
    private MyItemAnimator myItemAnimator;
    private DividerListItemDecoration dividerListItemDecoration;
    private DividerGridItemDecoration dividerGridItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initCtrl();
        myAdapter = new MyAdapter(list, this);
        myAdapter.setListener(this);
        myRecyclerVIew.setAdapter(myAdapter);
        myRecyclerVIew.setLayoutManager(new LinearLayoutManager(this));
        /*myRecyclerVIew.addItemDecoration(new RecyclerView.ItemDecoration() {
            *//**
             * 这个方法是控件绘制之前的绘制方法，在这里绘制会比较灵活
             * @param c
             * @param parent
             * @param state
             *//*
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                //c.drawColor(Color.BLUE);
            }

            *//**
             * 绘制前景
             * @param c
             * @param parent
             * @param state
             *//*
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image);
                c.drawBitmap(bitmap,parent.getWidth()/2-bitmap.getWidth()/2,parent.getHeight()/2-bitmap.getHeight(),null);
            }

            *//**
             * 设置分割线
             * @param outRect
             * @param view
             * @param parent
             * @param state
             *//*
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                //参数（左上右下）
                int position = parent.getChildAdapterPosition(view);
                //outRect.set(5,5*position,5,5*position);
                outRect.set(5,5,5,5);
            }
        });*/
        //工具类使用于List方式
        dividerListItemDecoration = new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST);
        dividerGridItemDecoration = new DividerGridItemDecoration(this);
        myRecyclerVIew.addItemDecoration(dividerListItemDecoration);
    }

    private void initView() {
        myRecyclerVIew = ((RecyclerView) findViewById(R.id.myRecyclerView));
        btn_list = ((Button) findViewById(R.id.btn_list));
        btn_grid = ((Button) findViewById(R.id.btn_grid));
        btn_flow = ((Button) findViewById(R.id.btn_flow));
        btn_add = ((Button) findViewById(R.id.btn_add));
        btn_del = ((Button) findViewById(R.id.btn_del));
        btn_change = ((Button) findViewById(R.id.btn_change));
        btn_delanim = ((Button) findViewById(R.id.btn_delanim));
        btn_addanim = ((Button) findViewById(R.id.btn_addanim));
        btn_moveanim = ((Button) findViewById(R.id.btn_moveanim));
        btn_changeanim = ((Button) findViewById(R.id.btn_changeanim));
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i%3==0) {
                list.add(String.format(Locale.CHINA,"第%03d条数据%s",i,"~~~~~~~~~~~~~~~~~~~~~~~~"));
            }else{
                list.add(String.format(Locale.CHINA,"第%03d条数据",i));
            }
        }
    }

    private void initCtrl(){
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_change.setOnClickListener(this);
        btn_delanim.setOnClickListener(this);
        btn_addanim.setOnClickListener(this);
        btn_moveanim.setOnClickListener(this);
        btn_changeanim.setOnClickListener(this);
    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this,"点击了第"+position+"个条目",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(int position) {
        Toast.makeText(this,"点击了第"+position+"个条目的图片",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_list:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);
                myRecyclerVIew.setLayoutManager(linearLayoutManager);
                myRecyclerVIew.removeItemDecoration(dividerGridItemDecoration);
                myRecyclerVIew.addItemDecoration(dividerListItemDecoration);
                break;
            case R.id.btn_grid:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
                //设置跨行或者跨列的数目
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position==0) {
                            return 3;
                        }else if(position==1){
                            return 2;
                        }
                        return 1;
                    }
                });
                myRecyclerVIew.setLayoutManager(gridLayoutManager);
                myRecyclerVIew.removeItemDecoration(dividerListItemDecoration);
                myRecyclerVIew.addItemDecoration(dividerGridItemDecoration);
                break;
            case R.id.btn_flow:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                myRecyclerVIew.setLayoutManager(staggeredGridLayoutManager);
                break;
            case R.id.btn_add:
                myAdapter.Add(0,"新添加的数据");
                myRecyclerVIew.scrollToPosition(0);
                break;
            case R.id.btn_del:
                myAdapter.Remove(0);
                break;
            case R.id.btn_change:
                myAdapter.Change(0,"更改后的数据");
                break;
            case R.id.btn_addanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setAddDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                break;
            case R.id.btn_delanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setRemoveDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                break;
            case R.id.btn_moveanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setMoveDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                break;
            case R.id.btn_changeanim:
                myItemAnimator = new MyItemAnimator();
                myItemAnimator.setChangeDuration(2000);
                myRecyclerVIew.setItemAnimator(myItemAnimator);
                break;
        }
    }
}
