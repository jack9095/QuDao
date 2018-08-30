package com.example.qingchen.vrmr.mainactivity.module;

import com.example.qingchen.vrmr.mainactivity.MainActivity;

import dagger.Component;


@Component(modules = InfoModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
