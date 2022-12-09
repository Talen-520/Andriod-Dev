
package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import edu.qc.seclass.glm.R;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    //EditText username,password;
    Database DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Login activity
        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        DB = new Database(this);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this,"All Fields required",Toast.LENGTH_SHORT).show();
                else {
                    //Boolean checkUser = DB.checkUsername(user);
                    //Boolean checkpass = DB.checkPassword(user,pass);
                    if (user.equals("admin") && pass.equals("admin")){//we assume that username and password is admin
                    //\\if(checkpass == true){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MainActivity.this, GMLActivity2.class);
                        startActivity(intent);//login success, then we jump to next activity GroceryList
                    }
                    else
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button forgot = (Button) findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Toast.makeText(MainActivity.this, "user&password is admin", Toast.LENGTH_SHORT).show();
                }


        });

    }
}
