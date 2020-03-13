package com.example.android.androidsession3;

import java.util.List;

public class MemesList {
    Boolean success;
    List<MemesClass> memes;

    public MemesList() {
    }

    public List<MemesClass> getMemes(){
        return memes;
    }
    public String getId(Integer i){
        return memes.get(i).getId();
    }
    public String getName(Integer i){
        return memes.get(i).getName();
    }
    public String getURL(Integer i){
        return memes.get(i).getUrl();
    }
    public Integer getHeight(Integer i){
        return memes.get(i).getHeight();
    }
    public Integer getWidth(Integer i){
        return memes.get(i).getWidth();
    }
}