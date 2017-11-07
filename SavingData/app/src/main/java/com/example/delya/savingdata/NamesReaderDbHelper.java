package com.example.delya.savingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.delya.savingdata.NamesReaderContract.SQL_CREATE_ENTRIES;
import static com.example.delya.savingdata.NamesReaderContract.SQL_DELETE_ENTRIES;

/**
 * Created by Delya on 31.10.2017.
 */

public class NamesReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NamesReader.db";


    public NamesReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void putName(String name) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(NamesReaderContract.NamesEntry.COLUMN_NAME_NAME, name);

        // Insert the new row, returning the primary key value of the new row
        db.insert(NamesReaderContract.NamesEntry.TABLE_NAME,
                  null,
                  values);
    }

    public String getAllNames() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                NamesReaderContract.NamesEntry.COLUMN_NAME_NAME
        };

        Cursor cursor = db.query(
                NamesReaderContract.NamesEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                "",                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                ""                                 // The sort order
        );

        String ret = "";
        cursor.moveToFirst();
        do {
            ret = ret.concat(cursor.getString(cursor.getColumnIndex(NamesReaderContract.NamesEntry.COLUMN_NAME_NAME)));
            ret = ret.concat("\n");
        } while (cursor.moveToNext());

        return ret;

    }
}
