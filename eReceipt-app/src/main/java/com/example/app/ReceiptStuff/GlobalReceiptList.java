package com.example.app.ReceiptStuff;

import com.example.app.ReceiptStuff.Receipt;

import java.util.HashMap;

public class GlobalReceiptList {
    //the map that keeps all user receipts. VERY IMPORTANT
    private static HashMap<String, Receipt> receiptMap = new HashMap<String, Receipt>();
    //adders getters
    public static void add(String name, Receipt receipt) {
        receiptMap.put(name, receipt);
    }

    public static Receipt get(String name) {
        return receiptMap.get(name);
    }

    public static HashMap<String, Receipt> getReceiptList(){
        return receiptMap;
    }
    //returns sum of all folder's budget
    public static int getTotalCost(){
        int totCost=0;
        for(Receipt fold:receiptMap.values()){
            totCost+=fold.getCost();
        }
        return totCost;
    }
    //returns the company's name
    public static String getCompany(){
        String company = "";
        for(Receipt fold:receiptMap.values()){
            company+=fold.getCompany();
        }
        return company;
    }
}

