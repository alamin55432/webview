package com.example.app;
// আপনার ফাইলের ওপরের package লাইনটি এখানে থাকবে

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.webview); // XML-এর ID-এর সাথে মিলিয়ে নেবেন
        WebSettings webSettings = myWebView.getSettings();
        
        // আধুনিক ওয়েবসাইট লোড হওয়ার সব জরুরি সেটিংস
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        myWebView.setWebChromeClient(new WebChromeClient());
        
        myWebView.setWebViewClient(new WebViewClient() {
            // SSL বা সার্টিফিকেট সামান্য সমস্যা থাকলেও যেন সাদা পেজ না দেখায়
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        // আপনার ওয়েবসাইটের পুরো লিংক (অবশ্যই https:// বা http:// সহ)
        myWebView.loadUrl("https://me-seven-beta.vercel.app/");
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
