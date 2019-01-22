package com.thunderstorm.remindme2;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "reminder.db";
    public static final String TABLE_NAME = "reminder_data";
    public static final String TABLE_IDS = "notification_ids";
    public static final String COL1 = "ID";
    public static final String COL2 = "TITLE";
    public static final String COL3 = "DAYS";
    public static final String COL4 = "TIME";
    public static final String COLID = "ID";
    public static final String COLREMINDERID = "REMINDER_ID";
    public static final String COLNOTIFICATIONID = "NOTIFICATION_ID";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " TITLE TEXT, DAYS TEXT, TIME TEXT)";
        db.execSQL(createTable);

        String createTable2 = "CREATE TABLE " + TABLE_IDS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + " NOTIFICATION_ID INTEGER, REMINDER_ID INTEGER, FOREIGN KEY(REMINDER_ID) REFERENCES reminder_data(ID))";
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IDS);
        onCreate(db);
    }

    public boolean updateData(String title, String days, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, title);
        contentValues.put(COL3, days);
        contentValues.put(COL4, time);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public boolean addNotifyID(int reminder, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLREMINDERID, reminder);
        contentValues.put(COLNOTIFICATIONID, id);


        long result = db.insert(TABLE_IDS, null, contentValues);

        return result != -1;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return data;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public int getReminderID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = -1;

        Cursor data = db.rawQuery("SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = ?", new String[] {name}, null );
        if(data.moveToFirst()) {
            id = data.getInt(data.getColumnIndex(COL1));
        }

        return id;
    }

    public Cursor getNotifyID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_IDS + " WHERE " + COLREMINDERID +  " = " + id , null);

        return data;
    }

    public boolean deleteItem(String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBHelper.TABLE_NAME, DBHelper.COL2 + "= ?",new String[] {name}) > 0;

    }

    public boolean deleteID(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBHelper.TABLE_IDS, DBHelper.COLNOTIFICATIONID + " = " + id, null) > 0;

    }
}
