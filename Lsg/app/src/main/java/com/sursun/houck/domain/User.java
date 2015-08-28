package com.sursun.houck.domain;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by houck on 2015/7/31.
 */
public class User extends EntityBase {


    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }

    public int getCallTimes() {
        return CallTimes;
    }

    public void setCallTimes(int callTimes) {
        CallTimes = callTimes;
    }

    public int getRespondTimes() {
        return RespondTimes;
    }

    public void setRespondTimes(int respondTimes) {
        RespondTimes = respondTimes;
    }

    public int getReceiveTimes() {
        return ReceiveTimes;
    }

    public void setReceiveTimes(int receiveTimes) {
        ReceiveTimes = receiveTimes;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    //登录名
    private String LoginName;

    //手机号
    private String Mobile;

    //真实姓名
    private String RealName;

    //昵称
    private String NickName;

    //性别
    //0：男
    //1：女
    private int Gender;

    //等级
    private int Level;

    //积分
    private int Points;

    //发布任务次数
    private int CallTimes;

    //响应任务次数
    private int RespondTimes;

    //执行任务次数
    private int ReceiveTimes;

    //自我介绍
    private String Intro;

    //备注
    private String Note;

    //创建时间
    private Date CreateTime;

}

