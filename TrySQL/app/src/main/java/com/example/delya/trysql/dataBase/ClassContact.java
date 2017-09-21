package com.example.delya.trysql.dataBase;

import android.provider.BaseColumns;

/**
 * Created by Delya on 07.09.2017.
 */

// класс, описывающий структуры базы данных
public class ClassContact {

    // чтобы никто случайно не инстанциировал наш класс
    public ClassContact() {}

    // внутренний класс, который определяет содержание таблицы
    public static abstract class ClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryId";
        public static final String COLUMN_NAME_TITLE  = "title";
    }
}
