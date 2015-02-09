package com.example.slezgouka.browser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slezgouka on 2/8/2015.
 */
public class BrowserActivity extends Activity {

    private static ArrayList<HistoryItem> sHistoryItems = new ArrayList<>();

    private static EditText url = null;
    private static WebView sWebView = null;
    private static Button go = null;
    private static Button back = null;
    private static Button forward = null;
    private static Button reload = null;
    private static Button stop = null;
    private static Button history = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_layout);

        url = (EditText) findViewById(R.id.url_input);
        go = (Button) findViewById(R.id.go_button);
        back = (Button) findViewById(R.id.back_btn);
        forward = (Button) findViewById(R.id.forward_btn);
        reload = (Button) findViewById(R.id.reload_btn);
        stop = (Button) findViewById(R.id.stop_btn);
        history = (Button) findViewById(R.id.history_button);

        sWebView = (WebView) findViewById(R.id.web_view);

        WebSettings webSettings = sWebView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);

        sWebView.setWebViewClient(new MyBrowser());

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = url.getText().toString();
                sWebView.loadUrl(urlString);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (back.isEnabled() && sWebView.canGoBack()) {
                    sWebView.goBack();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can not go back", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forward.isEnabled() && sWebView.canGoForward()) {
                    sWebView.goForward();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can not go forward", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sWebView.reload();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stop.isEnabled()) {
                    sWebView.stopLoading();
                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebBackForwardList webBackForwardList = sWebView.copyBackForwardList();
                for (int i = 0; i < webBackForwardList.getSize() - 1; i++) {
                    HistoryItem historyItem = new HistoryItem(Parcel.obtain());
                    WebHistoryItem webHistoryItem = webBackForwardList.getItemAtIndex(i);
                    historyItem.setName(webHistoryItem.getTitle().toString());
                    historyItem.setUrl(webHistoryItem.getUrl().toString());
                    sHistoryItems.add(historyItem);
                }

                Intent intent = new Intent(BrowserActivity.this, HistoryActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("historyItems", sHistoryItems);
                intent.putExtras(b);
                startActivity(intent);

            }
        });


    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            stop.setEnabled(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            stop.setEnabled(false);
            back.setEnabled(sWebView.canGoBack());
            forward.setEnabled(sWebView.canGoForward());
        }


    }

    @Override
    public void onBackPressed() {
        if (sWebView.canGoBack()) {
            sWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
