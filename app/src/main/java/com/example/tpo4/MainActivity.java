package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button admin,student,tpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin=(Button)findViewById(R.id.admin);
        student=(Button)findViewById(R.id.student);
        tpo=(Button)findViewById(R.id.tpo);
    }

    public void IamAdmin(View view)
    {
        Intent i=new Intent(this,AdminHomepage.class);
        startActivity(i);
    }
    public void IamStudent(View view)
    {
       Intent i1=new Intent(this,StudentLogin.class);
       startActivity(i1);
    }
   public void IamTpo(View view)
    {
       Intent i2=new Intent(this,TpoLoginPage.class);
       startActivity(i2);
    }

}
