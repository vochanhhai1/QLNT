package com.example.tt_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tt_app.R;
import com.example.tt_app.View.DetailActivity;
import com.example.tt_app.View.UpdateDichVu;
import com.example.tt_app.View.dbmanager;
import com.example.tt_app.model.DataClass;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<DataClass> dataholder;
    private Context mContext;

    public myadapter(Context context,ArrayList<DataClass> dataholder ) {
        this.mContext=context;
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        DataClass dataClass = dataholder.get(position);
        if(dataClass == null)
        {
            return;
        }
        holder.dTopic.setText(dataClass.getUploadTopic());
        DecimalFormat dcf = new DecimalFormat("###,###,###");
        holder.dDesc.setText(dcf.format(Double.parseDouble(String.valueOf(dataClass.getUploadDichvu())))+"đ");
        holder.recImage.setImageBitmap(BitmapFactory.decodeByteArray(dataClass.getUploadhinhanh(),0,dataClass.getUploadhinhanh().length));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(dataClass);
            }
        });
    }

    private void onClickGoToDetail(DataClass dataClass) {
        Intent intent = new Intent(mContext,DetailActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("iddichvu",dataClass.getId_dichvu());
        intent.putExtra("uploadtopic", dataClass.getUploadTopic());
        intent.putExtra("uploaddonvido", dataClass.getUploadDonvido());
        intent.putExtra("uploaddichvu",dataClass.getUploadDichvu());
        intent.putExtra("uploadnote", dataClass.getUploadNote());
        intent.putExtra("uploadhinhanh", dataClass.getUploadhinhanh());
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

    public void searchDataList(ArrayList<DataClass> searchList){
        dataholder = searchList;
        notifyDataSetChanged();
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        androidx.cardview.widget.CardView cardView;
        TextView dTopic,dDesc;
        ImageView recImage;


        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            cardView =itemView.findViewById(R.id.recCard);
            dTopic=(TextView)itemView.findViewById(R.id.recTitle);
            dDesc=(TextView)itemView.findViewById(R.id.recDesc);
            recImage = (ImageView) itemView.findViewById(R.id.recImage);
        }
    }

}