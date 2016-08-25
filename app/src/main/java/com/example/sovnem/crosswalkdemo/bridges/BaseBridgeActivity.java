package com.example.sovnem.crosswalkdemo.bridges;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sovnem.crosswalkdemo.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

/**
 * 需要启动activity的桥 都要在继承这个baseactivity的activity中注册
 *
 * @author zjh
 * @description
 * @date 16/8/23.
 */
public class BaseBridgeActivity extends AppCompatActivity {
    public static final int REQUESTCODE_TAKE_PHOTO = 0x01;
    public static final int REQUESTCODE_SCAN_CODE = 0x02;
    public static final int REQUESTCODE_SELECT_FILE = 0x03;
    public static final int REQUESTCODE_SELECT_PICTURE = 0x04;
    public static final int REQUESTCODE_SELECT_CONTACT = 0x05;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        print(data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUESTCODE_TAKE_PHOTO:
                    CameraBridge.handleActivityResult(data.getExtras());
                    break;
                case REQUESTCODE_SCAN_CODE:
                    ScanCodeBridge.handleActivityResult(data.getExtras());
                    break;
                case REQUESTCODE_SELECT_FILE:
                    SelectContactBridge.handleActivityResult(data.getExtras());
                    break;
                case REQUESTCODE_SELECT_PICTURE:

                    break;
                case REQUESTCODE_SELECT_CONTACT:
                    break;
            }
        }
    }

    private void print(Intent data) {
        if (data == null) return;
        JSONObject jo = new JSONObject();
        Bundle b = data.getExtras();
        if (b == null) return;
        Set<String> set = b.keySet();
        for (String s : set) {
            L.i(s + "," + b.get(s));
            try {
                jo.put(s, b.get(s));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
