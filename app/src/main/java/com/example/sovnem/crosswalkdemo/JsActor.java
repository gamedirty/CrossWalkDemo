package com.example.sovnem.crosswalkdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;

import org.xwalk.core.JavascriptInterface;

import java.util.Random;

/**
 * @author zjh
 * @description
 * @date 16/8/4.
 */
public class JsActor {
    private Context context;

    public JsActor(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void onJsClick() {
        L.i("方法反反复复");
    }

    @JavascriptInterface
    public String showImage(String local) {
        return null;
    }

}
