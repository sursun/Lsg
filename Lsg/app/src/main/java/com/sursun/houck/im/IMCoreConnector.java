package com.sursun.houck.im;

/**
 * Created by Administrator on 2015/8/9.
 */
public interface IMCoreConnector {
    void Login(String userId );
    void SendMessage(String userId );
    void Logout(String userId );
}
