package com.example.app.ReceiptStuff;

import android.graphics.Bitmap;
import java.util.Date;
//Justin's stuff
public class Receipt {
    //receipt information
    private Bitmap photo;
    private double cost;
    private String company;
    private Date refundDate;
    private boolean timer;
    private Date date;
    //constructor
    public Receipt(Bitmap photo, double cost, String company, Date refundDate,boolean timer) {
        this.photo = photo;
        this.cost = cost;
        this.company = company;
        this.refundDate = refundDate;
        this.timer = timer;
        this.date = new Date();
    }
    public Receipt(Bitmap photo, double cost, String company){
        this(photo,cost,company,new Date(),false);
    }

    public int getRefundDaysLeft(){
        return (int)((date.getTime()-refundDate.getTime())/(1000*60*60*24));
    }

    //buncha getters and setters
    public double getCost() {
        return cost;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public String getCompany() {
        return company;
    }

    public void setRefundDate(Date date) {
        this.refundDate = date;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
