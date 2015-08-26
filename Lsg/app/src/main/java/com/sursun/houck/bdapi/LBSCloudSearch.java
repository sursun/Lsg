package com.sursun.houck.bdapi;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.sursun.houck.lsg.LsgApplication;
//import org.apache.commons.httpclient.URIException;
//import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.client.utils.*;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by houck on 2015/8/17.
 */
public class LBSCloudSearch {
    private static String mTAG = "LBSCloudSearch";

    //百度云检索API URI
    private static final String BAIDU_BASE_URI = "http://api.map.baidu.com";
    private static final String SEARCH_URI_NEARBY = "http://api.map.baidu.com/geosearch/v2/nearby?";
    private static final String SEARCH_URI_LOCAL = "http://api.map.baidu.com/geosearch/v2/local?";

    private static final String DATA_CREATE_URI = "/geodata/v3/poi/create?";
    private static final String DATA_DETAIL_URI = "http://api.map.baidu.com/geodata/v3/poi/detail?";

    public static final int SEARCH_TYPE_NEARBY = 1;
    public static final int SEARCH_TYPE_LOCAL = 2;

    private static int currSearchType = 0;

    //云检索公钥
    private static String ak = "KNhjSGcx4UUsPv3utjCKXvMQ";

    public static final int MSG_NET_TIMEOUT = 100;
    public static final int MSG_NET_STATUS_ERROR = 200;
    public static final int MSG_NET_SUCC = 1;

    private static int TIME_OUT = 12000;
    private static int retry = 3;
    private static boolean IsBusy = false;

