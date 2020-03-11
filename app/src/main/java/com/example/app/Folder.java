package com.example.app;

import android.widget.Button;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Folder {
    private String name;
    private String use;
    private String note;
    private ArrayList<Reciept> reciepts = new ArrayList<>();
    public Folder(String name, String use,String note){
        this.name = name;
        this.use = use;
        this.note = note;
    }
    public void addReciept(Reciept r){
        reciepts.add(r);
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
