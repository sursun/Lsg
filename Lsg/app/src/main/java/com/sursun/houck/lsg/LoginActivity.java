package com.sursun.houck.lsg;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sursun.houck.common.LocalConfig;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.domain.User;
import com.sursun.houck.im.OnIMLoginListener;
import com.sursun.houck.im.yuntx.YuntxConnector;
import com.yuntongxun.ecsdk.ECDevice;

public class LoginActivity extends ActionBarActivity {

    private EditText cMobile;
    private EditText cPassword;

    private User mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cMobile = (EditText)findViewById(R.id.mobile);
        cPassword= (EditText)findViewById(R.id.password);

        mUser = LocalConfig.GetUser(LoginActivity.this);

        if (mUser != null){
            //attemptToLogin();
            cMobile.setText(mUser.Mobile);
            cPassword.setText(mUser.Note);
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

        mUser.Mobile = mobile;
        mUser.Note = password;

        attemptToLogin();
    }

    private boolean attemptToLogin(){

        boolean bRet = false;

        if(mUser == null)
            return bRet;

        LocalConfig.SaveUser(LoginActivity.this, mUser);

        YuntxConnector.getInstance().login(mUser.Mobile, new OnIMLoginListener() {
            @Override
            public void onLoginResult(boolean success, String msg) {

                Intent intent = new Intent(LoginActivity.this, MapActivity.class);

                intent.putExtra("username", LoginActivity.this.mUser.Mobile);
                intent.putExtra("note", LoginActivity.this.mUser.Note);

                LoginActivity.this.startActivity(intent);

                LoginActivity.this.finish();

            }
        });

        bRet = true;
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
