package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenAdapterVh>{

    private ArrayList<String> folderNames;
    private AddButtonDestination addButtonDestination;

    public HomeScreenAdapter(ArrayList<String> folderNames, AddButtonDestination addButtonDestination) {
        this.folderNames = folderNames;
        this.addButtonDestination = addButtonDestination;
    }

    @NonNull
    @Override
    public HomeScreenAdapter.HomeScreenAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeScreenAdapterVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_screen_recycler_view,null));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenAdapter.HomeScreenAdapterVh holder, int position) {
        holder.nameText.setText(folderNames.get(position));
    }

    @Override
    public int getItemCount() {
        return folderNames.size();
    }

    public void addItem(String newName){
        folderNames.add(newName);
        this.notifyItemInserted(folderNames.size()-1);
    }

    public interface AddButtonDestination {
        void AddButtonDestination(String name);
    }

    public class HomeScreenAdapterVh extends RecyclerView.ViewHolder {
        TextView nameText;
        public HomeScreenAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                addButtonDestination.AddButtonDestination(folderNames.get(getAdapterPosition()));
                }
            });
        }
    }
}
