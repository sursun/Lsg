package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/8/17.
 * 任务申请
 */
public class TaskApply extends EntityBase {

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserLoginName() {
        return UserLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        UserLoginName = userLoginName;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(int userLevel) {
        UserLevel = userLevel;
    }

    public String getAuditReason() {
        return AuditReason;
    }

    public void setAuditReason(String auditReason) {
        AuditReason = auditReason;
    }

    public Date getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(Date auditTime) {
        AuditTime = auditTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    //所属任务
    private int TaskId;

    // 申请人
    private int UserId;
    private String UserLoginName;
    //昵称
    private String UserNickName;
    //等级
    private int UserLevel;

    // 审核理由
    private String AuditReason;

    // 审核时间
    private Date AuditTime;

    // 审核状态
    // 0：等待审核 1：审核通过 2：拒绝
    private int Status;

    // 申请时间
    private Date CreateTime;
}
