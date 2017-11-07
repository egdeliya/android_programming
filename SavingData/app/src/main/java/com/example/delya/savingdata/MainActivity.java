package com.example.delya.savingdata;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String KEY_PREF_NAME = "UserName1";
    private final String ID_PREF_HIGHSCORE = "com.exmple.delya.savingdata.Highscore";

     NamesReaderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new NamesReaderDbHelper(this);

        SharedPreferences sharedPreferences = getSharedPreferences(ID_PREF_HIGHSCORE, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_PREF_NAME, "world");

        setHello(name);

    }

    public void onSave(View v) {
        EditText text = (EditText)findViewById(R.id.editText);
        String name = text.getText().toString();
        if (name.isEmpty())
            return;

        setHello(name);

        SharedPreferences sharedPreferences = getSharedPreferences(ID_PREF_HIGHSCORE, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PREF_NAME, name);

        // это для многопоточности, чтобы было безопасно
        // apply в отличие от commit делает всё это в фоновом потоке
        editor.apply();

        mDbHelper.putName(name);

        String names = mDbHelper.getAllNames();
        Toast.makeText(this, names, Toast.LENGTH_SHORT).show();

    }

    void setHello(String name) {
        String s = "Hello, " + name + "!";
        TextView helloText = (TextView)findViewById(R.id.textView);
        helloText.setText(s);
    }
}
