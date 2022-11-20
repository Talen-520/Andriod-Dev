package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import com.example.grocerylistmanager.R;

import java.util.ArrayList;

public class GMLActivity3 extends AppCompatActivity implements AddDialogAct.searchDialogActListener {

    private ArrayList<item> allItems = new ArrayList<>();

    private HierarchialList allitemTypes_Names = new HierarchialList();

    private itemDB itemDB;
    private RecyclerView rGroceryListItems;
    private listAdapter groceryListItemAdapter;
    private Database Data;

    private Button groupItemByType;
    private Button uncheckAllItemButton;
    private Button addItemByType;
    private Button addItemByName;

    private long groceryListId;
    private GroceryList groceryList;
    private ArrayList<GroceryListItem> items;

    private EditText itemNameEDT, itemTypeEDT, itemUnitEDT, itemQtyEDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list3);
       // getSupportActionBar().setTitle("Grocery List");
//        groceryListId = selectedListIntent.getLongExtra(MainActivity.GROCERY_LIST_ID_CONSTANT, 0);

        //   groupedByType = false;

        // browseItemsClicked();

        //itemNameEDT = findViewById(R.id.itemTypeEDT);

        Button browseItemsButton = (Button) findViewById(R.id.browseItemsButton);
        browseItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GMLActivity3.this, GMLActivity4.class));
            }
        });


        Button otherOptionsAct5 = (Button) findViewById(R.id.otherOptionsAct5);
        otherOptionsAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GMLActivity3.this, GMLActivity5.class));
            }
        });


    }

    /**
     * uncheckAllItemButton = void findViewById(R.id.clearAll);
     * uncheckAllItemButton.setOnClickListener(new View.OnClickListener() {
     *
     * @Override public void onClick(View view) {
     * //Uncheck all items in the list
     * Data.uncheckAllGroceryListItems(groceryListId);
     * items = Data.getGroceryListItemByGroceryListId((int) groceryListId);
     * displayGroceryListItem(groupedByType);
     * }
     * });
     */
    public void clearAllChecks(View view) { // when the clearAllChecks button get clicked on, this function will be ran


    }
/*
    public void otherOptionsClicked(View view){
        Button otherOptionsAct5 = (Button) findViewById(R.id.otherOptionsAct5);
        otherOptionsAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GMLActivity3.this, GMLActivity5.class));
            }
        });
    }

    public void browseItemsClicked(View view) { // when the browse  types button get clicked on, this function will be ran
        Button browseItemsButton = (Button) findViewById(R.id.browseItemsButton);
        browseItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GMLActivity3.this, GMLActivity4.class));
            }
        });
    }
*/
    public void openAddItemDialog() { // dialog for adding item
        AddDialogAct exampleDialog = new AddDialogAct();
        exampleDialog.show(getSupportFragmentManager(), " example dialog");
    }

    @Override
    public void applyTexts(String itemType, String itemName, String itemQuantity) { // dialog function for adding an item

        if (itemType.length() == 0 || itemName.length() == 0 || itemQuantity.length() == 0) {
            Toast.makeText(GMLActivity3.this, "Empty Values", Toast.LENGTH_SHORT).show();
        } else {

            // item input = new item(itemType.toLowerCase(), itemName.toLowerCase());

            if (isitemContained(itemType.toLowerCase(), itemName.toLowerCase()) == false) {
                Toast.makeText(GMLActivity3.this, "Incorrect Item Type or Item Name", Toast.LENGTH_SHORT).show();
            } else {
                // ADD  input ITEM TO USER'S GROCERY LIST: DATABASE NEED TO ADD CODE HERE

                //adapter was made so user can add item by clicking the btton in the activity
            }
        }

    }

    public boolean isitemContained(String itemt, String itemn) {

        for (int i = 0; i < allItems.size(); i++) {

            //String listitemT = allItems.get(i).getItemType();
            //  String listitemN = allItems.get(i).getItemName();

            //   if( listitemT.equals( itemt.toLowerCase() ) == true && listitemN.equals( itemn.toLowerCase() ) == true  ){
            return true;
        }
        return false;
    }
}    //}
/**
        public void displayGroceryListItem(boolean groupedByType) {
           // rGroceryListItems = findViewById(R.id.groceryItemRecyclerView);

         //   groceryListItemAdapter = new listAdapter(items, this, groupedByType);

            rGroceryListItems.setAdapter(groceryListItemAdapter);

            rGroceryListItems.setLayoutManager(new LinearLayoutManager(this));
        }
        return false;
    }
}
*/