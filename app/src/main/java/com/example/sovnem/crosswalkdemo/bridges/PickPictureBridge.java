package com.example.sovnem.crosswalkdemo.bridges;

import android.app.Activity;
import android.os.Bundle;

import com.elianshang.photopicker.PhotoPickerActivity;
import com.elianshang.photopicker.intent.PhotoPickerIntent;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author zjh
 * @description
 * @date 16/8/26.
 */
public class PickPictureBridge {
    public static final String BRIDGE_NAME = "pickphotos_bridge";
    private static CallBackFunction callBackFunction;

    public static void bindToWebview(BridgeWebView bridgeWebView, final Activity activity, final int requestCode) {
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                PhotoPickerIntent intent = new PhotoPickerIntent(activity);
                intent.setMaxTotal(9);
                intent.setShowCarema(true);
                activity.startActivityForResult(intent, requestCode);
                callBackFunction = function;
            }
        });

    }

    public static final void handleActivityResult(Bundle bundle) {
        if (callBackFunction==null)return;
        ArrayList<String> photos =
                bundle.getStringArrayList(PhotoPickerActivity.EXTRA_RESULT);
        String photosStr = new Gson().toJson(photos);
        callBackFunction.onCallBack(photosStr);

    }
}
