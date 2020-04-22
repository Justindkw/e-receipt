package com.example.app;

import android.graphics.Bitmap;
import java.util.Date;

public class Receipt {
    private Bitmap photo;
    private double cost;
    private String company;
    private Date dayLimit;

    public Receipt(Bitmap photo, int amt, String company) {
        this.photo = photo;
        this.cost = amt;
        this.company = company;
    }


    public double getCost() {
        return cost;
    }

    public Bitmap getPhoto() {
        return photo;
    }


    public void setDayLimit(Date date) {
        this.dayLimit = date;
    }
}