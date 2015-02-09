package com.example.slezgouka.browser;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slezgouka on 2/6/2015.
 */
public class HistoryAdapter extends BaseAdapter {
    List<HistoryItem> mHistoryItems;

    public HistoryAdapter(List<HistoryItem> contacts) {
        mHistoryItems = contacts;
    }

    @Override
    public int getCount() {
        return mHistoryItems.size();
    }

    @Override
    public HistoryItem getItem(int position) {
        return mHistoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.history_item_layout, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView phone = (TextView) convertView.findViewById(R.id.url);

        HistoryItem con = mHistoryItems.get(position);

        name.setText(con.getName());
        phone.setText(con.getUrl());

        return convertView;
    }
}
