package com.sursun.houck.im.yuntx;

import android.content.Context;

import com.sursun.houck.common.LogUtil;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.im.HKConnector;
import com.sursun.houck.im.OnIMLoginListener;
import com.sursun.houck.im.OnIMReceiveListener;
import com.sursun.houck.im.OnIMSendListener;
import com.sursun.houck.lsg.LsgApplication;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.util.List;

/**
 * Created by houck on 2015/8/10.
 */
public class YuntxConnector extends HKConnector {

    private final String TAG = "YuntxConnector";
    private static YuntxConnector sInstance;

    private ECDevice.ECConnectState mConnect = ECDevice.ECConnectState.CONNECT_FAILED;
    private ECInitParams mInitParams;
    /**用户注册Appkey*/
    private String appKey = "8a48b5514f06f404014f0c4f19700553";
    /**用户注册Token*/
    private String appToken = "55998299fe67d5d7eeeaef19204ef179";

    public static YuntxConnector getInstance() {
        if (sInstance == null) {
            sInstance = new YuntxConnector();
        }
        return sInstance;
    }

    @Override
    public void login(String userId, OnIMLoginListener l) {
        super.login(userId, l);

        if(!ECDevice.isInitialized()) {


            Context ctx = LsgApplication.getInstance().getApplicationContext();
            ECDevice.initial(ctx, new ECDevice.InitListener() {
                @Override
                public void onInitialized() {
                    // SDK已经初始化成功
                    getInstance().initialized();
                }

                @Override
                public void onError(Exception exception) {
                    // SDK 初始化失败,可能有如下原因造成
                    // 1、可能SDK已经处于初始化状态
                    // 2、SDK所声明必要的权限未在清单文件（AndroidManifest.xml）里配置、
                    //    或者未配置服务属性android:exported="false";
                    // 3、当前手机设备系统版本低于ECSDK所支持的最低版本（当前ECSDK支持
                    //    Android Build.VERSION.SDK_INT 以及以上版本）

                    LogUtil.d(TAG, "ECDevice initial error :" + exception.getMessage());

                    ECDevice.unInitial();
                }
            });

            return;
        }

        getInstance().initialized();
    }

    public void initialized() {
        // SDK已经初始化成功

        if (mInitParams == null || mInitParams.getInitParams() == null || mInitParams.getInitParams().isEmpty()){
            mInitParams = new ECInitParams();
        }

        mInitParams.reset();
        mInitParams.setUserid(mUserId);
        mInitParams.setAppKey(appKey);
        mInitParams.setToken(appToken);
        mInitParams.setMode(ECInitParams.LoginMode.FORCE_LOGIN);
        mInitParams.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);

        if(!mInitParams.validate()) {
            ToastUtil.showMessage("注册参数错误，请检查");
//            Intent intent = new Intent(ACTION_SDK_CONNECT);
//            intent.putExtra("error", -1);
//            mContext.sendBroadcast(intent);
            return ;
        }

        // 设置SDK注册结果回调通知，当第一次初始化注册成功或者失败会通过该引用回调
        // 通知应用SDK注册状态.


