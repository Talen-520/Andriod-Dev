package com.example.tipcalculator;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TipCalculatorActivity extends MainActivity {

    public void calledTipCalculatorActivity(View view){
        TextView fifteenPercentTip = findViewById(R.id.fifteenPercentTipValue);
        TextView twentyPercentTip = findViewById(R.id.twentyPercentTipValue);
        TextView twentyfivePercentTip = findViewById(R.id.twentyfivePercentTipValue);

        TextView fifteenPercentTotal = findViewById(R.id.fifteenPercentTotalValue);
        TextView twentyPercentTotal = findViewById(R.id.twentyPercentTotalValue);
        TextView twentyfivePercentTotal = findViewById(R.id.twentyfivePercentTotalValue);


        EditText CheckAmount = findViewById(R.id.checkAmountValue);
        EditText PartySize = findViewById(R.id.partySizeValue);
        if(CheckAmount.getText().toString().isEmpty()|| PartySize.getText().toString().isEmpty()){
            Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(TipCalculatorActivity.this, "invaild input", Toast.LENGTH_SHORT).show();
    }
}
