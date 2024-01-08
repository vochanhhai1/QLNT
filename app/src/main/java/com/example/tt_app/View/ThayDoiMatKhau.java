package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tt_app.R;

public class ThayDoiMatKhau extends AppCompatActivity {

    EditText currentPassword,newPassword,confirmPassword;
    Button changePassword;
    ImageView caidatBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_mat_khau);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxa();

        dbmanager db = new dbmanager(this);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password_cu = currentPassword.getText().toString();
                String password_moi = newPassword.getText().toString();
                String repassword_moi = confirmPassword.getText().toString();
                Cursor cursor = new dbmanager(getApplicationContext()).readalldatausers();

                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String password = cursor.getString(4);
                    if (password_cu.equals("") || password_moi.equals("") || repassword_moi.equals(""))
                        Toast.makeText(ThayDoiMatKhau.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    else {
                        if (password.equals(password_cu)) {
                            if (password_moi.equals(repassword_moi)) {
                                Boolean update = db.updatePassword(id, password_moi);
                                if (update == true) {
                                    Toast.makeText(ThayDoiMatKhau.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Setting.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ThayDoiMatKhau.this, "Đổi mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ThayDoiMatKhau.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ThayDoiMatKhau.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        caidatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        }

    private void anhxa() {
        currentPassword=findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        changePassword= findViewById(R.id.changePassword);
        caidatBack = findViewById(R.id.caidatBack);
    }
}