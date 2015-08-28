package com.sursun.houck.dao;

import android.os.Handler;

import com.sursun.houck.bdapi.LBSCloudSearch;
import com.sursun.houck.domain.EntityBase;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by houck on 2015/8/17.
 */
public class DaoBase<M extends EntityBase> implements IDaoBase<M>{
    @Override
    public void delete(int id) {

    }

    @Override
    public M saveOrUpdate(M item,Handler callback) {

        HashMap<String, String> mp = getFilterParams(item);

        if (item.getId() > 0 ){
            //upadate
            ;
        }else {
            //create

           // LBSCloudSearch.create(item.getGeotableId(), mp, callback);


        }



        return null;
    }

    @Override
    public M get(String tableid,int id) {

        return null;
    }

    @Override
    public List<M> getAll() {
        return null;
    }

    public HashMap<String, String> getFilterParams(Object obj) {
        HashMap<String, String> mp = new HashMap<String, String>();

        Field[] ff = obj.getClass().getDeclaredFields();
        for (Field field : ff) {

            try {
               // int i = field.getModifiers();

                field.setAccessible(true);

                Object value = field.get(obj);

                if(value == null)
                    continue;

                String strVal = value.toString();

                if(field.getType().toString().indexOf("Date") > 0){
                    Date dd = (Date)value;

                    SimpleDateFormat sDateFormat  =  new  SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    strVal = sDateFormat.format(dd);
                }

                mp.put(field.getName(),strVal );

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return mp;
    }

}
