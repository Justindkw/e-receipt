package com.example.app.ReceiptStuff;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

    private boolean deleteMode = false;
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
        else{
            holder.daysLeft.setText("");
        }
        if(deleteMode){
            holder.deleteBox.setVisibility(View.VISIBLE);
        }
        else{
            holder.deleteBox.setVisibility(View.INVISIBLE);
            holder.deleteBox.setChecked(false);
        }
        int newColor = (left>=7) ? 0:(7-left)*(100/7);
        //Log.d("left",left+" "+newColor+" "+receipts.get(position).isTimer());
        holder.daysLeft.setTextColor(Color.HSVToColor( new float[]{ 100-newColor, 0.9f, 0.6f}));
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }
    public void setDeleteMode(boolean deleteMode){
        this.deleteMode = deleteMode;
    }
    public boolean getDeleteMode(){
        return deleteMode;
    }
    public void deleteSelected(){
        ArrayList<Receipt> found = new ArrayList<>();
        for(Receipt r:receipts){
            if(r.isSelected()){
                found.add(r);
            }
        }
        receipts.removeAll(found);
    }

    //updates recycler view with a new folder
    public void notifyInsert(){
        this.notifyItemInserted(receipts.size()-1);
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddReceiptDestination(int receipt);
    }

    public class ReceiptFolderAdapterVh extends RecyclerView.ViewHolder {
        ImageView receiptPhoto;
        TextView nameText;
        TextView companyText;
        TextView daysLeft;
        CheckBox deleteBox;
        public ReceiptFolderAdapterVh(@NonNull View itemView) {
            super(itemView);
            receiptPhoto = itemView.findViewById(R.id.receiptImage);
            nameText = itemView.findViewById(R.id.date);
            companyText = itemView.findViewById(R.id.companyName);
            daysLeft = itemView.findViewById(R.id.daysLeft);
            deleteBox = itemView.findViewById(R.id.selectReceipt);
            //sets button function
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!deleteMode){
                        addButtonDestination.AddReceiptDestination(getAdapterPosition());
                    }
                }
            });
            deleteBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receipts.get(getAdapterPosition()).setSelected(deleteBox.isChecked());
                }
            });

        }
    }
}
