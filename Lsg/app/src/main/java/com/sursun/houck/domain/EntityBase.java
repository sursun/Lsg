package com.sursun.houck.domain;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by houck on 2015/8/17.
 */
public class EntityBase implements IEntityBase {

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    protected int Id;


}
