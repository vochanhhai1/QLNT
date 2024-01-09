package com.example.tt_app.QLHoadon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tt_app.QLHoadon.Adapter.ChonphongAdapter;
import com.example.tt_app.R;
import com.example.tt_app.model.DataPhong;

public class Laphoadon extends AppCompatActivity {

    ImageView laphoadonBack;
    TextView rlChonphong;
    String tenphong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laphoadon);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxaid();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            DataPhong obj = (DataPhong) bundle.getSerializable("dataphong");
            tenphong = obj.getPhong();
        }
        rlChonphong.setText("Phòng số "+tenphong);
        rlChonphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), ChonPhong.class);
                startActivity(intent);
            }
        });
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
        rlChonphong = findViewById(R.id.rlChonphong);
    }
}