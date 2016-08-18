package com.example.sovnem.crosswalkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sovnem.crosswalkdemo.bridge.LocationBridge;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.lsh123.zxing.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class HullActivity extends AppCompatActivity {
    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1109;
    BridgeWebView xWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xWalkView = (BridgeWebView) findViewById(R.id.webview);
//        xWalkView.load("http://url.cn/2FE34ZV", null);
//        xWalkView.load("http://qa.market-trans-h5.wmdev2.lsh123.com/",null);
//        xWalkView.load("http://yc.market-h5.wmdev2.lsh123.com", null);
        xWalkView.load("file:///android_asset/index.html", null);


        LocationBridge.bindToWebview(xWalkView);

        L.i("版本号:" + xWalkView.getAPIVersion());
        xWalkView.registerHandler("scaner", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                launchScanner(function);
            }
        });

        xWalkView.registerHandler("bindTag", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                L.i(data);
                try {
                    JSONObject jo = new JSONObject(data);
                    bindTag(function, jo.getString("tag"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void bindTag(final CallBackFunction function, String tag) {
        JPushInterface.setAlias(this, tag, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                L.i("绑定结果:" + i + "," + s);
                function.onCallBack("绑定结果:" + i + "," + s);
            }
        });
    }

    private CallBackFunction scanCallback;

    private void launchScanner(CallBackFunction function) {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 1109);
        scanCallback = function;
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xWalkView != null)
            xWalkView.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (xWalkView != null) {
            xWalkView.onNewIntent(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1109) {
                String result = print(data);
                scanCallback.onCallBack(result);
            }
        }
    }

    private String print(Intent data) {
        JSONObject jo = new JSONObject();
        Bundle b = data.getExtras();
        Set<String> set = b.keySet();
        for (String s : set) {
            L.i(s + "," + b.get(s));
            try {
                jo.put(s, b.get(s));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jo.toString();
    }


}
