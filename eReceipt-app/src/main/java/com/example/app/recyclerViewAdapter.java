package com.example.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mFolderNames;
    private OnFolderListener mOnFolderListener;
    private Context mContext;
    private ButtonOnClick buttonOnClick;

    public recyclerViewAdapter(Context mContext, ArrayList<String> mFolderNames, OnFolderListener mOnFolderListener, ButtonOnClick buttonOnClick) {
        this.mFolderNames = mFolderNames;
        this.mContext = mContext;
        this.mOnFolderListener = mOnFolderListener;
        this.buttonOnClick = buttonOnClick;

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

//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: clicked on: " + mFolderNames.get(position));
//
//                Toast.makeText(mContext, mFolderNames.get(position), Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(mContext, homeScreen.class);
//                intent.putExtra("name", mFolderNames.get(position));
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mFolderNames.size();
    }
    public interface ButtonOnClick{
        void ButtonOnClick(String string);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button folderButton;
        TextView folderName;
        RelativeLayout parentLayout;
        OnFolderListener onFolderListener;

        public ViewHolder(@NonNull View itemView, OnFolderListener onFolderListener) {
            super(itemView);
            folderButton = itemView.findViewById(R.id.info_folder);
            Log.d("name",mFolderNames.get(getAdapterPosition()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonOnClick.ButtonOnClick(mFolderNames.get(getAdapterPosition()));
                }
            });
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
