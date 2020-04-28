package com.example.app.ReceiptStuff;

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
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    //updates recycler view with a new folder
    public void updateView(){
        this.notifyItemInserted(receipts.size()-1);
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddReceiptDestination(String name);
    }

    public class ReceiptFolderAdapterVh extends RecyclerView.ViewHolder {
        ImageView receiptPhoto;
        TextView nameText;
        public ReceiptFolderAdapterVh(@NonNull View itemView) {
            super(itemView);
            receiptPhoto = itemView.findViewById(R.id.receiptImage);
            nameText = itemView.findViewById(R.id.date);
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
