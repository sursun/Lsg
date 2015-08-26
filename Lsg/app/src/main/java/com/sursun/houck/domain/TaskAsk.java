package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/8/17.
 * 任务咨询
 */
public class TaskAsk extends Entity {

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //所属任务
    private int taskId;

    //咨询人
    private int userId;

    //咨询内容
    private String content;

    //咨询时间
    private Date createTime;

    @Override
    public String getGeotableId() {
        return "117519";
    }
}
