package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tt_app.R;

public class ChonPhong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_phong);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}