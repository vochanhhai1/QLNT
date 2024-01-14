package com.example.tt_app.QLPhong;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_app.QLPhong.Adapter.DichvufromPhongAdapter;
import com.example.tt_app.Adapter.PhotoAdapter;
import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.model.DataClass;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class Themphong extends AppCompatActivity {
    ImageView themphongBack,updateAnhphong;
    TextInputLayout sophong,chiphi,gioihan,dientich,tiencoc;
    TextView mota,lydo;
    Button btnThemphong;
    RecyclerView rcfAnhphong,rcfupdateDichvu;
    private PhotoAdapter photoAdapter;
    RadioGroup radioGroup;
    String selection = "";
    private List<DataClass> DichvutList;
    private DichvufromPhongAdapter dichvufromPhongAdapter;
    private String imagePaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themphong);
        anhxaid();
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //add multil hinh
        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        rcfAnhphong.setLayoutManager(gridLayoutManager);
        rcfAnhphong.setFocusable(false);
        rcfAnhphong.setAdapter(photoAdapter);

        //add dichvu

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        rcfupdateDichvu.setLayoutManager(gridLayoutManager1);


        Cursor cursor = new dbmanager(this).readalldata();
        DichvutList = new ArrayList<>();


        while (cursor.moveToNext()) {
            DataClass obj = new DataClass(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getBlob(5));
            DichvutList.add(obj);
        }
        dichvufromPhongAdapter = new DichvufromPhongAdapter(DichvutList);
        rcfupdateDichvu.setAdapter(dichvufromPhongAdapter);

        themphongBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Themphong.this, Phong.class);
                startActivity(intent);
            }
        });

        updateAnhphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });

        btnThemphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!validateSoPhong() | !validateChiPhi() | !validateTiencoc()) {
                    return;
                }
                String inputSophong = sophong.getEditText().getText().toString();
                String inputhchiphi= chiphi.getEditText().getText().toString();
                String inputhtiencoc= tiencoc.getEditText().getText().toString();
                startActivity(new Intent(getApplicationContext(), Phong.class));
                dbmanager db = new dbmanager(getApplicationContext());

                String datanuoc = "";
                String datadien = "";
                List<DataClass> stList = dichvufromPhongAdapter.getStudentist();
                for (int i = 0; i < stList.size(); i++) {
                    DataClass singleStudent = stList.get(0);
                    DataClass singleStudent1 = stList.get(1);
                    if (singleStudent.isSelected() == true) {
                        datanuoc = singleStudent.getUploadDichvu().toString();
                    }else
                        datanuoc= String.valueOf(0);
                    if (singleStudent1.isSelected() == true)
                    {
                        datadien = singleStudent1.getUploadDichvu().toString();
                    }else
                        datadien= String.valueOf(0);
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton radioButton = findViewById(selectedId);
                    selection = radioButton.getText().toString();
                }
                db.insertDataPhong(inputSophong, Integer.valueOf(inputhchiphi),
                        Integer.valueOf(dientich.getEditText().getText().toString()), Integer.valueOf(gioihan.getEditText().getText().toString()),
                        Integer.valueOf(inputhtiencoc), selection, imagePaths, mota.getText().toString(), lydo.getText().toString(),Integer.valueOf(datanuoc), Integer.valueOf(datadien));
            }

        });

    }

    private boolean validateSoPhong() {
        String soPhongInput = sophong.getEditText().getText().toString().trim();

        if (soPhongInput.isEmpty()) {
            sophong.setError("Trường không thể trống\n");
            return false;
        } else if (soPhongInput.length() > 15) {
            sophong.setError("Tên phòng quá dài");
            return false;
        } else {
            sophong.setError(null);
            return true;
        }
    }
    private boolean validateChiPhi() {
        String ChiphiInput = chiphi.getEditText().getText().toString().trim();

        if (ChiphiInput.isEmpty()) {
            chiphi.setError("Trường không thể trống\n");
            return false;
        } else {
            chiphi.setError(null);
            return true;
        }
    }

    private boolean validateTiencoc() {
        String TienconInput = tiencoc.getEditText().getText().toString().trim();

        if (TienconInput.isEmpty()) {
            tiencoc.setError("Trường không thể trống\n");
            return false;
        } else {
            tiencoc.setError(null);
            return true;
        }
    }
    private void requestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openBottomPicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Themphong.this, "Quyền bị từ chối\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("Nếu bạn từ chối quyền, bạn không thể sử dụng dịch vụ này\n\nVui lòng bật quyền tại [Cài đặt] > [Quyền]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,  Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openBottomPicker() {
        TedBottomPicker.OnMultiImageSelectedListener listener = new TedBottomPicker.OnMultiImageSelectedListener() {
            @Override
            public void onImagesSelected(ArrayList<Uri> uriList) {
                StringBuilder imagePathBuilder = new StringBuilder();
                for (Uri uri : uriList) {
                    String imagePath = uri.toString();
                    imagePathBuilder.append(imagePath).append(",");
                }
                imagePaths = imagePathBuilder.toString();


                photoAdapter.setData(uriList);
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(Themphong.this)
                .setOnMultiImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }
    private void anhxaid() {
        themphongBack= findViewById(R.id.themphongBack);
        sophong= findViewById(R.id.sophong);
        chiphi= findViewById(R.id.chiphi);
        mota= findViewById(R.id.mota);
        gioihan= findViewById(R.id.gioihan);
        dientich= findViewById(R.id.dientich);
        tiencoc= findViewById(R.id.tiencoc);
        lydo= findViewById(R.id.lydo);
        rcfAnhphong= findViewById(R.id.rcfAnhphong);
        btnThemphong = findViewById(R.id.btnThemphong);
        updateAnhphong= findViewById(R.id.updateAnhphong);
        radioGroup= findViewById(R.id.radiogroup);
        rcfupdateDichvu = findViewById(R.id.rcfupdateDichvu);
    }
}