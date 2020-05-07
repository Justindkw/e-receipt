package com.example.app.ReceiptStuff;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ReceiptPopUp extends AppCompatActivity {
    Switch timer;
    Receipt receipt;
    private boolean checked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_pop_up);

        receipt = GlobalFolderList.get(getIntent().getStringExtra("folder")).getReceipt(getIntent().getIntExtra("receipt", 0));

        ((ImageView)findViewById(R.id.receiptImage)).setImageBitmap(receipt.getPhoto());

        ((TextView)findViewById(R.id.receiptCompanyName)).setText(receipt.getCompany());

        ((TextView)findViewById(R.id.receiptPrice)).setText("$"+new DecimalFormat("0.00").format(receipt.getCost()));

        ((TextView)findViewById(R.id.receiptMadeDate)).setText(new SimpleDateFormat("MMM dd, yyyy").format(receipt.getDate()));

        if(receipt.isTimer()) {
            ((TextView) findViewById(R.id.receiptDueDate)).setText("Due: "+new SimpleDateFormat("MMM dd, yyyy").format(receipt.getRefundDate()));
        }
        timer = findViewById(R.id.enableTimerPopUp);
        if(receipt.isTimer()){
            timer.setAlpha(1.0f);
            timer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checked = isChecked;
                }
            });
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double width = dm.widthPixels*0.6;
        double height = dm.heightPixels*0.8;
        getWindow().setLayout((int)width,(int)height);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.dimAmount=0.7f;
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            setResult(RESULT_OK, new Intent().putExtra("receiptPosition",getIntent().getIntExtra("receipt", -1)).putExtra("timer",checked));
            finish();
        }
        return true;
    }
}
