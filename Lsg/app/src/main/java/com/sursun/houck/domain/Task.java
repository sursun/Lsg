package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/8/17.
 * 任务
 */
public class Task extends Entity {

    @Override
    public String getGeotableId() {
        return "117513";
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //发布人
    private int userId;

    //内容
    private String content;

    //任务状态
    // 0：发布中
    // 1：关闭
    // 2：完成
    private int status;

    //持续时间
    private int duration;

    //发布时间
    private Date createTime;


}
