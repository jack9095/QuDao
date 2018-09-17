package com.kuanquan.qudao.statusbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kuanquan.qudao.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;


/**
 * 让消息提示显示到状态栏
 */
public class StatuBarActivity extends AppCompatActivity implements OnClickListener{

    private NotificationManager manager;
    private Button button1;
    private Button button2;
    private Button button3;
    private int Notification_ID;

    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private PendingIntent mResultIntent;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);



        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //取得PendingIntent， 并设置跳转到的Activity，
        Intent intent = new Intent(this, StatuBarActivity.class);
        mResultIntent = PendingIntent.getActivity(this, 1, intent, Intent.FLAG_ACTIVITY_NEW_TASK);

    }

    @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                showNotification();
                break;
            case R.id.button2:
                manager.cancel(Notification_ID);
                break;
            case R.id.button3:
                RemoteViews customView = new RemoteViews(getPackageName(),
                        R.layout.notification_layout);

                mNotification = new NotificationCompat.Builder(getBaseContext())

                        // 设置小图标
                        .setSmallIcon(R.mipmap.ic_launcher)

                        // 设置状态栏文本标题
                        .setTicker("你的系统有更新")

                        //设置自定义布局
                        .setContent(customView)

                        // 设置ContentIntent
                        .setContentIntent(mResultIntent)

                        // 设置Notification提示铃声为系统默认铃声
                        .setSound(
                                RingtoneManager.getActualDefaultRingtoneUri(
                                        getBaseContext(),
                                        RingtoneManager.TYPE_NOTIFICATION))

                        // 点击Notification的时候自动移除
                        .setAutoCancel(true).build();

                /*
                 * 当API level 低于14的时候使用setContent()方法是没有用的
                 * 需要对contentView字段直接赋值才能生效
                 */
                if (Build.VERSION.SDK_INT < 14) {
                    mNotification.contentView = customView;
                }

                mNotificationManager.notify(0, mNotification);
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showNotification() {
        Notification.Builder builder=new Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置图标
        builder.setColor(Color.parseColor("#44cb7f")); // 绿色
        builder.setTicker("                    jvnadjfnvjda通知来啦");//手机状态栏的提示
//        builder.setContentTitle("我是通知标题");//设置标题
//        builder.setContentText("我是通知内容");//设置通知内容
        builder.setWhen(System.currentTimeMillis());//设置通知时间
        Intent intent=new Intent(this,StatuBarActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);//点击后的意图
//        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯
//        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
//        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        Notification notification=builder.build();//4.1以上，以下要用getNotification()
        manager.notify(Notification_ID, notification);
    }

}
