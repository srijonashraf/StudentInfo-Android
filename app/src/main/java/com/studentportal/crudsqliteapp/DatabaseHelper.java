package com.studentportal.crudsqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String TABLE_COL_1 = "id";
    public static final String TABLE_COL_2 = "name";
    public static final String TABLE_COL_3 = "surname";
    public static final String TABLE_COL_4 = "marks";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY, NAME TEXT, SURNAME TEXT, MARKS REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Performing Insert Operation on Database
    public boolean insertData(String id, String name, String surname, double marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_1, id);
        contentValues.put(TABLE_COL_2, name);
        contentValues.put(TABLE_COL_3, surname);
        contentValues.put(TABLE_COL_4, marks);

        // Insert contents into database
        long success = db.insert(TABLE_NAME, null, contentValues);

        return success != -1;
    }

    // Read all Data from Database using CURSOR to pick one by one row
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + TABLE_COL_1 + " ASC", null);
    }

    // Update Data of Database table
    public boolean updateData(String id, String name, String surname, double marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COL_2, name);
        contentValues.put(TABLE_COL_3, surname);
        contentValues.put(TABLE_COL_4, marks);

        db.update(TABLE_NAME, contentValues, TABLE_COL_1 + " = ?", new String[]{id});
        return true;
    }

    // Delete Data from Database table
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, TABLE_COL_1 + " = ?", new String[]{id});
    }
}