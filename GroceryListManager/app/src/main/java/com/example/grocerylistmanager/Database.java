package com.example.grocerylistmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public Database(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username TEXT PRIMARY key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }
    //for sigup purpose, but we only have 1 user
    // you can make similar one for additem  into database
    public Boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username",username);
        values.put("password",password);

        long result = db.insert("users",null,values);
        if(result==-1)return false;
        else
            return true;
    }
//check if username in the database, we only have 1 user
// so we dont need this method
// you can make similar one for checkitem in database
    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From user where username = admin",new String []{username});//set username here
        if (cursor.getCount()>0)return true;
        else return false;
    }

    public boolean checkPassword(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From user where username=admin AND password=admin",new String []{username,password});//set user&pass here
        if (cursor.getCount()>0)return true;
        else return false;
    }

}
