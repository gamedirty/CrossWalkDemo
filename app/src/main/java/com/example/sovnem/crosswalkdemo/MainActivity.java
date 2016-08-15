package com.example.sovnem.crosswalkdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.xwalk.core.XWalkView;

public class MainActivity extends AppCompatActivity {
    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 1109;
    XWalkView xWalkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xWalkView = (XWalkView) findViewById(R.id.webview);
//        xWalkView.load("http://url.cn/2FE34ZV", null);
//        xWalkView.load("http://qa.market-trans-h5.wmdev2.lsh123.com/",null);
        xWalkView.load("http://www.baidu.com", null);
        L.i("版本号:" + xWalkView.getAPIVersion());
        xWalkView.addJavascriptInterface(new JsActor(this), "andoridactor");
        xWalkView.load("javascript:alert('gamedirty')", null);

        doLocation();
    }

    private void doLocation() {

        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        aMapLocationClientOption.setInterval(10 * 1000);

        final AMapLocationClient mapLocationClient = new AMapLocationClient(this);
        mapLocationClient.setLocationOption(aMapLocationClientOption);
        mapLocationClient.startLocation();

        AMapLocationListener mapLocationListener = new AMapLocationListener() {

            @Override
            public void onLocationChanged(final AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    L.i("定位成功:" + aMapLocation.getLongitude() + "," + aMapLocation.getLatitude());
                    mapLocationClient.stopLocation();
                }
            }
        };
        mapLocationClient.setLocationListener(mapLocationListener);
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
        if (null != xWalkView) {
            xWalkView.onActivityResult(requestCode, resultCode, data);
        }
    }


}
