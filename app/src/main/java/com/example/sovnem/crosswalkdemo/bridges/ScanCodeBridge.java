package com.example.sovnem.crosswalkdemo.bridges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.lsh123.zxing.CaptureActivity;

/**
 * 扫二维码桥
 * @author zjh
 * @description
 * @date 16/8/23.
 */
public class ScanCodeBridge {
    public static final String BRIDGE_NAME = "scanBridgeSend";
    private static CallBackFunction callBackFunction;

    public static void bindToWebview(BridgeWebView bridgeWebView, final Activity activity, final int requestCode) {
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                activity.startActivityForResult(new Intent(activity, CaptureActivity.class), requestCode);
                callBackFunction = function;
            }
        });
    }

    public static final void handleActivityResult(Bundle bundle) {
        if (callBackFunction == null) return;
        String result = bundle.getString("SCAN_RESULT");
        callBackFunction.onCallBack(result);
    }

}
