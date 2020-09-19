package com.example.ashpazbashi.views.recyclerViewAdaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashpazbashi.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PicAdaptor extends RecyclerView.Adapter<PicAdaptor.ViewHolder>{
    private List<String> data;
    private LayoutInflater inflater;
    private OnPicListener listener;

    public PicAdaptor(List<String> data, Context context, OnPicListener listener) {
        this.data = new ArrayList<>();
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.food_pic_item,parent,false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File imgFile = new File(data.get(position));
        if (imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        OnPicListener onPicListener;

        public ViewHolder(@NonNull View itemView, OnPicListener onPicListener) {
            super(itemView);
            this.onPicListener = onPicListener;
            imageView = itemView.findViewById(R.id.foodMediaImageView);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onPicListener.click(getAdapterPosition());
        }
    }

    public interface OnPicListener {
        void click(int position);
    }

}
