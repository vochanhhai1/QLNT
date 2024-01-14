package com.example.tt_app.QLHopdong;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.tt_app.R;

public class ThemHopdong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hopdong);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        WebView webView = findViewById(R.id.webview);
        String htmlData = "<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"></head><body><h1>Hello, World!</h1><p>Đây là ví dụ.</p></body></html>";
        String mime = "text/html";
        String encoding = "utf-8";
        webView.loadDataWithBaseURL(null, htmlData, mime, encoding, null);
    }
}