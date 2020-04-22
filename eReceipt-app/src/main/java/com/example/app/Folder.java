package com.example.app;
import androidx.annotation.NonNull;
import java.util.ArrayList;
//Justin's stuff
public class Folder {
    private String name;
    private String use;
    private String note;
    private double spendings = 0;
    private double budget = 0;
    private ArrayList<Receipt> receipts = new ArrayList<>();

    public Folder(String name, String use, String note) {
        this.name = name;
        this.use = use;
        this.note = note;
    }

    public int addReceipt(Receipt r) {
        this.receipts.add(r);
        this.spendings += r.getCost();
        return receipts.size() - 1;
    }

    public double getSpendings() {
        return spendings;
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
    @Override
    public String toString() {
        return name;
    }
}
