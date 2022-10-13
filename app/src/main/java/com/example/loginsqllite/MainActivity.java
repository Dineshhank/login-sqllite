package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword=findViewById(R.id.confirmpassword);
        signin =findViewById(R.id.Rsignin);
        signup = findViewById(R.id.signin);
        DB = new DBhelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();


                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)|| TextUtils.isEmpty(repass))
                    Toast.makeText(MainActivity.this,"all fields required",Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(repass)){
                        Boolean Checkuser = DB.checkusername(user);
                        if(Checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true) {
                                Toast.makeText(MainActivity.this,"Registration successfully",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registration failed",Toast.LENGTH_LONG).show();

                            }
                        }else{
                            Toast.makeText(MainActivity.this,"user already exists",Toast.LENGTH_LONG).show();

                        }

                    }else{
                        Toast.makeText(MainActivity.this,"passwords are not matching",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),loginactivity.class);
                startActivity(intent);
            }
        });

    }
}