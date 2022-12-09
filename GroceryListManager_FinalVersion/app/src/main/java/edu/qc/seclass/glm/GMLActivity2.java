package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
//import edu.qc.seclass.glm.R;


import java.util.ArrayList;
//this activity is the Grocery List Management interface. This is allows the user to create/edit/rename grocery list
//It calls on several dialog classes to perform these functionality.
//After the user is finished, they can proceed to adding items in the next activity page

public class GMLActivity2 extends AppCompatActivity implements GroceryListAdapter.GListClickListener,
        renameDialog.RenameDialogListener, removeDialog.RemoveDialogListener,
        createDialog.CreateListDialogListener{
    private GroceryListAdapter adapter;
    private Button createListBtn;
    private ListView groceryListView;
    private ImageButton deleteButton;
    private ArrayList<GroceryList> groceryListArray;
    private RecyclerView rGroceryLists;

    Database Database;
    public static final String GROCERY_LIST_ID_CONSTANT = "groceryListId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmlactivity2);

     //   groceryListArray = DB.getAllGroceryList();
        setTitle("Grocery List Manager");
     //   Intent intent = getIntent();
     //   int gListId = intent.getIntExtra("gListId", 0);
     //   String gListName = intent.getStringExtra("gListName");
      Database = new Database(this);
      //connect to database to store the list in an Arraylist
      Database.open();

        createListBtn = findViewById(R.id.createListBtn);
        createListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateListDialog();
            }
        });
        groceryListArray = new ArrayList<>();
        displayGroceryLists();
        //show all the list that was maded
    }
    //open create dialog class to perform to action to add the new of a list into the DB
    private void openCreateListDialog() {
        createDialog dialog = new createDialog();
        dialog.show(getSupportFragmentManager(), "Create Grocery List");
    }
    //Use recycler view and populate with the array list
    public void displayGroceryLists() {
        rGroceryLists = findViewById(R.id.groceryListRecyclerView);

        adapter = new GroceryListAdapter((ArrayList<GroceryList>) groceryListArray, this);

        rGroceryLists.setAdapter(adapter);

        rGroceryLists.setLayoutManager(new LinearLayoutManager(this));
    }
    public void GListNameClick(int position) {
        selectGroceryList(position);
    }
    public void selectGroceryList(int position) {
        Intent intent = new Intent(this, GMLActivity3.class);
        intent.putExtra(GROCERY_LIST_ID_CONSTANT, groceryListArray.get(position).getGListId());
        startActivity(intent);
    } //goes to GMLActivity3 (Adding items page) after selecting a list to go on

    //Same case with create dialog, it opens the delete dialog to peform the
    // the deleting action after clicking the button
    private void openDeleteListDialog(int position) {
        removeDialog dialog = new removeDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Delete Grocery List");
    }
    /**
     * Listener that apply when the view is populated with a grocery list,
     * several buttons will be interactive that can peform the edit/delete/uncheck
     * a list.
     * @param position
     */@Override
    public void removeClickListener(int position) {
        openDeleteListDialog(position);
    }


    @Override
    public void renameClickListener(int position) {
        openEditNameDialog(position);
    }

    private void openEditNameDialog(int position) {
        renameDialog dialog = new renameDialog();
        Bundle args = new Bundle();
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Rename Grocery List");
    }
    @Override
    public void renameGroceryList(String gListName, int position) {
        if (gListName.equals("")) {
            Toast.makeText(this, "No name was entered. The list could not be renamed.", Toast.LENGTH_SHORT).show();
        }
        else {
            Database.updateGroceryListNameById(groceryListArray.get(position).getGListId(), gListName);
            groceryListArray = Database.getAllGroceryList();
            displayGroceryLists();
        }
    }

    @Override
    public void deleteGroceryList(int position) {
        Database.deleteAllGroceryListItemFromGroceryList(groceryListArray.get(position).getGListId());
        Database.deleteGroceryListById(groceryListArray.get(position).getGListId());
        groceryListArray = Database.getAllGroceryList();
        displayGroceryLists();
    }

    @Override
    public void createList(String gListName) {
        if (gListName.equals("")) {
            Toast.makeText(this, "No name was entered. The list could not be created.", Toast.LENGTH_SHORT).show();
        }
        else {
            Database.insertGroceryList(gListName);
            groceryListArray = Database.getAllGroceryList();
              displayGroceryLists();
        }
    }
}