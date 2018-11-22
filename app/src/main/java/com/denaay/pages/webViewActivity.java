package com.denaay.pages;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.denaay.R;

public class webViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String mprofile = getIntent().getStringExtra("mkind");
        WebView webView = (WebView) findViewById(R.id.NewsWebview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.clearCache(true);
        webView.loadUrl(mprofile);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressBar mProgressBar = findViewById(R.id.progressProfile);
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        }, 3000);
    }
}
