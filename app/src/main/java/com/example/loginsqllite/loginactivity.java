package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {
        EditText username,password;
        Button signin;
        DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        username= findViewById(R.id.Lusername);
        password = findViewById(R.id.Lpass);
        signin = findViewById(R.id.Lsignin);
        DB = new DBhelper(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user= username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass))
                    Toast.makeText(loginactivity.this,"All filelds required",Toast.LENGTH_LONG).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(loginactivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(loginactivity.this,"login failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}