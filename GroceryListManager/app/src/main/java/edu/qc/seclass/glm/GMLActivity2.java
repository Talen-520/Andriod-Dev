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

import com.example.grocerylistmanager.R;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class GMLActivity2 extends AppCompatActivity implements GroceryListAdapter.GListClickListener,
        renameDialog.RenameDialogListener, removeDialog.RemoveDialogListener,
        createDialog.CreateListDialogListener{
  //  private CreateListDialogListener listener;
    private GroceryListAdapter adapter;
    private Button createListBtn;
    private ListView groceryListView;
    private ImageButton deleteButton;
    private ArrayList<GroceryList> groceryListArray;
    private RecyclerView rGroceryLists;

    Database Database;
    public static final String GROCERY_LIST_ID_CONSTANT = "groceryListId";


   // @SuppressLint("WrongViewCast")
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

      Database.open();

        // Get the Create List button from the view and set a listener to open the dialog used to create a list
        createListBtn = findViewById(R.id.createListBtn);
        createListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateListDialog();
            }
        });
        groceryListArray = new ArrayList<>();

        if (groceryListArray.size() == 0) {
            Toast.makeText(this, "Welcome to the Grocery List Manager! Press the 'Create Grocery List' button below to begin", Toast.LENGTH_SHORT).show();
        }
        //Call the method that will populate the activity with grocery lists
        displayGroceryLists();

    }
    private void openCreateListDialog() {
        //Create the CreateGroceryList Dialog
        createDialog dialog = new createDialog();
        //Set the title and show the dialog
        dialog.show(getSupportFragmentManager(), "Create Grocery List");
    }
    /**
    public void selectButtonClicked(View view){

        String listID = selectAction.getText().toString();
        String listName = selectAction2.getText().toString();

        if(  listID.length() == 0 || listName.length() == 0 ){
            Toast.makeText(MainActivity.this, "Empty values!", Toast.LENGTH_SHORT).show();
        }
        else {

            GroceryList input = new GroceryList(Long.parseLong(listID), listName);

            if(  groceryListArray.contains(input) == true  ){

                // starting GML activity 3
                Intent intent= new Intent(MainActivity.this, GMLActivity3.class);
                startActivity(intent);//login success, then we jump to next activity GroceryList
               // Toast.makeText(MainActivity.this, "new Activity should start", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "This list doesn't exist!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void renameButtonClicked(View view){

        String listID = renamingAction.getText().toString();
        String listName = renamingAction2.getText().toString();
        String newlistName = renamingAction3.getText().toString();

        if(  listID.length() == 0 || listName.length() == 0 || newlistName.length() == 0 ){
            Toast.makeText(MainActivity.this, "Empty values!", Toast.LENGTH_SHORT).show();
        }
        else {
            GroceryList input = new GroceryList(Long.parseLong(listID), listName);

            if( groceryListArray.contains(input) == true ){

               for(int i=0; i < groceryListArray.size(); i++){
                   if( groceryListArray.get(i) == input ){
                       groceryListArray.get(i).setGroceryListName(newlistName);
                       break;
                   }
               }
                Toast.makeText(MainActivity.this, "This list has been renamed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "This list doesn't exist!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void deleteButtonClicked(View view){

        String listID = deletingAction.getText().toString();
        String listName = deletingAction2.getText().toString();

        if(  listID.length() == 0 || listName.length() == 0 ){
            Toast.makeText(MainActivity.this, "Empty values!", Toast.LENGTH_SHORT).show();
        }
        else{

            GroceryList input = new GroceryList(Long.parseLong(listID), listName);

            if( groceryListArray.contains(input) == true  ){
                groceryListArray.remove(input);
                Toast.makeText(MainActivity.this, "This list has been deleted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "This list doesn't exist!", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void selectGroceryList(int position) {
        Intent intent = new Intent(this, GMLActivity3.class);
        intent.putExtra(GROCERY_LIST_ID_CONSTANT, groceryListArray.get(position).getGListId());
        startActivity(intent);
    }
     public void openDialog(View view) {

     final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
     View mView = getLayoutInflater().inflate(R.layout.activity_gladapter, null);

     final EditText text_input = (EditText) mView.findViewById(R.id.text_input);
     Button button_back = (Button) mView.findViewById(R.id.btn_back);
     Button button_create = (Button) mView.findViewById(R.id.btn_create);

     alert.setView(mView);

     final AlertDialog alertDialog = alert.create();
     alertDialog.setCanceledOnTouchOutside(false);

     button_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    alertDialog.dismiss();
    }
    });

     button_create.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    //Placeholder list
    TextView username = (TextView) findViewById(R.id.username);
    TextView password = (TextView) findViewById(R.id.password);
    GroceryList newListName = new GroceryList();

    String user = username.getText().toString();
    String pass = username.getText().toString();
    if (user.equals("admin") && pass.equals("admin")) {
    MainActivity.this.groceryListArray.add(newListName);
    adapter.notifyDataSetChanged();
    alertDialog.dismiss();
    Toast.makeText(view.getContext(), "New list has been created "+newListName.getGListName(), Toast.LENGTH_SHORT).show();
    }
    else{
    Toast.makeText(view.getContext(), "There's list already with the same name "+ newListName.getGListName(), Toast.LENGTH_SHORT).show();
    }
    }
    });

     alertDialog.show();
     }

     */
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
    }



    @Override
    public void removeClickListener(int position) {
        openDeleteListDialog(position);
    }

    // Opens the DeleteList dialog, allowing the user to delete the List
    private void openDeleteListDialog(int position) {
        removeDialog dialog = new removeDialog();
        Bundle args = new Bundle();
        //Pass in the position of the List in the ArrayList that is to be deleted.
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "Delete Grocery List");
    }

    @Override
    public void renameClickListener(int position) {
        openEditNameDialog(position);
    }

    // Opens the RenameGroceryList dialog, allowing the user to delete their list
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
            Toast.makeText(this, "No name entered. The list could not be renamed.", Toast.LENGTH_SHORT).show();
        }
        else {
            Database.updateGroceryListNameById(groceryListArray.get(position).getGListId(), gListName);
            groceryListArray = Database.getAllGroceryList();
            displayGroceryLists();
        }
    }

    //Overridden from the RemoveGroceryListDialog Class, deletes the list and it's groceryListItems from the database and redisplays the Lists
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
            Toast.makeText(this, "No name was specified. The list could not be created.", Toast.LENGTH_SHORT).show();
        }
        else {
            Database.insertGroceryList(gListName);
            groceryListArray = Database.getAllGroceryList();
            if (groceryListArray.size() <= 1) {
                Toast.makeText(this, "Click on the name of your Grocery List to get started!", Toast.LENGTH_SHORT).show();
            }
            displayGroceryLists();
        }
    }
}