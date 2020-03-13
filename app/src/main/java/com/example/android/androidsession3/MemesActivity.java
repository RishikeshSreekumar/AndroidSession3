package com.example.android.androidsession3;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MemesActivity extends AppCompatActivity {
    TextView textView;
    Gson gson;
    GsonBuilder gsonBuilder = new GsonBuilder();
    MemesList memesList;
    RecyclerView memeRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String memesJSON = getIntent().getStringExtra("memes");
        gson = gsonBuilder.create();
        memesList = new MemesList();
        memesList = gson.fromJson(memesJSON,MemesList.class);

        memeRecycler = (RecyclerView) findViewById(R.id.memerecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        memeRecycler.setLayoutManager(mLayoutManager);
        if(memesList!=null) {
            MemesAdapter memesAdapter = new MemesAdapter(memesList.getData().getMemes(), getApplicationContext());
            memeRecycler.setAdapter(memesAdapter);
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            2);
                }
            }
        }
    }
}