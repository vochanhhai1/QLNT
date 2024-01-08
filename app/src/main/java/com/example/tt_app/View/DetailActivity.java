package com.example.tt_app.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tt_app.Adapter.myadapter;
import com.example.tt_app.View.dbmanager;
import com.example.tt_app.R;
import com.example.tt_app.model.DataClass;
import com.github.clans.fab.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailActivity extends AppCompatActivity{

    TextView  tvTitle, tvNote,tvDichvu,tvDonvido;
    ImageView tvImage;
    FloatingActionButton deleteButton,editButton;
    private static Context context;

    int dichvu,id_dichvu;
    byte[] hinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        anhxa();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            id_dichvu = bundle.getInt("iddichvu");
            tvTitle.setText(bundle.getString("uploadtopic"));
            tvDonvido.setText(bundle.getString("uploaddonvido"));
            dichvu = bundle.getInt("uploaddichvu");
            tvNote.setText(bundle.getString("uploadnote"));
            hinh = bundle.getByteArray("uploadhinhanh");
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(bundle.getString("uploadtopic")); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //chuyen byte[] thanh bitmap
//        byte[] uploadhinhanh = dataClass.getUploadhinhanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        tvImage.setImageBitmap(bitmap);

        DecimalFormat dcf = new DecimalFormat("###,###,###");
        tvDichvu.setText(dcf.format(Double.parseDouble(String.valueOf(dichvu)))+"đ");




        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "dsadjahds", Toast.LENGTH_SHORT).show();
                dbmanager db = new dbmanager(getApplicationContext());
                db.DeleteDichvu(new DataClass(id_dichvu,null,null,null,null,null));

                Intent intent = new Intent(DetailActivity.this,DichVu.class);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickGoToUpdate(dataClass);

                Intent intent = new Intent(DetailActivity.this, UpdateDichVu.class)
                        .putExtra("iddichvu",id_dichvu)
                        .putExtra("uploadtopic", tvTitle.getText().toString())
                        .putExtra("uploaddonvido", tvDonvido.getText().toString())
                        .putExtra("uploaddichvu", dichvu)
                        .putExtra("uploadnote", tvNote.getText().toString())
                        .putExtra("uploadhinhanh", hinh);

                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        tvTitle = findViewById(R.id.detailTitle);
        tvDichvu = findViewById(R.id.detailDichvu);
        tvNote = findViewById(R.id.detailNote);
        tvImage = findViewById(R.id.detailImage);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        tvDonvido = findViewById(R.id.detailDonvido);
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