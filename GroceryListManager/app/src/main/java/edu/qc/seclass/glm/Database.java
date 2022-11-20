package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database {
    private Context context;

    //public static final String DBNAME = "Login.db";
    //  private static Database INSTANCE = null;
    public Database(Context context) {
        this.context = context;
        //  super(context, "Login.db", null, 1);
    }

    // }
    private DatabaseItem databaseHelper;
    private SQLiteDatabase DB;
    // private static SQLiteOpenHelper openHelper;
    //  private static Database instance;
    // @Override
    // public void onCreate(SQLiteDatabase db) {
    //     db.execSQL("create table user(username TEXT PRIMARY key,password TEXT)");

    //     Log.e("db operations ","Tables  Created ");
    //  }
    public Database open() throws SQLException {
        databaseHelper = new DatabaseItem(context);
        DB = databaseHelper.getWritableDatabase();
        return this;
    }

    /**
     * @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     * db.execSQL("drop table if exists user");
     * <p>
     * }
     * //for sigup purpose, but we only have 1 user
     * // you can make similar one for additem  into db
     * public Boolean insertData(String username, String password){
     * SQLiteDatabase db = this.getWritableDatabase();
     * ContentValues values = new ContentValues();
     * <p>
     * values.put("username",username);
     * values.put("password",password);
     * <p>
     * long result = db.insert("users",null,values);
     * if(result==-1)return false;
     * else
     * return true;
     * }
     * //check if username in the db, we only have 1 user
     * // so we dont need this method
     * // you can make similar one for checkitem in db
     * public boolean checkUsername(String username){
     * SQLiteDatabase db = this.getWritableDatabase();
     * Cursor cursor = db.rawQuery("Select * From user where username = admin",new String []{username});//set username here
     * if (cursor.getCount()>0)return true;
     * else return false;
     * }
     * <p>
     * public boolean checkPassword(String username,String password){
     * SQLiteDatabase db = this.getWritableDatabase();
     * Cursor cursor = db.rawQuery("Select * From user where username=admin AND password=admin",new String []{username,password});//set user&pass here
     * if (cursor.getCount()>0)return true;
     * else return false;
     * }
     */
    public void close() {
        databaseHelper.close();
    }

    //Add a new Grocery List with a given name
    public void insertGroceryList(String groceryListName) {
        ContentValues groceryList = new ContentValues();
        groceryList.put(HierarchialList.GroceryList.GROCERY_LIST_NAME, groceryListName);
        DB.insert(HierarchialList.GroceryList.GROCERY_LIST_TABLE, null, groceryList);
    }

    // Update the Name of a grocery list by the ID
    public int updateGroceryListNameById(long groceryListId, String groceryListName) {
        ContentValues groceryList = new ContentValues();
        groceryList.put(HierarchialList.GroceryList.GROCERY_LIST_NAME, groceryListName);
        return DB.update(HierarchialList.GroceryList.GROCERY_LIST_TABLE, groceryList, HierarchialList.GroceryList.GROCERY_LIST_ID + " = " + groceryListId, null);
    }

    // Delete a GroceryList with a certain ID
    public void deleteGroceryListById(long groceryListId) {
        DB.delete(HierarchialList.GroceryList.GROCERY_LIST_TABLE,
                HierarchialList.GroceryList.GROCERY_LIST_ID + " = " + groceryListId, null);
    }

    //Get a list of all Grocery Lists
    public ArrayList getAllGroceryList() {
        String[] columns = new String[]{HierarchialList.GroceryList.GROCERY_LIST_ID, HierarchialList.GroceryList.GROCERY_LIST_NAME};
        Cursor cursor = DB.query(HierarchialList.GroceryList.GROCERY_LIST_TABLE, columns, null, null, null, null, HierarchialList.GroceryList.GROCERY_LIST_ID);
        ArrayList<GroceryList> groceryLists = new ArrayList<>();
        while (cursor.moveToNext()) {
            GroceryList groceryList = new GroceryList();
            groceryList.setGListId(Long.parseLong(cursor.getString(0)));
            groceryList.setGroceryListName(cursor.getString(1));
            groceryLists.add(groceryList);
        }
        return groceryLists;
    }

    // Get a Grocery List given the ID
    public GroceryList getGroceryListById(long groceryListId) {
        String[] columns = new String[]{HierarchialList.GroceryList.GROCERY_LIST_ID, HierarchialList.GroceryList.GROCERY_LIST_NAME};
        Cursor cursor = DB.query(HierarchialList.GroceryList.GROCERY_LIST_TABLE, columns, HierarchialList.GroceryList.GROCERY_LIST_ID + "=" + groceryListId, null, null, null, null);
        GroceryList groceryList = new GroceryList();
        cursor.moveToFirst();
        groceryList.setGListId(Long.parseLong(cursor.getString(0)));
        groceryList.setGroceryListName(cursor.getString(1));

        return groceryList;
    }

    // Get an Item Type by its ID
    public itemType getItemTypeByItemTypeId(long itemTypeId) {
        String[] columns = new String[]{HierarchialList.ItemType.ITEM_TYPE_ID, HierarchialList.ItemType.ITEM_TYPE_NAME};
        Cursor cursor = DB.query(HierarchialList.ItemType.ITEM_TYPE_TABLE, columns, HierarchialList.ItemType.ITEM_TYPE_ID + " = " + itemTypeId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        itemType itemType = new itemType();
        itemType.setItemTypeId(Long.parseLong(cursor.getString(0)));
        itemType.setItemTypeName(cursor.getString(1));

        return itemType;
    }

    // Get an Item by its ID
    public item getItemByItemId(long itemId) {
        String[] columns = new String[]{HierarchialList.Item.ITEM_ID, HierarchialList.Item.ITEM_TYPE_ID, HierarchialList.Item.ITEM_NAME};
        Cursor cursor = DB.query(HierarchialList.Item.ITEM_TABLE, columns, HierarchialList.Item.ITEM_ID + " = " + itemId, null, null, null, null);
        item item = new item();
        cursor.moveToFirst();
        item.setItemId(Long.parseLong(cursor.getString(0)));
        item.setItemName(cursor.getString(2));
        item.setItemType(getItemTypeByItemTypeId(Long.parseLong(cursor.getString(1))));

        return item;
    }

    //Get all the Grocery List Items by Grocery List ID. This will also get the Items and Item Types that correspond to the Grocery List Items
    public ArrayList getGroceryListItemByGroceryListId(int groceryListId) {
        String[] columns = new String[]{HierarchialList.GroceryListItem.GROCERY_LIST_ID,
                HierarchialList.GroceryListItem.ITEM_ID, HierarchialList.GroceryListItem.QUANTITY,
                HierarchialList.GroceryListItem.QUANTITY_UNIT, HierarchialList.GroceryListItem.IS_CHECKED};
        Cursor cursor = DB.query(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE, columns, HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListId, null, null, null, null);
        ArrayList<GroceryListItem> groceryListItems = new ArrayList<>();
        while (cursor != null && cursor.moveToNext()) {
            GroceryListItem groceryListItem = new GroceryListItem();
            groceryListItem.setGroceryListId(Long.parseLong(cursor.getString(0)));
            groceryListItem.setItem(getItemByItemId(Long.parseLong(cursor.getString(1))));
            groceryListItem.setQuantity(Integer.parseInt(cursor.getString(2)));
            groceryListItem.setQuantityUnit(cursor.getString(3));
            groceryListItem.setChecked(Integer.parseInt(cursor.getString(4)) == 1 ? true : false);
            groceryListItems.add(groceryListItem);
        }
        return groceryListItems;
    }

    //Get the Grocery List Items for a certain Grocery List grouped and ordered by Item Type. Uses a custom raw Query.
    public ArrayList getGroceryListItemsGroupedByItemType(long groceryListId) {
        Cursor cursor = DB.rawQuery("SELECT GroceryListItem.GroceryListId, GroceryListItem.ItemId, GroceryListItem.Quantity, GroceryListItem.QuantityUnit, GroceryListItem.IsChecked FROM GroceryListItem INNER JOIN Item ON GroceryListItem.ItemId = Item.ItemId WHERE groceryListId = " +
                groceryListId + " ORDER BY Item.ItemTypeId", null);
        ArrayList<GroceryListItem> groceryListItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            GroceryListItem groceryListItem = new GroceryListItem();
            groceryListItem.setGroceryListId(Long.parseLong(cursor.getString(0)));
            groceryListItem.setItem(getItemByItemId(Long.parseLong(cursor.getString(1))));
            groceryListItem.setQuantity(Integer.parseInt(cursor.getString(2)));
            groceryListItem.setQuantityUnit(cursor.getString(3));
            groceryListItem.setChecked(Integer.parseInt(cursor.getString(4)) == 1 ? true : false);
            groceryListItems.add(groceryListItem);
        }
        return groceryListItems;
    }

    //Update the Quantity of a Grocery List Item
    public int updateGroceryListItem(GroceryListItem groceryListItem, int newQuantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HierarchialList.GroceryListItem.QUANTITY, newQuantity);
        return DB.update(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                contentValues, HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + HierarchialList.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Update the Unit Type of a Grocery List Item
    public int updateGroceryListItem(GroceryListItem groceryListItem, String newQuantityUnit) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HierarchialList.GroceryListItem.QUANTITY_UNIT, newQuantityUnit);
        return DB.update(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                contentValues, HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + HierarchialList.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Update the checked status of a Grocery List Item
    public int updateGroceryListItem(GroceryListItem groceryListItem, boolean switchCheck) {
        ContentValues contentValues = new ContentValues();
        int switchCheckInt = (switchCheck ? 1 : 0);
        contentValues.put(HierarchialList.GroceryListItem.IS_CHECKED, switchCheckInt);
        return DB.update(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                contentValues, HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListItem.getGroceryListId() +
                        " AND " + HierarchialList.GroceryListItem.ITEM_ID + " = " + groceryListItem.getItem().getItemId(), null);
    }

    //Delete a Grocery List Item
    public void deleteGroceryListItem(long groceryListItem) {
        DB.delete(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE,
                HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + GroceryListItem.getGroceryListId() +
                        " AND " + HierarchialList.GroceryListItem.ITEM_ID + " = " + GroceryListItem.getItem().getItemId(), null);
    }

    //Uncheck all Grocery List Items
    public int uncheckAllGroceryListItems(long groceryListId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HierarchialList.GroceryListItem.IS_CHECKED, false);
        return DB.update(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE, contentValues,
                HierarchialList.GroceryListItem.GROCERY_LIST_ID + " = " + groceryListId, null);
    }

    //Get a list of all Item Types
    public ArrayList getAllItemTypes() {
        String[] columns = new String[]{HierarchialList.ItemType.ITEM_TYPE_ID, HierarchialList.ItemType.ITEM_TYPE_NAME};
        Cursor cursor = DB.query(HierarchialList.ItemType.ITEM_TYPE_TABLE, columns, null, null, null, null, null);
        ArrayList<itemType> itemTypes = new ArrayList<>();

        while (cursor.moveToNext()) {
            itemType itemType = new itemType();
            itemType.setItemTypeId(Long.parseLong(cursor.getString(0)));
            itemType.setItemTypeName(cursor.getString(1));
            itemTypes.add(itemType);
        }

        return itemTypes;
    }

    /*public long getItemTypeIdByItemTypeName(String itemTypeName) {
        String[] columns = new String[]{HierarchialList.ItemType.ITEM_TYPE_ID};
        Cursor cursor = db.query(HierarchialList.ItemType.ITEM_TYPE_TABLE, columns, HierarchialList.ItemType.ITEM_TYPE_NAME + "=" + itemTypeName, null, null, null, null);
        cursor.moveToFirst();
        return Long.parseLong(cursor.getString(0));
    }*/

    //Get all of the Items for a given Item Type ID
    public ArrayList getAllItemsByItemTypeId(long itemTypeId) {
        itemType itemType = getItemTypeByItemTypeId(itemTypeId);
        String[] columns = new String[]{HierarchialList.Item.ITEM_ID, HierarchialList.Item.ITEM_NAME};
        Cursor cursor = DB.query(HierarchialList.Item.ITEM_TABLE, columns,
                HierarchialList.Item.ITEM_TYPE_ID + " = " + itemTypeId, null, null, null, null);

        ArrayList<item> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            item item = new item();
            item.setItemType(itemType);
            item.setItemId(Long.parseLong(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            items.add(item);
        }

        return items;
    }

    //Get all items that are similar to a given search query name
    public ArrayList<item> getAllItemsByName(String name) {
        name = "'%" + name + "%'";
        String[] columns = new String[]{HierarchialList.Item.ITEM_ID, HierarchialList.Item.ITEM_NAME, HierarchialList.Item.ITEM_TYPE_ID};
        Cursor cursor = DB.query(HierarchialList.Item.ITEM_TABLE, columns,
                HierarchialList.Item.ITEM_NAME + " LIKE " + name, null, null, null, null);

        ArrayList<item> items = new ArrayList<>();

        while (cursor.moveToNext()) {
            item item = new item();
            itemType type = getItemTypeByItemTypeId(Long.parseLong(cursor.getString(2)));
            item.setItemType(type);
            item.setItemId(Long.parseLong(cursor.getString(0)));
            item.setItemName(cursor.getString(1));
            items.add(item);
        }

        return items;
    }
    //Delete all Grocery List Items for a given Grocery List
    public void deleteAllGroceryListItemFromGroceryList(long groceryListId) {
        DB.delete(HierarchialList.GroceryListItem.GROCERY_LIST_ITEM_TABLE, HierarchialList.GroceryListItem.GROCERY_LIST_ID + "=" + groceryListId, null);

    }

}