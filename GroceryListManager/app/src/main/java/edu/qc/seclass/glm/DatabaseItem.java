package edu.qc.seclass.glm;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseItem extends SQLiteOpenHelper {
    private SQLiteDatabase DB;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GroceryListManager.DB";
    private static final String TABLE_LIST_OF_ITEMS = "userListOfItems";
    private static final String COL_LIST_ITEM_ID = "listOfItemsID";
    private static final String COL_LIST_QTY = "itemQty";
    private static final String COL_IS_CHECKED = "isChecked";
    private static final String TABLE_LIST = "UserList";
    private static final String COL_LIST_ID = "ListID";
    private static final String COL_LIST_NAME = "ListName";
    private int itemIDCount = 0;

    //created item table
    private static final String TABLE_ITEM = "Item";
    private static final String COL_ITEM_ID = "itemID";
    private static final String COL_ITEM_NAME = "itemName";
    private static final String COL_ITEM_TYPE_NAME = "itemType";

    // New Database Display items
   // public static final String GROCERY_LIST_ITEM_TABLE_DISPLAY = "GroceryListItemDisplay";
   // public static final String GROCERY_LIST_ID2 = "GroceryListIdDisplay";
   // public static final String ITEM_ID2 = "ItemIdDisplay";
  //  public static final String ITEM_Name2 = "itemNameDisplay";
  //  public static final String QUANTITY2 = "QuantityDisplay";
   // public static final String QUANTITY_UNIT2 = "QuantityUnitDisplay";
  //  public static final String IS_CHECKED2 = "IsCheckedDisplay";

    // List of items to be initially loaded into the database upon installation.
    private final item[] INITIAL_itemS = new item[]{
            new item(1, "Apples", 1, ""),
            new item(1, "Banana", 1, ""),
            new item(1, "Oranges", 1, ""),
            new item(1, "Pears", 1, ""),
            new item(1, "Watermelon", 1, ""),
            new item(1, "Raspberry", 1, ""),
            new item(1, "Strawberries", 1, ""),
            new item(1, "Blueberries", 1, ""),
            new item(1, "Grapes", 1, ""),
            new item(1, "Pineapple", 1, ""),
            new item(1, "Mangos", 1, ""),


            new item(1, "Hot Dogs", 2, ""),
            new item(1, "Hamburgers", 2, ""),
            new item(1, "Bacon", 2, ""),
            new item(1, "Chicken", 2, ""),

            new item(1, "Carrots", 3, ""),
            new item(1, "Spinach", 3, ""),
            new item(1, "Kale", 3, ""),
            new item(1, "Spinach", 3, ""),
            new item(1, "Cucumbers", 3, ""),
            new item(1, "Tomatoes", 3, ""),
            new item(1, "Carrots", 3, ""),

            new item(1, "Coke", 4, ""),
            new item(1, "Water", 4, ""),
            new item(1, "Beer", 4, ""),
            new item(1, "Coffee", 4, ""),
            new item(1, "Wine", 4, ""),
            new item(1, "Gatorade", 4, ""),
            new item(1, "Tea", 4, ""),
            new item(1, "Iced Tea", 4, ""),


            new item(1, "Milk", 5, ""),
            new item(1, "Cheese", 5, ""),
            new item(1, "Yogurt", 5, ""),
            new item(1, "Butter", 5, ""),

            new item(1, "Salmon", 6, ""),
            new item(1, "Shrimp", 6, ""),
            new item(1, "Tuna", 6, ""),

            new item(1, "Cake", 7, ""),
            new item(1, "Cookies", 7, ""),
            new item(1, "Donuts", 7, ""),


            new item(1, "Cheerios", 8, ""),
            new item(1, "Rice Krispies", 8, ""),
            new item(1, "Honey Nut Cheerios", 8, ""),
            new item(1, "Egg", 8, ""),
            new item(1, "Honey Bunches of Oats", 8, ""),
            new item(1, "Oatmeal", 8, ""),
            new item(1, "Pancake Mix", 8, ""),
            new item(1, "Rice", 8, ""),
            new item(1, "Waffles", 8, ""),
            new item(1, "Pancakes", 8, ""),
            new item(1, "Bread", 8, ""),
            new item(1, "Bagels", 8, ""),

            new item(1, "Potato Chips", 9, ""),
            new item(1, "Pretzels", 9, ""),
            new item(1, "Chocolate Bars", 9, ""),
            new item(1, "Jelly Beans", 9, ""),

            new item(1, "Salt", 10, ""),
            new item(1, "Mayonnaise", 10, ""),
            new item(1, "BBQ Sauce", 10, ""),
            new item(1, "Garlic Powder", 10, ""),
            new item(1, "Pepper", 10, ""),

    };

    public DatabaseItem(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Method that specifies all actions to be taken when the Database is first created upon installation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

       // String createTable = "CREATE TABLE " + GROCERY_LIST_ITEM_TABLE_DISPLAY + " (" + GROCERY_LIST_ID2 + " INTEGER PRIMARY KEY, " + ITEM_ID2 + " INTEGER, " +
       //         ITEM_Name2 + " TEXT, " + QUANTITY2 + " INTEGER, " + QUANTITY_UNIT2 + " TEXT, " + IS_CHECKED2 + " INTEGER DEFAULT 0 )"   ;


        //Create the tables
        //sqLiteDatabase.execSQL(createTable);
        sqLiteDatabase.execSQL(HierarchialList.CREATE_TABLE_GROCERY_LIST);
        sqLiteDatabase.execSQL(HierarchialList.CREATE_TABLE_ITEM_TYPE);
        sqLiteDatabase.execSQL(HierarchialList.CREATE_TABLE_ITEM);
        sqLiteDatabase.execSQL(HierarchialList.CREATE_TABLE_GROCERY_LIST_ITEM);
        //Insert item types
        for (int i = 0; i < HierarchialList.ITEM_TYPES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(HierarchialList.ItemType.ITEM_TYPE_NAME, HierarchialList.ITEM_TYPES[i]);
            sqLiteDatabase.insert(HierarchialList.ItemType.ITEM_TYPE_TABLE, null, values);
        }
        //Insert items with specific item types
        for (int i = 0; i < INITIAL_itemS.length; i++) {
            ContentValues values = new ContentValues();
            values.put("itemTypeId", INITIAL_itemS[i].getItemType().getItemTypeId());
            values.put("itemName", INITIAL_itemS[i].getItemName());
            sqLiteDatabase.insert(HierarchialList.Item.ITEM_TABLE, null, values);
        }
    }
    public boolean insertGroceryListItem(int groceryListId, item item, int quantity, String quantityType) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HierarchialList.GroceryListItem.GROCERY_LIST_ID, groceryListId);
        contentValues.put(HierarchialList.GroceryListItem.ITEM_ID, item.getItemId());
        contentValues.put(HierarchialList.GroceryListItem.ITEM_Name, item.getItemName());
        contentValues.put(HierarchialList.GroceryListItem.QUANTITY, quantity);
        contentValues.put(HierarchialList.GroceryListItem.QUANTITY_UNIT, quantityType);
        contentValues.put(HierarchialList.GroceryListItem.IS_CHECKED, 0);

        long result = db.insert(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE, null, contentValues);
        // error here
        if(result == -1){
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    public void insertItem(itemType itemType, String itemName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HierarchialList.Item.ITEM_TYPE_ID, itemType.getItemTypeId());
        contentValues.put(HierarchialList.Item.ITEM_NAME, itemName);

        DB.insert(HierarchialList.Item.ITEM_TABLE, null, contentValues);
    }


    public void deleteAllGroceryLists() {
        DB.execSQL("DELETE FROM " + HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE);
        DB.execSQL("DELETE FROM " + HierarchialList.GroceryList.GROCERY_LIST_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HierarchialList.Item.ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HierarchialList.ItemType.ITEM_TYPE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HierarchialList.GroceryList.GROCERY_LIST_TABLE);


        onCreate(sqLiteDatabase);
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

/*
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
*/
    // delete items from list based on listID

    public boolean deleteGroceryListItem(Long listID, Long itemID, String itemName){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE + " WHERE " + HierarchialList.GroceryListItem.ITEM_Name + " = " + itemName + " AND "
                + HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + listID + " AND " + HierarchialList.GroceryListItem.ITEM_ID + " = " + itemID , null);
        if(cursor.getCount() > 0){

            String query = HierarchialList.GroceryListItem.ITEM_Name + " = " + itemName + " AND "
                    + HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + listID + " AND " + HierarchialList.GroceryListItem.ITEM_ID + " = " + itemID;

            long result = db.delete(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE, query, null);

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


