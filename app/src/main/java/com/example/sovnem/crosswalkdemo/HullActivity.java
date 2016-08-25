package com.example.sovnem.crosswalkdemo;

import android.os.Bundle;

import com.example.sovnem.crosswalkdemo.bridges.BaseBridgeActivity;
import com.example.sovnem.crosswalkdemo.bridges.CameraBridge;
import com.example.sovnem.crosswalkdemo.bridges.JpushBridge;
import com.example.sovnem.crosswalkdemo.bridges.LocationBridge;
import com.example.sovnem.crosswalkdemo.bridges.PhoneCallBridge;
import com.example.sovnem.crosswalkdemo.bridges.ScanCodeBridge;
import com.example.sovnem.crosswalkdemo.bridges.SelectContactBridge;
import com.github.lzyzsd.jsbridge.BridgeWebView;

public class HullActivity extends BaseBridgeActivity {
    BridgeWebView xWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xWalkView = (BridgeWebView) findViewById(R.id.webview);
//        xWalkView.load("http://url.cn/2FE34ZV", null);
//        xWalkView.load("http://qa.market-trans-h5.wmdev2.lsh123.com/",null);
//        xWalkView.load("http://toutiao.com/a6321453243865530626/", null);
//        xWalkView.load("http://yc.market-h5.wmdev2.lsh123.com", null);
//        xWalkView.loadUrl("file:///android_asset/index.html");
        xWalkView.load("file:///android_asset/index.html", null);
//        xWalkView.load("http://www.baidu.com",null);
        bindBridge(xWalkView);
    }

    private void bindBridge(BridgeWebView xWalkView) {
        LocationBridge.bindToWebview(xWalkView);
        JpushBridge.bindToWebview(xWalkView);
        ScanCodeBridge.bindToWebview(xWalkView, this, REQUESTCODE_SCAN_CODE);
        CameraBridge.bindToWebview(xWalkView, this, REQUESTCODE_TAKE_PHOTO);
        PhoneCallBridge.bindToWebview(xWalkView,this);
        SelectContactBridge.bindToWebview(xWalkView,this,REQUESTCODE_SELECT_CONTACT);
    }


}
