package com.sursun.houck.domain;

import java.util.Date;

/**
 * Created by houck on 2015/8/17.
 */
public class UserEval extends Entity {

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //被评价人
    private int toUserId;

    //评价类型（雇主、雇员）
    //0：雇员
    //1：雇主
    private int type;

    //内容
    private String content;

    //评价人
    private int fromUserId;

    //评价星级
    private int level;

    //评价时间
    private Date createTime;

    @Override
    public String getGeotableId() {
        return "117515";
    }
}
