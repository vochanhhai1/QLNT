package com.example.tt_app.QLNguoithue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tt_app.Adapter.DetailHinhanhAdapter;
import com.example.tt_app.Adapter.PhotoAdapter;
import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.model.DataNguoithue;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;


public class UpdateNguoithue extends AppCompatActivity {
    TextInputLayout updateHovaten, updateSodienthoai, updateEmail, updateNgaysinh, updateCmnd, updateNgaycap, updateNoicap, updateDiachi;
    int updatecmnd,updateIdnguoithue,updatePhone,updateMaphong;
    AutoCompleteTextView updateChonphong;
    String updateAnhcm;
    RecyclerView rcfupdateHinhcmnd;
    ImageView updateimgAnhcmnd,updateimgSodienthoai,datapickerupdateNgaycap,datapickerupdateNgaysinh;
    Button btnSua;
    private PhotoAdapter photoAdapter;
    private static final int CONTACT_PICK_REQUEST = 1;

    private List<String> items = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String imagePaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nguoithue);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxaid();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            updateIdnguoithue = bundle.getInt("idnguoithue");
            updateHovaten.getEditText().setText(bundle.getString("hovaten"));
            updatePhone = bundle.getInt("sodienthoai");
            updateChonphong.setText(bundle.getString("chonphong"));
            updateEmail.getEditText().setText(bundle.getString("email"));
            updateNgaysinh.getEditText().setText(bundle.getString("ngaysinh"));
            updatecmnd = bundle.getInt("cmnd");
            updateNgaycap.getEditText().setText(bundle.getString("ngaycap"));
            updateNoicap.getEditText().setText(bundle.getString("noicap"));
            updateDiachi.getEditText().setText(bundle.getString("diachi"));
            updateAnhcm = bundle.getString("anhcm");

//            Toast.makeText(this,""+ updateMaphong,Toast.LENGTH_SHORT).show();
        }

        updateCmnd.getEditText().setText(updatecmnd+"");
        updateSodienthoai.getEditText().setText(updatePhone+"");


        //list chon phong
        Cursor cursor = new dbmanager(this).readTenPhong();
        if (cursor.moveToFirst()) {
            do {
                // Lấy giá trị tên từ cột "name"
                String name = cursor.getString(cursor.getColumnIndex("phong"));
                items.add(name);
            } while (cursor.moveToNext());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        updateChonphong.setAdapter(adapter);

        List<String> imagePaths = Arrays.asList(updateAnhcm.split(","));
        List<String> mutableImagePathList = new ArrayList<>(imagePaths);

        for (int i = 0; i < mutableImagePathList.size(); i++) {
//            String imagePath = mutableImagePathList.get(1);
            // Thực hiện xử lý với imagePath
        }
        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        rcfupdateHinhcmnd.setLayoutManager(gridLayoutManager);
        rcfupdateHinhcmnd.setFocusable(false);
        DetailHinhanhAdapter adapter = new DetailHinhanhAdapter(mutableImagePathList, this);
        rcfupdateHinhcmnd.setAdapter(adapter);




        updateimgAnhcmnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions();

            }
        });
        updateimgSodienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts();
            }
        });


        //date picker

        datapickerupdateNgaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogNgaySinh();
            }
        });
        datapickerupdateNgaycap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogNgayCap();
            }
        });
    }
    private void showDatePickerDialogNgaySinh() {
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(UpdateNguoithue.this, style, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear1, int selectedMonth1, int selectedDay1) {
                // Xử lý ngày tháng năm đã chọn ở đây
                String selectedDate1 = selectedDay1 + "-" + (selectedMonth1 + 1) + "-" + selectedYear1;
                // Hiển thị ngày tháng năm đã chọn
                updateNgaysinh.getEditText().setText(selectedDate1);

            }
        }, year1, month1, day1);

        // Hiển thị hộp thoại chọn ngày tháng năm
        datePickerDialog1.show();
    }

    private void showDatePickerDialogNgayCap() {
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateNguoithue.this, style, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                // Xử lý ngày tháng năm đã chọn ở đây
                String selectedDate = selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear;
                // Hiển thị ngày tháng năm đã chọn
                updateNgaycap.getEditText().setText(selectedDate);
            }
        }, year, month, day);

        // Hiển thị hộp thoại chọn ngày tháng năm
        datePickerDialog.show();
    }

    private void openContacts() {
        Intent contactsIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactsIntent, CONTACT_PICK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK) {
            Cursor cursor = null;
            try {
                if (data != null) {
                    // Đọc dữ liệu từ kết quả trả về
                    Uri uri = data.getData();
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // Hiển thị số điện thoại lên TextView
                        updateSodienthoai.getEditText().setText(phoneNumber);
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
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
                Toast.makeText(UpdateNguoithue.this, "Quyền bị từ chối\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("Nếu bạn từ chối quyền, bạn không thể sử dụng dịch vụ này\n\nVui lòng bật quyền tại [Cài đặt] > [Quyền]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
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
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbmanager db = new dbmanager(getApplicationContext());
                        db.UpdateNguoithue(new DataNguoithue(updateIdnguoithue,
                                updateHovaten.getEditText().getText().toString(),
                                updatePhone,
                                updateChonphong.getText().toString(),
                                updateEmail.getEditText().getText().toString(),
                                updateNgaysinh.getEditText().getText().toString(),
                                updatecmnd,
                                updateNgaycap.getEditText().getText().toString(),
                                updateNoicap.getEditText().getText().toString(),
                                updateDiachi.getEditText().getText().toString(),
                                imagePaths,Integer.parseInt(updateChonphong.getText().toString())));
                        Intent intent = new Intent(UpdateNguoithue.this, NguoiThue.class);
                        startActivity(intent);
                    }
                });
                Toast.makeText(UpdateNguoithue.this, "Bạn đã chọn ảnh thành công", Toast.LENGTH_SHORT).show();

                photoAdapter.setData(uriList);
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(UpdateNguoithue.this)
                .setOnMultiImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }
    private void anhxaid() {
        updateHovaten = findViewById(R.id.updateHovaten);
        updateSodienthoai = findViewById(R.id.updateSodienthoai);
        updateChonphong = findViewById(R.id.updateChonphong);
        updateEmail = findViewById(R.id.updateEmail);
        updateNgaysinh = findViewById(R.id.updateNgaysinh);
        updateCmnd = findViewById(R.id.updateCmnd);
        updateNgaycap = findViewById(R.id.updateNgaycap);
        updateNoicap = findViewById(R.id.updateNoicap);
        updateDiachi = findViewById(R.id.updateDiachi);
        rcfupdateHinhcmnd= findViewById(R.id.rcfupdateHinhcmnd);
        updateimgAnhcmnd= findViewById(R.id.updateimgAnhcmnd);
        btnSua= findViewById(R.id.btnSua);
        updateimgSodienthoai= findViewById(R.id.updateimgSodienthoai);
        datapickerupdateNgaysinh = findViewById(R.id.datapickerupdateNgaysinh);
        datapickerupdateNgaycap = findViewById(R.id.datapickerupdateNgaycap);
    }
}