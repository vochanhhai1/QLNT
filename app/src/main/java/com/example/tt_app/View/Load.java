package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.example.tt_app.R;

public class Load extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIMEOUT = 5000; // 5 giây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myintent = new Intent(Load.this, Login.class);
                startActivity(myintent);
                finish();

                getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
            }
        },SPLASH_SCREEN_TIMEOUT);
    }
}