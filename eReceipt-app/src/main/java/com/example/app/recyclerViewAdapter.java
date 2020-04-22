package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mFolderNames = new ArrayList<>();
    private Context mContext;

    public recyclerViewAdapter(Context mContext, ArrayList<String> mFolderNames) {
        this.mFolderNames = mFolderNames;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_screen_recycler_view, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        holder.folderName.setText(mFolderNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mFolderNames.get(position));

                Toast.makeText(mContext, mFolderNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, FolderFile.class);
                intent.putExtra("name", mFolderNames.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFolderNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button folderButton;
        TextView folderName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderButton = itemView.findViewById(R.id.info_folder);
            folderName = itemView.findViewById(R.id.info_text);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
