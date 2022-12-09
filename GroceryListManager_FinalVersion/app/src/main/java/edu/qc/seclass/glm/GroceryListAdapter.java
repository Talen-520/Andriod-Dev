package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AlertDialog;


//import edu.qc.seclass.glm.R;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolder> {

    private ArrayList<GroceryList> mGroceryLists;

    private GListClickListener mGListClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView groceryListNameTextView;
        public Button removeGroceryListButton;
        public Button renameGroceryListButton;

        // The constructor gets the views of each item for the GroceryList layout, and sets the OnClickListeners
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            groceryListNameTextView = itemView.findViewById(R.id.groceryListView);
            removeGroceryListButton = itemView.findViewById(R.id.removeButton);
            renameGroceryListButton = itemView.findViewById(R.id.renameButton);

            groceryListNameTextView.setOnClickListener(this);
            removeGroceryListButton.setOnClickListener(this);
            renameGroceryListButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (view == groceryListNameTextView) {
                mGListClickListener.GListNameClick(position);
            } else if (view == removeGroceryListButton) {
                mGListClickListener.removeClickListener(position);
            } else if (view == renameGroceryListButton) {
                mGListClickListener.renameClickListener(position);
            }
        }
    }

    public GroceryListAdapter(ArrayList<GroceryList> groceryListArray, GListClickListener listener) {
        mGroceryLists = groceryListArray;
        this.mGListClickListener = listener;
    }

    @NonNull
    @Override
    public GroceryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View groceryListView = inflater.inflate(R.layout.activity_grocery_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(groceryListView);
        return viewHolder;
    }

    //This method adds names to the different Views within the viewHolder
    @Override
    public void onBindViewHolder(@NonNull GroceryListAdapter.ViewHolder holder, int position) {
        GroceryList groceryList = mGroceryLists.get(position);

        TextView groceryListNameTextView = holder.groceryListNameTextView;
        Button removeGroceryListButton = holder.removeGroceryListButton;
        Button renameGroceryListButton = holder.renameGroceryListButton;

        groceryListNameTextView.setText((position + 1) + ". " + groceryList.getGListName());
    }

    @Override
    public int getItemCount() {
        return mGroceryLists.size();
    }

     public interface GListClickListener {
        void GListNameClick(int position);

        void removeClickListener(int position);

        void renameClickListener(int position);

        void renameGroceryList(String gListName, int position);

        //Overridden from the RemoveGroceryListDialog Class, deletes the list and it's groceryListItems from the database and redisplays the Lists
        void deleteGroceryList(int position);
    }
}