package com.example.tpo4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tpoDB.db";
    public static final String TABLE_TPO = "tpo";
    public static final String COLUMN_TPOID = "TpoID";
    public static final String COLUMN_TPONAME = "TpoName";

    public MyDBHandler2(AddTpo context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }



     public MyDBHandler2(TpoLoginPage context,String name, SQLiteDatabase.CursorFactory factory, int version)
     {
         super(context, DATABASE_NAME, factory, DATABASE_VERSION);
     }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE__TABLE = "CREATE TABLE " + TABLE_TPO + "(" + COLUMN_TPOID + " TEXT PRIMARY KEY," + COLUMN_TPONAME + " TEXT)";
        db.execSQL(CREATE__TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TPO);
        onCreate(db);

    }

    public void addHandler(Tpo t) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TPOID, t.getID());
        values.put(COLUMN_TPONAME, t.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_TPO, null, values);
        db.close();
    }
    public boolean checkUser(String username,String password)
    {
        String[] columns= {COLUMN_TPOID};
        int count=0;
        try (SQLiteDatabase db = getReadableDatabase()) {

            String selection = COLUMN_TPOID + " = ?" + " AND " + COLUMN_TPOID + " = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(TABLE_TPO, columns, selection, selectionArgs, null, null, null);
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
    public Tpo findHandler(String name) {
        String query = "Select * FROM " + TABLE_TPO + " WHERE " +
                COLUMN_TPONAME + " = '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Tpo t2=new Tpo();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            t2.setID(cursor.getString(0));
            t2.setName(cursor.getString(1));
            cursor.close();

        } else {
            t2 = null;
        }
        db.close();
        return t2;
    }

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_TPO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            result += "Tpo ID: "+result_0 + "\nTpo Name: " + result_1 + "\n-----------------------------------------------------------\n" +System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public boolean deleteHandler(String ID) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_TPO + " WHERE " + COLUMN_TPOID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Tpo t3=new Tpo();
        if (cursor.moveToFirst()) {
            t3.setID(cursor.getString(0));
            db.delete(TABLE_TPO, COLUMN_TPOID + "=?",
                    new String[] {
                            String.valueOf(t3.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(String ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_TPOID, ID);
        args.put(COLUMN_TPONAME, name);

        return db.update(TABLE_TPO, args, COLUMN_TPOID + "=" + ID, null) > 0;
    }
}

