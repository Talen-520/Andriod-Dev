package edu.qc.seclass.glm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerylistmanager.R;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private final List<itemDBModel> values;
    private int listId;
    private Context context;

    public listAdapter(List<itemDBModel> values, int listId, Context context) {
        this.values = values;
        this.listId = listId;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_grocery_list3, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }




    public void onBindViewHolder(final ViewHolder holder, int position){
        holder.item = values.get(position);
        holder.idView.setText(values.get(position).getItemName());
       // final int itemId = holder.item.getitemId();

        String type = holder.item.getItemType();
        holder.type.setText(type);

        itemDB dbh = itemDB.getInstance(context);
   //     holder.quantity.setText(String.valueOf(dbh.getQuantity(itemId, listId)));

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemDB dbh = itemDB.getInstance(context);
                int itemId = 0;
                int qty = dbh.getQuantity(itemId, listId);
                if(qty == 0){
                    dbh.addListItem(holder.item.getItemName(), 1, itemId, listId);
                }
                else{
                    dbh.increaseQuantity(itemId, listId, qty);
                }

                holder.quantity.setText(String.valueOf(dbh.getQuantity(itemId, listId)));
            }
        });
    }

    @Override
    public int getItemCount(){
        return values.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idView, quantity, addButton, type;
        public itemDBModel item;

        public ViewHolder(View view){
            super(view);
            idView = view.findViewById(R.id.itemNameTV);
            quantity = view.findViewById(R.id.itemQTYTV);
            type = view.findViewById(R.id.itemTypeTV);
            addButton = view.findViewById(R.id.addByType);
        }

    }
}