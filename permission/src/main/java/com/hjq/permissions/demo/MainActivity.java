package com.hjq.permissions.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.fly.permissions.OnPermissionListener;
import com.fly.permissions.Permission;
import com.fly.permissions.CallPermissions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 请求权限
    public void requestPermission(View view) {
        CallPermissions.with(this)
                .permission(Permission.CALL_PHONE, Permission.CAMERA) // 检查通话和相机权限
                .request(new OnPermissionListener() {

                    @Override
                    public void onPermissionResult(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "获取权限成功，部分权限未正常授予", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void errorPermission(List<String> denied, boolean quick) {
                        if(quick) {
                            Toast.makeText(MainActivity.this, "被永久拒绝授权，请手动授予权限", Toast.LENGTH_SHORT).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            CallPermissions.gotoPermissionSettings(MainActivity.this);
                        }else {
                            Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 判断是否获得权限
    public void isHasPermission(View view) {
        if (CallPermissions.isPermission(MainActivity.this, Permission.CALL_PHONE, Permission.CAMERA)) {
            Toast.makeText(MainActivity.this, "已经获取到权限，不需要再次申请了", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "还没有获取到权限或者部分权限未授予", Toast.LENGTH_SHORT).show();
        }
    }

    // 跳转修改权限的设置页面 （安卓手机众多，有的可能不能跳转）
    public void gotoPermissionSettings(View view) {
        CallPermissions.gotoPermissionSettings(MainActivity.this);
    }
}
