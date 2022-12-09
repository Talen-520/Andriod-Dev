package edu.qc.seclass.glm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class ItemAddedAdapter extends RecyclerView.Adapter<ItemAddedAdapter.ViewHolder> {
    private ArrayList<item> items;
    private ItemAddedClickListener gAddingToListClickListener;
    //this adapter is in charge in populate the view in activity of which item is called and is being added to the list
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView AddingToListTextView;
        public Button AddingToListButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            AddingToListTextView = itemView.findViewById(R.id.itemAddToListTextview);

            AddingToListButton = itemView.findViewById(R.id.itemAddToListButton);
            AddingToListButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            gAddingToListClickListener.onItemAddedToListClick(position);
        }
    }

    public ItemAddedAdapter(ArrayList<item> items, ItemAddedAdapter.ItemAddedClickListener listener) {
        this.items = items;
        gAddingToListClickListener = listener;
    }

    @NonNull
    @Override
    public ItemAddedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View AddingToListView = inflater.inflate(R.layout.additem, parent, false);

        ItemAddedAdapter.ViewHolder viewHolder = new ItemAddedAdapter.ViewHolder(AddingToListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddedAdapter.ViewHolder holder, int position) {
        item item = items.get(position);

        TextView AddingToListTextView = holder.AddingToListTextView;

        AddingToListTextView.setText((position + 1) + ". " + item.getItemName());

        Button AddingToListButton = holder.AddingToListButton;
        AddingToListButton.setText("Add this item");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemAddedClickListener {
        void onItemAddedToListClick(int position);
    }
}