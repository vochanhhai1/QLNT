package com.example.tt_app.NguoiThueFragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt_app.Adapter.adapter_nguoithue;
import com.example.tt_app.R;
import com.example.tt_app.View.dbmanager;
import com.example.tt_app.model.DataNguoithue;

import java.util.ArrayList;


public class ChuaCoPhong extends Fragment {


    public ChuaCoPhong() {
        // Required empty public constructor
    }
    androidx.appcompat.widget.SearchView search_nguoithue;
    RecyclerView recyclerView_nguoithue;
    ArrayList<DataNguoithue> dataNguoithues;
    adapter_nguoithue adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chua_co_phong, container, false);
        anhxaid(view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView_nguoithue.setLayoutManager(gridLayoutManager);


        Cursor cursor = new dbmanager(getActivity()).readalldataNguoithuerong();
        dataNguoithues = new ArrayList<>();


        while (cursor.moveToNext()) {
            DataNguoithue obj = new DataNguoithue(cursor.getInt(0),cursor.getString(1), cursor.getInt(2)
                    , cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),
                    cursor.getString(8),cursor.getString(9),cursor.getString(10),2);
            dataNguoithues.add(obj);
        }

        adapter = new adapter_nguoithue(dataNguoithues,getActivity());
        recyclerView_nguoithue.setAdapter(adapter);
        //cập nhật dữ liệu thay đổi

        adapter.notifyDataSetChanged();

        //search
        search_nguoithue.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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


        return view;
    }

    public void searchList(String text){
        ArrayList<DataNguoithue> searchList = new ArrayList<>();
        for (DataNguoithue dataNguoithue: dataNguoithues){
            if (dataNguoithue.getHovaten().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataNguoithue);
            }
        }
        adapter.searchDataList(searchList);
    }
    private void anhxaid(View view) {
        search_nguoithue = view.findViewById(R.id.search_nguoithue);
        recyclerView_nguoithue = view.findViewById(R.id.recyclerView_nguoithue);
    }
}