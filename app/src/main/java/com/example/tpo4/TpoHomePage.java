package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TpoHomePage extends AppCompatActivity {
    Button c,n,p,s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_home_page);
        c=(Button)findViewById(R.id.addcomp);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method();
            }
        });

        n=(Button)findViewById(R.id.notification);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifymethod();
            }
        });


    }

    public void method()
    {
        Intent i=new Intent(this,CompanyDetails.class);
        startActivity(i);
    }

    public void notifymethod()
    {
        Intent i2 = new Intent(this,SendEmailNotifications.class);
        startActivity(i2);
    }
}
