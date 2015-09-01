package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/8/17.
 */
public class UserEval extends EntityBase {

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public int getToUserId() {
        return ToUserId;
    }

    public void setToUserId(int toUserId) {
        ToUserId = toUserId;
    }

    public String getToUserLoginName() {
        return ToUserLoginName;
    }

    public void setToUserLoginName(String toUserLoginName) {
        ToUserLoginName = toUserLoginName;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getFromUserId() {
        return FromUserId;
    }

    public void setFromUserId(int fromUserId) {
        FromUserId = fromUserId;
    }

    public String getFromUserLoginName() {
        return FromUserLoginName;
    }

    public void setFromUserLoginName(String fromUserLoginName) {
        FromUserLoginName = fromUserLoginName;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    private int TaskId;

    //被评价人
    private int ToUserId;
    private String ToUserLoginName;

    //评价类型（雇主、雇员）
    //0：雇员
    //1：雇主
    private String UserRole;

    //内容
    private String Content;

    //评价人
    private int FromUserId;
    private String FromUserLoginName;

    //评价星级
    private int Level;

    //评价时间
    private Date CreateTime;


}
