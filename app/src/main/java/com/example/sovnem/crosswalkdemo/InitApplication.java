package com.example.sovnem.crosswalkdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * @author zjh
 * @description
 * @date 16/8/16.
 */
public class InitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
    }
}
