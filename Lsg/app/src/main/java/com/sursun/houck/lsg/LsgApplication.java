package com.sursun.houck.lsg;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.sursun.houck.common.LogUtil;
import com.sursun.houck.domain.User;

/**
 * Created by houck on 2015/7/28.
 */
public class LsgApplication extends Application {

    private static LsgApplication instance;

    //
    //全局变量
    //

    // 定位结果
    public BDLocation curLocation = null;
    public LatLng ptCurLocation = null;

    //当前登录用户
    public User mUser = null;

    /**
     * 单例，返回一个实例
     * @return
     */
    public static LsgApplication getInstance() {
        if (instance == null) {
            LogUtil.w("[LsgApplication] instance is null.");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);

    }
}
