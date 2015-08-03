package com.sursun.houck.lsg;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by houck on 2015/7/28.
 */
public class LsgApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }
}
