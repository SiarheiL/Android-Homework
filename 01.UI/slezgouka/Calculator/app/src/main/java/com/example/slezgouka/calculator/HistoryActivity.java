package com.example.slezgouka.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slezgouka on 2/19/2015.
 */
public class HistoryActivity  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_history_list);

        ListView mListViw = (ListView) findViewById(android.R.id.list);
        mListViw.setAdapter(new HistoryAdapter(HistoryStorage.getInstance().getHistoryList()));

    }
}
