package com.example.delya.serviceandroid;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.widget.Toast;

public class MyIntentService extends IntentService {
    private static final String ACTION_FOO = "com.example.delya.serviceandroid.action.FOO";

    private static final String EXTRA_DELTA = "com.example.delya.serviceandroid.extra.DELTA";

    public static final String STOP_COUNTING_KEY = "com.example.delya.serviceandroid.reciever.STOPCOUNT";

    private boolean isCounting = false;
    private boolean isStopCounting = false;

    BroadcastReceiver reciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isStopCounting = true;
        }
    };

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(reciever, new IntentFilter(STOP_COUNTING_KEY));
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(reciever);
        super.onDestroy();
    }

    public static void startActionFoo(Context context, long delta) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_DELTA, delta);
        context.startService(intent);
    }

    private void publishProgress(long count) {
        Intent intent = new Intent(MainActivity.COUNT_UPDATE_KEY);
        intent.putExtra("COUNT", ""+count);
        sendBroadcast(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final Long param1 = intent.getLongExtra(EXTRA_DELTA, 0);
                handleActionFoo(param1);
            }
        }
    }

    private void handleActionFoo(long delta) {

        if (isCounting) {
            return;
        }

        long count = 0;
        while (!isStopCounting) {
            SystemClock.sleep(500);
            count += delta;
            publishProgress(count);
        }

        isCounting = false;
        isStopCounting = false;

        Toast.makeText(this, "Count finished ", Toast.LENGTH_SHORT).show();
    }
}
