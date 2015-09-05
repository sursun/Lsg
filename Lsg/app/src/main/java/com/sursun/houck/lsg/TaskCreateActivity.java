package com.sursun.houck.lsg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sursun.houck.common.IHttpResponseHandler;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.dao.TaskDao;
import com.sursun.houck.domain.Task;
import com.sursun.houck.domain.User;

public class TaskCreateActivity extends AppCompatActivity {

    private EditText cContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        cContent = (EditText)findViewById(R.id.editTextContent);
    }

    public void onClickCreate(View v) {

        String strContent = cContent.getText().toString();

        User user = LsgApplication.getInstance().mUser;
        if (user == null)
            return;

        TaskDao taskDao = new TaskDao();

        taskDao.Create(user.getLoginName(), strContent,15, new IHttpResponseHandler() {
            @Override
            public void onResponse(Object obj) {
                Task task = (Task) obj;
                if (task != null) {

                    ToastUtil.showMessage("发布成功！");

                    TaskCreateActivity.this.finish();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_create, menu);
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
