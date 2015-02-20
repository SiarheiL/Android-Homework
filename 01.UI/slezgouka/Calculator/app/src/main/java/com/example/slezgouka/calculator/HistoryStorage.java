package com.example.slezgouka.calculator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by slezgouka on 2/19/2015.
 */
public class HistoryStorage {
    private List<String> mHistoryList = new LinkedList<>();

    private HistoryStorage() {
    }

    ;


    public List<String> getHistoryList() {
        return mHistoryList;
    }

    public void addHistoryElement(String operation) {
        mHistoryList.add(operation);
    }


    private static class HistoryStorageHelper {
        private static final HistoryStorage INSTANCE = new HistoryStorage();
    }

    public static HistoryStorage getInstance() {
        return HistoryStorageHelper.INSTANCE;
    }

}
