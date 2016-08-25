package com.example.sovnem.crosswalkdemo.bridges;

import com.example.sovnem.crosswalkdemo.InitApplication;
import com.example.sovnem.crosswalkdemo.L;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * @author zjh
 * @description
 * @date 16/8/23.
 */
public class JpushBridge {
    public static final String BRIDGE_NAME ="bindTag";

    public static  void bindToWebview(BridgeWebView bridgeWebView){
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
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
    private static void bindTag(final CallBackFunction function, String tag) {
        JPushInterface.setAlias(InitApplication.getInstance(), tag, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                L.i("绑定结果:" + i + "," + s);
                function.onCallBack("绑定结果:" + i + "," + s);
            }
        });
    }

}
