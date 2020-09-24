package com.example.tpo4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    TextView lst;
    EditText studentid;
    EditText studentname;
    EditText studentbranch;
    EditText studentcgpa;
    EditText studentemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        lst = (TextView) findViewById(R.id.list);
        studentid = (EditText) findViewById(R.id.studentID);
        studentname = (EditText) findViewById(R.id.studentName);
        studentbranch = (EditText) findViewById(R.id.sbranch);
        studentcgpa = (EditText) findViewById(R.id.scgpa);
        studentemail = (EditText) findViewById(R.id.semail);

    }
    public void addStudent (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        int id = Integer.parseInt(studentid.getText().toString());
        String name = studentname.getText().toString();
        String branch= studentbranch.getText().toString();
        float cgpa=Float.parseFloat(studentcgpa.getText().toString());
        String email=studentemail.getText().toString();
        Student student = new Student(id,name,branch,cgpa,email);
        dbHandler.addHandler(student);
        studentid.setText("");
        studentname.setText("");
        studentbranch.setText("");
        studentcgpa.setText("");
        studentemail.setText("");
    }

    public void findStudent (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Student student = dbHandler.findHandler(studentname.getText().toString());
        if (student != null) {
            lst.setText(String.valueOf(student.getID()) +" "+ student.getStudentName());

        } else {
            lst.setText("No Match Found");
        }
    }

    public void loadStudents(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        studentid.setText("");
        studentname.setText("");
        studentbranch.setText("");
        studentcgpa.setText("");
        studentemail.setText("");
    }

    public void deleteStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(
                studentid.getText().toString()));
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
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.updateHandler(Integer.parseInt(studentid.getText().toString()), studentname.getText().toString(),studentbranch.getText().toString() ,Float.parseFloat(studentcgpa.getText().toString()) ,studentemail.getText().toString());
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

