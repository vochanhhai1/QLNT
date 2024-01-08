package com.example.tt_app.DetailPhongFragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_app.Adapter.DichvufromPhongAdapter;
import com.example.tt_app.Adapter.DichvufromPhongAdapter1;
import com.example.tt_app.R;
import com.example.tt_app.View.DetailNguoithue;
import com.example.tt_app.View.DetailPhong;
import com.example.tt_app.View.NguoiThue;
import com.example.tt_app.View.Phong;
import com.example.tt_app.View.dbmanager;
import com.example.tt_app.model.DataClass;
import com.example.tt_app.model.DataNguoithue;
import com.example.tt_app.model.DataPhong;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ThongTin extends Fragment {


    public ThongTin() {
        // Required empty public constructor
    }
    private int id,dientich, chiphi,tiencoc,songuoithue;
    private String anh,lydo,mota;
    TextView ttChiphi,ttDientich,ttTiencoc,ttGioihan;
    EditText ttmota,ttnhapluuy;
    private DetailPhong detailPhong;
    ArrayList<DataPhong> dataholder;
    RadioButton rdbNam,rdbNu;
    RecyclerView rcfDetailInfoPhong;
    Button detailXoa;
    private List<DataClass> DichvutList;
    private DichvufromPhongAdapter1 dichvufromPhongAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin, container, false);

        anhxaid(view);
        detailPhong = (DetailPhong) getActivity();
        Detail();

        Cursor cursor = new dbmanager(getActivity()).ReadDoiTuongToPhong(id);
        dataholder = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex("doituong"));
                // Tạo RadioButton và thiết lập dữ liệu
                if (data.equals("Nam")) {
                    rdbNam.setChecked(true);
                } else if (data.equals("Nữ")) {
                    rdbNu.setChecked(true);
                }
            } while (cursor.moveToNext());
        }


        //add dichvu detail
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        rcfDetailInfoPhong.setLayoutManager(gridLayoutManager1);


        Cursor cursor1 = new dbmanager(getActivity()).readalldata();
        DichvutList = new ArrayList<>();


        while (cursor1.moveToNext()) {
            DataClass obj = new DataClass(cursor1.getInt(0),cursor1.getString(1), cursor1.getString(2), cursor1.getInt(3), cursor1.getString(4),cursor1.getBlob(5));
            DichvutList.add(obj);
        }
        dichvufromPhongAdapter = new DichvufromPhongAdapter1(DichvutList);
        rcfDetailInfoPhong.setAdapter(dichvufromPhongAdapter);

        //xoa
        detailXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbmanager db = new dbmanager(getActivity());
                db.DeletePhong(id);
                Intent intent = new Intent(getActivity(), Phong.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void Detail()
    {
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        id= detailPhong.getMaphong();
        dientich = detailPhong.getDientich();
        ttDientich.setText(dientich+" m\u00B2");
        chiphi = detailPhong.getChiphithue();
        ttChiphi.setText(dcf.format(chiphi)+" đ");
        tiencoc = detailPhong.getTiencoc();
        ttTiencoc.setText(dcf.format(tiencoc)+" đ");
        songuoithue = detailPhong.getSonguoithue();
        ttGioihan.setText(""+songuoithue);
        mota = detailPhong.getMota();
        Toast.makeText(getActivity(),""+mota,Toast.LENGTH_SHORT).show();
        ttmota.setText(mota);
        lydo = detailPhong.getLydo();
        ttnhapluuy.setText(lydo);
        anh = detailPhong.getAnhphong();

    }

    private void anhxaid(View view) {
        rdbNam= view.findViewById(R.id.rdbNam);
        rdbNu= view.findViewById(R.id.rdbNu);
        ttChiphi = view.findViewById(R.id.ttChiphi);
        ttDientich = view.findViewById(R.id.ttDientich);
        ttTiencoc = view.findViewById(R.id.ttTiencoc);
        ttGioihan = view.findViewById(R.id.ttGioihan);
        ttmota = view.findViewById(R.id.mota);
        ttnhapluuy = view.findViewById(R.id.nhapluuy);
        rcfDetailInfoPhong= view.findViewById(R.id.rcfDetailInfoPhong);
        detailXoa = view.findViewById(R.id.detailXoa);
    }
}