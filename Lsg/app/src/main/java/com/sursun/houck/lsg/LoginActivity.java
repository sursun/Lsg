package com.sursun.houck.lsg;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.sursun.houck.common.IHttpResponseHandler;
import com.sursun.houck.common.LocalConfig;
import com.sursun.houck.dao.UserDao;
import com.sursun.houck.domain.User;

public class LoginActivity extends ActionBarActivity {

    private EditText cMobile;
    private EditText cPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cMobile = (EditText)findViewById(R.id.mobile);
        cPassword= (EditText)findViewById(R.id.password);

        String name = LocalConfig.GetLoginName();
        String psw = LocalConfig.GetPassWord();

        cMobile.setText(name);
        cPassword.setText(psw);

    }

    /**
     * 开始登录
     * @param v
     */
    public void onClickLogin(View v) {

        String mobile = cMobile.getText().toString();
        String password = cPassword.getText().toString();

        attemptToLogin(mobile, password);
    }

    private void attemptToLogin(String name,String psw){

        LocalConfig.SaveLoginName(name);
        LocalConfig.SavePassWord(psw);


        UserDao userDao = new UserDao();

        userDao.ValidUser(name, psw, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                User user = (User) obj;
                if (user != null) {

                    LsgApplication.getInstance().mUser = user;

                    Intent intent = new Intent(LoginActivity.this, MapActivity.class);

                    LoginActivity.this.startActivity(intent);

                    LoginActivity.this.finish();
                }
            }
        });
    }
    public void onClickRegisterUser(View view){

        Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
        startActivity(intent);
    }

    public void onClickCngPsw(View view){

        UserDao userDao = new UserDao();

        String mobile = cMobile.getText().toString();

        userDao.ChangePassWord(mobile, "123", null);
    }

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
