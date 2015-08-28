package com.sursun.houck.dao;

import com.sursun.houck.common.HttpUtil;
import com.sursun.houck.common.JSONHelper;
import com.sursun.houck.domain.User;
import com.sursun.houck.model.UserModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houck on 2015/8/25.
 */
public class UserDao {

    public boolean RegisterUser(final String mobile, final String psw){
        boolean bRet = false;

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("mobile",mobile));
        nvps.add(new BasicNameValuePair("psw",psw));

        JSONObject resultJsonObject = HttpUtil.getJSONObjectByPost(HttpUtil.User_Register, nvps);

        try {
            UserModel userModel = JSONHelper.parseObject(resultJsonObject, UserModel.class);
            if (userModel != null && userModel.isSuccess())
                bRet = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bRet;
    }
    public boolean ValidUser(final String loginName, final String passWord){
        boolean bRet = false;

//        User usr = new User();
//        usr.setName(userName);
//        usr.setPassWord(passWord);

        //HttpEntity entity = HttpUtil.getEntityByPost("/SGAccount/AndroidLogin", usr);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("name",loginName));
        nvps.add(new BasicNameValuePair("psw",passWord));

        JSONObject resultJsonObject = HttpUtil.getJSONObjectByPost(HttpUtil.REQUEST_Login, nvps);

        try {
            UserModel userModel = JSONHelper.parseObject(resultJsonObject, UserModel.class);
            if (userModel != null && userModel.isSuccess())
                bRet = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bRet;
    }

    public boolean ChangePassWord(String strUserName,String strOld,String strNew){
        boolean bRet = false;

        User usr = new User();
//        usr.setName(strUserName);
//        usr.setPassWord(strOld);
//        usr.setNewPassWord(strNew);

        JSONObject resultJsonObject = HttpUtil.getJSONObjectByPost(HttpUtil.REQUEST_ChangePassWord, usr);

        try {
            UserModel userModel = JSONHelper.parseObject(resultJsonObject, UserModel.class);
            if (userModel != null && userModel.isSuccess())
                bRet = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bRet;
    }

}
