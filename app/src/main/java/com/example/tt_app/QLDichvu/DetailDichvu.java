package com.example.tt_app.QLDichvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.model.DataClass;
import com.github.clans.fab.FloatingActionButton;

import java.text.DecimalFormat;

public class DetailDichvu extends AppCompatActivity{

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

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.baseline_info_24);
                builder.setMessage("Bạn có muốn xóa dịch vụ này không?");
                builder.setPositiveButton("Có chứ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbmanager db = new dbmanager(getApplicationContext());
                        db.DeleteDichvu(new DataClass(id_dichvu,null,null,null,null,null));

                        Intent intent = new Intent(DetailDichvu.this, DichVu.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickGoToUpdate(dataClass);

                Intent intent = new Intent(DetailDichvu.this, UpdateDichVu.class)
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