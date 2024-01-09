package com.example.tt_app.QLPhong.DetailPhongFragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt_app.QLNguoithue.Adapter.adapter_nguoithue;
import com.example.tt_app.R;
import com.example.tt_app.QLPhong.DetailPhong;
import com.example.tt_app.QLNguoithue.ThemNguoiThue;
import com.example.tt_app.Database.dbmanager;
import com.example.tt_app.model.DataNguoithue;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class NguoiThue extends Fragment {


    public NguoiThue() {
        // Required empty public constructor
    }

    RecyclerView rcfFragNguoithue;
    FloatingActionButton fab_phong;
    ArrayList<DataNguoithue> dataholder;
    adapter_nguoithue adapterNguoithue;

    private DetailPhong detailPhong;
    private int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nguoi_thue, container, false);
        anhxaid(view);

        detailPhong = (DetailPhong) getActivity();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcfFragNguoithue.setLayoutManager(gridLayoutManager);

        id = detailPhong.getMaphong();

        Cursor cursor = new dbmanager(getActivity()).readNguoiThuetoPhong(id);
        dataholder = new ArrayList<>();


        while (cursor.moveToNext()) {
            DataNguoithue obj = new DataNguoithue(cursor.getInt(0),cursor.getString(1), cursor.getInt(2)
                    , cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),
                    cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getInt(11));
            dataholder.add(obj);
        }

        adapterNguoithue = new adapter_nguoithue(dataholder,getActivity());
        rcfFragNguoithue.setAdapter(adapterNguoithue);
        //cập nhật dữ liệu thay đổi

        adapterNguoithue.notifyDataSetChanged();

        fab_phong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThemNguoiThue.class);
                startActivity(intent);
            }
        });
        return view;
    }


    private void anhxaid(View view) {
        rcfFragNguoithue = view.findViewById(R.id.rcfFragNguoithue);
        fab_phong = view.findViewById(R.id.fab_phong);
    }

}