package com.example.app.ReceiptStuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

import java.util.ArrayList;

class ReceiptScreenAdapter extends RecyclerView.Adapter<ReceiptScreenAdapter.ReceiptFolderAdapterVh> {
    //list of receipt names
    private ArrayList<String> receiptNames;
    //interfaced used for start activity
    private ReceiptScreenAdapter.AddButtonDestination addButtonDestination;
    //constructor
    public ReceiptScreenAdapter(ArrayList<String> receiptNames, ReceiptScreenAdapter.AddButtonDestination addButtonDestination) {
        this.receiptNames = receiptNames;
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
        holder.nameText.setText(receiptNames.get(position));
    }

    @Override
    public int getItemCount() {
        return receiptNames.size();
    }

    //updates recycler view with a new folder
    public void addItem(String newName){
        receiptNames.add(newName);
        this.notifyItemInserted(receiptNames.size()-1);
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddButtonDestination(String name);
    }

    public class ReceiptFolderAdapterVh extends RecyclerView.ViewHolder {
        TextView nameText;
        public ReceiptFolderAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.date);
            //sets button function
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addButtonDestination.AddButtonDestination(receiptNames.get(getAdapterPosition()));
                }
            });
        }
    }
}
