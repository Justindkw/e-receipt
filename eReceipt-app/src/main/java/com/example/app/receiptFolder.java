package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class receiptFolder extends AppCompatActivity {
    static final int REQUEST_RECIEPT = 1;
    //GlobalFolderList folderList = (GlobalFolderList)getApplication();
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_folder);
        name = getIntent().getParcelableExtra("folderName");
    }
    private void toAddFolder(){
        startActivityForResult(new Intent(receiptFolder.this, addReceipt.class), REQUEST_RECIEPT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECIEPT && resultCode == RESULT_OK) {
            Receipt newReceipt = (Receipt)data.getParcelableExtra("newReceipt");
            if(newReceipt != null){
                GlobalFolderList.get(name).addReceipt(newReceipt);
            }
        }
    }
}
