package com.sursun.houck.dao;

import com.sursun.houck.common.HttpUtil;
import com.sursun.houck.common.IHttpResponseHandler;
import com.sursun.houck.common.JSONHelper;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.domain.Task;
import com.sursun.houck.domain.User;
import com.sursun.houck.model.ErrorModel;
import com.sursun.houck.model.TaskModel;
import com.sursun.houck.model.UserModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/30.
 */
public class TaskDao {

    private final String Task_Create = "/Task/Create";
    private final String Task_Close = "/Task/Close";
    private final String Task_Ask = "/Task/Ask";
    private final String Task_Apply = "/Task/Apply";
    private final String Task_Audit = "/Task/Audit";
    private final String Task_Get = "/Task/Get";

    public void Create(final String loginName, final String content,final int duration, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("name",loginName));
        nvps.add(new BasicNameValuePair("content",content));
        nvps.add(new BasicNameValuePair("duration",Integer.toString(duration)));

        HttpUtil.getJSONObjectByPost(Task_Create, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                Task task = null;
                try {
                    TaskModel taskModel = JSONHelper.parseObject((JSONObject) obj, TaskModel.class);
                    if (taskModel != null) {

                        if (taskModel.isSuccess()) {
                            task = taskModel.getData();
                        } else {
                            ErrorModel errorModel = JSONHelper.parseObject((JSONObject) obj, ErrorModel.class);
                            ToastUtil.showMessage(errorModel.getData());
                        }
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();

                }

                if (handler != null)
                    handler.onResponse(task);
            }
        });
    }

    public void Close(final int taskId, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("id",Integer.toString(taskId)));

        HttpUtil.getJSONObjectByPost(Task_Close, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                Task task = null;
                try {
                    TaskModel taskModel = JSONHelper.parseObject((JSONObject) obj, TaskModel.class);
                    if (taskModel != null) {

                        if (taskModel.isSuccess()) {
                            task = taskModel.getData();
                        } else {
                            ErrorModel errorModel = JSONHelper.parseObject((JSONObject) obj, ErrorModel.class);
                            ToastUtil.showMessage(errorModel.getData());
                        }
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();

                }

                if (handler != null)
                    handler.onResponse(task);
            }
        });


    }

    public void Get(final int taskId, final IHttpResponseHandler handler){

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("id",Integer.toString(taskId)));

        HttpUtil.getJSONObjectByPost(Task_Get, nvps, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                Task task = null;
                try {
                    TaskModel taskModel = JSONHelper.parseObject((JSONObject) obj, TaskModel.class);
                    if (taskModel != null) {

                        if (taskModel.isSuccess()) {
                            task = taskModel.getData();
                        } else {
                            ErrorModel errorModel = JSONHelper.parseObject((JSONObject) obj, ErrorModel.class);
                            ToastUtil.showMessage(errorModel.getData());
                        }
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();

                }

                if (handler != null)
                    handler.onResponse(task);
            }
        });


    }

}
