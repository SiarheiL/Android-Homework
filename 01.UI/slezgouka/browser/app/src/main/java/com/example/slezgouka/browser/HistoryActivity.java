package com.example.slezgouka.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by slezgouka on 2/8/2015.
 */
public class HistoryActivity extends Activity {
    private static List<HistoryItem> sHistoryItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list_layout);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        sHistoryItems = b.getParcelableArrayList("historyItems");
        ListView mListViw = (ListView) findViewById(android.R.id.list);
        mListViw.setAdapter(new HistoryAdapter(sHistoryItems));

    }
}
