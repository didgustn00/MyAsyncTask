package com.example.amikom.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsyncTask extends AppCompatActivity {

    public static final String DEMO_ASYNC = "DemoAsync";
    private TextView tvStatus;
    private Button btn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_async_task);

        tvStatus = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(0);
                new DemoAsync().execute(10);
            }
        });

    }

    private class DemoAsync extends AsyncTask<Integer, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            tvStatus.setText("Starting");

        }

        @Override
        protected String doInBackground(Integer... params) {

            try {
                int count = params[0];
                for (int i = 0; i < count; i++) {
                    Thread.sleep(1500);
                    publishProgress((int) (((i + 1) / (float) count) * 100));
                }
            } catch (Exception e) {

            }
            return "done";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);
            tvStatus.setText("Running "+ progress[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}