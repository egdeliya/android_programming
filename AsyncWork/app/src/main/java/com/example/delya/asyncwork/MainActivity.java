package com.example.delya.asyncwork;

import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.os.AsyncTask.Status.FINISHED;
import static android.os.AsyncTask.Status.PENDING;

public class MainActivity extends AppCompatActivity {

    private Button mStopLongOperationBtn;
    private Button mLongOperationBtn;
    private ProgressBar mProgress;
    private HeavyTask mTask;

    private class HeavyTask extends AsyncTask<Integer, Long, Long> {

        @Override
        protected Long doInBackground(Integer... params) {
            int size = params.length;
            int totalComplete = 0;
            for (int i = 0; i < size; ++i) {
                if (isCancelled()) {
                    break;
                }
                SystemClock.sleep(params[i]);
                totalComplete += 1;

                int percent = totalComplete * 100 / size;
                publishProgress(Long.valueOf(percent));
            }

            return Long.valueOf(totalComplete);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Выполнение начато...", Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "Выполнено " + aLong + " заданий", Toast.LENGTH_SHORT)
                    .show();
            mTask = null;
            mStopLongOperationBtn.setVisibility(View.GONE);

        }

        @Override
        protected void onCancelled(Long aLong) {
            super.onCancelled();
            Toast.makeText(getApplicationContext(), "Выполнение прервано, но выполнено " + aLong + " заданий" , Toast.LENGTH_SHORT)
                    .show();
            mStopLongOperationBtn.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            mProgress.setProgress(values[0].intValue());

//            Toast.makeText(getApplicationContext(), "Выполнено " + values[0] + "%", Toast.LENGTH_SHORT)
//                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setMax(100);

        mLongOperationBtn = (Button) findViewById(R.id.myButton);
        mLongOperationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTask != null) {
                    Toast.makeText(getApplicationContext(), "Уже выполняется", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                mTask = new HeavyTask();
                mTask.execute(2000, 1000, 5000, 1000, 1000);
                mStopLongOperationBtn.setVisibility(View.VISIBLE);
            }
        });

        mStopLongOperationBtn = (Button)findViewById(R.id.cancelOperation);
        mStopLongOperationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTask != null) {
                    boolean cancelResult = mTask.cancel(false);
                    if (cancelResult) {
                        Toast.makeText(getApplicationContext(), "Выполнение прервано!", Toast.LENGTH_SHORT)
                                .show();
                    }
                    mTask = null;
                }
            }
        });

        mStopLongOperationBtn.setVisibility(View.GONE);
    }
}
