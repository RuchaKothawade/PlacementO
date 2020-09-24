package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class StudentLogin extends AppCompatActivity {
    EditText nm;
    EditText ps;
    Button lgn;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        nm=(EditText)findViewById(R.id.UserID);
        ps=(EditText)findViewById(R.id.pass);
        lgn=(Button)findViewById(R.id.submit);
        dbHandler = new MyDBHandler(this, null, null, 1);
        Intent i=getIntent();
        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=nm.getText().toString().trim();
                String password=ps.getText().toString().trim();
                Boolean res=dbHandler.checkUser(user,password);
                if(res==true)
                {
                    Toast.makeText(StudentLogin.this,"Successfully LogedIn",Toast.LENGTH_SHORT).show();
                     nextmethod();
                }
                else
                {
                    Toast.makeText(StudentLogin.this,"LogIn Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void nextmethod()
    {
        Intent ni=new Intent(this,StudentHomePage.class);
        startActivity(ni);
    }
}
