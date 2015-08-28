package com.sursun.houck.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.sursun.houck.domain.User;
import com.sursun.houck.lsg.LsgApplication;

import org.apache.http.util.EncodingUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by houck on 2015/7/31.
 */
public class LocalConfig {

//    private static String fileName = "lcocalinfo";
    private static String SharedInfo_User = "user_info";
    private static SharedPreferences userInfo = null;

    private static SharedPreferences myGetSharedPreferences(){
        if (userInfo == null)
            userInfo = LsgApplication.getInstance().getSharedPreferences(SharedInfo_User, 0);

        return userInfo;
    }
    public static String GetLoginName(){
        return myGetSharedPreferences().getString("name", "");
    }

    public static void SaveLoginName(String name){
        myGetSharedPreferences().edit().putString("name", name).commit();
    }

    public static String GetPassWord(){
        return myGetSharedPreferences().getString("password", "");
    }

    public static void SavePassWord(String psw){
        myGetSharedPreferences().edit().putString("password", psw).commit();
    }

//    public static User GetUser(Context packageContext ) {
//
//        Log.w("LocalConfig","GetUser---begin");
//
//        User user = null;
//        try {
//            String strContent = readFile( packageContext,fileName);
//
//            Log.w("LocalConfig","strContent=" + strContent);
//
//            if(strContent.length() > 3)
//            {
//                String[] lt = strContent.split("\\|");
//
//                user = new User();
//                user.setMobile(lt[0]);
//                user.setNote(lt[1]);
//            }
//
//            if(user == null){
//                Log.w("LocalConfig","User=null");
//            }else{
//                Log.w("LocalConfig","User_Name=" + user.getMobile() + "User_Note" + user.getNote());
//            }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            Log.w("LocalConfig", e.getMessage());
//        }
//
//        Log.w("LocalConfig","GetUser---end");
//        return user;
//    }
//
//    public static boolean SaveUser(Context packageContext,User user) {
//
//        Log.w("LocalConfig","SaveUser---begin");
//         boolean bRet = false;
//        try {
//            String strContent = "";
//
//            strContent += user.getMobile();
//            strContent += "|";
//            strContent += user.getNote();
//
//            Log.w("LocalConfig","strContent=" + strContent);
//
//            writeFile(packageContext,fileName,strContent);
//
//            bRet = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.w("LocalConfig", e.getMessage());
//        }
//
//        Log.w("LocalConfig","SaveUser---end");
//
//        return bRet;
//    }

//    //读文件
//    public static String readFile(Context packageContext,String fileName) throws IOException {
//
//        String res="";
//
//        try {
//            FileInputStream fis=packageContext.openFileInput(fileName); //获得输入流
////用来获得内存缓冲区的数据，转换成字节数组
//            ByteArrayOutputStream stream=new ByteArrayOutputStream();
//            byte[] buffer=new byte[1024];
//            int length=-1;
//            while((length=fis.read(buffer))!=-1) {
//                stream.write(buffer,0,length);//获取内存缓冲区中的数据
//            }
//            stream.close(); //关闭
//            fis.close();
//
//            res = stream.toString();
//           // tv.setText(stream.toString()); //设置文本控件显示内容
//           // Toast.makeText(MyFile.this,"读取成功",Toast.LENGTH_LONG).show();//弹出Toast消息
//        } catch (FileNotFoundException e) {
//            //Toast.makeText(MyFile.this, "文件不存在", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        return res;
//    }
//
//    //写文件
//    public static void writeFile(Context packageContext,String fileName, String write_str) throws IOException{
//
//        try {
//
//        //通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
//            FileOutputStream fos=packageContext.openFileOutput(fileName,Context.MODE_WORLD_WRITEABLE);
//            fos.write(write_str.getBytes());//写入
//            fos.close(); // 关闭输出流
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
}
