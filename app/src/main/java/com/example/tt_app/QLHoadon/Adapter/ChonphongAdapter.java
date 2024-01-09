package com.example.tt_app.QLHoadon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_app.QLHoadon.Laphoadon;
import com.example.tt_app.QLPhong.DetailPhong;
import com.example.tt_app.R;
import com.example.tt_app.model.DataPhong;

import java.util.ArrayList;

public class ChonphongAdapter extends RecyclerView.Adapter<ChonphongAdapter.viewholder> {
    private Context context;
    private ArrayList<DataPhong> dataPhongs;

    public ChonphongAdapter(Context context, ArrayList<DataPhong> dataPhongs) {
        this.context = context;
        this.dataPhongs = dataPhongs;
    }

    @NonNull
    @Override
    public ChonphongAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_chonphong,parent,false);
        return new ChonphongAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonphongAdapter.viewholder holder, int position) {
        DataPhong dataPhong = dataPhongs.get(position);
        holder.cpTenphong.setText("Phòng số "+dataPhong.getPhong());

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetailPhong(dataPhong);
            }
        });
    }


    private void onClickGoToDetailPhong(DataPhong dataPhong) {
        Intent intent = new Intent(context, Laphoadon.class);

        DataPhong obj = new DataPhong(dataPhong.getMaphong(), dataPhong.getPhong(), dataPhong.getChiphithue(), dataPhong.getDientich(), dataPhong.getSonguoithue(), dataPhong.getTiencoc()
                , dataPhong.getDoituong(), dataPhong.getAnhphong(), dataPhong.getGiadien(),dataPhong.getGianuoc(), dataPhong.getMota(), dataPhong.getLydo());
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataphong", obj);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return dataPhongs.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView cpTenphong;
        CardView cvItem;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            cpTenphong= itemView.findViewById(R.id.cpTenphong);
            cvItem= itemView.findViewById(R.id.cvItem);
        }
    }
}
