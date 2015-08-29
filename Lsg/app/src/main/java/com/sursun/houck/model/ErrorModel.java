package com.sursun.houck.model;

import com.sursun.houck.domain.User;

/**
 * Created by houck on 2015/8/28.
 */
public class ErrorModel extends ResponseModel {

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

}
