package com.example.app.BudgetingStuff;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.FolderStuff.Folder;
import com.example.app.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BudgetingAdapter extends RecyclerView.Adapter<BudgetingAdapter.MyViewHolder> {
//Justin's stuff
    private LayoutInflater inflater;
    //all the folders
    public ArrayList<Folder> folderArrayList;
    //array of user input status. false means the user input isn't double, true means it is
    private boolean[] areDoubles;
    //array of all budget
    private double[] budgets;
    //constructor
    public BudgetingAdapter(Context ctx, ArrayList<Folder> folderArrayList){
        inflater = LayoutInflater.from(ctx);
        this.folderArrayList = folderArrayList;
        //make arrays of same size as folderArrayList
        areDoubles = new boolean[folderArrayList.size()];
        budgets = new double[folderArrayList.size()];
    }
    //checks for user input mistakes
    public boolean correctBudgets(){
        for(boolean b : areDoubles) if(!b) return false;
        return true;
    }
    //sets budgets
    public void finalizeBudgets(){
        for(int i =0; i<budgets.length;i++){
            folderArrayList.get(i).setBudget(budgets[i]);
        }
    }
    //LUCAS HELP ME WITH THIS RECYCLERVIEW STUFF
    @Override
    public BudgetingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_budgeting_recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BudgetingAdapter.MyViewHolder holder, final int position) {
        holder.editText.setText(new DecimalFormat("0.00").format(folderArrayList.get(position).getBudget()));
        holder.folderName.setText(folderArrayList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return folderArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private EditText editText;
        private TextView folderName;

        private MyViewHolder(View itemView) {
            super(itemView);
            //finds stuff from xml
            editText = itemView.findViewById(R.id.editBudget);
            folderName = itemView.findViewById(R.id.folderNameBudget);
            //adds listener to user input
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                //checks while user is giving input
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try{
                        //attempts to make user input string to double. If it is double then the text color is black and sets the input status to true
                        budgets[getAdapterPosition()] = Double.valueOf(editText.getText().toString());
                        editText.setTextColor(Color.BLACK);
                        areDoubles[getAdapterPosition()] = true;
                    }
                    catch (NumberFormatException e){
                        //string cannot be converted to double. Changes text color to red and sets the input status to false
                        editText.setTextColor(Color.RED);
                        areDoubles[getAdapterPosition()] = false;
                    }
                }
                //useless. Basically does the same thing
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }
}
