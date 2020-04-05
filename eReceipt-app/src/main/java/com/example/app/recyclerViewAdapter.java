package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {

    private String[] mData;
    private LayoutInflater mInflater;
    private  ItemClickListener mClickListener;

    // data is passed into the constructor
    recyclerViewAdapter(Context context, String[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = mInflater.from(mContext).inflate(R.layout.activity_home_screen_recycler_view, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView.setText(mData[position]); //probably don't need this
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        Button myFolderButton;

        ViewHolder(View itemView) {
            super(itemView);

            myTextView = itemView.findViewById(R.id.info_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            int itemPosition = recyclerViewAdapter.getChildLayoutPosition(view);
            String item = mList.get(itemPosition);
            Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
