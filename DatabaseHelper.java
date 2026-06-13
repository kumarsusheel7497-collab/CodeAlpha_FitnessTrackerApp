package com.example.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Fitness.db";
    public static final String TABLE_NAME = "activities";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE activities(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "exercise TEXT," +
                "duration TEXT," +
                "calories TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS activities");
        onCreate(db);
    }

    public boolean insertData(String exercise, String duration, String calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("exercise", exercise);
        cv.put("duration", duration);
        cv.put("calories", calories);

        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM activities", null);
    }
}
