package com.example.delya.trysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.delya.trysql.dataBase.ClassDbHelper;

public class MainActivity extends AppCompatActivity {

    private ClassDbHelper mDbHelper;
    private static final String Tag = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

