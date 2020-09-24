package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomepage extends AppCompatActivity {

    Button addtpo,addstudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
        addtpo=(Button)findViewById(R.id.addtpo);
        addstudent=(Button)findViewById(R.id.addstudent);
        Intent i2=getIntent();
       addtpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickaddtpo();
            }
        });
    }
    public void onclickaddtpo()
    {
        Intent i1=new Intent(this,AddTpo.class);
        startActivity(i1);
    }
    public void onclickaddstudent(View view)
    {
        Intent i=new Intent(this,AddStudent.class);
        startActivity(i);
    }
}
