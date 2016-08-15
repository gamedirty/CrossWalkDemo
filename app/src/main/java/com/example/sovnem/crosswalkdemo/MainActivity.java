package com.example.sovnem.crosswalkdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import org.xwalk.core.XWalkGetBitmapCallback;
import org.xwalk.core.XWalkJavascriptResult;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

public class MainActivity extends AppCompatActivity {
    XWalkView xWalkView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xWalkView = (XWalkView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        xWalkView.setResourceClient(new MyResourceClient(xWalkView));
        xWalkView.setUIClient(new MyUIClient(xWalkView));
//        xWalkView.load("http://url.cn/2FE34ZV", null);
        xWalkView.load("http://qa.market-trans-h5.wmdev2.lsh123.com/",null);
//        xWalkView.load("http://www.baidu.com", null);
        L.i("版本号:" + xWalkView.getAPIVersion());
        xWalkView.addJavascriptInterface(new JsActor(this), "andoridactor");
        xWalkView.load("",null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xWalkView!=null)
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

    class MyResourceClient extends XWalkResourceClient {
        MyResourceClient(XWalkView view) {
            super(view);
        }

        @Override
        public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
            L.i("shouldOverrideUrlLoading:" + url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onProgressChanged(XWalkView view, int progressInPercent) {
            super.onProgressChanged(view, progressInPercent);
            progressBar.setProgress(progressInPercent);
            L.i("onProgressChanged:" + progressInPercent);
        }


    }

    class MyUIClient extends XWalkUIClient {
        MyUIClient(XWalkView view) {
            super(view);
        }

        @Override
        public void onFullscreenToggled(XWalkView view, boolean enterFullscreen) {
            super.onFullscreenToggled(view, enterFullscreen);
        }

        @Override
        public void onPageLoadStarted(XWalkView view, String url) {
            super.onPageLoadStarted(view, url);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
            super.onPageLoadStopped(view, url, status);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public boolean onJsAlert(XWalkView view, String url, String message, XWalkJavascriptResult result) {
            L.i("onJsAlert:" + message);
            return super.onJsAlert(view, url, message, result);
        }

    }

}
