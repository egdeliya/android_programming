package com.example.delya.buttonclickcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView showNum;
    private EditText enterNumEditText;
    private TextView showResult;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_increase = (Button) findViewById(R.id.btn_increase);
        Button btn_decrease = (Button) findViewById(R.id.btn_decrease);
        Button btn_equals = (Button) findViewById(R.id.equals_btn);
        enterNumEditText = (EditText) findViewById(R.id.enterNum);
        showNum = (TextView) findViewById(R.id.showNum);
        showResult = (TextView) findViewById(R.id.result);

        btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                showNum.setText("" + counter);
            }
        });

        btn_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter > 0) {
                    counter--;
                    showNum.setText("" + counter);
                }
            }
        });

        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String input = enterNumEditText.getText().toString();
                    Log.d(TAG, "input is "+input);
                    int num = Integer.parseInt(input);
                    showResult.setText("" + (counter * num));
                } catch (NumberFormatException e) {
                    showResult.setText("wrong input");
                }
            }
        });
    }}