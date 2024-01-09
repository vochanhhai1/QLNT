package com.example.tt_app.QLPhong;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_app.QLPhong.DetailPhongFragment.ViewpaperPhongAdapter;
import com.example.tt_app.QLPhong.DetailPhongFragment.NguoiThue;
import com.example.tt_app.QLPhong.DetailPhongFragment.ThongTin;
import com.example.tt_app.R;
import com.google.android.material.tabs.TabLayout;

public class DetailPhong extends AppCompatActivity {

    private TabLayout tablayerDetailPhong;
    ImageView PhongBack, imgEditPhong;
    private ViewPager viewPager;
    private TextView DetailphongName;
    private Integer maphong, chiphithue, dientich, songuoithue, tiencoc, dichvu;
    private String phong, doituong, anhphong, mota, lydo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phong);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxaid();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            maphong = bundle.getInt("maphong");
            phong = bundle.getString("phong");
            chiphithue = bundle.getInt("chiphithue");
            dientich = bundle.getInt("dientich");
            songuoithue = bundle.getInt("songuoithue");
            tiencoc = bundle.getInt("tiencoc");
            doituong = bundle.getString("doituong");
            anhphong = bundle.getString("anhphong");
            mota = bundle.getString("mota");
            lydo = bundle.getString("lydo");
        }

        DetailphongName.setText("Phòng " + phong);
        ViewpaperPhongAdapter viewpaperAdapter = new ViewpaperPhongAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewpaperAdapter);
        tablayerDetailPhong.setupWithViewPager(viewPager);

        PhongBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Phong.class);
                startActivity(intent);
            }
        });

        sendDataToFragment();
        onClickGoToUpdatePhong();
        sendDataToThongtin();
    }

    private void onClickGoToUpdatePhong() {
        imgEditPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPhong.this, UpdatePhong.class)
                        .putExtra("maphong", maphong)
                        .putExtra("phong", phong)
                        .putExtra("chiphithue",chiphithue)
                        .putExtra("dientich", dientich)
                        .putExtra("songuoithue", songuoithue)
                        .putExtra("tiencoc", tiencoc)
                        .putExtra("doituong", doituong)
                        .putExtra("anhphong", anhphong)
                        .putExtra("dichvu", dichvu)
                        .putExtra("mota", mota)
                        .putExtra("lydo", lydo);
                startActivity(intent);
            }
        });

    }

    private void sendDataToFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new NguoiThue());
        fragmentTransaction.commit();
    }

    public void sendDataToThongtin()
    {
//        // Tạo một đối tượng Bundle để chứa dữ liệu
//        Bundle bundle = new Bundle();
//        bundle.putInt("maphong", maphong);
//        bundle.putString("phong", phong);
//        bundle.putInt("chiphithue", chiphithue);
//        bundle.putInt("dientich", dientich);
//        bundle.putInt("songuoithue", songuoithue);
//        bundle.putInt("tiencoc", tiencoc);
//        bundle.putString("doituong", doituong);
//        bundle.putInt("dichvu", dichvu);
//        bundle.putString("mota", mota);
//        bundle.putString("lydo", lydo);
//
//        ThongTin fragment = new ThongTin();
//        fragment.setArguments(bundle);
//
//// Mở Fragment bằng cách thêm vào FragmentManager
//        FragmentManager fragmentManager = getSupportFragmentManager(); // Sử dụng getSupportFragmentManager() nếu bạn đang sử dụng AppCompatActivity
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.viewPager, fragment);
//        transaction.commit();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new ThongTin());
        fragmentTransaction.commit();
    }

    public Integer getMaphong() {
        return maphong;
    }

    public Integer getChiphithue() {
        return chiphithue;
    }

    public Integer getDientich() {
        return dientich;
    }

    public Integer getSonguoithue() {
        return songuoithue;
    }

    public Integer getTiencoc() {
        return tiencoc;
    }

    public Integer getDichvu() {
        return dichvu;
    }

    public String getPhong() {
        return phong;
    }

    public String getDoituong() {
        return doituong;
    }

    public String getAnhphong() {
        return anhphong;
    }

    public String getMota() {
        return mota;
    }

    public String getLydo() {
        return lydo;
    }

    private void anhxaid() {
        tablayerDetailPhong = findViewById(R.id.tablayerDetailPhong);
        PhongBack = findViewById(R.id.PhongBack);
        viewPager = findViewById(R.id.viewPager);
        DetailphongName = findViewById(R.id.DetailphongName);
        imgEditPhong = findViewById(R.id.imgEditPhong);
    }
}