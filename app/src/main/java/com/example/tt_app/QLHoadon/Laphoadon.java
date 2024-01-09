package com.example.tt_app.QLHoadon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tt_app.R;

public class Laphoadon extends AppCompatActivity {

    ImageView laphoadonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laphoadon);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxaid();

        laphoadonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), HoaDon.class);
                startActivity(intent);
            }
        });
    }

    private void anhxaid() {
        laphoadonBack= findViewById(R.id.laphoadonBack);
    }
}