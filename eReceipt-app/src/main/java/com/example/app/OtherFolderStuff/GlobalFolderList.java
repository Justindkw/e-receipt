package com.example.app.OtherFolderStuff;

import java.util.HashMap;
//Justin's stuff
public class GlobalFolderList{
    //the map that keeps all user folders. VERY IMPORTANT
    private static HashMap<String, Folder> folderMap = new HashMap<String, Folder>();
    //adders getters
    public static void add(String name, Folder folder) {
        folderMap.put(name, folder);
    }

    public static Folder get(String name) {
        return folderMap.get(name);
    }

    public static HashMap<String,Folder> getFolderList(){
        return folderMap;
    }
    //returns sum of all folder's budget
    public static int getTotalBudget(){
        int totBudget=0;
        for(Folder fold:folderMap.values()){
            totBudget+=fold.getBudget();
        }
        return totBudget;
    }
    //returns sun of all folder's spending
    public static int getTotalSpending(){
        int totSpending=0;
        for(Folder fold:folderMap.values()){
            totSpending+=fold.getSpending();
        }
        return totSpending;
    }
}