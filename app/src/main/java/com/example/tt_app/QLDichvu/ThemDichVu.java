package com.example.tt_app.QLDichvu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;

public class ThemDichVu extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    TextInputLayout uploadTopic, uploadDonvido, uploadNote, uploadDichvu;
    Uri uri;

    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dich_vu);
        AnhxaId();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm Dịch Vụ"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // lấy hình ảnh từ trong thiết bị điện thoại
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(ThemDichVu.this, "Không có hình ảnh được chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!validateTendichvu() | !validatedonvi() | !validatepriceDichvu()) {
                    return;
                }
                String inputname = uploadTopic.getEditText().getText().toString();
                String inputdonvi= uploadDonvido.getEditText().getText().toString();
                String inputdichvu= uploadDichvu.getEditText().getText().toString();

                //chuyen imageview sang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) uploadImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);

                byte[] uploadhinhanh =byteArray.toByteArray();
                dbmanager db = new dbmanager(getApplicationContext());

                db.insertData_dichvu(inputname, inputdonvi, Integer.parseInt(inputdichvu), uploadNote.getEditText().getText().toString(),uploadhinhanh);
                startActivity(new Intent(getApplicationContext(), DichVu.class));
            }
        });

    }
    private boolean validateTendichvu() {
        String usernameInput = uploadTopic.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            uploadTopic.setError("Trường không thể trống\n");
            return false;
        }else if (usernameInput.matches(".*\\d+.*")) {
            uploadTopic.setError("Tên dịch vụ không thể chứa số");
            return false;
        } else {
            uploadTopic.setError(null);
            return true;
        }
    }
    private boolean validatedonvi() {
        String DonviInput = uploadDonvido.getEditText().getText().toString().trim();

        if (DonviInput.isEmpty()) {
            uploadDonvido.setError("Trường không thể trống\n");
            return false;
        } else {
            uploadDonvido.setError(null);
            return true;
        }
    }
    private boolean validatepriceDichvu() {
        String DichvuInput = uploadDichvu.getEditText().getText().toString().trim();

        if (DichvuInput.isEmpty()) {
            uploadDichvu.setError("Trường không thể trống\n");
            return false;
        } else {
            uploadDichvu.setError(null);
            return true;
        }
    }

    private void AnhxaId() {
        uploadImage = findViewById(R.id.uploadImage);
        uploadDonvido = findViewById(R.id.uploadDonvido);
        uploadTopic = findViewById(R.id.uploadTopic);
        uploadDichvu = findViewById(R.id.uploadDichvu);
        uploadNote = findViewById(R.id.uploadNote);
        saveButton = findViewById(R.id.saveButton);
    }

    //quay về
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}