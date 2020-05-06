package com.example.app;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.app.ReceiptStuff.Receipt;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Extras {
    public static void sort(List<Receipt> receipts,int type){
        switch (type) {
            case 0:
                Collections.sort(receipts, new Comparator<Receipt>() {
                    @Override
                    public int compare(Receipt o1, Receipt o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });
                break;
            case 1:
                Collections.sort(receipts, new Comparator<Receipt>() {
                    @Override
                    public int compare(Receipt o1, Receipt o2) {
                        return o1.getCompany().toLowerCase().compareTo(o2.getCompany().toLowerCase());
                    }
                });
                break;
            case 2:
                Collections.sort(receipts, new Comparator<Receipt>() {
                    @Override
                    public int compare(Receipt o1, Receipt o2) {
                        return Double.compare(o1.getCost(), o2.getCost());
                    }
                });
                break;
            case 3:
                Collections.sort(receipts, new Comparator<Receipt>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public int compare(Receipt o1, Receipt o2) {
                        return !o1.isTimer() ? 1 : !o2.isTimer()? -1 : Integer.compare(o1.getRefundDaysLeft(), o2.getRefundDaysLeft());
                    }
                });
        }
    }
}
