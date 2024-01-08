package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tt_app.Adapter.DetailHinhanhAdapter;
import com.example.tt_app.Adapter.PhotoAdapter;
import com.example.tt_app.Adapter.adapter_nguoithue;
import com.example.tt_app.Adapter.UpdatePhotoAdapter;
import com.example.tt_app.Adapter.myadapter;
import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.example.tt_app.model.DataClass;
import com.example.tt_app.model.DataNguoithue;
import com.example.tt_app.model.DataPhong;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class DetailNguoithue extends AppCompatActivity {

    TextView detailHovaten,detailEmail,detailNgaysinh,detailSodienthoai,detailPhong,detailCmnd,detailNgaycap,detailNoicap,detailDiachi;
    ImageView detailAddhinhcmnd,ThongtinnguoithueBack,detailHinh,imgEdit;

    int sodienthoai,id_dichvu,cmnd;
    String anhcmnd;

    RecyclerView detailRcfhinh;
    Button detailXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nguoithue);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id_dichvu = bundle.getInt("idnguoithue");
            detailHovaten.setText(bundle.getString("hovaten"));
            sodienthoai = bundle.getInt("sodienthoai");
            detailPhong.setText(bundle.getString("chonphong"));
            detailEmail.setText(bundle.getString("email"));
            detailNgaysinh.setText(bundle.getString("ngaysinh"));
            cmnd = bundle.getInt("cmnd");
            detailNgaycap.setText(bundle.getString("ngaycap"));
            detailNoicap.setText(bundle.getString("noicap"));
            detailDiachi.setText(bundle.getString("diachi"));
            anhcmnd = bundle.getString("anhcm");
        }

            List<String> imagePaths = Arrays.asList(anhcmnd.split(","));
            List<String> mutableImagePathList = new ArrayList<>(imagePaths);

        for (int i = 0; i < mutableImagePathList.size(); i++) {
//            String imagePath = mutableImagePathList.get(1);
            // Thực hiện xử lý với imagePath
        }
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
            detailRcfhinh.setLayoutManager(gridLayoutManager);
            detailRcfhinh.setFocusable(false);
            UpdatePhotoAdapter adapter = new UpdatePhotoAdapter(mutableImagePathList,this);
            detailRcfhinh.setAdapter(adapter);


            detailSodienthoai.setText(sodienthoai + "");
            detailCmnd.setText(cmnd + "");

            backtoNguoithue();
            deleteNguoithue();
            EditNguoithue();
        }

    private void backtoNguoithue() {
        ThongtinnguoithueBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailNguoithue.this, NguoiThue.class);
                startActivity(intent);
            }
        });


    }

    private void EditNguoithue() {
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailNguoithue.this, UpdateNguoithue.class)
                        .putExtra("idnguoithue",id_dichvu)
                        .putExtra("hovaten", detailHovaten.getText().toString())
                        .putExtra("sodienthoai", sodienthoai)
                        .putExtra("chonphong", detailPhong.getText().toString())
                        .putExtra("email", detailEmail.getText().toString())
                        .putExtra("ngaysinh", detailNgaysinh.getText().toString())
                        .putExtra("cmnd", cmnd)
                        .putExtra("ngaycap", detailNgaycap.getText().toString())
                        .putExtra("noicap", detailNoicap.getText().toString())
                        .putExtra("diachi", detailDiachi.getText().toString())
                        .putExtra("anhcm", anhcmnd);
                startActivity(intent);
            }
        });
    }


    private void deleteNguoithue() {
        detailXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager db = new dbmanager(getApplicationContext());
                db.DeleteNguoithue(new DataNguoithue(id_dichvu,null,null,null,null,null,null,null,null,null,null,null));

                Intent intent = new Intent(DetailNguoithue.this,NguoiThue.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        ThongtinnguoithueBack= findViewById(R.id.ThongtinnguoithueBack);
        detailHovaten= findViewById(R.id.detailHovaten);
        detailEmail= findViewById(R.id.detailEmail);
        detailNgaysinh= findViewById(R.id.detailNgaysinh);
        detailSodienthoai= findViewById(R.id.detailSodienthoai);
        detailPhong= findViewById(R.id.detailPhong);
        detailCmnd= findViewById(R.id.detailCmnd);
        detailNgaycap= findViewById(R.id.detailNgaycap);
        detailNoicap= findViewById(R.id.detailNoicap);
        detailDiachi= findViewById(R.id.detailDiachi);
        detailRcfhinh= findViewById(R.id.detailRcfhinh);
        detailAddhinhcmnd= findViewById(R.id.detailAddhinhcmnd);
        detailHinh = findViewById(R.id.detailHinh);
        detailXoa = findViewById(R.id.detailXoa);
        imgEdit = findViewById(R.id.imgEdit);
    }

}