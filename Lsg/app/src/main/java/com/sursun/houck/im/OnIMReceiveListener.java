package com.sursun.houck.im;

/**
 * Created by houck on 2015/8/10.
 */
public interface OnIMReceiveListener {
    // 收到新消息
    void OnReceivedMessage(String msg);

//    // 收到群组通知消息（有人加入、退出...）
//    // 可以根据ECGroupNoticeMessage.ECGroupMessageType类型区分不同消息类型
//    void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage notice);
//
//    // 登陆成功之后SDK回调该接口通知账号离线消息数
//    void onOfflineMessageCount(int count);
//
//    // SDK根据应用设置的离线消息拉去规则通知应用离线消息
//    void onReceiveOfflineMessage(List msgs);
//
//    // SDK通知应用离线消息拉取完成
//    void onReceiveOfflineMessageCompletion();
//
//    // SDK通知应用当前账号的个人信息版本号
//    void onServicePersonVersion(int version);
}
