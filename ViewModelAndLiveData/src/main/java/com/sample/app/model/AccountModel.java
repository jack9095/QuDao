package com.sample.app.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sample.app.bean.AccountBean;


public class AccountModel extends AndroidViewModel{

    // LiveData
    private MutableLiveData<AccountBean> mAccount = new MutableLiveData<>();

    public AccountModel(@NonNull Application application) {
        super(application);
    }

    public void setAccount(String name, String phone, String blog){
        mAccount.setValue(new AccountBean(name, phone, blog));
    }

    // LiveData
    public MutableLiveData<AccountBean> getAccount(){
        return mAccount;
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
//    直到当前Activity被系统销毁时，Framework会调用ViewModel的onCleared()方法，我们可以在onCleared()方法中做一些资源清理操作
    @Override
    protected void onCleared() {
        Log.e("AccountModel", "==========onCleared()==========");
        super.onCleared();
    }

    public static String getFormatContent(String name, String phone, String blog) {
        StringBuilder mBuilder = new StringBuilder();
        mBuilder.append("昵称:");
        mBuilder.append(name);
        mBuilder.append("\n手机:");
        mBuilder.append(phone);
        mBuilder.append("\n博客:");
        mBuilder.append(blog);
        return mBuilder.toString();
    }
}
