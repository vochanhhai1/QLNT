package com.example.tt_app.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.tt_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HoaDon extends AppCompatActivity {

    SearchView searchHoadon;
    ImageView HoadonBack;
    FloatingActionButton fab_Hoadon;
    RecyclerView recyclerViewHoadon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        //ấn thanh actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        anhxaid();


    }

    private void anhxaid() {
        searchHoadon= findViewById(R.id.searchHoadon);
        HoadonBack= findViewById(R.id.HoadonBack);
        fab_Hoadon= findViewById(R.id.fab_Hoadon);
        recyclerViewHoadon= findViewById(R.id.recyclerViewHoadon);
    }
}