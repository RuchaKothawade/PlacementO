package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentHomePage extends AppCompatActivity {

    Button b, c, n, p;
    String st;
    TextView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);
        b = (Button) findViewById(R.id.vcmpd);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent i=new Intent(StudentHomePage.this,DisplayCompanyDetails.class);
                startActivity(i);
            }
        });

        n = (Button) findViewById(R.id.viewNotification);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // Intent gmail = new Intent(Intent.ACTION_VIEW);
              // mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationActivity");
              // startActivity(gmail);

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(intent);
            }
        });

    }
}

