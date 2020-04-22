package com.example.app;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class BudgetingAdapter extends RecyclerView.Adapter<BudgetingAdapter.MyViewHolder> {

        private LayoutInflater inflater;
        public static ArrayList<Folder> folderArrayList;
        private static boolean[] areDoubles;
        private static double[] budgets;


        public BudgetingAdapter(Context ctx, ArrayList<Folder> folderArrayList){

            inflater = LayoutInflater.from(ctx);
            this.folderArrayList = folderArrayList;
            areDoubles = new boolean[folderArrayList.size()];
            budgets = new double[folderArrayList.size()];
        }

        public static boolean correctBudgets(){
            for(boolean b : areDoubles) if(!b) return false;
            return true;
        }
        public static void finalizeBudgets(){
            for(int i =0; i<budgets.length;i++){
                folderArrayList.get(i).setTotalBudget(budgets[i]);
            }
        }
        @Override
        public BudgetingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.activity_budgeting_recycler_view, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final BudgetingAdapter.MyViewHolder holder, final int position) {


            holder.editText.setText(String.valueOf(folderArrayList.get(position).getTotalBudget()));
            holder.folderName.setText(folderArrayList.get(position).toString());
            Log.d("print","yes");

        }

        @Override
        public int getItemCount() {
            return folderArrayList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            protected EditText editText;
            protected TextView folderName;

            public MyViewHolder(View itemView) {
                super(itemView);

                editText = (EditText) itemView.findViewById(R.id.editBudget);
                folderName = (TextView) itemView.findViewById(R.id.folderNameBudget);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try{
                            budgets[getAdapterPosition()] = Double.valueOf(editText.getText().toString());
                            editText.setTextColor(Color.BLACK);
                            areDoubles[getAdapterPosition()] = true;
                        }
                        catch (NumberFormatException e){
                            editText.setTextColor(Color.RED);
                            areDoubles[getAdapterPosition()] = false;
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });

            }

        }
}
