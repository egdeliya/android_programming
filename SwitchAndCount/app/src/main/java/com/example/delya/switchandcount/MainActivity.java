package com.example.delya.switchandcount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mCount > 0) {
            mCount = getIntent().getExtras().getInt("Counter2", 0);

            TextView showCounter = (TextView)findViewById(R.id.textViewCounter1);
            showCounter.setText("Clicks number is " + mCount);
        }

    }

    public void switchToSecondActivity(View view) {
        Intent my_intent = new Intent(this, SecondActivity.class);
        my_intent.putExtra("Counter1", ++mCount);
        startActivity(my_intent);
    }
}
