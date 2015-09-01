package com.sursun.houck.lsg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sursun.houck.common.IHttpResponseHandler;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.dao.UserDao;

public class RegisterUserActivity extends AppCompatActivity {

    private RegisterUserActivity mInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mInstance = this;
    }

    public void onClickRegister(View v) {

        EditText cMobile = (EditText)findViewById(R.id.editTextMobile);
        EditText cPsw1 = (EditText)findViewById(R.id.editTextPsw1);
        EditText cPsw2 = (EditText)findViewById(R.id.editTextPsw2);

        String mobile = cMobile.getText().toString().trim();
        String psw1 = cPsw1.getText().toString().trim();
        String psw2 = cPsw2.getText().toString().trim();

        if (!psw1.endsWith(psw2)){
            ToastUtil.showMessage("两次输入的密码不一致！");
            return;
        }

        UserDao userDao = new UserDao();

        userDao.RegisterUser(mobile, psw1, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                boolean bRet = (boolean)obj;
                if(bRet){
                    ToastUtil.showMessage("创建用户成功！");
                    if(mInstance != null)
                        mInstance.finish();
                }else{
                    ToastUtil.showMessage("创建失败~~~~");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_user, menu);
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
