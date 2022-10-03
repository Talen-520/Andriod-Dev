package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //EditText CheckAmount = findViewById(R.id.checkAmountValue);
   // EditText PartySize = findViewById(R.id.partySizeValue);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        buttonCompute = (Button) findViewById (R.id.buttonCompute);
        buttonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputAmount = Integer.valueOf(CheckAmount.getText().toString());
                fifteenPercentTip.setText(inputAmount + "thats it");

                showToast(String.valueOf(inputAmount));

            }
        });
*/

    }


    public void calledTipCalculatorActivity(View view){
        TextView fifteenPercentTip = findViewById(R.id.fifteenPercentTipValue);
        TextView twentyPercentTip = findViewById(R.id.twentyPercentTipValue);
        TextView twentyfivePercentTip = findViewById(R.id.twentyfivePercentTipValue);

        TextView fifteenPercentTotal = findViewById(R.id.fifteenPercentTotalValue);
        TextView twentyPercentTotal = findViewById(R.id.twentyPercentTotalValue);
        TextView twentyfivePercentTotal = findViewById(R.id.twentyfivePercentTotalValue);


        EditText CheckAmount = findViewById(R.id.checkAmountValue);
        EditText PartySize = findViewById(R.id.partySizeValue);
        if(CheckAmount.getText().toString().isEmpty()|| PartySize.getText().toString().isEmpty()){Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        return;
        }

        double inputAmount = Integer.parseInt(CheckAmount.getText().toString());
        if(inputAmount < 0 ){Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();

        }
        double numOfpeople = Integer.parseInt(PartySize.getText().toString());
        if(numOfpeople <= 0 ){Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        }

        double fifteenPercentTipresult = (inputAmount/numOfpeople) * 0.15;
        double twentyPercentTipresult = (inputAmount/numOfpeople) * 0.2;
        double twentyfivePercentTipresult = (inputAmount/numOfpeople) * 0.25;
        //inputAmount=(int)result;

        double fifteenPercentTotalresult = (inputAmount/numOfpeople) + fifteenPercentTipresult;
        double twentyPercentTotalresult = (inputAmount/numOfpeople) + twentyPercentTipresult;
        double twentyfivePercentTotalresult = (inputAmount/numOfpeople) + twentyfivePercentTipresult;

        fifteenPercentTip.setText(""+Math.round(fifteenPercentTipresult));
        twentyPercentTip.setText(""+Math.round(twentyPercentTipresult));
        twentyfivePercentTip.setText(""+Math.round(twentyfivePercentTipresult));

        fifteenPercentTotal.setText(""+Math.round(fifteenPercentTotalresult));
        twentyPercentTotal.setText(""+Math.round(twentyPercentTotalresult));
        twentyfivePercentTotal.setText(""+Math.round(twentyfivePercentTotalresult));
        //CheckAmount.getText().toString()
    }
    private void showToast(String text){
        Toast.makeText(MainActivity.this, "invaild input", Toast.LENGTH_SHORT).show();
    }
}