package com.example.tt_app.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.tt_app.R;
import com.example.tt_app.View.dbmanager;
import com.example.tt_app.model.DataNguoithue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailHinhanhAdapter extends RecyclerView.Adapter<DetailHinhanhAdapter.PhotoViewHolder>{

    private List<String> imageList;
    private Context context;
    private dbmanager db;

    public DetailHinhanhAdapter(List<String> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_updatephoto, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.uploadimg) // Thay thế bằng ảnh placeholder của bạn
                .error(R.drawable.uploadimg) // Thay thế bằng ảnh lỗi của bạn (nếu cần)
                .diskCacheStrategy(DiskCacheStrategy.DATA); // Lưu vào bộ nhớ đệm tệp tin ảnh để load nhanh hơn
        String imageUrl = imageList.get(position);
        Glide.with(context)
                .load(Uri.parse(imageUrl))
                .apply(options)
                .into(holder.updatePhoto);
//        Picasso.get().load(imageUrl).into(holder.imgPhoto);
        holder.updatexoaAnhcmnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageList.remove(holder.getAdapterPosition());
                notifyItemChanged(holder.getAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView updatePhoto;
        private ImageView updatexoaAnhcmnd;

        public PhotoViewHolder(@NonNull View itemView ) {
            super(itemView);
            updatePhoto = itemView.findViewById(R.id.updatePhoto);
            updatexoaAnhcmnd = itemView.findViewById(R.id.updatexoaAnhcmnd);

        }
    }
}
