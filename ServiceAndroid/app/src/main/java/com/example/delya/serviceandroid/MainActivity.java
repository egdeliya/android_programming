package com.example.delya.serviceandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String COUNT_UPDATE_KEY = "com.example.delya.serviceandroid.reciever.UPDATECOUNT";

    BroadcastReceiver reciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String count = intent.getStringExtra("COUNT");
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(""+count);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(reciever, new IntentFilter(COUNT_UPDATE_KEY));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(reciever);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        if (view.getId() == R.id.buttonStart) {
            MyIntentService.startActionFoo(this, 15);
        } else if (view.getId() == R.id.buttonStop){
            Intent intent = new Intent(MyIntentService.STOP_COUNTING_KEY);
            sendBroadcast(intent);
        } else {

        }
    }
}

