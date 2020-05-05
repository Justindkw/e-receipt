package com.example.app.ReceiptStuff;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app.R;

public class ReceiptWindow extends Fragment {
    public ReceiptWindow() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getActivity().getIntent();
        Bitmap receipt = i.getParcelableExtra("image");
        ((ImageView)getView().findViewById(R.id.receiptImage)).setImageBitmap(receipt);
        Log.d("company",i.getStringExtra("date"));
        ((TextView)getView().findViewById(R.id.receiptCompanyName)).setText(i.getStringExtra("company"));
        ((TextView)getView().findViewById(R.id.receiptMadeDate)).setText(i.getStringExtra("date"));
        ((TextView)getView().findViewById(R.id.receiptDueDate)).setText(i.getStringExtra("dueDate"));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        double width = dm.widthPixels*0.6;
//        double height = dm.heightPixels*0.6;
//        getWindow().setLayout((int)width,(int)height);

        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.dimAmount=0.7f;
        params.gravity = Gravity.CENTER;
        getActivity().getWindow().setAttributes(params);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
