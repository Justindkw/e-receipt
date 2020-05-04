package com.example.app.FolderStuff;

import androidx.annotation.NonNull;

import com.example.app.ReceiptStuff.Receipt;

import java.util.ArrayList;
import java.util.Date;

//Justin's stuff
public class Folder {
    //buncha stuff stored in the folders
    private String name;
    private int color;
    private Date date;
    private double spending = 0;
    private double budget = 0;
    private boolean selected;
    private ArrayList<Receipt> receipts = new ArrayList<>();
    private final int daysBeforeNotified = 7;
    //constructor
    public Folder(String name, int color, double budget) {
        this.name = name;
        this.budget = budget;
        this.color = color;
        this.date = new Date();
    }
    //buncha getters, adders, and setters
    //adds new receipt but also adds it's cost to spending
    public int addReceipt(Receipt r) {
        this.receipts.add(r);
        this.spending += r.getCost();
        return receipts.size() - 1;
    }
    public int totalNotification(){
        int totNotif = 0;
        for(Receipt r: receipts){
            if(r.isTimer()&&daysBeforeNotified>r.getRefundDaysLeft()){
                totNotif+=1;
            }
        }
        return totNotif;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
