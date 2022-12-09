
package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//This class allows the user to add an item by a specified item type by using clicking on the item.
//The user will prompted with quantity and unit dialog, then the item can be added
import java.util.ArrayList;


public class AdditemByType extends AppCompatActivity implements ItemTypeAdapter.ItemTypeClickListener, ItemAddedAdapter.ItemAddedClickListener, AddItemToListDialog.AddItemToListDialogListener {

    private GroceryList groceryList;
    private ArrayList<itemType> itemTypes;
    private ArrayList<item> items;
    //  private Button searchItemButton;
    private Database Database;

    private RecyclerView RVItemTypes;
    private RecyclerView RVItems;

    private ItemTypeAdapter itemTypeAdapter;
    private ItemAddedAdapter itemAddToListAdapter;
    private Button returnToListButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem_by_type);
        Intent intent = getIntent();

        Database = new Database(this);

        Database.open();

        groceryList = Database.getGroceryListById(intent.getLongExtra("groceryListId", 0));


        returnToListButton = findViewById(R.id.addItemByItemTypeReturnToListButton);
        returnToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the ID from Activity2
                Intent intent = new Intent(getApplicationContext(), GMLActivity3.class);
                intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, groceryList.getGListId());
                startActivity(intent);
            }
        });

        itemTypes = Database.getAllItemTypes();

        displayItemTypes();
    }

    private void displayItemTypes() {
        RVItemTypes = findViewById(R.id.addItemByItemTypeRecyclerView);

        itemTypeAdapter = new ItemTypeAdapter(itemTypes, (ItemTypeAdapter.ItemTypeClickListener) this);

        RVItemTypes.setAdapter(itemTypeAdapter);

        RVItemTypes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemTypeClick(int position) {
        //Upon pressing on an item type, display the items that fall under that item type
        setTitle(itemTypes.get(position).getItemTypeName());

        items = Database.getAllItemsByItemTypeId(itemTypes.get(position).getItemTypeId());

        RVItems = findViewById(R.id.addItemByItemTypeRecyclerView);

        itemAddToListAdapter = new ItemAddedAdapter(items, this);

        RVItems.setAdapter(itemAddToListAdapter);

        RVItems.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemAddedToListClick(int position) {
        openAddItemToListByItemTypeDialog(position);
    }

    private void openAddItemToListByItemTypeDialog(int position) {
        AddItemToListDialog dialog = new AddItemToListDialog();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("Item Name", items.get(position).getItemName());

        dialog.setArguments(args);

        dialog.show(getSupportFragmentManager(), "Add Item to List");
    }

    @Override
    public void AddItemToList(int quantity, String quantityUnit, int position) {
        //Insert the grocery list item into the list and return to ListActivity
        Database.insertGroceryListItem(groceryList.getGListId(), items.get(position), quantity, quantityUnit);
        Intent intent = new Intent(getApplicationContext(), GMLActivity3.class);
        intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, groceryList.getGListId());

        startActivity(intent);
    }
}

 