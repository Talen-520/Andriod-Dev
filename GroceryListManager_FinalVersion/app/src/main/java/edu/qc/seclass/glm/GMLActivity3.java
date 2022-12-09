package edu.qc.seclass.glm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
//This activity hold the functionality of going to another activity and peforming adding actions through buttons.
//The user are allowed to delete items, edit the items, uncheck item, similarly principles to GroceryListManagement.

public class GMLActivity3 extends AppCompatActivity implements GroceryItemAdapter.ItemClickListener,
        DeleteGroceryListItem.DeleteItemViewListener,EditQuantityUnit.EditQuantityUnitListener {

    private RecyclerView RgroceryListItems;
    private GroceryItemAdapter GroceryItemAdapter;
    private Database Database;
    private Intent selectedListIntent;
    private long groceryListId;
    private GroceryList groceryList;
    private ArrayList<GroceryListItem> items;
    public Database database = new Database(this);

    private Button uncheckAllItemButton;
    private Button addItemByType;
    private Button addItemByName;

    private boolean groupedByType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list3);
        // Get the Intent that started this activity and extract the listId
        selectedListIntent = getIntent();
        groceryListId = selectedListIntent.getLongExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, 0);
        groupedByType = false;
        Database = new Database(this);
        Database.open();
        groceryList = Database.getGroceryListById(groceryListId);
        items = Database.getGroceryListItemByGroceryListId((int) groceryListId);


        addItemByName = findViewById(R.id.addByType);
        addItemByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goes to GMLActivity4 for the page to search for items after clicking the button
                Intent intent = new Intent(GMLActivity3.this, GMLActivity4.class);
                intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, groceryListId);
                startActivity(intent);
            }
        });
        //goes to Other options page, where the user are able added new items/types.
        Button otherOptionsAct5 = (Button) findViewById(R.id.otherOptionsAct5);
        otherOptionsAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GMLActivity3.this, GMLActivity5.class));
            }
        });

        addItemByType = findViewById(R.id.browseItemsButton);
        addItemByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to AddItemByType page
                Intent intent = new Intent(GMLActivity3.this, AdditemByType.class);
                intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, groceryListId);
                startActivity(intent);
            }
        });

        uncheckAllItemButton = findViewById(R.id.clearAll);
        uncheckAllItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uncheck all items in the list through clicking the checkbox
                Database.uncheckAllGroceryListItems(groceryListId);
                items = Database.getGroceryListItemByGroceryListId((int) groceryListId);
                displayGroceryListItem(groupedByType);
            }
        });

        displayGroceryListItem(groupedByType);
    }
    //populating the view with the grocery items and it's ordered by the type
    public void displayGroceryListItem(boolean groupedByType) {
        RgroceryListItems = findViewById(R.id.groceryItemRecyclerView);

        GroceryItemAdapter = new GroceryItemAdapter(items, this, groupedByType);

        RgroceryListItems.setAdapter(GroceryItemAdapter);

        RgroceryListItems.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * After the view is populated with items, several buttons will present and interactive
     * Which will respond to the user input on the button, allowing to edit/delete/uncheck a item.
     * @param position
     */
    @Override
    public void onIsCheckedCheckBoxClickListener(int position) {
        isCheckedItem(position);

    }

    public void isCheckedItem(int position) {
        if (items.get(position).isChecked())
            Database.updateGroceryListItem(items.get(position), false);
        else Database.updateGroceryListItem(items.get(position), true);

    }

    @Override
    public void onQuantityAndUnitButtonClickListener(int position) {
        QuantityAndUnitButton(position);
    }

    public void QuantityAndUnitButton(int position) {
        EditQuantityUnit dialog = new EditQuantityUnit();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("quantity", items.get(position).getQuantity());
        args.putString("quantityUnit", items.get(position).getQuantityUnit());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Change Quantity And Unit");
    }

    @Override
    public void ItemQuantityAndUnit(int position, int quantity, String unit) {

        Database.updateGroceryListItem(items.get(position), quantity);
        Database.updateGroceryListItem(items.get(position), unit);

        items = Database.getGroceryListItemByGroceryListId((int) groceryListId);
        displayGroceryListItem(groupedByType);

    }

    @Override
    public void DeleteItemButtonClickListener(int position) {
        openDeleteListDialog(position);
    }

    public void openDeleteListDialog(int position) {
        DeleteGroceryListItem dialog = new DeleteGroceryListItem();
        Bundle args = new Bundle();
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Delete Item");
    }

    @Override
    public void deleteGroceryListItem(int position) {
        Database.deleteGroceryListItem(items.get(position));
        items = Database.getGroceryListItemByGroceryListId((int) groceryListId);
        displayGroceryListItem(groupedByType);
    }





}