        // 当网络断开导致SDK断开连接或者重连成功也会通过该设置回调
        // mInitParams.setOnChatReceiveListener(IMChattingHelper.getInstance());
        mInitParams.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener(){

            @Override
            public void onConnect() {
                LogUtil.d(TAG , "onConnect ");
            }

            @Override
            public void onDisconnect(ECError ecError) {

                LogUtil.d(TAG , "onConnectState " + ecError.errorCode);
            }

            @Override
            public void onConnectState(ECDevice.ECConnectState ecConnectState, ECError ecError) {

                boolean bResult = false;
                String strMsg = "";

                if(ecConnectState == ECDevice.ECConnectState.CONNECT_FAILED ) {
                    if(ecError.errorCode == SdkErrorCode.SDK_KICKED_OFF){
                        strMsg = "登录失败，账号异地登陆！";
                    }
                    else{
                        strMsg = "登录失败，消息服务器连接失败！";
                    }
                }
                else if (ecConnectState == ECDevice.ECConnectState.CONNECT_SUCCESS)
                {
                    bResult = true;
                    strMsg = "登录成功";
                }

                if (bResult == false)
                    ToastUtil.showMessage(strMsg);

                LogUtil.d(TAG , "onConnectState " + ecError.errorCode);
                getInstance().mConnect = ecConnectState;

                if (YuntxConnector.getInstance() != null)
                {
                    YuntxConnector.getInstance().loginListener.onLoginResult(bResult, strMsg);
                }
            }
        });

        try {
            ECDevice.login(mInitParams);
        }
        catch (Exception ex)
        {
            LogUtil.d(TAG,ex.getMessage());
        }

    }

    @Override
    public void sendMessage(String from, String to, String msg, OnIMSendListener l) {
        super.sendMessage(from, to, msg, l);

        try {
            // 组建一个待发送的ECMessage
            ECMessage ecMsg = ECMessage.createECMessage(ECMessage.Type.TXT);
            //设置消息的属性：发出者，接受者，发送时间等
            ecMsg.setForm(from);
            ecMsg.setMsgTime(System.currentTimeMillis());
            // 设置消息接收者
            ecMsg.setTo(to);
            ecMsg.setSessionId(to);
            // 设置消息发送类型（发送或者接收）
            ecMsg.setDirection(ECMessage.Direction.SEND);

            // 创建一个文本消息体，并添加到消息对象中
            ECTextMessageBody msgBody = new ECTextMessageBody(msg);

            // 将消息体存放到ECMessage中
            ecMsg.setBody(msgBody);

            // 调用SDK发送接口发送消息到服务器
            ECChatManager manager = ECDevice.getECChatManager();
            manager.sendMessage(ecMsg, new ECChatManager.OnSendMessageListener() {
                @Override
                public void onSendMessageComplete(ECError error, ECMessage message) {
                    // 处理消息发送结果
                    if(message == null) {
                        return ;
                    }
                    // 将发送的消息更新到本地数据库并刷新UI

                    if(YuntxConnector.getInstance().sendListener != null){
                        YuntxConnector.getInstance().sendListener.onComplete(true,"发送成功");
                    }
                }


                @Override
                public void onProgress(String msgId, int totalByte, int progressByte) {
                    // 处理文件发送上传进度（尽上传文件、图片时候SDK回调该方法）
                }

                @Override
                public void onComplete(ECError error) {
                    // 忽略
                }
            });
        } catch (Exception e) {
            // 处理发送异常
            LogUtil.e(TAG, "send message fail , e=" + e.getMessage());
        }

    }

    @Override
    public void setOnIMReceiveListener(OnIMReceiveListener l) {
        super.setOnIMReceiveListener(l);

        YuntxConnector.getInstance().mInitParams.setOnChatReceiveListener(new OnChatReceiveListener() {
            @Override
            public void OnReceivedMessage(ECMessage ecMessage) {
                // 收到新消息
                if(ecMessage == null)
                    return;

                // 接收到的IM消息，根据IM消息类型做不同的处理(IM消息类型：ECMessage.Type)
                ECMessage.Type type = ecMessage.getType();
                if(type != ECMessage.Type.TXT) {
                    return;//非文本消息，暂时不处理 houck
                }
                //
                //houck 暂时只处理文本消息
                //

//                else {
//
//                    String thumbnailFileUrl = null;
//                    String remoteUrl = null;
//                    if (type == ECMessage.Type.FILE) {
//                        // 在这里处理附件消息
//                        ECFileMessageBody fileMsgBody = (ECFileMessageBody) ecMessage.getBody();
//                        // 获得下载地址
//                        remoteUrl = fileMsgBody.getRemoteUrl();
//                    } else if (type == ECMessage.Type.IMAGE) {
//                        // 在这里处理图片消息
//                        ECImageMessageBody imageMsgBody = (ECImageMessageBody) ecMessage.getBody();
//                        // 获得缩略图地址
//                        thumbnailFileUrl = imageMsgBody.getThumbnailFileUrl();
//                        // 获得原图地址
//                        remoteUrl = imageMsgBody.getRemoteUrl();
//                    } else if (type == ECMessage.Type.VOICE) {
//                        // 在这里处理语音消息
//                        ECVoiceMessageBody voiceMsgBody = (ECVoiceMessageBody) ecMessage.getBody();
//                        // 获得下载地址
//                        remoteUrl = voiceMsgBody.getRemoteUrl();
//                    } else {
//                        LogUtil.e(TAG , "Can't handle msgType=" + type.name()
//                                + " , then ignore.");
//                        // 后续还会支持（地址位置、视频以及自定义等消息类型）
//                    }
//
//                    if(TextUtils.isEmpty(remoteUrl)) {
//                        return ;
//                    }
//                    if(!TextUtils.isEmpty(thumbnailFileUrl)) {
//                        // 先下载缩略图
//                    } else {
//                        // 下载附件
//                    }
//                }
                // 根据不同类型处理完消息之后，将消息序列化到本地存储（sqlite）
                // 通知UI有新消息到达

                // 在这里处理文本消息
                ECTextMessageBody textMessageBody = (ECTextMessageBody) ecMessage.getBody();

                if(YuntxConnector.getInstance().receiveListener != null)
                {
                    YuntxConnector.getInstance().receiveListener.OnReceivedMessage(ecMessage.getForm(),textMessageBody.getMessage());
                }
            }

            @Override
            public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage ecGroupNoticeMessage) {
             // 收到群组通知消息（有人加入、退出...）
            // 可以根据ECGroupNoticeMessage.ECGroupMessageType类型区分不同消息类型

            }

            @Override
            public void onOfflineMessageCount(int i) {
                // 登陆成功之后SDK回调该接口通知账号离线消息数
            }

            @Override
            public int onGetOfflineMessage() {
                return 0;
            }

            @Override
            public void onReceiveOfflineMessage(List<ECMessage> list) {

                // SDK根据应用设置的离线消息拉去规则通知应用离线消息
            }

            @Override
            public void onReceiveOfflineMessageCompletion() {

                // SDK通知应用离线消息拉取完成
            }

            @Override
            public void onServicePersonVersion(int i) {
            // SDK通知应用当前账号的个人信息版本号
            }

            @Override
            public void onReceiveDeskMessage(ECMessage ecMessage) {

            }

            @Override
            public void onSoftVersion(String s, int i) {

            }
        });
    }
}
