package com.example.sovnem.crosswalkdemo;

import org.xwalk.core.JavascriptInterface;

/**
 * @author zjh
 * @description
 * @date 16/8/4.
 */
public class JsActor {

    @JavascriptInterface
    public void onJsClick() {
        L.i("方法反反复复");
    }

    public String showImage(String local) {
        return null;
    }


}
