package edu.qc.seclass.glm;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;
import java.util.*;

public class itemDB extends SQLiteOpenHelper{

    private static itemDB instance = null;
    private static final int DB_VERSION = 1;
    public static String DB_NAME = "GLM.db";
    private int itemIDCount = 0;

    //Singleton function
    public static itemDB getInstance(Context context){
        if(instance == null){
            instance = new itemDB(context.getApplicationContext());
        }
        return instance;
    }



    public itemDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }



    //created table containing multiple lists
    private static final String TABLE_LIST = "UserList";
    private static final String COL_LIST_ID = "ListID";
    private static final String COL_LIST_NAME = "ListName";

    private static final String CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS "
                                                    + TABLE_LIST + "("
                                                    + COL_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + COL_LIST_NAME + " TEXT )";


    //created item table
    private static final String TABLE_ITEM = "Item";
    private static final String COL_ITEM_ID = "itemID";
    private static final String COL_ITEM_NAME = "itemName";
    private static final String COL_ITEM_TYPE_NAME = "itemType";

    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE IF NOT EXISTS "
                                                    + TABLE_ITEM + "("
                                                    + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + COL_ITEM_NAME + " TEXT, "
                                                    + COL_ITEM_TYPE_NAME + " TEXT )";


    //created list of items table
    private static final String TABLE_LIST_OF_ITEMS = "userListOfItems";
    private static final String COL_LIST_ITEM_ID = "listOfItemsID";
    private static final String COL_LIST_QTY = "itemQty";
    private static final String COL_IS_CHECKED = "isChecked";

    private static final String CREATE_TABLE_LIST_OF_ITEMS = "CREATE TABLE IF NOT EXISTS "
                                                            + TABLE_LIST_OF_ITEMS + "("
                                                            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                            + COL_LIST_ITEM_ID + " INTEGER, "
                                                            + COL_LIST_QTY + " INTEGER, "
                                                            + COL_ITEM_NAME + " TEXT, "
                                                            + COL_IS_CHECKED + " INTEGER DEFAULT 0, "
            + COL_LIST_ID + " INTEGER, "
                                                            + COL_ITEM_ID + "INTEGER )";



    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_LIST);
        db.execSQL(CREATE_TABLE_ITEMS);
        db.execSQL(CREATE_TABLE_LIST_OF_ITEMS);


        fillDB(db, "apple", "fruit");
        fillDB(db, "banana", "fruit");
        fillDB(db, "orange", "fruit");
        fillDB(db, "pear", "fruit");
        fillDB(db, "watermelon", "fruit");
        fillDB(db, "raspberry", "fruit");
        fillDB(db, "strawberry", "fruit");
        fillDB(db, "blueberry", "fruit");

        fillDB(db, "lettuce","vegetables");
        fillDB(db, "spinach","vegetables");
        fillDB(db, "silver beet","vegetables");
        fillDB(db, "onion","vegetables");
        fillDB(db, "garlic","vegetables");
        fillDB(db, "potato","vegetables");
        fillDB(db, "sweet potato","vegetables");
        fillDB(db, "pumpkin","vegetables");
        fillDB(db, "cucumber","vegetables");
        fillDB(db, "celery","vegetables");
        fillDB(db, "cabbage","vegetables");
        fillDB(db, "broccoli","vegetables");
        fillDB(db, "yarn","vegetables");
        fillDB(db, "zucchini","vegetables");
        fillDB(db, "cauliflower","vegetables");
        fillDB(db, "brussels sprouts","vegetables");
        fillDB(db, "shallot","vegetables");

        fillDB(db, "water","drinks");
        fillDB(db, "coca cola","drinks");
        fillDB(db, "pepsi","drinks");
        fillDB(db, "gatorade","drinks");
        fillDB(db, "powerade","drinks");
        fillDB(db, "mountain dew","drinks");
        fillDB(db, "sprite","drinks");
        fillDB(db, "hience","drinks");
        fillDB(db, "crown royal","drinks");
        fillDB(db, "rum","drinks");
        fillDB(db, "tequila","drinks");
        fillDB(db, "vodka","drinks");
        fillDB(db, "whiskey","drinks");
        fillDB(db, "coffee","drinks");
        fillDB(db, "tea","drinks");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST_OF_ITEMS);

        onCreate(db);
    }

    //function to fill the item database with grocery items
    public void fillDB(SQLiteDatabase db, String itemName, String itemType){

        ContentValues values = new ContentValues();

        values.put(COL_ITEM_NAME, itemName);
        values.put(COL_ITEM_TYPE_NAME, itemType);



        db.insert(TABLE_ITEM, null, values);

        db.close();
    }

    //function to add a new item into the database
    public void addNewItem(String itemName, String itemType){



        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues value = new ContentValues();

        value.put(COL_ITEM_NAME, itemName);
        value.put(COL_ITEM_TYPE_NAME, itemType);

        db.insert(TABLE_ITEM, null, value);
        db.close();
    }

    //function to change the name of a list
    public void editListName(int listID, String newListName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_LIST_NAME, newListName);

        db.update(TABLE_LIST, values, "ListID=?", new String[]{String.valueOf(listID)});
        db.close();
    }

    //function to create a list
    public void createList(String listName){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();

        value.put(COL_LIST_NAME, listName);

        db.insert(TABLE_LIST, null, value);

        db.close();
    }

    //function to delete a list
    public void deleteList(int listID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST,"ListID= ?", new String[]{String.valueOf(listID)});
        db.close();
    }

    //function to delete all lists
    public void deleteAllLists(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST,null, null);
        db.close();
    }


    //function to delete checked items in the list
    public boolean deleteChecked(int listId){
        SQLiteDatabase db = this.getWritableDatabase();

        String dbEdit = COL_LIST_ID + "=" + listId + " AND " + COL_IS_CHECKED + "=" + "1";

        return db.delete(TABLE_LIST_OF_ITEMS, dbEdit, null) > 0;

    }


    //function to show all items found in the db
    @SuppressLint("Range")
    public ArrayList<itemDBModel> showAllItems(){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<itemDBModel> items = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEM, null);

        if(cursor.moveToFirst()){
            do{
                items.add(new itemDBModel(cursor.getInt(cursor.getColumnIndex(COL_ITEM_ID)),
                                            cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME)),
                                            cursor.getString(cursor.getColumnIndex(COL_ITEM_TYPE_NAME))
                ));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return items;
    }

    //function to search for a specific item in the db
    @SuppressLint("Range")
    public ArrayList<itemDBModel> searchForItem(String input){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<itemDBModel> items = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEM
                                    + " WHERE " + COL_ITEM_NAME
                                    + " LIKE ' " + input + "&" + "'", null);

        if(cursor.moveToFirst()){
            do{
                items.add(new itemDBModel(cursor.getInt(cursor.getColumnIndex(COL_ITEM_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_ITEM_TYPE_NAME))
                ));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return items;
    }


    //function to list the items in a grocery list by list id
    @SuppressLint("Range")
    public ArrayList<listModel> listOfItemsByListId(int listId){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<listModel> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT " + TABLE_LIST_OF_ITEMS + ".*, "
                                        + TABLE_ITEM + "." + COL_ITEM_NAME
                                        + " FROM " + TABLE_LIST_OF_ITEMS
                                        + " JOIN " + TABLE_ITEM
                                        + " ON " + TABLE_ITEM + "." + COL_ITEM_ID + "=" + TABLE_LIST_OF_ITEMS + "." + COL_LIST_ITEM_ID
                                        + " WHERE " + COL_LIST_ID + "=" + listId, null);

        if(cursor.moveToFirst()){
            do{
                list.add(new listModel(cursor.getInt(cursor.getColumnIndex(COL_LIST_ITEM_ID)),
                                        cursor.getInt(cursor.getColumnIndex(COL_IS_CHECKED)),
                                        cursor.getString(cursor.getColumnIndex(COL_LIST_QTY)),
                                        cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME))));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;

    }


    //function to increment the quantity of an item
    public void increaseQuantity(int itemId, int listId, int qty){

        SQLiteDatabase db = this.getReadableDatabase();
        qty += 1;

        Cursor cursor = db.rawQuery(" UPDATE " + TABLE_LIST_OF_ITEMS
                                    + " SET " + COL_LIST_QTY + "="
                                    + qty + " WHERE "
                                    + COL_LIST_ITEM_ID + "=" + itemId
                                    + " AND " + COL_LIST_ITEM_ID
                                    + "=" + listId, null);

        if(cursor.moveToFirst()){
            do{
                System.out.println("increased");
            }while(cursor.moveToNext());
        }
    }

    @SuppressLint("Range")
    public int getQuantity(int itemId, int listId){
        SQLiteDatabase db = this.getReadableDatabase();
        int qty = 0;

        Cursor cursor = db.rawQuery("SELECT " + COL_LIST_QTY + " FROM " + TABLE_LIST_OF_ITEMS
                                    + " JOIN " + TABLE_ITEM + " ON "
                                    + TABLE_ITEM + "." + COL_ITEM_ID + "=" + TABLE_LIST_OF_ITEMS + "." + COL_LIST_ITEM_ID
                                    + " WHERE " + COL_LIST_ID + "=" + listId, null);

        if(cursor.moveToFirst()){
            do{
                qty = cursor.getInt(cursor.getColumnIndex(COL_LIST_QTY));
            } while(cursor.moveToNext());
        }

        cursor.close();

        db.close();

        return qty;
    }

    //function to check the box in the list
    public void checkBox(int listId, int isChecked){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" UPDATE " + TABLE_LIST_OF_ITEMS
                                    + " SET " + COL_IS_CHECKED + " = "
                                    + isChecked + " WHERE "
                                    + COL_LIST_ITEM_ID + "=" + listId, null);

        if(cursor.moveToFirst()){
            do{
                System.out.println("checked");
            }while(cursor.moveToNext());
        }
        cursor.close();
    }


    //adds items into a list based on the listId
    public boolean addListItem(String itemName, int qty, int itemId, int listId){

        ArrayList<itemDBModel> allItems = showAllItems();

        for(int i=0; i < allItems.size(); i++){

             if( i == allItems.size() - 1 &&  allItems.get(i).getItemName().equals(itemName.toLowerCase() ) == false  ) {
                return false;
             }
             else if(  allItems.get(i).getItemName().equals(itemName.toLowerCase() ) == true ){
                break;
             }


        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        itemIDCount++;

        value.put(COL_ITEM_TYPE_NAME,itemName.toLowerCase() );
        value.put(COL_LIST_ID, listId);
        value.put(COL_LIST_ITEM_ID, itemIDCount);
        value.put(COL_LIST_QTY, qty);

        long result = db.insert(TABLE_LIST_OF_ITEMS, null, value);
        db.close();


        if(result == -1){
            return false;
        }
        return true;
    }


    // delete items from list based on listID
    public boolean deleteListItem(String itemName, int qty, int itemId, int listId){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LIST_OF_ITEMS + " WHERE " + COL_ITEM_NAME + " = " + itemName.toLowerCase() + " AND " + COL_LIST_ID + " = " + listId, null);
        if(cursor.getCount() > 0){

            String query = COL_ITEM_NAME + " = " + itemName.toLowerCase() + " AND " + COL_LIST_ID + " = " + listId;
            long result = db.delete(TABLE_LIST_OF_ITEMS, query, null);

            if(result == -1){
                cursor.close();
                db.close();
                return false;
            }

        }
        else {
            cursor.close();
            db.close();
            return false;
        }


        cursor.close();
        db.close();

        return true;
    }







}
