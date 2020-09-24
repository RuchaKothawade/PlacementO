package com.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompanyDetails extends AppCompatActivity {

    TextView lst;
    EditText studentid;
    EditText studentname;
    EditText studentbranch;
    EditText studentcgpa;
    EditText studentemail;
    String st;
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        lst = (TextView) findViewById(R.id.list);
        studentid = (EditText) findViewById(R.id.cID);
        studentname = (EditText) findViewById(R.id.cName);
        studentbranch = (EditText) findViewById(R.id.date);
        studentcgpa = (EditText) findViewById(R.id.cgpa);
        studentemail = (EditText) findViewById(R.id.ssal);
        b=(Button)findViewById(R.id.btnload);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadStudents(v);
                Intent i=new Intent(CompanyDetails.this,DisplayCompanyDetails.class);
                st=lst.getText().toString();
                i.putExtra("Value",st);
                startActivity(i);
                finish();
            }
        });

    }

    public void addStudent (View view) {
        MyDBHandler3 dbHandler = new MyDBHandler3(this, null, null, 1);
        String id = studentid.getText().toString();
        String name = studentname.getText().toString();
        String branch= studentbranch.getText().toString();
        float cgpa=Float.parseFloat(studentcgpa.getText().toString());
        String email=studentemail.getText().toString();
        Company student = new Company(id,name,branch,cgpa,email);
        dbHandler.addHandler(student);
        studentid.setText("");
        studentname.setText("");
        studentbranch.setText("");
        studentcgpa.setText("");
        studentemail.setText("");
    }

    public void findStudent (View view) {
        MyDBHandler3 dbHandler = new MyDBHandler3(this, null, null, 1);
        Company student = dbHandler.findHandler(studentname.getText().toString());
        if (student != null) {
            lst.setText(String.valueOf(student.getID()) +" "+ student.getCName());

        } else {
            lst.setText("No Match Found");
        }
    }

    public void loadStudents(View view) {
        MyDBHandler3 dbHandler = new MyDBHandler3(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        studentid.setText("");
        studentname.setText("");
        studentbranch.setText("");
        studentcgpa.setText("");
        studentemail.setText("");
    }

    public void deleteStudent(View view) {
        MyDBHandler3 dbHandler = new MyDBHandler3(this, null, null, 1);
        boolean result = dbHandler.deleteHandler(
                studentid.getText().toString());
        if (result) {
            studentid.setText("");
            studentname.setText("");
            studentbranch.setText("");
            studentcgpa.setText("");
            studentemail.setText("");
            lst.setText("Record Deleted");
        } else
            studentid.setText("No Match Found");
    }

    public void updateStudent(View view) {
        MyDBHandler3 dbHandler = new MyDBHandler3(this, null, null, 1);
        boolean result = dbHandler.updateHandler(studentid.getText().toString(), studentname.getText().toString(),studentbranch.getText().toString() ,Float.parseFloat(studentcgpa.getText().toString()) ,studentemail.getText().toString());
        if (result) {
            studentid.setText("");
            studentname.setText("");
            studentbranch.setText("");
            studentcgpa.setText("");
            studentemail.setText("");
            lst.setText("Record Updated");
        } else
            studentid.setText("No Match Found");
    }
}

