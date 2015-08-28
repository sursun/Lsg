package com.sursun.houck.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateFormat;

import com.sursun.houck.domain.EntityBase;
import com.sursun.houck.model.ResponseModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by houck on 2015/8/28.
 */
public class HttpUtil {
    //public static final String BASE_URL = "http://10.0.2.2:51949";
    public static final String BASE_URL = "http://192.168.0.112:51949";

    public static final String REQUEST_Knock_Server = "/SGAccount/TestConnect";
    public static final String REQUEST_Login = "/SGAccount/AndroidLogin";
    public static final String User_Register = "/SGAccount/RegisterUser";
    public static final String REQUEST_ChangePassWord = "/SGAccount/PadChangePassword";
    public static final String REQUEST_GetResources = "/FileManager/GetResources";
    public static final String REQUEST_DownloadResources = "/FileManager/Download?url=";

    public static HttpGet getHttpGet(String url){
        if(!url.startsWith("http"))
            url = BASE_URL + url;

        LogUtil.i("getHttpGet:" + url);

        HttpGet request = new HttpGet(url);
        return request;
    }

    public static HttpPost getHttpPost(String url)
    {
        if(!url.startsWith("http"))
            url = BASE_URL + url;
        System.out.println("getHttpPost:" + url);
        HttpPost request = new HttpPost(url);
        return request;
    }

    public static HttpResponse getHttpResponse(HttpGet request)
    {
        HttpResponse response = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            response = httpclient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static HttpResponse getHttpResponse(HttpPost request)
    {
        HttpResponse response = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            response = httpclient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

//    public static HttpEntity getEntityByPost(String url, EntityBase entityBase)
//    {
//        HttpPost request = HttpUtil.getHttpPost(url);
//
//
//        //JsonReader
//        try {
//            List<NameValuePair> nvps = PrepareNameValuePair(entityBase);
//            request.setEntity(new UrlEncodedFormEntity(nvps));
//            HttpResponse response = getHttpResponse(request);
//
//            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
//                return response.getEntity();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public static HttpEntity getEntityByPost(String url, List<NameValuePair> nvps)
    {
        HttpPost request = HttpUtil.getHttpPost(url);

        //JsonReader
        try {
            request.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = getHttpResponse(request);

            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                return response.getEntity();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject getJSONObjectByPost(String url, List<NameValuePair> nvps)
    {
        JSONObject resultJsonObject = null;
        HttpEntity httpEntity = getEntityByPost(url,nvps);

        if (httpEntity != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
                StringBuilder entityStringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    entityStringBuilder.append(line + "/n");
                }
                // 利用从HttpEntity中得到的String生成JsonObject
                resultJsonObject = new JSONObject(entityStringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultJsonObject;
    }

    public static JSONObject getJSONObjectByPost(String url,EntityBase entityBase)
    {
        List<NameValuePair> nvps = PrepareNameValuePair(entityBase);
        return getJSONObjectByPost(url,nvps);
//        JSONObject resultJsonObject = null;
//        HttpEntity httpEntity = getEntityByPost(url,entityBase);
//
//        if (httpEntity != null) {
//            try {
//                BufferedReader bufferedReader = new BufferedReader(
//                        new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
//                StringBuilder entityStringBuilder = new StringBuilder();
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    entityStringBuilder.append(line + "/n");
//                }
//                // 利用从HttpEntity中得到的String生成JsonObject
//                resultJsonObject = new JSONObject(entityStringBuilder.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return resultJsonObject;
    }

    public static JSONObject getJSONObjectByPost(String url)
    {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        return getJSONObjectByPost(url,nvps);
//        JSONObject resultJsonObject = null;
//        HttpEntity httpEntity = getEntityByPost(url,entityBase);
//
//        if (httpEntity != null) {
//            try {
//                BufferedReader bufferedReader = new BufferedReader(
//                        new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
//                StringBuilder entityStringBuilder = new StringBuilder();
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    entityStringBuilder.append(line + "/n");
//                }
//                // 利用从HttpEntity中得到的String生成JsonObject
//                resultJsonObject = new JSONObject(entityStringBuilder.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return resultJsonObject;
    }

    public static HttpEntity getEntityForGet(String url)
    {
        HttpGet request = HttpUtil.getHttpGet(url);

        HttpResponse response = getHttpResponse(request);

        if(response.getStatusLine().getStatusCode() == 200)
            return response.getEntity();

        return null;
    }

    public static List<NameValuePair> PrepareNameValuePair(EntityBase entityBase)
    {
        List <NameValuePair> nvps = new ArrayList<NameValuePair>();

        if(entityBase == null)
            return nvps;

        Field[] field = entityBase.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字
            // System.out.println("attribute name:"+name);
            String type = field[j].getGenericType().toString();    //获取属性的类型

            Method m = null;
            try {
                m = entityBase.getClass().getMethod("get"+name);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if(m == null)
                continue;

            if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名

                String value = null;    //调用getter方法获取属性值
                try {
                    value = (String) m.invoke(entityBase);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(value != null){
                    //System.out.println("attribute value:"+value);
                    String strTmp = value;
                    try {
                        strTmp = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    nvps.add(new BasicNameValuePair(name, strTmp));
                }
            }
            else if(type.equals("class java.lang.Integer")){

                Integer value = null;
                try {
                    value = (Integer) m.invoke(entityBase);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(value != null){
                    //System.out.println("attribute value:"+value);
                    nvps.add(new BasicNameValuePair(name,String.valueOf(value)));
                }
            }
            else if(type.equals("class java.lang.Short")){

                Short value = null;
                try {
                    value = (Short) m.invoke(entityBase);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(value != null){
                    //System.out.println("attribute value:"+value);
                    nvps.add(new BasicNameValuePair(name,String.valueOf(value)));
                }
            }
            else if(type.equals("class java.lang.Double")){

                Double value = null;
                try {
                    value = (Double) m.invoke(entityBase);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(value != null){
                    //System.out.println("attribute value:"+value);
                    nvps.add(new BasicNameValuePair(name,String.valueOf(value)));
                }
            }
            else if(type.equals("class java.lang.Boolean")){

                Boolean value = null;
                try {
                    value = (Boolean) m.invoke(entityBase);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(value != null){
                    //System.out.println("attribute value:"+value);
                    nvps.add(new BasicNameValuePair(name,String.valueOf(value)));
                }
            }
            else if(type.equals("class java.util.Date")){

                Date value = null;
                try {
                    value = (Date) m.invoke(entityBase);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(value != null){
                    //System.out.println("attribute value:"+value.toLocaleString());
                    nvps.add(
                            new BasicNameValuePair(name, DateFormat.format("yyy-mm-dd HH:MM:ss", value).toString())
                    );
                }
            }
        }

        return nvps;
    }

    public static boolean isServerAvailable(Context context) {

        boolean bRet = false;
        if (isNetworkConnected(context) == false)
            return bRet;

        try {
            JSONObject resultJsonObject = HttpUtil.getJSONObjectByPost(REQUEST_Knock_Server);

            ResponseModel responseModel = JSONHelper.parseObject(resultJsonObject, ResponseModel.class);
            if (responseModel != null && responseModel.isSuccess())
                bRet = true;

        } catch (JSONException e) {
            LogUtil.i(e.getMessage());
        }
        return bRet;
    }

    public static boolean isNetworkConnected(Context context) {

        if (context != null) {

            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}