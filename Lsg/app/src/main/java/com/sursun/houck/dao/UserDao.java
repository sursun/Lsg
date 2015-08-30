package com.sursun.houck.dao;


import com.sursun.houck.common.HttpUtil;
import com.sursun.houck.common.IHttpResponseHandler;
import com.sursun.houck.common.JSONHelper;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.domain.User;
import com.sursun.houck.model.ErrorModel;
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

    private final String User_Register = "/User/Register";
    private final String REQUEST_Login = "/SGAccount/AndroidLogin";
    private final String REQUEST_ChangePassWord = "/User/CngPsw";
    private final String User_Eval_Add = "/User/Eval";
    private final String User_Eval_List = "/User/EvalList";

    public void RegisterUser(final String mobile, final String psw, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("mobile",mobile));
        nvps.add(new BasicNameValuePair("psw",psw));

        HttpUtil.getJSONObjectByPost(User_Register, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                boolean bRet = false;
                try {
                    UserModel userModel = JSONHelper.parseObject((JSONObject)obj, UserModel.class);
                    if (userModel != null && userModel.isSuccess()){

                    }
                        bRet = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(handler != null)
                    handler.onResponse(bRet);
            }
        });
    }

    public void ValidUser(final String loginName, final String passWord, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("name",loginName));
        nvps.add(new BasicNameValuePair("psw",passWord));

        HttpUtil.getJSONObjectByPost(REQUEST_Login, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                User usr = null;
                try {
                    UserModel userModel = JSONHelper.parseObject((JSONObject)obj, UserModel.class);
                    if (userModel != null ){

                        if(userModel.isSuccess()){
                            usr = userModel.getData();
                        }else{
                            ErrorModel errorModel =JSONHelper.parseObject((JSONObject) obj, ErrorModel.class);
                            ToastUtil.showMessage(errorModel.getData());
                        }
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();

                }

                if(handler != null)
                    handler.onResponse(usr);
            }
        });


    }

    public void ChangePassWord(final String loginName, final String passWord, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("name",loginName));
        nvps.add(new BasicNameValuePair("psw",passWord));

        HttpUtil.getJSONObjectByPost(REQUEST_ChangePassWord, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {

                boolean bRet = false;
                try {
                    ErrorModel errorModel = JSONHelper.parseObject((JSONObject)obj, ErrorModel.class);
                    if (errorModel != null ){
                        if(errorModel.isSuccess())
                            bRet= true;
                        ToastUtil.showMessage(errorModel.getData());
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();

                }

                if(handler != null)
                    handler.onResponse(bRet);
            }
        });


    }


    public void AddEval(final String loginNameFrom,final String loginNameTo, int taskid,String content,int level, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("name",loginName));
        nvps.add(new BasicNameValuePair("psw",passWord));

        HttpUtil.getJSONObjectByPost(REQUEST_Login, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                User usr = null;
                try {
                    UserModel userModel = JSONHelper.parseObject((JSONObject)obj, UserModel.class);
                    if (userModel != null ){

                        if(userModel.isSuccess()){
                            usr = userModel.getData();
                        }else{
                            ErrorModel errorModel =JSONHelper.parseObject((JSONObject) obj, ErrorModel.class);
                            ToastUtil.showMessage(errorModel.getData());
                        }
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();

                }

                if(handler != null)
                    handler.onResponse(usr);
            }
        });


    }

}
