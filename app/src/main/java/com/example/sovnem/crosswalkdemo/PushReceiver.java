package com.example.sovnem.crosswalkdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author zjh
 * @description
 * @date 16/8/16.
 */
public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        L.i("收到推送通知了");
    }
}
