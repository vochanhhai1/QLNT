package com.example.tt_app.QLDichvu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.example.tt_app.QLDichvu.Adapter.AdapterDichvu;
import com.example.tt_app.MainActivity;
import com.example.tt_app.R;
import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.model.DataClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DichVu extends AppCompatActivity {

    //khai bao biến
    FloatingActionButton fab;
    androidx.appcompat.widget.SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<DataClass> dataholder;
    ImageView dichvuBack;
    AdapterDichvu adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //ánh xạ id
        anhxaid();
        searchView.clearFocus();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent themdichvu = new Intent(getApplicationContext(), ThemDichVu.class);
                startActivity(themdichvu);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);


        Cursor cursor = new dbmanager(this).readalldata();
        dataholder = new ArrayList<>();


        while (cursor.moveToNext()) {
            DataClass obj = new DataClass(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4),cursor.getBlob(5));
            dataholder.add(obj);
        }

        adapter = new AdapterDichvu(this,dataholder);
        recyclerView.setAdapter(adapter);
        //cập nhật dữ liệu thay đổi

        adapter.notifyDataSetChanged();

        //search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        dichvuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DichVu.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



    public void searchList(String text){
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass: dataholder){
            if (dataClass.getUploadTopic().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }

    private void anhxaid() {
        fab = findViewById(R.id.fab);
        searchView = findViewById(R.id.search);
        dichvuBack = findViewById(R.id.dichvuBack);
    }


    //quay về
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Intent intent = new Intent(DichVu.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}