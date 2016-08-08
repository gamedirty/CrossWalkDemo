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

    @JavascriptInterface
    public void showNotify(String title, String message, String messageId, String extras) {
        showNotification(context, title, message, messageId, extras);
    }

    private void showNotification(Context context, String title, String message, String messageId, String extras) {

        if (TextUtils.isEmpty(title)) {
            title = context.getString(R.string.app_name);
        }
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle
                .setBigContentTitle(title)
//                .setSummaryText("SummaryText")
                .bigText(message);//支持长文本显示

        int notificationId = new Random().nextInt();
        Intent intent = new Intent(context, Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1109, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notify = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon)) //通知图标以largeicon和smallicon叠加显示
                .setTicker(title)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(textStyle)
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)//设置锁屏时候显示通知
                .setPriority(1000)//提高优先级，使能在弹出float window
                .build();
        notify.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS;
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notify.color = Color.parseColor("#efefff");//设置通知图标背景色
        }
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(notificationId, notify);
    }


}
