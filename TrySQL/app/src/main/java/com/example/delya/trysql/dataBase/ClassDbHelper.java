package com.example.delya.trysql.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Delya on 07.09.2017.
 */
// класс, отвечающий за создание, удаление, обновление базы данных
public class ClassDbHelper extends SQLiteOpenHelper {
    // при изменении схемы базы данных, нужно будет инкрементировать версию
    public static final int DATA_BASE_VERSION = 1;
    public static final String DATA_BASE_NAME = "SQLTRy.db";

    public ClassDbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ClassContact.ClassEntry.TABLE_NAME + " ( " +
                ClassContact.ClassEntry._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ClassContact.ClassEntry.COLUMN_NAME_ENTRY_ID + " TEXT NOT NULL, " +
                ClassContact.ClassEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // база данных просто кэширует данные из интернета, так что чтобы обновить её,
        // нужно просто удалить старые данные и скачать новые
        db.execSQL("DROP TABLE IF EXISTS " + ClassContact.ClassEntry.TABLE_NAME);
        onCreate(db);
    }

    public void onDawngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
