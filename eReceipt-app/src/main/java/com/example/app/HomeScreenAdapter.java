package com.example.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.HomeScreenAdapterVh>{

    private ArrayList<Folder> folderList;
    private Context context;
    private SelectedUser selectedUser;

    public HomeScreenAdapter(ArrayList<Folder> folderList, SelectedUser selectedUser) {
        this.folderList = folderList;
        this.selectedUser = selectedUser;
    }

    @NonNull
    @Override
    public HomeScreenAdapter.HomeScreenAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new HomeScreenAdapterVh(LayoutInflater.from(context).inflate(R.layout.activity_home_screen_recycler_view,null));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenAdapter.HomeScreenAdapterVh holder, int position) {

        Folder folder = folderList.get(position);
        String foldeName = folder.toString();
        holder.tvUsername.setText(foldeName);

    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }




    public interface SelectedUser{

        void selectedUser(String name);

    }

    public class HomeScreenAdapterVh extends RecyclerView.ViewHolder {
        TextView tvUsername;
        public HomeScreenAdapterVh(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(folderList.get(getAdapterPosition()).toString());
                }
            });


        }
    }

}
