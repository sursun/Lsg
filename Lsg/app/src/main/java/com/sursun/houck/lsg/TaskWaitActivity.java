package com.sursun.houck.lsg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.sursun.houck.common.IHttpResponseHandler;
import com.sursun.houck.common.ToastUtil;
import com.sursun.houck.dao.TaskDao;
import com.sursun.houck.domain.Task;
import com.sursun.houck.domain.TaskApply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskWaitActivity extends AppCompatActivity {

    private Task mTask = null;
    private TextView mStatusView = null;
    private TextView mTimeView = null;
    private TextView mContentView = null;
    private TextView mCountView = null;

    private ApplyListAdapter mApplyListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_wait);

        mStatusView = (TextView)findViewById(R.id.textViewStutas);
        mTimeView = (TextView)findViewById(R.id.textViewTime);
        mContentView = (TextView)findViewById(R.id.textViewContent);
        mCountView = (TextView)findViewById(R.id.textViewCount);

 //       Intent intent = this.getIntent();
//        int taskId = intent.getIntExtra("taskid",0);
//        TaskDao taskDao = new TaskDao();
//
//        taskDao.Get(taskId, new IHttpResponseHandler() {
//            @Override
//            public void onResponse(Object obj) {
//                mTask = (Task) obj;
//                initTaskInfo();
//            }
//        });

        mApplyListAdapter = new ApplyListAdapter(new ArrayList<TaskApply>());
        final ListView mListView = (ListView) findViewById(R.id.listViewApply);
        mListView.setAdapter(mApplyListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TaskApply taskApply = (TaskApply)mListView.getItemAtPosition(position);
                ToastUtil.showMessage(taskApply.getUserNickName());
            }
        });

        initTaskInfo();
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            long between = (System.currentTimeMillis() - mTask.getCreateTime().getTime())/1000;//秒
            long duration = mTask.getDuration()*60;

            if(between >= duration){
                mStatusView.setText("已经结束");
                mTimeView.setText("00时：00分：00秒");
                return;
            }

            between = duration - between;

            long hour1=between/3600;
            between= between%(60*60);
            long minute1=between/60;
            long second1=between%60;

            String strTmp = "";
            if(hour1 < 10){
                strTmp = strTmp + "0";
            }
            strTmp = strTmp + Long.toString(hour1) + "时：";

            if(minute1 < 10){
                strTmp = strTmp + "0";
            }
            strTmp = strTmp + Long.toString(minute1) + "分：";

            if(second1 < 10){
                strTmp = strTmp + "0";
            }
            strTmp = strTmp + Long.toString(second1) + "秒";

            mTimeView.setText(strTmp);

            handler.postDelayed(runnable,1000);

        }
    };

    private void initTaskInfo(){
        if(mTask == null){
            mTask = new Task();
            mTask.setContent("招收美术（最好擅长手工或陶艺）及音乐类（要求熟悉口琴等孩子容易学会的乐器）专业的学生兼职。要求喜爱孩子、耐心、思想健康。待遇优厚。");
            mTask.setCreateTime(new Date(System.currentTimeMillis()));
            mTask.setDuration(5);
            mTask.setStatus("正在进行");
           // return;
        }

        mStatusView.setText(mTask.getStatus());
        mContentView.setText(mTask.getContent());

        handler.postDelayed(runnable, 1000);

        for (int i=0;i<23;i++){
            TaskApply taskApply = new TaskApply();
            taskApply.setUserNickName("程康" + i);
            taskApply.setUserLevel(3);
            mApplyListAdapter.list.add(taskApply);
        }

        mApplyListAdapter.notifyDataSetChanged();
        mCountView.setText(Integer.toString(mApplyListAdapter.getCount()));
    }

    private class ApplyListAdapter extends BaseAdapter {
        public List<TaskApply> list;
        public ApplyListAdapter(List<TaskApply> res) {
            super();
            this.list = res;
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            convertView = View.inflate(TaskWaitActivity.this,
                    R.layout.task_apply_item, null);
            final TextView name = (TextView) convertView.findViewById(R.id.textViewName);
            RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);

            if (list == null || list.size() == 0 || index >= list.size()) {
                name.setText("");
            } else {
                name.setText(list.get(index).getUserNickName());
                ratingBar.setRating(list.get(index).getUserLevel());
            }

            Button btnPass = (Button) convertView.findViewById(R.id.btnPass);
            Button btnReject = (Button) convertView.findViewById(R.id.btnReject);
            btnPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showMessage(name.getText() + "通过");
                }
            });

            btnReject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showMessage(name.getText() + "拒绝");
                }
            });

            return convertView;
        }

        @Override
        public int getCount() {
            if (list == null) {
                return 0;
            } else {
                return list.size();
            }

        }

        @Override
        public Object getItem(int index) {
            if (list == null) {
                return new TaskApply();
            } else {
                return list.get(index);
            }

        }

        @Override
        public long getItemId(int id) {
            return id;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_wait, menu);
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
