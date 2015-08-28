package com.sursun.houck.model;

import com.sursun.houck.domain.User;

/**
 * Created by houck on 2015/8/28.
 */
public class UserModel extends ResponseModel {

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    private User data;

}
