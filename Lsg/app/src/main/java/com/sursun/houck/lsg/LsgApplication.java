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
        // ��ʹ�� SDK �����֮ǰ��ʼ�� context ��Ϣ������ ApplicationContext
        SDKInitializer.initialize(this);
    }
}
