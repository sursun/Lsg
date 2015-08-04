package com.sursun.houck.lsg;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sursun.houck.common.LocalConfig;
import com.sursun.houck.domain.User;


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
            cMobile.setText(mUser.Name);
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

        mUser.Name = mobile;
        mUser.Note = password;

        attemptToLogin();
    }

    private boolean attemptToLogin(){

        boolean bRet = false;

        if(mUser == null)
            return bRet;

        LocalConfig.SaveUser(LoginActivity.this,mUser);

        Intent intent = new Intent(LoginActivity.this, MapActivity.class);

        intent.putExtra("username",mUser.Name);
        intent.putExtra("note", mUser.Note);

        this.startActivity(intent);

        LoginActivity.this.finish();

        bRet = true;
        return bRet;
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
