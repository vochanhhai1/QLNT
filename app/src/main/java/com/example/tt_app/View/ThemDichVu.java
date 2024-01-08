package com.example.tt_app.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tt_app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
                startActivity(new Intent(getApplicationContext(), DichVu.class));
                //chuyen imageview sang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) uploadImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);

                byte[] uploadhinhanh =byteArray.toByteArray();

                processinsert(uploadTopic.getEditText().getText().toString(), uploadDonvido.getEditText().getText().toString(), Integer.parseInt(uploadDichvu.getEditText().
                        getText().toString()), uploadNote.getEditText().getText().toString(),uploadhinhanh);
            }
        });

    }

    private void AnhxaId() {
        uploadImage = findViewById(R.id.uploadImage);
        uploadDonvido = findViewById(R.id.uploadDonvido);
        uploadTopic = findViewById(R.id.uploadTopic);
        uploadDichvu = findViewById(R.id.uploadDichvu);
        uploadNote = findViewById(R.id.uploadNote);
        saveButton = findViewById(R.id.saveButton);
    }

    private void processinsert(String t, String d, int v, String n, byte[] h) {
        String res = new dbmanager(this).insertData_dichvu(t, d, v, n,h);
        uploadTopic.getEditText().setText("");
        uploadDonvido.getEditText().setText("");
        uploadDichvu.getEditText().setText("");
        uploadNote.getEditText().setText("");
        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
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