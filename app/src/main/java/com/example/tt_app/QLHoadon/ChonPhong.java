package com.example.tt_app.QLHoadon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.QLHoadon.Adapter.ChonphongAdapter;
import com.example.tt_app.QLPhong.Adapter.AdapterPhong;
import com.example.tt_app.R;
import com.example.tt_app.model.DataPhong;

import java.util.ArrayList;

public class ChonPhong extends AppCompatActivity {
    SearchView searchChonphong;
    RecyclerView recyclerViewChonphong;
    ImageView ChonphongBack;
    ArrayList<DataPhong>dataPhongs;
    ChonphongAdapter chonphongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_phong);
        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        anhxaid();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerViewChonphong.setLayoutManager(gridLayoutManager);

        Cursor cursor = new dbmanager(this).readalldataPhong();
        dataPhongs = new ArrayList<>();

        while (cursor.moveToNext())
        {
            DataPhong obj = new DataPhong(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getInt(3),cursor.getInt(4),cursor.getDouble(5), cursor.getString(6),cursor.getString(7),cursor.getInt(8),
                    cursor.getInt(9),cursor.getString(10),cursor.getString(11));
            dataPhongs.add(obj);
        }
        chonphongAdapter = new ChonphongAdapter(this,dataPhongs);
        recyclerViewChonphong.setAdapter(chonphongAdapter);
        chonphongAdapter.notifyDataSetChanged();

    }


    private void anhxaid() {
        searchChonphong= findViewById(R.id.searchChonphong);
        recyclerViewChonphong= findViewById(R.id.recyclerViewChonphong);
        ChonphongBack= findViewById(R.id.ChonphongBack);
    }
}