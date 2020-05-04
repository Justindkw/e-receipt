package com.example.app.FolderStuff;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;

public class ReceiptPopUp extends AppCompatActivity {
    private Bitmap receipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_pop_up);

        Intent i = getIntent();
        receipt = i.getParcelableExtra("image");
        ((ImageView)findViewById(R.id.receiptImage)).setImageBitmap(receipt);
        Log.d("company",i.getStringExtra("date"));
        ((TextView)findViewById(R.id.receiptCompanyName)).setText(i.getStringExtra("company"));
        ((TextView)findViewById(R.id.receiptMadeDate)).setText(i.getStringExtra("date"));
        ((TextView)findViewById(R.id.receiptDueDate)).setText(i.getStringExtra("dueDate"));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double width = dm.widthPixels*0.6;
        double height = dm.heightPixels*0.6;
        getWindow().setLayout((int)width,(int)height);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.dimAmount=0.7f;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
