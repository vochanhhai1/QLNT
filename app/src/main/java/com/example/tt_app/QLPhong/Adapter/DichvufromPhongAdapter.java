package com.example.tt_app.QLPhong.Adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_app.R;
import com.example.tt_app.model.DataClass;

import java.text.DecimalFormat;
import java.util.List;

public class DichvufromPhongAdapter extends RecyclerView.Adapter<DichvufromPhongAdapter.ViewHolder> {
    private List<DataClass> stList;


    public DichvufromPhongAdapter(List<DataClass> stList) {
        this.stList = stList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DichvufromPhongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dichvu_row,parent,false);
        return new DichvufromPhongAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DichvufromPhongAdapter.ViewHolder holder, int position) {
        DataClass dataClass = stList.get(position);

        holder.tvTen.setText(dataClass.getUploadTopic());

        DecimalFormat dcf = new DecimalFormat("###,###,###");
        holder.tvPhi.setText(dcf.format(Double.parseDouble(String.valueOf(dataClass.getUploadDichvu())))+"đ");

        holder.imgAnhdichvu.setImageBitmap(BitmapFactory.decodeByteArray(dataClass.getUploadhinhanh(),0,dataClass.getUploadhinhanh().length));

        holder.chkSelected.setTag(dataClass);

        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                DataClass contact = (DataClass) cb.getTag();

                contact.setSelected(cb.isChecked());
                dataClass.setSelected(cb.isChecked());
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
        TextView tvTen,tvPhi;
        ImageView imgAnhdichvu;
         CheckBox chkSelected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = (TextView) itemView.findViewById(R.id.tvTen);
            tvPhi = (TextView) itemView.findViewById(R.id.tvPhi);
            imgAnhdichvu = (ImageView) itemView.findViewById(R.id.imgAnhdichvu);
            chkSelected = (CheckBox) itemView.findViewById(R.id.chkSelected);
        }
    }
    public List<DataClass> getStudentist() {
        return stList;
    }
}