    /**
     * 创建数据
     */
    public static boolean get(final String geotable_id,final HashMap<String, String> filterParams,final Handler handler, final String networkType) {
        if (IsBusy || filterParams == null)
            return false;
        IsBusy = true;

        new Thread() {
            public void run() {
                int count = retry;
                while (count > 0){
                    try {

                        //根据过滤选项拼接请求URL
                        String requestURL = DATA_CREATE_URI;

                        requestURL = requestURL   + "&"
                                + "ak=" + ak
                                + "&geotable_id=" + geotable_id;

                        String filter = null;
                        Iterator iter = filterParams.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry entry = (Map.Entry) iter.next();
                            String key = entry.getKey().toString();
                            String value = entry.getValue().toString();

                            if(key.equals("filter")){
                                filter = value;
                            }else{
                                if(key.equals("region") && currSearchType == SEARCH_TYPE_NEARBY){
                                    continue;
                                }
                                requestURL = requestURL + "&" + key + "=" + value;
                            }
                        }

                        if(filter != null && !filter.equals("")){
                            //substring(3) 为了去掉"|" 的encode  "%7C"
                            requestURL = requestURL + "&filter=" + filter.substring(3);
                        }

                        Log.d("LSG", "request url:" + requestURL);

                        HttpGet httpRequest = new HttpGet(requestURL);
                        HttpClient httpclient = new DefaultHttpClient();
                        httpclient.getParams().setParameter(
                                CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
                        httpclient.getParams().setParameter(
                                CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);

                        HttpProtocolParams.setUseExpectContinue(httpclient.getParams(), false);

                        if(networkType.equals("cmwap")){
                            HttpHost proxy = new HttpHost("10.0.0.172", 80, "http");
                            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                                    proxy);
                        }else if(networkType.equals("ctwap")){
                            HttpHost proxy = new HttpHost("10.0.0.200", 80, "http");
                            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                                    proxy);
                        }


                        HttpResponse httpResponse = httpclient.execute(httpRequest);
                        int status = httpResponse.getStatusLine().getStatusCode();
                        if ( status == HttpStatus.SC_OK ) {

                            String result = EntityUtils.toString(httpResponse
                                    .getEntity(), "utf-8");
                            Header a = httpResponse.getEntity().getContentType();
                            Message msgTmp = handler.obtainMessage(LBSCloudSearch.MSG_NET_SUCC);
                            msgTmp.obj = result;
                            msgTmp.sendToTarget();


                            break;
                        } else {
                            httpRequest.abort();
                            Message msgTmp = handler.obtainMessage(LBSCloudSearch.MSG_NET_STATUS_ERROR);
                            msgTmp.obj = "HttpStatus error";
                            msgTmp.sendToTarget();
                        }
                    } catch (Exception e) {
                        Log.e("LSG", "网络异常，请检查网络后重试！");
                        e.printStackTrace();
                    }

                    count--;
                }

                if ( count <= 0 && handler != null){
                    Message msgTmp =  handler.obtainMessage(LBSCloudSearch.MSG_NET_TIMEOUT);
                    msgTmp.sendToTarget();
                }

                IsBusy = false;

            }
        }.start();

        return true;
    }


    /**
     * 创建数据
     */
    public static boolean create( final String geotable_id,final HashMap<String, String> filterParams,final Handler handler) {
        if (IsBusy || filterParams == null)
            return false;
        IsBusy = true;

        new Thread() {
            public void run() {
                int count = retry;
                while (count > 0){
                    try {

                        //根据过滤选项拼接请求URL
                        String requestURL = BAIDU_BASE_URI + DATA_CREATE_URI;

                        requestURL = requestURL   + "&ak=" + ak
                                + "&geotable_id=" + geotable_id;

                        requestURL = requestURL + "&latitude=" + LsgApplication.getInstance().currlocation.getLatitude();
                        requestURL = requestURL + "&longitude=" + LsgApplication.getInstance().currlocation.getLongitude();
                        requestURL = requestURL + "&coord_type=3";


//                        Iterator iter = filterParams.entrySet().iterator();
//                        while (iter.hasNext()) {
//                            Map.Entry entry = (Map.Entry) iter.next();
//                            String key = entry.getKey().toString();
//                            String value = entry.getValue().toString();
//
//                            requestURL = requestURL + "&" + key + "=" + value;
//                        }
                        Map<String, String> linkMap = new LinkedHashMap<String, String>(filterParams);
                        String paramsStr = toQueryString(linkMap);

                        String wholeStr = new String(DATA_CREATE_URI + paramsStr + "URzNrH4lTRMiT30x2fv56yQYWq9IWhcw");
                        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
                        // 调用下面的MD5方法得到sn签名值
                        String sn = MD5(tempStr);

                        requestURL = requestURL + "&sn=" + sn;

                        Log.d(mTAG, "request url:" + requestURL);

//                        HttpClient client = new DefaultHttpClient();
//                        HttpPost post = new HttpPost(
//                                "http://api.map.baidu.com/geodata/v3/geotable/create");
//                        List<NameValuePair> params = new ArrayList<NameValuePair>();
//                        params.add(new BasicNameValuePair("geotype", "1"));
//                        params.add(new BasicNameValuePair("ak", "yourak"));
//                        params.add(new BasicNameValuePair("name", "geotable80"));
//                        params.add(new BasicNameValuePair("is_published", "1"));
//                        params.add(new BasicNameValuePair("sn", sn));
//                        HttpEntity formEntity = new UrlEncodedFormEntity(params);
//                        post.setEntity(formEntity);
//                        HttpResponse response = client.execute(post);
//                        InputStream is = response.getEntity().getContent();

                        HttpPost httpRequest = new HttpPost(requestURL);
                        HttpClient httpclient = new DefaultHttpClient();
                        httpclient.getParams().setParameter(
                                CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
                        httpclient.getParams().setParameter(
                                CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);

                        HttpProtocolParams.setUseExpectContinue(httpclient.getParams(), false);

                        HttpResponse httpResponse = httpclient.execute(httpRequest);
                        int status = httpResponse.getStatusLine().getStatusCode();
                        if ( status == HttpStatus.SC_OK ) {

                            String result = EntityUtils.toString(httpResponse
                                    .getEntity(), "utf-8");
                            JSONObject json = new JSONObject(result);
                            int nRet = json.getInt("status");

                            Message msgTmp = handler.obtainMessage(LBSCloudSearch.MSG_NET_SUCC);

                            if(nRet != 0){
                                //失败
                                msgTmp =  handler.obtainMessage(LBSCloudSearch.MSG_NET_STATUS_ERROR);
                                result = json.getString("message");
                            }

                            msgTmp.obj = result;
                            msgTmp.sendToTarget();

                            break;
                        } else {
                            httpRequest.abort();
                            Message msgTmp = handler.obtainMessage(LBSCloudSearch.MSG_NET_STATUS_ERROR);
                            msgTmp.obj = "HttpStatus error";
                            msgTmp.sendToTarget();
                        }
                    } catch (Exception e) {
                        Log.e(mTAG, "网络异常，请检查网络后重试！");
                        e.printStackTrace();
                    }

                    count--;
                }

                if ( count <= 0 && handler != null){
                    Message msgTmp =  handler.obtainMessage(LBSCloudSearch.MSG_NET_TIMEOUT);
                    msgTmp.sendToTarget();
                }

                IsBusy = false;

            }
        }.start();

        return true;
    }

    /**
     * 云检索访问
     * @param filterParams	访问参数，key为filter时特殊处理。
     * @param handler		数据回调Handler
     * @param networkType	手机联网类型
     * @return
     */
    public static boolean request( final String geotable_id,final int searchType,final HashMap<String, String> filterParams,final Handler handler, final String networkType) {
        if (IsBusy || filterParams == null)
            return false;
        IsBusy = true;

        new Thread() {
            public void run() {
                int count = retry;
                while (count > 0){
                    try {

                        //根据过滤选项拼接请求URL
                        String requestURL = "";
                        if(searchType == -1){
                            //沿用上次搜索保存的search type
                            if(currSearchType == SEARCH_TYPE_NEARBY){
                                requestURL = SEARCH_URI_NEARBY;
                            }else if(currSearchType == SEARCH_TYPE_LOCAL){
                                requestURL = SEARCH_URI_LOCAL;
                            }
                        }else{
                            if(searchType == SEARCH_TYPE_NEARBY){
                                requestURL = SEARCH_URI_NEARBY;
                            }else if(searchType == SEARCH_TYPE_LOCAL){
                                requestURL = SEARCH_URI_LOCAL;
                            }
                            currSearchType = searchType;
                        }
                        requestURL = requestURL   + "&"
                                + "ak=" + ak
                                + "&geotable_id=" + geotable_id;

                        String filter = null;
                        Iterator iter = filterParams.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry entry = (Map.Entry) iter.next();
                            String key = entry.getKey().toString();
                            String value = entry.getValue().toString();

                            if(key.equals("filter")){
                                filter = value;
                            }else{
                                if(key.equals("region") && currSearchType == SEARCH_TYPE_NEARBY){
                                    continue;
                                }
                                requestURL = requestURL + "&" + key + "=" + value;
                            }
                        }

                        if(filter != null && !filter.equals("")){
                            //substring(3) 为了去掉"|" 的encode  "%7C"
                            requestURL = requestURL + "&filter=" + filter.substring(3);
                        }

                        Log.d("LSG", "request url:" + requestURL);

                        HttpGet httpRequest = new HttpGet(requestURL);
                        HttpClient httpclient = new DefaultHttpClient();
                        httpclient.getParams().setParameter(
                                CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
                        httpclient.getParams().setParameter(
                                CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);

                        HttpProtocolParams.setUseExpectContinue(httpclient.getParams(), false);

                        if(networkType.equals("cmwap")){
                            HttpHost proxy = new HttpHost("10.0.0.172", 80, "http");
                            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                                    proxy);
                        }else if(networkType.equals("ctwap")){
                            HttpHost proxy = new HttpHost("10.0.0.200", 80, "http");
                            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
                                    proxy);
                        }


                        HttpResponse httpResponse = httpclient.execute(httpRequest);
                        int status = httpResponse.getStatusLine().getStatusCode();
                        if ( status == HttpStatus.SC_OK ) {

                            String result = EntityUtils.toString(httpResponse
                                    .getEntity(), "utf-8");
                            Header a = httpResponse.getEntity().getContentType();
                            Message msgTmp = handler.obtainMessage(LBSCloudSearch.MSG_NET_SUCC);
                            msgTmp.obj = result;
                            msgTmp.sendToTarget();


                            break;
                        } else {
                            httpRequest.abort();
                            Message msgTmp = handler.obtainMessage(LBSCloudSearch.MSG_NET_STATUS_ERROR);
                            msgTmp.obj = "HttpStatus error";
                            msgTmp.sendToTarget();
                        }
                    } catch (Exception e) {
                        Log.e("LSG", "网络异常，请检查网络后重试！");
                        e.printStackTrace();
                    }

                    count--;
                }

                if ( count <= 0 && handler != null){
                    Message msgTmp =  handler.obtainMessage(LBSCloudSearch.MSG_NET_TIMEOUT);
                    msgTmp.sendToTarget();
                }

                IsBusy = false;

            }
        }.start();

        return true;
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    public static String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append( URLEncoder.encode((String) pair.getValue(),"UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}


