package com.example.sovnem.crosswalkdemo.bridges;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * @author zjh
 * @description
 * @date 16/8/23.
 */
public class SelectContactBridge {
    private final static String BRIDGE_NAME = "select_file";
    private static CallBackFunction callBackFunction;

    public static void bindToWebview(BridgeWebView bridgeWebView, final Activity activity, final int requestCode) {
        bridgeWebView.registerHandler(BRIDGE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                activity.startActivityForResult(intent, requestCode);
                callBackFunction = function;
            }
        });
    }

    public static void handleActivityResult(Intent extras,Activity activity){
        if (callBackFunction==null)return;

        Uri contactData = extras.getData();
        Cursor cursor = activity.managedQuery(contactData, null, null, null,
                null);
        cursor.moveToFirst();
        String num = getContactPhone(cursor,activity);
        callBackFunction.onCallBack(num);
    }

    private static String getContactPhone(Cursor cursor, Activity activity) {
        // TODO Auto-generated method stub
        int phoneColumn = cursor
                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int phoneNum = cursor.getInt(phoneColumn);
        String result = "";
        if (phoneNum > 0) {
            // 获得联系人的ID号
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(idColumn);
            // 获得联系人电话的cursor
            Cursor phone = activity.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                            + contactId, null, null);
            if (phone.moveToFirst()) {
                for (; !phone.isAfterLast(); phone.moveToNext()) {
                    int index = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int typeindex = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                    int phone_type = phone.getInt(typeindex);
                    String phoneNumber = phone.getString(index);
                    result = phoneNumber;
//                  switch (phone_type) {//此处请看下方注释
//                  case 2:
//                      result = phoneNumber;
//                      break;
//
//                  default:
//                      break;
//                  }
                }
                if (!phone.isClosed()) {
                    phone.close();
                }
            }
        }
        return result;
    }
}
