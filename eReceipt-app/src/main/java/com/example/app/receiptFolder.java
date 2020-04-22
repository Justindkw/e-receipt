package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class receiptFolder extends AppCompatActivity {
    static final int REQUEST_RECIEPT = 1;


    private String name;
    private ArrayList<Receipt> receipts;
    private Button createReciept;
    private ImageView test;
    private Folder folder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);

        test = findViewById(R.id.testImage);

        name = getIntent().getStringExtra("folderName");
        folder = GlobalFolderList.get(name);
        receipts = folder.getReceipts();
        createReciept = findViewById(R.id.createReceipt);
        createReciept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAddReceipt();
            }
        });
    }
    private void toAddReceipt(){
        Intent intent = new Intent(receiptFolder.this, addReceipt.class);
        intent.putExtra("name",name);
        startActivityForResult(intent, REQUEST_RECIEPT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECIEPT && resultCode == RESULT_OK) {
            int pos = data.getIntExtra("receiptPos",-1);
            Log.d("app",receipts.size()+" "+pos);
            if(pos != -1){
                Receipt newReceipt = receipts.get(pos);
                test.setImageBitmap(newReceipt.getPhoto());
            }
        }
    }
}
