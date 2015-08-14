package com.sursun.houck.im.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sursun.houck.im.HKMessage;

import java.util.List;

/**
 * Created by houck on 2015/8/12.
 */
public class ChattingListAdapter extends BaseAdapter {


    public List<HKMessage> list;

    public ChattingListAdapter(List<HKMessage> res) {
        super();
        this.list = res;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
