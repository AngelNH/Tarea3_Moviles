package com.iteso.classproyect;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iteso.classproyect.tools.WebAppInterface;

import com.iteso.classproyect.tools.WebAppInterface;

import java.net.MalformedURLException;
import java.net.URL;

public class ActivityPrivacyPolicy extends AppCompatActivity {
    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("www.iteso.mx")){
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        setContentView(R.layout.activity_privacy_policy);
        webView = findViewById(R.id.activity_privacy_web);
        webView.loadUrl("file:///android_asset/PrivacyPolicy.html");//remember the triple "/" because it´s linux
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
        webView.setWebViewClient(new MyWebViewClient());
    }
}
