package com.example.app;

import android.app.Application;

import java.util.HashMap;

public class GlobalFolderList{
    private static HashMap<String, Folder> folderMap = new HashMap<String, Folder>();

    public static void add(String name, Folder folder) {
        folderMap.put(name, folder);
    }

    public static Folder get(String name) {
        return folderMap.get(name);
    }
    public static HashMap<String,Folder> getFolderList(){
        return folderMap;
    }
}
