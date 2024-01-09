package com.example.tt_app.QLCaidat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.example.tt_app.View.Login;

public class Setting extends AppCompatActivity {


    ImageView caidatBack;
    TextView setting_user,setting_email;
    RelativeLayout Dangxuat,rlThaydoimk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxa();

        Cursor cursor = new dbmanager(this).readalldatausers();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String hovaten = cursor.getString(1);
            String email = cursor.getString(2);
            int sodienthoai = cursor.getInt(3);
           String password = cursor.getString(4);
            setting_user.setText(hovaten);
            setting_email.setText(email);
        }


        rlThaydoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ThayDoiMatKhau.class);
                startActivity(intent1);
            }
        });

        Dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        caidatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        caidatBack= findViewById(R.id.caidatBack);
        Dangxuat = findViewById(R.id.Dangxuat);
        setting_user=findViewById(R.id.setting_user);
        setting_email=findViewById(R.id.setting_email);
        rlThaydoimk= findViewById(R.id.rlThaydoimk);
    }
}