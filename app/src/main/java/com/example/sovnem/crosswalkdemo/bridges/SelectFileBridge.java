package com.example.sovnem.crosswalkdemo.bridges;

import android.app.Activity;
import android.content.Intent;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * @author zjh
 * @description
 * @date 16/8/23.
 */
public class SelectFileBridge {
    private final static String BRIDGE_NAME = "select_file";
    private static CallBackFunction callBackFunction;
    public static void bindToWebview(BridgeWebView bridgeWebView, final Activity activity,int requestCode) {
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent();
                callBackFunction = function;
            }
        });
    }
}
