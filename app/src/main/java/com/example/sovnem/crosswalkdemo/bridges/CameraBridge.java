package com.example.sovnem.crosswalkdemo.bridges;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author zjh
 * @description
 * @date 16/8/23.
 */
public class CameraBridge {

    public final static String BRIDGE_NAME = "camera";
    private static CallBackFunction callBackFunction;

    public static void bindToWebview(BridgeWebView bridgeWebView, final Activity activity, final int requestCode) {
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                activity.startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), requestCode);
                callBackFunction = function;
            }
        });
    }

    public static void handleActivityResult(Bundle bundle) {
        if (callBackFunction == null) return;
        Bitmap bm = (Bitmap) bundle.get("data");
        String result = "data:image/png;base64," + bitmaptoString(bm);
        callBackFunction.onCallBack(result);
    }

    public static String bitmaptoString(Bitmap bitmap) {
        // 将Bitmap转换成Base64字符串
        StringBuffer string = new StringBuffer();
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            bStream.flush();
            bStream.close();
            byte[] bytes = bStream.toByteArray();
            string.append(Base64.encodeToString(bytes, Base64.NO_WRAP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("string.." + string.length());
        return string.toString();
    }
}
