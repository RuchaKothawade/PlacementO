package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayCompanyDetails extends AppCompatActivity {
    TextView tv;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_company_details);
        tv=findViewById(R.id.dcdetails);
        //st=getIntent().getExtras().getString("Value");
        MyDBHandler3 db= new MyDBHandler3(this, null, null, 1);
        st= db.loadHandler();
        tv.setText(st);
    }
}
