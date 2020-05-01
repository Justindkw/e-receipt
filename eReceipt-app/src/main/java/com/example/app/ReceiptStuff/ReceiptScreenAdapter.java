package com.example.app.ReceiptStuff;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

class ReceiptScreenAdapter extends RecyclerView.Adapter<ReceiptScreenAdapter.ReceiptFolderAdapterVh> {
    //list of receipt names
    private ArrayList<Receipt> receipts;
    //interfaced used for start activity
    private ReceiptScreenAdapter.AddButtonDestination addButtonDestination;
    //constructor
    public ReceiptScreenAdapter(ArrayList<Receipt> receipts, ReceiptScreenAdapter.AddButtonDestination addButtonDestination) {
        this.receipts = receipts;
        this.addButtonDestination = addButtonDestination;
    }

    //I believe this returns the Recycler View's content (in this case, it is the receipts that are the content)
    @NonNull
    @Override
    public ReceiptScreenAdapter.ReceiptFolderAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReceiptFolderAdapterVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_receipt_folder_recycler_view,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptScreenAdapter.ReceiptFolderAdapterVh holder, int position) {
        holder.nameText.setText(new SimpleDateFormat("MMM dd, yyyy").format(receipts.get(position).getDate()));
        holder.receiptPhoto.setImageBitmap(receipts.get(position).getPhoto());
        holder.companyText.setText(receipts.get(position).getCompany());
        int left = receipts.get(position).getRefundDaysLeft();
        if(receipts.get(position).isTimer()){
            holder.daysLeft.setText(left+" days left");
        }
        int newColor = (left>10) ? 0:(10-left)*10;
        holder.daysLeft.setTextColor(Color.HSVToColor( new float[]{ 100-newColor, 100, 100}));
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    //updates recycler view with a new folder
    public void notifyInsert(){
        this.notifyItemInserted(receipts.size()-1);
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddReceiptDestination(String name);
    }

    public class ReceiptFolderAdapterVh extends RecyclerView.ViewHolder {
        ImageView receiptPhoto;
        TextView nameText;
        TextView companyText;
        TextView daysLeft;
        public ReceiptFolderAdapterVh(@NonNull View itemView) {
            super(itemView);
            receiptPhoto = itemView.findViewById(R.id.receiptImage);
            nameText = itemView.findViewById(R.id.date);
            companyText = itemView.findViewById(R.id.companyName);
            daysLeft = itemView.findViewById(R.id.daysLeft);
            //sets button function
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addButtonDestination.AddReceiptDestination(receipts.get(getAdapterPosition()).getCompany());
                }
            });
        }
    }
}
