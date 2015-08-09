package com.sursun.houck.im.yuntx;

/**
 * Created by Administrator on 2015/8/8.
 */
public class CCPAppManager {

    private static ClientUser mClientUser;

    /**
     * 缓存账号注册信息
     * @param user
     */
    public static void setClientUser(ClientUser user) {
        mClientUser = user;
    }

    /**
     * 保存注册账号信息
     * @return
     */
    public static ClientUser getClientUser() {
       return mClientUser;
    }
}
