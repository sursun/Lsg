package com.sursun.houck.domain;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by houck on 2015/8/17.
 */
public class Entity implements IEntity {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getModifytiem() {
        return modifytiem;
    }

    public void setModifytiem(Date modifytiem) {
        this.modifytiem = modifytiem;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    protected int id = 0;
    private Date createtime;
    private Date modifytiem;

    private HashMap<String, String> map = new HashMap<String, String>();

    @Override
    public String getGeotableId() {
        return null;
    }

    @Override
    public HashMap<String, String> getFilterParams() {
        return map;
    }
}
