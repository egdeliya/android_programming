package com.example.delya.switchandcount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Delya on 19.03.2017.
 */

public class SecondActivity extends AppCompatActivity {
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mCount = getIntent().getExtras().getInt("Counter1", 0);

        TextView showCounter = (TextView)findViewById(R.id.textVeiwCounter2);
        showCounter.setText("Clicks number is " + mCount);
    }

    public void switchToMainActivity(View view) {
        Intent my_intent = new Intent(this, MainActivity.class);
        my_intent.putExtra("Counter2", ++mCount);
        startActivity(my_intent);
    }

}
