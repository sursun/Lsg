package com.sursun.houck.bdapi;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.sursun.houck.common.LogUtil;
import com.sursun.houck.lsg.LsgApplication;

/**
 * Created by houck on 2015/8/25.
 */
public class LBSLocation {

    private MyLocationListenner myListener = null;
    private LocationClient mLocationClient = null;
    private HKLocationListener hkLocationListener = null;
    private boolean mIsOnce = true;

    private static LBSLocation location = null;
    public static LBSLocation getInstance() {

        if (location == null) {
            location = new LBSLocation();
        }

        return location;
    }

    private LBSLocation() {
        myListener = new MyLocationListenner();
        mLocationClient = new LocationClient(LsgApplication.getInstance());
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
    }

    public void registerLocationListener(HKLocationListener var1) {
        if(var1 == null) {
            throw new IllegalStateException("please set a non-null listener");
        } else {
            hkLocationListener = var1;
        }
    }

    /**
     * 开始定位请求，结果在回调中
     */

    public void startLocation(boolean isOnce) {

        LogUtil.w("startLocation");

        this.mIsOnce = isOnce;

        mLocationClient.stop();

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setAddrType("all");// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        //option.disableCache(true);// 禁止启用缓存定位
        option.setScanSpan(1000);//循环定位时，一秒定位一次

        mLocationClient.setLocOption(option);
        mLocationClient.requestLocation();
    }
    public void stopLocation() {
        mLocationClient.stop();
    }

    /**
     * 监听函数，有新位置的时候，格式化成字符串，输出到屏幕中
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;

            LsgApplication.getInstance().curLocation = location;
            LsgApplication.getInstance().ptCurLocation = new LatLng(location.getLatitude(), location.getLongitude());

            if(mIsOnce){
                mLocationClient.stop();
            }

            if(hkLocationListener != null)
                hkLocationListener.onReceiveLocation(location);
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }
}
