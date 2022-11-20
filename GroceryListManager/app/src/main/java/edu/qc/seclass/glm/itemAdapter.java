package edu.qc.seclass.glm;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerylistmanager.R;

import java.util.ArrayList;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<itemDBModel> items;

    public itemAdapter(ArrayList<itemDBModel> items, Context context){
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public itemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_grocery_list3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter.ViewHolder holder, int position) {
        itemDBModel model = items.get(position);
        holder.itemNameTV.setText(model.getItemName());
        holder.itemTypeTV.setText(model.getItemUnit());
        holder.itemUnitTV.setText(model.getItemUnit());
        holder.itemQTYTV.setText(model.getItemQTY());
        holder.itemSelTV.setText(model.getItemSel());
        holder.itemChkTV.setText(model.getItemChk());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView itemNameTV, itemTypeTV, itemUnitTV, itemQTYTV, itemSelTV, itemChkTV;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            itemNameTV = itemView.findViewById(R.id.itemNameTV);
            itemTypeTV = itemView.findViewById(R.id.itemTypeTV);
            itemUnitTV = itemView.findViewById(R.id.itemUnitTV);
            itemQTYTV = itemView.findViewById(R.id.itemQTYTV);
            itemSelTV = itemView.findViewById(R.id.itemSelTV);
            itemChkTV = itemView.findViewById(R.id.itemChkTV);
        }
    }
}
