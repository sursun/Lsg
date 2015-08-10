package com.sursun.houck.im;

/**
 * Created by houck on 2015/8/9.
 */
public interface IMConnector {

    void login(String userId, OnIMLoginListener l);

    void logout(String userId, OnIMLogoutListener l);

    void sendMessage(String to, String msg, OnIMSendListener l);
    void sendMessage(String from, String to, String msg, OnIMSendListener l);

    void setOnIMReceiveListener(OnIMReceiveListener l);
    OnIMReceiveListener getOnIMReceiveListener();
}
