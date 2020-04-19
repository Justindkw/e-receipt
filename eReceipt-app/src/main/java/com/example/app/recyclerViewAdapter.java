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
    //RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {
//
//
////    private ArrayList<recyclerViewData> data; //the ArrayList which contains the data form recyclerViewData is called "data"
////    private AdapterView.OnItemClickListener mListener;
////    private String folderName;
////
////    public recyclerViewAdapter(ArrayList<recyclerViewData> data) {
////        this.data = data;
////    }
////
////    @NonNull
////    @Override
////    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //removed @Nonnull before "ViewGroup parent"
////        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_screen_recycler_view, parent, false);
////
////        return new ViewHolder(v);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
////        recyclerViewData setData = data.get(position);
////
////        holder.folderText.setText(setData.getText());
////        holder.folderId.setId(setData.getButtonId()); //setId works for some reason. i tried setButtonId which didn't work
////        holder.folderId.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                newIntent();
////            }
////        });
////    }
////
////    @Override
////    public int getItemCount() {
////        if (data != null) {
////            return data.size();
////        } else {
////            return 0;
////        }
////    }
////
////    public static class ViewHolder extends RecyclerView.ViewHolder{ //possibly change "RecyclerView" to "recyclerViewAdapter"
////        public final View view ; //idk what this is for but i think i need it
////        public final TextView folderText; //the folder's text
////        public final Button folderId; //the folder's id
////
////        public ViewHolder(View view) {
////            super(view);
////            this.view = view;
////            folderText = view.findViewById(R.id.info_text); //folderText has been set to the folder's name
////            folderId = view.findViewById(R.id.info_folder); //folderId has been set to the button
////        }
////
//////        public void bind(final TextView folderText, final Button folderId) {
//////            folderText.setText(view.getText());
//////            folderId.setId(view.getId());
//////            Picasso.with(itemView.getContext()).load(folderText)
//////
//////        }
////
////    }
////
////    private void newIntent() {
////        Intent intent  = new Intent();
////        //intent.putExtra(folderName, homeScreen.getFolders().get(folderName));
////    }//make sure folderName is the name of the folder
////
////    //allows click events to be caught
////    void setClickListner(AdapterView.OnItemClickListener itemClickListener) {
////        this.mListener = itemClickListener;
////    }
////
////    // parent activity will implement this method to respond to click events
////    public interface ItemClickListener {
////        void onItemClick(View view, int position);
////    }
//
////    private LayoutInflater mInflater;
////    private String[] mData;
////    private ItemClickListener mClickListener; //keep above 3
////
////    //new things
////    private ArrayList<City> text;
////    public final View view;
////    public final TextView name;
////    public final R.id buttonID;
////
////    // data is passed into the constructor
////    public recyclerViewAdapter(Context context, String[] data) {
////        this.mInflater = LayoutInflater.from(context);
////        this.mData = data;
//////        super(view);
//////        this.view = view;
//////        name = view.findViewById(R.id.info_text);
//////        buttonID = view.findViewById(R.id.info_folder);
////
////        //this.mClickListener = mClickListener;
////        //this.buttonID = buttonID;
////    }
////
////
////    //
//////    // data is passed into the constructor
//////    recyclerViewAdapter(Context context, String[] data, Button button) {
//////        this.mInflater = LayoutInflater.from(context);
//////        this.mData = data;
//////        this.buttonID = button;
//////    }
////
////    // inflates the cell layout from xml when needed
////    @Override
////    @NonNull
////    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = mInflater.from(parent.getContext()).inflate(R.layout.activity_home_screen_recycler_view, parent, false);
////        return new ViewHolder(view);
////    }
////
////    // binds the data to the TextView in each cell
////    @Override
////    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
////        recyclerViewData city = text.get(position);
////
////        holder.name.setText(city.getName(city.getName()));
////        holder.id.setId(city.getButtonId());
////
////        //holder.myTextView.setText(mData[position]);
////    }
////
////    // total number of cells
////    @Override
////    public int getItemCount() {
////        if (mData != null) {
////            return text.size();
////        } else {
////            return 0;
////        }
////    }
////
////
////    // stores and recycles views as they are scrolled off screen
////    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
////        TextView myTextView;
////        R.id buttonId;
////
////        ViewHolder(View itemView) {
////            super(itemView);
////            myTextView = itemView.findViewById(R.id.info_text);
////            buttonId = itemView.findViewById(R.id.info_folder);
////
////            itemView.setOnClickListener(this);
////        }
////
////        @Override
////        public void onClick(View view) {
////            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
////        }
////    }
////
////    // convenience method for getting data at click position
////    String getItem(int id) {
////        return mData[id];
////    }
////
////    // allows clicks events to be caught
////    void setClickListener(ItemClickListener itemClickListener) {
////        this.mClickListener = itemClickListener;
////    }
////
////    // parent activity will implement this method to respond to click events
////    public interface ItemClickListener {
////        void onItemClick(View view, int position);
////    }
}
