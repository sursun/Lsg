package com.sursun.houck.im;

/**
 * Created by houck on 2015/8/10.
 */
public class HKConnector implements IMConnector {

    protected String mUserId;
    protected OnIMLoginListener loginListener=null;
    protected OnIMLogoutListener logoutListener=null;
    protected OnIMSendListener sendListener=null;
    protected OnIMReceiveListener receiveListener=null;

    @Override
    public void login(String userId, OnIMLoginListener l) {
        mUserId = userId;
        this.loginListener = l;
    }

    @Override
    public void logout(String userId, OnIMLogoutListener l) {
        this.logoutListener = l;
    }

    @Override
    public void sendMessage(String to, String msg, OnIMSendListener l) {
        sendMessage(mUserId, to, msg, l);
    }

    @Override
    public void sendMessage(String from, String to, String msg, OnIMSendListener l) {
        this.sendListener = l;
    }
    @Override
    public void setOnIMReceiveListener(OnIMReceiveListener l) {
        this.receiveListener = l;
    }

    @Override
    public OnIMReceiveListener getOnIMReceiveListener() {
        return receiveListener;
    }
}
