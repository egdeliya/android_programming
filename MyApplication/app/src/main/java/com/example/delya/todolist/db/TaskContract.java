package com.example.delya.todolist.db;

import android.provider.BaseColumns;

/**
 * Created by Delya on 16.08.2017.
 */

public class TaskContract {
    public static final String DB_NAME = "com.example.delya.todolist.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}
