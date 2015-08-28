package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/8/17.
 * 任务申请
 */
public class TaskApplication extends EntityBase {

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //所属任务
    private int taskId;

    // 申请人
    private int userId;

    // 申请时间
    private Date createTime;

    // 审核理由
    private String auditReason;

    // 审核时间
    private Date auditTime;

    // 审核状态
    // 0：等待审核 1：审核通过 2：拒绝
    private int status;


}
