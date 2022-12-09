package edu.qc.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

//this activity is the functionality to add the item by name, by typing the word into the search bar,
//It also has the ability to goto option button page when item searched is unrecognizable.
public class GMLActivity4 extends AppCompatActivity implements ItemAddedAdapter.ItemAddedClickListener, AddItemToListDialog.AddItemToListDialogListener {
    private ArrayList<item> items;
    private Database Database;
    private Button searchItem;
    private Button otherOptionsAct5;

    private Button returnToListButton;
    private EditText text;
    private GroceryList list;
    private RecyclerView recycler;
    private ItemAddedAdapter recyclerAdapter;
    private long listId;
    private String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_by_search);
        //retrieve the grocery list id from grocery list management page
        Intent intent = getIntent();
        listId = intent.getLongExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, 0);

        //search the items in the db
        Database = new Database(this);
        Database.open();
        list = Database.getGroceryListById(listId);
        items = new ArrayList<>();
     //   otherOptionsAct5 = findViewById(R.id.otherOptionsAct5);
        searchItem = findViewById(R.id.searchItemByNameButton);
        text = findViewById(R.id.searchItemByNameEditText);
        returnToListButton = findViewById(R.id.addItemByNameReturnToListButton);
        searchTerm = new String("");
        searchItem.setOnClickListener(new View.OnClickListener() {
            // Search for the items
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                searchTerm = new String(text.getText().toString());
                items = Database.getAllItemsByName(searchTerm);
                if (items.size() == 0){
                    Intent intent = new Intent(getApplicationContext(), GMLActivity5.class);
                    intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, listId);
                    intent.putExtra("New Item Name", "");
                    startActivity(intent);

                }
                try {
                    boolean success = addItemsToListView();
                    if (!success) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Please try again!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });


        returnToListButton.setOnClickListener(new View.OnClickListener() {
            //return to previous layout
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GMLActivity3.class);
                intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, listId);
                startActivity(intent);
            }
        });
    }

    private boolean addItemsToListView() {
        //add the items shown based on the search
        try {
            recycler = findViewById(R.id.AddItemByNameRecyclerView);
            recyclerAdapter = new ItemAddedAdapter(items, this);

            recycler.setAdapter(recyclerAdapter);
            recycler.setLayoutManager(new LinearLayoutManager(this));

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void onItemAddedToListClick(int position) {
        AddItemToListDialog dialog = new AddItemToListDialog();
        //dialog buttton that prompts the user to add the item
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("Item Name", items.get(position).getItemName());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Add Item to List");
    }

    @Override
    public void AddItemToList(int quantity, String quantityUnit, int position) {
        Database.insertGroceryListItem(list.getGListId(), items.get(position), quantity, quantityUnit);
        Intent intent = new Intent(getApplicationContext(), GMLActivity3.class);
        intent.putExtra(GMLActivity2.GROCERY_LIST_ID_CONSTANT, list.getGListId());
        //put in the grocery list based on the id
        startActivity(intent);
    }
}