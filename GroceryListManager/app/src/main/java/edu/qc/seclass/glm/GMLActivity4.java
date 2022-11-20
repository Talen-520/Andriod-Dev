package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.grocerylistmanager.R;

public class GMLActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmlactivity4);
       // getSupportActionBar().setTitle("Items Showcase");

        goBackClicked();
    }

    public void goBackClicked(){

        Button backButton = (Button) findViewById(R.id.goBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }


}