package com.example.tt_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tt_app.QLDichvu.DichVu;
import com.example.tt_app.QLHoadon.HoaDon;
import com.example.tt_app.QLHopdong.Hopdong;
import com.example.tt_app.QLNguoithue.NguoiThue;
import com.example.tt_app.QLPhong.Phong;
import com.example.tt_app.QLCaidat.Setting;
import com.example.tt_app.Database.dbmanager;

public class MainActivity extends AppCompatActivity {
    //khai bao bien
    TextView textView;
    ImageView imgDichvu;

    RelativeLayout rlDichvu,rlSetting,rlHoadon,rlHopdong,rlNguoithue,rlPhong;

//    String user,email,phone,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxaid();

//        CreateBottomNavigation();

//        LoginFirebase();

        OpenDichvu();

        OpenSetting();

        OpenHoadon();

        OpenHopdong();
        OpenPhong();

        OpenNguoithue();
        //hien thi ten user
       readuser();

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    private void readuser() {
        Cursor cursor = new dbmanager(getApplicationContext()).readalldatausers();
        if (cursor.moveToFirst()) {
            do {
                String hovaten = cursor.getString(cursor.getColumnIndex("hovaten"));
                textView.setText(hovaten);
            } while (cursor.moveToNext());
        }
    }

    private void OpenPhong() {
        rlPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phong = new Intent(getApplicationContext(), Phong.class);
                startActivity(phong);
            }
        });
    }

    private void OpenHopdong() {
        rlHopdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hopdong = new Intent(getApplicationContext(), Hopdong.class);
                startActivity(hopdong);
            }
        });
    }

    private void OpenHoadon() {
        rlHoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Xác nhận thoát");
//                builder.setIcon(R.drawable.baseline_info_24);
//                builder.setMessage("Tính năng hóa đơn chưa được ra mắt!!");
//
//                builder.setPositiveButton("Có chứ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog dialog =builder.create();
//                dialog.show();
                Intent hoadon = new Intent(getApplicationContext(), HoaDon.class);
                startActivity(hoadon);
            }
    });

    }

    private void OpenSetting() {
        rlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(getApplicationContext(), Setting.class);
                startActivity(setting);
            }
        });
    }

    private void OpenNguoithue() {
        rlNguoithue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nguoithue = new Intent(getApplicationContext(), NguoiThue.class);
                startActivity(nguoithue);
            }
        });
    }

    private void OpenDichvu() {
        rlDichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dichvu = new Intent(getApplicationContext(), DichVu.class);
                startActivity(dichvu);
            }
        });
    }

//    private void LoginFirebase() {
//        if(user==null){
//            Intent intent = new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//            finish();
//        }else {
//            textView.setText(user.getEmail());
//        }
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }

//    private void CreateBottomNavigation() {
//        bottomNavigation.show(3, true);
//        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.setting));
//        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.person));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_home_24));
//        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.apartment));
//        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.wallet));
//
//        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()) {
//                    case 1:
//                        setting.setVisibility(View.VISIBLE);
//                        home.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        //Màu thanh điều hướng
//                        getWindow().setNavigationBarColor(Color.parseColor("#1E88E5"));
//                        bottomNavigation.setBackgroundBottomColor(Color.parseColor("#1E88E5"));
//                        //màu thanh trạng thái
//                        getWindow().setStatusBarColor(Color.parseColor("#1E88E5"));
//
//                        main.setBackgroundColor(Color.parseColor("#0EE1CC"));
//                        break;
//                    case 2:
//                        setting.setVisibility(View.GONE);
//                        person.setVisibility(View.VISIBLE);
//                        home.setVisibility(View.GONE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        //Màu thanh điều hướng
//                        getWindow().setNavigationBarColor(Color.parseColor("#F44336"));
//                        bottomNavigation.setBackgroundBottomColor(Color.parseColor("#F44336"));
//                        //màu thanh trạng thái
//                        getWindow().setStatusBarColor(Color.parseColor("#F44336"));
//
//                        main.setBackgroundColor(Color.parseColor("#3949AB"));
//                        break;
//                    case 3:
//                        setting.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        home.setVisibility(View.VISIBLE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        //Màu thanh điều hướng
//                        getWindow().setNavigationBarColor(Color.parseColor("#ff6f00"));
//                        bottomNavigation.setBackgroundBottomColor(Color.parseColor("#ff6f00"));
//                        //màu thanh trạng thái
//                        getWindow().setStatusBarColor(Color.parseColor("#ff6f00"));
//
//                        main.setBackgroundColor(Color.parseColor("#ffffff"));
//                        break;
//
//                    case 4:
//                        setting.setVisibility(View.GONE);
//                        home.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        apartment.setVisibility(View.VISIBLE);
//                        wallet.setVisibility(View.GONE);
//                        //Màu thanh điều hướng
//                        getWindow().setNavigationBarColor(Color.parseColor("#ff6f00"));
//                        bottomNavigation.setBackgroundBottomColor(Color.parseColor("#ff6f00"));
//                        //màu thanh trạng thái
//                        getWindow().setStatusBarColor(Color.parseColor("#ff6f00"));
//
//                        main.setBackgroundColor(Color.parseColor("#8E24AA"));
//                        break;
//                    case 5:
//                        setting.setVisibility(View.GONE);
//                        home.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.VISIBLE);
//                        //Màu thanh điều hướng
//                        getWindow().setNavigationBarColor(Color.parseColor("#ff6f00"));
//                        bottomNavigation.setBackgroundBottomColor(Color.parseColor("#ff6f00"));
//                        //màu thanh trạng thái
//                        getWindow().setStatusBarColor(Color.parseColor("#ff6f00"));
//
//                        main.setBackgroundColor(Color.parseColor("#0EE1CC"));
//                        break;
//                }
//                return null;
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()) {
//                    case 1:
//                        setting.setVisibility(View.VISIBLE);
//                        home.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        break;
//                }
//                return null;
//            }
//        });
//
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()) {
//                    case 2:
//                        setting.setVisibility(View.GONE);
//                        person.setVisibility(View.VISIBLE);
//                        home.setVisibility(View.GONE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        break;
//                }
//                return null;
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()) {
//                    case 3:
//                        setting.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        home.setVisibility(View.VISIBLE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        break;
//                }
//                return null;
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()) {
//                    case 4:
//                        setting.setVisibility(View.GONE);
//                        home.setVisibility(View.GONE);
//                        person.setVisibility(View.VISIBLE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.GONE);
//                        break;
//                }
//                return null;
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()) {
//                    case 5:
//                        setting.setVisibility(View.GONE);
//                        home.setVisibility(View.GONE);
//                        person.setVisibility(View.GONE);
//                        apartment.setVisibility(View.GONE);
//                        wallet.setVisibility(View.VISIBLE);
//                        break;
//                }
//                return null;
//            }
//        });
//    }

    private void anhxaid() {
        textView = findViewById(R.id.user);

//        bottomNavigation = findViewById(R.id.bottomNavigation);
//        setting = findViewById(R.id.setting);
//        home = findViewById(R.id.home);
//        person = findViewById(R.id.person);
//        apartment = findViewById(R.id.apartment);
//        wallet = findViewById(R.id.wallet);
//        main = findViewById(R.id.main);
        rlSetting=findViewById(R.id.rlSetting);
        rlDichvu = findViewById(R.id.rlDichvu);
        rlHoadon= findViewById(R.id.rlHoadon);
        rlHopdong=findViewById(R.id.rlHopdong);
        rlNguoithue = findViewById(R.id.rlNguoithue);
        rlPhong = findViewById(R.id.rlPhong);
    }
}