package com.example.tt_app.QLPhong.Adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_app.R;
import com.example.tt_app.model.DataClass;

import java.text.DecimalFormat;
import java.util.List;

public class ThongtinAdapter extends RecyclerView.Adapter<ThongtinAdapter.ViewHolder> {
    private List<DataClass> stList;

    public ThongtinAdapter(List<DataClass> stList) {
        this.stList = stList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThongtinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_thongtin,parent,false);
        return new ThongtinAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongtinAdapter.ViewHolder holder, int position) {
        DataClass dataClass = stList.get(position);
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
            }
        });
    }

    @Override
    public int getItemCount() {
        if (stList!=null)
        {
            return stList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dTopic,dDesc;
        ImageView recImage;
        androidx.cardview.widget.CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dTopic=(TextView)itemView.findViewById(R.id.recTitle);
            dDesc=(TextView)itemView.findViewById(R.id.recDesc);
            recImage = (ImageView) itemView.findViewById(R.id.recImage);
            cardView =itemView.findViewById(R.id.recCard);
        }
    }
    public List<DataClass> getStudentist() {
        return stList;
    }
}
