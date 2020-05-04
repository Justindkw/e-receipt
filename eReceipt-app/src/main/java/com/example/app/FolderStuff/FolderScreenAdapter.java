package com.example.app.FolderStuff;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.util.ArrayList;
//Justin's stuff
public class FolderScreenAdapter extends RecyclerView.Adapter<FolderScreenAdapter.HomeScreenAdapterVh>{
    //list of folder names
    private ArrayList<String> folderNames;
    private ArrayList<Integer> checkedFolders;
    //interface used for start activity
    private AddButtonDestination addButtonDestination;

    private boolean deleteMode = false;
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
        int notif = GlobalFolderList.get(folderNames.get(position)).totalNotification();
        if(notif!=0){
            holder.notification.setText(String.valueOf(notif));
            holder.notification.setVisibility(View.VISIBLE);
        }
        else{
            holder.notification.setVisibility(View.INVISIBLE);
        }
        if(deleteMode){
            holder.deleteBox.setVisibility(View.VISIBLE);
        }
        else{
            holder.deleteBox.setVisibility(View.INVISIBLE);
            holder.deleteBox.setChecked(false);
        }
        holder.buttonLayout.getBackground().mutate().setColorFilter(GlobalFolderList.get(folderNames.get(position)).getColor(), PorterDuff.Mode.SRC_ATOP);
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
    public void setDeleteMode(boolean deleteMode){
        this.deleteMode = deleteMode;
    }
    public boolean getDeleteMode(){
        return deleteMode;
    }
    public void deleteSelected(){
        ArrayList<String> found = new ArrayList<>();
        for(String folder:folderNames){
            if(GlobalFolderList.get(folder).isSelected()){
                found.add(folder);
                GlobalFolderList.remove(folder);
            }
        }
        folderNames.removeAll(found);
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddButtonDestination(String name);
    }

    public class HomeScreenAdapterVh extends RecyclerView.ViewHolder {
        TextView nameText;
        CheckBox deleteBox;
        ConstraintLayout buttonLayout;
        TextView notification;
        public HomeScreenAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.info_text);
            deleteBox = itemView.findViewById(R.id.deleteBox);
            buttonLayout = itemView.findViewById(R.id.folderLayout);
            notification = itemView.findViewById(R.id.timerNotification);
            //sets button function
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!deleteMode){
                        addButtonDestination.AddButtonDestination(folderNames.get(getAdapterPosition()));
                    }
                }
            });
            deleteBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalFolderList.get(folderNames.get(getAdapterPosition())).setSelected(deleteBox.isChecked());
                }
            });
        }
    }
}
