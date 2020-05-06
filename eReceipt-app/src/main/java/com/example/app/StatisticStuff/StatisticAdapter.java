package com.example.app.StatisticStuff;

import android.content.res.ColorStateList;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.FolderStuff.Folder;
import com.example.app.GlobalFolderList;
import com.example.app.R;

import java.util.ArrayList;

//Justin's stuff
public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.HomeScreenAdapterVh>{
    //list of folder names
    private ArrayList<Folder> folders;
    //interfaced used for start activity
    private AddButtonDestination addButtonDestination;
    //constructor
    public StatisticAdapter(AddButtonDestination addButtonDestination) {
        this.folders = GlobalFolderList.retrieveBudgetableFolders();
        this.addButtonDestination = addButtonDestination;
    }
    //LUwUcas help I donno what to write!
    @NonNull
    @Override
    public StatisticAdapter.HomeScreenAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeScreenAdapterVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_statistics_recycler_view,null));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull HomeScreenAdapterVh holder, int position) {
        //sets variables needed
        Folder fold = folders.get(position);
        int percent = (int)(fold.getSpending()/fold.getBudget()*100);
        //sets the name of the folder on top of the progress bar
        holder.nameText.setText(fold.toString());
        //sets the percent spent on top of the progress bar
        String display = percent+"%";
        holder.percentDisplay.setText(display);
        //sets progress bar's progress and changes the color of the bar accordingly
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.bar.setProgress(percent, true);
        }
        holder.bar.setProgressTintList(ColorStateList.valueOf(fold.getColor()));
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }
    //interface to set button function
    public interface AddButtonDestination {
        void AddButtonDestination(String name,int color);
    }

    public class HomeScreenAdapterVh extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView percentDisplay;
        ProgressBar bar;
        public HomeScreenAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.budgetFolderName);
            percentDisplay = itemView.findViewById(R.id.folderPercent);
            bar = itemView.findViewById(R.id.budgetBar);
            //sets button function
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                addButtonDestination.AddButtonDestination(folders.get(getAdapterPosition()).toString(),folders.get(getAdapterPosition()).getColor());
                }
            });
        }
    }
}
