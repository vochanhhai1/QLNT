package com.example.tt_app.View;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tt_app.Adapter.myadapter;
import com.example.tt_app.R;
import com.example.tt_app.model.DataClass;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UpdateDichVu extends AppCompatActivity {

    TextInputLayout tvUpdateTitle, tvUpdateNote,tvUpdateDichvu,tvUpdateDonvido;
    ImageView tvUpdateImage;
    Button updateButton;

    private static Context context;

    Uri uri;

    int updateDichvu,id_updatedichvu;
    byte[] updataHinhanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dich_vu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sửa Dịch Vụ"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Anhxa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
//            Glide.with(UpdateDichVu.this).load(bundle.getString("Image")).into(tvUpdateImage);
            id_updatedichvu= bundle.getInt("iddichvu");
            tvUpdateTitle.getEditText().setText(bundle.getString("uploadtopic"));
            tvUpdateDonvido.getEditText().setText(bundle.getString("uploaddonvido"));
            updateDichvu = bundle.getInt("uploaddichvu");

            tvUpdateNote.getEditText().setText(bundle.getString("uploadnote"));
            updataHinhanh = bundle.getByteArray("uploadhinhanh");

            byte[] updataHinhanh = bundle.getByteArray("uploadhinhanh");
            Bitmap bitmap = BitmapFactory.decodeByteArray(updataHinhanh, 0, updataHinhanh.length);
            tvUpdateImage.setImageBitmap(bitmap);
        }

        tvUpdateDichvu.getEditText().setText(updateDichvu+"");

        // lấy hình ảnh từ trong thiết bị điện thoại
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            tvUpdateImage.setImageURI(uri);
                        } else {
                            Toast.makeText(UpdateDichVu.this, "Không có hình ảnh được chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        tvUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });



//        Bundle bundle = getIntent().getExtras();
//        if (bundle == null) {
//            return;
//        }
//
//        DataClass dataClass = (DataClass) bundle.get("object_user1");
//
//        //chuyen byte[] thanh bitmap
//        byte[] uploadhinhanh = dataClass.getUploadhinhanh();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(uploadhinhanh, 0, uploadhinhanh.length);
//        tvUpdateImage.setImageBitmap(bitmap);
//
//        Integer dichvu = dataClass.getUploadDichvu();
//

//        tvUpdateTitle.getEditText().setText(dataClass.getUploadTopic());
//        tvUpdateNote.getEditText().setText(dataClass.getUploadNote());
////        tvUpdateDichvu.setText(dataClass.getUploadDichvu().toString());
//        tvUpdateDonvido.getEditText().setText(dataClass.getUploadDonvido().toString());



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "dsadjahds", Toast.LENGTH_SHORT).show();
                //chuyen imageview sang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) tvUpdateImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);

                byte[] uploadhinhanh =byteArray.toByteArray();

                dbmanager db = new dbmanager(getApplicationContext());
                db.UpdateDichvu(new DataClass(id_updatedichvu,tvUpdateTitle.getEditText().getText().toString(),tvUpdateDonvido.getEditText().getText().toString()
                        ,Integer.parseInt(tvUpdateDichvu.getEditText().
                        getText().toString()),tvUpdateNote.getEditText().getText().toString(),uploadhinhanh));

                Intent intent = new Intent(UpdateDichVu.this,DichVu.class);
                startActivity(intent);
            }
        });

    }

    private void Anhxa() {
        tvUpdateTitle=findViewById(R.id.updateTopic);
        tvUpdateNote=findViewById(R.id.updateNote);
        tvUpdateDichvu=findViewById(R.id.updateDichvu);
        tvUpdateDonvido=findViewById(R.id.updateDonvido);
        tvUpdateImage=findViewById(R.id.updateImage);
        updateButton=findViewById(R.id.updateButton);
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