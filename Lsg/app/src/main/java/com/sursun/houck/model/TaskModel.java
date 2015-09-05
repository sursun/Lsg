package com.sursun.houck.model;

import com.sursun.houck.domain.Task;

/**
 * Created by Administrator on 2015/9/4.
 */
public class TaskModel extends ResponseModel {
    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }

    private Task data;
}
