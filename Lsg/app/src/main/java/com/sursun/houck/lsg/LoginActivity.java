package com.sursun.houck.lsg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sursun.houck.bdapi.LBSCloudSearch;
import com.sursun.houck.bdapi.LBSLocation;
import com.sursun.houck.common.LocalConfig;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.dao.UserDao;
import com.sursun.houck.domain.User;
import com.sursun.houck.im.OnIMLoginListener;
import com.sursun.houck.im.yuntx.YuntxConnector;

import java.util.Date;

public class LoginActivity extends ActionBarActivity {

    private EditText cMobile;
    private EditText cPassword;

    private User mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //定位
        LBSLocation.getInstance(LsgApplication.getInstance()).startLocation(true);

        cMobile = (EditText)findViewById(R.id.mobile);
        cPassword= (EditText)findViewById(R.id.password);

        //mUser = LocalConfig.GetUser(LoginActivity.this);
        mUser = new User();

       // mUser.setLoginname();

        String name = LocalConfig.GetLoginName();
        String psw = LocalConfig.GetPassWord();
//        SharedPreferences userInfo = getSharedPreferences("user_info", 0);
//        mName = userInfo.getString("name", "");

        if (mUser != null){
            //attemptToLogin();
            cMobile.setText(name);
            cPassword.setText(psw);
        }
    }

    /**
     * 开始登录
     * @param v
     */
    public void onClickLogin(View v) {

        String mobile = cMobile.getText().toString();
        String password = cPassword.getText().toString();

        if(mUser == null)
            mUser = new User();

        mUser.setLoginName(mobile);

        attemptToLogin(mobile,password);
    }

    private boolean attemptToLogin(String name,String psw){

        boolean bRet = false;

        if(mUser == null)
            return bRet;

        LocalConfig.SaveLoginName(name);
        LocalConfig.SavePassWord(psw);

        //LocalConfig.SaveUser(LoginActivity.this, mUser);

        UserDao userDao = new UserDao();

        bRet = userDao.RegisterUser(name,psw);
        //bRet = userDao.ValidUser(name,psw);

//        userDao.saveOrUpdate(mUser, new Handler(){
//            public void handleMessage(Message msg) {
//                //progress.setVisibility(View.INVISIBLE);
//                switch (msg.what) {
//                    case LBSCloudSearch.MSG_NET_TIMEOUT:
//                        ToastUtil.showMessage("创建用户时，超时！");
//                        break;
//                    case LBSCloudSearch.MSG_NET_STATUS_ERROR:
//                        ToastUtil.showMessage("创建用户失败:" + msg.obj.toString());
//                        break;
//                    case LBSCloudSearch.MSG_NET_SUCC:
//                        ToastUtil.showMessage("创建用户成功！");
//                        break;
//
//                }
//            }
//        });

//        YuntxConnector.getInstance().login(mUser.getMobile(), new OnIMLoginListener() {
//            @Override
//            public void onLoginResult(boolean success, String msg) {
//
//                Intent intent = new Intent(LoginActivity.this, MapActivity.class);
//
//                intent.putExtra("username", LoginActivity.this.mUser.getMobile());
//                intent.putExtra("note", LoginActivity.this.mUser.getNote());
//
//                LoginActivity.this.startActivity(intent);
//
//                LoginActivity.this.finish();
//
//            }
//        });

        //bRet = true;
        return bRet;
    }


//    @Override
//    protected void handleReceiver(Context context, Intent intent) {
//        //super.handleReceiver(context, intent);
//        int error = intent.getIntExtra("error" , -1);
//        LogUtil.d("SettingPersionInfoActivity" , "handleReceiver");
//        if(SDKCoreHelper.ACTION_SDK_CONNECT.equals(intent.getAction())) {
//            // 初始注册结果，成功或者失败
//            if(SDKCoreHelper.getConnectState() == ECDevice.ECConnectState.CONNECT_SUCCESS
//                    && error == SdkErrorCode.REQUEST_SUCCESS) {
//
//                dismissPostingDialog();
//                try {
//                    saveAccount();
//                } catch (InvalidClassException e) {
//                    e.printStackTrace();
//                }
//                ContactsCache.getInstance().load();
//                LogUtil.d("SettingPersionInfoActivity" , "handleReceiver ok");
//                if(IMChattingHelper.getInstance().mServicePersonVersion == 0) {
//                    Intent settingAction = new Intent(this, SettingPersionInfoActivity.class);
//                    settingAction.putExtra("from_regist" , true);
//                    startActivityForResult(settingAction, 0x2a);
//                    return ;
//                }
//                doLauncherAction();
//                return ;
//            }
//            if(intent.hasExtra("error")) {
//                if(SdkErrorCode.CONNECTTING == error) {
//                    return ;
//                }
//                ToastUtil.showMessage("登陆失败，请稍后重试[" + error + "]");
//            }
//            dismissPostingDialog();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
