package com.example.sovnem.crosswalkdemo.bridges;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author zjh
 * @description
 * @date 16/8/22.
 */
public class PhoneCallBridge {
    public static final String BRIDGE_NAME = "phonecall_bridge";

    public static void bindToWebview(BridgeWebView bridgeWebView, final Activity activity) {
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String number = jsonObject.getString("number");
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri uri = Uri.parse("tel:" + number);
                    intent.setData(uri);
                    activity.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}



