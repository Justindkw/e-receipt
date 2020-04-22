package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Folder {
    private String name;
    private String use;
    private String note;
    private double totalSpent = 0;
    private double totalBudget = 0;
    private ArrayList<Receipt> receipts = new ArrayList<>();

    public Folder(String name, String use, String note) {
        this.name = name;
        this.use = use;
        this.note = note;
    }

    public int addReceipt(Receipt r) {
        this.receipts.add(r);
        this.totalSpent += r.getCost();
        return receipts.size() - 1;
    }


    public double getTotalSpent() {
        return totalSpent;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    public void setTotalBudget(double budget) {
        this.totalBudget = budget;
    }
    public double getTotalBudget() {
        return totalBudget;
    }

    public int size() {
        return receipts.size();
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
