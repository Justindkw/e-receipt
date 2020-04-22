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

    private ArrayList<String> mFolderNames;
    private OnFolderListener mOnFolderListener;
    private Context mContext;

    public recyclerViewAdapter(Context mContext, ArrayList<String> mFolderNames, OnFolderListener mOnFolderListener) {
        this.mFolderNames = mFolderNames;
        this.mContext = mContext;
        this.mOnFolderListener = mOnFolderListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_screen_recycler_view, parent, false);

        return new ViewHolder(view, mOnFolderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        holder.folderName.setText(mFolderNames.get(position));

    }

    @Override
    public int getItemCount() {
        return mFolderNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button folderButton;
        TextView folderName;
        RelativeLayout parentLayout;
        OnFolderListener onFolderListener;

        public ViewHolder(@NonNull View itemView, OnFolderListener onFolderListener) {
            super(itemView);
            folderButton = itemView.findViewById(R.id.info_folder);
            folderName = itemView.findViewById(R.id.info_text);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            this.onFolderListener = onFolderListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFolderListener.onFolderClick(getAdapterPosition());
        }
    }

    public interface OnFolderListener {
        void onFolderClick(int position);
    }

}
