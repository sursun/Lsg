package com.sursun.houck.im;

import com.sursun.houck.im.yuntx.SDKCoreHelper;

/**
 * Created by Administrator on 2015/8/9.
 */
public class IMCoreHelper {

    private static IMCoreHelper sInstance;
    public static IMCoreHelper getInstance() {
        if (sInstance == null) {
            sInstance = new IMCoreHelper();
        }
        return sInstance;
    }

    private OnServerConnectListener b;
    public void setOnServerConnectListener(OnServerConnectListener var1) {
        this.b = var1;
    }
    public OnServerConnectListener getOnServerConnectListener() {
        return this.b;
    }

    public void Login(String userId){
        SDKCoreHelper.getInstance().Login(userId);
    }


}
