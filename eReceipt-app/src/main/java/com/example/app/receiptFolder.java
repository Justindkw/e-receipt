package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class receiptFolder extends AppCompatActivity {
    private Folder folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);
        folder = getIntent().getParcelableExtra("folder");
    }
}
