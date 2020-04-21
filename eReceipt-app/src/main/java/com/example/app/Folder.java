package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Folder implements Parcelable {
    private String name;
    private String use;
    private String note;
    private double totalSpent = 0;
    //private ArrayList<Receipt> receipts = new ArrayList<>();
    public Folder(String name, String use,String note){
        this.name = name;
        this.use = use;
        this.note = note;
    }

    protected Folder(Parcel in) {
        name = in.readString();
        use = in.readString();
        note = in.readString();
    }

    public static final Creator<Folder> CREATOR = new Creator<Folder>() {
        @Override
        public Folder createFromParcel(Parcel in) {
            return new Folder(in);
        }

        @Override
        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };

    /*public void addReceipt(Receipt r){
        receipts.add(r);
        totalSpent+=r.getCost();
    }*/
    public double getTotalSpent(){
        return totalSpent;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(use);
        dest.writeString(note);
    }
}
