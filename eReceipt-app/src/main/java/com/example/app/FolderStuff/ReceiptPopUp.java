package com.example.app.FolderStuff;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.GlobalFolderList;
import com.example.app.R;
import com.example.app.ReceiptStuff.Receipt;

import java.text.SimpleDateFormat;

public class ReceiptPopUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("I am in","i");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_pop_up);
        Receipt receipt = GlobalFolderList.get(getIntent().getStringExtra("folder")).getReceipt(getIntent().getIntExtra("receipt", 0));
        ((ImageView)findViewById(R.id.receiptImage)).setImageBitmap(receipt.getPhoto());
        ((TextView)findViewById(R.id.receiptCompanyName)).setText(receipt.getCompany());
        ((TextView)findViewById(R.id.receiptMadeDate)).setText(new SimpleDateFormat("MMM dd, yyyy").format(receipt.getDate()));
        ((TextView)findViewById(R.id.receiptDueDate)).setText(new SimpleDateFormat("MMM dd, yyyy").format(receipt.getRefundDate()));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double width = dm.widthPixels*0.6;
        double height = dm.heightPixels*0.6;
        getWindow().setLayout((int)width,(int)height);
        Log.d("pop",dm.heightPixels+" "+dm.widthPixels);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.dimAmount=0.7f;
        params.gravity = Gravity.CENTER;
        Log.d("params", params.dimAmount+"");
        getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
