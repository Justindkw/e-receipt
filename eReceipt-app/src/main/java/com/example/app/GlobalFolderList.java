package com.example.app;

import com.example.app.FolderStuff.Folder;

import java.util.HashMap;
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
}
