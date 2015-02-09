package com.example.slezgouka.browser;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by slezgouka on 2/8/2015.
 */
public class HistoryItem implements Parcelable {
    private String mName;
    private String mUrl;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "mName='" + mName + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mUrl);
    }

    public HistoryItem(Parcel source) {
        mName = source.readString();
        mUrl = source.readString();
    }

    public HistoryItem() {

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public HistoryItem createFromParcel(Parcel source) {
            final HistoryItem hi = new HistoryItem();
            hi.mName = source.readString();
            hi.mUrl = source.readString();
            return hi;
        }

        @Override
        public HistoryItem[] newArray(int size) {
            return new HistoryItem[size];
        }
    };
}
