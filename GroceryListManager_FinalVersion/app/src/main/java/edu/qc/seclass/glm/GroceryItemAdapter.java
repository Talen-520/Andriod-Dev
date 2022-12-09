package edu.qc.seclass.glm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
//this adapter is in charge of populating the textview with the items after it was added
//it will specify and pull the required layout for each grocery list item, for example, the item quantity can be edited

public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder> {
    private boolean groupedByType;
    private ArrayList<GroceryListItem> mListItem;
    private ItemClickListener mListItemClickListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ItemnameTextView;
        public TextView ItemQuantityTextView;
        public TextView ItemUnitTextView;
        public Button ItemQuantityAndUnitButton;
        public Button deleteItemButton;
        public CheckBox isCheckedCheckBox;
       public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ItemnameTextView = itemView.findViewById(R.id.groceryItemNameTextView);
            ItemQuantityTextView = itemView.findViewById(R.id.groceryItemQuantityTextView);
            ItemUnitTextView = itemView.findViewById(R.id.groceryItemUnitTextView);
            ItemQuantityAndUnitButton = itemView.findViewById(R.id.changeItemQuantityAndUnitButton);
            deleteItemButton = itemView.findViewById(R.id.deleteItemButton);
            isCheckedCheckBox = itemView.findViewById(R.id.isCheckedCheckBox);
            //buttons/textViews that appear alongside the grocery item
            ItemnameTextView.setOnClickListener(this);
            ItemQuantityTextView.setOnClickListener(this);
            ItemUnitTextView.setOnClickListener(this);
            ItemQuantityAndUnitButton.setOnClickListener(this);
            deleteItemButton.setOnClickListener(this);
            isCheckedCheckBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (view == deleteItemButton) {
                mListItemClickListener.DeleteItemButtonClickListener(position);
            } else if (view == isCheckedCheckBox) {
                mListItemClickListener.onIsCheckedCheckBoxClickListener(position);
            } else if (view == ItemQuantityAndUnitButton) {
                mListItemClickListener.onQuantityAndUnitButtonClickListener(position);
            }
        }
    }



    @NonNull
    @Override
    public GroceryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listItemListView = inflater.inflate(R.layout.grocery_list_item, parent, false);

        ViewHolder viewHolder = new GroceryItemAdapter.ViewHolder(listItemListView);
        return viewHolder;
    }

    public GroceryItemAdapter(ArrayList<GroceryListItem> listItems, GroceryItemAdapter.ItemClickListener listener, boolean groupedByType) {
        mListItem = listItems;
        this.mListItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryItemAdapter.ViewHolder holder, int position) {
        GroceryListItem groceryListItem = mListItem.get(position);


        TextView ItemnameTextView = holder.ItemnameTextView;
        TextView ItemQuantityTextView = holder.ItemQuantityTextView;
        TextView ItemUnitTextView = holder.ItemUnitTextView;
        Button deleteItemButton = holder.deleteItemButton;
        Button ItemQuantityAndUnitButton = holder.ItemQuantityAndUnitButton;

        CheckBox isCheckedCheckBox = holder.isCheckedCheckBox;
         ItemnameTextView.setText((position + 1) + ". " + groceryListItem.getItem().getItemName());

        if (groceryListItem.getQuantity() != 0) {
            ItemQuantityTextView.setText("QTY: " + Integer.toString(groceryListItem.getQuantity()));
            ItemUnitTextView.setText(groceryListItem.getQuantityUnit());
        } else {
            ItemQuantityTextView.setText("");
            ItemUnitTextView.setText("");
        }

        deleteItemButton.setText("Delete");
        ItemQuantityAndUnitButton.setText("Edit");
        isCheckedCheckBox.setChecked(mListItem.get(position).isChecked());


    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    public interface ItemClickListener {

        void DeleteItemButtonClickListener(int position);

        void onIsCheckedCheckBoxClickListener(int position);

        void onQuantityAndUnitButtonClickListener(int position);
    }

}


