package com.example.app.FolderStuff;

import androidx.annotation.NonNull;

import com.example.app.ReceiptStuff.Receipt;

import java.util.ArrayList;
import java.util.Date;

//Justin's stuff
public class Folder {
    //buncha stuff stored in the folders
    private String name;
    private String use;
    private String note;
    private Date date;
    private double spending = 0;
    private double budget = 0;
    private ArrayList<Receipt> receipts = new ArrayList<>();
    //constructor
    public Folder(String name, String use, String note) {
        this.name = name;
        this.use = use;
        this.note = note;
        this.date = new Date();
    }
    //buncha getters, adders, and setters
    //adds new receipt but also adds it's cost to spending
    public int addReceipt(Receipt r) {
        this.receipts.add(r);
        this.spending += r.getCost();
        return receipts.size() - 1;
    }

    public double getSpending() {
        return spending;
    }
    //only exists to make demo folders
    public void setSpending(double spending){
        this.spending = spending;
    }

    public ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public int size() {
        return receipts.size();
    }

    @NonNull
    public String toString() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
