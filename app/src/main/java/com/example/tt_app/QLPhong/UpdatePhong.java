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

import com.example.tt_app.Adapter.DetailHinhanhAdapter;
import com.example.tt_app.QLPhong.Adapter.DichvufromPhongAdapter;
import com.example.tt_app.Adapter.PhotoAdapter;
import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.model.DataClass;
import com.example.tt_app.model.DataPhong;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class UpdatePhong extends AppCompatActivity {
    ImageView themphongBack, updateAnhphong;
    TextInputLayout updateSophong, updateChiphi, updateGioihan, updateDientich, updateTiencoc;
    TextView updateMota, updateLydo;
    Button btnUpdatephong;
    RecyclerView updatercfAnhphong, updatercfDichvu;
    private PhotoAdapter photoAdapter;
    RadioGroup radioGroup;
    String selection = "";
    private List<DataClass> DichvutList;
    private DichvufromPhongAdapter dichvufromPhongAdapter;
    Integer maphong, chiphi,dientich,tiencoc,songuoithue;
    String sophong,anh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phong);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxaid();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            maphong = bundle.getInt("maphong");
            sophong = bundle.getString("phong");
            chiphi = bundle.getInt("chiphithue");
            dientich = bundle.getInt("dientich");
            tiencoc = bundle.getInt("tiencoc");
            songuoithue = bundle.getInt("songuoithue");
            updateMota.setText(bundle.getString("mota"));
            updateLydo.setText(bundle.getString("lydo"));
            anh = bundle.getString("anhphong");
        }
        updateGioihan.getEditText().setText(""+songuoithue);
        updateDientich.getEditText().setText(""+dientich);
        updateTiencoc.getEditText().setText(""+tiencoc);
        updateChiphi.getEditText().setText(""+chiphi);
        updateSophong.getEditText().setText(sophong + "");
        Toast.makeText(this,""+sophong,Toast.LENGTH_SHORT).show();


        List<String> imagePaths = Arrays.asList(anh.split(","));
        List<String> mutableImagePathList = new ArrayList<>(imagePaths);

        for (int i = 0; i < mutableImagePathList.size(); i++) {
//            String imagePath = mutableImagePathList.get(1);
            // Thực hiện xử lý với imagePath
        }
        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        updatercfAnhphong.setLayoutManager(gridLayoutManager);
        updatercfAnhphong.setFocusable(false);
        DetailHinhanhAdapter adapter = new DetailHinhanhAdapter(mutableImagePathList, this);
        updatercfAnhphong.setAdapter(adapter);

        //add dichvu
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        updatercfDichvu.setLayoutManager(gridLayoutManager1);


        Cursor cursor = new dbmanager(this).readalldata();
        DichvutList = new ArrayList<>();


        while (cursor.moveToNext()) {
            DataClass obj = new DataClass(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getBlob(5));
            DichvutList.add(obj);
        }
        dichvufromPhongAdapter = new DichvufromPhongAdapter(DichvutList);
        updatercfDichvu.setAdapter(dichvufromPhongAdapter);

        updateAnhphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });
    }

    private void requestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openBottomPicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(UpdatePhong.this, "Quyền bị từ chối\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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

                btnUpdatephong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Phong.class));
                        dbmanager db = new dbmanager(getApplicationContext());
                        String imagePaths = imagePathBuilder.toString();

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
                            db.updateDataPhong(new DataPhong(maphong,sophong,  Double.valueOf(String.valueOf(updateChiphi.getEditText().getText())),
                                    Integer.valueOf(String.valueOf(updateDientich.getEditText().getText())),Integer.valueOf(String.valueOf(updateGioihan.getEditText().getText())),
                                    Double.valueOf(String.valueOf(updateTiencoc.getEditText().getText())), selection, imagePaths,
                                    Integer.valueOf(datanuoc),Integer.valueOf(datadien),updateMota.getText().toString(), updateLydo.getText().toString()));
                    }
                });
                photoAdapter.setData(uriList);
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(UpdatePhong.this)
                .setOnMultiImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }

    private void anhxaid() {
        themphongBack = findViewById(R.id.themphongBack);
        updateSophong = findViewById(R.id.updateSoPhong);
        updateChiphi = findViewById(R.id.updateChiphi);
        updateMota = findViewById(R.id.updateMota);
        updateGioihan = findViewById(R.id.updateGioihan);
        updateDientich = findViewById(R.id.updateDientich);
        updateTiencoc = findViewById(R.id.updateTiencoc);
        updateLydo = findViewById(R.id.updateLydo);
        updatercfAnhphong = findViewById(R.id.updatercfAnhphong);
        btnUpdatephong = findViewById(R.id.btnUpdatephong);
        updateAnhphong = findViewById(R.id.updateAnhphong);
        radioGroup = findViewById(R.id.radiogroup);
        updatercfDichvu = findViewById(R.id.updatercfDichvu);
    }
}