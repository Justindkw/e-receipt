package com.example.app.FolderStuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

import java.util.ArrayList;
//Justin's stuff
public class FolderScreenAdapter extends RecyclerView.Adapter<FolderScreenAdapter.HomeScreenAdapterVh>{
    //list of folder names
    private ArrayList<String> folderNames;
    //interfaced used for start activity
    private AddButtonDestination addButtonDestination;
    //constructor
    public FolderScreenAdapter(ArrayList<String> folderNames, AddButtonDestination addButtonDestination) {
        this.folderNames = folderNames;
        this.addButtonDestination = addButtonDestination;
    }
    //LUwUcas help I donno what to write!
    @NonNull
    @Override
    public FolderScreenAdapter.HomeScreenAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeScreenAdapterVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_folder_screen_recycler_view,null));
    }

    @Override
    public void onBindViewHolder(@NonNull FolderScreenAdapter.HomeScreenAdapterVh holder, int position) {
        holder.nameText.setText(folderNames.get(position));
    }

    @Override
    public int getItemCount() {
        return folderNames.size();
    }
    //updates recycler view with a new folder
    public void addItem(String newName){
        folderNames.add(newName);
        this.notifyItemInserted(folderNames.size()-1);
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddButtonDestination(String name);
    }

    public class HomeScreenAdapterVh extends RecyclerView.ViewHolder {
        TextView nameText;
        public HomeScreenAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.info_text);
            //sets button function
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                addButtonDestination.AddButtonDestination(folderNames.get(getAdapterPosition()));
                }
            });
        }
    }
}
