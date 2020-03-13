package com.example.android.androidsession3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.MyViewHolder> {
    List<MemesClass> memes;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView memeImage;
        public TextView memeName;
        public Button memeSave;
        public MyViewHolder(View itemView) {
            super(itemView);
            memeImage = (ImageView) itemView.findViewById(R.id.memeimage);
            memeName = (TextView) itemView.findViewById(R.id.txt_memename);
            memeSave = (Button) itemView.findViewById(R.id.memesave);
        }
    }
    public MemesAdapter(List<MemesClass> memesClasses,Context context){
        this.memes=memesClasses;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memes_view, parent, false);

        return new MyViewHolder(itemView);

    }


    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MemesClass meme = memes.get(position);
        holder.memeName.setText(meme.getName());
        holder.memeImage.setMaxHeight(meme.getHeight());
        holder.memeImage.setMaxWidth(meme.getWidth());
        Picasso.get()
                .load(meme.getUrl())
                .into(holder.memeImage);
        holder.memeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path;
                Bitmap image = ((BitmapDrawable)holder.memeImage.getDrawable()).getBitmap();

                path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),meme.getName()+".jpg");
                OutputStream outputStream=null;
                try {
                    outputStream = new FileOutputStream(path);
                    image.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    Toast.makeText(context,"Image Saved",Toast.LENGTH_SHORT).show();
                    outputStream.flush();
                    outputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }
}