package com.example.slezgouka.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by slezgouka on 2/19/2015.
 */
public class HistoryAdapter extends BaseAdapter {

    List<String> mHistoryItems;

    public HistoryAdapter(List<String> operations) {
        mHistoryItems = operations;
    }

    @Override
    public int getCount() {
        return mHistoryItems.size();
    }

    @Override
    public String getItem(int position) {
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
                    .inflate(R.layout.operation_history_item, parent, false);
        }

        TextView mOperation = (TextView) convertView.findViewById(R.id.operation_item);


        String mFullOperation = mHistoryItems.get(position);

        mOperation.setText(mFullOperation.toString());


        return convertView;
    }

}
