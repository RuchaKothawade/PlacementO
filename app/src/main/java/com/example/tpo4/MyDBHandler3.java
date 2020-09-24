package com.example.tpo4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler3 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "companyDB.db";
    public static final String TABLE_COMPANY = "company";
    public static final String COLUMN_ID = "CID";
    public static final String COLUMN_NAME = "CName";
    public static final String COLUMN_DATE="CDate";
    public static final String COLUMN_CGPA="Cgpa";
    public static final String COLUMN_SAL="Csal";

    public MyDBHandler3(StudentHomePage context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public MyDBHandler3(DisplayCompanyDetails context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public MyDBHandler3(CompanyDetails context, String name, SQLiteDatabase.CursorFactory factory, int version  ) {
        super (context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_COMPANY + "(" + COLUMN_ID + " TEXT PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_DATE + " TEXT," + COLUMN_CGPA + " FLOAT," + COLUMN_SAL +" TEXT )";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        onCreate(db);

    }

    public void addHandler(Company c) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, c.getID());
        values.put(COLUMN_NAME, c.getCName());
        values.put(COLUMN_DATE,c.getDate());
        values.put(COLUMN_CGPA,c.getCGPA());
        values.put(COLUMN_SAL,c.getsalary());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_COMPANY, null, values);
        db.close();
    }

    public Company findHandler(String studentname) {
        String query = "Select * FROM " + TABLE_COMPANY + " WHERE " +
                COLUMN_NAME + " = '" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Company student = new Company();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(cursor.getString(0));
            student.setCName(cursor.getString(1));
            student.setDate(cursor.getString(2));
            student.setCGPA(Float.parseFloat(cursor.getString(3)));
            student.setsalary(cursor.getString(4));

            cursor.close();

        } else {
            student = null;
        }
        db.close();
        return student;
    }

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_COMPANY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            float result_3 = cursor.getFloat(3);
            String result_4 = cursor.getString(4);
            result += "\nID: "+result_0 + "\nName: " + result_1 + "\nDate: " + result_2 + "\nCGPA cutoff: " + String.valueOf(result_3) + "\nSalary: " + result_4 +"\n-------------------------------------------------------\n" +System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteHandler(String ID) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_COMPANY + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Company student = new Company();
        if (cursor.moveToFirst()) {
            student.setID(cursor.getString(0));
            db.delete(TABLE_COMPANY, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(String ID, String name, String branch, float cgpa ,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_DATE,branch);
        args.put(COLUMN_CGPA,cgpa);
        args.put(COLUMN_SAL,email);
        return db.update(TABLE_COMPANY, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}



