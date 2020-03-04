package com.example.app;

import android.graphics.Bitmap;

import java.util.Date;

public class Reciept {
    private Bitmap photo;
    private int amt;
    private String company;
    private Date dayLimit;
    public Reciept(Bitmap photo,int amt, String company) {
        this.photo = photo;
        this.amt = amt;
        this.company = company;
    }
    public void setDayLimit(Date date){
        this.dayLimit = date;
    }
}
