package com.example.tt_app.QLNguoithue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tt_app.Adapter.PhotoAdapter;
import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.google.android.material.textfield.TextInputLayout;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import gun0912.tedbottompicker.TedBottomPicker;

public class ThemNguoiThue extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");


    TextInputLayout hovaten, sodienthoai, email, ngaysinh, cmnd, ngaycap, noicap, diachi, anhcmnd;
    Uri uri;
    ImageView imgAnhcmnd, datapickerNgaysinh, datapickerNgaycap,imgSodienthoai;
    Button btnThemnguoithue;
    RecyclerView rcfHinhcmnd;

    AutoCompleteTextView chonphong;
    private PhotoAdapter photoAdapter;
    private static final int CONTACT_PICK_REQUEST = 1;
    private static final int READ_CONTACTS_PERMISSION_REQUEST = 1;
    private List<String> items = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String imagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_thue);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxaid();
        //add multil hinh


        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        rcfHinhcmnd.setLayoutManager(gridLayoutManager);
        rcfHinhcmnd.setFocusable(false);
        rcfHinhcmnd.setAdapter(photoAdapter);



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
        chonphong.setAdapter(adapter);


        btnThemnguoithue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validateUsername()) {
                    return;
                }
                String inputemail = email.getEditText().getText().toString();
                String inputhovaten= hovaten.getEditText().getText().toString();

                dbmanager db = new dbmanager(getApplicationContext());
                startActivity(new Intent(getApplicationContext(), NguoiThue.class));

                db.insertData_nguoithue(inputhovaten, Integer.parseInt(sodienthoai.getEditText().getText().toString()), chonphong.getText().toString(),inputemail, ngaysinh.getEditText().getText().toString()
                        , Integer.parseInt(cmnd.getEditText().getText().toString()), ngaycap.getEditText().getText().toString(), noicap.getEditText().getText().toString(), diachi.getEditText().getText().toString(), imagePaths);

            }
        });

        imgAnhcmnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });

        //date picker


        datapickerNgaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogNgaySinh();
            }
        });
        datapickerNgaycap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogNgayCap();
            }
        });

        imgSodienthoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndReadContacts();
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
        DatePickerDialog datePickerDialog1 = new DatePickerDialog(ThemNguoiThue.this, style, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear1, int selectedMonth1, int selectedDay1) {
                // Xử lý ngày tháng năm đã chọn ở đây
                String selectedDate1 = selectedDay1 + "-" + (selectedMonth1 + 1) + "-" + selectedYear1;
                // Hiển thị ngày tháng năm đã chọn
                ngaysinh.getEditText().setText(selectedDate1);

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
        DatePickerDialog datePickerDialog = new DatePickerDialog(ThemNguoiThue.this, style, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                String selectedDate = selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear;

                ngaycap.getEditText().setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
    private void checkPermissionAndReadContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_PERMISSION_REQUEST);
        } else {
            openContacts();
        }
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
                        sodienthoai.getEditText().setText(phoneNumber);
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_CONTACTS_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Người dùng đã cấp quyền, tiếp tục với việc lấy dữ liệu danh bạ
                openContacts();
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
                Toast.makeText(ThemNguoiThue.this, "Quyền bị từ chối\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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

                photoAdapter.setData(uriList);
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(ThemNguoiThue.this)
                .setOnMultiImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }
    public  boolean validateEmail()
    {
        String validateUser = email.getEditText().getText().toString().trim();

        if (validateUser.isEmpty())
        {
                email.setError("Trường không thể trống\n");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(validateUser).matches()) {
            email.setError("Hãy nhập đúng email của bạn");
            return false;
        } else
        {
            email.setError(null);
            return true;
        }

    }

    private boolean validateUsername() {
        String usernameInput = hovaten.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            hovaten.setError("Trường không thể trống\n");
            return false;
        } else if (usernameInput.length() > 15) {
            hovaten.setError("Tên người dùng quá dài");
            return false;
        }else if (usernameInput.matches(".*\\d+.*")) {
                hovaten.setError("Tên người dùng không thể chứa số");
                return false;
        } else {
            hovaten.setError(null);
            return true;
        }
    }
    private void anhxaid() {
        hovaten = findViewById(R.id.hovaten);
        sodienthoai = findViewById(R.id.sodienthoai);
        chonphong = findViewById(R.id.chonphong);
        email = findViewById(R.id.email);
        ngaysinh = findViewById(R.id.ngaysinh);
        cmnd = findViewById(R.id.cmnd);
        ngaycap = findViewById(R.id.ngaycap);
        noicap = findViewById(R.id.noicap);
        diachi = findViewById(R.id.diachi);
//        anhcmnd= findViewById(R.id.anhcmnd);
        imgAnhcmnd = findViewById(R.id.updateimgAnhcmnd);
        btnThemnguoithue = findViewById(R.id.btnThemnguoithue);
        rcfHinhcmnd = findViewById(R.id.rcfupdateHinhcmnd);
        datapickerNgaysinh = findViewById(R.id.datapickerNgaysinh);
        datapickerNgaycap = findViewById(R.id.datapickerNgaycap);
        imgSodienthoai= findViewById(R.id.imgSodienthoai);
    }

}