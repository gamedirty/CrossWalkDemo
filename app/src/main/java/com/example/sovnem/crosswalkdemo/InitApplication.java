package com.example.sovnem.crosswalkdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * @author zjh
 * @description
 * @date 16/8/16.
 */
public class InitApplication extends Application {
    private static InitApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
        instance = this;
    }

    public static InitApplication getInstance() {
        return instance;
    }
}
