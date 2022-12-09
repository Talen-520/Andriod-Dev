package edu.qc.seclass.glm;

import static edu.qc.seclass.glm.GMLActivity2.GROCERY_LIST_ID_CONSTANT;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//This is the other option page/UI, which allows the user to add a new item/type.
// This page is launched either by clicking the button or you're sent to it from
// the condition of unrecognizable search in GMLActivity4

public class GMLActivity5 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmlactivity5);
        Toast.makeText(this, "You can choose/create an item type or item name to be add into the database", Toast.LENGTH_SHORT).show();
        Button goback2 = (Button) findViewById(R.id.goBackButton2);
        goback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        EditText itemIDDB = (EditText) findViewById(R.id.itemIDDB);
        EditText itemNameDB = (EditText) findViewById(R.id.itemNameDB);
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
                String itemID = itemIDAct5.getText().toString();
                // String itemName = itemNameAct5.getText().toString();


                if(   itemID.length() == 0 || listID.length() == 0 ){
                    Toast.makeText(getApplicationContext(), "Empty values", Toast.LENGTH_SHORT).show();
                }
                else {

                    DatabaseItem databasehelper = new DatabaseItem(GMLActivity5.this);
                    boolean itemDeleted = databasehelper.deleteGroceryListItem(Integer.parseInt(listID), Integer.parseInt(itemID));

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
                        Toast.makeText(getApplicationContext(), "An Error Occurred or itemID taken", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Button addingitemDB = (Button) findViewById(R.id.addingitemDB);

        addingitemDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemidDB = itemIDDB.getText().toString();
                String itemnameDB = itemNameDB.getText().toString();

                if( itemidDB.length() == 0 || itemnameDB.length() == 0  ){
                    Toast.makeText(getApplicationContext(), "Empty Values", Toast.LENGTH_SHORT).show();
                }
                else {

                    int itemIDNumber = Integer.parseInt(itemidDB);

                    DatabaseItem dbhelper = new DatabaseItem(GMLActivity5.this);
                    int itemAddedDB = dbhelper.insertItem(itemIDNumber, itemnameDB);

                    if( itemAddedDB == 1  ){
                        Toast.makeText(getApplicationContext(), "Item Added Successfully to Database", Toast.LENGTH_SHORT).show();
                    }
                    else if(  itemAddedDB == 0   ){
                        Toast.makeText(getApplicationContext(), "Item name is already added", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error Occurred ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Button addingItemTypeDB = (Button) findViewById(R.id.addingItemTypeDB);

        addingItemTypeDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemTypeDB = itemNameDB.getText().toString();

                if(  itemTypeDB.length() == 0  ){
                    Toast.makeText(getApplicationContext(), "Empty Values", Toast.LENGTH_SHORT).show();
                }
                else {

                    DatabaseItem dbhelper = new DatabaseItem(GMLActivity5.this);
                    boolean itemTypeAddedDB = dbhelper.insertItemTypeDB(itemTypeDB);

                    if( itemTypeAddedDB == true){
                        Toast.makeText(getApplicationContext(), "ItemType Added Successfully to Database", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error Occurred or item type is already added", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });








    } // onCreate Method





} // class