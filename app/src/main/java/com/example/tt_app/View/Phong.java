package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tt_app.Adapter.AdapterPhong;
import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.example.tt_app.model.DataPhong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class    Phong extends AppCompatActivity {
    ImageView PhongBack,dichvuBack;
    FloatingActionButton fab_phong;
    ArrayList<DataPhong> dataholder;
    AdapterPhong myadapter;
    RecyclerView recyclerViewPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxaid();

        //trove
        PhongBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phong = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(phong);
            }
        });

        fab_phong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent themphong = new Intent(getApplicationContext(), Themphong.class);
                startActivity(themphong);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerViewPhong.setLayoutManager(gridLayoutManager);

        Cursor cursor = new dbmanager(this).readalldataPhong();
        dataholder = new ArrayList<>();

        while (cursor.moveToNext())
        {
            DataPhong obj = new DataPhong(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5), cursor.getString(6),cursor.getString(7),cursor.getInt(8),
                    cursor.getInt(9),cursor.getString(10),cursor.getString(11));
            dataholder.add(obj);
        }
        myadapter = new AdapterPhong(dataholder,this);
        recyclerViewPhong.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();

    }

    private void anhxaid() {
        PhongBack = findViewById( R.id.PhongBack);
        fab_phong = findViewById(R.id.fab_phong);
        dichvuBack= findViewById(R.id.dichvuBack);
        recyclerViewPhong = findViewById(R.id.recyclerViewPhong);
    }
}