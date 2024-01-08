package com.example.tt_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_app.DetailPhongFragment.NguoiThue;
import com.example.tt_app.R;
import com.example.tt_app.View.DetailNguoithue;
import com.example.tt_app.View.DetailPhong;
import com.example.tt_app.View.dbmanager;
import com.example.tt_app.model.DataClass;
import com.example.tt_app.model.DataNguoithue;
import com.example.tt_app.model.DataPhong;

import java.util.ArrayList;

public class AdapterPhong extends RecyclerView.Adapter<AdapterPhong.viewholder> {

    ArrayList<DataPhong> dataholder;
    Context context;

    Integer maphong;
    public AdapterPhong(ArrayList<DataPhong> dataholder, Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPhong.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_phong, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPhong.viewholder holder, int position) {
        DataPhong dataPhong = dataholder.get(position);
        Toast.makeText(context,"ly do la"+dataPhong.getLydo(),Toast.LENGTH_SHORT).show();
        holder.recPhong.setText("Phòng " + dataPhong.getPhong());
        Cursor cursor = new dbmanager(context).CountNguoiThueInPhong(dataPhong.getMaphong());
        if (cursor.moveToFirst()) {
            do {
                String soluong = cursor.getString(cursor.getColumnIndex("songuoi"));
                holder.tvSonguoi.setText(soluong);
            } while (cursor.moveToNext());
        }
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetailPhong(dataPhong);

            }
        });
    }

    private void onClickGoToDetailPhong(DataPhong dataPhong) {
        Intent intent = new Intent(context, DetailPhong.class);
//        DataPhong obj = new DataPhong(dataPhong.getMaphong(), dataPhong.getPhong(), dataPhong.getChiphithue(), dataPhong.getDientich(), dataPhong.getSonguoithue(), dataPhong.getTiencoc()
//                , dataPhong.getDoituong(), dataPhong.getAnhphong(), dataPhong.getDichvu(), dataPhong.getMota(), dataPhong.getLydo());
        Bundle bundle = new Bundle();
//        bundle.putSerializable("dataphong", obj);
        intent.putExtra("maphong",dataPhong.getMaphong());
        intent.putExtra("phong", dataPhong.getPhong());
        intent.putExtra("chiphithue", dataPhong.getChiphithue());
        intent.putExtra("dientich",dataPhong.getDientich());
        intent.putExtra("songuoithue", dataPhong.getSonguoithue());
        intent.putExtra("tiencoc", dataPhong.getTiencoc());
        intent.putExtra("doituong", dataPhong.getDoituong());
        intent.putExtra("anhphong", dataPhong.getAnhphong());
//        intent.putExtra("dichvu", dataPhong.getDichvu());
        intent.putExtra("mota", dataPhong.getMota());
        intent.putExtra("lydo",dataPhong.getLydo());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (dataholder != null) {
            return dataholder.size();
        }
        return 0;
    }
    public void searchDataList(ArrayList<DataPhong> searchList){
        dataholder = searchList;
        notifyDataSetChanged();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView recPhong,tvSonguoi;
        CardView recCard;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            recPhong = itemView.findViewById(R.id.recPhong);
            recCard = itemView.findViewById(R.id.recCard);
            tvSonguoi = itemView.findViewById(R.id.tvSonguoi);
        }
    }
}
