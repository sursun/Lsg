package com.sursun.houck.domain;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by houck on 2015/7/31.
 */
public class User extends Entity {

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickName) {
        this.nickname = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCalltimes() {
        return calltimes;
    }

    public void setCalltimes(int calltimes) {
        this.calltimes = calltimes;
    }

    public int getRespondtimes() {
        return respondtimes;
    }

    public void setRespondtimes(int respondtimes) {
        this.respondtimes = respondtimes;
    }

    public int getReceiveTimes() {
        return receiveTimes;
    }

    public void setReceiveTimes(int receiveTimes) {
        this.receiveTimes = receiveTimes;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    //手机号，登录名
    private String mobile;

    //密码
    private String password;

    //昵称
    private String nickname;

    //性别
    //0：男
    //1：女
    private int gender;

    //等级
    private int level;

    //积分
    private int points;

    //发布任务次数
    private int calltimes;

    //响应任务次数
    private int respondtimes;

    //执行任务次数
    private int receiveTimes;

    //自我介绍
    private String intro;

    //备注
    private String note;

    //最后一次登录时间
    private Date lastlogintime;

    @Override
    public String getGeotableId() {
        return "62066";
    }

    @Override
    public HashMap<String, String> getFilterParams() {
        HashMap<String, String> mp = super.getFilterParams();
        mp.put("radius", "eee");



        return mp;
    }
}

