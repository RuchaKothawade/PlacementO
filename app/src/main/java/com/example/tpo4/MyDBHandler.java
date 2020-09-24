package com.example.tpo4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDB.db";


    public static final String TABLE_STUDENTS = "students";
    //columns in students table
    public static final String COLUMN_ID = "StudentID";
    public static final String COLUMN_NAME = "StudentName";
    public static final String COLUMN_BRANCH="StudentBranch";
    public static final String COLUMN_CGPA="StudentCgpa";
    public static final String COLUMN_EMAIL="StudentEmail";


    public MyDBHandler(AddStudent context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super((Context) context, DATABASE_NAME, factory, DATABASE_VERSION);
    }



    public MyDBHandler(StudentLogin context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_BRANCH + " TEXT," + COLUMN_CGPA + " FLOAT," + COLUMN_EMAIL +" TEXT )";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);

    }

    public void addHandler(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getID());
        values.put(COLUMN_NAME, student.getStudentName());
        values.put(COLUMN_BRANCH,student.getStudentBranch());
        values.put(COLUMN_CGPA,student.getStudentCGPA());
        values.put(COLUMN_EMAIL,student.getStudentEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    public Student findHandler(String studentname) {
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " +
                COLUMN_NAME + " = '" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            student.setBranch(cursor.getString(2));
            student.setCGPA(Float.parseFloat(cursor.getString(3)));
            student.setemailid(cursor.getString(4));

            cursor.close();

        } else {
            student = null;
        }
        db.close();
        return student;
    }
    public boolean checkUser(String username,String password)
    {
        String[] columns= {COLUMN_ID};
        int count=0;
        try (SQLiteDatabase db = getReadableDatabase()) {

            String selection = COLUMN_ID + " = ?" + " AND " + COLUMN_ID + " = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(TABLE_STUDENTS, columns, selection, selectionArgs, null, null, null);
            count = cursor.getCount();
            cursor.close();
            db.close();
        }
        catch (Exception e)
        {

        }
        if(count>0)
        {
            return true;
        }
        else
            return false;

    }


    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            float result_3 = cursor.getFloat(3);
            String result_4 = cursor.getString(4);
            result += "id: "+String.valueOf(result_0) + "\nName: " + result_1 + "\nBranch: " + result_2 + "\nCGPA: " + String.valueOf(result_3) + "\nEmail: " + result_4 +"\n---------------------------------------------------------\n"+ System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_STUDENTS, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int ID, String name, String branch, float cgpa ,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_BRANCH,branch);
        args.put(COLUMN_CGPA,cgpa);
        args.put(COLUMN_EMAIL,email);
        return db.update(TABLE_STUDENTS, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}


