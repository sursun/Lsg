package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/7/31.
 */
public class User {
    //手机号，登录名
    public String mobile="";
    //密码
    public String passWord="";
    //昵称
    public String nickName="";
    //性别
    public Gender gender;
    //备注
    public String note="";

    //创建日期
    public Date createTime;
}

