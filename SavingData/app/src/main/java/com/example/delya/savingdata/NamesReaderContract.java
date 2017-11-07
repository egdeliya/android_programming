package com.example.delya.savingdata;

import android.provider.BaseColumns;

/**
 * Created by Delya on 31.10.2017.
 */

public final class NamesReaderContract {

    private static final String TEXT_TYPE = " TEXT";
//    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NamesEntry.TABLE_NAME + " (" +
                    NamesEntry._ID + " INTEGER PRIMARY KEY," +
                    NamesEntry.COLUMN_NAME_NAME + TEXT_TYPE +
            " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NamesEntry.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public NamesReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class NamesEntry implements BaseColumns {
        public static final String TABLE_NAME = "namesTable";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
