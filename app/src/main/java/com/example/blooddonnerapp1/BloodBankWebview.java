package com.example.blooddonnerapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class BloodBankWebview extends AppCompatActivity {
WebView webview;
ProgressBar progressbar;
String url="https://www.justdial.com/Jalandhar/Blood-Banks/nct-10049054";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank_webview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Blood Bank ");

        webview=findViewById(R.id.webview);
        progressbar=findViewById(R.id.progress);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressbar.setVisibility(View.VISIBLE);
            }
        });
webview.getSettings().setJavaScriptEnabled(true);
    }
}