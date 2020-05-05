package com.example.app;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.app.FolderStuff.Folder;
import com.example.app.ReceiptStuff.Receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

//Justin's stuff
public class GlobalFolderList{
    //the map that keeps all user folders. VERY IMPORTANT
    private static HashMap<String, Folder> folderMap = new HashMap<String, Folder>();
    //mto make sure dummy folder are only made one
    public static AtomicBoolean initator = new AtomicBoolean(true);
    //adders getters
    public static void add(String name, Folder folder) {
        folderMap.put(name, folder);
    }

    public static Folder get(String name) {
        return folderMap.get(name);
    }

    public static void remove(String name){
        folderMap.remove(name);
    }

    public static HashMap<String,Folder> getFolderList(){
        return folderMap;
    }
    //returns sum of all folder's budget
    public static double getTotalBudget(){
        double totBudget=0;
        for(Folder fold:folderMap.values()){
            totBudget+=fold.getBudget();
        }
        return totBudget;
    }
    //returns sun of all folder's spending
    public static double getTotalSpending(){
        double totSpending=0;
        for(Folder fold:folderMap.values()){
            totSpending+=fold.getSpending();
        }
        return totSpending;
    }
    public static void inflateFolders(Bitmap defaultPic){
        String[] color = new String[]{"#2F4760","#2D2821","#925E4D","#5E352D"};//,"#A89382","#8E757F","#201615","#8A2517"};
        String[] names = new String[]{"Food","Clothing","Gas","Entertainment"};//,"Reimbursements","Medical","Supplies","Travel"};
        String[] companies = new String[]{"Albicious","Genix","Audile","Cogideo","Megandu","Corize","Calcise","Vooloo","Skido","Hydrozzy","Omnizio","Sysil"};
        int[] budget = new int[]{350,200,400,250,0,150,70,500};
        Random rand = new Random();
        for (int i = 0; i < names.length; i++) {
            Folder fold = new Folder(names[i], Color.parseColor(color[i]), budget[i]);
            double receiptAmt = Math.random()*10+5;
            double avgCost = budget[i]/receiptAmt;
            for(int s = 0;s<receiptAmt;s++){
                try {
                    fold.addReceipt(new Receipt(defaultPic,
                            (0.5)*(avgCost-(avgCost*2*Math.random()-avgCost)),
                            companies[rand.nextInt(companies.length)],
                            new SimpleDateFormat("dd/MM/yyyy").parse((Math.round(Math.random()*15)+15)+"/04/2020"),
                            new SimpleDateFormat("dd/MM/yyyy").parse(Math.round(Math.random()*15)+"/05/2020"),
                            Math.random()>0.5)
                    );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            GlobalFolderList.add(names[i], fold);
        }
    }
}
