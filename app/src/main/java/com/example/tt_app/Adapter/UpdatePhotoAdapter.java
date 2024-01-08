package com.example.tt_app.Adapter;

import android.content.Context;
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

import java.util.List;

public class UpdatePhotoAdapter extends RecyclerView.Adapter<UpdatePhotoAdapter.PhotoViewHolder>{
    private List<String> imageList;
    private Context context;

    public UpdatePhotoAdapter(List<String> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_photo, parent, false);
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
                .into(holder.img_photo);
//        Picasso.get().load(imageUrl).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_photo;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            img_photo = itemView.findViewById(R.id.img_photo);
        }
    }
}

