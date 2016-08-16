package com.example.sovnem.crosswalkdemo;


import com.amap.api.location.AMapLocation;

public class MapTools {



    /**
     * 映射高德定位类型
     *
     * @param type
     * @return
     */
    public static String locationTypeName(int type) {
        if (type == AMapLocation.LOCATION_TYPE_GPS) {
            return "GPS";
        } else if (type == AMapLocation.LOCATION_TYPE_SAME_REQ) {
            return "SAME";
        } else if (type == AMapLocation.LOCATION_TYPE_FIX_CACHE) {
            return "CACHE";
        } else if (type == AMapLocation.LOCATION_TYPE_WIFI) {
            return "WIFI";
        } else if (type == AMapLocation.LOCATION_TYPE_CELL) {
            return "CALL";
        } else if (type == AMapLocation.LOCATION_TYPE_AMAP) {
            return "AMAP";
        } else if (type == AMapLocation.LOCATION_TYPE_OFFLINE) {
            return "OFFLINE";
        }

        return "UNKNOW";
    }

}
