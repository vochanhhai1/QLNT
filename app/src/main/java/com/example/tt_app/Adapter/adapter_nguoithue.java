package com.example.tt_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_app.R;
import com.example.tt_app.View.DetailActivity;
import com.example.tt_app.View.DetailNguoithue;
import com.example.tt_app.model.DataClass;
import com.example.tt_app.model.DataNguoithue;

import java.util.ArrayList;

public class adapter_nguoithue extends RecyclerView.Adapter<adapter_nguoithue.myviewholder> {
    ArrayList<DataNguoithue> dataholder;
    private Context mContext;


    public adapter_nguoithue(ArrayList<DataNguoithue> dataholder, Context mContext) {
        this.dataholder = dataholder;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_nguoithue,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        DataNguoithue dataClass = dataholder.get(position);
        if(dataClass == null)
        {
            return;
        }
        holder.recTenNguoiThue.setText(dataClass.getHovaten());
        holder.recSodienthoai.setText(dataClass.getSodienthoai().toString());
        holder.recPhong.setText("Phòng số " + dataClass.getChonphong());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetailNguoithue(dataClass);
            }
        });

    }

    private void onClickGoToDetailNguoithue(DataNguoithue dataClass) {
        Intent intent = new Intent(mContext, DetailNguoithue.class);
        Bundle bundle = new Bundle();
        intent.putExtra("idnguoithue",dataClass.getId_dichvu());
        intent.putExtra("hovaten", dataClass.getHovaten());
        intent.putExtra("sodienthoai", dataClass.getSodienthoai());
        intent.putExtra("chonphong",dataClass.getChonphong());
        intent.putExtra("email", dataClass.getEmail());
        intent.putExtra("ngaysinh", dataClass.getNgaysinh());
        intent.putExtra("cmnd", dataClass.getCmnd());
        intent.putExtra("ngaycap", dataClass.getNgaycap());
        intent.putExtra("noicap", dataClass.getNoicap());
        intent.putExtra("diachi", dataClass.getDiachi());
        intent.putExtra("anhcm",dataClass.getAnhcm());
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (dataholder!=null)
        {
            return dataholder.size();
        }
        return 0;
    }

    public void searchDataList(ArrayList<DataNguoithue> searchList) {
        dataholder = searchList;
        notifyDataSetChanged();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        androidx.cardview.widget.CardView cardView;
        TextView recTenNguoiThue,recPhong,recSodienthoai;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            recTenNguoiThue=itemView.findViewById(R.id.recTenNguoiThue);
            recPhong=itemView.findViewById(R.id.recPhong);
            recSodienthoai=itemView.findViewById(R.id.recSodienthoai);
            cardView = itemView.findViewById(R.id.recCard_nguoithue);
        }
    }
}
