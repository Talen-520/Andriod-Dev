package edu.qc.seclass.glm;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerylistmanager.R;

import java.util.ArrayList;

public class viewItems extends AppCompatActivity{

    private ArrayList<itemDBModel> list;
    private itemDB db;
    private itemAdapter adapter;
    private RecyclerView itemRV;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list3);

        list = new ArrayList<>();
        db = new itemDB(viewItems.this);

        list = db.showAllItems();

        adapter = new itemAdapter(list, viewItems.this);
        itemRV = findViewById(R.id.itemNameTV);

        LinearLayoutManager lLM = new LinearLayoutManager(viewItems.this, RecyclerView.VERTICAL, false);
        itemRV.setLayoutManager(lLM);

        itemRV.setAdapter(adapter);
    }
}
