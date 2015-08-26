package com.sursun.houck.dao;

import android.os.Handler;

import com.sursun.houck.domain.Entity;

import java.util.List;

/**
 * Created by houck on 2015/8/24.
 */
public interface IDaoBase<M extends Entity> {

    public abstract void delete(int id);

    public abstract M saveOrUpdate(M item,Handler handler);

    public abstract M get(String tableid,int  id);

    public abstract List<M> getAll();

//    public abstract List<M> getAll(QueryBase query);
//
//    public abstract RecordList<M> getList(QueryBase query);

}
