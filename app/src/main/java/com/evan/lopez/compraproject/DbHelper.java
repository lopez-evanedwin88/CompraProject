package com.evan.lopez.compraproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 7/14/2015.
 */
public class DbHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "Database";
    static final String TABLE_NAME = "Item";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,_name TEXT NOT NULL);";

    public DbHelper (Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }
}
