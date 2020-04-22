package com.example.app;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;

public class GlobalFolderList{
    private static HashMap<String, Folder> folderMap = new HashMap<String, Folder>();

    public static void add(String name, Folder folder) {
        folderMap.put(name, folder);
    }

    public static Folder get(String name) {
        String test="";
        for(String i:folderMap.keySet()){
            test+=i;
        }
        Log.d("Myapp",test);
        return folderMap.get(name);
    }
    public static HashMap<String,Folder> getFolderList(){
        return folderMap;
    }
}