package edu.qc.seclass.glm;

import static edu.qc.seclass.glm.GMLActivity2.GROCERY_LIST_ID_CONSTANT;
import edu.qc.seclass.glm.itemDB;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grocerylistmanager.R;

public class GMLActivity5 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmlactivity5);

        Button goback2 = (Button) findViewById(R.id.goBackButton2);
        goback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        EditText listIDAct5 = (EditText) findViewById(R.id.listIDAct5);
        EditText itemIDAct5 = (EditText) findViewById(R.id.itemIDAct5);
        EditText itemNameAct5 = (EditText) findViewById(R.id.itemNameAct5);;
        EditText itemTypeAct5 = (EditText) findViewById(R.id.itemTypeAct5);
        EditText itemQAct5 = (EditText) findViewById(R.id.itmQAct5);
        EditText itemQTypeAct5 = (EditText) findViewById(R.id.itmQTypeAct5);

        Button deleteItemAct5 = (Button) findViewById(R.id.deleteItemAct5);;

        deleteItemAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String listID = listIDAct5.getText().toString();
                String itemID = listIDAct5.getText().toString();
                String itemName = itemNameAct5.getText().toString();


                if( itemName.length() == 0 || itemID.length() == 0 || listID.length() == 0 ){
                    Toast.makeText(getApplicationContext(), "Empty values", Toast.LENGTH_SHORT).show();
                }
                else {

                  DatabaseItem databasehelper = new DatabaseItem(GMLActivity5.this);
                  boolean itemDeleted = databasehelper.deleteGroceryListItem(Long.parseLong(listID), Long.parseLong(itemID), itemName);

                    if(  itemDeleted == true ){
                        Toast.makeText(getApplicationContext(), "Item Successfully Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "An Error Occurred or the item doesn't exist in the list (listID) ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



        Button addingItemAct5 = (Button) findViewById(R.id.addingItemAct5);

        addingItemAct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String listID2 = listIDAct5.getText().toString();
                    String itemID2 = itemIDAct5.getText().toString();
                    String itemName2 = itemNameAct5.getText().toString();
                    String itemType2 = itemTypeAct5.getText().toString();
                    String listQ2 = itemQAct5.getText().toString();
                    String listQtype2 = itemQTypeAct5.getText().toString();

                    if( itemID2.length() == 0 || itemName2.length() == 0 || listQ2.length() == 0 || listID2.length() == 0 || itemType2.length() == 0 || listQtype2.length() == 0){
                        Toast.makeText(getApplicationContext(), "Empty values", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        item itemadd = new item( Long.parseLong(itemID2), itemName2, itemType2);

                        DatabaseItem databasehelper = new DatabaseItem(GMLActivity5.this);

                        boolean itemAdded = databasehelper.insertGroceryListItem(Integer.parseInt(listID2), itemadd, Integer.parseInt(listQ2), listQtype2);

                        if(  itemAdded == true ){
                            Toast.makeText(getApplicationContext(), "Item Successfully Added", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "An Error Occurred, Retry", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });

    } // onCreate Method





} // class